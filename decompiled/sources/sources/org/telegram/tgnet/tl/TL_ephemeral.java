package org.telegram.tgnet.tl;

import java.util.ArrayList;
import org.telegram.messenger.MessagesStorage$$ExternalSyntheticLambda117;
import org.telegram.messenger.MessagesStorage$$ExternalSyntheticLambda84;
import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.TLMethod;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_iv;

/* loaded from: classes6.dex */
public class TL_ephemeral {

    public static abstract class EphemeralMessage extends TLObject {
        public int anchor_msg_id;
        public long chat_instance;
        public int date;
        public ArrayList<TLRPC.MessageEntity> entities;
        public int flags;
        public TLRPC.Peer from_id;
        public int id;
        public boolean invert_media;
        public TLRPC.MessageMedia media;
        public String message;
        public boolean noforwards;
        public boolean out;
        public TLRPC.Peer peer_id;
        public long receiver_id;
        public TLRPC.ReplyMarkup reply_markup;
        public TLRPC.MessageReplyHeader reply_to;
        public TL_iv.RichMessage rich_message;
        public int top_msg_id;
        public long via_bot_id;
        public boolean welcome;

        public EphemeralMessage() {
        }

        public static EphemeralMessage TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            return (EphemeralMessage) TLObject.TLdeserialize(EphemeralMessage.class, fromConstructor(r3), r2, r3, r4);
        }

