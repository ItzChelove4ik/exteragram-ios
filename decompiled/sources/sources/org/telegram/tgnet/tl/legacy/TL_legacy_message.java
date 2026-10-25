package org.telegram.tgnet.tl.legacy;

import android.text.TextUtils;
import org.telegram.messenger.MessagesStorage$$ExternalSyntheticLambda117;
import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.TLRPC$TL_channel$$ExternalSyntheticLambda0;
import org.telegram.tgnet.Vector;

/* loaded from: classes6.dex */
public class TL_legacy_message {

    public static class TL_message_layer118 extends TLRPC.TL_message {
        public static final int constructor = -181507201;

        public TL_message_layer118() {
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r5, boolean r6) {
            int r0 = r5.readInt32(r6);
            this.flags = r0;
            this.out = TLObject.hasFlag(r0, 2);
            this.mentioned = TLObject.hasFlag(this.flags, 16);
            this.media_unread = TLObject.hasFlag(this.flags, 32);
            this.silent = TLObject.hasFlag(this.flags, 8192);
            this.post = TLObject.hasFlag(this.flags, 16384);
            this.from_scheduled = TLObject.hasFlag(this.flags, 262144);
            this.legacy = TLObject.hasFlag(this.flags, 524288);
            this.edit_hide = TLObject.hasFlag(this.flags, TLObject.FLAG_21);
            this.id = r5.readInt32(r6);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L5;
            TLRPC.TL_peerUser r02 = new TLRPC.TL_peerUser();
            this.from_id = r02;
            r02.user_id = r5.readInt32(r6);
        L5:
            this.peer_id = TLRPC.Peer.TLdeserialize(r5, r5.readInt32(r6), r6);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.fwd_from = TLRPC.MessageFwdHeader.TLdeserialize(r5, r5.readInt32(r6), r6);
        L9:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L12;
            this.via_bot_id = r5.readInt32(r6);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L14;
            TLRPC.TL_messageReplyHeader r03 = new TLRPC.TL_messageReplyHeader();
            this.reply_to = r03;
            r03.flags = 16 | r03.flags;
            r03.reply_to_msg_id = r5.readInt32(r6);
        L14:
            this.date = r5.readInt32(r6);
            this.message = r5.readString(r6);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L24;
            TLRPC.MessageMedia r04 = TLRPC.MessageMedia.TLdeserialize(r5, r5.readInt32(r6), r6);
            this.media = r04;
            if (r04 == null) goto L19;
            this.ttl = r04.ttl_seconds;
        L19:
            if (r04 == null) goto L24;
            if (TextUtils.isEmpty(r04.captionLegacy) == true) goto L24;
            this.message = this.media.captionLegacy;
        L24:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L27;
            this.reply_markup = TLRPC.ReplyMarkup.TLdeserialize(r5, r5.readInt32(r6), r6);
        L27:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L30;
            this.entities = Vector.deserialize(r5, new MessagesStorage$$ExternalSyntheticLambda117(), r6);
        L30:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L33;
            this.views = r5.readInt32(r6);
        L33:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L36;
            this.forwards = r5.readInt32(r6);
        L36:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L39;
            this.edit_date = r5.readInt32(r6);
        L39:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L42;
            this.post_author = r5.readString(r6);
        L42:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L45;
            this.grouped_id = r5.readInt64(r6);
        L45:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L48;
            this.restriction_reason = Vector.deserialize(r5, new TLRPC$TL_channel$$ExternalSyntheticLambda0(), r6);
            return;
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.out);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 16, this.mentioned);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 32, this.media_unread);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 8192, this.silent);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 16384, this.post);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 262144, this.from_scheduled);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 524288, this.legacy);
            this.flags = r07;
            int r08 = TLObject.setFlag(r07, TLObject.FLAG_21, this.edit_hide);
            this.flags = r08;
            r4.writeInt32(r08);
            r4.writeInt32(this.id);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L5;
            r4.writeInt32((int) this.from_id.user_id);
        L5:
            this.peer_id.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.fwd_from.serializeToStream(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L12;
            r4.writeInt32((int) this.via_bot_id);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L14;
            r4.writeInt32(this.reply_to.reply_to_msg_id);
        L14:
            r4.writeInt32(this.date);
            r4.writeString(this.message);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L18;
            this.media.serializeToStream(r4);
        L18:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L21;
            this.reply_markup.serializeToStream(r4);
        L21:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L24;
            Vector.serialize(r4, this.entities);
        L24:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L27;
            r4.writeInt32(this.views);
        L27:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L30;
            r4.writeInt32(this.forwards);
        L30:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L33;
            r4.writeInt32(this.edit_date);
        L33:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L36;
            r4.writeString(this.post_author);
        L36:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L39;
            r4.writeInt64(this.grouped_id);
        L39:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L41;
            Vector.serialize(r4, this.restriction_reason);
        L41:
            writeAttachPath(r4);
        }
    }

    public static class TL_message_layer123 extends TLRPC.TL_message {
        public static final int constructor = 1487813065;

        public TL_message_layer123() {
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.out = TLObject.hasFlag(r0, 2);
            this.mentioned = TLObject.hasFlag(this.flags, 16);
            this.media_unread = TLObject.hasFlag(this.flags, 32);
            this.silent = TLObject.hasFlag(this.flags, 8192);
            this.post = TLObject.hasFlag(this.flags, 16384);
            this.from_scheduled = TLObject.hasFlag(this.flags, 262144);
            this.legacy = TLObject.hasFlag(this.flags, 524288);
            this.edit_hide = TLObject.hasFlag(this.flags, TLObject.FLAG_21);
            this.pinned = TLObject.hasFlag(this.flags, 16777216);
            this.id = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L5;
            this.from_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.peer_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.fwd_from = TLRPC.MessageFwdHeader.TLdeserialize(r3, r3.readInt32(r4), r4);
        L9:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L12;
            this.via_bot_id = r3.readInt32(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L14;
            this.reply_to = TLRPC.MessageReplyHeader.TLdeserialize(r3, r3.readInt32(r4), r4);
        L14:
            this.date = r3.readInt32(r4);
            this.message = r3.readString(r4);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L24;
            TLRPC.MessageMedia r02 = TLRPC.MessageMedia.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.media = r02;
            if (r02 == null) goto L19;
            this.ttl = r02.ttl_seconds;
        L19:
            if (r02 == null) goto L24;
            if (TextUtils.isEmpty(r02.captionLegacy) == true) goto L24;
            this.message = this.media.captionLegacy;
        L24:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L27;
            this.reply_markup = TLRPC.ReplyMarkup.TLdeserialize(r3, r3.readInt32(r4), r4);
        L27:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L30;
            this.entities = Vector.deserialize(r3, new MessagesStorage$$ExternalSyntheticLambda117(), r4);
        L30:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L33;
            this.views = r3.readInt32(r4);
        L33:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L36;
            this.forwards = r3.readInt32(r4);
        L36:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L39;
            this.replies = TLRPC.MessageReplies.TLdeserialize(r3, r3.readInt32(r4), r4);
        L39:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L42;
            this.edit_date = r3.readInt32(r4);
        L42:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L45;
            this.post_author = r3.readString(r4);
        L45:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L48;
            this.grouped_id = r3.readInt64(r4);
        L48:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L51;
            this.restriction_reason = Vector.deserialize(r3, new TLRPC$TL_channel$$ExternalSyntheticLambda0(), r4);
            return;
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.out);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 16, this.mentioned);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 32, this.media_unread);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 8192, this.silent);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 16384, this.post);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 262144, this.from_scheduled);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 524288, this.legacy);
            this.flags = r07;
            int r08 = TLObject.setFlag(r07, TLObject.FLAG_21, this.edit_hide);
            this.flags = r08;
            int r09 = TLObject.setFlag(r08, 16777216, this.pinned);
            this.flags = r09;
            r4.writeInt32(r09);
            r4.writeInt32(this.id);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L5;
            this.from_id.serializeToStream(r4);
        L5:
            this.peer_id.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.fwd_from.serializeToStream(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L12;
            r4.writeInt32((int) this.via_bot_id);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L14;
            this.reply_to.serializeToStream(r4);
        L14:
            r4.writeInt32(this.date);
            r4.writeString(this.message);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L18;
            this.media.serializeToStream(r4);
        L18:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L21;
            this.reply_markup.serializeToStream(r4);
        L21:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L24;
            Vector.serialize(r4, this.entities);
        L24:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L27;
            r4.writeInt32(this.views);
        L27:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L30;
            r4.writeInt32(this.forwards);
        L30:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L33;
            this.replies.serializeToStream(r4);
        L33:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L36;
            r4.writeInt32(this.edit_date);
        L36:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L39;
            r4.writeString(this.post_author);
        L39:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L42;
            r4.writeInt64(this.grouped_id);
        L42:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L44;
            Vector.serialize(r4, this.restriction_reason);
        L44:
            writeAttachPath(r4);
        }
    }

    public static class TL_message_layer131 extends TLRPC.TL_message {
        public static final int constructor = -1125940270;

        public TL_message_layer131() {
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.out = TLObject.hasFlag(r0, 2);
            this.mentioned = TLObject.hasFlag(this.flags, 16);
            this.media_unread = TLObject.hasFlag(this.flags, 32);
            this.silent = TLObject.hasFlag(this.flags, 8192);
            this.post = TLObject.hasFlag(this.flags, 16384);
            this.from_scheduled = TLObject.hasFlag(this.flags, 262144);
            this.legacy = TLObject.hasFlag(this.flags, 524288);
            this.edit_hide = TLObject.hasFlag(this.flags, TLObject.FLAG_21);
            this.pinned = TLObject.hasFlag(this.flags, 16777216);
            this.id = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L5;
            this.from_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.peer_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.fwd_from = TLRPC.MessageFwdHeader.TLdeserialize(r3, r3.readInt32(r4), r4);
        L9:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L12;
            this.via_bot_id = r3.readInt32(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L14;
            this.reply_to = TLRPC.MessageReplyHeader.TLdeserialize(r3, r3.readInt32(r4), r4);
        L14:
            this.date = r3.readInt32(r4);
            this.message = r3.readString(r4);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L24;
            TLRPC.MessageMedia r02 = TLRPC.MessageMedia.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.media = r02;
            if (r02 == null) goto L19;
            this.ttl = r02.ttl_seconds;
        L19:
            if (r02 == null) goto L24;
            if (TextUtils.isEmpty(r02.captionLegacy) == true) goto L24;
            this.message = this.media.captionLegacy;
        L24:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L27;
            this.reply_markup = TLRPC.ReplyMarkup.TLdeserialize(r3, r3.readInt32(r4), r4);
        L27:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L30;
            this.entities = Vector.deserialize(r3, new MessagesStorage$$ExternalSyntheticLambda117(), r4);
        L30:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L33;
            this.views = r3.readInt32(r4);
        L33:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L36;
            this.forwards = r3.readInt32(r4);
        L36:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L39;
            this.replies = TLRPC.MessageReplies.TLdeserialize(r3, r3.readInt32(r4), r4);
        L39:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L42;
            this.edit_date = r3.readInt32(r4);
        L42:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L45;
            this.post_author = r3.readString(r4);
        L45:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L48;
            this.grouped_id = r3.readInt64(r4);
        L48:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L51;
            this.restriction_reason = Vector.deserialize(r3, new TLRPC$TL_channel$$ExternalSyntheticLambda0(), r4);
        L51:
            if (TLObject.hasFlag(this.flags, 33554432) == false) goto L54;
            this.ttl_period = r3.readInt32(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.out);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 16, this.mentioned);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 32, this.media_unread);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 8192, this.silent);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 16384, this.post);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 262144, this.from_scheduled);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 524288, this.legacy);
            this.flags = r07;
            int r08 = TLObject.setFlag(r07, TLObject.FLAG_21, this.edit_hide);
            this.flags = r08;
            int r09 = TLObject.setFlag(r08, 16777216, this.pinned);
            this.flags = r09;
            r4.writeInt32(r09);
            r4.writeInt32(this.id);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L5;
            this.from_id.serializeToStream(r4);
        L5:
            this.peer_id.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.fwd_from.serializeToStream(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L12;
            r4.writeInt32((int) this.via_bot_id);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L14;
            this.reply_to.serializeToStream(r4);
        L14:
            r4.writeInt32(this.date);
            r4.writeString(this.message);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L18;
            this.media.serializeToStream(r4);
        L18:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L21;
            this.reply_markup.serializeToStream(r4);
        L21:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L24;
            Vector.serialize(r4, this.entities);
        L24:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L27;
            r4.writeInt32(this.views);
        L27:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L30;
            r4.writeInt32(this.forwards);
        L30:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L33;
            this.replies.serializeToStream(r4);
        L33:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L36;
            r4.writeInt32(this.edit_date);
        L36:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L39;
            r4.writeString(this.post_author);
        L39:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L42;
            r4.writeInt64(this.grouped_id);
        L42:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L45;
            Vector.serialize(r4, this.restriction_reason);
        L45:
            if (TLObject.hasFlag(this.flags, 33554432) == false) goto L47;
            r4.writeInt32(this.ttl_period);
        L47:
            writeAttachPath(r4);
        }
    }

    public static class TL_message_layer135 extends TLRPC.TL_message {
        public static final int constructor = -2049520670;

        public TL_message_layer135() {
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.out = TLObject.hasFlag(r0, 2);
            this.mentioned = TLObject.hasFlag(this.flags, 16);
            this.media_unread = TLObject.hasFlag(this.flags, 32);
            this.silent = TLObject.hasFlag(this.flags, 8192);
            this.post = TLObject.hasFlag(this.flags, 16384);
            this.from_scheduled = TLObject.hasFlag(this.flags, 262144);
            this.legacy = TLObject.hasFlag(this.flags, 524288);
            this.edit_hide = TLObject.hasFlag(this.flags, TLObject.FLAG_21);
            this.pinned = TLObject.hasFlag(this.flags, 16777216);
            this.noforwards = TLObject.hasFlag(this.flags, 67108864);
            this.id = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L5;
            this.from_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.peer_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.fwd_from = TLRPC.MessageFwdHeader.TLdeserialize(r3, r3.readInt32(r4), r4);
        L9:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L12;
            this.via_bot_id = r3.readInt64(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L14;
            this.reply_to = TLRPC.MessageReplyHeader.TLdeserialize(r3, r3.readInt32(r4), r4);
        L14:
            this.date = r3.readInt32(r4);
            this.message = r3.readString(r4);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L24;
            TLRPC.MessageMedia r02 = TLRPC.MessageMedia.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.media = r02;
            if (r02 == null) goto L19;
            this.ttl = r02.ttl_seconds;
        L19:
            if (r02 == null) goto L24;
            if (TextUtils.isEmpty(r02.captionLegacy) == true) goto L24;
            this.message = this.media.captionLegacy;
        L24:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L27;
            this.reply_markup = TLRPC.ReplyMarkup.TLdeserialize(r3, r3.readInt32(r4), r4);
        L27:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L30;
            this.entities = Vector.deserialize(r3, new MessagesStorage$$ExternalSyntheticLambda117(), r4);
        L30:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L33;
            this.views = r3.readInt32(r4);
        L33:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L36;
            this.forwards = r3.readInt32(r4);
        L36:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L39;
            this.replies = TLRPC.MessageReplies.TLdeserialize(r3, r3.readInt32(r4), r4);
        L39:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L42;
            this.edit_date = r3.readInt32(r4);
        L42:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L45;
            this.post_author = r3.readString(r4);
        L45:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L48;
            this.grouped_id = r3.readInt64(r4);
        L48:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L51;
            this.restriction_reason = Vector.deserialize(r3, new TLRPC$TL_channel$$ExternalSyntheticLambda0(), r4);
        L51:
            if (TLObject.hasFlag(this.flags, 33554432) == false) goto L54;
            this.ttl_period = r3.readInt32(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.out);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 16, this.mentioned);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 32, this.media_unread);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 8192, this.silent);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 16384, this.post);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 262144, this.from_scheduled);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 524288, this.legacy);
            this.flags = r07;
            int r08 = TLObject.setFlag(r07, TLObject.FLAG_21, this.edit_hide);
            this.flags = r08;
            int r09 = TLObject.setFlag(r08, 16777216, this.pinned);
            this.flags = r09;
            int r010 = TLObject.setFlag(r09, 67108864, this.noforwards);
            this.flags = r010;
            r4.writeInt32(r010);
            r4.writeInt32(this.id);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L5;
            this.from_id.serializeToStream(r4);
        L5:
            this.peer_id.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.fwd_from.serializeToStream(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L12;
            r4.writeInt64(this.via_bot_id);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L14;
            this.reply_to.serializeToStream(r4);
        L14:
            r4.writeInt32(this.date);
            r4.writeString(this.message);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L18;
            this.media.serializeToStream(r4);
        L18:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L21;
            this.reply_markup.serializeToStream(r4);
        L21:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L24;
            Vector.serialize(r4, this.entities);
        L24:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L27;
            r4.writeInt32(this.views);
        L27:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L30;
            r4.writeInt32(this.forwards);
        L30:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L33;
            this.replies.serializeToStream(r4);
        L33:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L36;
            r4.writeInt32(this.edit_date);
        L36:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L39;
            r4.writeString(this.post_author);
        L39:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L42;
            r4.writeInt64(this.grouped_id);
        L42:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L45;
            Vector.serialize(r4, this.restriction_reason);
        L45:
            if (TLObject.hasFlag(this.flags, 33554432) == false) goto L47;
            r4.writeInt32(this.ttl_period);
        L47:
            writeAttachPath(r4);
        }
    }

    public static class TL_message_layer169 extends TLRPC.TL_message {
        public static final int constructor = 940666592;

        public TL_message_layer169() {
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.out = TLObject.hasFlag(r0, 2);
            this.mentioned = TLObject.hasFlag(this.flags, 16);
            this.media_unread = TLObject.hasFlag(this.flags, 32);
            this.silent = TLObject.hasFlag(this.flags, 8192);
            this.post = TLObject.hasFlag(this.flags, 16384);
            this.from_scheduled = TLObject.hasFlag(this.flags, 262144);
            this.legacy = TLObject.hasFlag(this.flags, 524288);
            this.edit_hide = TLObject.hasFlag(this.flags, TLObject.FLAG_21);
            this.pinned = TLObject.hasFlag(this.flags, 16777216);
            this.noforwards = TLObject.hasFlag(this.flags, 67108864);
            this.invert_media = TLObject.hasFlag(this.flags, 134217728);
            this.id = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L5;
            this.from_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.peer_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.fwd_from = TLRPC.MessageFwdHeader.TLdeserialize(r3, r3.readInt32(r4), r4);
        L9:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L12;
            this.via_bot_id = r3.readInt64(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L14;
            this.reply_to = TLRPC.MessageReplyHeader.TLdeserialize(r3, r3.readInt32(r4), r4);
        L14:
            this.date = r3.readInt32(r4);
            this.message = r3.readString(r4);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L24;
            TLRPC.MessageMedia r02 = TLRPC.MessageMedia.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.media = r02;
            if (r02 == null) goto L19;
            this.ttl = r02.ttl_seconds;
        L19:
            if (r02 == null) goto L24;
            if (TextUtils.isEmpty(r02.captionLegacy) == true) goto L24;
            this.message = this.media.captionLegacy;
        L24:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L27;
            this.reply_markup = TLRPC.ReplyMarkup.TLdeserialize(r3, r3.readInt32(r4), r4);
        L27:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L30;
            this.entities = Vector.deserialize(r3, new MessagesStorage$$ExternalSyntheticLambda117(), r4);
        L30:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L33;
            this.views = r3.readInt32(r4);
        L33:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L36;
            this.forwards = r3.readInt32(r4);
        L36:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L39;
            this.replies = TLRPC.MessageReplies.TLdeserialize(r3, r3.readInt32(r4), r4);
        L39:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L42;
            this.edit_date = r3.readInt32(r4);
        L42:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L45;
            this.post_author = r3.readString(r4);
        L45:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L48;
            this.grouped_id = r3.readInt64(r4);
        L48:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L51;
            this.reactions = TLRPC.MessageReactions.TLdeserialize(r3, r3.readInt32(r4), r4);
        L51:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L54;
            this.restriction_reason = Vector.deserialize(r3, new TLRPC$TL_channel$$ExternalSyntheticLambda0(), r4);
        L54:
            if (TLObject.hasFlag(this.flags, 33554432) == false) goto L57;
            this.ttl_period = r3.readInt32(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.out);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 16, this.mentioned);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 32, this.media_unread);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 8192, this.silent);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 16384, this.post);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 262144, this.from_scheduled);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 524288, this.legacy);
            this.flags = r07;
            int r08 = TLObject.setFlag(r07, TLObject.FLAG_21, this.edit_hide);
            this.flags = r08;
            int r09 = TLObject.setFlag(r08, 16777216, this.pinned);
            this.flags = r09;
            int r010 = TLObject.setFlag(r09, 67108864, this.noforwards);
            this.flags = r010;
            int r011 = TLObject.setFlag(r010, 134217728, this.invert_media);
            this.flags = r011;
            r4.writeInt32(r011);
            r4.writeInt32(this.id);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L5;
            this.from_id.serializeToStream(r4);
        L5:
            this.peer_id.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.fwd_from.serializeToStream(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L12;
            r4.writeInt64(this.via_bot_id);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L14;
            this.reply_to.serializeToStream(r4);
        L14:
            r4.writeInt32(this.date);
            r4.writeString(this.message);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L18;
            this.media.serializeToStream(r4);
        L18:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L21;
            this.reply_markup.serializeToStream(r4);
        L21:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L24;
            Vector.serialize(r4, this.entities);
        L24:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L27;
            r4.writeInt32(this.views);
        L27:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L30;
            r4.writeInt32(this.forwards);
        L30:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L33;
            this.replies.serializeToStream(r4);
        L33:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L36;
            r4.writeInt32(this.edit_date);
        L36:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L39;
            r4.writeString(this.post_author);
        L39:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L42;
            r4.writeInt64(this.grouped_id);
        L42:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L45;
            this.reactions.serializeToStream(r4);
        L45:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L48;
            Vector.serialize(r4, this.restriction_reason);
        L48:
            if (TLObject.hasFlag(this.flags, 33554432) == false) goto L50;
            r4.writeInt32(this.ttl_period);
        L50:
            writeAttachPath(r4);
        }
    }

    public static class TL_message_layer173 extends TLRPC.TL_message {
        public static final int constructor = 1992213009;

        public TL_message_layer173() {
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.out = TLObject.hasFlag(r0, 2);
            this.mentioned = TLObject.hasFlag(this.flags, 16);
            this.media_unread = TLObject.hasFlag(this.flags, 32);
            this.silent = TLObject.hasFlag(this.flags, 8192);
            this.post = TLObject.hasFlag(this.flags, 16384);
            this.from_scheduled = TLObject.hasFlag(this.flags, 262144);
            this.legacy = TLObject.hasFlag(this.flags, 524288);
            this.edit_hide = TLObject.hasFlag(this.flags, TLObject.FLAG_21);
            this.pinned = TLObject.hasFlag(this.flags, 16777216);
            this.noforwards = TLObject.hasFlag(this.flags, 67108864);
            this.invert_media = TLObject.hasFlag(this.flags, 134217728);
            this.id = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L5;
            this.from_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.peer_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 268435456) == false) goto L9;
            this.saved_peer_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.fwd_from = TLRPC.MessageFwdHeader.TLdeserialize(r3, r3.readInt32(r4), r4);
        L12:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L15;
            this.via_bot_id = r3.readInt64(r4);
        L15:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L17;
            this.reply_to = TLRPC.MessageReplyHeader.TLdeserialize(r3, r3.readInt32(r4), r4);
        L17:
            this.date = r3.readInt32(r4);
            this.message = r3.readString(r4);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L27;
            TLRPC.MessageMedia r02 = TLRPC.MessageMedia.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.media = r02;
            if (r02 == null) goto L22;
            this.ttl = r02.ttl_seconds;
        L22:
            if (r02 == null) goto L27;
            if (TextUtils.isEmpty(r02.captionLegacy) == true) goto L27;
            this.message = this.media.captionLegacy;
        L27:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L30;
            this.reply_markup = TLRPC.ReplyMarkup.TLdeserialize(r3, r3.readInt32(r4), r4);
        L30:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L33;
            this.entities = Vector.deserialize(r3, new MessagesStorage$$ExternalSyntheticLambda117(), r4);
        L33:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L36;
            this.views = r3.readInt32(r4);
        L36:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L39;
            this.forwards = r3.readInt32(r4);
        L39:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L42;
            this.replies = TLRPC.MessageReplies.TLdeserialize(r3, r3.readInt32(r4), r4);
        L42:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L45;
            this.edit_date = r3.readInt32(r4);
        L45:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L48;
            this.post_author = r3.readString(r4);
        L48:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L51;
            this.grouped_id = r3.readInt64(r4);
        L51:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L54;
            this.reactions = TLRPC.MessageReactions.TLdeserialize(r3, r3.readInt32(r4), r4);
        L54:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L57;
            this.restriction_reason = Vector.deserialize(r3, new TLRPC$TL_channel$$ExternalSyntheticLambda0(), r4);
        L57:
            if (TLObject.hasFlag(this.flags, 33554432) == false) goto L60;
            this.ttl_period = r3.readInt32(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.out);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 16, this.mentioned);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 32, this.media_unread);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 8192, this.silent);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 16384, this.post);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 262144, this.from_scheduled);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 524288, this.legacy);
            this.flags = r07;
            int r08 = TLObject.setFlag(r07, TLObject.FLAG_21, this.edit_hide);
            this.flags = r08;
            int r09 = TLObject.setFlag(r08, 16777216, this.pinned);
            this.flags = r09;
            int r010 = TLObject.setFlag(r09, 67108864, this.noforwards);
            this.flags = r010;
            int r011 = TLObject.setFlag(r010, 134217728, this.invert_media);
            this.flags = r011;
            r4.writeInt32(r011);
            r4.writeInt32(this.id);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L5;
            this.from_id.serializeToStream(r4);
        L5:
            this.peer_id.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 268435456) == false) goto L9;
            this.saved_peer_id.serializeToStream(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.fwd_from.serializeToStream(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L15;
            r4.writeInt64(this.via_bot_id);
        L15:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L17;
            this.reply_to.serializeToStream(r4);
        L17:
            r4.writeInt32(this.date);
            r4.writeString(this.message);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L21;
            this.media.serializeToStream(r4);
        L21:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L24;
            this.reply_markup.serializeToStream(r4);
        L24:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L27;
            Vector.serialize(r4, this.entities);
        L27:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L30;
            r4.writeInt32(this.views);
        L30:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L33;
            r4.writeInt32(this.forwards);
        L33:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L36;
            this.replies.serializeToStream(r4);
        L36:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L39;
            r4.writeInt32(this.edit_date);
        L39:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L42;
            r4.writeString(this.post_author);
        L42:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L45;
            r4.writeInt64(this.grouped_id);
        L45:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L48;
            this.reactions.serializeToStream(r4);
        L48:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L51;
            Vector.serialize(r4, this.restriction_reason);
        L51:
            if (TLObject.hasFlag(this.flags, 33554432) == false) goto L53;
            r4.writeInt32(this.ttl_period);
        L53:
            writeAttachPath(r4);
        }
    }

    public static class TL_message_layer175 extends TLRPC.TL_message {
        public static final int constructor = 508332649;

        public TL_message_layer175() {
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.out = TLObject.hasFlag(r0, 2);
            this.mentioned = TLObject.hasFlag(this.flags, 16);
            this.media_unread = TLObject.hasFlag(this.flags, 32);
            this.silent = TLObject.hasFlag(this.flags, 8192);
            this.post = TLObject.hasFlag(this.flags, 16384);
            this.from_scheduled = TLObject.hasFlag(this.flags, 262144);
            this.legacy = TLObject.hasFlag(this.flags, 524288);
            this.edit_hide = TLObject.hasFlag(this.flags, TLObject.FLAG_21);
            this.pinned = TLObject.hasFlag(this.flags, 16777216);
            this.noforwards = TLObject.hasFlag(this.flags, 67108864);
            this.invert_media = TLObject.hasFlag(this.flags, 134217728);
            this.id = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L6;
            this.from_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L6:
            if (TLObject.hasFlag(this.flags, 536870912) == false) goto L8;
            this.from_boosts_applied = r3.readInt32(r4);
        L8:
            this.peer_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 268435456) == false) goto L12;
            this.saved_peer_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L12:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L15;
            this.fwd_from = TLRPC.MessageFwdHeader.TLdeserialize(r3, r3.readInt32(r4), r4);
        L15:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L18;
            this.via_bot_id = r3.readInt64(r4);
        L18:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L20;
            this.reply_to = TLRPC.MessageReplyHeader.TLdeserialize(r3, r3.readInt32(r4), r4);
        L20:
            this.date = r3.readInt32(r4);
            this.message = r3.readString(r4);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L30;
            TLRPC.MessageMedia r02 = TLRPC.MessageMedia.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.media = r02;
            if (r02 == null) goto L25;
            this.ttl = r02.ttl_seconds;
        L25:
            if (r02 == null) goto L30;
            if (TextUtils.isEmpty(r02.captionLegacy) == true) goto L30;
            this.message = this.media.captionLegacy;
        L30:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L33;
            this.reply_markup = TLRPC.ReplyMarkup.TLdeserialize(r3, r3.readInt32(r4), r4);
        L33:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L36;
            this.entities = Vector.deserialize(r3, new MessagesStorage$$ExternalSyntheticLambda117(), r4);
        L36:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L39;
            this.views = r3.readInt32(r4);
        L39:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L42;
            this.forwards = r3.readInt32(r4);
        L42:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L45;
            this.replies = TLRPC.MessageReplies.TLdeserialize(r3, r3.readInt32(r4), r4);
        L45:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L48;
            this.edit_date = r3.readInt32(r4);
        L48:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L51;
            this.post_author = r3.readString(r4);
        L51:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L54;
            this.grouped_id = r3.readInt64(r4);
        L54:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L57;
            this.reactions = TLRPC.MessageReactions.TLdeserialize(r3, r3.readInt32(r4), r4);
        L57:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L60;
            this.restriction_reason = Vector.deserialize(r3, new TLRPC$TL_channel$$ExternalSyntheticLambda0(), r4);
        L60:
            if (TLObject.hasFlag(this.flags, 33554432) == false) goto L63;
            this.ttl_period = r3.readInt32(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.out);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 16, this.mentioned);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 32, this.media_unread);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 8192, this.silent);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 16384, this.post);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 262144, this.from_scheduled);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 524288, this.legacy);
            this.flags = r07;
            int r08 = TLObject.setFlag(r07, TLObject.FLAG_21, this.edit_hide);
            this.flags = r08;
            int r09 = TLObject.setFlag(r08, 16777216, this.pinned);
            this.flags = r09;
            int r010 = TLObject.setFlag(r09, 67108864, this.noforwards);
            this.flags = r010;
            int r011 = TLObject.setFlag(r010, 134217728, this.invert_media);
            this.flags = r011;
            r4.writeInt32(r011);
            r4.writeInt32(this.id);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L6;
            this.from_id.serializeToStream(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 536870912) == false) goto L8;
            r4.writeInt32(this.from_boosts_applied);
        L8:
            this.peer_id.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 268435456) == false) goto L12;
            this.saved_peer_id.serializeToStream(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L15;
            this.fwd_from.serializeToStream(r4);
        L15:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L18;
            r4.writeInt64(this.via_bot_id);
        L18:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L20;
            this.reply_to.serializeToStream(r4);
        L20:
            r4.writeInt32(this.date);
            r4.writeString(this.message);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L24;
            this.media.serializeToStream(r4);
        L24:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L27;
            this.reply_markup.serializeToStream(r4);
        L27:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L30;
            Vector.serialize(r4, this.entities);
        L30:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L33;
            r4.writeInt32(this.views);
        L33:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L36;
            r4.writeInt32(this.forwards);
        L36:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L39;
            this.replies.serializeToStream(r4);
        L39:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L42;
            r4.writeInt32(this.edit_date);
        L42:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L45;
            r4.writeString(this.post_author);
        L45:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L48;
            r4.writeInt64(this.grouped_id);
        L48:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L51;
            this.reactions.serializeToStream(r4);
        L51:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L54;
            Vector.serialize(r4, this.restriction_reason);
        L54:
            if (TLObject.hasFlag(this.flags, 33554432) == false) goto L56;
            r4.writeInt32(this.ttl_period);
        L56:
            writeAttachPath(r4);
        }
    }

    public static class TL_message_layer176 extends TLRPC.TL_message {
        public static final int constructor = -1502839044;

        public TL_message_layer176() {
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.out = TLObject.hasFlag(r0, 2);
            this.mentioned = TLObject.hasFlag(this.flags, 16);
            this.media_unread = TLObject.hasFlag(this.flags, 32);
            this.silent = TLObject.hasFlag(this.flags, 8192);
            this.post = TLObject.hasFlag(this.flags, 16384);
            this.from_scheduled = TLObject.hasFlag(this.flags, 262144);
            this.legacy = TLObject.hasFlag(this.flags, 524288);
            this.edit_hide = TLObject.hasFlag(this.flags, TLObject.FLAG_21);
            this.pinned = TLObject.hasFlag(this.flags, 16777216);
            this.noforwards = TLObject.hasFlag(this.flags, 67108864);
            this.invert_media = TLObject.hasFlag(this.flags, 134217728);
            this.id = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L6;
            this.from_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L6:
            if (TLObject.hasFlag(this.flags, 536870912) == false) goto L8;
            this.from_boosts_applied = r3.readInt32(r4);
        L8:
            this.peer_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 268435456) == false) goto L12;
            this.saved_peer_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L12:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L15;
            this.fwd_from = TLRPC.MessageFwdHeader.TLdeserialize(r3, r3.readInt32(r4), r4);
        L15:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L18;
            this.via_bot_id = r3.readInt64(r4);
        L18:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L20;
            this.reply_to = TLRPC.MessageReplyHeader.TLdeserialize(r3, r3.readInt32(r4), r4);
        L20:
            this.date = r3.readInt32(r4);
            this.message = r3.readString(r4);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L30;
            TLRPC.MessageMedia r02 = TLRPC.MessageMedia.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.media = r02;
            if (r02 == null) goto L25;
            this.ttl = r02.ttl_seconds;
        L25:
            if (r02 == null) goto L30;
            if (TextUtils.isEmpty(r02.captionLegacy) == true) goto L30;
            this.message = this.media.captionLegacy;
        L30:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L33;
            this.reply_markup = TLRPC.ReplyMarkup.TLdeserialize(r3, r3.readInt32(r4), r4);
        L33:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L36;
            this.entities = Vector.deserialize(r3, new MessagesStorage$$ExternalSyntheticLambda117(), r4);
        L36:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L39;
            this.views = r3.readInt32(r4);
        L39:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L42;
            this.forwards = r3.readInt32(r4);
        L42:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L45;
            this.replies = TLRPC.MessageReplies.TLdeserialize(r3, r3.readInt32(r4), r4);
        L45:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L48;
            this.edit_date = r3.readInt32(r4);
        L48:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L51;
            this.post_author = r3.readString(r4);
        L51:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L54;
            this.grouped_id = r3.readInt64(r4);
        L54:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L57;
            this.reactions = TLRPC.MessageReactions.TLdeserialize(r3, r3.readInt32(r4), r4);
        L57:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L60;
            this.restriction_reason = Vector.deserialize(r3, new TLRPC$TL_channel$$ExternalSyntheticLambda0(), r4);
        L60:
            if (TLObject.hasFlag(this.flags, 33554432) == false) goto L63;
            this.ttl_period = r3.readInt32(r4);
        L63:
            if (TLObject.hasFlag(this.flags, TLObject.FLAG_30) == false) goto L66;
            this.quick_reply_shortcut_id = r3.readInt32(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.out);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 16, this.mentioned);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 32, this.media_unread);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 8192, this.silent);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 16384, this.post);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 262144, this.from_scheduled);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 524288, this.legacy);
            this.flags = r07;
            int r08 = TLObject.setFlag(r07, TLObject.FLAG_21, this.edit_hide);
            this.flags = r08;
            int r09 = TLObject.setFlag(r08, 16777216, this.pinned);
            this.flags = r09;
            int r010 = TLObject.setFlag(r09, 67108864, this.noforwards);
            this.flags = r010;
            int r011 = TLObject.setFlag(r010, 134217728, this.invert_media);
            this.flags = r011;
            r4.writeInt32(r011);
            r4.writeInt32(this.id);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L6;
            this.from_id.serializeToStream(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 536870912) == false) goto L8;
            r4.writeInt32(this.from_boosts_applied);
        L8:
            this.peer_id.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 268435456) == false) goto L12;
            this.saved_peer_id.serializeToStream(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L15;
            this.fwd_from.serializeToStream(r4);
        L15:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L18;
            r4.writeInt64(this.via_bot_id);
        L18:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L20;
            this.reply_to.serializeToStream(r4);
        L20:
            r4.writeInt32(this.date);
            r4.writeString(this.message);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L24;
            this.media.serializeToStream(r4);
        L24:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L27;
            this.reply_markup.serializeToStream(r4);
        L27:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L30;
            Vector.serialize(r4, this.entities);
        L30:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L33;
            r4.writeInt32(this.views);
        L33:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L36;
            r4.writeInt32(this.forwards);
        L36:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L39;
            this.replies.serializeToStream(r4);
        L39:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L42;
            r4.writeInt32(this.edit_date);
        L42:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L45;
            r4.writeString(this.post_author);
        L45:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L48;
            r4.writeInt64(this.grouped_id);
        L48:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L51;
            this.reactions.serializeToStream(r4);
        L51:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L54;
            Vector.serialize(r4, this.restriction_reason);
        L54:
            if (TLObject.hasFlag(this.flags, 33554432) == false) goto L57;
            r4.writeInt32(this.ttl_period);
        L57:
            if (TLObject.hasFlag(this.flags, TLObject.FLAG_30) == false) goto L59;
            r4.writeInt32(this.quick_reply_shortcut_id);
        L59:
            writeAttachPath(r4);
        }
    }

    public static class TL_message_layer179 extends TLRPC.TL_message {
        public static final int constructor = 592953125;

        public TL_message_layer179() {
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r4, boolean r5) {
            int r0 = r4.readInt32(r5);
            this.flags = r0;
            this.out = TLObject.hasFlag(r0, 2);
            this.mentioned = TLObject.hasFlag(this.flags, 16);
            this.media_unread = TLObject.hasFlag(this.flags, 32);
            this.silent = TLObject.hasFlag(this.flags, 8192);
            this.post = TLObject.hasFlag(this.flags, 16384);
            this.from_scheduled = TLObject.hasFlag(this.flags, 262144);
            this.legacy = TLObject.hasFlag(this.flags, 524288);
            this.edit_hide = TLObject.hasFlag(this.flags, TLObject.FLAG_21);
            this.pinned = TLObject.hasFlag(this.flags, 16777216);
            this.noforwards = TLObject.hasFlag(this.flags, 67108864);
            this.invert_media = TLObject.hasFlag(this.flags, 134217728);
            int r02 = r4.readInt32(r5);
            this.flags2 = r02;
            this.offline = TLObject.hasFlag(r02, 2);
            this.id = r4.readInt32(r5);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L6;
            this.from_id = TLRPC.Peer.TLdeserialize(r4, r4.readInt32(r5), r5);
        L6:
            if (TLObject.hasFlag(this.flags, 536870912) == false) goto L8;
            this.from_boosts_applied = r4.readInt32(r5);
        L8:
            this.peer_id = TLRPC.Peer.TLdeserialize(r4, r4.readInt32(r5), r5);
            if (TLObject.hasFlag(this.flags, 268435456) == false) goto L12;
            this.saved_peer_id = TLRPC.Peer.TLdeserialize(r4, r4.readInt32(r5), r5);
        L12:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L15;
            this.fwd_from = TLRPC.MessageFwdHeader.TLdeserialize(r4, r4.readInt32(r5), r5);
        L15:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L18;
            this.via_bot_id = r4.readInt64(r5);
        L18:
            if (TLObject.hasFlag(this.flags2, 1) == false) goto L21;
            this.via_business_bot_id = r4.readInt64(r5);
        L21:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L23;
            this.reply_to = TLRPC.MessageReplyHeader.TLdeserialize(r4, r4.readInt32(r5), r5);
        L23:
            this.date = r4.readInt32(r5);
            this.message = r4.readString(r5);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L33;
            TLRPC.MessageMedia r03 = TLRPC.MessageMedia.TLdeserialize(r4, r4.readInt32(r5), r5);
            this.media = r03;
            if (r03 == null) goto L28;
            this.ttl = r03.ttl_seconds;
        L28:
            if (r03 == null) goto L33;
            if (TextUtils.isEmpty(r03.captionLegacy) == true) goto L33;
            this.message = this.media.captionLegacy;
        L33:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L36;
            this.reply_markup = TLRPC.ReplyMarkup.TLdeserialize(r4, r4.readInt32(r5), r5);
        L36:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L39;
            this.entities = Vector.deserialize(r4, new MessagesStorage$$ExternalSyntheticLambda117(), r5);
        L39:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L42;
            this.views = r4.readInt32(r5);
        L42:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L45;
            this.forwards = r4.readInt32(r5);
        L45:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L48;
            this.replies = TLRPC.MessageReplies.TLdeserialize(r4, r4.readInt32(r5), r5);
        L48:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L51;
            this.edit_date = r4.readInt32(r5);
        L51:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L54;
            this.post_author = r4.readString(r5);
        L54:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L57;
            this.grouped_id = r4.readInt64(r5);
        L57:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L60;
            this.reactions = TLRPC.MessageReactions.TLdeserialize(r4, r4.readInt32(r5), r5);
        L60:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L63;
            this.restriction_reason = Vector.deserialize(r4, new TLRPC$TL_channel$$ExternalSyntheticLambda0(), r5);
        L63:
            if (TLObject.hasFlag(this.flags, 33554432) == false) goto L66;
            this.ttl_period = r4.readInt32(r5);
        L66:
            if (TLObject.hasFlag(this.flags, TLObject.FLAG_30) == false) goto L69;
            this.quick_reply_shortcut_id = r4.readInt32(r5);
            return;
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r5) {
            r5.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.out);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 16, this.mentioned);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 32, this.media_unread);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 8192, this.silent);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 16384, this.post);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 262144, this.from_scheduled);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 524288, this.legacy);
            this.flags = r07;
            int r08 = TLObject.setFlag(r07, TLObject.FLAG_21, this.edit_hide);
            this.flags = r08;
            int r09 = TLObject.setFlag(r08, 16777216, this.pinned);
            this.flags = r09;
            int r010 = TLObject.setFlag(r09, 67108864, this.noforwards);
            this.flags = r010;
            int r011 = TLObject.setFlag(r010, 134217728, this.invert_media);
            this.flags = r011;
            r5.writeInt32(r011);
            int r012 = TLObject.setFlag(this.flags2, 2, this.offline);
            this.flags2 = r012;
            r5.writeInt32(r012);
            r5.writeInt32(this.id);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L6;
            this.from_id.serializeToStream(r5);
        L6:
            if (TLObject.hasFlag(this.flags, 536870912) == false) goto L8;
            r5.writeInt32(this.from_boosts_applied);
        L8:
            this.peer_id.serializeToStream(r5);
            if (TLObject.hasFlag(this.flags, 268435456) == false) goto L12;
            this.saved_peer_id.serializeToStream(r5);
        L12:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L15;
            this.fwd_from.serializeToStream(r5);
        L15:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L18;
            r5.writeInt64(this.via_bot_id);
        L18:
            if (TLObject.hasFlag(this.flags2, 1) == false) goto L21;
            r5.writeInt64(this.via_business_bot_id);
        L21:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L23;
            this.reply_to.serializeToStream(r5);
        L23:
            r5.writeInt32(this.date);
            r5.writeString(this.message);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L27;
            this.media.serializeToStream(r5);
        L27:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L30;
            this.reply_markup.serializeToStream(r5);
        L30:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L33;
            Vector.serialize(r5, this.entities);
        L33:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L36;
            r5.writeInt32(this.views);
        L36:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L39;
            r5.writeInt32(this.forwards);
        L39:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L42;
            this.replies.serializeToStream(r5);
        L42:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L45;
            r5.writeInt32(this.edit_date);
        L45:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L48;
            r5.writeString(this.post_author);
        L48:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L51;
            r5.writeInt64(this.grouped_id);
        L51:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L54;
            this.reactions.serializeToStream(r5);
        L54:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L57;
            Vector.serialize(r5, this.restriction_reason);
        L57:
            if (TLObject.hasFlag(this.flags, 33554432) == false) goto L60;
            r5.writeInt32(this.ttl_period);
        L60:
            if (TLObject.hasFlag(this.flags, TLObject.FLAG_30) == false) goto L62;
            r5.writeInt32(this.quick_reply_shortcut_id);
        L62:
            writeAttachPath(r5);
        }
    }

    public static class TL_message_layer180 extends TLRPC.TL_message {
        public static final int constructor = -1109353426;

        public TL_message_layer180() {
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r5, boolean r6) {
            int r0 = r5.readInt32(r6);
            this.flags = r0;
            this.out = TLObject.hasFlag(r0, 2);
            this.mentioned = TLObject.hasFlag(this.flags, 16);
            this.media_unread = TLObject.hasFlag(this.flags, 32);
            this.silent = TLObject.hasFlag(this.flags, 8192);
            this.post = TLObject.hasFlag(this.flags, 16384);
            this.from_scheduled = TLObject.hasFlag(this.flags, 262144);
            this.legacy = TLObject.hasFlag(this.flags, 524288);
            this.edit_hide = TLObject.hasFlag(this.flags, TLObject.FLAG_21);
            this.pinned = TLObject.hasFlag(this.flags, 16777216);
            this.noforwards = TLObject.hasFlag(this.flags, 67108864);
            this.invert_media = TLObject.hasFlag(this.flags, 134217728);
            int r02 = r5.readInt32(r6);
            this.flags2 = r02;
            this.offline = TLObject.hasFlag(r02, 2);
            this.id = r5.readInt32(r6);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L6;
            this.from_id = TLRPC.Peer.TLdeserialize(r5, r5.readInt32(r6), r6);
        L6:
            if (TLObject.hasFlag(this.flags, 536870912) == false) goto L8;
            this.from_boosts_applied = r5.readInt32(r6);
        L8:
            this.peer_id = TLRPC.Peer.TLdeserialize(r5, r5.readInt32(r6), r6);
            if (TLObject.hasFlag(this.flags, 268435456) == false) goto L12;
            this.saved_peer_id = TLRPC.Peer.TLdeserialize(r5, r5.readInt32(r6), r6);
        L12:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L15;
            this.fwd_from = TLRPC.MessageFwdHeader.TLdeserialize(r5, r5.readInt32(r6), r6);
        L15:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L18;
            this.via_bot_id = r5.readInt64(r6);
        L18:
            if (TLObject.hasFlag(this.flags2, 1) == false) goto L21;
            this.via_business_bot_id = r5.readInt64(r6);
        L21:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L23;
            this.reply_to = TLRPC.MessageReplyHeader.TLdeserialize(r5, r5.readInt32(r6), r6);
        L23:
            this.date = r5.readInt32(r6);
            this.message = r5.readString(r6);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L33;
            TLRPC.MessageMedia r03 = TLRPC.MessageMedia.TLdeserialize(r5, r5.readInt32(r6), r6);
            this.media = r03;
            if (r03 == null) goto L28;
            this.ttl = r03.ttl_seconds;
        L28:
            if (r03 == null) goto L33;
            if (TextUtils.isEmpty(r03.captionLegacy) == true) goto L33;
            this.message = this.media.captionLegacy;
        L33:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L36;
            this.reply_markup = TLRPC.ReplyMarkup.TLdeserialize(r5, r5.readInt32(r6), r6);
        L36:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L39;
            this.entities = Vector.deserialize(r5, new MessagesStorage$$ExternalSyntheticLambda117(), r6);
        L39:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L42;
            this.views = r5.readInt32(r6);
        L42:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L45;
            this.forwards = r5.readInt32(r6);
        L45:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L48;
            this.replies = TLRPC.MessageReplies.TLdeserialize(r5, r5.readInt32(r6), r6);
        L48:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L51;
            this.edit_date = r5.readInt32(r6);
        L51:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L54;
            this.post_author = r5.readString(r6);
        L54:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L57;
            this.grouped_id = r5.readInt64(r6);
        L57:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L60;
            this.reactions = TLRPC.MessageReactions.TLdeserialize(r5, r5.readInt32(r6), r6);
        L60:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L63;
            this.restriction_reason = Vector.deserialize(r5, new TLRPC$TL_channel$$ExternalSyntheticLambda0(), r6);
        L63:
            if (TLObject.hasFlag(this.flags, 33554432) == false) goto L66;
            this.ttl_period = r5.readInt32(r6);
        L66:
            if (TLObject.hasFlag(this.flags, TLObject.FLAG_30) == false) goto L69;
            this.quick_reply_shortcut_id = r5.readInt32(r6);
        L69:
            if (TLObject.hasFlag(this.flags2, 4) == false) goto L72;
            this.effect = r5.readInt64(r6);
            return;
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r5) {
            r5.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.out);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 16, this.mentioned);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 32, this.media_unread);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 8192, this.silent);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 16384, this.post);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 262144, this.from_scheduled);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 524288, this.legacy);
            this.flags = r07;
            int r08 = TLObject.setFlag(r07, TLObject.FLAG_21, this.edit_hide);
            this.flags = r08;
            int r09 = TLObject.setFlag(r08, 16777216, this.pinned);
            this.flags = r09;
            int r010 = TLObject.setFlag(r09, 67108864, this.noforwards);
            this.flags = r010;
            int r011 = TLObject.setFlag(r010, 134217728, this.invert_media);
            this.flags = r011;
            r5.writeInt32(r011);
            int r012 = TLObject.setFlag(this.flags2, 2, this.offline);
            this.flags2 = r012;
            r5.writeInt32(r012);
            r5.writeInt32(this.id);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L6;
            this.from_id.serializeToStream(r5);
        L6:
            if (TLObject.hasFlag(this.flags, 536870912) == false) goto L8;
            r5.writeInt32(this.from_boosts_applied);
        L8:
            this.peer_id.serializeToStream(r5);
            if (TLObject.hasFlag(this.flags, 268435456) == false) goto L12;
            this.saved_peer_id.serializeToStream(r5);
        L12:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L15;
            this.fwd_from.serializeToStream(r5);
        L15:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L18;
            r5.writeInt64(this.via_bot_id);
        L18:
            if (TLObject.hasFlag(this.flags2, 1) == false) goto L21;
            r5.writeInt64(this.via_business_bot_id);
        L21:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L23;
            this.reply_to.serializeToStream(r5);
        L23:
            r5.writeInt32(this.date);
            r5.writeString(this.message);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L27;
            this.media.serializeToStream(r5);
        L27:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L30;
            this.reply_markup.serializeToStream(r5);
        L30:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L33;
            Vector.serialize(r5, this.entities);
        L33:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L36;
            r5.writeInt32(this.views);
        L36:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L39;
            r5.writeInt32(this.forwards);
        L39:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L42;
            this.replies.serializeToStream(r5);
        L42:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L45;
            r5.writeInt32(this.edit_date);
        L45:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L48;
            r5.writeString(this.post_author);
        L48:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L51;
            r5.writeInt64(this.grouped_id);
        L51:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L54;
            this.reactions.serializeToStream(r5);
        L54:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L57;
            Vector.serialize(r5, this.restriction_reason);
        L57:
            if (TLObject.hasFlag(this.flags, 33554432) == false) goto L60;
            r5.writeInt32(this.ttl_period);
        L60:
            if (TLObject.hasFlag(this.flags, TLObject.FLAG_30) == false) goto L63;
            r5.writeInt32(this.quick_reply_shortcut_id);
        L63:
            if (TLObject.hasFlag(this.flags2, 4) == false) goto L65;
            r5.writeInt64(this.effect);
        L65:
            writeAttachPath(r5);
        }
    }

    public static class TL_message_layer195 extends TLRPC.TL_message {
        public static final int constructor = -1808510398;

        public TL_message_layer195() {
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r6, boolean r7) {
            int r0 = r6.readInt32(r7);
            this.flags = r0;
            this.out = TLObject.hasFlag(r0, 2);
            this.mentioned = TLObject.hasFlag(this.flags, 16);
            this.media_unread = TLObject.hasFlag(this.flags, 32);
            this.silent = TLObject.hasFlag(this.flags, 8192);
            this.post = TLObject.hasFlag(this.flags, 16384);
            this.from_scheduled = TLObject.hasFlag(this.flags, 262144);
            this.legacy = TLObject.hasFlag(this.flags, 524288);
            this.edit_hide = TLObject.hasFlag(this.flags, TLObject.FLAG_21);
            this.pinned = TLObject.hasFlag(this.flags, 16777216);
            this.noforwards = TLObject.hasFlag(this.flags, 67108864);
            this.invert_media = TLObject.hasFlag(this.flags, 134217728);
            int r02 = r6.readInt32(r7);
            this.flags2 = r02;
            this.offline = TLObject.hasFlag(r02, 2);
            this.video_processing_pending = TLObject.hasFlag(this.flags2, 16);
            this.id = r6.readInt32(r7);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L6;
            this.from_id = TLRPC.Peer.TLdeserialize(r6, r6.readInt32(r7), r7);
        L6:
            if (TLObject.hasFlag(this.flags, 536870912) == false) goto L8;
            this.from_boosts_applied = r6.readInt32(r7);
        L8:
            this.peer_id = TLRPC.Peer.TLdeserialize(r6, r6.readInt32(r7), r7);
            if (TLObject.hasFlag(this.flags, 268435456) == false) goto L12;
            this.saved_peer_id = TLRPC.Peer.TLdeserialize(r6, r6.readInt32(r7), r7);
        L12:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L15;
            this.fwd_from = TLRPC.MessageFwdHeader.TLdeserialize(r6, r6.readInt32(r7), r7);
        L15:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L18;
            this.via_bot_id = r6.readInt64(r7);
        L18:
            if (TLObject.hasFlag(this.flags2, 1) == false) goto L21;
            this.via_business_bot_id = r6.readInt64(r7);
        L21:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L23;
            this.reply_to = TLRPC.MessageReplyHeader.TLdeserialize(r6, r6.readInt32(r7), r7);
        L23:
            this.date = r6.readInt32(r7);
            this.message = r6.readString(r7);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L33;
            TLRPC.MessageMedia r03 = TLRPC.MessageMedia.TLdeserialize(r6, r6.readInt32(r7), r7);
            this.media = r03;
            if (r03 == null) goto L28;
            this.ttl = r03.ttl_seconds;
        L28:
            if (r03 == null) goto L33;
            if (TextUtils.isEmpty(r03.captionLegacy) == true) goto L33;
            this.message = this.media.captionLegacy;
        L33:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L36;
            this.reply_markup = TLRPC.ReplyMarkup.TLdeserialize(r6, r6.readInt32(r7), r7);
        L36:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L39;
            this.entities = Vector.deserialize(r6, new MessagesStorage$$ExternalSyntheticLambda117(), r7);
        L39:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L42;
            this.views = r6.readInt32(r7);
        L42:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L45;
            this.forwards = r6.readInt32(r7);
        L45:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L48;
            this.replies = TLRPC.MessageReplies.TLdeserialize(r6, r6.readInt32(r7), r7);
        L48:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L51;
            this.edit_date = r6.readInt32(r7);
        L51:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L54;
            this.post_author = r6.readString(r7);
        L54:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L57;
            this.grouped_id = r6.readInt64(r7);
        L57:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L60;
            this.reactions = TLRPC.MessageReactions.TLdeserialize(r6, r6.readInt32(r7), r7);
        L60:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L63;
            this.restriction_reason = Vector.deserialize(r6, new TLRPC$TL_channel$$ExternalSyntheticLambda0(), r7);
        L63:
            if (TLObject.hasFlag(this.flags, 33554432) == false) goto L66;
            this.ttl_period = r6.readInt32(r7);
        L66:
            if (TLObject.hasFlag(this.flags, TLObject.FLAG_30) == false) goto L69;
            this.quick_reply_shortcut_id = r6.readInt32(r7);
        L69:
            if (TLObject.hasFlag(this.flags2, 4) == false) goto L72;
            this.effect = r6.readInt64(r7);
        L72:
            if (TLObject.hasFlag(this.flags2, 8) == false) goto L75;
            this.factcheck = TLRPC.TL_factCheck.TLdeserialize(r6, r6.readInt32(r7), r7);
            return;
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r6) {
            r6.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.out);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 16, this.mentioned);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 32, this.media_unread);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 8192, this.silent);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 16384, this.post);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 262144, this.from_scheduled);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 524288, this.legacy);
            this.flags = r07;
            int r08 = TLObject.setFlag(r07, TLObject.FLAG_21, this.edit_hide);
            this.flags = r08;
            int r09 = TLObject.setFlag(r08, 16777216, this.pinned);
            this.flags = r09;
            int r010 = TLObject.setFlag(r09, 67108864, this.noforwards);
            this.flags = r010;
            int r011 = TLObject.setFlag(r010, 134217728, this.invert_media);
            this.flags = r011;
            r6.writeInt32(r011);
            int r012 = TLObject.setFlag(this.flags2, 2, this.offline);
            this.flags2 = r012;
            int r013 = TLObject.setFlag(r012, 16, this.video_processing_pending);
            this.flags2 = r013;
            r6.writeInt32(r013);
            r6.writeInt32(this.id);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L6;
            this.from_id.serializeToStream(r6);
        L6:
            if (TLObject.hasFlag(this.flags, 536870912) == false) goto L8;
            r6.writeInt32(this.from_boosts_applied);
        L8:
            this.peer_id.serializeToStream(r6);
            if (TLObject.hasFlag(this.flags, 268435456) == false) goto L12;
            this.saved_peer_id.serializeToStream(r6);
        L12:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L15;
            this.fwd_from.serializeToStream(r6);
        L15:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L18;
            r6.writeInt64(this.via_bot_id);
        L18:
            if (TLObject.hasFlag(this.flags2, 1) == false) goto L21;
            r6.writeInt64(this.via_business_bot_id);
        L21:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L23;
            this.reply_to.serializeToStream(r6);
        L23:
            r6.writeInt32(this.date);
            r6.writeString(this.message);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L27;
            this.media.serializeToStream(r6);
        L27:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L30;
            this.reply_markup.serializeToStream(r6);
        L30:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L33;
            Vector.serialize(r6, this.entities);
        L33:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L36;
            r6.writeInt32(this.views);
        L36:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L39;
            r6.writeInt32(this.forwards);
        L39:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L42;
            this.replies.serializeToStream(r6);
        L42:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L45;
            r6.writeInt32(this.edit_date);
        L45:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L48;
            r6.writeString(this.post_author);
        L48:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L51;
            r6.writeInt64(this.grouped_id);
        L51:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L54;
            this.reactions.serializeToStream(r6);
        L54:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L57;
            Vector.serialize(r6, this.restriction_reason);
        L57:
            if (TLObject.hasFlag(this.flags, 33554432) == false) goto L60;
            r6.writeInt32(this.ttl_period);
        L60:
            if (TLObject.hasFlag(this.flags, TLObject.FLAG_30) == false) goto L63;
            r6.writeInt32(this.quick_reply_shortcut_id);
        L63:
            if (TLObject.hasFlag(this.flags2, 4) == false) goto L66;
            r6.writeInt64(this.effect);
        L66:
            if (TLObject.hasFlag(this.flags2, 8) == false) goto L68;
            this.factcheck.serializeToStream(r6);
        L68:
            writeAttachPath(r6);
        }
    }

    public static class TL_message_layer199 extends TLRPC.TL_message {
        public static final int constructor = -1761756183;

        public TL_message_layer199() {
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r7, boolean r8) {
            int r0 = r7.readInt32(r8);
            this.flags = r0;
            this.out = TLObject.hasFlag(r0, 2);
            this.mentioned = TLObject.hasFlag(this.flags, 16);
            this.media_unread = TLObject.hasFlag(this.flags, 32);
            this.silent = TLObject.hasFlag(this.flags, 8192);
            this.post = TLObject.hasFlag(this.flags, 16384);
            this.from_scheduled = TLObject.hasFlag(this.flags, 262144);
            this.legacy = TLObject.hasFlag(this.flags, 524288);
            this.edit_hide = TLObject.hasFlag(this.flags, TLObject.FLAG_21);
            this.pinned = TLObject.hasFlag(this.flags, 16777216);
            this.noforwards = TLObject.hasFlag(this.flags, 67108864);
            this.invert_media = TLObject.hasFlag(this.flags, 134217728);
            int r02 = r7.readInt32(r8);
            this.flags2 = r02;
            this.offline = TLObject.hasFlag(r02, 2);
            this.video_processing_pending = TLObject.hasFlag(this.flags2, 16);
            this.id = r7.readInt32(r8);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L6;
            this.from_id = TLRPC.Peer.TLdeserialize(r7, r7.readInt32(r8), r8);
        L6:
            if (TLObject.hasFlag(this.flags, 536870912) == false) goto L8;
            this.from_boosts_applied = r7.readInt32(r8);
        L8:
            this.peer_id = TLRPC.Peer.TLdeserialize(r7, r7.readInt32(r8), r8);
            if (TLObject.hasFlag(this.flags, 268435456) == false) goto L12;
            this.saved_peer_id = TLRPC.Peer.TLdeserialize(r7, r7.readInt32(r8), r8);
        L12:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L15;
            this.fwd_from = TLRPC.MessageFwdHeader.TLdeserialize(r7, r7.readInt32(r8), r8);
        L15:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L18;
            this.via_bot_id = r7.readInt64(r8);
        L18:
            if (TLObject.hasFlag(this.flags2, 1) == false) goto L21;
            this.via_business_bot_id = r7.readInt64(r8);
        L21:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L23;
            this.reply_to = TLRPC.MessageReplyHeader.TLdeserialize(r7, r7.readInt32(r8), r8);
        L23:
            this.date = r7.readInt32(r8);
            this.message = r7.readString(r8);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L33;
            TLRPC.MessageMedia r03 = TLRPC.MessageMedia.TLdeserialize(r7, r7.readInt32(r8), r8);
            this.media = r03;
            if (r03 == null) goto L28;
            this.ttl = r03.ttl_seconds;
        L28:
            if (r03 == null) goto L33;
            if (TextUtils.isEmpty(r03.captionLegacy) == true) goto L33;
            this.message = this.media.captionLegacy;
        L33:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L36;
            this.reply_markup = TLRPC.ReplyMarkup.TLdeserialize(r7, r7.readInt32(r8), r8);
        L36:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L39;
            this.entities = Vector.deserialize(r7, new MessagesStorage$$ExternalSyntheticLambda117(), r8);
        L39:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L42;
            this.views = r7.readInt32(r8);
        L42:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L45;
            this.forwards = r7.readInt32(r8);
        L45:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L48;
            this.replies = TLRPC.MessageReplies.TLdeserialize(r7, r7.readInt32(r8), r8);
        L48:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L51;
            this.edit_date = r7.readInt32(r8);
        L51:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L54;
            this.post_author = r7.readString(r8);
        L54:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L57;
            this.grouped_id = r7.readInt64(r8);
        L57:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L60;
            this.reactions = TLRPC.MessageReactions.TLdeserialize(r7, r7.readInt32(r8), r8);
        L60:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L63;
            this.restriction_reason = Vector.deserialize(r7, new TLRPC$TL_channel$$ExternalSyntheticLambda0(), r8);
        L63:
            if (TLObject.hasFlag(this.flags, 33554432) == false) goto L66;
            this.ttl_period = r7.readInt32(r8);
        L66:
            if (TLObject.hasFlag(this.flags, TLObject.FLAG_30) == false) goto L69;
            this.quick_reply_shortcut_id = r7.readInt32(r8);
        L69:
            if (TLObject.hasFlag(this.flags2, 4) == false) goto L72;
            this.effect = r7.readInt64(r8);
        L72:
            if (TLObject.hasFlag(this.flags2, 8) == false) goto L75;
            this.factcheck = TLRPC.TL_factCheck.TLdeserialize(r7, r7.readInt32(r8), r8);
        L75:
            if (TLObject.hasFlag(this.flags2, 32) == false) goto L78;
            this.report_delivery_until_date = r7.readInt32(r8);
            return;
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r8) {
            r8.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.out);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 16, this.mentioned);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 32, this.media_unread);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 8192, this.silent);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 16384, this.post);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 262144, this.from_scheduled);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 524288, this.legacy);
            this.flags = r07;
            int r08 = TLObject.setFlag(r07, TLObject.FLAG_21, this.edit_hide);
            this.flags = r08;
            int r09 = TLObject.setFlag(r08, 16777216, this.pinned);
            this.flags = r09;
            int r010 = TLObject.setFlag(r09, 67108864, this.noforwards);
            this.flags = r010;
            int r011 = TLObject.setFlag(r010, 134217728, this.invert_media);
            this.flags = r011;
            r8.writeInt32(r011);
            int r012 = TLObject.setFlag(this.flags2, 2, this.offline);
            this.flags2 = r012;
            int r013 = TLObject.setFlag(r012, 16, this.video_processing_pending);
            this.flags2 = r013;
            r8.writeInt32(r013);
            r8.writeInt32(this.id);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L6;
            this.from_id.serializeToStream(r8);
        L6:
            if (TLObject.hasFlag(this.flags, 536870912) == false) goto L8;
            r8.writeInt32(this.from_boosts_applied);
        L8:
            this.peer_id.serializeToStream(r8);
            if (TLObject.hasFlag(this.flags, 268435456) == false) goto L12;
            this.saved_peer_id.serializeToStream(r8);
        L12:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L15;
            this.fwd_from.serializeToStream(r8);
        L15:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L18;
            r8.writeInt64(this.via_bot_id);
        L18:
            if (TLObject.hasFlag(this.flags2, 1) == false) goto L21;
            r8.writeInt64(this.via_business_bot_id);
        L21:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L23;
            this.reply_to.serializeToStream(r8);
        L23:
            r8.writeInt32(this.date);
            r8.writeString(this.message);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L27;
            this.media.serializeToStream(r8);
        L27:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L30;
            this.reply_markup.serializeToStream(r8);
        L30:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L33;
            Vector.serialize(r8, this.entities);
        L33:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L36;
            r8.writeInt32(this.views);
        L36:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L39;
            r8.writeInt32(this.forwards);
        L39:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L42;
            this.replies.serializeToStream(r8);
        L42:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L45;
            r8.writeInt32(this.edit_date);
        L45:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L48;
            r8.writeString(this.post_author);
        L48:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L51;
            r8.writeInt64(this.grouped_id);
        L51:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L54;
            this.reactions.serializeToStream(r8);
        L54:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L57;
            Vector.serialize(r8, this.restriction_reason);
        L57:
            if (TLObject.hasFlag(this.flags, 33554432) == false) goto L60;
            r8.writeInt32(this.ttl_period);
        L60:
            if (TLObject.hasFlag(this.flags, TLObject.FLAG_30) == false) goto L63;
            r8.writeInt32(this.quick_reply_shortcut_id);
        L63:
            if (TLObject.hasFlag(this.flags2, 4) == false) goto L66;
            r8.writeInt64(this.effect);
        L66:
            if (TLObject.hasFlag(this.flags2, 8) == false) goto L69;
            this.factcheck.serializeToStream(r8);
        L69:
            if (TLObject.hasFlag(this.flags2, 32) == false) goto L71;
            r8.writeInt32(this.report_delivery_until_date);
        L71:
            writeAttachPath(r8);
        }
    }

    public static class TL_message_layer205 extends TLRPC.TL_message {
        public static final int constructor = -356721331;

        public TL_message_layer205() {
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r8, boolean r9) {
            int r0 = r8.readInt32(r9);
            this.flags = r0;
            this.out = TLObject.hasFlag(r0, 2);
            this.mentioned = TLObject.hasFlag(this.flags, 16);
            this.media_unread = TLObject.hasFlag(this.flags, 32);
            this.silent = TLObject.hasFlag(this.flags, 8192);
            this.post = TLObject.hasFlag(this.flags, 16384);
            this.from_scheduled = TLObject.hasFlag(this.flags, 262144);
            this.legacy = TLObject.hasFlag(this.flags, 524288);
            this.edit_hide = TLObject.hasFlag(this.flags, TLObject.FLAG_21);
            this.pinned = TLObject.hasFlag(this.flags, 16777216);
            this.noforwards = TLObject.hasFlag(this.flags, 67108864);
            this.invert_media = TLObject.hasFlag(this.flags, 134217728);
            int r02 = r8.readInt32(r9);
            this.flags2 = r02;
            this.offline = TLObject.hasFlag(r02, 2);
            this.video_processing_pending = TLObject.hasFlag(this.flags2, 16);
            this.id = r8.readInt32(r9);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L6;
            this.from_id = TLRPC.Peer.TLdeserialize(r8, r8.readInt32(r9), r9);
        L6:
            if (TLObject.hasFlag(this.flags, 536870912) == false) goto L8;
            this.from_boosts_applied = r8.readInt32(r9);
        L8:
            this.peer_id = TLRPC.Peer.TLdeserialize(r8, r8.readInt32(r9), r9);
            if (TLObject.hasFlag(this.flags, 268435456) == false) goto L12;
            this.saved_peer_id = TLRPC.Peer.TLdeserialize(r8, r8.readInt32(r9), r9);
        L12:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L15;
            this.fwd_from = TLRPC.MessageFwdHeader.TLdeserialize(r8, r8.readInt32(r9), r9);
        L15:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L18;
            this.via_bot_id = r8.readInt64(r9);
        L18:
            if (TLObject.hasFlag(this.flags2, 1) == false) goto L21;
            this.via_business_bot_id = r8.readInt64(r9);
        L21:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L23;
            this.reply_to = TLRPC.MessageReplyHeader.TLdeserialize(r8, r8.readInt32(r9), r9);
        L23:
            this.date = r8.readInt32(r9);
            this.message = r8.readString(r9);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L33;
            TLRPC.MessageMedia r03 = TLRPC.MessageMedia.TLdeserialize(r8, r8.readInt32(r9), r9);
            this.media = r03;
            if (r03 == null) goto L28;
            this.ttl = r03.ttl_seconds;
        L28:
            if (r03 == null) goto L33;
            if (TextUtils.isEmpty(r03.captionLegacy) == true) goto L33;
            this.message = this.media.captionLegacy;
        L33:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L36;
            this.reply_markup = TLRPC.ReplyMarkup.TLdeserialize(r8, r8.readInt32(r9), r9);
        L36:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L39;
            this.entities = Vector.deserialize(r8, new MessagesStorage$$ExternalSyntheticLambda117(), r9);
        L39:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L42;
            this.views = r8.readInt32(r9);
        L42:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L45;
            this.forwards = r8.readInt32(r9);
        L45:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L48;
            this.replies = TLRPC.MessageReplies.TLdeserialize(r8, r8.readInt32(r9), r9);
        L48:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L51;
            this.edit_date = r8.readInt32(r9);
        L51:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L54;
            this.post_author = r8.readString(r9);
        L54:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L57;
            this.grouped_id = r8.readInt64(r9);
        L57:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L60;
            this.reactions = TLRPC.MessageReactions.TLdeserialize(r8, r8.readInt32(r9), r9);
        L60:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L63;
            this.restriction_reason = Vector.deserialize(r8, new TLRPC$TL_channel$$ExternalSyntheticLambda0(), r9);
        L63:
            if (TLObject.hasFlag(this.flags, 33554432) == false) goto L66;
            this.ttl_period = r8.readInt32(r9);
        L66:
            if (TLObject.hasFlag(this.flags, TLObject.FLAG_30) == false) goto L69;
            this.quick_reply_shortcut_id = r8.readInt32(r9);
        L69:
            if (TLObject.hasFlag(this.flags2, 4) == false) goto L72;
            this.effect = r8.readInt64(r9);
        L72:
            if (TLObject.hasFlag(this.flags2, 8) == false) goto L75;
            this.factcheck = TLRPC.TL_factCheck.TLdeserialize(r8, r8.readInt32(r9), r9);
        L75:
            if (TLObject.hasFlag(this.flags2, 32) == false) goto L78;
            this.report_delivery_until_date = r8.readInt32(r9);
        L78:
            if (TLObject.hasFlag(this.flags2, 64) == false) goto L81;
            this.paid_message_stars = r8.readInt64(r9);
            return;
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r8) {
            r8.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.out);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 16, this.mentioned);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 32, this.media_unread);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 8192, this.silent);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 16384, this.post);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 262144, this.from_scheduled);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 524288, this.legacy);
            this.flags = r07;
            int r08 = TLObject.setFlag(r07, TLObject.FLAG_21, this.edit_hide);
            this.flags = r08;
            int r09 = TLObject.setFlag(r08, 16777216, this.pinned);
            this.flags = r09;
            int r010 = TLObject.setFlag(r09, 67108864, this.noforwards);
            this.flags = r010;
            int r011 = TLObject.setFlag(r010, 134217728, this.invert_media);
            this.flags = r011;
            r8.writeInt32(r011);
            int r012 = TLObject.setFlag(this.flags2, 2, this.offline);
            this.flags2 = r012;
            int r013 = TLObject.setFlag(r012, 16, this.video_processing_pending);
            this.flags2 = r013;
            r8.writeInt32(r013);
            r8.writeInt32(this.id);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L6;
            this.from_id.serializeToStream(r8);
        L6:
            if (TLObject.hasFlag(this.flags, 536870912) == false) goto L8;
            r8.writeInt32(this.from_boosts_applied);
        L8:
            this.peer_id.serializeToStream(r8);
            if (TLObject.hasFlag(this.flags, 268435456) == false) goto L12;
            this.saved_peer_id.serializeToStream(r8);
        L12:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L15;
            this.fwd_from.serializeToStream(r8);
        L15:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L18;
            r8.writeInt64(this.via_bot_id);
        L18:
            if (TLObject.hasFlag(this.flags2, 1) == false) goto L21;
            r8.writeInt64(this.via_business_bot_id);
        L21:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L23;
            this.reply_to.serializeToStream(r8);
        L23:
            r8.writeInt32(this.date);
            r8.writeString(this.message);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L27;
            this.media.serializeToStream(r8);
        L27:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L30;
            this.reply_markup.serializeToStream(r8);
        L30:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L33;
            Vector.serialize(r8, this.entities);
        L33:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L36;
            r8.writeInt32(this.views);
        L36:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L39;
            r8.writeInt32(this.forwards);
        L39:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L42;
            this.replies.serializeToStream(r8);
        L42:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L45;
            r8.writeInt32(this.edit_date);
        L45:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L48;
            r8.writeString(this.post_author);
        L48:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L51;
            r8.writeInt64(this.grouped_id);
        L51:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L54;
            this.reactions.serializeToStream(r8);
        L54:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L57;
            Vector.serialize(r8, this.restriction_reason);
        L57:
            if (TLObject.hasFlag(this.flags, 33554432) == false) goto L60;
            r8.writeInt32(this.ttl_period);
        L60:
            if (TLObject.hasFlag(this.flags, TLObject.FLAG_30) == false) goto L63;
            r8.writeInt32(this.quick_reply_shortcut_id);
        L63:
            if (TLObject.hasFlag(this.flags2, 4) == false) goto L66;
            r8.writeInt64(this.effect);
        L66:
            if (TLObject.hasFlag(this.flags2, 8) == false) goto L69;
            this.factcheck.serializeToStream(r8);
        L69:
            if (TLObject.hasFlag(this.flags2, 32) == false) goto L72;
            r8.writeInt32(this.report_delivery_until_date);
        L72:
            if (TLObject.hasFlag(this.flags2, 64) == false) goto L74;
            r8.writeInt64(this.paid_message_stars);
        L74:
            writeAttachPath(r8);
        }
    }

    public static class TL_message_layer216 extends TLRPC.TL_message {
        public static final int constructor = -1743401272;

        public TL_message_layer216() {
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r9, boolean r10) {
            int r0 = r9.readInt32(r10);
            this.flags = r0;
            this.out = TLObject.hasFlag(r0, 2);
            this.mentioned = TLObject.hasFlag(this.flags, 16);
            this.media_unread = TLObject.hasFlag(this.flags, 32);
            this.silent = TLObject.hasFlag(this.flags, 8192);
            this.post = TLObject.hasFlag(this.flags, 16384);
            this.from_scheduled = TLObject.hasFlag(this.flags, 262144);
            this.legacy = TLObject.hasFlag(this.flags, 524288);
            this.edit_hide = TLObject.hasFlag(this.flags, TLObject.FLAG_21);
            this.pinned = TLObject.hasFlag(this.flags, 16777216);
            this.noforwards = TLObject.hasFlag(this.flags, 67108864);
            this.invert_media = TLObject.hasFlag(this.flags, 134217728);
            int r02 = r9.readInt32(r10);
            this.flags2 = r02;
            this.offline = TLObject.hasFlag(r02, 2);
            this.video_processing_pending = TLObject.hasFlag(this.flags2, 16);
            this.paid_suggested_post_stars = TLObject.hasFlag(this.flags2, 256);
            this.paid_suggested_post_ton = TLObject.hasFlag(this.flags2, 512);
            this.id = r9.readInt32(r10);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L6;
            this.from_id = TLRPC.Peer.TLdeserialize(r9, r9.readInt32(r10), r10);
        L6:
            if (TLObject.hasFlag(this.flags, 536870912) == false) goto L8;
            this.from_boosts_applied = r9.readInt32(r10);
        L8:
            this.peer_id = TLRPC.Peer.TLdeserialize(r9, r9.readInt32(r10), r10);
            if (TLObject.hasFlag(this.flags, 268435456) == false) goto L12;
            this.saved_peer_id = TLRPC.Peer.TLdeserialize(r9, r9.readInt32(r10), r10);
        L12:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L15;
            this.fwd_from = TLRPC.MessageFwdHeader.TLdeserialize(r9, r9.readInt32(r10), r10);
        L15:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L18;
            this.via_bot_id = r9.readInt64(r10);
        L18:
            if (TLObject.hasFlag(this.flags2, 1) == false) goto L21;
            this.via_business_bot_id = r9.readInt64(r10);
        L21:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L23;
            this.reply_to = TLRPC.MessageReplyHeader.TLdeserialize(r9, r9.readInt32(r10), r10);
        L23:
            this.date = r9.readInt32(r10);
            this.message = r9.readString(r10);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L33;
            TLRPC.MessageMedia r03 = TLRPC.MessageMedia.TLdeserialize(r9, r9.readInt32(r10), r10);
            this.media = r03;
            if (r03 == null) goto L28;
            this.ttl = r03.ttl_seconds;
        L28:
            if (r03 == null) goto L33;
            if (TextUtils.isEmpty(r03.captionLegacy) == true) goto L33;
            this.message = this.media.captionLegacy;
        L33:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L36;
            this.reply_markup = TLRPC.ReplyMarkup.TLdeserialize(r9, r9.readInt32(r10), r10);
        L36:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L39;
            this.entities = Vector.deserialize(r9, new MessagesStorage$$ExternalSyntheticLambda117(), r10);
        L39:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L42;
            this.views = r9.readInt32(r10);
        L42:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L45;
            this.forwards = r9.readInt32(r10);
        L45:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L48;
            this.replies = TLRPC.MessageReplies.TLdeserialize(r9, r9.readInt32(r10), r10);
        L48:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L51;
            this.edit_date = r9.readInt32(r10);
        L51:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L54;
            this.post_author = r9.readString(r10);
        L54:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L57;
            this.grouped_id = r9.readInt64(r10);
        L57:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L60;
            this.reactions = TLRPC.MessageReactions.TLdeserialize(r9, r9.readInt32(r10), r10);
        L60:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L63;
            this.restriction_reason = Vector.deserialize(r9, new TLRPC$TL_channel$$ExternalSyntheticLambda0(), r10);
        L63:
            if (TLObject.hasFlag(this.flags, 33554432) == false) goto L66;
            this.ttl_period = r9.readInt32(r10);
        L66:
            if (TLObject.hasFlag(this.flags, TLObject.FLAG_30) == false) goto L69;
            this.quick_reply_shortcut_id = r9.readInt32(r10);
        L69:
            if (TLObject.hasFlag(this.flags2, 4) == false) goto L72;
            this.effect = r9.readInt64(r10);
        L72:
            if (TLObject.hasFlag(this.flags2, 8) == false) goto L75;
            this.factcheck = TLRPC.TL_factCheck.TLdeserialize(r9, r9.readInt32(r10), r10);
        L75:
            if (TLObject.hasFlag(this.flags2, 32) == false) goto L78;
            this.report_delivery_until_date = r9.readInt32(r10);
        L78:
            if (TLObject.hasFlag(this.flags2, 64) == false) goto L81;
            this.paid_message_stars = r9.readInt64(r10);
        L81:
            if (TLObject.hasFlag(this.flags2, 128) == false) goto L84;
            this.suggested_post = TLRPC.SuggestedPost.TLdeserialize(r9, r9.readInt32(r10), r10);
            return;
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r10) {
            r10.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.out);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 16, this.mentioned);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 32, this.media_unread);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 8192, this.silent);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 16384, this.post);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 262144, this.from_scheduled);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 524288, this.legacy);
            this.flags = r07;
            int r08 = TLObject.setFlag(r07, TLObject.FLAG_21, this.edit_hide);
            this.flags = r08;
            int r09 = TLObject.setFlag(r08, 16777216, this.pinned);
            this.flags = r09;
            int r010 = TLObject.setFlag(r09, 67108864, this.noforwards);
            this.flags = r010;
            int r011 = TLObject.setFlag(r010, 134217728, this.invert_media);
            this.flags = r011;
            r10.writeInt32(r011);
            int r012 = TLObject.setFlag(this.flags2, 2, this.offline);
            this.flags2 = r012;
            int r013 = TLObject.setFlag(r012, 16, this.video_processing_pending);
            this.flags2 = r013;
            if (this.suggested_post == null) goto L5;
            boolean r1 = true;
        L6:
            int r014 = TLObject.setFlag(r013, 128, r1);
            this.flags2 = r014;
            int r015 = TLObject.setFlag(r014, 256, this.paid_suggested_post_stars);
            this.flags2 = r015;
            int r016 = TLObject.setFlag(r015, 512, this.paid_suggested_post_ton);
            this.flags2 = r016;
            r10.writeInt32(r016);
            r10.writeInt32(this.id);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L10;
            this.from_id.serializeToStream(r10);
        L10:
            if (TLObject.hasFlag(this.flags, 536870912) == false) goto L12;
            r10.writeInt32(this.from_boosts_applied);
        L12:
            this.peer_id.serializeToStream(r10);
            if (TLObject.hasFlag(this.flags, 268435456) == false) goto L16;
            this.saved_peer_id.serializeToStream(r10);
        L16:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L19;
            this.fwd_from.serializeToStream(r10);
        L19:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L22;
            r10.writeInt64(this.via_bot_id);
        L22:
            if (TLObject.hasFlag(this.flags2, 1) == false) goto L25;
            r10.writeInt64(this.via_business_bot_id);
        L25:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L27;
            this.reply_to.serializeToStream(r10);
        L27:
            r10.writeInt32(this.date);
            r10.writeString(this.message);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L31;
            this.media.serializeToStream(r10);
        L31:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L34;
            this.reply_markup.serializeToStream(r10);
        L34:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L37;
            Vector.serialize(r10, this.entities);
        L37:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L40;
            r10.writeInt32(this.views);
        L40:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L43;
            r10.writeInt32(this.forwards);
        L43:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L46;
            this.replies.serializeToStream(r10);
        L46:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L49;
            r10.writeInt32(this.edit_date);
        L49:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L52;
            r10.writeString(this.post_author);
        L52:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L55;
            r10.writeInt64(this.grouped_id);
        L55:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L58;
            this.reactions.serializeToStream(r10);
        L58:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L61;
            Vector.serialize(r10, this.restriction_reason);
        L61:
            if (TLObject.hasFlag(this.flags, 33554432) == false) goto L64;
            r10.writeInt32(this.ttl_period);
        L64:
            if (TLObject.hasFlag(this.flags, TLObject.FLAG_30) == false) goto L67;
            r10.writeInt32(this.quick_reply_shortcut_id);
        L67:
            if (TLObject.hasFlag(this.flags2, 4) == false) goto L70;
            r10.writeInt64(this.effect);
        L70:
            if (TLObject.hasFlag(this.flags2, 8) == false) goto L73;
            this.factcheck.serializeToStream(r10);
        L73:
            if (TLObject.hasFlag(this.flags2, 32) == false) goto L76;
            r10.writeInt32(this.report_delivery_until_date);
        L76:
            if (TLObject.hasFlag(this.flags2, 64) == false) goto L79;
            r10.writeInt64(this.paid_message_stars);
        L79:
            if (TLObject.hasFlag(this.flags2, 128) == false) goto L81;
            this.suggested_post.serializeToStream(r10);
        L81:
            writeAttachPath(r10);
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_message_layer220 extends TLRPC.TL_message {
        public static final int constructor = -1188071729;

        public TL_message_layer220() {
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r10, boolean r11) {
            int r0 = r10.readInt32(r11);
            this.flags = r0;
            this.out = TLObject.hasFlag(r0, 2);
            this.mentioned = TLObject.hasFlag(this.flags, 16);
            this.media_unread = TLObject.hasFlag(this.flags, 32);
            this.silent = TLObject.hasFlag(this.flags, 8192);
            this.post = TLObject.hasFlag(this.flags, 16384);
            this.from_scheduled = TLObject.hasFlag(this.flags, 262144);
            this.legacy = TLObject.hasFlag(this.flags, 524288);
            this.edit_hide = TLObject.hasFlag(this.flags, TLObject.FLAG_21);
            this.pinned = TLObject.hasFlag(this.flags, 16777216);
            this.noforwards = TLObject.hasFlag(this.flags, 67108864);
            this.invert_media = TLObject.hasFlag(this.flags, 134217728);
            int r02 = r10.readInt32(r11);
            this.flags2 = r02;
            this.offline = TLObject.hasFlag(r02, 2);
            this.video_processing_pending = TLObject.hasFlag(this.flags2, 16);
            this.paid_suggested_post_stars = TLObject.hasFlag(this.flags2, 256);
            this.paid_suggested_post_ton = TLObject.hasFlag(this.flags2, 512);
            this.id = r10.readInt32(r11);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L6;
            this.from_id = TLRPC.Peer.TLdeserialize(r10, r10.readInt32(r11), r11);
        L6:
            if (TLObject.hasFlag(this.flags, 536870912) == false) goto L8;
            this.from_boosts_applied = r10.readInt32(r11);
        L8:
            this.peer_id = TLRPC.Peer.TLdeserialize(r10, r10.readInt32(r11), r11);
            if (TLObject.hasFlag(this.flags, 268435456) == false) goto L12;
            this.saved_peer_id = TLRPC.Peer.TLdeserialize(r10, r10.readInt32(r11), r11);
        L12:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L15;
            this.fwd_from = TLRPC.MessageFwdHeader.TLdeserialize(r10, r10.readInt32(r11), r11);
        L15:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L18;
            this.via_bot_id = r10.readInt64(r11);
        L18:
            if (TLObject.hasFlag(this.flags2, 1) == false) goto L21;
            this.via_business_bot_id = r10.readInt64(r11);
        L21:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L23;
            this.reply_to = TLRPC.MessageReplyHeader.TLdeserialize(r10, r10.readInt32(r11), r11);
        L23:
            this.date = r10.readInt32(r11);
            this.message = r10.readString(r11);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L33;
            TLRPC.MessageMedia r03 = TLRPC.MessageMedia.TLdeserialize(r10, r10.readInt32(r11), r11);
            this.media = r03;
            if (r03 == null) goto L28;
            this.ttl = r03.ttl_seconds;
        L28:
            if (r03 == null) goto L33;
            if (TextUtils.isEmpty(r03.captionLegacy) == true) goto L33;
            this.message = this.media.captionLegacy;
        L33:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L36;
            this.reply_markup = TLRPC.ReplyMarkup.TLdeserialize(r10, r10.readInt32(r11), r11);
        L36:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L39;
            this.entities = Vector.deserialize(r10, new MessagesStorage$$ExternalSyntheticLambda117(), r11);
        L39:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L42;
            this.views = r10.readInt32(r11);
        L42:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L45;
            this.forwards = r10.readInt32(r11);
        L45:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L48;
            this.replies = TLRPC.MessageReplies.TLdeserialize(r10, r10.readInt32(r11), r11);
        L48:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L51;
            this.edit_date = r10.readInt32(r11);
        L51:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L54;
            this.post_author = r10.readString(r11);
        L54:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L57;
            this.grouped_id = r10.readInt64(r11);
        L57:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L60;
            this.reactions = TLRPC.MessageReactions.TLdeserialize(r10, r10.readInt32(r11), r11);
        L60:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L63;
            this.restriction_reason = Vector.deserialize(r10, new TLRPC$TL_channel$$ExternalSyntheticLambda0(), r11);
        L63:
            if (TLObject.hasFlag(this.flags, 33554432) == false) goto L66;
            this.ttl_period = r10.readInt32(r11);
        L66:
            if (TLObject.hasFlag(this.flags, TLObject.FLAG_30) == false) goto L69;
            this.quick_reply_shortcut_id = r10.readInt32(r11);
        L69:
            if (TLObject.hasFlag(this.flags2, 4) == false) goto L72;
            this.effect = r10.readInt64(r11);
        L72:
            if (TLObject.hasFlag(this.flags2, 8) == false) goto L75;
            this.factcheck = TLRPC.TL_factCheck.TLdeserialize(r10, r10.readInt32(r11), r11);
        L75:
            if (TLObject.hasFlag(this.flags2, 32) == false) goto L78;
            this.report_delivery_until_date = r10.readInt32(r11);
        L78:
            if (TLObject.hasFlag(this.flags2, 64) == false) goto L81;
            this.paid_message_stars = r10.readInt64(r11);
        L81:
            if (TLObject.hasFlag(this.flags2, 128) == false) goto L84;
            this.suggested_post = TLRPC.SuggestedPost.TLdeserialize(r10, r10.readInt32(r11), r11);
        L84:
            if (TLObject.hasFlag(this.flags2, 1024) == false) goto L87;
            this.schedule_repeat_period = r10.readInt32(r11);
            return;
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r10) {
            r10.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.out);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 16, this.mentioned);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 32, this.media_unread);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 8192, this.silent);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 16384, this.post);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 262144, this.from_scheduled);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 524288, this.legacy);
            this.flags = r07;
            int r08 = TLObject.setFlag(r07, TLObject.FLAG_21, this.edit_hide);
            this.flags = r08;
            int r09 = TLObject.setFlag(r08, 16777216, this.pinned);
            this.flags = r09;
            int r010 = TLObject.setFlag(r09, 67108864, this.noforwards);
            this.flags = r010;
            int r011 = TLObject.setFlag(r010, 134217728, this.invert_media);
            this.flags = r011;
            r10.writeInt32(r011);
            int r012 = TLObject.setFlag(this.flags2, 2, this.offline);
            this.flags2 = r012;
            int r013 = TLObject.setFlag(r012, 16, this.video_processing_pending);
            this.flags2 = r013;
            if (this.suggested_post == null) goto L5;
            boolean r1 = true;
        L6:
            int r014 = TLObject.setFlag(r013, 128, r1);
            this.flags2 = r014;
            int r015 = TLObject.setFlag(r014, 256, this.paid_suggested_post_stars);
            this.flags2 = r015;
            int r016 = TLObject.setFlag(r015, 512, this.paid_suggested_post_ton);
            this.flags2 = r016;
            r10.writeInt32(r016);
            r10.writeInt32(this.id);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L10;
            this.from_id.serializeToStream(r10);
        L10:
            if (TLObject.hasFlag(this.flags, 536870912) == false) goto L12;
            r10.writeInt32(this.from_boosts_applied);
        L12:
            this.peer_id.serializeToStream(r10);
            if (TLObject.hasFlag(this.flags, 268435456) == false) goto L16;
            this.saved_peer_id.serializeToStream(r10);
        L16:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L19;
            this.fwd_from.serializeToStream(r10);
        L19:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L22;
            r10.writeInt64(this.via_bot_id);
        L22:
            if (TLObject.hasFlag(this.flags2, 1) == false) goto L25;
            r10.writeInt64(this.via_business_bot_id);
        L25:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L27;
            this.reply_to.serializeToStream(r10);
        L27:
            r10.writeInt32(this.date);
            r10.writeString(this.message);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L31;
            this.media.serializeToStream(r10);
        L31:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L34;
            this.reply_markup.serializeToStream(r10);
        L34:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L37;
            Vector.serialize(r10, this.entities);
        L37:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L40;
            r10.writeInt32(this.views);
        L40:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L43;
            r10.writeInt32(this.forwards);
        L43:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L46;
            this.replies.serializeToStream(r10);
        L46:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L49;
            r10.writeInt32(this.edit_date);
        L49:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L52;
            r10.writeString(this.post_author);
        L52:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L55;
            r10.writeInt64(this.grouped_id);
        L55:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L58;
            this.reactions.serializeToStream(r10);
        L58:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L61;
            Vector.serialize(r10, this.restriction_reason);
        L61:
            if (TLObject.hasFlag(this.flags, 33554432) == false) goto L64;
            r10.writeInt32(this.ttl_period);
        L64:
            if (TLObject.hasFlag(this.flags, TLObject.FLAG_30) == false) goto L67;
            r10.writeInt32(this.quick_reply_shortcut_id);
        L67:
            if (TLObject.hasFlag(this.flags2, 4) == false) goto L70;
            r10.writeInt64(this.effect);
        L70:
            if (TLObject.hasFlag(this.flags2, 8) == false) goto L73;
            this.factcheck.serializeToStream(r10);
        L73:
            if (TLObject.hasFlag(this.flags2, 32) == false) goto L76;
            r10.writeInt32(this.report_delivery_until_date);
        L76:
            if (TLObject.hasFlag(this.flags2, 64) == false) goto L79;
            r10.writeInt64(this.paid_message_stars);
        L79:
            if (TLObject.hasFlag(this.flags2, 128) == false) goto L82;
            this.suggested_post.serializeToStream(r10);
        L82:
            if (TLObject.hasFlag(this.flags2, 1024) == false) goto L84;
            r10.writeInt32(this.schedule_repeat_period);
        L84:
            writeAttachPath(r10);
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_message_layer222 extends TLRPC.TL_message {
        public static final int constructor = -1665888023;

        public TL_message_layer222() {
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r11, boolean r12) {
            int r0 = r11.readInt32(r12);
            this.flags = r0;
            this.out = TLObject.hasFlag(r0, 2);
            this.mentioned = TLObject.hasFlag(this.flags, 16);
            this.media_unread = TLObject.hasFlag(this.flags, 32);
            this.silent = TLObject.hasFlag(this.flags, 8192);
            this.post = TLObject.hasFlag(this.flags, 16384);
            this.from_scheduled = TLObject.hasFlag(this.flags, 262144);
            this.legacy = TLObject.hasFlag(this.flags, 524288);
            this.edit_hide = TLObject.hasFlag(this.flags, TLObject.FLAG_21);
            this.pinned = TLObject.hasFlag(this.flags, 16777216);
            this.noforwards = TLObject.hasFlag(this.flags, 67108864);
            this.invert_media = TLObject.hasFlag(this.flags, 134217728);
            int r02 = r11.readInt32(r12);
            this.flags2 = r02;
            this.offline = TLObject.hasFlag(r02, 2);
            this.video_processing_pending = TLObject.hasFlag(this.flags2, 16);
            this.paid_suggested_post_stars = TLObject.hasFlag(this.flags2, 256);
            this.paid_suggested_post_ton = TLObject.hasFlag(this.flags2, 512);
            this.id = r11.readInt32(r12);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L6;
            this.from_id = TLRPC.Peer.TLdeserialize(r11, r11.readInt32(r12), r12);
        L6:
            if (TLObject.hasFlag(this.flags, 536870912) == false) goto L8;
            this.from_boosts_applied = r11.readInt32(r12);
        L8:
            this.peer_id = TLRPC.Peer.TLdeserialize(r11, r11.readInt32(r12), r12);
            if (TLObject.hasFlag(this.flags, 268435456) == false) goto L12;
            this.saved_peer_id = TLRPC.Peer.TLdeserialize(r11, r11.readInt32(r12), r12);
        L12:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L15;
            this.fwd_from = TLRPC.MessageFwdHeader.TLdeserialize(r11, r11.readInt32(r12), r12);
        L15:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L18;
            this.via_bot_id = r11.readInt64(r12);
        L18:
            if (TLObject.hasFlag(this.flags2, 1) == false) goto L21;
            this.via_business_bot_id = r11.readInt64(r12);
        L21:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L23;
            this.reply_to = TLRPC.MessageReplyHeader.TLdeserialize(r11, r11.readInt32(r12), r12);
        L23:
            this.date = r11.readInt32(r12);
            this.message = r11.readString(r12);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L33;
            TLRPC.MessageMedia r03 = TLRPC.MessageMedia.TLdeserialize(r11, r11.readInt32(r12), r12);
            this.media = r03;
            if (r03 == null) goto L28;
            this.ttl = r03.ttl_seconds;
        L28:
            if (r03 == null) goto L33;
            if (TextUtils.isEmpty(r03.captionLegacy) == true) goto L33;
            this.message = this.media.captionLegacy;
        L33:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L36;
            this.reply_markup = TLRPC.ReplyMarkup.TLdeserialize(r11, r11.readInt32(r12), r12);
        L36:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L39;
            this.entities = Vector.deserialize(r11, new MessagesStorage$$ExternalSyntheticLambda117(), r12);
        L39:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L42;
            this.views = r11.readInt32(r12);
        L42:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L45;
            this.forwards = r11.readInt32(r12);
        L45:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L48;
            this.replies = TLRPC.MessageReplies.TLdeserialize(r11, r11.readInt32(r12), r12);
        L48:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L51;
            this.edit_date = r11.readInt32(r12);
        L51:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L54;
            this.post_author = r11.readString(r12);
        L54:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L57;
            this.grouped_id = r11.readInt64(r12);
        L57:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L60;
            this.reactions = TLRPC.MessageReactions.TLdeserialize(r11, r11.readInt32(r12), r12);
        L60:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L63;
            this.restriction_reason = Vector.deserialize(r11, new TLRPC$TL_channel$$ExternalSyntheticLambda0(), r12);
        L63:
            if (TLObject.hasFlag(this.flags, 33554432) == false) goto L66;
            this.ttl_period = r11.readInt32(r12);
        L66:
            if (TLObject.hasFlag(this.flags, TLObject.FLAG_30) == false) goto L69;
            this.quick_reply_shortcut_id = r11.readInt32(r12);
        L69:
            if (TLObject.hasFlag(this.flags2, 4) == false) goto L72;
            this.effect = r11.readInt64(r12);
        L72:
            if (TLObject.hasFlag(this.flags2, 8) == false) goto L75;
            this.factcheck = TLRPC.TL_factCheck.TLdeserialize(r11, r11.readInt32(r12), r12);
        L75:
            if (TLObject.hasFlag(this.flags2, 32) == false) goto L78;
            this.report_delivery_until_date = r11.readInt32(r12);
        L78:
            if (TLObject.hasFlag(this.flags2, 64) == false) goto L81;
            this.paid_message_stars = r11.readInt64(r12);
        L81:
            if (TLObject.hasFlag(this.flags2, 128) == false) goto L84;
            this.suggested_post = TLRPC.SuggestedPost.TLdeserialize(r11, r11.readInt32(r12), r12);
        L84:
            if (TLObject.hasFlag(this.flags2, 1024) == false) goto L87;
            this.schedule_repeat_period = r11.readInt32(r12);
        L87:
            if (TLObject.hasFlag(this.flags2, 2048) == false) goto L90;
            this.summary_from_language = r11.readString(r12);
            return;
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r11) {
            r11.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.out);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 16, this.mentioned);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 32, this.media_unread);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 8192, this.silent);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 16384, this.post);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 262144, this.from_scheduled);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 524288, this.legacy);
            this.flags = r07;
            int r08 = TLObject.setFlag(r07, TLObject.FLAG_21, this.edit_hide);
            this.flags = r08;
            int r09 = TLObject.setFlag(r08, 16777216, this.pinned);
            this.flags = r09;
            int r010 = TLObject.setFlag(r09, 67108864, this.noforwards);
            this.flags = r010;
            int r011 = TLObject.setFlag(r010, 134217728, this.invert_media);
            this.flags = r011;
            r11.writeInt32(r011);
            int r012 = TLObject.setFlag(this.flags2, 2, this.offline);
            this.flags2 = r012;
            int r013 = TLObject.setFlag(r012, 16, this.video_processing_pending);
            this.flags2 = r013;
            if (this.suggested_post == null) goto L5;
            boolean r1 = true;
        L6:
            int r014 = TLObject.setFlag(r013, 128, r1);
            this.flags2 = r014;
            int r015 = TLObject.setFlag(r014, 256, this.paid_suggested_post_stars);
            this.flags2 = r015;
            int r016 = TLObject.setFlag(r015, 512, this.paid_suggested_post_ton);
            this.flags2 = r016;
            r11.writeInt32(r016);
            r11.writeInt32(this.id);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L10;
            this.from_id.serializeToStream(r11);
        L10:
            if (TLObject.hasFlag(this.flags, 536870912) == false) goto L12;
            r11.writeInt32(this.from_boosts_applied);
        L12:
            this.peer_id.serializeToStream(r11);
            if (TLObject.hasFlag(this.flags, 268435456) == false) goto L16;
            this.saved_peer_id.serializeToStream(r11);
        L16:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L19;
            this.fwd_from.serializeToStream(r11);
        L19:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L22;
            r11.writeInt64(this.via_bot_id);
        L22:
            if (TLObject.hasFlag(this.flags2, 1) == false) goto L25;
            r11.writeInt64(this.via_business_bot_id);
        L25:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L27;
            this.reply_to.serializeToStream(r11);
        L27:
            r11.writeInt32(this.date);
            r11.writeString(this.message);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L31;
            this.media.serializeToStream(r11);
        L31:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L34;
            this.reply_markup.serializeToStream(r11);
        L34:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L37;
            Vector.serialize(r11, this.entities);
        L37:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L40;
            r11.writeInt32(this.views);
        L40:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L43;
            r11.writeInt32(this.forwards);
        L43:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L46;
            this.replies.serializeToStream(r11);
        L46:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L49;
            r11.writeInt32(this.edit_date);
        L49:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L52;
            r11.writeString(this.post_author);
        L52:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L55;
            r11.writeInt64(this.grouped_id);
        L55:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L58;
            this.reactions.serializeToStream(r11);
        L58:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L61;
            Vector.serialize(r11, this.restriction_reason);
        L61:
            if (TLObject.hasFlag(this.flags, 33554432) == false) goto L64;
            r11.writeInt32(this.ttl_period);
        L64:
            if (TLObject.hasFlag(this.flags, TLObject.FLAG_30) == false) goto L67;
            r11.writeInt32(this.quick_reply_shortcut_id);
        L67:
            if (TLObject.hasFlag(this.flags2, 4) == false) goto L70;
            r11.writeInt64(this.effect);
        L70:
            if (TLObject.hasFlag(this.flags2, 8) == false) goto L73;
            this.factcheck.serializeToStream(r11);
        L73:
            if (TLObject.hasFlag(this.flags2, 32) == false) goto L76;
            r11.writeInt32(this.report_delivery_until_date);
        L76:
            if (TLObject.hasFlag(this.flags2, 64) == false) goto L79;
            r11.writeInt64(this.paid_message_stars);
        L79:
            if (TLObject.hasFlag(this.flags2, 128) == false) goto L82;
            this.suggested_post.serializeToStream(r11);
        L82:
            if (TLObject.hasFlag(this.flags2, 1024) == false) goto L85;
            r11.writeInt32(this.schedule_repeat_period);
        L85:
            if (TLObject.hasFlag(this.flags2, 2048) == false) goto L87;
            r11.writeString(this.summary_from_language);
        L87:
            writeAttachPath(r11);
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_message_layer224 extends TLRPC.TL_message {
        public static final int constructor = 988112002;

        public TL_message_layer224() {
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r11, boolean r12) {
            int r0 = r11.readInt32(r12);
            this.flags = r0;
            this.out = TLObject.hasFlag(r0, 2);
            this.mentioned = TLObject.hasFlag(this.flags, 16);
            this.media_unread = TLObject.hasFlag(this.flags, 32);
            this.silent = TLObject.hasFlag(this.flags, 8192);
            this.post = TLObject.hasFlag(this.flags, 16384);
            this.from_scheduled = TLObject.hasFlag(this.flags, 262144);
            this.legacy = TLObject.hasFlag(this.flags, 524288);
            this.edit_hide = TLObject.hasFlag(this.flags, TLObject.FLAG_21);
            this.pinned = TLObject.hasFlag(this.flags, 16777216);
            this.noforwards = TLObject.hasFlag(this.flags, 67108864);
            this.invert_media = TLObject.hasFlag(this.flags, 134217728);
            int r02 = r11.readInt32(r12);
            this.flags2 = r02;
            this.offline = TLObject.hasFlag(r02, 2);
            this.video_processing_pending = TLObject.hasFlag(this.flags2, 16);
            this.paid_suggested_post_stars = TLObject.hasFlag(this.flags2, 256);
            this.paid_suggested_post_ton = TLObject.hasFlag(this.flags2, 512);
            this.id = r11.readInt32(r12);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L6;
            this.from_id = TLRPC.Peer.TLdeserialize(r11, r11.readInt32(r12), r12);
        L6:
            if (TLObject.hasFlag(this.flags, 536870912) == false) goto L9;
            this.from_boosts_applied = r11.readInt32(r12);
        L9:
            if (TLObject.hasFlag(this.flags2, 4096) == false) goto L11;
            this.from_rank = r11.readString(r12);
        L11:
            this.peer_id = TLRPC.Peer.TLdeserialize(r11, r11.readInt32(r12), r12);
            if (TLObject.hasFlag(this.flags, 268435456) == false) goto L15;
            this.saved_peer_id = TLRPC.Peer.TLdeserialize(r11, r11.readInt32(r12), r12);
        L15:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L18;
            this.fwd_from = TLRPC.MessageFwdHeader.TLdeserialize(r11, r11.readInt32(r12), r12);
        L18:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L21;
            this.via_bot_id = r11.readInt64(r12);
        L21:
            if (TLObject.hasFlag(this.flags2, 1) == false) goto L24;
            this.via_business_bot_id = r11.readInt64(r12);
        L24:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L26;
            this.reply_to = TLRPC.MessageReplyHeader.TLdeserialize(r11, r11.readInt32(r12), r12);
        L26:
            this.date = r11.readInt32(r12);
            this.message = r11.readString(r12);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L36;
            TLRPC.MessageMedia r03 = TLRPC.MessageMedia.TLdeserialize(r11, r11.readInt32(r12), r12);
            this.media = r03;
            if (r03 == null) goto L31;
            this.ttl = r03.ttl_seconds;
        L31:
            if (r03 == null) goto L36;
            if (TextUtils.isEmpty(r03.captionLegacy) == true) goto L36;
            this.message = this.media.captionLegacy;
        L36:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L39;
            this.reply_markup = TLRPC.ReplyMarkup.TLdeserialize(r11, r11.readInt32(r12), r12);
        L39:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L42;
            this.entities = Vector.deserialize(r11, new MessagesStorage$$ExternalSyntheticLambda117(), r12);
        L42:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L45;
            this.views = r11.readInt32(r12);
        L45:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L48;
            this.forwards = r11.readInt32(r12);
        L48:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L51;
            this.replies = TLRPC.MessageReplies.TLdeserialize(r11, r11.readInt32(r12), r12);
        L51:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L54;
            this.edit_date = r11.readInt32(r12);
        L54:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L57;
            this.post_author = r11.readString(r12);
        L57:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L60;
            this.grouped_id = r11.readInt64(r12);
        L60:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L63;
            this.reactions = TLRPC.MessageReactions.TLdeserialize(r11, r11.readInt32(r12), r12);
        L63:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L66;
            this.restriction_reason = Vector.deserialize(r11, new TLRPC$TL_channel$$ExternalSyntheticLambda0(), r12);
        L66:
            if (TLObject.hasFlag(this.flags, 33554432) == false) goto L69;
            this.ttl_period = r11.readInt32(r12);
        L69:
            if (TLObject.hasFlag(this.flags, TLObject.FLAG_30) == false) goto L72;
            this.quick_reply_shortcut_id = r11.readInt32(r12);
        L72:
            if (TLObject.hasFlag(this.flags2, 4) == false) goto L75;
            this.effect = r11.readInt64(r12);
        L75:
            if (TLObject.hasFlag(this.flags2, 8) == false) goto L78;
            this.factcheck = TLRPC.TL_factCheck.TLdeserialize(r11, r11.readInt32(r12), r12);
        L78:
            if (TLObject.hasFlag(this.flags2, 32) == false) goto L81;
            this.report_delivery_until_date = r11.readInt32(r12);
        L81:
            if (TLObject.hasFlag(this.flags2, 64) == false) goto L84;
            this.paid_message_stars = r11.readInt64(r12);
        L84:
            if (TLObject.hasFlag(this.flags2, 128) == false) goto L87;
            this.suggested_post = TLRPC.SuggestedPost.TLdeserialize(r11, r11.readInt32(r12), r12);
        L87:
            if (TLObject.hasFlag(this.flags2, 1024) == false) goto L90;
            this.schedule_repeat_period = r11.readInt32(r12);
        L90:
            if (TLObject.hasFlag(this.flags2, 2048) == false) goto L93;
            this.summary_from_language = r11.readString(r12);
            return;
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r11) {
            r11.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.out);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 16, this.mentioned);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 32, this.media_unread);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 8192, this.silent);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 16384, this.post);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 262144, this.from_scheduled);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 524288, this.legacy);
            this.flags = r07;
            int r08 = TLObject.setFlag(r07, TLObject.FLAG_21, this.edit_hide);
            this.flags = r08;
            int r09 = TLObject.setFlag(r08, 16777216, this.pinned);
            this.flags = r09;
            int r010 = TLObject.setFlag(r09, 67108864, this.noforwards);
            this.flags = r010;
            int r011 = TLObject.setFlag(r010, 134217728, this.invert_media);
            this.flags = r011;
            r11.writeInt32(r011);
            int r012 = TLObject.setFlag(this.flags2, 2, this.offline);
            this.flags2 = r012;
            int r013 = TLObject.setFlag(r012, 16, this.video_processing_pending);
            this.flags2 = r013;
            boolean r2 = false;
            if (this.suggested_post == null) goto L5;
            boolean r1 = true;
        L6:
            int r014 = TLObject.setFlag(r013, 128, r1);
            this.flags2 = r014;
            int r015 = TLObject.setFlag(r014, 256, this.paid_suggested_post_stars);
            this.flags2 = r015;
            int r016 = TLObject.setFlag(r015, 512, this.paid_suggested_post_ton);
            this.flags2 = r016;
            if (this.from_rank == null) goto L9;
            r2 = true;
        L9:
            int r017 = TLObject.setFlag(r016, 4096, r2);
            this.flags2 = r017;
            r11.writeInt32(r017);
            r11.writeInt32(this.id);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L13;
            this.from_id.serializeToStream(r11);
        L13:
            if (TLObject.hasFlag(this.flags, 536870912) == false) goto L16;
            r11.writeInt32(this.from_boosts_applied);
        L16:
            if (TLObject.hasFlag(this.flags2, 4096) == false) goto L18;
            r11.writeString(this.from_rank);
        L18:
            this.peer_id.serializeToStream(r11);
            if (TLObject.hasFlag(this.flags, 268435456) == false) goto L22;
            this.saved_peer_id.serializeToStream(r11);
        L22:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L25;
            this.fwd_from.serializeToStream(r11);
        L25:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L28;
            r11.writeInt64(this.via_bot_id);
        L28:
            if (TLObject.hasFlag(this.flags2, 1) == false) goto L31;
            r11.writeInt64(this.via_business_bot_id);
        L31:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L33;
            this.reply_to.serializeToStream(r11);
        L33:
            r11.writeInt32(this.date);
            r11.writeString(this.message);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L37;
            this.media.serializeToStream(r11);
        L37:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L40;
            this.reply_markup.serializeToStream(r11);
        L40:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L43;
            Vector.serialize(r11, this.entities);
        L43:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L46;
            r11.writeInt32(this.views);
        L46:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L49;
            r11.writeInt32(this.forwards);
        L49:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L52;
            this.replies.serializeToStream(r11);
        L52:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L55;
            r11.writeInt32(this.edit_date);
        L55:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L58;
            r11.writeString(this.post_author);
        L58:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L61;
            r11.writeInt64(this.grouped_id);
        L61:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L64;
            this.reactions.serializeToStream(r11);
        L64:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L67;
            Vector.serialize(r11, this.restriction_reason);
        L67:
            if (TLObject.hasFlag(this.flags, 33554432) == false) goto L70;
            r11.writeInt32(this.ttl_period);
        L70:
            if (TLObject.hasFlag(this.flags, TLObject.FLAG_30) == false) goto L73;
            r11.writeInt32(this.quick_reply_shortcut_id);
        L73:
            if (TLObject.hasFlag(this.flags2, 4) == false) goto L76;
            r11.writeInt64(this.effect);
        L76:
            if (TLObject.hasFlag(this.flags2, 8) == false) goto L79;
            this.factcheck.serializeToStream(r11);
        L79:
            if (TLObject.hasFlag(this.flags2, 32) == false) goto L82;
            r11.writeInt32(this.report_delivery_until_date);
        L82:
            if (TLObject.hasFlag(this.flags2, 64) == false) goto L85;
            r11.writeInt64(this.paid_message_stars);
        L85:
            if (TLObject.hasFlag(this.flags2, 128) == false) goto L88;
            this.suggested_post.serializeToStream(r11);
        L88:
            if (TLObject.hasFlag(this.flags2, 1024) == false) goto L91;
            r11.writeInt32(this.schedule_repeat_period);
        L91:
            if (TLObject.hasFlag(this.flags2, 2048) == false) goto L93;
            r11.writeString(this.summary_from_language);
        L93:
            writeAttachPath(r11);
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_message_layer226 extends TLRPC.TL_message {
        public static final int constructor = -1779470549;

        public TL_message_layer226() {
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r11, boolean r12) {
            int r0 = r11.readInt32(r12);
            this.flags = r0;
            this.out = TLObject.hasFlag(r0, 2);
            this.mentioned = TLObject.hasFlag(this.flags, 16);
            this.media_unread = TLObject.hasFlag(this.flags, 32);
            this.silent = TLObject.hasFlag(this.flags, 8192);
            this.post = TLObject.hasFlag(this.flags, 16384);
            this.from_scheduled = TLObject.hasFlag(this.flags, 262144);
            this.legacy = TLObject.hasFlag(this.flags, 524288);
            this.edit_hide = TLObject.hasFlag(this.flags, TLObject.FLAG_21);
            this.pinned = TLObject.hasFlag(this.flags, 16777216);
            this.noforwards = TLObject.hasFlag(this.flags, 67108864);
            this.invert_media = TLObject.hasFlag(this.flags, 134217728);
            int r02 = r11.readInt32(r12);
            this.flags2 = r02;
            this.offline = TLObject.hasFlag(r02, 2);
            this.video_processing_pending = TLObject.hasFlag(this.flags2, 16);
            this.paid_suggested_post_stars = TLObject.hasFlag(this.flags2, 256);
            this.paid_suggested_post_ton = TLObject.hasFlag(this.flags2, 512);
            this.id = r11.readInt32(r12);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L6;
            this.from_id = TLRPC.Peer.TLdeserialize(r11, r11.readInt32(r12), r12);
        L6:
            if (TLObject.hasFlag(this.flags, 536870912) == false) goto L9;
            this.from_boosts_applied = r11.readInt32(r12);
        L9:
            if (TLObject.hasFlag(this.flags2, 4096) == false) goto L11;
            this.from_rank = r11.readString(r12);
        L11:
            this.peer_id = TLRPC.Peer.TLdeserialize(r11, r11.readInt32(r12), r12);
            if (TLObject.hasFlag(this.flags, 268435456) == false) goto L15;
            this.saved_peer_id = TLRPC.Peer.TLdeserialize(r11, r11.readInt32(r12), r12);
        L15:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L18;
            this.fwd_from = TLRPC.MessageFwdHeader.TLdeserialize(r11, r11.readInt32(r12), r12);
        L18:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L21;
            this.via_bot_id = r11.readInt64(r12);
        L21:
            if (TLObject.hasFlag(this.flags2, 1) == false) goto L24;
            this.via_business_bot_id = r11.readInt64(r12);
        L24:
            if (TLObject.hasFlag(this.flags2, 524288) == false) goto L27;
            this.guestchat_via_from = TLRPC.Peer.TLdeserialize(r11, r11.readInt32(r12), r12);
        L27:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L29;
            this.reply_to = TLRPC.MessageReplyHeader.TLdeserialize(r11, r11.readInt32(r12), r12);
        L29:
            this.date = r11.readInt32(r12);
            this.message = r11.readString(r12);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L39;
            TLRPC.MessageMedia r03 = TLRPC.MessageMedia.TLdeserialize(r11, r11.readInt32(r12), r12);
            this.media = r03;
            if (r03 == null) goto L34;
            this.ttl = r03.ttl_seconds;
        L34:
            if (r03 == null) goto L39;
            if (TextUtils.isEmpty(r03.captionLegacy) == true) goto L39;
            this.message = this.media.captionLegacy;
        L39:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L42;
            this.reply_markup = TLRPC.ReplyMarkup.TLdeserialize(r11, r11.readInt32(r12), r12);
        L42:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L45;
            this.entities = Vector.deserialize(r11, new MessagesStorage$$ExternalSyntheticLambda117(), r12);
        L45:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L48;
            this.views = r11.readInt32(r12);
        L48:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L51;
            this.forwards = r11.readInt32(r12);
        L51:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L54;
            this.replies = TLRPC.MessageReplies.TLdeserialize(r11, r11.readInt32(r12), r12);
        L54:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L57;
            this.edit_date = r11.readInt32(r12);
        L57:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L60;
            this.post_author = r11.readString(r12);
        L60:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L63;
            this.grouped_id = r11.readInt64(r12);
        L63:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L66;
            this.reactions = TLRPC.MessageReactions.TLdeserialize(r11, r11.readInt32(r12), r12);
        L66:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L69;
            this.restriction_reason = Vector.deserialize(r11, new TLRPC$TL_channel$$ExternalSyntheticLambda0(), r12);
        L69:
            if (TLObject.hasFlag(this.flags, 33554432) == false) goto L72;
            this.ttl_period = r11.readInt32(r12);
        L72:
            if (TLObject.hasFlag(this.flags, TLObject.FLAG_30) == false) goto L75;
            this.quick_reply_shortcut_id = r11.readInt32(r12);
        L75:
            if (TLObject.hasFlag(this.flags2, 4) == false) goto L78;
            this.effect = r11.readInt64(r12);
        L78:
            if (TLObject.hasFlag(this.flags2, 8) == false) goto L81;
            this.factcheck = TLRPC.TL_factCheck.TLdeserialize(r11, r11.readInt32(r12), r12);
        L81:
            if (TLObject.hasFlag(this.flags2, 32) == false) goto L84;
            this.report_delivery_until_date = r11.readInt32(r12);
        L84:
            if (TLObject.hasFlag(this.flags2, 64) == false) goto L87;
            this.paid_message_stars = r11.readInt64(r12);
        L87:
            if (TLObject.hasFlag(this.flags2, 128) == false) goto L90;
            this.suggested_post = TLRPC.SuggestedPost.TLdeserialize(r11, r11.readInt32(r12), r12);
        L90:
            if (TLObject.hasFlag(this.flags2, 1024) == false) goto L93;
            this.schedule_repeat_period = r11.readInt32(r12);
        L93:
            if (TLObject.hasFlag(this.flags2, 2048) == false) goto L96;
            this.summary_from_language = r11.readString(r12);
            return;
        }

        @Override // org.telegram.tgnet.TLRPC.TL_message, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r12) {
            r12.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.out);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 16, this.mentioned);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 32, this.media_unread);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 8192, this.silent);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 16384, this.post);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 262144, this.from_scheduled);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 524288, this.legacy);
            this.flags = r07;
            int r08 = TLObject.setFlag(r07, TLObject.FLAG_21, this.edit_hide);
            this.flags = r08;
            int r09 = TLObject.setFlag(r08, 16777216, this.pinned);
            this.flags = r09;
            int r010 = TLObject.setFlag(r09, 67108864, this.noforwards);
            this.flags = r010;
            int r011 = TLObject.setFlag(r010, 134217728, this.invert_media);
            this.flags = r011;
            r12.writeInt32(r011);
            int r012 = TLObject.setFlag(this.flags2, 2, this.offline);
            this.flags2 = r012;
            int r013 = TLObject.setFlag(r012, 16, this.video_processing_pending);
            this.flags2 = r013;
            boolean r2 = false;
            if (this.suggested_post == null) goto L5;
            boolean r1 = true;
        L6:
            int r014 = TLObject.setFlag(r013, 128, r1);
            this.flags2 = r014;
            int r015 = TLObject.setFlag(r014, 256, this.paid_suggested_post_stars);
            this.flags2 = r015;
            int r016 = TLObject.setFlag(r015, 512, this.paid_suggested_post_ton);
            this.flags2 = r016;
            if (this.from_rank == null) goto L9;
            boolean r13 = true;
        L10:
            int r017 = TLObject.setFlag(r016, 4096, r13);
            this.flags2 = r017;
            if (this.guestchat_via_from == null) goto L13;
            r2 = true;
        L13:
            int r018 = TLObject.setFlag(r017, 524288, r2);
            this.flags2 = r018;
            r12.writeInt32(r018);
            r12.writeInt32(this.id);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L17;
            this.from_id.serializeToStream(r12);
        L17:
            if (TLObject.hasFlag(this.flags, 536870912) == false) goto L20;
            r12.writeInt32(this.from_boosts_applied);
        L20:
            if (TLObject.hasFlag(this.flags2, 4096) == false) goto L22;
            r12.writeString(this.from_rank);
        L22:
            this.peer_id.serializeToStream(r12);
            if (TLObject.hasFlag(this.flags, 268435456) == false) goto L26;
            this.saved_peer_id.serializeToStream(r12);
        L26:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L29;
            this.fwd_from.serializeToStream(r12);
        L29:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L32;
            r12.writeInt64(this.via_bot_id);
        L32:
            if (TLObject.hasFlag(this.flags2, 1) == false) goto L35;
            r12.writeInt64(this.via_business_bot_id);
        L35:
            if (TLObject.hasFlag(this.flags2, 524288) == false) goto L38;
            this.guestchat_via_from.serializeToStream(r12);
        L38:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L40;
            this.reply_to.serializeToStream(r12);
        L40:
            r12.writeInt32(this.date);
            r12.writeString(this.message);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L44;
            this.media.serializeToStream(r12);
        L44:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L47;
            this.reply_markup.serializeToStream(r12);
        L47:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L50;
            Vector.serialize(r12, this.entities);
        L50:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L53;
            r12.writeInt32(this.views);
        L53:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L56;
            r12.writeInt32(this.forwards);
        L56:
            if (TLObject.hasFlag(this.flags, 8388608) == false) goto L59;
            this.replies.serializeToStream(r12);
        L59:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L62;
            r12.writeInt32(this.edit_date);
        L62:
            if (TLObject.hasFlag(this.flags, 65536) == false) goto L65;
            r12.writeString(this.post_author);
        L65:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L68;
            r12.writeInt64(this.grouped_id);
        L68:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L71;
            this.reactions.serializeToStream(r12);
        L71:
            if (TLObject.hasFlag(this.flags, 4194304) == false) goto L74;
            Vector.serialize(r12, this.restriction_reason);
        L74:
            if (TLObject.hasFlag(this.flags, 33554432) == false) goto L77;
            r12.writeInt32(this.ttl_period);
        L77:
            if (TLObject.hasFlag(this.flags, TLObject.FLAG_30) == false) goto L80;
            r12.writeInt32(this.quick_reply_shortcut_id);
        L80:
            if (TLObject.hasFlag(this.flags2, 4) == false) goto L83;
            r12.writeInt64(this.effect);
        L83:
            if (TLObject.hasFlag(this.flags2, 8) == false) goto L86;
            this.factcheck.serializeToStream(r12);
        L86:
            if (TLObject.hasFlag(this.flags2, 32) == false) goto L89;
            r12.writeInt32(this.report_delivery_until_date);
        L89:
            if (TLObject.hasFlag(this.flags2, 64) == false) goto L92;
            r12.writeInt64(this.paid_message_stars);
        L92:
            if (TLObject.hasFlag(this.flags2, 128) == false) goto L95;
            this.suggested_post.serializeToStream(r12);
        L95:
            if (TLObject.hasFlag(this.flags2, 1024) == false) goto L98;
            r12.writeInt32(this.schedule_repeat_period);
        L98:
            if (TLObject.hasFlag(this.flags2, 2048) == false) goto L100;
            r12.writeString(this.summary_from_language);
        L100:
            writeAttachPath(r12);
            return;
        L9:
            r13 = false;
            goto L10
        L5:
            r1 = false;
            goto L6
        }
    }

    private TL_legacy_message() {
    }
}
