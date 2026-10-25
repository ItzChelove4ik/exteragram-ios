package org.telegram.tgnet.tl;

import java.util.ArrayList;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.utils.tlutils.AmountUtils$Amount;
import org.telegram.messenger.utils.tlutils.AmountUtils$Currency;
import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.TLMethod;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1;
import org.telegram.tgnet.TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1;
import org.telegram.tgnet.Vector;

/* loaded from: classes6.dex */
public class TL_stars {

    public static class CheckCanSendGiftResult extends TLObject {
        public CheckCanSendGiftResult() {
        }

        public static CheckCanSendGiftResult TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-706379148)) goto L5;
            TLObject r0 = new checkCanSendGiftResultFail();
        L10:
            return (CheckCanSendGiftResult) TLObject.TLdeserialize(CheckCanSendGiftResult.class, r0, r2, r3, r4);
        L5:
            if (r3 == 927967149) goto L7;
            r0 = null;
            goto L10
        L7:
            r0 = new checkCanSendGiftResultOk();
            goto L10
        }
    }

    public static class InputSavedStarGift extends TLObject {
        public InputSavedStarGift() {
        }

        public static InputSavedStarGift TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-251549057)) goto L5;
            TLObject r0 = new TL_inputSavedStarGiftChat();
        L13:
            return (InputSavedStarGift) TLObject.TLdeserialize(InputSavedStarGift.class, r0, r2, r3, r4);
        L5:
            if (r3 != 545636920) goto L7;
            r0 = new TL_inputSavedStarGiftSlug();
            goto L13
        L7:
            if (r3 == 1764202389) goto L9;
            r0 = null;
            goto L13
        L9:
            r0 = new TL_inputSavedStarGiftUser();
            goto L13
        }
    }

    public static abstract class InputStarGiftAuction extends TLObject {
        public InputStarGiftAuction() {
        }

        public static InputStarGiftAuction TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            return (InputStarGiftAuction) TLObject.TLdeserialize(InputStarGiftAuction.class, fromConstructor(r3), r2, r3, r4);
        }

        private static InputStarGiftAuction fromConstructor(int r1) {
            if (r1 == 48327832) goto L11;
            if (r1 == 2058715912) goto L9;
            return null;
        L9:
            return new TL_inputStarGiftAuctionSlug();
        L11:
            return new TL_inputStarGiftAuction();
        }
    }

    public static class PaidReactionPrivacy extends TLObject {
        public TLRPC.InputPeer peer;

        public PaidReactionPrivacy() {
        }

        public static PaidReactionPrivacy TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-596837136)) goto L5;
            TLObject r0 = new paidReactionPrivacyPeer();
        L13:
            return (PaidReactionPrivacy) TLObject.TLdeserialize(PaidReactionPrivacy.class, r0, r2, r3, r4);
        L5:
            if (r3 != 520887001) goto L7;
            r0 = new paidReactionPrivacyAnonymous();
            goto L13
        L7:
            if (r3 == 543872158) goto L9;
            r0 = null;
            goto L13
        L9:
            r0 = new paidReactionPrivacyDefault();
            goto L13
        }

        public long getDialogId() {
            if ((this instanceof paidReactionPrivacyDefault) == false) goto L6;
            return 0;
        L6:
            if ((this instanceof paidReactionPrivacyAnonymous) == false) goto L10;
            return UserObject.ANONYMOUS;
        L10:
            if ((this instanceof paidReactionPrivacyPeer) == true) goto L12;
            return 0;
        L12:
            return DialogObject.getPeerDialogId(this.peer);
        }
    }

    public static class SavedStarGift extends TLObject {
        public int can_craft_at;
        public int can_export_at;
        public int can_resell_at;
        public int can_transfer_at;
        public boolean can_upgrade;
        public ArrayList<Integer> collection_id;
        public long convert_stars;
        public int date;
        public long drop_original_details_stars;
        public int flags;
        public TLRPC.Peer from_id;
        public StarGift gift;
        public int gift_num;
        public TLRPC.TL_textWithEntities message;
        public int msg_id;
        public boolean name_hidden;
        public boolean pinned_to_top;
        public String prepaid_upgrade_hash;
        public boolean refunded;
        public long saved_id;
        public long transfer_stars;
        public boolean unsaved;
        public boolean upgrade_separate;
        public long upgrade_stars;

        public SavedStarGift() {
            this.collection_id = new ArrayList();
        }

        public static SavedStarGift TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            switch(r3) {
                case -1987861422: goto L11;
                case -539360103: goto L10;
                case -355041186: goto L9;
                case 430552434: goto L8;
                case 514213599: goto L7;
                case 949728281: goto L6;
                case 1105150972: goto L5;
                case 1616305061: goto L4;
                default: goto L3;
            };
        L3:
            TLObject r0 = null;
        L13:
            return (SavedStarGift) TLObject.TLdeserialize(SavedStarGift.class, r0, r2, r3, r4);
        L4:
            r0 = new TL_savedStarGift_layer202();
            goto L13
        L5:
            r0 = new TL_savedStarGift();
            goto L13
        L6:
            r0 = new TL_savedStarGift_layer221_2();
            goto L13
        L7:
            r0 = new TL_savedStarGift_layer211();
            goto L13
        L8:
            r0 = new TL_savedStarGift_layer214();
            goto L13
        L9:
            r0 = new TL_savedStarGift_layer221();
            goto L13
        L10:
            r0 = new TL_savedStarGift_layer209();
            goto L13
        L11:
            r0 = new TL_savedStarGift_layer219();
            goto L13
        }
    }

    /* loaded from: classes.dex */
    public static class StarGift extends TLObject {
        public ArrayList<StarGiftAttribute> attributes;
        public boolean auction;
        public String auction_slug;
        public int auction_start_date;
        public int availability_issued;
        public int availability_remains;
        public long availability_resale;
        public int availability_total;
        public TL_starGiftBackground background;
        public boolean birthday;
        public boolean burned;
        public boolean can_upgrade;
        public long convert_stars;
        public int craft_chance_permille;
        public boolean crafted;
        public int first_sale_date;
        public int flags;
        public String gift_address;
        public long gift_id;
        public int gifts_per_round;
        public TLRPC.Peer host_id;
        public long id;
        public int last_sale_date;
        public boolean limited;
        public boolean limited_per_user;
        public int locked_until_date;
        public int num;
        public int offer_min_stars;
        public String owner_address;
        public TLRPC.Peer owner_id;
        public String owner_name;
        public TLRPC.PeerColor peer_color;
        public boolean peer_color_available;
        public int per_user_remains;
        public int per_user_total;
        public TLRPC.Peer released_by;
        public boolean require_premium;
        public boolean resale_ton_only;
        public ArrayList<StarsAmount> resell_amount;
        public long resell_min_stars;
        public String slug;
        public boolean sold_out;
        public long stars;
        public TLRPC.Document sticker;
        public boolean theme_available;
        public TLRPC.Peer theme_peer;
        public String title;
        public long upgrade_stars;
        public int upgrade_variants;
        public long value_amount;
        public String value_currency;
        public long value_usd_amount;

        public StarGift() {
            this.attributes = new ArrayList();
        }

        public static StarGift TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            return (StarGift) TLObject.TLdeserialize(StarGift.class, fromConstructor(r3), r2, r3, r4);
        }

        private static StarGift fromConstructor(int r0) {
            switch(r0) {
                case -2136190013: goto L48;
                case -2047825459: goto L46;
                case -1365150482: goto L44;
                case -1329630181: goto L42;
                case -970274264: goto L40;
                case -218202550: goto L38;
                case -164136786: goto L36;
                case 12386139: goto L34;
                case 46953416: goto L32;
                case 463097215: goto L30;
                case 468707429: goto L28;
                case 648369470: goto L26;
                case 825922887: goto L24;
                case 880997154: goto L22;
                case 973640632: goto L20;
                case 975654224: goto L18;
                case 1237678029: goto L16;
                case 1453155529: goto L14;
                case 1549979985: goto L12;
                case 1678891913: goto L10;
                case 1779697613: goto L8;
                case 2139438098: goto L6;
                default: goto L3;
            };
        L3:
            return null;
        L6:
            return new TL_starGift_layer209();
        L8:
            return new TL_starGiftUnique_layer196();
        L10:
            return new TL_starGiftUnique_layer206();
        L12:
            return new TL_starGiftUnique_layer202();
        L14:
            return new TL_starGiftUnique_layer221();
        L16:
            return new TL_starGift_layer195();
        L18:
            return new TL_starGiftUnique_layer211();
        L20:
            return new TL_starGiftUnique_layer215();
        L22:
            return new TL_starGiftUnique_layer197();
        L24:
            return new TL_starGift();
        L26:
            return new TL_starGiftUnique_layer213();
        L28:
            return new TL_starGiftUnique_layer214();
        L30:
            return new TL_starGift_layer219();
        L32:
            return new TL_starGift_layer202();
        L34:
            return new TL_starGift_layer212();
        L36:
            return new TL_starGiftUnique_layer210();
        L38:
            return new TL_starGiftUnique_layer198();
        L40:
            return new TL_starGift_layer206();
        L42:
            return new TL_starGiftUnique_layer219();
        L44:
            return new TL_starGift_layer190();
        L46:
            return new TL_starGiftUnique();
        L48:
            return new TL_starGift_layer217();
        }

        public TLRPC.Document getDocument() {
            TLRPC.Document r0 = this.sticker;
            if (r0 == null) goto L5;
            return r0;
        L5:
            ArrayList<StarGiftAttribute> r4 = this.attributes;
            int r02 = r4.size();
            int r1 = 0;
        L6:
            if (r1 >= r02) goto L11;
            StarGiftAttribute r2 = r4.get(r1);
            r1 = r1 + 1;
            StarGiftAttribute r22 = r2;
            if ((r22 instanceof starGiftAttributeModel) == false) goto L6;
            return ((starGiftAttributeModel) r22).document;
        L11:
            return null;
        }

        public AmountUtils$Amount getResellAmount(AmountUtils$Currency r7) {
            ArrayList<StarsAmount> r0 = this.resell_amount;
            if (r0 == null) goto L16;
            if (r0.isEmpty() == true) goto L16;
            ArrayList<StarsAmount> r6 = this.resell_amount;
            int r02 = r6.size();
            int r3 = 0;
        L8:
            if (r3 >= r02) goto L14;
            StarsAmount r4 = r6.get(r3);
            r3 = r3 + 1;
            StarsAmount r42 = r4;
            if (r42.getCurrency() != r7) goto L8;
            return AmountUtils$Amount.of(r42);
        L14:
            return AmountUtils$Amount.fromNano(0, r7);
        L16:
            return AmountUtils$Amount.fromNano(0, r7);
        }

        @Deprecated
        public long getResellStars() {
            AmountUtils$Amount r2 = getResellAmount(AmountUtils$Currency.STARS);
            if (r2 != null) goto L5;
            return 0;
        L5:
            return r2.asDecimal();
        }
    }

    public static class StarGiftAttribute extends TLObject {
        public boolean crafted;
        public int flags;
        public String name;
        public StarGiftAttributeRarity rarity;

        public StarGiftAttribute() {
        }

        public static StarGiftAttribute TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            switch(r3) {
                case -1809377438: goto L12;
                case -1624963868: goto L11;
                case -1070837941: goto L10;
                case -650279524: goto L9;
                case -524291476: goto L8;
                case 330104601: goto L7;
                case 970559507: goto L6;
                case 1315997162: goto L5;
                case 1448235490: goto L4;
                default: goto L3;
            };
        L3:
            TLObject r0 = null;
        L14:
            return (StarGiftAttribute) TLObject.TLdeserialize(StarGiftAttribute.class, r0, r2, r3, r4);
        L4:
            r0 = new starGiftAttributeModel();
            goto L14
        L5:
            r0 = new starGiftAttributePattern();
            goto L14
        L6:
            r0 = new starGiftAttributeModel_layer221();
            goto L14
        L7:
            r0 = new starGiftAttributePattern_layer221();
            goto L14
        L8:
            r0 = new starGiftAttributeOriginalDetails();
            goto L14
        L9:
            r0 = new starGiftAttributeBackdrop_layer221();
            goto L14
        L10:
            r0 = new starGiftAttributeOriginalDetails_layer197();
            goto L14
        L11:
            r0 = new starGiftAttributeBackdrop();
            goto L14
        L12:
            r0 = new starGiftAttributeBackdrop_layer202();
            goto L14
        }

        public int getRarityPermille() {
            StarGiftAttributeRarity r1 = this.rarity;
            if ((r1 instanceof TL_starGiftAttributeRarity) == true) goto L5;
            return 0;
        L5:
            return ((TL_starGiftAttributeRarity) r1).permille;
        }
    }

    public static class StarGiftAttributeId extends TLObject {
        public int backdrop_id;
        public long document_id;

        public StarGiftAttributeId() {
        }

        public static StarGiftAttributeId TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != 520210263) goto L5;
            TLObject r0 = new starGiftAttributeIdBackdrop();
        L13:
            return (StarGiftAttributeId) TLObject.TLdeserialize(StarGiftAttributeId.class, r0, r2, r3, r4);
        L5:
            if (r3 != 1219145276) goto L7;
            r0 = new starGiftAttributeIdModel();
            goto L13
        L7:
            if (r3 == 1242965043) goto L9;
            r0 = null;
            goto L13
        L9:
            r0 = new starGiftAttributeIdPattern();
            goto L13
        }
    }

    public static class StarGiftAttributeRarity extends TLObject {
        public StarGiftAttributeRarity() {
        }

        public static StarGiftAttributeRarity TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            return (StarGiftAttributeRarity) TLObject.TLdeserialize(StarGiftAttributeRarity.class, fromConstructor(r3), r2, r3, r4);
        }

        private static StarGiftAttributeRarity fromConstructor(int r0) {
            switch(r0) {
                case -822614104: goto L14;
                case -607231095: goto L12;
                case -259174037: goto L10;
                case 910391095: goto L8;
                case 2029777832: goto L6;
                default: goto L3;
            };
        L3:
            return null;
        L6:
            return new TL_starGiftAttributeRarityEpic();
        L8:
            return new TL_starGiftAttributeRarity();
        L10:
            return new TL_starGiftAttributeRarityRare();
        L12:
            return new TL_starGiftAttributeRarityUncommon();
        L14:
            return new TL_starGiftAttributeRarityLegendary();
        }
    }

    public static class StarGiftAuctionRound extends TLObject {
        public int current_window;
        public int duration;
        public int extend_top;
        public int num;

        public StarGiftAuctionRound() {
        }

        public static StarGiftAuctionRound TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != 178266597) goto L5;
            TLObject r0 = new TL_starGiftAuctionRoundExtendable();
        L10:
            return (StarGiftAuctionRound) TLObject.TLdeserialize(StarGiftAuctionRound.class, r0, r2, r3, r4);
        L5:
            if (r3 == 984483112) goto L7;
            r0 = null;
            goto L10
        L7:
            r0 = new TL_starGiftAuctionRound();
            goto L10
        }
    }

    public static abstract class StarGiftAuctionState extends TLObject {
        public StarGiftAuctionState() {
        }

        public static StarGiftAuctionState TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            return (StarGiftAuctionState) TLObject.TLdeserialize(StarGiftAuctionState.class, fromConstructor(r3), r2, r3, r4);
        }

        private static StarGiftAuctionState fromConstructor(int r1) {
            if (r1 == (-1758614593)) goto L15;
            if (r1 == (-30197422)) goto L13;
            if (r1 == 1998212710) goto L11;
            return null;
        L11:
            return new TL_starGiftAuctionState();
        L13:
            return new TL_starGiftAuctionStateNotModified();
        L15:
            return new TL_starGiftAuctionStateFinished();
        }
    }

    public static class StarGiftCollections extends TLObject {
        public ArrayList<TL_starGiftCollection> collections;

        public StarGiftCollections() {
            this.collections = new ArrayList();
        }

        public static StarGiftCollections TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-1977011469)) goto L5;
            TLObject r0 = new TL_starGiftCollections();
        L10:
            return (StarGiftCollections) TLObject.TLdeserialize(StarGiftCollections.class, r0, r2, r3, r4);
        L5:
            if (r3 == (-1598402793)) goto L7;
            r0 = null;
            goto L10
        L7:
            r0 = new TL_starGiftCollectionsNotModified();
            goto L10
        }
    }

    public static class StarGiftUpgradePrice extends TLObject {
        public static final int constructor = -1712704739;
        public int date;
        public long upgrade_stars;

        public StarGiftUpgradePrice() {
        }

        public static StarGiftUpgradePrice TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-1712704739) == r3) goto L5;
            StarGiftUpgradePrice r0 = null;
        L7:
            return (StarGiftUpgradePrice) TLObject.TLdeserialize(StarGiftUpgradePrice.class, r0, r2, r3, r4);
        L5:
            r0 = new StarGiftUpgradePrice();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.date = r2.readInt32(r3);
            this.upgrade_stars = r2.readInt64(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.date);
            r3.writeInt64(this.upgrade_stars);
        }
    }

    public static class StarGifts extends TLObject {
        public StarGifts() {
        }

        public static StarGifts TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-1551326360)) goto L5;
            TLObject r0 = new TL_starGiftsNotModified();
        L10:
            return (StarGifts) TLObject.TLdeserialize(StarGifts.class, r0, r2, r3, r4);
        L5:
            if (r3 == 785918357) goto L7;
            r0 = null;
            goto L10
        L7:
            r0 = new TL_starGifts();
            goto L10
        }
    }

    /* loaded from: classes.dex */
    public static abstract class StarsAmount extends TLObject {
        public long amount;
        public int nanos;

        public StarsAmount() {
        }

        public static StarsAmount TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-1145654109)) goto L5;
            TLObject r0 = new TL_starsAmount();
        L10:
            return (StarsAmount) TLObject.TLdeserialize(StarsAmount.class, r0, r2, r3, r4);
        L5:
            if (r3 == 1957618656) goto L7;
            r0 = null;
            goto L10
        L7:
            r0 = new TL_starsTonAmount();
            goto L10
        }

        public static StarsAmount ofStars(long r1) {
            TL_starsAmount r0 = new TL_starsAmount();
            r0.amount = r1;
            return r0;
        }

        public boolean equals(StarsAmount r6) {
            if (r6 != null) goto L6;
            return false;
        L6:
            if (this.amount == r6.amount) goto L8;
        L11:
            return false;
        L8:
            if (this.nanos != r6.nanos) goto L11;
            return true;
        }

        public abstract AmountUtils$Currency getCurrency();

        public boolean negative() {
            long r0 = this.amount;
            if (r0 != 0) goto L9;
            if (this.nanos >= 0) goto L7;
            return true;
        L7:
            return false;
        L9:
            if (r0 >= 0) goto L11;
            return true;
        L11:
            return false;
        }

        public boolean positive() {
            long r0 = this.amount;
            if (r0 != 0) goto L9;
            if (this.nanos <= 0) goto L7;
            return true;
        L7:
            return false;
        L9:
            if (r0 <= 0) goto L11;
            return true;
        L11:
            return false;
        }

        public double toDouble() {
            return this.amount + (this.nanos / 1.0E9d);
        }
    }

    public static class StarsStatus extends TLObject {
        public StarsAmount balance;
        public ArrayList<TLRPC.Chat> chats;
        public int flags;
        public ArrayList<StarsTransaction> history;
        public String next_offset;
        public ArrayList<StarsSubscription> subscriptions;
        public long subscriptions_missing_balance;
        public String subscriptions_next_offset;
        public ArrayList<TLRPC.User> users;

        public StarsStatus() {
            this.balance = StarsAmount.ofStars(0);
            this.subscriptions = new ArrayList();
            this.history = new ArrayList();
            this.chats = new ArrayList();
            this.users = new ArrayList();
        }

        public static StarsStatus TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-1141231252)) goto L5;
            TLObject r0 = new TL_payments_starsStatus_layer194();
        L10:
            return (StarsStatus) TLObject.TLdeserialize(StarsStatus.class, r0, r2, r3, r4);
        L5:
            if (r3 == 1822222573) goto L7;
            r0 = null;
            goto L10
        L7:
            r0 = new TL_payments_starsStatus();
            goto L10
        }
    }

    public static class StarsSubscription extends TLObject {
        public boolean bot_canceled;
        public boolean can_refulfill;
        public boolean canceled;
        public String chat_invite_hash;
        public int flags;
        public String id;
        public String invoice_slug;
        public boolean missing_balance;
        public TLRPC.Peer peer;
        public TLRPC.WebDocument photo;
        public TL_starsSubscriptionPricing pricing;
        public String title;
        public int until_date;

        public StarsSubscription() {
        }

        public static StarsSubscription TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-797707802)) goto L5;
            TLObject r0 = new TL_starsSubscription_old();
        L13:
            return (StarsSubscription) TLObject.TLdeserialize(StarsSubscription.class, r0, r2, r3, r4);
        L5:
            if (r3 != 779004698) goto L7;
            r0 = new TL_starsSubscription();
            goto L13
        L7:
            if (r3 == 1401868056) goto L9;
            r0 = null;
            goto L13
        L9:
            r0 = new TL_starsSubscription_layer193();
            goto L13
        }
    }

    public static class StarsTransaction extends TLObject {
        public int ads_proceeds_from_date;
        public int ads_proceeds_to_date;
        public StarsAmount amount;
        public byte[] bot_payload;
        public boolean business_transfer;
        public int date;
        public String description;
        public ArrayList<TLRPC.MessageMedia> extended_media;
        public boolean failed;
        public int flags;
        public boolean floodskip;
        public int floodskip_number;
        public boolean gift;
        public int giveaway_post_id;
        public String id;
        public int msg_id;
        public boolean offer;
        public boolean paid_message;
        public int paid_messages;
        public StarsTransactionPeer peer;
        public boolean pending;
        public boolean phonegroup_message;
        public TLRPC.WebDocument photo;
        public boolean posts_search;
        public boolean premium_gift;
        public int premium_gift_months;
        public boolean reaction;
        public TLRPC.Peer received_by;
        public boolean refund;
        public TLRPC.Peer sent_by;
        public StarGift stargift;
        public boolean stargift_auction_bid;
        public boolean stargift_drop_original_details;
        public boolean stargift_prepaid_upgrade;
        public boolean stargift_resale;
        public boolean stargift_upgrade;
        public StarsAmount starref_amount;
        public int starref_commission_permille;
        public TLRPC.Peer starref_peer;
        public boolean subscription;
        public int subscription_period;
        public String title;
        public int transaction_date;
        public String transaction_url;

        public StarsTransaction() {
            this.amount = StarsAmount.ofStars(0);
            this.extended_media = new ArrayList();
        }

        public static StarsTransaction TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            return (StarsTransaction) TLObject.TLdeserialize(StarsTransaction.class, fromConstructor(r3), r2, r3, r4);
        }

        private static StarsTransaction fromConstructor(int r0) {
            switch(r0) {
                case -1549805238: goto L26;
                case -1442789224: goto L24;
                case -865044046: goto L22;
                case -321582812: goto L20;
                case -294313259: goto L18;
                case 178185410: goto L16;
                case 325426864: goto L14;
                case 766853519: goto L12;
                case 903148150: goto L10;
                case 1127934763: goto L8;
                case 1692387622: goto L6;
                default: goto L3;
            };
        L3:
            return null;
        L6:
            return new TL_starsTransaction_layer199();
        L8:
            return new TL_starsTransaction_layer186();
        L10:
            return new TL_starsTransaction_layer194();
        L12:
            return new TL_starsTransaction_layer185();
        L14:
            return new TL_starsTransaction();
        L16:
            return new TL_starsTransaction_layer191();
        L18:
            return new TL_starsTransaction_layer188();
        L20:
            return new TL_starsTransaction_layer199_2();
        L22:
            return new TL_starsTransaction_layer181();
        L24:
            return new TL_starsTransaction_layer182();
        L26:
            return new TL_starsTransaction_layer205();
        }
    }

    public static class StarsTransactionPeer extends TLObject {
        public TLRPC.Peer peer;

        public StarsTransactionPeer() {
        }

        public static StarsTransactionPeer TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            switch(r3) {
                case -1779253276: goto L11;
                case -1269320843: goto L10;
                case -670195363: goto L9;
                case -382740222: goto L8;
                case -110658899: goto L7;
                case 621656824: goto L6;
                case 1617438738: goto L5;
                case 2069236235: goto L4;
                default: goto L3;
            };
        L3:
            TLObject r0 = null;
        L13:
            return (StarsTransactionPeer) TLObject.TLdeserialize(StarsTransactionPeer.class, r0, r2, r3, r4);
        L4:
            r0 = new TL_starsTransactionPeerPlayMarket();
            goto L13
        L5:
            r0 = new TL_starsTransactionPeerAds();
            goto L13
        L6:
            r0 = new TL_starsTransactionPeerPremiumBot();
            goto L13
        L7:
            r0 = new TL_starsTransactionPeerAPI();
            goto L13
        L8:
            r0 = new TL_starsTransactionPeerFragment();
            goto L13
        L9:
            r0 = new TL_starsTransactionPeer();
            goto L13
        L10:
            r0 = new TL_starsTransactionPeerAppStore();
            goto L13
        L11:
            r0 = new TL_starsTransactionPeerUnsupported();
            goto L13
        }
    }

    public static class TL_AuctionBidLevel extends TLObject {
        public static final int constructor = 822231244;
        public long amount;
        public int date;
        public int pos;

        public TL_AuctionBidLevel() {
        }

        public static TL_AuctionBidLevel TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (822231244 == r3) goto L5;
            TL_AuctionBidLevel r0 = null;
        L7:
            return (TL_AuctionBidLevel) TLObject.TLdeserialize(TL_AuctionBidLevel.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_AuctionBidLevel();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.pos = r3.readInt32(r4);
            this.amount = r3.readInt64(r4);
            this.date = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.pos);
            r3.writeInt64(this.amount);
            r3.writeInt32(this.date);
        }
    }

    public static class TL_StarGiftActiveAuctionState extends TLObject {
        public static final int constructor = -753154979;
        public StarGift gift;
        public StarGiftAuctionState state;
        public TL_StarGiftAuctionUserState user_state;

        public TL_StarGiftActiveAuctionState() {
        }

        public static TL_StarGiftActiveAuctionState TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-753154979) == r3) goto L5;
            TL_StarGiftActiveAuctionState r0 = null;
        L7:
            return (TL_StarGiftActiveAuctionState) TLObject.TLdeserialize(TL_StarGiftActiveAuctionState.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_StarGiftActiveAuctionState();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.gift = StarGift.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.state = StarGiftAuctionState.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.user_state = TL_StarGiftAuctionUserState.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.gift.serializeToStream(r2);
            this.state.serializeToStream(r2);
            this.user_state.serializeToStream(r2);
        }
    }

    public static class TL_StarGiftAuctionAcquiredGift extends TLObject {
        public static final int constructor = 1118831432;
        public long bid_amount;
        public int date;
        public int flags;
        public int gift_num;
        public TLRPC.TL_textWithEntities message;
        public boolean name_hidden;
        public TLRPC.Peer peer;
        public int pos;
        public int round;

        public TL_StarGiftAuctionAcquiredGift() {
        }

        public static TL_StarGiftAuctionAcquiredGift TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (1118831432 == r3) goto L5;
            TL_StarGiftAuctionAcquiredGift r0 = null;
        L7:
            return (TL_StarGiftAuctionAcquiredGift) TLObject.TLdeserialize(TL_StarGiftAuctionAcquiredGift.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_StarGiftAuctionAcquiredGift();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.name_hidden = TLObject.hasFlag(r0, 1);
            this.peer = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.date = r3.readInt32(r4);
            this.bid_amount = r3.readInt64(r4);
            this.round = r3.readInt32(r4);
            this.pos = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L6;
            this.message = TLRPC.TL_textWithEntities.TLdeserialize(r3, r3.readInt32(r4), r4);
        L6:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.gift_num = r3.readInt32(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r5) {
            r5.writeInt32(constructor);
            boolean r2 = true;
            int r0 = TLObject.setFlag(this.flags, 1, this.name_hidden);
            this.flags = r0;
            if (this.message != null) goto L6;
            r2 = false;
        L6:
            int r02 = TLObject.setFlag(r0, 2, r2);
            this.flags = r02;
            r5.writeInt32(r02);
            this.peer.serializeToStream(r5);
            r5.writeInt32(this.date);
            r5.writeInt64(this.bid_amount);
            r5.writeInt32(this.round);
            r5.writeInt32(this.pos);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L10;
            this.message.serializeToStream(r5);
        L10:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L13;
            r5.writeInt32(this.gift_num);
            return;
        }
    }

    public static class TL_StarGiftAuctionUserState extends TLObject {
        public static final int constructor = 787403204;
        public int acquired_count;
        public long bid_amount;
        public int bid_date;
        public int flags;
        public long min_bid_amount;
        public TLRPC.Peer peer;
        public boolean returned;

        public TL_StarGiftAuctionUserState() {
        }

        public static TL_StarGiftAuctionUserState TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (787403204 == r3) goto L5;
            TL_StarGiftAuctionUserState r0 = null;
        L7:
            return (TL_StarGiftAuctionUserState) TLObject.TLdeserialize(TL_StarGiftAuctionUserState.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_StarGiftAuctionUserState();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.returned = TLObject.hasFlag(r0, 2);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            this.bid_amount = r3.readInt64(r4);
            this.bid_date = r3.readInt32(r4);
            this.min_bid_amount = r3.readInt64(r4);
            this.peer = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.acquired_count = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.returned);
            this.flags = r0;
            r4.writeInt32(r0);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            r4.writeInt64(this.bid_amount);
            r4.writeInt32(this.bid_date);
            r4.writeInt64(this.min_bid_amount);
            this.peer.serializeToStream(r4);
        L5:
            r4.writeInt32(this.acquired_count);
        }
    }

    public static class TL_changeStarsSubscription extends TLObject {
        public static final int constructor = -948500360;
        public Boolean canceled;
        public int flags;
        public TLRPC.InputPeer peer;
        public String subscription_id;

        public TL_changeStarsSubscription() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = this.flags;
            if (this.canceled == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 1, r1);
            this.flags = r02;
            r4.writeInt32(r02);
            this.peer.serializeToStream(r4);
            r4.writeString(this.subscription_id);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L10;
            r4.writeBool(this.canceled.booleanValue());
            return;
        L10:
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_fulfillStarsSubscription extends TLObject {
        public static final int constructor = -866391117;
        public TLRPC.InputPeer peer;
        public String subscription_id;

        public TL_fulfillStarsSubscription() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            r2.writeString(this.subscription_id);
        }
    }

    public static class TL_getStarsSubscriptions extends TLObject {
        public static final int constructor = 52761285;
        public int flags;
        public boolean missing_balance;
        public String offset;
        public TLRPC.InputPeer peer;

        public TL_getStarsSubscriptions() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return StarsStatus.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.missing_balance);
            this.flags = r0;
            r4.writeInt32(r0);
            this.peer.serializeToStream(r4);
            r4.writeString(this.offset);
        }
    }

    public static final class TL_inputSavedStarGiftChat extends InputSavedStarGift {
        public static final int constructor = -251549057;
        public TLRPC.InputPeer peer;
        public long saved_id;

        public TL_inputSavedStarGiftChat() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.peer = TLRPC.InputPeer.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.saved_id = r2.readInt64(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            this.peer.serializeToStream(r3);
            r3.writeInt64(this.saved_id);
        }
    }

    public static final class TL_inputSavedStarGiftSlug extends InputSavedStarGift {
        public static final int constructor = 545636920;
        public String slug;

        public TL_inputSavedStarGiftSlug() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.slug = r1.readString(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.slug);
        }
    }

    public static final class TL_inputSavedStarGiftUser extends InputSavedStarGift {
        public static final int constructor = 1764202389;
        public int msg_id;

        public TL_inputSavedStarGiftUser() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.msg_id = r1.readInt32(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.msg_id);
        }
    }

    public static class TL_inputStarGiftAuction extends InputStarGiftAuction {
        public static final int constructor = 48327832;
        public long gift_id;

        public TL_inputStarGiftAuction() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.gift_id = r1.readInt64(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.gift_id);
        }
    }

    public static class TL_inputStarGiftAuctionSlug extends InputStarGiftAuction {
        public static final int constructor = 2058715912;
        public String slug;

        public TL_inputStarGiftAuctionSlug() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.slug = r1.readString(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.slug);
        }
    }

    public static class TL_payments_getStarsGiftOptions extends TLObject {
        public static final int constructor = -741774392;
        public int flags;
        public TLRPC.InputUser user_id;

        public TL_payments_getStarsGiftOptions() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return Vector.TLDeserialize(r1, r2, r3, new TL_stars$TL_payments_getStarsGiftOptions$$ExternalSyntheticLambda0());
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.user_id.serializeToStream(r3);
            return;
        }
    }

    public static class TL_payments_getStarsGiveawayOptions extends TLObject {
        public static final int constructor = -1122042562;

        public TL_payments_getStarsGiveawayOptions() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return Vector.TLDeserialize(r1, r2, r3, new TL_stars$TL_payments_getStarsGiveawayOptions$$ExternalSyntheticLambda0());
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_payments_getStarsStatus extends TLObject {
        public static final int constructor = 1319744447;
        public TLRPC.InputPeer peer;
        public boolean ton;

        public TL_payments_getStarsStatus() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return StarsStatus.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            r4.writeInt32(TLObject.setFlag(0, 1, this.ton));
            this.peer.serializeToStream(r4);
        }
    }

    public static class TL_payments_getStarsTopupOptions extends TLObject {
        public static final int constructor = -1072773165;

        public TL_payments_getStarsTopupOptions() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return Vector.TLDeserialize(r1, r2, r3, new TL_stars$TL_payments_getStarsTopupOptions$$ExternalSyntheticLambda0());
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_payments_getStarsTransactions extends TLObject {
        public static final int constructor = 1775912279;
        public boolean ascending;
        public int flags;
        public boolean inbound;
        public int limit;
        public String offset;
        public boolean outbound;
        public TLRPC.InputPeer peer;
        public String subscription_id;
        public boolean ton;

        public TL_payments_getStarsTransactions() {
            this.limit = 50;
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return StarsStatus.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r5) {
            r5.writeInt32(constructor);
            boolean r2 = true;
            int r0 = TLObject.setFlag(this.flags, 1, this.inbound);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.outbound);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 4, this.ascending);
            this.flags = r03;
            if (this.subscription_id != null) goto L6;
            r2 = false;
        L6:
            int r04 = TLObject.setFlag(r03, 8, r2);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 16, this.ton);
            this.flags = r05;
            r5.writeInt32(r05);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L9;
            r5.writeString(this.subscription_id);
        L9:
            this.peer.serializeToStream(r5);
            r5.writeString(this.offset);
            r5.writeInt32(this.limit);
        }
    }

    public static final class TL_payments_savedStarGifts extends TLObject {
        public static final int constructor = -1779201615;
        public boolean chat_notifications_enabled;
        public ArrayList<TLRPC.Chat> chats;
        public int count;
        public int flags;
        public ArrayList<SavedStarGift> gifts;
        public String next_offset;
        public ArrayList<TLRPC.User> users;

        public TL_payments_savedStarGifts() {
            this.gifts = new ArrayList();
            this.chats = new ArrayList();
            this.users = new ArrayList();
        }

        public static TL_payments_savedStarGifts TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-1779201615) == r3) goto L5;
            TL_payments_savedStarGifts r0 = null;
        L7:
            return (TL_payments_savedStarGifts) TLObject.TLdeserialize(TL_payments_savedStarGifts.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_payments_savedStarGifts();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.count = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L5;
            this.chat_notifications_enabled = r3.readBool(r4);
        L5:
            this.gifts = Vector.deserialize(r3, new TL_stars$TL_payments_savedStarGifts$$ExternalSyntheticLambda0(), r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L8;
            this.next_offset = r3.readString(r4);
        L8:
            this.chats = Vector.deserialize(r3, new TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1(), r4);
            this.users = Vector.deserialize(r3, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeInt32(this.count);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L5;
            r3.writeBool(this.chat_notifications_enabled);
        L5:
            Vector.serialize(r3, this.gifts);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L8;
            r3.writeString(this.next_offset);
        L8:
            Vector.serialize(r3, this.chats);
            Vector.serialize(r3, this.users);
        }
    }

    public static class TL_payments_sendStarsForm extends TLMethod<TLRPC.payments_PaymentResult> {
        public static final int constructor = 2040056084;
        public long form_id;
        public TLRPC.InputInvoice invoice;

        public TL_payments_sendStarsForm() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public TLRPC.payments_PaymentResult deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.payments_PaymentResult.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.form_id);
            this.invoice.serializeToStream(r3);
        }
    }

    public static class TL_payments_starsStatus extends StarsStatus {
        public static final int constructor = 1822222573;

        public TL_payments_starsStatus() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.balance = StarsAmount.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L6;
            this.subscriptions = Vector.deserialize(r3, new TL_stars$TL_payments_starsStatus$$ExternalSyntheticLambda0(), r4);
        L6:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.subscriptions_next_offset = r3.readString(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L12;
            this.subscriptions_missing_balance = r3.readInt64(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            this.history = Vector.deserialize(r3, new TL_stars$TL_payments_starsStatus$$ExternalSyntheticLambda1(), r4);
        L15:
            if (TLObject.hasFlag(this.flags, 1) == false) goto L17;
            this.next_offset = r3.readString(r4);
        L17:
            this.chats = Vector.deserialize(r3, new TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1(), r4);
            this.users = Vector.deserialize(r3, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            this.balance.serializeToStream(r3);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L6;
            Vector.serialize(r3, this.subscriptions);
        L6:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            r3.writeString(this.subscriptions_next_offset);
        L9:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L12;
            r3.writeInt64(this.subscriptions_missing_balance);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            Vector.serialize(r3, this.history);
        L15:
            if (TLObject.hasFlag(this.flags, 1) == false) goto L17;
            r3.writeString(this.next_offset);
        L17:
            Vector.serialize(r3, this.chats);
            Vector.serialize(r3, this.users);
        }
    }

    public static class TL_payments_starsStatus_layer194 extends TL_payments_starsStatus {
        public static final int constructor = -1141231252;

        public TL_payments_starsStatus_layer194() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_payments_starsStatus, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.balance = StarsAmount.ofStars(r3.readInt64(r4));
            if (TLObject.hasFlag(this.flags, 2) == false) goto L6;
            this.subscriptions = Vector.deserialize(r3, new TL_stars$TL_payments_starsStatus$$ExternalSyntheticLambda0(), r4);
        L6:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.subscriptions_next_offset = r3.readString(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L12;
            this.subscriptions_missing_balance = r3.readInt64(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            this.history = Vector.deserialize(r3, new TL_stars$TL_payments_starsStatus$$ExternalSyntheticLambda1(), r4);
        L15:
            if (TLObject.hasFlag(this.flags, 1) == false) goto L17;
            this.next_offset = r3.readString(r4);
        L17:
            this.chats = Vector.deserialize(r3, new TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1(), r4);
            this.users = Vector.deserialize(r3, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r4);
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_payments_starsStatus, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeInt64(this.balance.amount);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L6;
            Vector.serialize(r3, this.subscriptions);
        L6:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            r3.writeString(this.subscriptions_next_offset);
        L9:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L12;
            r3.writeInt64(this.subscriptions_missing_balance);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            Vector.serialize(r3, this.history);
        L15:
            if (TLObject.hasFlag(this.flags, 1) == false) goto L17;
            r3.writeString(this.next_offset);
        L17:
            Vector.serialize(r3, this.chats);
            Vector.serialize(r3, this.users);
        }
    }

    public static final class TL_payments_uniqueStarGift extends TLObject {
        public static final int constructor = 1097619176;
        public ArrayList<TLRPC.Chat> chats;
        public StarGift gift;
        public ArrayList<TLRPC.User> users;

        public TL_payments_uniqueStarGift() {
            this.chats = new ArrayList();
            this.users = new ArrayList();
        }

        public static TL_payments_uniqueStarGift TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (1097619176 == r3) goto L5;
            TL_payments_uniqueStarGift r0 = null;
        L7:
            return (TL_payments_uniqueStarGift) TLObject.TLdeserialize(TL_payments_uniqueStarGift.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_payments_uniqueStarGift();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.gift = StarGift.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.chats = Vector.deserialize(r2, new TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1(), r3);
            this.users = Vector.deserialize(r2, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.gift.serializeToStream(r2);
            Vector.serialize(r2, this.chats);
            Vector.serialize(r2, this.users);
        }
    }

    public static class TL_savedStarGift extends SavedStarGift {
        public static final int constructor = 1105150972;

        public TL_savedStarGift() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.name_hidden = TLObject.hasFlag(r0, 1);
            this.unsaved = TLObject.hasFlag(this.flags, 32);
            this.refunded = TLObject.hasFlag(this.flags, 512);
            this.can_upgrade = TLObject.hasFlag(this.flags, 1024);
            this.pinned_to_top = TLObject.hasFlag(this.flags, 4096);
            this.upgrade_separate = TLObject.hasFlag(this.flags, 131072);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L5;
            this.from_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.date = r3.readInt32(r4);
            this.gift = StarGift.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.message = TLRPC.TL_textWithEntities.TLdeserialize(r3, r3.readInt32(r4), r4);
        L9:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L12;
            this.msg_id = r3.readInt32(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L15;
            this.saved_id = r3.readInt64(r4);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            this.convert_stars = r3.readInt64(r4);
        L18:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L21;
            this.upgrade_stars = r3.readInt64(r4);
        L21:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L24;
            this.can_export_at = r3.readInt32(r4);
        L24:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L27;
            this.transfer_stars = r3.readInt64(r4);
        L27:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L30;
            this.can_transfer_at = r3.readInt32(r4);
        L30:
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L33;
            this.can_resell_at = r3.readInt32(r4);
        L33:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L36;
            this.collection_id = Vector.deserializeInt(r3, r4);
        L36:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L39;
            this.prepaid_upgrade_hash = r3.readString(r4);
        L39:
            if (TLObject.hasFlag(this.flags, 262144) == false) goto L42;
            this.drop_original_details_stars = r3.readInt64(r4);
        L42:
            if (TLObject.hasFlag(this.flags, 524288) == false) goto L45;
            this.gift_num = r3.readInt32(r4);
        L45:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L48;
            this.can_craft_at = r3.readInt32(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.name_hidden);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 32, this.unsaved);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 512, this.refunded);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 1024, this.can_upgrade);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 4096, this.pinned_to_top);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 131072, this.upgrade_separate);
            this.flags = r06;
            r4.writeInt32(r06);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L5;
            this.from_id.serializeToStream(r4);
        L5:
            r4.writeInt32(this.date);
            this.gift.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.message.serializeToStream(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L12;
            r4.writeInt32(this.msg_id);
        L12:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L15;
            r4.writeInt64(this.saved_id);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            r4.writeInt64(this.convert_stars);
        L18:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L21;
            r4.writeInt64(this.upgrade_stars);
        L21:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L24;
            r4.writeInt32(this.can_export_at);
        L24:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L27;
            r4.writeInt64(this.transfer_stars);
        L27:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L30;
            r4.writeInt32(this.can_transfer_at);
        L30:
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L33;
            r4.writeInt32(this.can_resell_at);
        L33:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L36;
            Vector.serializeInt(r4, this.collection_id);
        L36:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L39;
            r4.writeString(this.prepaid_upgrade_hash);
        L39:
            if (TLObject.hasFlag(this.flags, 262144) == false) goto L42;
            r4.writeInt64(this.drop_original_details_stars);
        L42:
            if (TLObject.hasFlag(this.flags, 524288) == false) goto L45;
            r4.writeInt32(this.gift_num);
        L45:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L48;
            r4.writeInt32(this.can_craft_at);
            return;
        }
    }

    public static class TL_savedStarGift_layer202 extends TL_savedStarGift {
        public static final int constructor = 1616305061;

        public TL_savedStarGift_layer202() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_savedStarGift, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.name_hidden = TLObject.hasFlag(r0, 1);
            this.unsaved = TLObject.hasFlag(this.flags, 32);
            this.refunded = TLObject.hasFlag(this.flags, 512);
            this.can_upgrade = TLObject.hasFlag(this.flags, 1024);
            this.pinned_to_top = TLObject.hasFlag(this.flags, 4096);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L5;
            this.from_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.date = r3.readInt32(r4);
            this.gift = StarGift.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.message = TLRPC.TL_textWithEntities.TLdeserialize(r3, r3.readInt32(r4), r4);
        L9:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L12;
            this.msg_id = r3.readInt32(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L15;
            this.saved_id = r3.readInt64(r4);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            this.convert_stars = r3.readInt64(r4);
        L18:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L21;
            this.upgrade_stars = r3.readInt64(r4);
        L21:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L24;
            this.can_export_at = r3.readInt32(r4);
        L24:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L27;
            this.transfer_stars = r3.readInt64(r4);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_savedStarGift, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.name_hidden);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 32, this.unsaved);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 512, this.refunded);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 1024, this.can_upgrade);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 4096, this.pinned_to_top);
            this.flags = r05;
            r4.writeInt32(r05);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L5;
            this.from_id.serializeToStream(r4);
        L5:
            r4.writeInt32(this.date);
            this.gift.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.message.serializeToStream(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L12;
            r4.writeInt32(this.msg_id);
        L12:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L15;
            r4.writeInt64(this.saved_id);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            r4.writeInt64(this.convert_stars);
        L18:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L21;
            r4.writeInt64(this.upgrade_stars);
        L21:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L24;
            r4.writeInt32(this.can_export_at);
        L24:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L27;
            r4.writeInt64(this.transfer_stars);
            return;
        }
    }

    public static class TL_savedStarGift_layer209 extends TL_savedStarGift {
        public static final int constructor = -539360103;

        public TL_savedStarGift_layer209() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_savedStarGift, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.name_hidden = TLObject.hasFlag(r0, 1);
            this.unsaved = TLObject.hasFlag(this.flags, 32);
            this.refunded = TLObject.hasFlag(this.flags, 512);
            this.can_upgrade = TLObject.hasFlag(this.flags, 1024);
            this.pinned_to_top = TLObject.hasFlag(this.flags, 4096);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L5;
            this.from_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.date = r3.readInt32(r4);
            this.gift = StarGift.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.message = TLRPC.TL_textWithEntities.TLdeserialize(r3, r3.readInt32(r4), r4);
        L9:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L12;
            this.msg_id = r3.readInt32(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L15;
            this.saved_id = r3.readInt64(r4);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            this.convert_stars = r3.readInt64(r4);
        L18:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L21;
            this.upgrade_stars = r3.readInt64(r4);
        L21:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L24;
            this.can_export_at = r3.readInt32(r4);
        L24:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L27;
            this.transfer_stars = r3.readInt64(r4);
        L27:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L30;
            this.can_transfer_at = r3.readInt32(r4);
        L30:
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L33;
            this.can_resell_at = r3.readInt32(r4);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_savedStarGift, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.name_hidden);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 32, this.unsaved);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 512, this.refunded);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 1024, this.can_upgrade);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 4096, this.pinned_to_top);
            this.flags = r05;
            r4.writeInt32(r05);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L5;
            this.from_id.serializeToStream(r4);
        L5:
            r4.writeInt32(this.date);
            this.gift.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.message.serializeToStream(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L12;
            r4.writeInt32(this.msg_id);
        L12:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L15;
            r4.writeInt64(this.saved_id);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            r4.writeInt64(this.convert_stars);
        L18:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L21;
            r4.writeInt64(this.upgrade_stars);
        L21:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L24;
            r4.writeInt32(this.can_export_at);
        L24:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L27;
            r4.writeInt64(this.transfer_stars);
        L27:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L30;
            r4.writeInt32(this.can_transfer_at);
        L30:
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L33;
            r4.writeInt32(this.can_resell_at);
            return;
        }
    }

    public static class TL_savedStarGift_layer211 extends TL_savedStarGift {
        public static final int constructor = 514213599;

        public TL_savedStarGift_layer211() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_savedStarGift, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.name_hidden = TLObject.hasFlag(r0, 1);
            this.unsaved = TLObject.hasFlag(this.flags, 32);
            this.refunded = TLObject.hasFlag(this.flags, 512);
            this.can_upgrade = TLObject.hasFlag(this.flags, 1024);
            this.pinned_to_top = TLObject.hasFlag(this.flags, 4096);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L5;
            this.from_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.date = r3.readInt32(r4);
            this.gift = StarGift.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.message = TLRPC.TL_textWithEntities.TLdeserialize(r3, r3.readInt32(r4), r4);
        L9:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L12;
            this.msg_id = r3.readInt32(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L15;
            this.saved_id = r3.readInt64(r4);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            this.convert_stars = r3.readInt64(r4);
        L18:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L21;
            this.upgrade_stars = r3.readInt64(r4);
        L21:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L24;
            this.can_export_at = r3.readInt32(r4);
        L24:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L27;
            this.transfer_stars = r3.readInt64(r4);
        L27:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L30;
            this.can_transfer_at = r3.readInt32(r4);
        L30:
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L33;
            this.can_resell_at = r3.readInt32(r4);
        L33:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L36;
            this.collection_id = Vector.deserializeInt(r3, r4);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_savedStarGift, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.name_hidden);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 32, this.unsaved);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 512, this.refunded);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 1024, this.can_upgrade);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 4096, this.pinned_to_top);
            this.flags = r05;
            r4.writeInt32(r05);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L5;
            this.from_id.serializeToStream(r4);
        L5:
            r4.writeInt32(this.date);
            this.gift.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.message.serializeToStream(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L12;
            r4.writeInt32(this.msg_id);
        L12:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L15;
            r4.writeInt64(this.saved_id);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            r4.writeInt64(this.convert_stars);
        L18:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L21;
            r4.writeInt64(this.upgrade_stars);
        L21:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L24;
            r4.writeInt32(this.can_export_at);
        L24:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L27;
            r4.writeInt64(this.transfer_stars);
        L27:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L30;
            r4.writeInt32(this.can_transfer_at);
        L30:
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L33;
            r4.writeInt32(this.can_resell_at);
        L33:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L36;
            Vector.serializeInt(r4, this.collection_id);
            return;
        }
    }

    public static class TL_savedStarGift_layer214 extends TL_savedStarGift {
        public static final int constructor = 430552434;

        public TL_savedStarGift_layer214() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_savedStarGift, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.name_hidden = TLObject.hasFlag(r0, 1);
            this.unsaved = TLObject.hasFlag(this.flags, 32);
            this.refunded = TLObject.hasFlag(this.flags, 512);
            this.can_upgrade = TLObject.hasFlag(this.flags, 1024);
            this.pinned_to_top = TLObject.hasFlag(this.flags, 4096);
            this.upgrade_separate = TLObject.hasFlag(this.flags, 131072);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L5;
            this.from_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.date = r3.readInt32(r4);
            this.gift = StarGift.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.message = TLRPC.TL_textWithEntities.TLdeserialize(r3, r3.readInt32(r4), r4);
        L9:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L12;
            this.msg_id = r3.readInt32(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L15;
            this.saved_id = r3.readInt64(r4);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            this.convert_stars = r3.readInt64(r4);
        L18:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L21;
            this.upgrade_stars = r3.readInt64(r4);
        L21:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L24;
            this.can_export_at = r3.readInt32(r4);
        L24:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L27;
            this.transfer_stars = r3.readInt64(r4);
        L27:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L30;
            this.can_transfer_at = r3.readInt32(r4);
        L30:
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L33;
            this.can_resell_at = r3.readInt32(r4);
        L33:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L36;
            this.collection_id = Vector.deserializeInt(r3, r4);
        L36:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L39;
            this.prepaid_upgrade_hash = r3.readString(r4);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_savedStarGift, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.name_hidden);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 32, this.unsaved);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 512, this.refunded);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 1024, this.can_upgrade);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 4096, this.pinned_to_top);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 131072, this.upgrade_separate);
            this.flags = r06;
            r4.writeInt32(r06);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L5;
            this.from_id.serializeToStream(r4);
        L5:
            r4.writeInt32(this.date);
            this.gift.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.message.serializeToStream(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L12;
            r4.writeInt32(this.msg_id);
        L12:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L15;
            r4.writeInt64(this.saved_id);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            r4.writeInt64(this.convert_stars);
        L18:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L21;
            r4.writeInt64(this.upgrade_stars);
        L21:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L24;
            r4.writeInt32(this.can_export_at);
        L24:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L27;
            r4.writeInt64(this.transfer_stars);
        L27:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L30;
            r4.writeInt32(this.can_transfer_at);
        L30:
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L33;
            r4.writeInt32(this.can_resell_at);
        L33:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L36;
            Vector.serializeInt(r4, this.collection_id);
        L36:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L39;
            r4.writeString(this.prepaid_upgrade_hash);
            return;
        }
    }

    public static class TL_savedStarGift_layer219 extends TL_savedStarGift {
        public static final int constructor = -1987861422;

        public TL_savedStarGift_layer219() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_savedStarGift, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.name_hidden = TLObject.hasFlag(r0, 1);
            this.unsaved = TLObject.hasFlag(this.flags, 32);
            this.refunded = TLObject.hasFlag(this.flags, 512);
            this.can_upgrade = TLObject.hasFlag(this.flags, 1024);
            this.pinned_to_top = TLObject.hasFlag(this.flags, 4096);
            this.upgrade_separate = TLObject.hasFlag(this.flags, 131072);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L5;
            this.from_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.date = r3.readInt32(r4);
            this.gift = StarGift.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.message = TLRPC.TL_textWithEntities.TLdeserialize(r3, r3.readInt32(r4), r4);
        L9:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L12;
            this.msg_id = r3.readInt32(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L15;
            this.saved_id = r3.readInt64(r4);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            this.convert_stars = r3.readInt64(r4);
        L18:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L21;
            this.upgrade_stars = r3.readInt64(r4);
        L21:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L24;
            this.can_export_at = r3.readInt32(r4);
        L24:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L27;
            this.transfer_stars = r3.readInt64(r4);
        L27:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L30;
            this.can_transfer_at = r3.readInt32(r4);
        L30:
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L33;
            this.can_resell_at = r3.readInt32(r4);
        L33:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L36;
            this.collection_id = Vector.deserializeInt(r3, r4);
        L36:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L39;
            this.prepaid_upgrade_hash = r3.readString(r4);
        L39:
            if (TLObject.hasFlag(this.flags, 262144) == false) goto L42;
            this.drop_original_details_stars = r3.readInt64(r4);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_savedStarGift, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.name_hidden);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 32, this.unsaved);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 512, this.refunded);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 1024, this.can_upgrade);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 4096, this.pinned_to_top);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 131072, this.upgrade_separate);
            this.flags = r06;
            r4.writeInt32(r06);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L5;
            this.from_id.serializeToStream(r4);
        L5:
            r4.writeInt32(this.date);
            this.gift.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.message.serializeToStream(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L12;
            r4.writeInt32(this.msg_id);
        L12:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L15;
            r4.writeInt64(this.saved_id);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            r4.writeInt64(this.convert_stars);
        L18:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L21;
            r4.writeInt64(this.upgrade_stars);
        L21:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L24;
            r4.writeInt32(this.can_export_at);
        L24:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L27;
            r4.writeInt64(this.transfer_stars);
        L27:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L30;
            r4.writeInt32(this.can_transfer_at);
        L30:
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L33;
            r4.writeInt32(this.can_resell_at);
        L33:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L36;
            Vector.serializeInt(r4, this.collection_id);
        L36:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L39;
            r4.writeString(this.prepaid_upgrade_hash);
        L39:
            if (TLObject.hasFlag(this.flags, 262144) == false) goto L42;
            r4.writeInt64(this.drop_original_details_stars);
            return;
        }
    }

    public static class TL_savedStarGift_layer221 extends TL_savedStarGift {
        public static final int constructor = -355041186;

        public TL_savedStarGift_layer221() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_savedStarGift, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.name_hidden = TLObject.hasFlag(r0, 1);
            this.unsaved = TLObject.hasFlag(this.flags, 32);
            this.refunded = TLObject.hasFlag(this.flags, 512);
            this.can_upgrade = TLObject.hasFlag(this.flags, 1024);
            this.pinned_to_top = TLObject.hasFlag(this.flags, 4096);
            this.upgrade_separate = TLObject.hasFlag(this.flags, 131072);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L5;
            this.from_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.date = r3.readInt32(r4);
            this.gift = StarGift.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.message = TLRPC.TL_textWithEntities.TLdeserialize(r3, r3.readInt32(r4), r4);
        L9:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L12;
            this.msg_id = r3.readInt32(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L15;
            this.saved_id = r3.readInt64(r4);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            this.convert_stars = r3.readInt64(r4);
        L18:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L21;
            this.upgrade_stars = r3.readInt64(r4);
        L21:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L24;
            this.can_export_at = r3.readInt32(r4);
        L24:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L27;
            this.transfer_stars = r3.readInt64(r4);
        L27:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L30;
            this.can_transfer_at = r3.readInt32(r4);
        L30:
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L33;
            this.can_resell_at = r3.readInt32(r4);
        L33:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L36;
            this.collection_id = Vector.deserializeInt(r3, r4);
        L36:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L39;
            this.prepaid_upgrade_hash = r3.readString(r4);
        L39:
            if (TLObject.hasFlag(this.flags, 262144) == false) goto L42;
            this.drop_original_details_stars = r3.readInt64(r4);
        L42:
            if (TLObject.hasFlag(this.flags, 524288) == false) goto L45;
            this.gift_num = r3.readInt32(r4);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_savedStarGift, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.name_hidden);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 32, this.unsaved);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 512, this.refunded);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 1024, this.can_upgrade);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 4096, this.pinned_to_top);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 131072, this.upgrade_separate);
            this.flags = r06;
            r4.writeInt32(r06);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L5;
            this.from_id.serializeToStream(r4);
        L5:
            r4.writeInt32(this.date);
            this.gift.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.message.serializeToStream(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L12;
            r4.writeInt32(this.msg_id);
        L12:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L15;
            r4.writeInt64(this.saved_id);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            r4.writeInt64(this.convert_stars);
        L18:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L21;
            r4.writeInt64(this.upgrade_stars);
        L21:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L24;
            r4.writeInt32(this.can_export_at);
        L24:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L27;
            r4.writeInt64(this.transfer_stars);
        L27:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L30;
            r4.writeInt32(this.can_transfer_at);
        L30:
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L33;
            r4.writeInt32(this.can_resell_at);
        L33:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L36;
            Vector.serializeInt(r4, this.collection_id);
        L36:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L39;
            r4.writeString(this.prepaid_upgrade_hash);
        L39:
            if (TLObject.hasFlag(this.flags, 262144) == false) goto L42;
            r4.writeInt64(this.drop_original_details_stars);
        L42:
            if (TLObject.hasFlag(this.flags, 524288) == false) goto L45;
            r4.writeInt32(this.gift_num);
            return;
        }
    }

    public static class TL_savedStarGift_layer221_2 extends TL_savedStarGift {
        public static final int constructor = 949728281;

        public TL_savedStarGift_layer221_2() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_savedStarGift, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.name_hidden = TLObject.hasFlag(r0, 1);
            this.unsaved = TLObject.hasFlag(this.flags, 32);
            this.refunded = TLObject.hasFlag(this.flags, 512);
            this.can_upgrade = TLObject.hasFlag(this.flags, 1024);
            this.pinned_to_top = TLObject.hasFlag(this.flags, 4096);
            this.upgrade_separate = TLObject.hasFlag(this.flags, 131072);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L5;
            this.from_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.date = r3.readInt32(r4);
            this.gift = StarGift.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.message = TLRPC.TL_textWithEntities.TLdeserialize(r3, r3.readInt32(r4), r4);
        L9:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L12;
            this.msg_id = r3.readInt32(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L15;
            this.saved_id = r3.readInt64(r4);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            this.convert_stars = r3.readInt64(r4);
        L18:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L21;
            this.upgrade_stars = r3.readInt64(r4);
        L21:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L24;
            this.can_export_at = r3.readInt32(r4);
        L24:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L27;
            this.transfer_stars = r3.readInt64(r4);
        L27:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L30;
            this.can_transfer_at = r3.readInt32(r4);
        L30:
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L33;
            this.can_resell_at = r3.readInt32(r4);
        L33:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L36;
            this.collection_id = Vector.deserializeInt(r3, r4);
        L36:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L39;
            this.prepaid_upgrade_hash = r3.readString(r4);
        L39:
            if (TLObject.hasFlag(this.flags, 262144) == false) goto L42;
            this.drop_original_details_stars = r3.readInt64(r4);
        L42:
            if (TLObject.hasFlag(this.flags, 524288) == false) goto L45;
            this.gift_num = r3.readInt32(r4);
        L45:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L48;
            this.can_craft_at = r3.readInt32(r4);
            r3.readInt32(r4);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_savedStarGift, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.name_hidden);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 32, this.unsaved);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 512, this.refunded);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 1024, this.can_upgrade);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 4096, this.pinned_to_top);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 131072, this.upgrade_separate);
            this.flags = r06;
            r4.writeInt32(r06);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L5;
            this.from_id.serializeToStream(r4);
        L5:
            r4.writeInt32(this.date);
            this.gift.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.message.serializeToStream(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L12;
            r4.writeInt32(this.msg_id);
        L12:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L15;
            r4.writeInt64(this.saved_id);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            r4.writeInt64(this.convert_stars);
        L18:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L21;
            r4.writeInt64(this.upgrade_stars);
        L21:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L24;
            r4.writeInt32(this.can_export_at);
        L24:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L27;
            r4.writeInt64(this.transfer_stars);
        L27:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L30;
            r4.writeInt32(this.can_transfer_at);
        L30:
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L33;
            r4.writeInt32(this.can_resell_at);
        L33:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L36;
            Vector.serializeInt(r4, this.collection_id);
        L36:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L39;
            r4.writeString(this.prepaid_upgrade_hash);
        L39:
            if (TLObject.hasFlag(this.flags, 262144) == false) goto L42;
            r4.writeInt64(this.drop_original_details_stars);
        L42:
            if (TLObject.hasFlag(this.flags, 524288) == false) goto L45;
            r4.writeInt32(this.gift_num);
        L45:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L48;
            r4.writeInt32(this.can_craft_at);
            r4.writeInt32(0);
            return;
        }
    }

    /* loaded from: classes.dex */
    public static class TL_starGift extends StarGift {
        public static final int constructor = 825922887;

        public TL_starGift() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r9, boolean r10) {
            int r0 = r9.readInt32(r10);
            this.flags = r0;
            this.limited = TLObject.hasFlag(r0, 1);
            this.sold_out = TLObject.hasFlag(this.flags, 2);
            this.birthday = TLObject.hasFlag(this.flags, 4);
            this.can_upgrade = TLObject.hasFlag(this.flags, 8);
            this.require_premium = TLObject.hasFlag(this.flags, 128);
            this.limited_per_user = TLObject.hasFlag(this.flags, 256);
            this.peer_color_available = TLObject.hasFlag(this.flags, 1024);
            this.auction = TLObject.hasFlag(this.flags, 2048);
            this.id = r9.readInt64(r10);
            this.sticker = TLRPC.Document.TLdeserialize(r9, r9.readInt32(r10), r10);
            this.stars = r9.readInt64(r10);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.availability_remains = r9.readInt32(r10);
            this.availability_total = r9.readInt32(r10);
        L6:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L8;
            this.availability_resale = r9.readInt64(r10);
        L8:
            this.convert_stars = r9.readInt64(r10);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L12;
            this.first_sale_date = r9.readInt32(r10);
            this.last_sale_date = r9.readInt32(r10);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            this.upgrade_stars = r9.readInt64(r10);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            this.resell_min_stars = r9.readInt64(r10);
        L18:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L21;
            this.title = r9.readString(r10);
        L21:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L24;
            this.released_by = TLRPC.Peer.TLdeserialize(r9, r9.readInt32(r10), r10);
        L24:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L27;
            this.per_user_total = r9.readInt32(r10);
            this.per_user_remains = r9.readInt32(r10);
        L27:
            if (TLObject.hasFlag(this.flags, 512) == false) goto L30;
            this.locked_until_date = r9.readInt32(r10);
        L30:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L33;
            this.auction_slug = r9.readString(r10);
            this.gifts_per_round = r9.readInt32(r10);
            this.auction_start_date = r9.readInt32(r10);
        L33:
            if (TLObject.hasFlag(this.flags, 4096) == false) goto L36;
            this.upgrade_variants = r9.readInt32(r10);
        L36:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L39;
            this.background = TL_starGiftBackground.TLdeserialize(r9, r9.readInt32(r10), r10);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r11) {
            r11.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.limited);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.sold_out);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 4, this.birthday);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 8, this.can_upgrade);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 128, this.require_premium);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 256, this.limited_per_user);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 1024, this.peer_color_available);
            this.flags = r07;
            int r08 = TLObject.setFlag(r07, 2048, this.auction);
            this.flags = r08;
            if (this.background == null) goto L5;
            boolean r1 = true;
        L6:
            int r09 = TLObject.setFlag(r08, 8192, r1);
            this.flags = r09;
            r11.writeInt32(r09);
            r11.writeInt64(this.id);
            this.sticker.serializeToStream(r11);
            r11.writeInt64(this.stars);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L10;
            r11.writeInt32(this.availability_remains);
            r11.writeInt32(this.availability_total);
        L10:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L12;
            r11.writeInt64(this.availability_resale);
        L12:
            r11.writeInt64(this.convert_stars);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L16;
            r11.writeInt32(this.first_sale_date);
            r11.writeInt32(this.last_sale_date);
        L16:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L19;
            r11.writeInt64(this.upgrade_stars);
        L19:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L22;
            r11.writeInt64(this.resell_min_stars);
        L22:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L25;
            r11.writeString(this.title);
        L25:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L28;
            this.released_by.serializeToStream(r11);
        L28:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L31;
            r11.writeInt32(this.per_user_total);
            r11.writeInt32(this.per_user_remains);
        L31:
            if (TLObject.hasFlag(this.flags, 512) == false) goto L34;
            r11.writeInt32(this.locked_until_date);
        L34:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L37;
            r11.writeString(this.auction_slug);
            r11.writeInt32(this.gifts_per_round);
            r11.writeInt32(this.auction_start_date);
        L37:
            if (TLObject.hasFlag(this.flags, 4096) == false) goto L40;
            r11.writeInt32(this.upgrade_variants);
        L40:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L43;
            this.background.serializeToStream(r11);
            return;
        L43:
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_starGiftAttributeRarity extends StarGiftAttributeRarity {
        public static final int constructor = 910391095;
        public int permille;

        public TL_starGiftAttributeRarity() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.permille = r1.readInt32(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.permille);
        }
    }

    public static class TL_starGiftAttributeRarityEpic extends StarGiftAttributeRarity {
        public static final int constructor = 2029777832;

        public TL_starGiftAttributeRarityEpic() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_starGiftAttributeRarityLegendary extends StarGiftAttributeRarity {
        public static final int constructor = -822614104;

        public TL_starGiftAttributeRarityLegendary() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_starGiftAttributeRarityRare extends StarGiftAttributeRarity {
        public static final int constructor = -259174037;

        public TL_starGiftAttributeRarityRare() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_starGiftAttributeRarityUncommon extends StarGiftAttributeRarity {
        public static final int constructor = -607231095;

        public TL_starGiftAttributeRarityUncommon() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_starGiftAuctionRound extends StarGiftAuctionRound {
        public static final int constructor = 984483112;

        public TL_starGiftAuctionRound() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.num = r2.readInt32(r3);
            this.duration = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.num);
            r2.writeInt32(this.duration);
        }
    }

    public static class TL_starGiftAuctionRoundExtendable extends StarGiftAuctionRound {
        public static final int constructor = 178266597;

        public TL_starGiftAuctionRoundExtendable() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.num = r2.readInt32(r3);
            this.duration = r2.readInt32(r3);
            this.extend_top = r2.readInt32(r3);
            this.current_window = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.num);
            r2.writeInt32(this.duration);
            r2.writeInt32(this.extend_top);
            r2.writeInt32(this.current_window);
        }
    }

    public static class TL_starGiftAuctionState extends StarGiftAuctionState {
        public static final int constructor = 1998212710;
        public ArrayList<TL_AuctionBidLevel> bid_levels;
        public int current_round;
        public int end_date;
        public int gifts_left;
        public int last_gift_num;
        public long min_bid_amount;
        public int next_round_at;
        public ArrayList<StarGiftAuctionRound> rounds;
        public int start_date;
        public ArrayList<Long> top_bidders;
        public int total_rounds;
        public int version;

        public TL_starGiftAuctionState() {
            this.bid_levels = new ArrayList();
            this.top_bidders = new ArrayList();
            this.rounds = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.version = r3.readInt32(r4);
            this.start_date = r3.readInt32(r4);
            this.end_date = r3.readInt32(r4);
            this.min_bid_amount = r3.readInt64(r4);
            this.bid_levels = Vector.deserialize(r3, new TL_stars$TL_starGiftAuctionState$$ExternalSyntheticLambda0(), r4);
            this.top_bidders = Vector.deserializeLong(r3, r4);
            this.next_round_at = r3.readInt32(r4);
            this.last_gift_num = r3.readInt32(r4);
            this.gifts_left = r3.readInt32(r4);
            this.current_round = r3.readInt32(r4);
            this.total_rounds = r3.readInt32(r4);
            this.rounds = Vector.deserialize(r3, new TL_stars$TL_starGiftAuctionState$$ExternalSyntheticLambda1(), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.version);
            r3.writeInt32(this.start_date);
            r3.writeInt32(this.end_date);
            r3.writeInt64(this.min_bid_amount);
            Vector.serialize(r3, this.bid_levels);
            Vector.serializeLong(r3, this.top_bidders);
            r3.writeInt32(this.next_round_at);
            r3.writeInt32(this.last_gift_num);
            r3.writeInt32(this.gifts_left);
            r3.writeInt32(this.current_round);
            r3.writeInt32(this.total_rounds);
            Vector.serialize(r3, this.rounds);
        }
    }

    public static class TL_starGiftAuctionStateFinished extends StarGiftAuctionState {
        public static final int constructor = -1758614593;
        public long average_price;
        public int end_date;
        public int flags;
        public int fragment_listed_count;
        public String fragment_listed_url;
        public int listed_count;
        public int start_date;

        public TL_starGiftAuctionStateFinished() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.start_date = r3.readInt32(r4);
            this.end_date = r3.readInt32(r4);
            this.average_price = r3.readInt64(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.listed_count = r3.readInt32(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.fragment_listed_count = r3.readInt32(r4);
            this.fragment_listed_url = r3.readString(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeInt32(this.start_date);
            r3.writeInt32(this.end_date);
            r3.writeInt64(this.average_price);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeInt32(this.listed_count);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r3.writeInt32(this.fragment_listed_count);
            r3.writeString(this.fragment_listed_url);
            return;
        }
    }

    public static class TL_starGiftAuctionStateNotModified extends StarGiftAuctionState {
        public static final int constructor = -30197422;

        public TL_starGiftAuctionStateNotModified() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_starGiftBackground extends TLObject {
        public static final int constructor = -1342872680;
        public int center_color;
        public int edge_color;
        public int text_color;

        public TL_starGiftBackground() {
        }

        public static TL_starGiftBackground TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-1342872680) == r3) goto L5;
            TL_starGiftBackground r0 = null;
        L7:
            return (TL_starGiftBackground) TLObject.TLdeserialize(TL_starGiftBackground.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_starGiftBackground();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.center_color = r2.readInt32(r3);
            this.edge_color = r2.readInt32(r3);
            this.text_color = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.center_color);
            r2.writeInt32(this.edge_color);
            r2.writeInt32(this.text_color);
        }
    }

    public static class TL_starGiftCollection extends TLObject {
        public static final int constructor = -1653926992;
        public int collection_id;
        public int flags;
        public int gifts_count;
        public long hash;
        public TLRPC.Document icon;
        public String title;

        public TL_starGiftCollection() {
        }

        public static TL_starGiftCollection TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-1653926992)) goto L5;
            TL_starGiftCollection r0 = new TL_starGiftCollection();
        L7:
            return (TL_starGiftCollection) TLObject.TLdeserialize(TL_starGiftCollection.class, r0, r2, r3, r4);
        L5:
            r0 = null;
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.collection_id = r3.readInt32(r4);
            this.title = r3.readString(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            this.icon = TLRPC.Document.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.gifts_count = r3.readInt32(r4);
            this.hash = r3.readInt64(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeInt32(this.collection_id);
            r3.writeString(this.title);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            this.icon.serializeToStream(r3);
        L5:
            r3.writeInt32(this.gifts_count);
            r3.writeInt64(this.hash);
        }
    }

    public static class TL_starGiftCollections extends StarGiftCollections {
        public static final int constructor = -1977011469;

        public TL_starGiftCollections() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.collections = Vector.deserialize(r2, new TL_stars$TL_starGiftCollections$$ExternalSyntheticLambda0(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.collections);
        }
    }

    public static class TL_starGiftCollectionsNotModified extends StarGiftCollections {
        public static final int constructor = -1598402793;

        public TL_starGiftCollectionsNotModified() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes.dex */
    public static class TL_starGiftUnique extends StarGift {
        public static final int constructor = -2047825459;

        public TL_starGiftUnique() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.require_premium = TLObject.hasFlag(r0, 64);
            this.resale_ton_only = TLObject.hasFlag(this.flags, 128);
            this.theme_available = TLObject.hasFlag(this.flags, 512);
            this.burned = TLObject.hasFlag(this.flags, 16384);
            this.crafted = TLObject.hasFlag(this.flags, 32768);
            this.id = r3.readInt64(r4);
            this.gift_id = r3.readInt64(r4);
            this.title = r3.readString(r4);
            this.slug = r3.readString(r4);
            this.num = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.owner_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.owner_name = r3.readString(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L11;
            this.owner_address = r3.readString(r4);
        L11:
            this.attributes = Vector.deserialize(r3, new TL_stars$TL_starGiftUnique$$ExternalSyntheticLambda0(), r4);
            this.availability_issued = r3.readInt32(r4);
            this.availability_total = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            this.gift_address = r3.readString(r4);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            this.resell_amount = Vector.deserialize(r3, new TL_stars$TL_starGiftUnique$$ExternalSyntheticLambda1(), r4);
        L18:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L21;
            this.released_by = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L21:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L24;
            this.value_amount = r3.readInt64(r4);
            this.value_currency = r3.readString(r4);
            this.value_usd_amount = r3.readInt64(r4);
        L24:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L27;
            this.theme_peer = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L27:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L30;
            this.peer_color = TLRPC.PeerColor.TLdeserialize(r3, r3.readInt32(r4), r4);
        L30:
            if (TLObject.hasFlag(this.flags, 4096) == false) goto L33;
            this.host_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L33:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L36;
            this.offer_min_stars = r3.readInt32(r4);
        L36:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L39;
            this.craft_chance_permille = r3.readInt32(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r6) {
            r6.writeInt32(constructor);
            int r0 = this.flags;
            ArrayList<StarsAmount> r1 = this.resell_amount;
            if (r1 != null) goto L5;
        L7:
            boolean r12 = false;
        L8:
            int r02 = TLObject.setFlag(r0, 16, r12);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 64, this.require_premium);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 128, this.resale_ton_only);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 512, this.theme_available);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 16384, this.burned);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 32768, this.crafted);
            this.flags = r07;
            r6.writeInt32(r07);
            r6.writeInt64(this.id);
            r6.writeInt64(this.gift_id);
            r6.writeString(this.title);
            r6.writeString(this.slug);
            r6.writeInt32(this.num);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L12;
            this.owner_id.serializeToStream(r6);
        L12:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L15;
            r6.writeString(this.owner_name);
        L15:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L17;
            r6.writeString(this.owner_address);
        L17:
            Vector.serialize(r6, this.attributes);
            r6.writeInt32(this.availability_issued);
            r6.writeInt32(this.availability_total);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L21;
            r6.writeString(this.gift_address);
        L21:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L24;
            Vector.serialize(r6, this.resell_amount);
        L24:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L27;
            this.released_by.serializeToStream(r6);
        L27:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L30;
            r6.writeInt64(this.value_amount);
            r6.writeString(this.value_currency);
            r6.writeInt64(this.value_usd_amount);
        L30:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L33;
            this.theme_peer.serializeToStream(r6);
        L33:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L36;
            this.peer_color.serializeToStream(r6);
        L36:
            if (TLObject.hasFlag(this.flags, 4096) == false) goto L39;
            this.host_id.serializeToStream(r6);
        L39:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L42;
            r6.writeInt32(this.offer_min_stars);
        L42:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L45;
            r6.writeInt32(this.craft_chance_permille);
            return;
        L45:
            return;
        L5:
            if (r1.isEmpty() == true) goto L7;
            r12 = true;
            goto L8
        }
    }

    public static class TL_starGiftUnique_layer196 extends TL_starGiftUnique {
        public static final int constructor = 1779697613;

        public TL_starGiftUnique_layer196() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGiftUnique, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r4, boolean r5) {
            this.id = r4.readInt64(r5);
            this.title = r4.readString(r5);
            this.num = r4.readInt32(r5);
            TLRPC.TL_peerUser r0 = new TLRPC.TL_peerUser();
            this.owner_id = r0;
            r0.user_id = r4.readInt64(r5);
            this.attributes = Vector.deserialize(r4, new TL_stars$TL_starGiftUnique$$ExternalSyntheticLambda0(), r5);
            this.availability_issued = r4.readInt32(r5);
            this.availability_total = r4.readInt32(r5);
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGiftUnique, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.id);
            r3.writeString(this.title);
            r3.writeInt32(this.num);
            r3.writeInt64(this.owner_id.user_id);
            Vector.serialize(r3, this.attributes);
            r3.writeInt32(this.availability_issued);
            r3.writeInt32(this.availability_total);
        }
    }

    public static class TL_starGiftUnique_layer197 extends TL_starGiftUnique {
        public static final int constructor = 880997154;

        public TL_starGiftUnique_layer197() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGiftUnique, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r4, boolean r5) {
            this.flags = r4.readInt32(r5);
            this.id = r4.readInt64(r5);
            this.title = r4.readString(r5);
            this.slug = r4.readString(r5);
            this.num = r4.readInt32(r5);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            TLRPC.TL_peerUser r0 = new TLRPC.TL_peerUser();
            this.owner_id = r0;
            r0.user_id = r4.readInt64(r5);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L8;
            this.owner_name = r4.readString(r5);
        L8:
            this.attributes = Vector.deserialize(r4, new TL_stars$TL_starGiftUnique$$ExternalSyntheticLambda0(), r5);
            this.availability_issued = r4.readInt32(r5);
            this.availability_total = r4.readInt32(r5);
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGiftUnique, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeInt64(this.id);
            r3.writeString(this.title);
            r3.writeString(this.slug);
            r3.writeInt32(this.num);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeInt64(this.owner_id.user_id);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L8;
            r3.writeString(this.owner_name);
        L8:
            Vector.serialize(r3, this.attributes);
            r3.writeInt32(this.availability_issued);
            r3.writeInt32(this.availability_total);
        }
    }

    public static class TL_starGiftUnique_layer198 extends TL_starGiftUnique {
        public static final int constructor = -218202550;

        public TL_starGiftUnique_layer198() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGiftUnique, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.id = r3.readInt64(r4);
            this.title = r3.readString(r4);
            this.slug = r3.readString(r4);
            this.num = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.owner_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.owner_name = r3.readString(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L11;
            this.owner_address = r3.readString(r4);
        L11:
            this.attributes = Vector.deserialize(r3, new TL_stars$TL_starGiftUnique$$ExternalSyntheticLambda0(), r4);
            this.availability_issued = r3.readInt32(r4);
            this.availability_total = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGiftUnique, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeInt64(this.id);
            r3.writeString(this.title);
            r3.writeString(this.slug);
            r3.writeInt32(this.num);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.owner_id.serializeToStream(r3);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r3.writeString(this.owner_name);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L11;
            r3.writeString(this.owner_address);
        L11:
            Vector.serialize(r3, this.attributes);
            r3.writeInt32(this.availability_issued);
            r3.writeInt32(this.availability_total);
        }
    }

    public static class TL_starGiftUnique_layer202 extends TL_starGiftUnique {
        public static final int constructor = 1549979985;

        public TL_starGiftUnique_layer202() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGiftUnique, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.id = r3.readInt64(r4);
            this.title = r3.readString(r4);
            this.slug = r3.readString(r4);
            this.num = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.owner_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.owner_name = r3.readString(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L11;
            this.owner_address = r3.readString(r4);
        L11:
            this.attributes = Vector.deserialize(r3, new TL_stars$TL_starGiftUnique$$ExternalSyntheticLambda0(), r4);
            this.availability_issued = r3.readInt32(r4);
            this.availability_total = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            this.gift_address = r3.readString(r4);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGiftUnique, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeInt64(this.id);
            r3.writeString(this.title);
            r3.writeString(this.slug);
            r3.writeInt32(this.num);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.owner_id.serializeToStream(r3);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r3.writeString(this.owner_name);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L11;
            r3.writeString(this.owner_address);
        L11:
            Vector.serialize(r3, this.attributes);
            r3.writeInt32(this.availability_issued);
            r3.writeInt32(this.availability_total);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            r3.writeString(this.gift_address);
            return;
        }
    }

    public static class TL_starGiftUnique_layer206 extends TL_starGiftUnique {
        public static final int constructor = 1678891913;

        public TL_starGiftUnique_layer206() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGiftUnique, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.id = r3.readInt64(r4);
            this.title = r3.readString(r4);
            this.slug = r3.readString(r4);
            this.num = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.owner_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.owner_name = r3.readString(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L11;
            this.owner_address = r3.readString(r4);
        L11:
            this.attributes = Vector.deserialize(r3, new TL_stars$TL_starGiftUnique$$ExternalSyntheticLambda0(), r4);
            this.availability_issued = r3.readInt32(r4);
            this.availability_total = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            this.gift_address = r3.readString(r4);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            ArrayList<StarsAmount> r0 = new ArrayList();
            this.resell_amount = r0;
            r0.add(StarsAmount.ofStars(r3.readInt64(r4)));
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGiftUnique, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeInt64(this.id);
            r3.writeString(this.title);
            r3.writeString(this.slug);
            r3.writeInt32(this.num);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.owner_id.serializeToStream(r3);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r3.writeString(this.owner_name);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L11;
            r3.writeString(this.owner_address);
        L11:
            Vector.serialize(r3, this.attributes);
            r3.writeInt32(this.availability_issued);
            r3.writeInt32(this.availability_total);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            r3.writeString(this.gift_address);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            r3.writeInt64(getResellStars());
            return;
        }
    }

    public static class TL_starGiftUnique_layer210 extends TL_starGiftUnique {
        public static final int constructor = -164136786;

        public TL_starGiftUnique_layer210() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGiftUnique, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r4, boolean r5) {
            int r0 = r4.readInt32(r5);
            this.flags = r0;
            this.require_premium = TLObject.hasFlag(r0, 64);
            this.id = r4.readInt64(r5);
            this.title = r4.readString(r5);
            this.slug = r4.readString(r5);
            this.num = r4.readInt32(r5);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.owner_id = TLRPC.Peer.TLdeserialize(r4, r4.readInt32(r5), r5);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.owner_name = r4.readString(r5);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L11;
            this.owner_address = r4.readString(r5);
        L11:
            this.attributes = Vector.deserialize(r4, new TL_stars$TL_starGiftUnique$$ExternalSyntheticLambda0(), r5);
            this.availability_issued = r4.readInt32(r5);
            this.availability_total = r4.readInt32(r5);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            this.gift_address = r4.readString(r5);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            ArrayList<StarsAmount> r02 = new ArrayList();
            this.resell_amount = r02;
            r02.add(StarsAmount.ofStars(r4.readInt64(r5)));
        L18:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L21;
            this.released_by = TLRPC.Peer.TLdeserialize(r4, r4.readInt32(r5), r5);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGiftUnique, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 64, this.require_premium);
            this.flags = r0;
            r4.writeInt32(r0);
            r4.writeInt64(this.id);
            r4.writeString(this.title);
            r4.writeString(this.slug);
            r4.writeInt32(this.num);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.owner_id.serializeToStream(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r4.writeString(this.owner_name);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L11;
            r4.writeString(this.owner_address);
        L11:
            Vector.serialize(r4, this.attributes);
            r4.writeInt32(this.availability_issued);
            r4.writeInt32(this.availability_total);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            r4.writeString(this.gift_address);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            r4.writeInt64(getResellStars());
        L18:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L21;
            this.released_by.serializeToStream(r4);
            return;
        }
    }

    public static class TL_starGiftUnique_layer211 extends TL_starGiftUnique {
        public static final int constructor = 975654224;

        public TL_starGiftUnique_layer211() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGiftUnique, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.require_premium = TLObject.hasFlag(r0, 64);
            this.resale_ton_only = TLObject.hasFlag(this.flags, 128);
            this.id = r3.readInt64(r4);
            this.title = r3.readString(r4);
            this.slug = r3.readString(r4);
            this.num = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.owner_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.owner_name = r3.readString(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L11;
            this.owner_address = r3.readString(r4);
        L11:
            this.attributes = Vector.deserialize(r3, new TL_stars$TL_starGiftUnique$$ExternalSyntheticLambda0(), r4);
            this.availability_issued = r3.readInt32(r4);
            this.availability_total = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            this.gift_address = r3.readString(r4);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            this.resell_amount = Vector.deserialize(r3, new TL_stars$TL_starGiftUnique$$ExternalSyntheticLambda1(), r4);
        L18:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L21;
            this.released_by = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGiftUnique, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r6) {
            r6.writeInt32(constructor);
            int r0 = this.flags;
            ArrayList<StarsAmount> r1 = this.resell_amount;
            if (r1 != null) goto L5;
        L7:
            boolean r12 = false;
        L8:
            int r02 = TLObject.setFlag(r0, 16, r12);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 64, this.require_premium);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 128, this.resale_ton_only);
            this.flags = r04;
            r6.writeInt32(r04);
            r6.writeInt64(this.id);
            r6.writeString(this.title);
            r6.writeString(this.slug);
            r6.writeInt32(this.num);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L12;
            this.owner_id.serializeToStream(r6);
        L12:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L15;
            r6.writeString(this.owner_name);
        L15:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L17;
            r6.writeString(this.owner_address);
        L17:
            Vector.serialize(r6, this.attributes);
            r6.writeInt32(this.availability_issued);
            r6.writeInt32(this.availability_total);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L21;
            r6.writeString(this.gift_address);
        L21:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L24;
            Vector.serialize(r6, this.resell_amount);
        L24:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L27;
            this.released_by.serializeToStream(r6);
            return;
        L27:
            return;
        L5:
            if (r1.isEmpty() == true) goto L7;
            r12 = true;
            goto L8
        }
    }

    public static class TL_starGiftUnique_layer213 extends TL_starGiftUnique {
        public static final int constructor = 648369470;

        public TL_starGiftUnique_layer213() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGiftUnique, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.require_premium = TLObject.hasFlag(r0, 64);
            this.resale_ton_only = TLObject.hasFlag(this.flags, 128);
            this.id = r3.readInt64(r4);
            this.gift_id = r3.readInt64(r4);
            this.title = r3.readString(r4);
            this.slug = r3.readString(r4);
            this.num = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.owner_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.owner_name = r3.readString(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L11;
            this.owner_address = r3.readString(r4);
        L11:
            this.attributes = Vector.deserialize(r3, new TL_stars$TL_starGiftUnique$$ExternalSyntheticLambda0(), r4);
            this.availability_issued = r3.readInt32(r4);
            this.availability_total = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            this.gift_address = r3.readString(r4);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            this.resell_amount = Vector.deserialize(r3, new TL_stars$TL_starGiftUnique$$ExternalSyntheticLambda1(), r4);
        L18:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L21;
            this.released_by = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L21:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L24;
            this.value_amount = r3.readInt64(r4);
            this.value_currency = r3.readString(r4);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGiftUnique, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r6) {
            r6.writeInt32(constructor);
            int r0 = this.flags;
            ArrayList<StarsAmount> r1 = this.resell_amount;
            if (r1 != null) goto L5;
        L7:
            boolean r12 = false;
        L8:
            int r02 = TLObject.setFlag(r0, 16, r12);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 64, this.require_premium);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 128, this.resale_ton_only);
            this.flags = r04;
            r6.writeInt32(r04);
            r6.writeInt64(this.id);
            r6.writeInt64(this.gift_id);
            r6.writeString(this.title);
            r6.writeString(this.slug);
            r6.writeInt32(this.num);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L12;
            this.owner_id.serializeToStream(r6);
        L12:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L15;
            r6.writeString(this.owner_name);
        L15:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L17;
            r6.writeString(this.owner_address);
        L17:
            Vector.serialize(r6, this.attributes);
            r6.writeInt32(this.availability_issued);
            r6.writeInt32(this.availability_total);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L21;
            r6.writeString(this.gift_address);
        L21:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L24;
            Vector.serialize(r6, this.resell_amount);
        L24:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L27;
            this.released_by.serializeToStream(r6);
        L27:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L30;
            r6.writeInt64(this.value_amount);
            r6.writeString(this.value_currency);
            return;
        L30:
            return;
        L5:
            if (r1.isEmpty() == true) goto L7;
            r12 = true;
            goto L8
        }
    }

    public static class TL_starGiftUnique_layer214 extends TL_starGiftUnique {
        public static final int constructor = 468707429;

        public TL_starGiftUnique_layer214() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGiftUnique, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.require_premium = TLObject.hasFlag(r0, 64);
            this.resale_ton_only = TLObject.hasFlag(this.flags, 128);
            this.theme_available = TLObject.hasFlag(this.flags, 512);
            this.id = r3.readInt64(r4);
            this.gift_id = r3.readInt64(r4);
            this.title = r3.readString(r4);
            this.slug = r3.readString(r4);
            this.num = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.owner_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.owner_name = r3.readString(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L11;
            this.owner_address = r3.readString(r4);
        L11:
            this.attributes = Vector.deserialize(r3, new TL_stars$TL_starGiftUnique$$ExternalSyntheticLambda0(), r4);
            this.availability_issued = r3.readInt32(r4);
            this.availability_total = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            this.gift_address = r3.readString(r4);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            this.resell_amount = Vector.deserialize(r3, new TL_stars$TL_starGiftUnique$$ExternalSyntheticLambda1(), r4);
        L18:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L21;
            this.released_by = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L21:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L24;
            this.value_amount = r3.readInt64(r4);
            this.value_currency = r3.readString(r4);
        L24:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L27;
            this.theme_peer = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGiftUnique, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r6) {
            r6.writeInt32(constructor);
            int r0 = this.flags;
            ArrayList<StarsAmount> r1 = this.resell_amount;
            if (r1 != null) goto L5;
        L7:
            boolean r12 = false;
        L8:
            int r02 = TLObject.setFlag(r0, 16, r12);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 64, this.require_premium);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 128, this.resale_ton_only);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 512, this.theme_available);
            this.flags = r05;
            r6.writeInt32(r05);
            r6.writeInt64(this.id);
            r6.writeInt64(this.gift_id);
            r6.writeString(this.title);
            r6.writeString(this.slug);
            r6.writeInt32(this.num);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L12;
            this.owner_id.serializeToStream(r6);
        L12:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L15;
            r6.writeString(this.owner_name);
        L15:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L17;
            r6.writeString(this.owner_address);
        L17:
            Vector.serialize(r6, this.attributes);
            r6.writeInt32(this.availability_issued);
            r6.writeInt32(this.availability_total);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L21;
            r6.writeString(this.gift_address);
        L21:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L24;
            Vector.serialize(r6, this.resell_amount);
        L24:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L27;
            this.released_by.serializeToStream(r6);
        L27:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L30;
            r6.writeInt64(this.value_amount);
            r6.writeString(this.value_currency);
        L30:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L33;
            this.theme_peer.serializeToStream(r6);
            return;
        L33:
            return;
        L5:
            if (r1.isEmpty() == true) goto L7;
            r12 = true;
            goto L8
        }
    }

    public static class TL_starGiftUnique_layer215 extends TL_starGiftUnique {
        public static final int constructor = 973640632;

        public TL_starGiftUnique_layer215() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGiftUnique, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.require_premium = TLObject.hasFlag(r0, 64);
            this.resale_ton_only = TLObject.hasFlag(this.flags, 128);
            this.theme_available = TLObject.hasFlag(this.flags, 512);
            this.id = r3.readInt64(r4);
            this.gift_id = r3.readInt64(r4);
            this.title = r3.readString(r4);
            this.slug = r3.readString(r4);
            this.num = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.owner_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.owner_name = r3.readString(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L11;
            this.owner_address = r3.readString(r4);
        L11:
            this.attributes = Vector.deserialize(r3, new TL_stars$TL_starGiftUnique$$ExternalSyntheticLambda0(), r4);
            this.availability_issued = r3.readInt32(r4);
            this.availability_total = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            this.gift_address = r3.readString(r4);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            this.resell_amount = Vector.deserialize(r3, new TL_stars$TL_starGiftUnique$$ExternalSyntheticLambda1(), r4);
        L18:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L21;
            this.released_by = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L21:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L24;
            this.value_amount = r3.readInt64(r4);
            this.value_currency = r3.readString(r4);
        L24:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L27;
            this.theme_peer = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L27:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L30;
            this.peer_color = TLRPC.PeerColor.TLdeserialize(r3, r3.readInt32(r4), r4);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGiftUnique, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r6) {
            r6.writeInt32(constructor);
            int r0 = this.flags;
            ArrayList<StarsAmount> r1 = this.resell_amount;
            if (r1 != null) goto L5;
        L7:
            boolean r12 = false;
        L8:
            int r02 = TLObject.setFlag(r0, 16, r12);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 64, this.require_premium);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 128, this.resale_ton_only);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 512, this.theme_available);
            this.flags = r05;
            r6.writeInt32(r05);
            r6.writeInt64(this.id);
            r6.writeInt64(this.gift_id);
            r6.writeString(this.title);
            r6.writeString(this.slug);
            r6.writeInt32(this.num);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L12;
            this.owner_id.serializeToStream(r6);
        L12:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L15;
            r6.writeString(this.owner_name);
        L15:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L17;
            r6.writeString(this.owner_address);
        L17:
            Vector.serialize(r6, this.attributes);
            r6.writeInt32(this.availability_issued);
            r6.writeInt32(this.availability_total);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L21;
            r6.writeString(this.gift_address);
        L21:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L24;
            Vector.serialize(r6, this.resell_amount);
        L24:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L27;
            this.released_by.serializeToStream(r6);
        L27:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L30;
            r6.writeInt64(this.value_amount);
            r6.writeString(this.value_currency);
        L30:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L33;
            this.theme_peer.serializeToStream(r6);
        L33:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L36;
            this.peer_color.serializeToStream(r6);
            return;
        L36:
            return;
        L5:
            if (r1.isEmpty() == true) goto L7;
            r12 = true;
            goto L8
        }
    }

    public static class TL_starGiftUnique_layer219 extends TL_starGiftUnique {
        public static final int constructor = -1329630181;

        public TL_starGiftUnique_layer219() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGiftUnique, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.require_premium = TLObject.hasFlag(r0, 64);
            this.resale_ton_only = TLObject.hasFlag(this.flags, 128);
            this.theme_available = TLObject.hasFlag(this.flags, 512);
            this.id = r3.readInt64(r4);
            this.gift_id = r3.readInt64(r4);
            this.title = r3.readString(r4);
            this.slug = r3.readString(r4);
            this.num = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.owner_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.owner_name = r3.readString(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L11;
            this.owner_address = r3.readString(r4);
        L11:
            this.attributes = Vector.deserialize(r3, new TL_stars$TL_starGiftUnique$$ExternalSyntheticLambda0(), r4);
            this.availability_issued = r3.readInt32(r4);
            this.availability_total = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            this.gift_address = r3.readString(r4);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            this.resell_amount = Vector.deserialize(r3, new TL_stars$TL_starGiftUnique$$ExternalSyntheticLambda1(), r4);
        L18:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L21;
            this.released_by = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L21:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L24;
            this.value_amount = r3.readInt64(r4);
            this.value_currency = r3.readString(r4);
        L24:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L27;
            this.theme_peer = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L27:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L30;
            this.peer_color = TLRPC.PeerColor.TLdeserialize(r3, r3.readInt32(r4), r4);
        L30:
            if (TLObject.hasFlag(this.flags, 4096) == false) goto L33;
            this.host_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGiftUnique, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r6) {
            r6.writeInt32(constructor);
            int r0 = this.flags;
            ArrayList<StarsAmount> r1 = this.resell_amount;
            if (r1 != null) goto L5;
        L7:
            boolean r12 = false;
        L8:
            int r02 = TLObject.setFlag(r0, 16, r12);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 64, this.require_premium);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 128, this.resale_ton_only);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 512, this.theme_available);
            this.flags = r05;
            r6.writeInt32(r05);
            r6.writeInt64(this.id);
            r6.writeInt64(this.gift_id);
            r6.writeString(this.title);
            r6.writeString(this.slug);
            r6.writeInt32(this.num);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L12;
            this.owner_id.serializeToStream(r6);
        L12:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L15;
            r6.writeString(this.owner_name);
        L15:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L17;
            r6.writeString(this.owner_address);
        L17:
            Vector.serialize(r6, this.attributes);
            r6.writeInt32(this.availability_issued);
            r6.writeInt32(this.availability_total);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L21;
            r6.writeString(this.gift_address);
        L21:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L24;
            Vector.serialize(r6, this.resell_amount);
        L24:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L27;
            this.released_by.serializeToStream(r6);
        L27:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L30;
            r6.writeInt64(this.value_amount);
            r6.writeString(this.value_currency);
        L30:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L33;
            this.theme_peer.serializeToStream(r6);
        L33:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L36;
            this.peer_color.serializeToStream(r6);
        L36:
            if (TLObject.hasFlag(this.flags, 4096) == false) goto L39;
            this.host_id.serializeToStream(r6);
            return;
        L39:
            return;
        L5:
            if (r1.isEmpty() == true) goto L7;
            r12 = true;
            goto L8
        }
    }

    public static class TL_starGiftUnique_layer221 extends TL_starGiftUnique {
        public static final int constructor = 1453155529;

        public TL_starGiftUnique_layer221() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGiftUnique, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.require_premium = TLObject.hasFlag(r0, 64);
            this.resale_ton_only = TLObject.hasFlag(this.flags, 128);
            this.theme_available = TLObject.hasFlag(this.flags, 512);
            this.id = r3.readInt64(r4);
            this.gift_id = r3.readInt64(r4);
            this.title = r3.readString(r4);
            this.slug = r3.readString(r4);
            this.num = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.owner_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.owner_name = r3.readString(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L11;
            this.owner_address = r3.readString(r4);
        L11:
            this.attributes = Vector.deserialize(r3, new TL_stars$TL_starGiftUnique$$ExternalSyntheticLambda0(), r4);
            this.availability_issued = r3.readInt32(r4);
            this.availability_total = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            this.gift_address = r3.readString(r4);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            this.resell_amount = Vector.deserialize(r3, new TL_stars$TL_starGiftUnique$$ExternalSyntheticLambda1(), r4);
        L18:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L21;
            this.released_by = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L21:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L24;
            this.value_amount = r3.readInt64(r4);
            this.value_currency = r3.readString(r4);
            this.value_usd_amount = r3.readInt64(r4);
        L24:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L27;
            this.theme_peer = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L27:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L30;
            this.peer_color = TLRPC.PeerColor.TLdeserialize(r3, r3.readInt32(r4), r4);
        L30:
            if (TLObject.hasFlag(this.flags, 4096) == false) goto L33;
            this.host_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L33:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L36;
            this.offer_min_stars = r3.readInt32(r4);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGiftUnique, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r6) {
            r6.writeInt32(constructor);
            int r0 = this.flags;
            ArrayList<StarsAmount> r1 = this.resell_amount;
            if (r1 != null) goto L5;
        L7:
            boolean r12 = false;
        L8:
            int r02 = TLObject.setFlag(r0, 16, r12);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 64, this.require_premium);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 128, this.resale_ton_only);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 512, this.theme_available);
            this.flags = r05;
            r6.writeInt32(r05);
            r6.writeInt64(this.id);
            r6.writeInt64(this.gift_id);
            r6.writeString(this.title);
            r6.writeString(this.slug);
            r6.writeInt32(this.num);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L12;
            this.owner_id.serializeToStream(r6);
        L12:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L15;
            r6.writeString(this.owner_name);
        L15:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L17;
            r6.writeString(this.owner_address);
        L17:
            Vector.serialize(r6, this.attributes);
            r6.writeInt32(this.availability_issued);
            r6.writeInt32(this.availability_total);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L21;
            r6.writeString(this.gift_address);
        L21:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L24;
            Vector.serialize(r6, this.resell_amount);
        L24:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L27;
            this.released_by.serializeToStream(r6);
        L27:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L30;
            r6.writeInt64(this.value_amount);
            r6.writeString(this.value_currency);
            r6.writeInt64(this.value_usd_amount);
        L30:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L33;
            this.theme_peer.serializeToStream(r6);
        L33:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L36;
            this.peer_color.serializeToStream(r6);
        L36:
            if (TLObject.hasFlag(this.flags, 4096) == false) goto L39;
            this.host_id.serializeToStream(r6);
        L39:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L42;
            r6.writeInt32(this.offer_min_stars);
            return;
        L42:
            return;
        L5:
            if (r1.isEmpty() == true) goto L7;
            r12 = true;
            goto L8
        }
    }

    public static class TL_starGift_layer190 extends TL_starGift {
        public static final int constructor = -1365150482;

        public TL_starGift_layer190() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGift, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r5, boolean r6) {
            int r0 = r5.readInt32(r6);
            this.flags = r0;
            boolean r1 = true;
            this.limited = TLObject.hasFlag(r0, 1);
            this.id = r5.readInt64(r6);
            this.sticker = TLRPC.Document.TLdeserialize(r5, r5.readInt32(r6), r6);
            this.stars = r5.readInt64(r6);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            this.availability_remains = r5.readInt32(r6);
            this.availability_total = r5.readInt32(r6);
        L5:
            this.convert_stars = r5.readInt64(r6);
            if (this.limited == true) goto L8;
        L10:
            r1 = false;
        L11:
            this.sold_out = r1;
            return;
        L8:
            if (this.availability_remains > 0) goto L10;
            goto L10
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGift, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.limited);
            this.flags = r0;
            r4.writeInt32(r0);
            r4.writeInt64(this.id);
            this.sticker.serializeToStream(r4);
            r4.writeInt64(this.stars);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            r4.writeInt32(this.availability_remains);
            r4.writeInt32(this.availability_total);
        L5:
            r4.writeInt64(this.convert_stars);
        }
    }

    public static class TL_starGift_layer195 extends TL_starGift {
        public static final int constructor = 1237678029;

        public TL_starGift_layer195() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGift, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r6, boolean r7) {
            int r0 = r6.readInt32(r7);
            this.flags = r0;
            this.limited = TLObject.hasFlag(r0, 1);
            this.sold_out = TLObject.hasFlag(this.flags, 2);
            this.birthday = TLObject.hasFlag(this.flags, 4);
            this.id = r6.readInt64(r7);
            this.sticker = TLRPC.Document.TLdeserialize(r6, r6.readInt32(r7), r7);
            this.stars = r6.readInt64(r7);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            this.availability_remains = r6.readInt32(r7);
            this.availability_total = r6.readInt32(r7);
        L5:
            this.convert_stars = r6.readInt64(r7);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.first_sale_date = r6.readInt32(r7);
            this.last_sale_date = r6.readInt32(r7);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGift, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r6) {
            r6.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.limited);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.sold_out);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 4, this.birthday);
            this.flags = r03;
            r6.writeInt32(r03);
            r6.writeInt64(this.id);
            this.sticker.serializeToStream(r6);
            r6.writeInt64(this.stars);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            r6.writeInt32(this.availability_remains);
            r6.writeInt32(this.availability_total);
        L5:
            r6.writeInt64(this.convert_stars);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r6.writeInt32(this.first_sale_date);
            r6.writeInt32(this.last_sale_date);
            return;
        }
    }

    public static class TL_starGift_layer202 extends TL_starGift {
        public static final int constructor = 46953416;

        public TL_starGift_layer202() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGift, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r7, boolean r8) {
            int r0 = r7.readInt32(r8);
            this.flags = r0;
            this.limited = TLObject.hasFlag(r0, 1);
            this.sold_out = TLObject.hasFlag(this.flags, 2);
            this.birthday = TLObject.hasFlag(this.flags, 4);
            this.can_upgrade = TLObject.hasFlag(this.flags, 8);
            this.id = r7.readInt64(r8);
            this.sticker = TLRPC.Document.TLdeserialize(r7, r7.readInt32(r8), r8);
            this.stars = r7.readInt64(r8);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            this.availability_remains = r7.readInt32(r8);
            this.availability_total = r7.readInt32(r8);
        L5:
            this.convert_stars = r7.readInt64(r8);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.first_sale_date = r7.readInt32(r8);
            this.last_sale_date = r7.readInt32(r8);
        L9:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L12;
            this.upgrade_stars = r7.readInt64(r8);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGift, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r6) {
            r6.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.limited);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.sold_out);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 4, this.birthday);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 8, this.can_upgrade);
            this.flags = r04;
            r6.writeInt32(r04);
            r6.writeInt64(this.id);
            this.sticker.serializeToStream(r6);
            r6.writeInt64(this.stars);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            r6.writeInt32(this.availability_remains);
            r6.writeInt32(this.availability_total);
        L5:
            r6.writeInt64(this.convert_stars);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r6.writeInt32(this.first_sale_date);
            r6.writeInt32(this.last_sale_date);
        L9:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L12;
            r6.writeInt64(this.upgrade_stars);
            return;
        }
    }

    public static class TL_starGift_layer206 extends TL_starGift {
        public static final int constructor = -970274264;

        public TL_starGift_layer206() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGift, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r7, boolean r8) {
            int r0 = r7.readInt32(r8);
            this.flags = r0;
            this.limited = TLObject.hasFlag(r0, 1);
            this.sold_out = TLObject.hasFlag(this.flags, 2);
            this.birthday = TLObject.hasFlag(this.flags, 4);
            this.can_upgrade = TLObject.hasFlag(this.flags, 8);
            this.id = r7.readInt64(r8);
            this.sticker = TLRPC.Document.TLdeserialize(r7, r7.readInt32(r8), r8);
            this.stars = r7.readInt64(r8);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.availability_remains = r7.readInt32(r8);
            this.availability_total = r7.readInt32(r8);
        L6:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L8;
            this.availability_resale = r7.readInt64(r8);
        L8:
            this.convert_stars = r7.readInt64(r8);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L12;
            this.first_sale_date = r7.readInt32(r8);
            this.last_sale_date = r7.readInt32(r8);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            this.upgrade_stars = r7.readInt64(r8);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            this.resell_min_stars = r7.readInt64(r8);
        L18:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L21;
            this.title = r7.readString(r8);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGift, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r8) {
            r8.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.limited);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.sold_out);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 4, this.birthday);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 8, this.can_upgrade);
            this.flags = r04;
            r8.writeInt32(r04);
            r8.writeInt64(this.id);
            this.sticker.serializeToStream(r8);
            r8.writeInt64(this.stars);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r8.writeInt32(this.availability_remains);
            r8.writeInt32(this.availability_total);
        L6:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L8;
            r8.writeInt64(this.availability_resale);
        L8:
            r8.writeInt64(this.convert_stars);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L12;
            r8.writeInt32(this.first_sale_date);
            r8.writeInt32(this.last_sale_date);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            r8.writeInt64(this.upgrade_stars);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            r8.writeInt64(this.resell_min_stars);
        L18:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L21;
            r8.writeString(this.title);
            return;
        }
    }

    public static class TL_starGift_layer209 extends TL_starGift {
        public static final int constructor = 2139438098;

        public TL_starGift_layer209() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGift, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r7, boolean r8) {
            int r0 = r7.readInt32(r8);
            this.flags = r0;
            this.limited = TLObject.hasFlag(r0, 1);
            this.sold_out = TLObject.hasFlag(this.flags, 2);
            this.birthday = TLObject.hasFlag(this.flags, 4);
            this.can_upgrade = TLObject.hasFlag(this.flags, 8);
            this.id = r7.readInt64(r8);
            this.sticker = TLRPC.Document.TLdeserialize(r7, r7.readInt32(r8), r8);
            this.stars = r7.readInt64(r8);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.availability_remains = r7.readInt32(r8);
            this.availability_total = r7.readInt32(r8);
        L6:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L8;
            this.availability_resale = r7.readInt64(r8);
        L8:
            this.convert_stars = r7.readInt64(r8);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L12;
            this.first_sale_date = r7.readInt32(r8);
            this.last_sale_date = r7.readInt32(r8);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            this.upgrade_stars = r7.readInt64(r8);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            this.resell_min_stars = r7.readInt64(r8);
        L18:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L21;
            this.title = r7.readString(r8);
        L21:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L24;
            this.released_by = TLRPC.Peer.TLdeserialize(r7, r7.readInt32(r8), r8);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGift, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r8) {
            r8.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.limited);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.sold_out);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 4, this.birthday);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 8, this.can_upgrade);
            this.flags = r04;
            r8.writeInt32(r04);
            r8.writeInt64(this.id);
            this.sticker.serializeToStream(r8);
            r8.writeInt64(this.stars);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r8.writeInt32(this.availability_remains);
            r8.writeInt32(this.availability_total);
        L6:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L8;
            r8.writeInt64(this.availability_resale);
        L8:
            r8.writeInt64(this.convert_stars);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L12;
            r8.writeInt32(this.first_sale_date);
            r8.writeInt32(this.last_sale_date);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            r8.writeInt64(this.upgrade_stars);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            r8.writeInt64(this.resell_min_stars);
        L18:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L21;
            r8.writeString(this.title);
        L21:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L24;
            this.released_by.serializeToStream(r8);
            return;
        }
    }

    public static class TL_starGift_layer212 extends TL_starGift {
        public static final int constructor = 12386139;

        public TL_starGift_layer212() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGift, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r8, boolean r9) {
            int r0 = r8.readInt32(r9);
            this.flags = r0;
            this.limited = TLObject.hasFlag(r0, 1);
            this.sold_out = TLObject.hasFlag(this.flags, 2);
            this.birthday = TLObject.hasFlag(this.flags, 4);
            this.can_upgrade = TLObject.hasFlag(this.flags, 8);
            this.require_premium = TLObject.hasFlag(this.flags, 128);
            this.limited_per_user = TLObject.hasFlag(this.flags, 256);
            this.id = r8.readInt64(r9);
            this.sticker = TLRPC.Document.TLdeserialize(r8, r8.readInt32(r9), r9);
            this.stars = r8.readInt64(r9);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.availability_remains = r8.readInt32(r9);
            this.availability_total = r8.readInt32(r9);
        L6:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L8;
            this.availability_resale = r8.readInt64(r9);
        L8:
            this.convert_stars = r8.readInt64(r9);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L12;
            this.first_sale_date = r8.readInt32(r9);
            this.last_sale_date = r8.readInt32(r9);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            this.upgrade_stars = r8.readInt64(r9);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            this.resell_min_stars = r8.readInt64(r9);
        L18:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L21;
            this.title = r8.readString(r9);
        L21:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L24;
            this.released_by = TLRPC.Peer.TLdeserialize(r8, r8.readInt32(r9), r9);
        L24:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L27;
            this.per_user_total = r8.readInt32(r9);
            this.per_user_remains = r8.readInt32(r9);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGift, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r9) {
            r9.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.limited);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.sold_out);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 4, this.birthday);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 8, this.can_upgrade);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 128, this.require_premium);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 256, this.limited_per_user);
            this.flags = r06;
            r9.writeInt32(r06);
            r9.writeInt64(this.id);
            this.sticker.serializeToStream(r9);
            r9.writeInt64(this.stars);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r9.writeInt32(this.availability_remains);
            r9.writeInt32(this.availability_total);
        L6:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L8;
            r9.writeInt64(this.availability_resale);
        L8:
            r9.writeInt64(this.convert_stars);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L12;
            r9.writeInt32(this.first_sale_date);
            r9.writeInt32(this.last_sale_date);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            r9.writeInt64(this.upgrade_stars);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            r9.writeInt64(this.resell_min_stars);
        L18:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L21;
            r9.writeString(this.title);
        L21:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L24;
            this.released_by.serializeToStream(r9);
        L24:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L27;
            r9.writeInt32(this.per_user_total);
            r9.writeInt32(this.per_user_remains);
            return;
        }
    }

    public static class TL_starGift_layer217 extends TL_starGift {
        public static final int constructor = -2136190013;

        public TL_starGift_layer217() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGift, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r8, boolean r9) {
            int r0 = r8.readInt32(r9);
            this.flags = r0;
            this.limited = TLObject.hasFlag(r0, 1);
            this.sold_out = TLObject.hasFlag(this.flags, 2);
            this.birthday = TLObject.hasFlag(this.flags, 4);
            this.can_upgrade = TLObject.hasFlag(this.flags, 8);
            this.require_premium = TLObject.hasFlag(this.flags, 128);
            this.limited_per_user = TLObject.hasFlag(this.flags, 256);
            this.peer_color_available = TLObject.hasFlag(this.flags, 1024);
            this.id = r8.readInt64(r9);
            this.sticker = TLRPC.Document.TLdeserialize(r8, r8.readInt32(r9), r9);
            this.stars = r8.readInt64(r9);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.availability_remains = r8.readInt32(r9);
            this.availability_total = r8.readInt32(r9);
        L6:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L8;
            this.availability_resale = r8.readInt64(r9);
        L8:
            this.convert_stars = r8.readInt64(r9);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L12;
            this.first_sale_date = r8.readInt32(r9);
            this.last_sale_date = r8.readInt32(r9);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            this.upgrade_stars = r8.readInt64(r9);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            this.resell_min_stars = r8.readInt64(r9);
        L18:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L21;
            this.title = r8.readString(r9);
        L21:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L24;
            this.released_by = TLRPC.Peer.TLdeserialize(r8, r8.readInt32(r9), r9);
        L24:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L27;
            this.per_user_total = r8.readInt32(r9);
            this.per_user_remains = r8.readInt32(r9);
        L27:
            if (TLObject.hasFlag(this.flags, 512) == false) goto L30;
            this.locked_until_date = r8.readInt32(r9);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGift, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r9) {
            r9.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.limited);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.sold_out);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 4, this.birthday);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 8, this.can_upgrade);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 128, this.require_premium);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 256, this.limited_per_user);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 1024, this.peer_color_available);
            this.flags = r07;
            r9.writeInt32(r07);
            r9.writeInt64(this.id);
            this.sticker.serializeToStream(r9);
            r9.writeInt64(this.stars);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r9.writeInt32(this.availability_remains);
            r9.writeInt32(this.availability_total);
        L6:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L8;
            r9.writeInt64(this.availability_resale);
        L8:
            r9.writeInt64(this.convert_stars);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L12;
            r9.writeInt32(this.first_sale_date);
            r9.writeInt32(this.last_sale_date);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            r9.writeInt64(this.upgrade_stars);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            r9.writeInt64(this.resell_min_stars);
        L18:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L21;
            r9.writeString(this.title);
        L21:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L24;
            this.released_by.serializeToStream(r9);
        L24:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L27;
            r9.writeInt32(this.per_user_total);
            r9.writeInt32(this.per_user_remains);
        L27:
            if (TLObject.hasFlag(this.flags, 512) == false) goto L30;
            r9.writeInt32(this.locked_until_date);
            return;
        }
    }

    public static class TL_starGift_layer219 extends TL_starGift {
        public static final int constructor = 463097215;

        public TL_starGift_layer219() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGift, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r9, boolean r10) {
            int r0 = r9.readInt32(r10);
            this.flags = r0;
            this.limited = TLObject.hasFlag(r0, 1);
            this.sold_out = TLObject.hasFlag(this.flags, 2);
            this.birthday = TLObject.hasFlag(this.flags, 4);
            this.can_upgrade = TLObject.hasFlag(this.flags, 8);
            this.require_premium = TLObject.hasFlag(this.flags, 128);
            this.limited_per_user = TLObject.hasFlag(this.flags, 256);
            this.peer_color_available = TLObject.hasFlag(this.flags, 1024);
            this.auction = TLObject.hasFlag(this.flags, 2048);
            this.id = r9.readInt64(r10);
            this.sticker = TLRPC.Document.TLdeserialize(r9, r9.readInt32(r10), r10);
            this.stars = r9.readInt64(r10);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.availability_remains = r9.readInt32(r10);
            this.availability_total = r9.readInt32(r10);
        L6:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L8;
            this.availability_resale = r9.readInt64(r10);
        L8:
            this.convert_stars = r9.readInt64(r10);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L12;
            this.first_sale_date = r9.readInt32(r10);
            this.last_sale_date = r9.readInt32(r10);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            this.upgrade_stars = r9.readInt64(r10);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            this.resell_min_stars = r9.readInt64(r10);
        L18:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L21;
            this.title = r9.readString(r10);
        L21:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L24;
            this.released_by = TLRPC.Peer.TLdeserialize(r9, r9.readInt32(r10), r10);
        L24:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L27;
            this.per_user_total = r9.readInt32(r10);
            this.per_user_remains = r9.readInt32(r10);
        L27:
            if (TLObject.hasFlag(this.flags, 512) == false) goto L30;
            this.locked_until_date = r9.readInt32(r10);
        L30:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L33;
            this.auction_slug = r9.readString(r10);
            this.gifts_per_round = r9.readInt32(r10);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starGift, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r10) {
            r10.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.limited);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.sold_out);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 4, this.birthday);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 8, this.can_upgrade);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 128, this.require_premium);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 256, this.limited_per_user);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 1024, this.peer_color_available);
            this.flags = r07;
            int r08 = TLObject.setFlag(r07, 2048, this.auction);
            this.flags = r08;
            r10.writeInt32(r08);
            r10.writeInt64(this.id);
            this.sticker.serializeToStream(r10);
            r10.writeInt64(this.stars);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r10.writeInt32(this.availability_remains);
            r10.writeInt32(this.availability_total);
        L6:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L8;
            r10.writeInt64(this.availability_resale);
        L8:
            r10.writeInt64(this.convert_stars);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L12;
            r10.writeInt32(this.first_sale_date);
            r10.writeInt32(this.last_sale_date);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            r10.writeInt64(this.upgrade_stars);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            r10.writeInt64(this.resell_min_stars);
        L18:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L21;
            r10.writeString(this.title);
        L21:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L24;
            this.released_by.serializeToStream(r10);
        L24:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L27;
            r10.writeInt32(this.per_user_total);
            r10.writeInt32(this.per_user_remains);
        L27:
            if (TLObject.hasFlag(this.flags, 512) == false) goto L30;
            r10.writeInt32(this.locked_until_date);
        L30:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L33;
            r10.writeString(this.auction_slug);
            r10.writeInt32(this.gifts_per_round);
            return;
        }
    }

    public static class TL_starGifts extends StarGifts {
        public static final int constructor = 785918357;
        public ArrayList<TLRPC.Chat> chats;
        public ArrayList<StarGift> gifts;
        public int hash;
        public ArrayList<TLRPC.User> users;

        public TL_starGifts() {
            this.gifts = new ArrayList();
            this.chats = new ArrayList();
            this.users = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.hash = r2.readInt32(r3);
            this.gifts = Vector.deserialize(r2, new TL_stars$TL_starGifts$$ExternalSyntheticLambda0(), r3);
            this.chats = Vector.deserialize(r2, new TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1(), r3);
            this.users = Vector.deserialize(r2, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.hash);
            Vector.serialize(r2, this.gifts);
            Vector.serialize(r2, this.chats);
            Vector.serialize(r2, this.users);
        }
    }

    public static class TL_starGiftsNotModified extends StarGifts {
        public static final int constructor = -1551326360;

        public TL_starGiftsNotModified() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes.dex */
    public static class TL_starsAmount extends StarsAmount {
        public static final int constructor = -1145654109;

        public TL_starsAmount() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.StarsAmount
        public AmountUtils$Currency getCurrency() {
            return AmountUtils$Currency.STARS;
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.amount = r3.readInt64(r4);
            this.nanos = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.amount);
            r3.writeInt32(this.nanos);
        }
    }

    public static class TL_starsGiftOption extends TLObject {
        public static final int constructor = 1577421297;
        public long amount;
        public String currency;
        public boolean extended;
        public int flags;
        public boolean loadingStorePrice;
        public boolean missingStorePrice;
        public long stars;
        public String store_product;

        public TL_starsGiftOption() {
        }

        public static TL_starsGiftOption TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (1577421297 == r3) goto L5;
            TL_starsGiftOption r0 = null;
        L7:
            return (TL_starsGiftOption) TLObject.TLdeserialize(TL_starsGiftOption.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_starsGiftOption();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.extended = TLObject.hasFlag(r0, 2);
            this.stars = r3.readInt64(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            this.store_product = r3.readString(r4);
        L5:
            this.currency = r3.readString(r4);
            this.amount = r3.readInt64(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.extended);
            this.flags = r0;
            r4.writeInt32(r0);
            r4.writeInt64(this.stars);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            r4.writeString(this.store_product);
        L5:
            r4.writeString(this.currency);
            r4.writeInt64(this.amount);
        }
    }

    public static class TL_starsGiveawayOption extends TLObject {
        public static final int constructor = -1798404822;
        public long amount;
        public String currency;
        public boolean extended;
        public int flags;
        public boolean isDefault;
        public boolean loadingStorePrice;
        public boolean missingStorePrice;
        public long stars;
        public String store_product;
        public ArrayList<TL_starsGiveawayWinnersOption> winners;
        public int yearly_boosts;

        public TL_starsGiveawayOption() {
            this.winners = new ArrayList();
        }

        public static TL_starsGiveawayOption TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-1798404822) == r3) goto L5;
            TL_starsGiveawayOption r0 = null;
        L7:
            return (TL_starsGiveawayOption) TLObject.TLdeserialize(TL_starsGiveawayOption.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_starsGiveawayOption();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.extended = TLObject.hasFlag(r0, 1);
            this.isDefault = TLObject.hasFlag(this.flags, 2);
            this.stars = r3.readInt64(r4);
            this.yearly_boosts = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L5;
            this.store_product = r3.readString(r4);
        L5:
            this.currency = r3.readString(r4);
            this.amount = r3.readInt64(r4);
            this.winners = Vector.deserialize(r3, new TL_stars$TL_starsGiveawayOption$$ExternalSyntheticLambda0(), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.extended);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.isDefault);
            this.flags = r02;
            r4.writeInt32(r02);
            r4.writeInt64(this.stars);
            r4.writeInt32(this.yearly_boosts);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L5;
            r4.writeString(this.store_product);
        L5:
            r4.writeString(this.currency);
            r4.writeInt64(this.amount);
            Vector.serialize(r4, this.winners);
        }
    }

    public static class TL_starsGiveawayWinnersOption extends TLObject {
        public static final int constructor = 1411605001;
        public int flags;
        public boolean isDefault;
        public long per_user_stars;
        public int users;

        public TL_starsGiveawayWinnersOption() {
        }

        public static TL_starsGiveawayWinnersOption TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (1411605001 == r3) goto L5;
            TL_starsGiveawayWinnersOption r0 = null;
        L7:
            return (TL_starsGiveawayWinnersOption) TLObject.TLdeserialize(TL_starsGiveawayWinnersOption.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_starsGiveawayWinnersOption();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.isDefault = TLObject.hasFlag(r0, 1);
            this.users = r3.readInt32(r4);
            this.per_user_stars = r3.readInt64(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.isDefault);
            this.flags = r0;
            r4.writeInt32(r0);
            r4.writeInt32(this.users);
            r4.writeInt64(this.per_user_stars);
        }
    }

    public static class TL_starsSubscription extends StarsSubscription {
        public static final int constructor = 779004698;

        public TL_starsSubscription() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.canceled = TLObject.hasFlag(r0, 1);
            this.can_refulfill = TLObject.hasFlag(this.flags, 2);
            this.missing_balance = TLObject.hasFlag(this.flags, 4);
            this.bot_canceled = TLObject.hasFlag(this.flags, 128);
            this.id = r3.readString(r4);
            this.peer = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.until_date = r3.readInt32(r4);
            this.pricing = TL_starsSubscriptionPricing.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L6;
            this.chat_invite_hash = r3.readString(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L9;
            this.title = r3.readString(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L12;
            this.photo = TLRPC.WebDocument.TLdeserialize(r3, r3.readInt32(r4), r4);
        L12:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L15;
            this.invoice_slug = r3.readString(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.canceled);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.can_refulfill);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 4, this.missing_balance);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 128, this.bot_canceled);
            this.flags = r04;
            r4.writeInt32(r04);
            r4.writeString(this.id);
            this.peer.serializeToStream(r4);
            r4.writeInt32(this.until_date);
            this.pricing.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L6;
            r4.writeString(this.chat_invite_hash);
        L6:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L9;
            r4.writeString(this.title);
        L9:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L12;
            this.photo.serializeToStream(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L15;
            r4.writeString(this.invoice_slug);
            return;
        }
    }

    public static class TL_starsSubscriptionPricing extends TLObject {
        public static final int constructor = 88173912;
        public long amount;
        public int period;

        public TL_starsSubscriptionPricing() {
        }

        public static TL_starsSubscriptionPricing TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (88173912 == r3) goto L5;
            TL_starsSubscriptionPricing r0 = null;
        L7:
            return (TL_starsSubscriptionPricing) TLObject.TLdeserialize(TL_starsSubscriptionPricing.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_starsSubscriptionPricing();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.period = r2.readInt32(r3);
            this.amount = r2.readInt64(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.period);
            r3.writeInt64(this.amount);
        }
    }

    public static class TL_starsSubscription_layer193 extends StarsSubscription {
        public static final int constructor = 1401868056;

        public TL_starsSubscription_layer193() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.canceled = TLObject.hasFlag(r0, 1);
            this.can_refulfill = TLObject.hasFlag(this.flags, 2);
            this.missing_balance = TLObject.hasFlag(this.flags, 4);
            this.id = r3.readString(r4);
            this.peer = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.until_date = r3.readInt32(r4);
            this.pricing = TL_starsSubscriptionPricing.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L6;
            this.chat_invite_hash = r3.readString(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.canceled);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.can_refulfill);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 4, this.missing_balance);
            this.flags = r03;
            r4.writeInt32(r03);
            r4.writeString(this.id);
            this.peer.serializeToStream(r4);
            r4.writeInt32(this.until_date);
            this.pricing.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L6;
            r4.writeString(this.chat_invite_hash);
            return;
        }
    }

    public static class TL_starsSubscription_old extends TL_starsSubscription {
        public static final int constructor = -797707802;

        public TL_starsSubscription_old() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starsSubscription, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.canceled = TLObject.hasFlag(r0, 1);
            this.can_refulfill = TLObject.hasFlag(this.flags, 2);
            this.missing_balance = TLObject.hasFlag(this.flags, 4);
            this.id = r3.readString(r4);
            this.peer = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.until_date = r3.readInt32(r4);
            this.pricing = TL_starsSubscriptionPricing.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starsSubscription, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.canceled);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.can_refulfill);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 4, this.missing_balance);
            this.flags = r03;
            r4.writeInt32(r03);
            r4.writeString(this.id);
            this.peer.serializeToStream(r4);
            r4.writeInt32(this.until_date);
            this.pricing.serializeToStream(r4);
        }
    }

    public static class TL_starsTonAmount extends StarsAmount {
        public static final int constructor = 1957618656;

        public TL_starsTonAmount() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.StarsAmount
        public AmountUtils$Currency getCurrency() {
            return AmountUtils$Currency.TON;
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.amount = r1.readInt64(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.amount);
        }
    }

    public static class TL_starsTopupOption extends TLObject {
        public static final int constructor = 198776256;
        public long amount;
        public String currency;
        public boolean extended;
        public int flags;
        public boolean loadingStorePrice;
        public boolean missingStorePrice;
        public long stars;
        public String store_product;

        public TL_starsTopupOption() {
        }

        public static TL_starsTopupOption TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (198776256 == r3) goto L5;
            TL_starsTopupOption r0 = null;
        L7:
            return (TL_starsTopupOption) TLObject.TLdeserialize(TL_starsTopupOption.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_starsTopupOption();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.extended = TLObject.hasFlag(r0, 2);
            this.stars = r3.readInt64(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            this.store_product = r3.readString(r4);
        L5:
            this.currency = r3.readString(r4);
            this.amount = r3.readInt64(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.extended);
            this.flags = r0;
            r4.writeInt32(r0);
            r4.writeInt64(this.stars);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            r4.writeString(this.store_product);
        L5:
            r4.writeString(this.currency);
            r4.writeInt64(this.amount);
        }
    }

    public static class TL_starsTransaction extends StarsTransaction {
        public static final int constructor = 325426864;

        public TL_starsTransaction() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r7, boolean r8) {
            int r0 = r7.readInt32(r8);
            this.flags = r0;
            this.refund = TLObject.hasFlag(r0, 8);
            this.pending = TLObject.hasFlag(this.flags, 16);
            this.failed = TLObject.hasFlag(this.flags, 64);
            this.gift = TLObject.hasFlag(this.flags, 1024);
            this.reaction = TLObject.hasFlag(this.flags, 2048);
            this.subscription = TLObject.hasFlag(this.flags, 4096);
            this.floodskip = TLObject.hasFlag(this.flags, 32768);
            this.stargift_upgrade = TLObject.hasFlag(this.flags, 262144);
            this.paid_message = TLObject.hasFlag(this.flags, 524288);
            this.premium_gift = TLObject.hasFlag(this.flags, 1048576);
            this.business_transfer = TLObject.hasFlag(this.flags, TLObject.FLAG_21);
            this.stargift_resale = TLObject.hasFlag(this.flags, 4194304);
            this.posts_search = TLObject.hasFlag(this.flags, 16777216);
            this.stargift_prepaid_upgrade = TLObject.hasFlag(this.flags, 33554432);
            this.stargift_drop_original_details = TLObject.hasFlag(this.flags, 67108864);
            this.phonegroup_message = TLObject.hasFlag(this.flags, 134217728);
            this.stargift_auction_bid = TLObject.hasFlag(this.flags, 268435456);
            this.offer = TLObject.hasFlag(this.flags, 536870912);
            this.id = r7.readString(r8);
            this.amount = StarsAmount.TLdeserialize(r7, r7.readInt32(r8), r8);
            this.date = r7.readInt32(r8);
            this.peer = StarsTransactionPeer.TLdeserialize(r7, r7.readInt32(r8), r8);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.title = r7.readString(r8);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.description = r7.readString(r8);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.photo = TLRPC.WebDocument.TLdeserialize(r7, r7.readInt32(r8), r8);
        L12:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L15;
            this.transaction_date = r7.readInt32(r8);
            this.transaction_url = r7.readString(r8);
        L15:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L18;
            this.bot_payload = r7.readByteArray(r8);
        L18:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L21;
            this.msg_id = r7.readInt32(r8);
        L21:
            if (TLObject.hasFlag(this.flags, 512) == false) goto L24;
            this.extended_media = Vector.deserialize(r7, new TL_stars$TL_starsTransaction$$ExternalSyntheticLambda0(), r8);
        L24:
            if (TLObject.hasFlag(this.flags, 4096) == false) goto L27;
            this.subscription_period = r7.readInt32(r8);
        L27:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L30;
            this.giveaway_post_id = r7.readInt32(r8);
        L30:
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L33;
            this.stargift = StarGift.TLdeserialize(r7, r7.readInt32(r8), r8);
        L33:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L36;
            this.floodskip_number = r7.readInt32(r8);
        L36:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L39;
            this.starref_commission_permille = r7.readInt32(r8);
        L39:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L42;
            this.starref_peer = TLRPC.Peer.TLdeserialize(r7, r7.readInt32(r8), r8);
            this.starref_amount = StarsAmount.TLdeserialize(r7, r7.readInt32(r8), r8);
        L42:
            if (TLObject.hasFlag(this.flags, 524288) == false) goto L45;
            this.paid_messages = r7.readInt32(r8);
        L45:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L48;
            this.premium_gift_months = r7.readInt32(r8);
        L48:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L51;
            this.ads_proceeds_from_date = r7.readInt32(r8);
            this.ads_proceeds_to_date = r7.readInt32(r8);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r8) {
            r8.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 8, this.refund);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 16, this.pending);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 64, this.failed);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 1024, this.gift);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 2048, this.reaction);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 4096, this.subscription);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 32768, this.floodskip);
            this.flags = r07;
            int r08 = TLObject.setFlag(r07, 262144, this.stargift_upgrade);
            this.flags = r08;
            int r09 = TLObject.setFlag(r08, 524288, this.paid_message);
            this.flags = r09;
            int r010 = TLObject.setFlag(r09, 1048576, this.premium_gift);
            this.flags = r010;
            int r011 = TLObject.setFlag(r010, TLObject.FLAG_21, this.business_transfer);
            this.flags = r011;
            int r012 = TLObject.setFlag(r011, 4194304, this.stargift_resale);
            this.flags = r012;
            int r013 = TLObject.setFlag(r012, 16777216, this.posts_search);
            this.flags = r013;
            int r014 = TLObject.setFlag(r013, 33554432, this.stargift_prepaid_upgrade);
            this.flags = r014;
            int r015 = TLObject.setFlag(r014, 67108864, this.stargift_drop_original_details);
            this.flags = r015;
            int r016 = TLObject.setFlag(r015, 134217728, this.phonegroup_message);
            this.flags = r016;
            int r017 = TLObject.setFlag(r016, 268435456, this.stargift_auction_bid);
            this.flags = r017;
            int r018 = TLObject.setFlag(r017, 536870912, this.offer);
            this.flags = r018;
            r8.writeInt32(r018);
            r8.writeString(this.id);
            this.amount.serializeToStream(r8);
            r8.writeInt32(this.date);
            this.peer.serializeToStream(r8);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r8.writeString(this.title);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r8.writeString(this.description);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.photo.serializeToStream(r8);
        L12:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L15;
            r8.writeInt32(this.transaction_date);
            r8.writeString(this.transaction_url);
        L15:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L18;
            r8.writeByteArray(this.bot_payload);
        L18:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L21;
            r8.writeInt32(this.msg_id);
        L21:
            if (TLObject.hasFlag(this.flags, 512) == false) goto L24;
            Vector.serialize(r8, this.extended_media);
        L24:
            if (TLObject.hasFlag(this.flags, 4096) == false) goto L27;
            r8.writeInt32(this.subscription_period);
        L27:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L30;
            r8.writeInt32(this.giveaway_post_id);
        L30:
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L33;
            this.stargift.serializeToStream(r8);
        L33:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L36;
            r8.writeInt32(this.floodskip_number);
        L36:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L39;
            r8.writeInt32(this.starref_commission_permille);
        L39:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L42;
            this.starref_peer.serializeToStream(r8);
            this.starref_amount.serializeToStream(r8);
        L42:
            if (TLObject.hasFlag(this.flags, 524288) == false) goto L45;
            r8.writeInt32(this.paid_messages);
        L45:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L48;
            r8.writeInt32(this.premium_gift_months);
        L48:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L51;
            r8.writeInt32(this.ads_proceeds_from_date);
            r8.writeInt32(this.ads_proceeds_to_date);
            return;
        }
    }

    public static class TL_starsTransactionPeer extends StarsTransactionPeer {
        public static final int constructor = -670195363;

        public TL_starsTransactionPeer() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.peer = TLRPC.Peer.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
        }
    }

    public static class TL_starsTransactionPeerAPI extends StarsTransactionPeer {
        public static final int constructor = -110658899;

        public TL_starsTransactionPeerAPI() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_starsTransactionPeerAds extends StarsTransactionPeer {
        public static final int constructor = 1617438738;

        public TL_starsTransactionPeerAds() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_starsTransactionPeerAppStore extends StarsTransactionPeer {
        public static final int constructor = -1269320843;

        public TL_starsTransactionPeerAppStore() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_starsTransactionPeerFragment extends StarsTransactionPeer {
        public static final int constructor = -382740222;

        public TL_starsTransactionPeerFragment() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_starsTransactionPeerPlayMarket extends StarsTransactionPeer {
        public static final int constructor = 2069236235;

        public TL_starsTransactionPeerPlayMarket() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_starsTransactionPeerPremiumBot extends StarsTransactionPeer {
        public static final int constructor = 621656824;

        public TL_starsTransactionPeerPremiumBot() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_starsTransactionPeerUnsupported extends StarsTransactionPeer {
        public static final int constructor = -1779253276;

        public TL_starsTransactionPeerUnsupported() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_starsTransaction_layer181 extends StarsTransaction {
        public static final int constructor = -865044046;

        public TL_starsTransaction_layer181() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.refund = TLObject.hasFlag(r0, 8);
            this.id = r3.readString(r4);
            this.amount = StarsAmount.ofStars(r3.readInt64(r4));
            this.date = r3.readInt32(r4);
            this.peer = StarsTransactionPeer.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.title = r3.readString(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.description = r3.readString(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.photo = TLRPC.WebDocument.TLdeserialize(r3, r3.readInt32(r4), r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 8, this.refund);
            this.flags = r0;
            r4.writeInt32(r0);
            r4.writeString(this.id);
            r4.writeInt64(this.amount.amount);
            r4.writeInt32(this.date);
            this.peer.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r4.writeString(this.title);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r4.writeString(this.description);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.photo.serializeToStream(r4);
            return;
        }
    }

    public static class TL_starsTransaction_layer182 extends TL_starsTransaction {
        public static final int constructor = -1442789224;

        public TL_starsTransaction_layer182() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starsTransaction, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.refund = TLObject.hasFlag(r0, 8);
            this.pending = TLObject.hasFlag(this.flags, 16);
            this.failed = TLObject.hasFlag(this.flags, 64);
            this.id = r3.readString(r4);
            this.amount = StarsAmount.ofStars(r3.readInt64(r4));
            this.date = r3.readInt32(r4);
            this.peer = StarsTransactionPeer.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.title = r3.readString(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.description = r3.readString(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.photo = TLRPC.WebDocument.TLdeserialize(r3, r3.readInt32(r4), r4);
        L12:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L15;
            this.transaction_date = r3.readInt32(r4);
            this.transaction_url = r3.readString(r4);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starsTransaction, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 8, this.refund);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 16, this.pending);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 64, this.failed);
            this.flags = r03;
            r4.writeInt32(r03);
            r4.writeString(this.id);
            r4.writeInt64(this.amount.amount);
            r4.writeInt32(this.date);
            this.peer.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r4.writeString(this.title);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r4.writeString(this.description);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.photo.serializeToStream(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L15;
            r4.writeInt32(this.transaction_date);
            r4.writeString(this.transaction_url);
            return;
        }
    }

    public static class TL_starsTransaction_layer185 extends TL_starsTransaction {
        public static final int constructor = 766853519;

        public TL_starsTransaction_layer185() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starsTransaction, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.refund = TLObject.hasFlag(r0, 8);
            this.pending = TLObject.hasFlag(this.flags, 16);
            this.failed = TLObject.hasFlag(this.flags, 64);
            this.gift = TLObject.hasFlag(this.flags, 1024);
            this.id = r3.readString(r4);
            this.amount = StarsAmount.ofStars(r3.readInt64(r4));
            this.date = r3.readInt32(r4);
            this.peer = StarsTransactionPeer.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.title = r3.readString(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.description = r3.readString(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.photo = TLRPC.WebDocument.TLdeserialize(r3, r3.readInt32(r4), r4);
        L12:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L15;
            this.transaction_date = r3.readInt32(r4);
            this.transaction_url = r3.readString(r4);
        L15:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L18;
            this.bot_payload = r3.readByteArray(r4);
        L18:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L21;
            this.msg_id = r3.readInt32(r4);
        L21:
            if (TLObject.hasFlag(this.flags, 512) == false) goto L24;
            this.extended_media = Vector.deserialize(r3, new TL_stars$TL_starsTransaction$$ExternalSyntheticLambda0(), r4);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starsTransaction, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 8, this.refund);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 16, this.pending);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 64, this.failed);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 1024, this.gift);
            this.flags = r04;
            r4.writeInt32(r04);
            r4.writeString(this.id);
            r4.writeInt64(this.amount.amount);
            r4.writeInt32(this.date);
            this.peer.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r4.writeString(this.title);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r4.writeString(this.description);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.photo.serializeToStream(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L15;
            r4.writeInt32(this.transaction_date);
            r4.writeString(this.transaction_url);
        L15:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L18;
            r4.writeByteArray(this.bot_payload);
        L18:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L21;
            r4.writeInt32(this.msg_id);
        L21:
            if (TLObject.hasFlag(this.flags, 512) == false) goto L24;
            Vector.serialize(r4, this.extended_media);
            return;
        }
    }

    public static class TL_starsTransaction_layer186 extends TL_starsTransaction {
        public static final int constructor = 1127934763;

        public TL_starsTransaction_layer186() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starsTransaction, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r5, boolean r6) {
            int r0 = r5.readInt32(r6);
            this.flags = r0;
            this.refund = TLObject.hasFlag(r0, 8);
            this.pending = TLObject.hasFlag(this.flags, 16);
            this.failed = TLObject.hasFlag(this.flags, 64);
            this.gift = TLObject.hasFlag(this.flags, 1024);
            this.reaction = TLObject.hasFlag(this.flags, 2048);
            this.subscription = TLObject.hasFlag(this.flags, 4096);
            this.id = r5.readString(r6);
            this.amount = StarsAmount.ofStars(r5.readInt64(r6));
            this.date = r5.readInt32(r6);
            this.peer = StarsTransactionPeer.TLdeserialize(r5, r5.readInt32(r6), r6);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.title = r5.readString(r6);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.description = r5.readString(r6);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.photo = TLRPC.WebDocument.TLdeserialize(r5, r5.readInt32(r6), r6);
        L12:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L15;
            this.transaction_date = r5.readInt32(r6);
            this.transaction_url = r5.readString(r6);
        L15:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L18;
            this.bot_payload = r5.readByteArray(r6);
        L18:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L21;
            this.msg_id = r5.readInt32(r6);
        L21:
            if (TLObject.hasFlag(this.flags, 512) == false) goto L24;
            this.extended_media = Vector.deserialize(r5, new TL_stars$TL_starsTransaction$$ExternalSyntheticLambda0(), r6);
        L24:
            if (TLObject.hasFlag(this.flags, 4096) == false) goto L27;
            this.subscription_period = r5.readInt32(r6);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starsTransaction, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 8, this.refund);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 16, this.pending);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 64, this.failed);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 1024, this.gift);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 2048, this.reaction);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 4096, this.subscription);
            this.flags = r06;
            r4.writeInt32(r06);
            r4.writeString(this.id);
            r4.writeInt64(this.amount.amount);
            r4.writeInt32(this.date);
            this.peer.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r4.writeString(this.title);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r4.writeString(this.description);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.photo.serializeToStream(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L15;
            r4.writeInt32(this.transaction_date);
            r4.writeString(this.transaction_url);
        L15:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L18;
            r4.writeByteArray(this.bot_payload);
        L18:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L21;
            r4.writeInt32(this.msg_id);
        L21:
            if (TLObject.hasFlag(this.flags, 512) == false) goto L24;
            Vector.serialize(r4, this.extended_media);
        L24:
            if (TLObject.hasFlag(this.flags, 4096) == false) goto L27;
            r4.writeInt32(this.subscription_period);
            return;
        }
    }

    public static class TL_starsTransaction_layer188 extends TL_starsTransaction {
        public static final int constructor = -294313259;

        public TL_starsTransaction_layer188() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starsTransaction, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r5, boolean r6) {
            int r0 = r5.readInt32(r6);
            this.flags = r0;
            this.refund = TLObject.hasFlag(r0, 8);
            this.pending = TLObject.hasFlag(this.flags, 16);
            this.failed = TLObject.hasFlag(this.flags, 64);
            this.gift = TLObject.hasFlag(this.flags, 1024);
            this.reaction = TLObject.hasFlag(this.flags, 2048);
            this.subscription = TLObject.hasFlag(this.flags, 4096);
            this.id = r5.readString(r6);
            this.amount = StarsAmount.ofStars(r5.readInt64(r6));
            this.date = r5.readInt32(r6);
            this.peer = StarsTransactionPeer.TLdeserialize(r5, r5.readInt32(r6), r6);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.title = r5.readString(r6);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.description = r5.readString(r6);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.photo = TLRPC.WebDocument.TLdeserialize(r5, r5.readInt32(r6), r6);
        L12:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L15;
            this.transaction_date = r5.readInt32(r6);
            this.transaction_url = r5.readString(r6);
        L15:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L18;
            this.bot_payload = r5.readByteArray(r6);
        L18:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L21;
            this.msg_id = r5.readInt32(r6);
        L21:
            if (TLObject.hasFlag(this.flags, 512) == false) goto L24;
            this.extended_media = Vector.deserialize(r5, new TL_stars$TL_starsTransaction$$ExternalSyntheticLambda0(), r6);
        L24:
            if (TLObject.hasFlag(this.flags, 4096) == false) goto L27;
            this.subscription_period = r5.readInt32(r6);
        L27:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L30;
            this.giveaway_post_id = r5.readInt32(r6);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starsTransaction, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 8, this.refund);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 16, this.pending);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 64, this.failed);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 1024, this.gift);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 2048, this.reaction);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 4096, this.subscription);
            this.flags = r06;
            r4.writeInt32(r06);
            r4.writeString(this.id);
            r4.writeInt64(this.amount.amount);
            r4.writeInt32(this.date);
            this.peer.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r4.writeString(this.title);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r4.writeString(this.description);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.photo.serializeToStream(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L15;
            r4.writeInt32(this.transaction_date);
            r4.writeString(this.transaction_url);
        L15:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L18;
            r4.writeByteArray(this.bot_payload);
        L18:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L21;
            r4.writeInt32(this.msg_id);
        L21:
            if (TLObject.hasFlag(this.flags, 512) == false) goto L24;
            Vector.serialize(r4, this.extended_media);
        L24:
            if (TLObject.hasFlag(this.flags, 4096) == false) goto L27;
            r4.writeInt32(this.subscription_period);
        L27:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L30;
            r4.writeInt32(this.giveaway_post_id);
            return;
        }
    }

    public static class TL_starsTransaction_layer191 extends TL_starsTransaction {
        public static final int constructor = 178185410;

        public TL_starsTransaction_layer191() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starsTransaction, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r5, boolean r6) {
            int r0 = r5.readInt32(r6);
            this.flags = r0;
            this.refund = TLObject.hasFlag(r0, 8);
            this.pending = TLObject.hasFlag(this.flags, 16);
            this.failed = TLObject.hasFlag(this.flags, 64);
            this.gift = TLObject.hasFlag(this.flags, 1024);
            this.reaction = TLObject.hasFlag(this.flags, 2048);
            this.subscription = TLObject.hasFlag(this.flags, 4096);
            this.id = r5.readString(r6);
            this.amount = StarsAmount.ofStars(r5.readInt64(r6));
            this.date = r5.readInt32(r6);
            this.peer = StarsTransactionPeer.TLdeserialize(r5, r5.readInt32(r6), r6);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.title = r5.readString(r6);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.description = r5.readString(r6);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.photo = TLRPC.WebDocument.TLdeserialize(r5, r5.readInt32(r6), r6);
        L12:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L15;
            this.transaction_date = r5.readInt32(r6);
            this.transaction_url = r5.readString(r6);
        L15:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L18;
            this.bot_payload = r5.readByteArray(r6);
        L18:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L21;
            this.msg_id = r5.readInt32(r6);
        L21:
            if (TLObject.hasFlag(this.flags, 512) == false) goto L24;
            this.extended_media = Vector.deserialize(r5, new TL_stars$TL_starsTransaction$$ExternalSyntheticLambda0(), r6);
        L24:
            if (TLObject.hasFlag(this.flags, 4096) == false) goto L27;
            this.subscription_period = r5.readInt32(r6);
        L27:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L30;
            this.giveaway_post_id = r5.readInt32(r6);
        L30:
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L33;
            this.stargift = StarGift.TLdeserialize(r5, r5.readInt32(r6), r6);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starsTransaction, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 8, this.refund);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 16, this.pending);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 64, this.failed);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 1024, this.gift);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 2048, this.reaction);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 4096, this.subscription);
            this.flags = r06;
            r4.writeInt32(r06);
            r4.writeString(this.id);
            r4.writeInt64(this.amount.amount);
            r4.writeInt32(this.date);
            this.peer.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r4.writeString(this.title);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r4.writeString(this.description);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.photo.serializeToStream(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L15;
            r4.writeInt32(this.transaction_date);
            r4.writeString(this.transaction_url);
        L15:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L18;
            r4.writeByteArray(this.bot_payload);
        L18:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L21;
            r4.writeInt32(this.msg_id);
        L21:
            if (TLObject.hasFlag(this.flags, 512) == false) goto L24;
            Vector.serialize(r4, this.extended_media);
        L24:
            if (TLObject.hasFlag(this.flags, 4096) == false) goto L27;
            r4.writeInt32(this.subscription_period);
        L27:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L30;
            r4.writeInt32(this.giveaway_post_id);
        L30:
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L33;
            this.stargift.serializeToStream(r4);
            return;
        }
    }

    public static class TL_starsTransaction_layer194 extends TL_starsTransaction {
        public static final int constructor = 903148150;

        public TL_starsTransaction_layer194() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starsTransaction, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r6, boolean r7) {
            int r0 = r6.readInt32(r7);
            this.flags = r0;
            this.refund = TLObject.hasFlag(r0, 8);
            this.pending = TLObject.hasFlag(this.flags, 16);
            this.failed = TLObject.hasFlag(this.flags, 64);
            this.gift = TLObject.hasFlag(this.flags, 1024);
            this.reaction = TLObject.hasFlag(this.flags, 2048);
            this.subscription = TLObject.hasFlag(this.flags, 4096);
            this.floodskip = TLObject.hasFlag(this.flags, 32768);
            this.id = r6.readString(r7);
            this.amount = StarsAmount.ofStars(r6.readInt64(r7));
            this.date = r6.readInt32(r7);
            this.peer = StarsTransactionPeer.TLdeserialize(r6, r6.readInt32(r7), r7);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.title = r6.readString(r7);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.description = r6.readString(r7);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.photo = TLRPC.WebDocument.TLdeserialize(r6, r6.readInt32(r7), r7);
        L12:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L15;
            this.transaction_date = r6.readInt32(r7);
            this.transaction_url = r6.readString(r7);
        L15:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L18;
            this.bot_payload = r6.readByteArray(r7);
        L18:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L21;
            this.msg_id = r6.readInt32(r7);
        L21:
            if (TLObject.hasFlag(this.flags, 512) == false) goto L24;
            this.extended_media = Vector.deserialize(r6, new TL_stars$TL_starsTransaction$$ExternalSyntheticLambda0(), r7);
        L24:
            if (TLObject.hasFlag(this.flags, 4096) == false) goto L27;
            this.subscription_period = r6.readInt32(r7);
        L27:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L30;
            this.giveaway_post_id = r6.readInt32(r7);
        L30:
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L33;
            this.stargift = StarGift.TLdeserialize(r6, r6.readInt32(r7), r7);
        L33:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L36;
            this.floodskip_number = r6.readInt32(r7);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starsTransaction, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r5) {
            r5.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 8, this.refund);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 16, this.pending);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 64, this.failed);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 1024, this.gift);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 2048, this.reaction);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 4096, this.subscription);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 32768, this.floodskip);
            this.flags = r07;
            r5.writeInt32(r07);
            r5.writeString(this.id);
            r5.writeInt64(this.amount.amount);
            r5.writeInt32(this.date);
            this.peer.serializeToStream(r5);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r5.writeString(this.title);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r5.writeString(this.description);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.photo.serializeToStream(r5);
        L12:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L15;
            r5.writeInt32(this.transaction_date);
            r5.writeString(this.transaction_url);
        L15:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L18;
            r5.writeByteArray(this.bot_payload);
        L18:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L21;
            r5.writeInt32(this.msg_id);
        L21:
            if (TLObject.hasFlag(this.flags, 512) == false) goto L24;
            Vector.serialize(r5, this.extended_media);
        L24:
            if (TLObject.hasFlag(this.flags, 4096) == false) goto L27;
            r5.writeInt32(this.subscription_period);
        L27:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L30;
            r5.writeInt32(this.giveaway_post_id);
        L30:
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L33;
            this.stargift.serializeToStream(r5);
        L33:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L36;
            r5.writeInt32(this.floodskip_number);
            return;
        }
    }

    public static class TL_starsTransaction_layer199 extends TL_starsTransaction {
        public static final int constructor = 1692387622;

        public TL_starsTransaction_layer199() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starsTransaction, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r5, boolean r6) {
            int r0 = r5.readInt32(r6);
            this.flags = r0;
            this.refund = TLObject.hasFlag(r0, 8);
            this.pending = TLObject.hasFlag(this.flags, 16);
            this.failed = TLObject.hasFlag(this.flags, 64);
            this.gift = TLObject.hasFlag(this.flags, 1024);
            this.reaction = TLObject.hasFlag(this.flags, 2048);
            this.subscription = TLObject.hasFlag(this.flags, 4096);
            this.floodskip = TLObject.hasFlag(this.flags, 32768);
            this.stargift_upgrade = TLObject.hasFlag(this.flags, 262144);
            this.paid_message = TLObject.hasFlag(this.flags, 524288);
            this.id = r5.readString(r6);
            this.amount = StarsAmount.TLdeserialize(r5, r5.readInt32(r6), r6);
            this.date = r5.readInt32(r6);
            this.peer = StarsTransactionPeer.TLdeserialize(r5, r5.readInt32(r6), r6);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.title = r5.readString(r6);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.description = r5.readString(r6);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.photo = TLRPC.WebDocument.TLdeserialize(r5, r5.readInt32(r6), r6);
        L12:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L15;
            this.transaction_date = r5.readInt32(r6);
            this.transaction_url = r5.readString(r6);
        L15:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L18;
            this.bot_payload = r5.readByteArray(r6);
        L18:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L21;
            this.msg_id = r5.readInt32(r6);
        L21:
            if (TLObject.hasFlag(this.flags, 512) == false) goto L24;
            this.extended_media = Vector.deserialize(r5, new TL_stars$TL_starsTransaction$$ExternalSyntheticLambda0(), r6);
        L24:
            if (TLObject.hasFlag(this.flags, 4096) == false) goto L27;
            this.subscription_period = r5.readInt32(r6);
        L27:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L30;
            this.giveaway_post_id = r5.readInt32(r6);
        L30:
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L33;
            this.stargift = StarGift.TLdeserialize(r5, r5.readInt32(r6), r6);
        L33:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L36;
            this.floodskip_number = r5.readInt32(r6);
        L36:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L39;
            this.starref_commission_permille = r5.readInt32(r6);
        L39:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L42;
            this.starref_peer = TLRPC.Peer.TLdeserialize(r5, r5.readInt32(r6), r6);
            this.starref_amount = StarsAmount.TLdeserialize(r5, r5.readInt32(r6), r6);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starsTransaction, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r6) {
            r6.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 8, this.refund);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 16, this.pending);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 64, this.failed);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 1024, this.gift);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 2048, this.reaction);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 4096, this.subscription);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 32768, this.floodskip);
            this.flags = r07;
            int r08 = TLObject.setFlag(r07, 262144, this.stargift_upgrade);
            this.flags = r08;
            int r09 = TLObject.setFlag(r08, 524288, this.paid_message);
            this.flags = r09;
            r6.writeInt32(r09);
            r6.writeString(this.id);
            this.amount.serializeToStream(r6);
            r6.writeInt32(this.date);
            this.peer.serializeToStream(r6);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r6.writeString(this.title);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r6.writeString(this.description);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.photo.serializeToStream(r6);
        L12:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L15;
            r6.writeInt32(this.transaction_date);
            r6.writeString(this.transaction_url);
        L15:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L18;
            r6.writeByteArray(this.bot_payload);
        L18:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L21;
            r6.writeInt32(this.msg_id);
        L21:
            if (TLObject.hasFlag(this.flags, 512) == false) goto L24;
            Vector.serialize(r6, this.extended_media);
        L24:
            if (TLObject.hasFlag(this.flags, 4096) == false) goto L27;
            r6.writeInt32(this.subscription_period);
        L27:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L30;
            r6.writeInt32(this.giveaway_post_id);
        L30:
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L33;
            this.stargift.serializeToStream(r6);
        L33:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L36;
            r6.writeInt32(this.floodskip_number);
        L36:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L39;
            r6.writeInt32(this.starref_commission_permille);
        L39:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L42;
            this.starref_peer.serializeToStream(r6);
            this.starref_amount.serializeToStream(r6);
            return;
        }
    }

    public static class TL_starsTransaction_layer199_2 extends TL_starsTransaction {
        public static final int constructor = -321582812;

        public TL_starsTransaction_layer199_2() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starsTransaction, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r6, boolean r7) {
            int r0 = r6.readInt32(r7);
            this.flags = r0;
            this.refund = TLObject.hasFlag(r0, 8);
            this.pending = TLObject.hasFlag(this.flags, 16);
            this.failed = TLObject.hasFlag(this.flags, 64);
            this.gift = TLObject.hasFlag(this.flags, 1024);
            this.reaction = TLObject.hasFlag(this.flags, 2048);
            this.subscription = TLObject.hasFlag(this.flags, 4096);
            this.floodskip = TLObject.hasFlag(this.flags, 32768);
            this.stargift_upgrade = TLObject.hasFlag(this.flags, 262144);
            this.paid_message = TLObject.hasFlag(this.flags, 524288);
            this.premium_gift = TLObject.hasFlag(this.flags, 1048576);
            this.id = r6.readString(r7);
            this.amount = StarsAmount.TLdeserialize(r6, r6.readInt32(r7), r7);
            this.date = r6.readInt32(r7);
            this.peer = StarsTransactionPeer.TLdeserialize(r6, r6.readInt32(r7), r7);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.title = r6.readString(r7);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.description = r6.readString(r7);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.photo = TLRPC.WebDocument.TLdeserialize(r6, r6.readInt32(r7), r7);
        L12:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L15;
            this.transaction_date = r6.readInt32(r7);
            this.transaction_url = r6.readString(r7);
        L15:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L18;
            this.bot_payload = r6.readByteArray(r7);
        L18:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L21;
            this.msg_id = r6.readInt32(r7);
        L21:
            if (TLObject.hasFlag(this.flags, 512) == false) goto L24;
            this.extended_media = Vector.deserialize(r6, new TL_stars$TL_starsTransaction$$ExternalSyntheticLambda0(), r7);
        L24:
            if (TLObject.hasFlag(this.flags, 4096) == false) goto L27;
            this.subscription_period = r6.readInt32(r7);
        L27:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L30;
            this.giveaway_post_id = r6.readInt32(r7);
        L30:
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L33;
            this.stargift = StarGift.TLdeserialize(r6, r6.readInt32(r7), r7);
        L33:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L36;
            this.floodskip_number = r6.readInt32(r7);
        L36:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L39;
            this.starref_commission_permille = r6.readInt32(r7);
        L39:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L42;
            this.starref_peer = TLRPC.Peer.TLdeserialize(r6, r6.readInt32(r7), r7);
            this.starref_amount = StarsAmount.TLdeserialize(r6, r6.readInt32(r7), r7);
        L42:
            if (TLObject.hasFlag(this.flags, 524288) == false) goto L45;
            this.paid_messages = r6.readInt32(r7);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starsTransaction, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r7) {
            r7.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 8, this.refund);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 16, this.pending);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 64, this.failed);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 1024, this.gift);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 2048, this.reaction);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 4096, this.subscription);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 32768, this.floodskip);
            this.flags = r07;
            int r08 = TLObject.setFlag(r07, 262144, this.stargift_upgrade);
            this.flags = r08;
            int r09 = TLObject.setFlag(r08, 524288, this.paid_message);
            this.flags = r09;
            int r010 = TLObject.setFlag(r09, 1048576, this.premium_gift);
            this.flags = r010;
            r7.writeInt32(r010);
            r7.writeString(this.id);
            this.amount.serializeToStream(r7);
            r7.writeInt32(this.date);
            this.peer.serializeToStream(r7);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r7.writeString(this.title);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r7.writeString(this.description);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.photo.serializeToStream(r7);
        L12:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L15;
            r7.writeInt32(this.transaction_date);
            r7.writeString(this.transaction_url);
        L15:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L18;
            r7.writeByteArray(this.bot_payload);
        L18:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L21;
            r7.writeInt32(this.msg_id);
        L21:
            if (TLObject.hasFlag(this.flags, 512) == false) goto L24;
            Vector.serialize(r7, this.extended_media);
        L24:
            if (TLObject.hasFlag(this.flags, 4096) == false) goto L27;
            r7.writeInt32(this.subscription_period);
        L27:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L30;
            r7.writeInt32(this.giveaway_post_id);
        L30:
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L33;
            this.stargift.serializeToStream(r7);
        L33:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L36;
            r7.writeInt32(this.floodskip_number);
        L36:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L39;
            r7.writeInt32(this.starref_commission_permille);
        L39:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L42;
            this.starref_peer.serializeToStream(r7);
            this.starref_amount.serializeToStream(r7);
        L42:
            if (TLObject.hasFlag(this.flags, 524288) == false) goto L45;
            r7.writeInt32(this.paid_messages);
            return;
        }
    }

    public static class TL_starsTransaction_layer205 extends TL_starsTransaction {
        public static final int constructor = -1549805238;

        public TL_starsTransaction_layer205() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starsTransaction, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r7, boolean r8) {
            int r0 = r7.readInt32(r8);
            this.flags = r0;
            this.refund = TLObject.hasFlag(r0, 8);
            this.pending = TLObject.hasFlag(this.flags, 16);
            this.failed = TLObject.hasFlag(this.flags, 64);
            this.gift = TLObject.hasFlag(this.flags, 1024);
            this.reaction = TLObject.hasFlag(this.flags, 2048);
            this.subscription = TLObject.hasFlag(this.flags, 4096);
            this.floodskip = TLObject.hasFlag(this.flags, 32768);
            this.stargift_upgrade = TLObject.hasFlag(this.flags, 262144);
            this.paid_message = TLObject.hasFlag(this.flags, 524288);
            this.premium_gift = TLObject.hasFlag(this.flags, 1048576);
            this.stargift_resale = TLObject.hasFlag(this.flags, 4194304);
            this.id = r7.readString(r8);
            this.amount = StarsAmount.TLdeserialize(r7, r7.readInt32(r8), r8);
            this.date = r7.readInt32(r8);
            this.peer = StarsTransactionPeer.TLdeserialize(r7, r7.readInt32(r8), r8);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.title = r7.readString(r8);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.description = r7.readString(r8);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.photo = TLRPC.WebDocument.TLdeserialize(r7, r7.readInt32(r8), r8);
        L12:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L15;
            this.transaction_date = r7.readInt32(r8);
            this.transaction_url = r7.readString(r8);
        L15:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L18;
            this.bot_payload = r7.readByteArray(r8);
        L18:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L21;
            this.msg_id = r7.readInt32(r8);
        L21:
            if (TLObject.hasFlag(this.flags, 512) == false) goto L24;
            this.extended_media = Vector.deserialize(r7, new TL_stars$TL_starsTransaction$$ExternalSyntheticLambda0(), r8);
        L24:
            if (TLObject.hasFlag(this.flags, 4096) == false) goto L27;
            this.subscription_period = r7.readInt32(r8);
        L27:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L30;
            this.giveaway_post_id = r7.readInt32(r8);
        L30:
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L33;
            this.stargift = StarGift.TLdeserialize(r7, r7.readInt32(r8), r8);
        L33:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L36;
            this.floodskip_number = r7.readInt32(r8);
        L36:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L39;
            this.starref_commission_permille = r7.readInt32(r8);
        L39:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L42;
            this.starref_peer = TLRPC.Peer.TLdeserialize(r7, r7.readInt32(r8), r8);
            this.starref_amount = StarsAmount.TLdeserialize(r7, r7.readInt32(r8), r8);
        L42:
            if (TLObject.hasFlag(this.flags, 524288) == false) goto L45;
            this.paid_messages = r7.readInt32(r8);
        L45:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L48;
            this.premium_gift_months = r7.readInt32(r8);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stars.TL_starsTransaction, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r8) {
            r8.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 8, this.refund);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 16, this.pending);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 64, this.failed);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 1024, this.gift);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 2048, this.reaction);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 4096, this.subscription);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 32768, this.floodskip);
            this.flags = r07;
            int r08 = TLObject.setFlag(r07, 262144, this.stargift_upgrade);
            this.flags = r08;
            int r09 = TLObject.setFlag(r08, 524288, this.paid_message);
            this.flags = r09;
            int r010 = TLObject.setFlag(r09, 1048576, this.premium_gift);
            this.flags = r010;
            int r011 = TLObject.setFlag(r010, 4194304, this.stargift_resale);
            this.flags = r011;
            r8.writeInt32(r011);
            r8.writeString(this.id);
            this.amount.serializeToStream(r8);
            r8.writeInt32(this.date);
            this.peer.serializeToStream(r8);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r8.writeString(this.title);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r8.writeString(this.description);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.photo.serializeToStream(r8);
        L12:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L15;
            r8.writeInt32(this.transaction_date);
            r8.writeString(this.transaction_url);
        L15:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L18;
            r8.writeByteArray(this.bot_payload);
        L18:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L21;
            r8.writeInt32(this.msg_id);
        L21:
            if (TLObject.hasFlag(this.flags, 512) == false) goto L24;
            Vector.serialize(r8, this.extended_media);
        L24:
            if (TLObject.hasFlag(this.flags, 4096) == false) goto L27;
            r8.writeInt32(this.subscription_period);
        L27:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L30;
            r8.writeInt32(this.giveaway_post_id);
        L30:
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L33;
            this.stargift.serializeToStream(r8);
        L33:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L36;
            r8.writeInt32(this.floodskip_number);
        L36:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L39;
            r8.writeInt32(this.starref_commission_permille);
        L39:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L42;
            this.starref_peer.serializeToStream(r8);
            this.starref_amount.serializeToStream(r8);
        L42:
            if (TLObject.hasFlag(this.flags, 524288) == false) goto L45;
            r8.writeInt32(this.paid_messages);
        L45:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L48;
            r8.writeInt32(this.premium_gift_months);
            return;
        }
    }

    /* loaded from: classes.dex */
    public static class Tl_starsRating extends TLObject {
        public static final int constructor = 453922567;
        public long current_level_stars;
        public int flags;
        public int level;
        public long next_level_stars;
        public long stars;

        public Tl_starsRating() {
        }

        public static Tl_starsRating TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (453922567 == r3) goto L5;
            Tl_starsRating r0 = null;
        L7:
            return (Tl_starsRating) TLObject.TLdeserialize(Tl_starsRating.class, r0, r2, r3, r4);
        L5:
            r0 = new Tl_starsRating();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.level = r3.readInt32(r4);
            this.current_level_stars = r3.readInt64(r4);
            this.stars = r3.readInt64(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.next_level_stars = r3.readInt64(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeInt32(this.level);
            r3.writeInt64(this.current_level_stars);
            r3.writeInt64(this.stars);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeInt64(this.next_level_stars);
            return;
        }
    }

    public static class UniqueStarGiftValueInfo extends TLObject {
        public static final int constructor = 1362093126;
        public long average_price;
        public String currency;
        public int flags;
        public long floor_price;
        public int fragment_listed_count;
        public String fragment_listed_url;
        public int initial_sale_date;
        public long initial_sale_price;
        public long initial_sale_stars;
        public int last_sale_date;
        public boolean last_sale_on_fragment;
        public long last_sale_price;
        public int listed_count;
        public long value;
        public boolean value_is_average;

        public UniqueStarGiftValueInfo() {
        }

        public static UniqueStarGiftValueInfo TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != 1362093126) goto L5;
            UniqueStarGiftValueInfo r0 = new UniqueStarGiftValueInfo();
        L7:
            return (UniqueStarGiftValueInfo) TLObject.TLdeserialize(UniqueStarGiftValueInfo.class, r0, r2, r3, r4);
        L5:
            r0 = null;
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.last_sale_on_fragment = TLObject.hasFlag(r0, 2);
            this.value_is_average = TLObject.hasFlag(this.flags, 64);
            this.currency = r3.readString(r4);
            this.value = r3.readInt64(r4);
            this.initial_sale_date = r3.readInt32(r4);
            this.initial_sale_stars = r3.readInt64(r4);
            this.initial_sale_price = r3.readInt64(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.last_sale_date = r3.readInt32(r4);
            this.last_sale_price = r3.readInt64(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.floor_price = r3.readInt64(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L12;
            this.average_price = r3.readInt64(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L15;
            this.listed_count = r3.readInt32(r4);
        L15:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L18;
            this.fragment_listed_count = r3.readInt32(r4);
            this.fragment_listed_url = r3.readString(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.last_sale_on_fragment);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 64, this.value_is_average);
            this.flags = r02;
            r4.writeInt32(r02);
            r4.writeString(this.currency);
            r4.writeInt64(this.value);
            r4.writeInt32(this.initial_sale_date);
            r4.writeInt64(this.initial_sale_stars);
            r4.writeInt64(this.initial_sale_price);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r4.writeInt32(this.last_sale_date);
            r4.writeInt64(this.last_sale_price);
        L6:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            r4.writeInt64(this.floor_price);
        L9:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L12;
            r4.writeInt64(this.average_price);
        L12:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L15;
            r4.writeInt32(this.listed_count);
        L15:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L18;
            r4.writeInt32(this.fragment_listed_count);
            r4.writeString(this.fragment_listed_url);
            return;
        }
    }

    public static class checkCanSendGift extends TLObject {
        public static final int constructor = -1060835895;
        public long gift_id;

        public checkCanSendGift() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return CheckCanSendGiftResult.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.gift_id);
        }
    }

    public static class checkCanSendGiftResultFail extends CheckCanSendGiftResult {
        public static final int constructor = -706379148;
        public TLRPC.TL_textWithEntities reason;

        public checkCanSendGiftResultFail() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.reason = TLRPC.TL_textWithEntities.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.reason.serializeToStream(r2);
        }
    }

    public static class checkCanSendGiftResultOk extends CheckCanSendGiftResult {
        public static final int constructor = 927967149;

        public checkCanSendGiftResultOk() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class convertStarGift extends TLObject {
        public static final int constructor = 1958676331;
        public InputSavedStarGift stargift;

        public convertStarGift() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.stargift.serializeToStream(r2);
        }
    }

    public static class craftStarGift extends TLMethod<TLRPC.Updates> {
        public static final int constructor = -1325832113;
        public ArrayList<InputSavedStarGift> stargift;

        public craftStarGift() {
            this.stargift = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public TLRPC.Updates deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.stargift);
        }
    }

    public static class createStarGiftCollection extends TLObject {
        public static final int constructor = 524947079;
        public TLRPC.InputPeer peer;
        public ArrayList<InputSavedStarGift> stargift;
        public String title;

        public createStarGiftCollection() {
            this.stargift = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_starGiftCollection.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            r2.writeString(this.title);
            Vector.serialize(r2, this.stargift);
        }
    }

    public static class deleteStarGiftCollection extends TLObject {
        public static final int constructor = -1386854168;
        public int collection_id;
        public TLRPC.InputPeer peer;

        public deleteStarGiftCollection() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            r2.writeInt32(this.collection_id);
        }
    }

    public static class getCraftStarGifts extends TLMethod<TL_payments_savedStarGifts> {
        public static final int constructor = -49947392;
        public long gift_id;
        public int limit;
        public String offset;

        public getCraftStarGifts() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public TL_payments_savedStarGifts deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return TL_payments_savedStarGifts.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.gift_id);
            r3.writeString(this.offset);
            r3.writeInt32(this.limit);
        }
    }

    public static class getResaleStarGifts extends TLObject {
        public static final int constructor = 2053087798;
        public ArrayList<StarGiftAttributeId> attributes;
        public long attributes_hash;
        public int flags;
        public boolean for_craft;
        public long gift_id;
        public int limit;
        public String offset;
        public boolean sort_by_num;
        public boolean sort_by_price;
        public boolean stars_only;

        public getResaleStarGifts() {
            this.attributes = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return resaleStarGifts.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.sort_by_price);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 4, this.sort_by_num);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 16, this.for_craft);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 32, this.stars_only);
            this.flags = r04;
            r4.writeInt32(r04);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            r4.writeInt64(this.attributes_hash);
        L5:
            r4.writeInt64(this.gift_id);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L8;
            Vector.serialize(r4, this.attributes);
        L8:
            r4.writeString(this.offset);
            r4.writeInt32(this.limit);
        }
    }

    public static class getSavedStarGift extends TLObject {
        public static final int constructor = -1269456634;
        public ArrayList<InputSavedStarGift> stargift;

        public getSavedStarGift() {
            this.stargift = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_payments_savedStarGifts.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.stargift);
        }
    }

    public static class getSavedStarGifts extends TLMethod<TL_payments_savedStarGifts> {
        public static final int constructor = -1558583959;
        public int collection_id;
        public boolean exclude_saved;
        public boolean exclude_unique;
        public boolean exclude_unlimited;
        public boolean exclude_unsaved;
        public boolean exclude_unupgradable;
        public boolean exclude_upgradable;
        public int flags;
        public int limit;
        public String offset;
        public TLRPC.InputPeer peer;
        public boolean peer_color_available;
        public boolean sort_by_value;

        public getSavedStarGifts() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public TL_payments_savedStarGifts deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return TL_payments_savedStarGifts.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.exclude_unsaved);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.exclude_saved);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 4, this.exclude_unlimited);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 16, this.exclude_unique);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 32, this.sort_by_value);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 128, this.exclude_upgradable);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 256, this.exclude_unupgradable);
            this.flags = r07;
            int r08 = TLObject.setFlag(r07, 512, this.peer_color_available);
            this.flags = r08;
            r4.writeInt32(r08);
            this.peer.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 64) == false) goto L5;
            r4.writeInt32(this.collection_id);
        L5:
            r4.writeString(this.offset);
            r4.writeInt32(this.limit);
        }
    }

    public static class getStarGiftCollections extends TLObject {
        public static final int constructor = -1743023651;
        public long hash;
        public TLRPC.InputPeer peer;

        public getStarGiftCollections() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return StarGiftCollections.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            this.peer.serializeToStream(r3);
            r3.writeInt64(this.hash);
        }
    }

    public static class getStarGiftUpgradeAttributes extends TLMethod<starGiftUpgradeAttributes> {
        public static final int constructor = 1828948824;
        public long gift_id;

        public getStarGiftUpgradeAttributes() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public starGiftUpgradeAttributes deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return starGiftUpgradeAttributes.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.gift_id);
        }
    }

    public static class getStarGiftUpgradePreview extends TLObject {
        public static final int constructor = -1667580751;
        public long gift_id;

        public getStarGiftUpgradePreview() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return starGiftUpgradePreview.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.gift_id);
        }
    }

    public static final class getStarGiftWithdrawalUrl extends TLObject {
        public static final int constructor = -798059608;
        public TLRPC.InputCheckPasswordSRP password;
        public InputSavedStarGift stargift;

        public getStarGiftWithdrawalUrl() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return starGiftWithdrawalUrl.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.stargift.serializeToStream(r2);
            this.password.serializeToStream(r2);
        }
    }

    public static class getStarGifts extends TLObject {
        public static final int constructor = -1000983152;
        public int hash;

        public getStarGifts() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return StarGifts.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.hash);
        }
    }

    public static final class getUniqueStarGift extends TLObject {
        public static final int constructor = -1583919758;
        public String slug;

        public getUniqueStarGift() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_payments_uniqueStarGift.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.slug);
        }
    }

    public static class getUniqueStarGiftValueInfo extends TLObject {
        public static final int constructor = 1130737515;
        public String slug;

        public getUniqueStarGiftValueInfo() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return UniqueStarGiftValueInfo.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.slug);
        }
    }

    public static class paidReactionPrivacyAnonymous extends PaidReactionPrivacy {
        public static final int constructor = 520887001;

        public paidReactionPrivacyAnonymous() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class paidReactionPrivacyDefault extends PaidReactionPrivacy {
        public static final int constructor = 543872158;

        public paidReactionPrivacyDefault() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class paidReactionPrivacyPeer extends PaidReactionPrivacy {
        public static final int constructor = -596837136;

        public paidReactionPrivacyPeer() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.peer = TLRPC.InputPeer.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
        }
    }

    public static class reorderStarGiftCollections extends TLObject {
        public static final int constructor = -1020594996;
        public ArrayList<Integer> order;
        public TLRPC.InputPeer peer;

        public reorderStarGiftCollections() {
            this.order = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            Vector.serializeInt(r2, this.order);
        }
    }

    public static class resaleStarGifts extends TLObject {
        public static final int constructor = -1803939105;
        public ArrayList<StarGiftAttribute> attributes;
        public long attributes_hash;
        public ArrayList<TLRPC.Chat> chats;
        public int count;
        public ArrayList<starGiftAttributeCounter> counters;
        public int flags;
        public ArrayList<StarGift> gifts;
        public String next_offset;
        public ArrayList<TLRPC.User> users;

        public resaleStarGifts() {
            this.gifts = new ArrayList();
            this.attributes = new ArrayList();
            this.chats = new ArrayList();
            this.counters = new ArrayList();
            this.users = new ArrayList();
        }

        public static resaleStarGifts TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-1803939105) == r3) goto L5;
            resaleStarGifts r0 = null;
        L7:
            return (resaleStarGifts) TLObject.TLdeserialize(resaleStarGifts.class, r0, r2, r3, r4);
        L5:
            r0 = new resaleStarGifts();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.count = r3.readInt32(r4);
            this.gifts = Vector.deserialize(r3, new TL_stars$TL_starGifts$$ExternalSyntheticLambda0(), r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.next_offset = r3.readString(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L8;
            this.attributes = Vector.deserialize(r3, new TL_stars$TL_starGiftUnique$$ExternalSyntheticLambda0(), r4);
            this.attributes_hash = r3.readInt64(r4);
        L8:
            this.chats = Vector.deserialize(r3, new TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1(), r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L11;
            this.counters = Vector.deserialize(r3, new TL_stars$resaleStarGifts$$ExternalSyntheticLambda0(), r4);
        L11:
            this.users = Vector.deserialize(r3, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeInt32(this.count);
            Vector.serialize(r3, this.gifts);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeString(this.next_offset);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L8;
            Vector.serialize(r3, this.attributes);
            r3.writeInt64(this.attributes_hash);
        L8:
            Vector.serialize(r3, this.chats);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L11;
            Vector.serialize(r3, this.counters);
        L11:
            Vector.serialize(r3, this.users);
        }
    }

    public static class saveStarGift extends TLObject {
        public static final int constructor = 707422588;
        public int flags;
        public InputSavedStarGift stargift;
        public boolean unsave;

        public saveStarGift() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.unsave);
            this.flags = r0;
            r4.writeInt32(r0);
            this.stargift.serializeToStream(r4);
        }
    }

    public static class starGiftAttributeBackdrop extends StarGiftAttribute {
        public static final int constructor = -1624963868;
        public int backdrop_id;
        public int center_color;
        public int edge_color;
        public int pattern_color;
        public int text_color;

        public starGiftAttributeBackdrop() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.name = r2.readString(r3);
            this.backdrop_id = r2.readInt32(r3);
            this.center_color = r2.readInt32(r3);
            this.edge_color = r2.readInt32(r3);
            this.pattern_color = r2.readInt32(r3);
            this.text_color = r2.readInt32(r3);
            this.rarity = StarGiftAttributeRarity.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.name);
            r2.writeInt32(this.backdrop_id);
            r2.writeInt32(this.center_color);
            r2.writeInt32(this.edge_color);
            r2.writeInt32(this.pattern_color);
            r2.writeInt32(this.text_color);
            this.rarity.serializeToStream(r2);
        }
    }

    public static class starGiftAttributeBackdrop_layer202 extends starGiftAttributeBackdrop {
        public static final int constructor = -1809377438;

        public starGiftAttributeBackdrop_layer202() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.starGiftAttributeBackdrop, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.name = r2.readString(r3);
            this.center_color = r2.readInt32(r3);
            this.edge_color = r2.readInt32(r3);
            this.pattern_color = r2.readInt32(r3);
            this.text_color = r2.readInt32(r3);
            TL_starGiftAttributeRarity r0 = new TL_starGiftAttributeRarity();
            this.rarity = r0;
            r0.permille = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.tl.TL_stars.starGiftAttributeBackdrop, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.name);
            r2.writeInt32(this.center_color);
            r2.writeInt32(this.edge_color);
            r2.writeInt32(this.pattern_color);
            r2.writeInt32(this.text_color);
            StarGiftAttributeRarity r1 = this.rarity;
            if ((r1 instanceof TL_starGiftAttributeRarity) == false) goto L6;
            r2.writeInt32(((TL_starGiftAttributeRarity) r1).permille);
            return;
        L6:
            r2.writeInt32(0);
        }
    }

    public static class starGiftAttributeBackdrop_layer221 extends starGiftAttributeBackdrop {
        public static final int constructor = -650279524;

        public starGiftAttributeBackdrop_layer221() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.starGiftAttributeBackdrop, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.name = r2.readString(r3);
            this.backdrop_id = r2.readInt32(r3);
            this.center_color = r2.readInt32(r3);
            this.edge_color = r2.readInt32(r3);
            this.pattern_color = r2.readInt32(r3);
            this.text_color = r2.readInt32(r3);
            TL_starGiftAttributeRarity r0 = new TL_starGiftAttributeRarity();
            this.rarity = r0;
            r0.permille = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.tl.TL_stars.starGiftAttributeBackdrop, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.name);
            r2.writeInt32(this.backdrop_id);
            r2.writeInt32(this.center_color);
            r2.writeInt32(this.edge_color);
            r2.writeInt32(this.pattern_color);
            r2.writeInt32(this.text_color);
            StarGiftAttributeRarity r1 = this.rarity;
            if ((r1 instanceof TL_starGiftAttributeRarity) == false) goto L6;
            r2.writeInt32(((TL_starGiftAttributeRarity) r1).permille);
            return;
        L6:
            r2.writeInt32(0);
        }
    }

    public static class starGiftAttributeCounter extends TLObject {
        public static int constructor = 783398488;
        public StarGiftAttributeId attribute;
        public int count;

        static {
        }

        public starGiftAttributeCounter() {
        }

        public static starGiftAttributeCounter TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (constructor == r3) goto L5;
            starGiftAttributeCounter r0 = null;
        L7:
            return (starGiftAttributeCounter) TLObject.TLdeserialize(starGiftAttributeCounter.class, r0, r2, r3, r4);
        L5:
            r0 = new starGiftAttributeCounter();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.attribute = StarGiftAttributeId.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.count = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.attribute.serializeToStream(r2);
            r2.writeInt32(this.count);
        }
    }

    public static class starGiftAttributeIdBackdrop extends StarGiftAttributeId {
        public static final int constructor = 520210263;

        public starGiftAttributeIdBackdrop() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.backdrop_id = r1.readInt32(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.backdrop_id);
        }
    }

    public static class starGiftAttributeIdModel extends StarGiftAttributeId {
        public static final int constructor = 1219145276;

        public starGiftAttributeIdModel() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.document_id = r1.readInt64(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.document_id);
        }
    }

    public static class starGiftAttributeIdPattern extends StarGiftAttributeId {
        public static final int constructor = 1242965043;

        public starGiftAttributeIdPattern() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.document_id = r1.readInt64(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.document_id);
        }
    }

    public static class starGiftAttributeModel extends StarGiftAttribute {
        public static final int constructor = 1448235490;
        public TLRPC.Document document;

        public starGiftAttributeModel() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r4, boolean r5) {
            this.flags = TLObject.setFlag(this.flags, 1, this.crafted);
            this.flags = r4.readInt32(r5);
            this.name = r4.readString(r5);
            this.document = TLRPC.Document.TLdeserialize(r4, r4.readInt32(r5), r5);
            this.rarity = StarGiftAttributeRarity.TLdeserialize(r4, r4.readInt32(r5), r5);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            this.crafted = TLObject.hasFlag(this.flags, 1);
            r3.writeString(this.name);
            this.document.serializeToStream(r3);
            this.rarity.serializeToStream(r3);
        }
    }

    public static class starGiftAttributeModel_layer221 extends starGiftAttributeModel {
        public static final int constructor = 970559507;

        public starGiftAttributeModel_layer221() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.starGiftAttributeModel, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.name = r2.readString(r3);
            this.document = TLRPC.Document.TLdeserialize(r2, r2.readInt32(r3), r3);
            TL_starGiftAttributeRarity r0 = new TL_starGiftAttributeRarity();
            this.rarity = r0;
            r0.permille = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.tl.TL_stars.starGiftAttributeModel, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.name);
            this.document.serializeToStream(r2);
            StarGiftAttributeRarity r1 = this.rarity;
            if ((r1 instanceof TL_starGiftAttributeRarity) == false) goto L6;
            r2.writeInt32(((TL_starGiftAttributeRarity) r1).permille);
            return;
        L6:
            r2.writeInt32(0);
        }
    }

    public static class starGiftAttributeOriginalDetails extends StarGiftAttribute {
        public static final int constructor = -524291476;
        public int date;
        public int flags;
        public TLRPC.TL_textWithEntities message;
        public TLRPC.Peer recipient_id;
        public TLRPC.Peer sender_id;

        public starGiftAttributeOriginalDetails() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            if (TLObject.hasFlag(r0, 1) == false) goto L5;
            this.sender_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.recipient_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.date = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.message = TLRPC.TL_textWithEntities.TLdeserialize(r3, r3.readInt32(r4), r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            this.sender_id.serializeToStream(r3);
        L5:
            this.recipient_id.serializeToStream(r3);
            r3.writeInt32(this.date);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.message.serializeToStream(r3);
            return;
        }
    }

    public static class starGiftAttributeOriginalDetails_layer197 extends starGiftAttributeOriginalDetails {
        public static final int constructor = -1070837941;

        public starGiftAttributeOriginalDetails_layer197() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.starGiftAttributeOriginalDetails, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r4, boolean r5) {
            int r0 = r4.readInt32(r5);
            this.flags = r0;
            if (TLObject.hasFlag(r0, 1) == false) goto L5;
            TLRPC.TL_peerUser r02 = new TLRPC.TL_peerUser();
            this.sender_id = r02;
            r02.user_id = r4.readInt64(r5);
        L5:
            TLRPC.TL_peerUser r03 = new TLRPC.TL_peerUser();
            this.recipient_id = r03;
            r03.user_id = r4.readInt64(r5);
            this.date = r4.readInt32(r5);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.message = TLRPC.TL_textWithEntities.TLdeserialize(r4, r4.readInt32(r5), r5);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stars.starGiftAttributeOriginalDetails, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            r3.writeInt64(this.sender_id.user_id);
        L5:
            r3.writeInt64(this.recipient_id.user_id);
            r3.writeInt32(this.date);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.message.serializeToStream(r3);
            return;
        }
    }

    public static class starGiftAttributePattern extends StarGiftAttribute {
        public static final int constructor = 1315997162;
        public TLRPC.Document document;

        public starGiftAttributePattern() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.name = r2.readString(r3);
            this.document = TLRPC.Document.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.rarity = StarGiftAttributeRarity.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.name);
            this.document.serializeToStream(r2);
            this.rarity.serializeToStream(r2);
        }
    }

    public static class starGiftAttributePattern_layer221 extends starGiftAttributePattern {
        public static final int constructor = 330104601;

        public starGiftAttributePattern_layer221() {
        }

        @Override // org.telegram.tgnet.tl.TL_stars.starGiftAttributePattern, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.name = r2.readString(r3);
            this.document = TLRPC.Document.TLdeserialize(r2, r2.readInt32(r3), r3);
            TL_starGiftAttributeRarity r0 = new TL_starGiftAttributeRarity();
            this.rarity = r0;
            r0.permille = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.tl.TL_stars.starGiftAttributePattern, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.name);
            this.document.serializeToStream(r2);
            StarGiftAttributeRarity r1 = this.rarity;
            if ((r1 instanceof TL_starGiftAttributeRarity) == false) goto L6;
            r2.writeInt32(((TL_starGiftAttributeRarity) r1).permille);
            return;
        L6:
            r2.writeInt32(0);
        }
    }

    public static class starGiftUpgradeAttributes extends TLObject {
        public static final int constructor = 1187439471;
        public ArrayList<StarGiftAttribute> attributes;

        public starGiftUpgradeAttributes() {
            this.attributes = new ArrayList();
        }

        public static starGiftUpgradeAttributes TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (1187439471 == r3) goto L5;
            starGiftUpgradeAttributes r0 = null;
        L7:
            return (starGiftUpgradeAttributes) TLObject.TLdeserialize(starGiftUpgradeAttributes.class, r0, r2, r3, r4);
        L5:
            r0 = new starGiftUpgradeAttributes();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.attributes = Vector.deserialize(r2, new TL_stars$TL_starGiftUnique$$ExternalSyntheticLambda0(), false);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.attributes);
        }
    }

    public static class starGiftUpgradePreview extends TLObject {
        public static final int constructor = 1038213101;
        public ArrayList<StarGiftUpgradePrice> next_prices;
        public ArrayList<StarGiftUpgradePrice> prices;
        public ArrayList<StarGiftAttribute> sample_attributes;

        public starGiftUpgradePreview() {
            this.sample_attributes = new ArrayList();
            this.prices = new ArrayList();
            this.next_prices = new ArrayList();
        }

        public static starGiftUpgradePreview TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (1038213101 == r3) goto L5;
            starGiftUpgradePreview r0 = null;
        L7:
            return (starGiftUpgradePreview) TLObject.TLdeserialize(starGiftUpgradePreview.class, r0, r2, r3, r4);
        L5:
            r0 = new starGiftUpgradePreview();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.sample_attributes = Vector.deserialize(r2, new TL_stars$TL_starGiftUnique$$ExternalSyntheticLambda0(), r3);
            this.prices = Vector.deserialize(r2, new TL_stars$starGiftUpgradePreview$$ExternalSyntheticLambda0(), r3);
            this.next_prices = Vector.deserialize(r2, new TL_stars$starGiftUpgradePreview$$ExternalSyntheticLambda0(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.sample_attributes);
            Vector.serialize(r2, this.prices);
            Vector.serialize(r2, this.next_prices);
        }
    }

    public static final class starGiftWithdrawalUrl extends TLObject {
        public static final int constructor = -2069218660;
        public String url;

        public starGiftWithdrawalUrl() {
        }

        public static starGiftWithdrawalUrl TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-2069218660) == r3) goto L5;
            starGiftWithdrawalUrl r0 = null;
        L7:
            return (starGiftWithdrawalUrl) TLObject.TLdeserialize(starGiftWithdrawalUrl.class, r0, r2, r3, r4);
        L5:
            r0 = new starGiftWithdrawalUrl();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.url = r1.readString(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.url);
        }
    }

    public static final class toggleChatStarGiftNotifications extends TLObject {
        public static final int constructor = 1626009505;
        public boolean enabled;
        public int flags;
        public TLRPC.InputPeer peer;

        public toggleChatStarGiftNotifications() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.enabled);
            this.flags = r0;
            r4.writeInt32(r0);
            this.peer.serializeToStream(r4);
        }
    }

    public static class toggleStarGiftsPinnedToTop extends TLObject {
        public static final int constructor = 353626032;
        public TLRPC.InputPeer peer;
        public ArrayList<InputSavedStarGift> stargift;

        public toggleStarGiftsPinnedToTop() {
            this.stargift = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            Vector.serialize(r2, this.stargift);
        }
    }

    public static class transferStarGift extends TLObject {
        public static final int constructor = 2132285290;
        public InputSavedStarGift stargift;
        public TLRPC.InputPeer to_id;

        public transferStarGift() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.stargift.serializeToStream(r2);
            this.to_id.serializeToStream(r2);
        }
    }

    public static class updatePaidMessagesPrice extends TLObject {
        public static final int constructor = 1259483771;
        public TLRPC.InputChannel channel;
        public int flags;
        public long send_paid_messages_stars;
        public boolean suggestions_allowed;

        public updatePaidMessagesPrice() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.suggestions_allowed);
            this.flags = r0;
            r4.writeInt32(r0);
            this.channel.serializeToStream(r4);
            r4.writeInt64(this.send_paid_messages_stars);
        }
    }

    public static class updateStarGiftCollection extends TLObject {
        public static final int constructor = 1339932391;
        public ArrayList<InputSavedStarGift> add_stargift;
        public int collection_id;
        public ArrayList<InputSavedStarGift> delete_stargift;
        public int flags;
        public ArrayList<InputSavedStarGift> order;
        public TLRPC.InputPeer peer;
        public String title;

        public updateStarGiftCollection() {
            this.delete_stargift = new ArrayList();
            this.add_stargift = new ArrayList();
            this.order = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_starGiftCollection.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            this.peer.serializeToStream(r3);
            r3.writeInt32(this.collection_id);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeString(this.title);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            Vector.serialize(r3, this.delete_stargift);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            Vector.serialize(r3, this.add_stargift);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            Vector.serialize(r3, this.order);
            return;
        }
    }

    public static class updateStarGiftPrice extends TLMethod<TLRPC.Updates> {
        public static final int constructor = -306287413;
        public StarsAmount resell_amount;
        public InputSavedStarGift stargift;

        public updateStarGiftPrice() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public TLRPC.Updates deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.stargift.serializeToStream(r2);
            this.resell_amount.serializeToStream(r2);
        }
    }

    public static class upgradeStarGift extends TLObject {
        public static final int constructor = -1361648395;
        public int flags;
        public boolean keep_original_details;
        public InputSavedStarGift stargift;

        public upgradeStarGift() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.keep_original_details);
            this.flags = r0;
            r4.writeInt32(r0);
            this.stargift.serializeToStream(r4);
        }
    }

    public TL_stars() {
    }
}