        private static EphemeralMessage fromConstructor(int r1) {
            if (r1 == (-1896618863)) goto L15;
            if (r1 == (-641278950)) goto L13;
            if (r1 == (-584597783)) goto L11;
            return null;
        L11:
            return new TL_ephemeralMessage();
        L13:
            return new TL_ephemeralMessage_layer228();
        L15:
            return new TL_ephemeralMessage_layer229_old();
        }
    }

    public static class TL_deleteAllWelcomeMessages extends TLMethod<TLRPC.Bool> {
        public static final int constructor = 1934595873;
        public TLRPC.InputPeer peer;

        public TL_deleteAllWelcomeMessages() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public TLRPC.Bool deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
        }
    }

    public static class TL_deleteMessage extends TLMethod<TLRPC.Bool> {
        public static final int constructor = -1829312617;
        public int flags;
        public int id;
        public TLRPC.InputPeer peer;
        public TLRPC.InputUser receiver_id;

        public TL_deleteMessage() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public TLRPC.Bool deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = this.flags;
            if (this.peer == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 1, r1);
            this.flags = r02;
            r4.writeInt32(r02);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L9;
            this.peer.serializeToStream(r4);
        L9:
            this.receiver_id.serializeToStream(r4);
            r4.writeInt32(this.id);
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_deleteWelcomeMessage extends TLMethod<TLRPC.Bool> {
        public static final int constructor = -394090015;
        public int id;
        public TLRPC.InputPeer peer;

        public TL_deleteWelcomeMessage() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public TLRPC.Bool deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            r2.writeInt32(this.id);
        }
    }

    public static class TL_editMessage extends TLRPC.TL_messages_editMessage {
        public static final int constructor = -811830693;
        public int flags;
        public boolean invert_media;
        public TLRPC.InputUser receiver_id;
        public boolean welcome;

        public TL_editMessage() {
        }

        @Override // org.telegram.tgnet.TLRPC.TL_messages_editMessage, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r10) {
            r10.writeInt32(constructor);
            int r0 = this.flags;
            boolean r2 = false;
            if (this.message == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 1, r1);
            this.flags = r02;
            if (this.entities == null) goto L9;
            boolean r12 = true;
        L10:
            int r03 = TLObject.setFlag(r02, 2, r12);
            this.flags = r03;
            if (this.reply_markup == null) goto L13;
            boolean r13 = true;
        L14:
            int r04 = TLObject.setFlag(r03, 4, r13);
            this.flags = r04;
            if (this.media == null) goto L17;
            boolean r14 = true;
        L18:
            int r05 = TLObject.setFlag(r04, 8, r14);
            this.flags = r05;
            if (this.rich_message == null) goto L21;
            boolean r15 = true;
        L22:
            int r06 = TLObject.setFlag(r05, 16, r15);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 32, this.invert_media);
            this.flags = r07;
            int r08 = TLObject.setFlag(r07, 64, this.welcome);
            this.flags = r08;
            if (this.peer == null) goto L25;
            r2 = true;
        L25:
            int r09 = TLObject.setFlag(r08, 128, r2);
            this.flags = r09;
            r10.writeInt32(r09);
            if (TLObject.hasFlag(this.flags, 128) == false) goto L28;
            this.peer.serializeToStream(r10);
        L28:
            this.receiver_id.serializeToStream(r10);
            r10.writeInt32(this.id);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L32;
            r10.writeString(this.message);
        L32:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L35;
            this.media.serializeToStream(r10);
        L35:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L38;
            Vector.serialize(r10, this.entities);
        L38:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L41;
            this.reply_markup.serializeToStream(r10);
        L41:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L44;
            this.rich_message.serializeToStream(r10);
            return;
        L44:
            return;
        L21:
            r15 = false;
            goto L22
        L17:
            r14 = false;
            goto L18
        L13:
            r13 = false;
            goto L14
        L9:
            r12 = false;
            goto L10
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_ephemeralMessage extends EphemeralMessage {
        public static final int constructor = -584597783;

        public TL_ephemeralMessage() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.out = TLObject.hasFlag(r0, 1);
            this.welcome = TLObject.hasFlag(this.flags, 32);
            this.invert_media = TLObject.hasFlag(this.flags, 128);
            this.noforwards = TLObject.hasFlag(this.flags, 4096);
            this.id = r3.readInt32(r4);
            this.from_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L5;
            this.peer_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.receiver_id = r3.readInt64(r4);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L8;
            this.top_msg_id = r3.readInt32(r4);
        L8:
            this.date = r3.readInt32(r4);
            this.message = r3.readString(r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.entities = Vector.deserialize(r3, new MessagesStorage$$ExternalSyntheticLambda117(), r4);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            this.media = TLRPC.MessageMedia.TLdeserialize(r3, r3.readInt32(r4), r4);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            this.reply_markup = TLRPC.ReplyMarkup.TLdeserialize(r3, r3.readInt32(r4), r4);
        L18:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L21;
            this.reply_to = TLRPC.MessageReplyHeader.TLdeserialize(r3, r3.readInt32(r4), r4);
        L21:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L24;
            this.rich_message = TL_iv.RichMessage.TLdeserialize(r3, r3.readInt32(r4), r4);
        L24:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L27;
            this.chat_instance = r3.readInt64(r4);
        L27:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L30;
            this.anchor_msg_id = r3.readInt32(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r10) {
            r10.writeInt32(constructor);
            boolean r2 = true;
            int r0 = TLObject.setFlag(this.flags, 1, this.out);
            this.flags = r0;
            if (this.entities == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 4, r1);
            this.flags = r02;
            if (this.media == null) goto L9;
            boolean r12 = true;
        L10:
            int r03 = TLObject.setFlag(r02, 8, r12);
            this.flags = r03;
            if (this.reply_markup == null) goto L13;
            boolean r13 = true;
        L14:
            int r04 = TLObject.setFlag(r03, 16, r13);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 32, this.welcome);
            this.flags = r05;
            if (this.reply_to == null) goto L17;
            boolean r14 = true;
        L18:
            int r06 = TLObject.setFlag(r05, 64, r14);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 128, this.invert_media);
            this.flags = r07;
            if (this.rich_message == null) goto L21;
            boolean r15 = true;
        L22:
            int r08 = TLObject.setFlag(r07, 256, r15);
            this.flags = r08;
            if (this.peer_id != null) goto L26;
            r2 = false;
        L26:
            int r09 = TLObject.setFlag(r08, 512, r2);
            this.flags = r09;
            int r010 = TLObject.setFlag(r09, 4096, this.noforwards);
            this.flags = r010;
            r10.writeInt32(r010);
            r10.writeInt32(this.id);
            this.from_id.serializeToStream(r10);
            if (TLObject.hasFlag(this.flags, 512) == false) goto L29;
            this.peer_id.serializeToStream(r10);
        L29:
            r10.writeInt64(this.receiver_id);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L32;
            r10.writeInt32(this.top_msg_id);
        L32:
            r10.writeInt32(this.date);
            r10.writeString(this.message);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L36;
            Vector.serialize(r10, this.entities);
        L36:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L39;
            this.media.serializeToStream(r10);
        L39:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L42;
            this.reply_markup.serializeToStream(r10);
        L42:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L45;
            this.reply_to.serializeToStream(r10);
        L45:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L48;
            this.rich_message.serializeToStream(r10);
        L48:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L51;
            r10.writeInt64(this.chat_instance);
        L51:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L54;
            r10.writeInt32(this.anchor_msg_id);
            return;
        L54:
            return;
        L21:
            r15 = false;
            goto L22
        L17:
            r14 = false;
            goto L18
        L13:
            r13 = false;
            goto L14
        L9:
            r12 = false;
            goto L10
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_ephemeralMessage_layer228 extends TL_ephemeralMessage {
        public static final int constructor = -641278950;

        public TL_ephemeralMessage_layer228() {
        }

        @Override // org.telegram.tgnet.tl.TL_ephemeral.TL_ephemeralMessage, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.out = TLObject.hasFlag(r0, 1);
            this.id = r3.readInt32(r4);
            this.from_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.peer_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.receiver_id = r3.readInt64(r4);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L5;
            this.top_msg_id = r3.readInt32(r4);
        L5:
            this.date = r3.readInt32(r4);
            this.message = r3.readString(r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.entities = Vector.deserialize(r3, new MessagesStorage$$ExternalSyntheticLambda117(), r4);
        L9:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L12;
            this.media = TLRPC.MessageMedia.TLdeserialize(r3, r3.readInt32(r4), r4);
        L12:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L15;
            this.reply_markup = TLRPC.ReplyMarkup.TLdeserialize(r3, r3.readInt32(r4), r4);
        L15:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L18;
            this.reply_to = TLRPC.MessageReplyHeader.TLdeserialize(r3, r3.readInt32(r4), r4);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_ephemeral.TL_ephemeralMessage, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r8) {
            r8.writeInt32(constructor);
            boolean r2 = true;
            int r0 = TLObject.setFlag(this.flags, 1, this.out);
            this.flags = r0;
            if (this.entities == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 4, r1);
            this.flags = r02;
            if (this.media == null) goto L9;
            boolean r12 = true;
        L10:
            int r03 = TLObject.setFlag(r02, 8, r12);
            this.flags = r03;
            if (this.reply_markup == null) goto L13;
            boolean r13 = true;
        L14:
            int r04 = TLObject.setFlag(r03, 16, r13);
            this.flags = r04;
            if (this.reply_to != null) goto L18;
            r2 = false;
        L18:
            int r05 = TLObject.setFlag(r04, 64, r2);
            this.flags = r05;
            r8.writeInt32(r05);
            r8.writeInt32(this.id);
            this.from_id.serializeToStream(r8);
            this.peer_id.serializeToStream(r8);
            r8.writeInt64(this.receiver_id);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L21;
            r8.writeInt32(this.top_msg_id);
        L21:
            r8.writeInt32(this.date);
            r8.writeString(this.message);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L25;
            Vector.serialize(r8, this.entities);
        L25:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L28;
            this.media.serializeToStream(r8);
        L28:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L31;
            this.reply_markup.serializeToStream(r8);
        L31:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L34;
            this.reply_to.serializeToStream(r8);
            return;
        L34:
            return;
        L13:
            r13 = false;
            goto L14
        L9:
            r12 = false;
            goto L10
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_ephemeralMessage_layer229_old extends TL_ephemeralMessage {
        public static final int constructor = -1896618863;

        public TL_ephemeralMessage_layer229_old() {
        }

        @Override // org.telegram.tgnet.tl.TL_ephemeral.TL_ephemeralMessage, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.out = TLObject.hasFlag(r0, 1);
            this.welcome = TLObject.hasFlag(this.flags, 32);
            this.invert_media = TLObject.hasFlag(this.flags, 128);
            this.id = r3.readInt32(r4);
            this.from_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.peer_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.receiver_id = r3.readInt64(r4);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L5;
            this.top_msg_id = r3.readInt32(r4);
        L5:
            this.date = r3.readInt32(r4);
            this.message = r3.readString(r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.entities = Vector.deserialize(r3, new MessagesStorage$$ExternalSyntheticLambda117(), r4);
        L9:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L12;
            this.media = TLRPC.MessageMedia.TLdeserialize(r3, r3.readInt32(r4), r4);
        L12:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L15;
            this.reply_markup = TLRPC.ReplyMarkup.TLdeserialize(r3, r3.readInt32(r4), r4);
        L15:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L18;
            this.reply_to = TLRPC.MessageReplyHeader.TLdeserialize(r3, r3.readInt32(r4), r4);
        L18:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L21;
            this.rich_message = TL_iv.RichMessage.TLdeserialize(r3, r3.readInt32(r4), r4);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_ephemeral.TL_ephemeralMessage, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r10) {
            r10.writeInt32(constructor);
            boolean r2 = true;
            int r0 = TLObject.setFlag(this.flags, 1, this.out);
            this.flags = r0;
            if (this.entities == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 4, r1);
            this.flags = r02;
            if (this.media == null) goto L9;
            boolean r12 = true;
        L10:
            int r03 = TLObject.setFlag(r02, 8, r12);
            this.flags = r03;
            if (this.reply_markup == null) goto L13;
            boolean r13 = true;
        L14:
            int r04 = TLObject.setFlag(r03, 16, r13);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 32, this.welcome);
            this.flags = r05;
            if (this.reply_to == null) goto L17;
            boolean r14 = true;
        L18:
            int r06 = TLObject.setFlag(r05, 64, r14);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 128, this.invert_media);
            this.flags = r07;
            if (this.rich_message != null) goto L22;
            r2 = false;
        L22:
            int r08 = TLObject.setFlag(r07, 256, r2);
            this.flags = r08;
            r10.writeInt32(r08);
            r10.writeInt32(this.id);
            this.from_id.serializeToStream(r10);
            this.peer_id.serializeToStream(r10);
            r10.writeInt64(this.receiver_id);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L25;
            r10.writeInt32(this.top_msg_id);
        L25:
            r10.writeInt32(this.date);
            r10.writeString(this.message);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L29;
            Vector.serialize(r10, this.entities);
        L29:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L32;
            this.media.serializeToStream(r10);
        L32:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L35;
            this.reply_markup.serializeToStream(r10);
        L35:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L38;
            this.reply_to.serializeToStream(r10);
        L38:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L41;
            this.rich_message.serializeToStream(r10);
            return;
        L41:
            return;
        L17:
            r14 = false;
            goto L18
        L13:
            r13 = false;
            goto L14
        L9:
            r12 = false;
            goto L10
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_getCallbackAnswer extends TLMethod<TLRPC.TL_messages_botCallbackAnswer> {
        public static final int constructor = 1067738312;
        public byte[] data;
        public int flags;
        public int id;
        public TLRPC.InputPeer peer;

        public TL_getCallbackAnswer() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public TLRPC.TL_messages_botCallbackAnswer deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.TL_messages_botCallbackAnswer.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = this.flags;
            if (this.data == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 2, r1);
            this.flags = r02;
            r4.writeInt32(r02);
            this.peer.serializeToStream(r4);
            r4.writeInt32(this.id);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L10;
            r4.writeByteArray(this.data);
            return;
        L10:
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_getWelcomeMessages extends TLMethod<WelcomeMessages> {
        public static final int constructor = -610614899;
        public long hash;
        public TLRPC.InputPeer peer;

        public TL_getWelcomeMessages() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public WelcomeMessages deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return WelcomeMessages.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            this.peer.serializeToStream(r3);
            r3.writeInt64(this.hash);
        }
    }

    public static class TL_reportMessage extends TLMethod<TLRPC.ReportResult> {
        public static final int constructor = -2029718849;
        public int id;
        public String message;
        public byte[] option;
        public TLRPC.InputPeer peer;

        public TL_reportMessage() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public TLRPC.ReportResult deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.ReportResult.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            r2.writeInt32(this.id);
            r2.writeByteArray(this.option);
            r2.writeString(this.message);
        }
    }

    public static class TL_sendMessage extends TLMethod<TLRPC.Updates> {
        public static final int constructor = -1165140171;
        public boolean anchor;
        public ArrayList<TLRPC.MessageEntity> entities;
        public int flags;
        public boolean invert_media;
        public TLRPC.InputMedia media;
        public String message;
        public TLRPC.InputPeer peer;
        public long query_id;
        public long random_id;
        public TLRPC.InputUser receiver_id;
        public TLRPC.ReplyMarkup reply_markup;
        public TLRPC.InputReplyTo reply_to;
        public TL_iv.TL_inputRichMessage rich_message;
        public boolean welcome;

        public TL_sendMessage() {
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
        public void serializeToStream(OutputSerializedData r11) {
            r11.writeInt32(constructor);
            int r0 = this.flags;
            boolean r2 = false;
            if (this.entities == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 2, r1);
            this.flags = r02;
            if (this.media == null) goto L9;
            boolean r12 = true;
        L10:
            int r03 = TLObject.setFlag(r02, 4, r12);
            this.flags = r03;
            if (this.reply_markup == null) goto L13;
            boolean r13 = true;
        L14:
            int r04 = TLObject.setFlag(r03, 8, r13);
            this.flags = r04;
            if (this.rich_message == null) goto L17;
            boolean r14 = true;
        L18:
            int r05 = TLObject.setFlag(r04, 16, r14);
            this.flags = r05;
            if (this.reply_to == null) goto L21;
            boolean r15 = true;
        L22:
            int r06 = TLObject.setFlag(r05, 32, r15);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 64, this.invert_media);
            this.flags = r07;
            int r08 = TLObject.setFlag(r07, 128, this.welcome);
            this.flags = r08;
            if (this.peer == null) goto L25;
            r2 = true;
        L25:
            int r09 = TLObject.setFlag(r08, 256, r2);
            this.flags = r09;
            int r010 = TLObject.setFlag(r09, 512, this.anchor);
            this.flags = r010;
            r11.writeInt32(r010);
            if (TLObject.hasFlag(this.flags, 256) == false) goto L28;
            this.peer.serializeToStream(r11);
        L28:
            this.receiver_id.serializeToStream(r11);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L31;
            r11.writeInt64(this.query_id);
        L31:
            r11.writeString(this.message);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L35;
            Vector.serialize(r11, this.entities);
        L35:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L38;
            this.media.serializeToStream(r11);
        L38:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L41;
            this.reply_markup.serializeToStream(r11);
        L41:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L43;
            this.rich_message.serializeToStream(r11);
        L43:
            r11.writeInt64(this.random_id);
            if (TLObject.hasFlag(this.flags, 32) == false) goto L47;
            this.reply_to.serializeToStream(r11);
            return;
        L47:
            return;
        L21:
            r15 = false;
            goto L22
        L17:
            r14 = false;
            goto L18
        L13:
            r13 = false;
            goto L14
        L9:
            r12 = false;
            goto L10
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_welcomeMessages extends WelcomeMessages {
        public static final int constructor = 273664114;

        public TL_welcomeMessages() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.hash = r3.readInt64(r4);
            this.messages = Vector.deserialize(r3, new MessagesStorage$$ExternalSyntheticLambda84(), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.hash);
            Vector.serialize(r3, this.messages);
        }
    }

    public static class TL_welcomeMessagesNotModified extends WelcomeMessages {
        public static final int constructor = 1509940017;

        public TL_welcomeMessagesNotModified() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static abstract class WelcomeMessages extends TLObject {
        public long hash;
        public ArrayList<EphemeralMessage> messages;

        public WelcomeMessages() {
            this.messages = new ArrayList();
        }

        public static WelcomeMessages TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            return (WelcomeMessages) TLObject.TLdeserialize(WelcomeMessages.class, fromConstructor(r3), r2, r3, r4);
        }

        private static WelcomeMessages fromConstructor(int r1) {
            if (r1 == 273664114) goto L11;
            if (r1 == 1509940017) goto L9;
            return null;
        L9:
            return new TL_welcomeMessagesNotModified();
        L11:
            return new TL_welcomeMessages();
        }
    }

    public TL_ephemeral() {
    }
}
