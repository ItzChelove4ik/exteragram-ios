package org.telegram.tgnet.tl;

import java.util.ArrayList;
import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.TLMethod;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;

/* loaded from: classes.dex */
public class TL_forum {

    /* loaded from: classes6.dex */
    public static class TL_messages_createForumTopic extends TLMethod<TLRPC.Updates> {
        public static final int constructor = 798540757;
        public int flags;
        public int icon_color;
        public long icon_emoji_id;
        public TLRPC.InputPeer peer;
        public long random_id;
        public TLRPC.InputPeer send_as;
        public String title;
        public boolean title_missing;

        public TL_messages_createForumTopic() {
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
        public void serializeToStream(OutputSerializedData r6) {
            r6.writeInt32(constructor);
            int r0 = this.flags;
            if (this.send_as == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 4, r1);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 16, this.title_missing);
            this.flags = r03;
            r6.writeInt32(r03);
            this.peer.serializeToStream(r6);
            r6.writeString(this.title);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L10;
            r6.writeInt32(this.icon_color);
        L10:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L12;
            r6.writeInt64(this.icon_emoji_id);
        L12:
            r6.writeInt64(this.random_id);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L16;
            this.send_as.serializeToStream(r6);
            return;
        L16:
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_messages_deleteTopicHistory extends TLMethod<TLRPC.TL_messages_affectedHistory> {
        public static final int constructor = -763269360;
        public TLRPC.InputPeer peer;
        public int top_msg_id;

        public TL_messages_deleteTopicHistory() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public TLRPC.TL_messages_affectedHistory deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.TL_messages_affectedHistory.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            r2.writeInt32(this.top_msg_id);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_messages_editForumTopic extends TLMethod<TLRPC.Updates> {
        public static final int constructor = -825487052;
        public boolean closed;
        public int flags;
        public boolean hidden;
        public long icon_emoji_id;
        public TLRPC.InputPeer peer;
        public String title;
        public int topic_id;

        public TL_messages_editForumTopic() {
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
            int r0 = this.flags;
            if (this.title == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 1, r1);
            this.flags = r02;
            r4.writeInt32(r02);
            this.peer.serializeToStream(r4);
            r4.writeInt32(this.topic_id);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L10;
            r4.writeString(this.title);
        L10:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L13;
            r4.writeInt64(this.icon_emoji_id);
        L13:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L16;
            r4.writeBool(this.closed);
        L16:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L19;
            r4.writeBool(this.hidden);
            return;
        L19:
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_messages_getForumTopics extends TLMethod<TLRPC.TL_messages_forumTopics> {
        public static final int constructor = 1000635391;
        public int limit;
        public int offset_date;
        public int offset_id;
        public int offset_topic;
        public TLRPC.InputPeer peer;
        public String q;

        public TL_messages_getForumTopics() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public TLRPC.TL_messages_forumTopics deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.TL_messages_forumTopics.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            if (this.q == null) goto L5;
            boolean r0 = true;
        L6:
            int r02 = TLObject.setFlag(0, 1, r0);
            r4.writeInt32(r02);
            this.peer.serializeToStream(r4);
            if (TLObject.hasFlag(r02, 1) == false) goto L9;
            r4.writeString(this.q);
        L9:
            r4.writeInt32(this.offset_date);
            r4.writeInt32(this.offset_id);
            r4.writeInt32(this.offset_topic);
            r4.writeInt32(this.limit);
            return;
        L5:
            r0 = false;
            goto L6
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_messages_getForumTopicsByID extends TLMethod<TLRPC.TL_messages_forumTopics> {
        public static final int constructor = -1358280184;
        public TLRPC.InputPeer peer;
        public ArrayList<Integer> topics;

        public TL_messages_getForumTopicsByID() {
            this.topics = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public TLRPC.TL_messages_forumTopics deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.TL_messages_forumTopics.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            Vector.serializeInt(r2, this.topics);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_messages_reorderPinnedForumTopics extends TLObject {
        public static final int constructor = 242762224;
        public boolean force;
        public ArrayList<Integer> order;
        public TLRPC.InputPeer peer;

        public TL_messages_reorderPinnedForumTopics() {
            this.order = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            r4.writeInt32(TLObject.setFlag(0, 1, this.force));
            this.peer.serializeToStream(r4);
            Vector.serializeInt(r4, this.order);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_messages_updatePinnedForumTopic extends TLObject {
        public static final int constructor = 392032849;
        public TLRPC.InputPeer peer;
        public boolean pinned;
        public int topic_id;

        public TL_messages_updatePinnedForumTopic() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            r2.writeInt32(this.topic_id);
            r2.writeBool(this.pinned);
        }
    }

    private TL_forum() {
    }
}
