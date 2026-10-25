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
import org.telegram.tgnet.tl.TL_stories;

/* loaded from: classes6.dex */
public class TL_stats {

    /* loaded from: classes.dex */
    public static class BroadcastRevenueTransaction extends TLObject {
        public BroadcastRevenueTransaction() {
        }

        public static BroadcastRevenueTransaction TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != 1121127726) goto L5;
            TLObject r0 = new TL_broadcastRevenueTransactionRefund();
        L13:
            return (BroadcastRevenueTransaction) TLObject.TLdeserialize(BroadcastRevenueTransaction.class, r0, r2, r3, r4);
        L5:
            if (r3 != 1434332356) goto L7;
            r0 = new TL_broadcastRevenueTransactionProceeds();
            goto L13
        L7:
            if (r3 == 1515784568) goto L9;
            r0 = null;
            goto L13
        L9:
            r0 = new TL_broadcastRevenueTransactionWithdrawal();
            goto L13
        }
    }

    public static abstract class PostInteractionCounters extends TLObject {
        public PostInteractionCounters() {
        }

        public static PostInteractionCounters TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-1974989273)) goto L5;
            TLObject r0 = new TL_postInteractionCountersStory();
        L10:
            return (PostInteractionCounters) TLObject.TLdeserialize(PostInteractionCounters.class, r0, r2, r3, r4);
        L5:
            if (r3 == (-419066241)) goto L7;
            r0 = null;
            goto L10
        L7:
            r0 = new TL_postInteractionCountersMessage();
            goto L10
        }
    }

    public static abstract class PublicForward extends TLObject {
        public PublicForward() {
        }

        public static PublicForward TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-302797360)) goto L5;
            TLObject r0 = new TL_stories.TL_publicForwardStory();
        L10:
            return (PublicForward) TLObject.TLdeserialize(PublicForward.class, r0, r2, r3, r4);
        L5:
            if (r3 == 32685898) goto L7;
            r0 = null;
            goto L10
        L7:
            r0 = new TL_publicForwardMessage();
            goto L10
        }
    }

    public static abstract class StatsGraph extends TLObject {
        public float rate;

        public StatsGraph() {
        }

        public static StatsGraph TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-1901828938)) goto L5;
            TLObject r0 = new TL_statsGraph();
        L13:
            return (StatsGraph) TLObject.TLdeserialize(StatsGraph.class, r0, r2, r3, r4);
        L5:
            if (r3 != (-1092839390)) goto L7;
            r0 = new TL_statsGraphError();
            goto L13
        L7:
            if (r3 == 1244130093) goto L9;
            r0 = null;
            goto L13
        L9:
            r0 = new TL_statsGraphAsync();
            goto L13
        }
    }

    public static class TL_broadcastRevenueStats extends TLObject {
        public static final int constructor = 1409802903;
        public TLRPC.BroadcastRevenueBalances balances;
        public StatsGraph revenue_graph;
        public StatsGraph top_hours_graph;
        public double usd_rate;

        public TL_broadcastRevenueStats() {
        }

        public static TL_broadcastRevenueStats TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (1409802903 == r3) goto L5;
            TL_broadcastRevenueStats r0 = null;
        L7:
            return (TL_broadcastRevenueStats) TLObject.TLdeserialize(TL_broadcastRevenueStats.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_broadcastRevenueStats();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.top_hours_graph = StatsGraph.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.revenue_graph = StatsGraph.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.balances = TLRPC.BroadcastRevenueBalances.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.usd_rate = r2.readDouble(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            this.top_hours_graph.serializeToStream(r3);
            this.revenue_graph.serializeToStream(r3);
            this.balances.serializeToStream(r3);
            r3.writeDouble(this.usd_rate);
        }
    }

    public static class TL_broadcastRevenueTransactionProceeds extends BroadcastRevenueTransaction {
        public static final int constructor = 1434332356;
        public long amount;
        public int from_date;
        public int to_date;

        public TL_broadcastRevenueTransactionProceeds() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.amount = r3.readInt64(r4);
            this.from_date = r3.readInt32(r4);
            this.to_date = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.amount);
            r3.writeInt32(this.from_date);
            r3.writeInt32(this.to_date);
        }
    }

    public static class TL_broadcastRevenueTransactionRefund extends BroadcastRevenueTransaction {
        public static final int constructor = 1121127726;
        public long amount;
        public int from_date;
        public String provider;

        public TL_broadcastRevenueTransactionRefund() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.amount = r3.readInt64(r4);
            this.from_date = r3.readInt32(r4);
            this.provider = r3.readString(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.amount);
            r3.writeInt32(this.from_date);
            r3.writeString(this.provider);
        }
    }

    public static class TL_broadcastRevenueTransactionWithdrawal extends BroadcastRevenueTransaction {
        public static final int constructor = 1515784568;
        public long amount;
        public int date;
        public boolean failed;
        public int flags;
        public boolean pending;
        public String provider;
        public int transaction_date;
        public String transaction_url;

        public TL_broadcastRevenueTransactionWithdrawal() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.pending = TLObject.hasFlag(r0, 1);
            this.failed = TLObject.hasFlag(this.flags, 4);
            this.amount = r3.readInt64(r4);
            this.date = r3.readInt32(r4);
            this.provider = r3.readString(r4);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L6;
            this.transaction_date = r3.readInt32(r4);
            this.transaction_url = r3.readString(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            boolean r0 = this.pending;
            int r1 = this.flags;
            if (r0 == false) goto L5;
            int r02 = r1 | 1;
        L6:
            this.flags = r02;
            if (this.failed == false) goto L9;
            int r03 = r02 | 1;
        L10:
            this.flags = r03;
            r3.writeInt64(this.amount);
            r3.writeInt32(this.date);
            r3.writeString(this.provider);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L14;
            r3.writeInt32(this.transaction_date);
            r3.writeString(this.transaction_url);
            return;
        L14:
            return;
        L9:
            r03 = r02 & (-2);
            goto L10
        L5:
            r02 = r1 & (-2);
            goto L6
        }
    }

    public static class TL_broadcastRevenueTransactions extends TLObject {
        public static final int constructor = -2028632986;
        public int count;
        public ArrayList<BroadcastRevenueTransaction> transactions;

        public TL_broadcastRevenueTransactions() {
            this.transactions = new ArrayList();
        }

        public static TL_broadcastRevenueTransactions TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-2028632986) == r3) goto L5;
            TL_broadcastRevenueTransactions r0 = null;
        L7:
            return (TL_broadcastRevenueTransactions) TLObject.TLdeserialize(TL_broadcastRevenueTransactions.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_broadcastRevenueTransactions();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.count = r2.readInt32(r3);
            this.transactions = Vector.deserialize(r2, new TL_stats$TL_broadcastRevenueTransactions$$ExternalSyntheticLambda0(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.count);
            Vector.serialize(r2, this.transactions);
        }
    }

    public static class TL_broadcastRevenueWithdrawalUrl extends TLObject {
        public static final int constructor = -328886473;
        public String url;

        public TL_broadcastRevenueWithdrawalUrl() {
        }

        public static TL_broadcastRevenueWithdrawalUrl TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-328886473) == r3) goto L5;
            TL_broadcastRevenueWithdrawalUrl r0 = null;
        L7:
            return (TL_broadcastRevenueWithdrawalUrl) TLObject.TLdeserialize(TL_broadcastRevenueWithdrawalUrl.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_broadcastRevenueWithdrawalUrl();
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

    public static class TL_broadcastStats extends TLObject {
        public static int constructor = 963421692;
        public TL_statsPercentValue enabled_notifications;
        public TL_statsAbsValueAndPrev followers;
        public StatsGraph followers_graph;
        public StatsGraph growth_graph;
        public StatsGraph interactions_graph;
        public StatsGraph iv_interactions_graph;
        public StatsGraph languages_graph;
        public StatsGraph mute_graph;
        public StatsGraph new_followers_by_source_graph;
        public TL_statsDateRangeDays period;
        public StatsGraph reactions_by_emotion_graph;
        public TL_statsAbsValueAndPrev reactions_per_post;
        public TL_statsAbsValueAndPrev reactions_per_story;
        public ArrayList<PostInteractionCounters> recent_posts_interactions;
        public TL_statsAbsValueAndPrev shares_per_post;
        public TL_statsAbsValueAndPrev shares_per_story;
        public StatsGraph story_interactions_graph;
        public StatsGraph story_reactions_by_emotion_graph;
        public StatsGraph top_hours_graph;
        public StatsGraph views_by_source_graph;
        public TL_statsAbsValueAndPrev views_per_post;
        public TL_statsAbsValueAndPrev views_per_story;

        static {
        }

        public TL_broadcastStats() {
            this.recent_posts_interactions = new ArrayList();
        }

        public static TL_broadcastStats TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (constructor == r3) goto L5;
            TL_broadcastStats r0 = null;
        L7:
            return (TL_broadcastStats) TLObject.TLdeserialize(TL_broadcastStats.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_broadcastStats();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.period = TL_statsDateRangeDays.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.followers = TL_statsAbsValueAndPrev.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.views_per_post = TL_statsAbsValueAndPrev.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.shares_per_post = TL_statsAbsValueAndPrev.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.reactions_per_post = TL_statsAbsValueAndPrev.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.views_per_story = TL_statsAbsValueAndPrev.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.shares_per_story = TL_statsAbsValueAndPrev.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.reactions_per_story = TL_statsAbsValueAndPrev.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.enabled_notifications = TL_statsPercentValue.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.growth_graph = StatsGraph.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.followers_graph = StatsGraph.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.mute_graph = StatsGraph.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.top_hours_graph = StatsGraph.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.interactions_graph = StatsGraph.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.iv_interactions_graph = StatsGraph.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.views_by_source_graph = StatsGraph.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.new_followers_by_source_graph = StatsGraph.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.languages_graph = StatsGraph.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.reactions_by_emotion_graph = StatsGraph.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.story_interactions_graph = StatsGraph.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.story_reactions_by_emotion_graph = StatsGraph.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.recent_posts_interactions = Vector.deserialize(r2, new TL_stats$TL_broadcastStats$$ExternalSyntheticLambda0(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.period.serializeToStream(r2);
            this.followers.serializeToStream(r2);
            this.views_per_post.serializeToStream(r2);
            this.shares_per_post.serializeToStream(r2);
            this.reactions_per_post.serializeToStream(r2);
            this.views_per_story.serializeToStream(r2);
            this.shares_per_story.serializeToStream(r2);
            this.reactions_per_story.serializeToStream(r2);
            this.enabled_notifications.serializeToStream(r2);
            this.growth_graph.serializeToStream(r2);
            this.followers_graph.serializeToStream(r2);
            this.mute_graph.serializeToStream(r2);
            this.top_hours_graph.serializeToStream(r2);
            this.interactions_graph.serializeToStream(r2);
            this.iv_interactions_graph.serializeToStream(r2);
            this.views_by_source_graph.serializeToStream(r2);
            this.new_followers_by_source_graph.serializeToStream(r2);
            this.languages_graph.serializeToStream(r2);
            this.reactions_by_emotion_graph.serializeToStream(r2);
            this.story_interactions_graph.serializeToStream(r2);
            this.story_reactions_by_emotion_graph.serializeToStream(r2);
            Vector.serialize(r2, this.recent_posts_interactions);
        }
    }

    public static class TL_getBroadcastStats extends TLObject {
        public static final int constructor = -1421720550;
        public TLRPC.InputChannel channel;
        public boolean dark;
        public int flags;

        public TL_getBroadcastStats() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_broadcastStats.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            boolean r0 = this.dark;
            int r1 = this.flags;
            if (r0 == false) goto L5;
            int r02 = r1 | 1;
        L6:
            this.flags = r02;
            r3.writeInt32(r02);
            this.channel.serializeToStream(r3);
            return;
        L5:
            r02 = r1 & (-2);
            goto L6
        }
    }

    public static class TL_getMegagroupStats extends TLObject {
        public static final int constructor = -589330937;
        public TLRPC.InputChannel channel;
        public boolean dark;
        public int flags;

        public TL_getMegagroupStats() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_megagroupStats.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            boolean r0 = this.dark;
            int r1 = this.flags;
            if (r0 == false) goto L5;
            int r02 = r1 | 1;
        L6:
            this.flags = r02;
            r3.writeInt32(r02);
            this.channel.serializeToStream(r3);
            return;
        L5:
            r02 = r1 & (-2);
            goto L6
        }
    }

    public static class TL_getMessagePublicForwards extends TLObject {
        public static final int constructor = 1595212100;
        public TLRPC.InputChannel channel;
        public int limit;
        public int msg_id;
        public String offset;

        public TL_getMessagePublicForwards() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_publicForwards.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.channel.serializeToStream(r2);
            r2.writeInt32(this.msg_id);
            r2.writeString(this.offset);
            r2.writeInt32(this.limit);
        }
    }

    public static class TL_getMessageStats extends TLObject {
        public static final int constructor = -1226791947;
        public TLRPC.InputChannel channel;
        public boolean dark;
        public int flags;
        public int msg_id;

        public TL_getMessageStats() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_messageStats.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            boolean r0 = this.dark;
            int r1 = this.flags;
            if (r0 == false) goto L5;
            int r02 = r1 | 1;
        L6:
            this.flags = r02;
            r3.writeInt32(r02);
            this.channel.serializeToStream(r3);
            r3.writeInt32(this.msg_id);
            return;
        L5:
            r02 = r1 & (-2);
            goto L6
        }
    }

    public static class TL_getStoryPublicForwards extends TLObject {
        public static final int constructor = -1505526026;
        public int id;
        public int limit;
        public String offset;
        public TLRPC.InputPeer peer;

        public TL_getStoryPublicForwards() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_publicForwards.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            r2.writeInt32(this.id);
            r2.writeString(this.offset);
            r2.writeInt32(this.limit);
        }
    }

    public static class TL_loadAsyncGraph extends TLObject {
        public static final int constructor = 1646092192;
        public int flags;
        public String token;
        public long x;

        public TL_loadAsyncGraph() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return StatsGraph.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeString(this.token);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeInt64(this.x);
            return;
        }
    }

    public static class TL_megagroupStats extends TLObject {
        public static final int constructor = -276825834;
        public StatsGraph actions_graph;
        public StatsGraph growth_graph;
        public StatsGraph languages_graph;
        public TL_statsAbsValueAndPrev members;
        public StatsGraph members_graph;
        public TL_statsAbsValueAndPrev messages;
        public StatsGraph messages_graph;
        public StatsGraph new_members_by_source_graph;
        public TL_statsDateRangeDays period;
        public TL_statsAbsValueAndPrev posters;
        public ArrayList<TL_statsGroupTopAdmin> top_admins;
        public StatsGraph top_hours_graph;
        public ArrayList<TL_statsGroupTopInviter> top_inviters;
        public ArrayList<TL_statsGroupTopPoster> top_posters;
        public ArrayList<TLRPC.User> users;
        public TL_statsAbsValueAndPrev viewers;
        public StatsGraph weekdays_graph;

        public TL_megagroupStats() {
            this.top_posters = new ArrayList();
            this.top_admins = new ArrayList();
            this.top_inviters = new ArrayList();
            this.users = new ArrayList();
        }

        public static TL_megagroupStats TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-276825834) == r3) goto L5;
            TL_megagroupStats r0 = null;
        L7:
            return (TL_megagroupStats) TLObject.TLdeserialize(TL_megagroupStats.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_megagroupStats();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.period = TL_statsDateRangeDays.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.members = TL_statsAbsValueAndPrev.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.messages = TL_statsAbsValueAndPrev.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.viewers = TL_statsAbsValueAndPrev.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.posters = TL_statsAbsValueAndPrev.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.growth_graph = StatsGraph.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.members_graph = StatsGraph.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.new_members_by_source_graph = StatsGraph.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.languages_graph = StatsGraph.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.messages_graph = StatsGraph.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.actions_graph = StatsGraph.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.top_hours_graph = StatsGraph.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.weekdays_graph = StatsGraph.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.top_posters = Vector.deserialize(r2, new TL_stats$TL_megagroupStats$$ExternalSyntheticLambda0(), r3);
            this.top_admins = Vector.deserialize(r2, new TL_stats$TL_megagroupStats$$ExternalSyntheticLambda1(), r3);
            this.top_inviters = Vector.deserialize(r2, new TL_stats$TL_megagroupStats$$ExternalSyntheticLambda2(), r3);
            this.users = Vector.deserialize(r2, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.period.serializeToStream(r2);
            this.members.serializeToStream(r2);
            this.messages.serializeToStream(r2);
            this.viewers.serializeToStream(r2);
            this.posters.serializeToStream(r2);
            this.growth_graph.serializeToStream(r2);
            this.members_graph.serializeToStream(r2);
            this.new_members_by_source_graph.serializeToStream(r2);
            this.languages_graph.serializeToStream(r2);
            this.messages_graph.serializeToStream(r2);
            this.actions_graph.serializeToStream(r2);
            this.top_hours_graph.serializeToStream(r2);
            this.weekdays_graph.serializeToStream(r2);
            Vector.serialize(r2, this.top_posters);
            Vector.serialize(r2, this.top_admins);
            Vector.serialize(r2, this.top_inviters);
            Vector.serialize(r2, this.users);
        }
    }

    public static class TL_messageStats extends TLObject {
        public static final int constructor = 2145983508;
        public StatsGraph reactions_by_emotion_graph;
        public StatsGraph views_graph;

        public TL_messageStats() {
        }

        public static TL_messageStats TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (2145983508 == r3) goto L5;
            TL_messageStats r0 = null;
        L7:
            return (TL_messageStats) TLObject.TLdeserialize(TL_messageStats.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_messageStats();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.views_graph = StatsGraph.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.reactions_by_emotion_graph = StatsGraph.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.views_graph.serializeToStream(r2);
            this.reactions_by_emotion_graph.serializeToStream(r2);
        }
    }

    public static class TL_postInteractionCountersMessage extends PostInteractionCounters {
        public static final int constructor = -419066241;
        public int forwards;
        public int msg_id;
        public int reactions;
        public int views;

        public TL_postInteractionCountersMessage() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.msg_id = r2.readInt32(r3);
            this.views = r2.readInt32(r3);
            this.forwards = r2.readInt32(r3);
            this.reactions = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.msg_id);
            r2.writeInt32(this.views);
            r2.writeInt32(this.forwards);
            r2.writeInt32(this.reactions);
        }
    }

    public static class TL_postInteractionCountersStory extends PostInteractionCounters {
        public static final int constructor = -1974989273;
        public int forwards;
        public int reactions;
        public int story_id;
        public int views;

        public TL_postInteractionCountersStory() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.story_id = r2.readInt32(r3);
            this.views = r2.readInt32(r3);
            this.forwards = r2.readInt32(r3);
            this.reactions = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.story_id);
            r2.writeInt32(this.views);
            r2.writeInt32(this.forwards);
            r2.writeInt32(this.reactions);
        }
    }

    public static class TL_publicForwardMessage extends PublicForward {
        public static final int constructor = 32685898;
        public TLRPC.Message message;

        public TL_publicForwardMessage() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.message = TLRPC.Message.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.message.serializeToStream(r2);
        }
    }

    public static class TL_publicForwards extends TLObject {
        public static final int constructor = -1828487648;
        public ArrayList<TLRPC.Chat> chats;
        public int count;
        public int flags;
        public ArrayList<PublicForward> forwards;
        public String next_offset;
        public ArrayList<TLRPC.User> users;

        public TL_publicForwards() {
            this.forwards = new ArrayList();
            this.chats = new ArrayList();
            this.users = new ArrayList();
        }

        public static TL_publicForwards TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-1828487648) == r3) goto L5;
            TL_publicForwards r0 = null;
        L7:
            return (TL_publicForwards) TLObject.TLdeserialize(TL_publicForwards.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_publicForwards();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.count = r3.readInt32(r4);
            this.forwards = Vector.deserialize(r3, new TL_stats$TL_publicForwards$$ExternalSyntheticLambda0(), r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            this.next_offset = r3.readString(r4);
        L5:
            this.chats = Vector.deserialize(r3, new TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1(), r4);
            this.users = Vector.deserialize(r3, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeInt32(this.count);
            Vector.serialize(r3, this.forwards);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            r3.writeString(this.next_offset);
        L5:
            Vector.serialize(r3, this.chats);
            Vector.serialize(r3, this.users);
        }
    }

    public static class TL_statsAbsValueAndPrev extends TLObject {
        public static final int constructor = -884757282;
        public double current;
        public double previous;

        public TL_statsAbsValueAndPrev() {
        }

        public static TL_statsAbsValueAndPrev TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-884757282) == r3) goto L5;
            TL_statsAbsValueAndPrev r0 = null;
        L7:
            return (TL_statsAbsValueAndPrev) TLObject.TLdeserialize(TL_statsAbsValueAndPrev.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_statsAbsValueAndPrev();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.current = r3.readDouble(r4);
            this.previous = r3.readDouble(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeDouble(this.current);
            r3.writeDouble(this.previous);
        }
    }

    public static class TL_statsDateRangeDays extends TLObject {
        public static final int constructor = -1237848657;
        public int max_date;
        public int min_date;

        public TL_statsDateRangeDays() {
        }

        public static TL_statsDateRangeDays TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-1237848657) == r3) goto L5;
            TL_statsDateRangeDays r0 = null;
        L7:
            return (TL_statsDateRangeDays) TLObject.TLdeserialize(TL_statsDateRangeDays.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_statsDateRangeDays();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.min_date = r2.readInt32(r3);
            this.max_date = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.min_date);
            r2.writeInt32(this.max_date);
        }
    }

    public static class TL_statsGetPollStats extends TLMethod<TL_statsPollStats> {
        public static int constructor = -1031931288;
        public boolean dark;
        public int flags;
        public int msg_id;
        public TLRPC.InputPeer peer;

        static {
        }

        public TL_statsGetPollStats() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public TL_statsPollStats deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return TL_statsPollStats.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.dark);
            this.flags = r0;
            r4.writeInt32(r0);
            this.peer.serializeToStream(r4);
            r4.writeInt32(this.msg_id);
        }
    }

    public static class TL_statsGraph extends StatsGraph {
        public static final int constructor = -1901828938;
        public int flags;
        public TLRPC.TL_dataJSON json;
        public String zoom_token;

        public TL_statsGraph() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.json = TLRPC.TL_dataJSON.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.zoom_token = r3.readString(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            this.json.serializeToStream(r3);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeString(this.zoom_token);
            return;
        }
    }

    public static class TL_statsGraphAsync extends StatsGraph {
        public static final int constructor = 1244130093;
        public String token;

        public TL_statsGraphAsync() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.token = r1.readString(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.token);
        }
    }

    public static class TL_statsGraphError extends StatsGraph {
        public static final int constructor = -1092839390;
        public String error;

        public TL_statsGraphError() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.error = r1.readString(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.error);
        }
    }

    public static class TL_statsGroupTopAdmin extends TLObject {
        public static final int constructor = -682079097;
        public int banned;
        public int deleted;
        public int kicked;
        public long user_id;

        public TL_statsGroupTopAdmin() {
        }

        public static TL_statsGroupTopAdmin TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-682079097) == r3) goto L5;
            TL_statsGroupTopAdmin r0 = null;
        L7:
            return (TL_statsGroupTopAdmin) TLObject.TLdeserialize(TL_statsGroupTopAdmin.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_statsGroupTopAdmin();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.user_id = r3.readInt64(r4);
            this.deleted = r3.readInt32(r4);
            this.kicked = r3.readInt32(r4);
            this.banned = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.user_id);
            r3.writeInt32(this.deleted);
            r3.writeInt32(this.kicked);
            r3.writeInt32(this.banned);
        }
    }

    public static class TL_statsGroupTopInviter extends TLObject {
        public static final int constructor = 1398765469;
        public int invitations;
        public long user_id;

        public TL_statsGroupTopInviter() {
        }

        public static TL_statsGroupTopInviter TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (1398765469 == r3) goto L5;
            TL_statsGroupTopInviter r0 = null;
        L7:
            return (TL_statsGroupTopInviter) TLObject.TLdeserialize(TL_statsGroupTopInviter.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_statsGroupTopInviter();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.user_id = r3.readInt64(r4);
            this.invitations = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.user_id);
            r3.writeInt32(this.invitations);
        }
    }

    public static class TL_statsGroupTopPoster extends TLObject {
        public static final int constructor = -1660637285;
        public int avg_chars;
        public int messages;
        public long user_id;

        public TL_statsGroupTopPoster() {
        }

        public static TL_statsGroupTopPoster TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-1660637285) == r3) goto L5;
            TL_statsGroupTopPoster r0 = null;
        L7:
            return (TL_statsGroupTopPoster) TLObject.TLdeserialize(TL_statsGroupTopPoster.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_statsGroupTopPoster();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.user_id = r3.readInt64(r4);
            this.messages = r3.readInt32(r4);
            this.avg_chars = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.user_id);
            r3.writeInt32(this.messages);
            r3.writeInt32(this.avg_chars);
        }
    }

    public static class TL_statsPercentValue extends TLObject {
        public static final int constructor = -875679776;
        public double part;
        public double total;

        public TL_statsPercentValue() {
        }

        public static TL_statsPercentValue TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-875679776) == r3) goto L5;
            TL_statsPercentValue r0 = null;
        L7:
            return (TL_statsPercentValue) TLObject.TLdeserialize(TL_statsPercentValue.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_statsPercentValue();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.part = r3.readDouble(r4);
            this.total = r3.readDouble(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeDouble(this.part);
            r3.writeDouble(this.total);
        }
    }

    public static class TL_statsPollStats extends TLObject {
        public static int constructor = 697941741;
        public StatsGraph votes_graph;

        static {
        }

        public TL_statsPollStats() {
        }

        public static TL_statsPollStats TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == constructor) goto L5;
            TL_statsPollStats r0 = null;
        L7:
            return (TL_statsPollStats) TLObject.TLdeserialize(TL_statsPollStats.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_statsPollStats();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.votes_graph = StatsGraph.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.votes_graph.serializeToStream(r2);
        }
    }

    public TL_stats() {
    }
}
