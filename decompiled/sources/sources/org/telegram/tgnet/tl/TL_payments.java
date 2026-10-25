package org.telegram.tgnet.tl;

import java.util.ArrayList;
import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.TLMethod;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1;
import org.telegram.tgnet.TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_stars;

/* loaded from: classes6.dex */
public class TL_payments {

    public static abstract class StarGiftActiveAuctions extends TLObject {
        public StarGiftActiveAuctions() {
        }

        public static StarGiftActiveAuctions TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            return (StarGiftActiveAuctions) TLObject.TLdeserialize(StarGiftActiveAuctions.class, fromConstructor(r3), r2, r3, r4);
        }

        private static StarGiftActiveAuctions fromConstructor(int r1) {
            if (r1 == (-1359565892)) goto L11;
            if (r1 == (-617358640)) goto L9;
            return null;
        L9:
            return new TL_starGiftActiveAuctionsNotModified();
        L11:
            return new TL_starGiftActiveAuctions();
        }
    }

    public static class TL_StarGiftAuctionAcquiredGifts extends TLObject {
        public static final int constructor = 2103169520;
        public ArrayList<TLRPC.Chat> chats;
        public ArrayList<TL_stars.TL_StarGiftAuctionAcquiredGift> gifts;
        public ArrayList<TLRPC.User> users;

        public TL_StarGiftAuctionAcquiredGifts() {
            this.gifts = new ArrayList();
            this.users = new ArrayList();
            this.chats = new ArrayList();
        }

        public static TL_StarGiftAuctionAcquiredGifts TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (2103169520 == r3) goto L5;
            TL_StarGiftAuctionAcquiredGifts r0 = null;
        L7:
            return (TL_StarGiftAuctionAcquiredGifts) TLObject.TLdeserialize(TL_StarGiftAuctionAcquiredGifts.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_StarGiftAuctionAcquiredGifts();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.gifts = Vector.deserialize(r2, new TL_payments$TL_StarGiftAuctionAcquiredGifts$$ExternalSyntheticLambda0(), r3);
            this.users = Vector.deserialize(r2, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r3);
            this.chats = Vector.deserialize(r2, new TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.gifts);
            Vector.serialize(r2, this.users);
            Vector.serialize(r2, this.chats);
        }
    }

    public static class TL_StarGiftAuctionState extends TLObject {
        public static final int constructor = 1798960364;
        public ArrayList<TLRPC.Chat> chats;
        public TL_stars.StarGift gift;
        public TL_stars.StarGiftAuctionState state;
        public int timeout;
        public TL_stars.TL_StarGiftAuctionUserState user_state;
        public ArrayList<TLRPC.User> users;

        public TL_StarGiftAuctionState() {
        }

        public static TL_StarGiftAuctionState TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (1798960364 == r3) goto L5;
            TL_StarGiftAuctionState r0 = null;
        L7:
            return (TL_StarGiftAuctionState) TLObject.TLdeserialize(TL_StarGiftAuctionState.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_StarGiftAuctionState();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.gift = TL_stars.StarGift.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.state = TL_stars.StarGiftAuctionState.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.user_state = TL_stars.TL_StarGiftAuctionUserState.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.timeout = r2.readInt32(r3);
            this.users = Vector.deserialize(r2, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r3);
            this.chats = Vector.deserialize(r2, new TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.gift.serializeToStream(r2);
            this.state.serializeToStream(r2);
            this.user_state.serializeToStream(r2);
            r2.writeInt32(this.timeout);
            Vector.serialize(r2, this.users);
            Vector.serialize(r2, this.chats);
        }
    }

    public static class TL_getStarGiftActiveAuctions extends TLMethod<StarGiftActiveAuctions> {
        public static final int constructor = -1513074355;
        public long hash;

        public TL_getStarGiftActiveAuctions() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public StarGiftActiveAuctions deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return StarGiftActiveAuctions.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.hash);
        }
    }

    public static class TL_getStarGiftAuctionAcquiredGifts extends TLMethod<TL_StarGiftAuctionAcquiredGifts> {
        public static final int constructor = 1805831148;
        public long gift_id;

        public TL_getStarGiftAuctionAcquiredGifts() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public TL_StarGiftAuctionAcquiredGifts deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return TL_StarGiftAuctionAcquiredGifts.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.gift_id);
        }
    }

    public static class TL_getStarGiftAuctionState extends TLMethod<TL_StarGiftAuctionState> {
        public static final int constructor = 1553986774;
        public TL_stars.InputStarGiftAuction auction;
        public int version;

        public TL_getStarGiftAuctionState() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public TL_StarGiftAuctionState deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return TL_StarGiftAuctionState.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.auction.serializeToStream(r2);
            r2.writeInt32(this.version);
        }
    }

    public static class TL_resolveStarGiftOffer extends TLMethod<TLRPC.Updates> {
        public static final int constructor = -372344804;
        public boolean decline;
        public int offer_msg_id;

        public TL_resolveStarGiftOffer() {
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
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            r4.writeInt32(TLObject.setFlag(0, 1, this.decline));
            r4.writeInt32(this.offer_msg_id);
        }
    }

    public static class TL_sendStarGiftOffer extends TLMethod<TLRPC.Updates> {
        public static final int constructor = -1883739327;
        public long allow_paid_stars;
        public int duration;
        public int flags;
        public TLRPC.InputPeer peer;
        public TL_stars.StarsAmount price;
        public long random_id;
        public String slug;

        public TL_sendStarGiftOffer() {
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
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            this.peer.serializeToStream(r3);
            r3.writeString(this.slug);
            this.price.serializeToStream(r3);
            r3.writeInt32(this.duration);
            r3.writeInt64(this.random_id);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeInt64(this.allow_paid_stars);
            return;
        }
    }

    public static class TL_starGiftActiveAuctions extends StarGiftActiveAuctions {
        public static final int constructor = -1359565892;
        public ArrayList<TL_stars.TL_StarGiftActiveAuctionState> auctions;
        public ArrayList<TLRPC.Chat> chats;
        public ArrayList<TLRPC.User> users;

        public TL_starGiftActiveAuctions() {
            this.auctions = new ArrayList();
            this.users = new ArrayList();
            this.chats = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.auctions = Vector.deserialize(r2, new TL_payments$TL_starGiftActiveAuctions$$ExternalSyntheticLambda0(), r3);
            this.users = Vector.deserialize(r2, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r3);
            this.chats = Vector.deserialize(r2, new TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.auctions);
            Vector.serialize(r2, this.users);
            Vector.serialize(r2, this.chats);
        }
    }

    public static class TL_starGiftActiveAuctionsNotModified extends StarGiftActiveAuctions {
        public static final int constructor = -617358640;

        public TL_starGiftActiveAuctionsNotModified() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class connectStarRefBot extends TLObject {
        public static final int constructor = 2127901834;
        public TLRPC.InputUser bot;
        public TLRPC.InputPeer peer;

        public connectStarRefBot() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return connectedStarRefBots.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            this.bot.serializeToStream(r2);
        }
    }

    public static class connectedBotStarRef extends TLObject {
        public static final int constructor = 429997937;
        public long bot_id;
        public int commission_permille;
        public int date;
        public int duration_months;
        public int flags;
        public long participants;
        public long revenue;
        public boolean revoked;
        public String url;

        public connectedBotStarRef() {
        }

        public static connectedBotStarRef TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (429997937 == r3) goto L5;
            connectedBotStarRef r0 = null;
        L7:
            return (connectedBotStarRef) TLObject.TLdeserialize(connectedBotStarRef.class, r0, r2, r3, r4);
        L5:
            r0 = new connectedBotStarRef();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.revoked = TLObject.hasFlag(r0, 2);
            this.url = r3.readString(r4);
            this.date = r3.readInt32(r4);
            this.bot_id = r3.readInt64(r4);
            this.commission_permille = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            this.duration_months = r3.readInt32(r4);
        L5:
            this.participants = r3.readInt64(r4);
            this.revenue = r3.readInt64(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.revoked);
            this.flags = r0;
            r4.writeInt32(r0);
            r4.writeString(this.url);
            r4.writeInt32(this.date);
            r4.writeInt64(this.bot_id);
            r4.writeInt32(this.commission_permille);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            r4.writeInt32(this.duration_months);
        L5:
            r4.writeInt64(this.participants);
            r4.writeInt64(this.revenue);
        }
    }

    public static class connectedStarRefBots extends TLObject {
        public static final int constructor = -1730811363;
        public ArrayList<connectedBotStarRef> connected_bots;
        public int count;
        public ArrayList<TLRPC.User> users;

        public connectedStarRefBots() {
            this.connected_bots = new ArrayList();
            this.users = new ArrayList();
        }

        public static connectedStarRefBots TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-1730811363) == r3) goto L5;
            connectedStarRefBots r0 = null;
        L7:
            return (connectedStarRefBots) TLObject.TLdeserialize(connectedStarRefBots.class, r0, r2, r3, r4);
        L5:
            r0 = new connectedStarRefBots();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.count = r2.readInt32(r3);
            this.connected_bots = Vector.deserialize(r2, new TL_payments$connectedStarRefBots$$ExternalSyntheticLambda0(), r3);
            this.users = Vector.deserialize(r2, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.count);
            Vector.serialize(r2, this.connected_bots);
            Vector.serialize(r2, this.users);
        }
    }

    public static class editConnectedStarRefBot extends TLObject {
        public static final int constructor = -453204829;
        public int flags;
        public String link;
        public TLRPC.InputPeer peer;
        public boolean revoked;

        public editConnectedStarRefBot() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return connectedStarRefBots.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.revoked);
            this.flags = r0;
            r4.writeInt32(r0);
            this.peer.serializeToStream(r4);
            r4.writeString(this.link);
        }
    }

    public static class getConnectedStarRefBot extends TLObject {
        public static final int constructor = -1210476304;
        public TLRPC.InputUser bot;
        public TLRPC.InputPeer peer;

        public getConnectedStarRefBot() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return connectedStarRefBots.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            this.bot.serializeToStream(r2);
        }
    }

    public static class getConnectedStarRefBots extends TLObject {
        public static final int constructor = 1483318611;
        public int flags;
        public int limit;
        public int offset_date;
        public String offset_link;
        public TLRPC.InputPeer peer;

        public getConnectedStarRefBots() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return connectedStarRefBots.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            this.peer.serializeToStream(r3);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L5;
            r3.writeInt32(this.offset_date);
            r3.writeString(this.offset_link);
        L5:
            r3.writeInt32(this.limit);
        }
    }

    public static class getSuggestedStarRefBots extends TLObject {
        public static final int constructor = 225134839;
        public int flags;
        public int limit;
        public String offset;
        public boolean order_by_date;
        public boolean order_by_revenue;
        public TLRPC.InputPeer peer;

        public getSuggestedStarRefBots() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return suggestedStarRefBots.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.order_by_revenue);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.order_by_date);
            this.flags = r02;
            r4.writeInt32(r02);
            this.peer.serializeToStream(r4);
            r4.writeString(this.offset);
            r4.writeInt32(this.limit);
        }
    }

    public static class starRefProgram extends TLObject {
        public static final int constructor = -586389774;
        public long bot_id;
        public int commission_permille;
        public TL_stars.StarsAmount daily_revenue_per_user;
        public int duration_months;
        public int end_date;
        public int flags;

        public starRefProgram() {
            this.daily_revenue_per_user = TL_stars.StarsAmount.ofStars(0);
        }

        public static starRefProgram TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-586389774) == r3) goto L5;
            starRefProgram r0 = null;
        L7:
            return (starRefProgram) TLObject.TLdeserialize(starRefProgram.class, r0, r2, r3, r4);
        L5:
            r0 = new starRefProgram();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.bot_id = r3.readInt64(r4);
            this.commission_permille = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.duration_months = r3.readInt32(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.end_date = r3.readInt32(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.daily_revenue_per_user = TL_stars.StarsAmount.TLdeserialize(r3, r3.readInt32(r4), r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeInt64(this.bot_id);
            r3.writeInt32(this.commission_permille);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeInt32(this.duration_months);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r3.writeInt32(this.end_date);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.daily_revenue_per_user.serializeToStream(r3);
            return;
        }
    }

    public static class suggestedStarRefBots extends TLObject {
        public static final int constructor = -1261053863;
        public int count;
        public int flags;
        public String next_offset;
        public ArrayList<starRefProgram> suggested_bots;
        public ArrayList<TLRPC.User> users;

        public suggestedStarRefBots() {
            this.suggested_bots = new ArrayList();
            this.users = new ArrayList();
        }

        public static suggestedStarRefBots TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-1261053863) == r3) goto L5;
            suggestedStarRefBots r0 = null;
        L7:
            return (suggestedStarRefBots) TLObject.TLdeserialize(suggestedStarRefBots.class, r0, r2, r3, r4);
        L5:
            r0 = new suggestedStarRefBots();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.count = r3.readInt32(r4);
            this.suggested_bots = Vector.deserialize(r3, new TL_payments$suggestedStarRefBots$$ExternalSyntheticLambda0(), r4);
            this.users = Vector.deserialize(r3, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.next_offset = r3.readString(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeInt32(this.count);
            Vector.serialize(r3, this.suggested_bots);
            Vector.serialize(r3, this.users);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeString(this.next_offset);
            return;
        }
    }

    public TL_payments() {
    }
}
