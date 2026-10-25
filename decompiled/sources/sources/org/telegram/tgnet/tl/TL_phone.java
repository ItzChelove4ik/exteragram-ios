package org.telegram.tgnet.tl;

import java.util.ArrayList;
import org.telegram.messenger.MessagePreviewParams$$ExternalSyntheticLambda0;
import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.TLMethod;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1;
import org.telegram.tgnet.TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1;
import org.telegram.tgnet.Vector;

/* loaded from: classes6.dex */
public class TL_phone {

    public static abstract class PhoneCall extends TLObject {
        public long access_hash;
        public long admin_id;
        public boolean conference_supported;
        public ArrayList<TLRPC.PhoneConnection> connections;
        public TLRPC.TL_dataJSON custom_parameters;
        public int date;
        public int duration;
        public int flags;
        public byte[] g_a_hash;
        public byte[] g_a_or_b;
        public byte[] g_b;
        public long id;
        public long key_fingerprint;
        public boolean need_debug;
        public boolean need_rating;
        public boolean p2p_allowed;
        public long participant_id;
        public PhoneCallProtocol protocol;
        public TLRPC.PhoneCallDiscardReason reason;
        public int receive_date;
        public int start_date;
        public boolean video;

        public PhoneCall() {
            this.connections = new ArrayList();
        }

        public static PhoneCall TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            switch(r3) {
                case -1770029977: goto L10;
                case -987599081: goto L9;
                case 347139340: goto L8;
                case 810769141: goto L7;
                case 912311057: goto L6;
                case 1355435489: goto L5;
                case 1399245077: goto L4;
                default: goto L3;
            };
        L3:
            TLObject r0 = null;
        L12:
            return (PhoneCall) TLObject.TLdeserialize(PhoneCall.class, r0, r2, r3, r4);
        L4:
            r0 = new TL_phoneCallEmpty();
            goto L12
        L5:
            r0 = new TL_phoneCallDiscarded();
            goto L12
        L6:
            r0 = new TL_phoneCallAccepted();
            goto L12
        L7:
            r0 = new TL_phoneCall();
            goto L12
        L8:
            r0 = new phoneCallRequested();
            goto L12
        L9:
            r0 = new TL_phoneCallWaiting();
            goto L12
        L10:
            r0 = new TL_phoneCall_layer176();
            goto L12
        }
    }

    public static abstract class PhoneCallProtocol extends TLObject {
        public int flags;
        public ArrayList<String> library_versions;
        public int max_layer;
        public int min_layer;
        public boolean udp_p2p;
        public boolean udp_reflector;

        public PhoneCallProtocol() {
            this.library_versions = new ArrayList();
        }

        public static PhoneCallProtocol TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-1564789301)) goto L5;
            TLObject r0 = new TL_phoneCallProtocol_layer110();
        L10:
            return (PhoneCallProtocol) TLObject.TLdeserialize(PhoneCallProtocol.class, r0, r2, r3, r4);
        L5:
            if (r3 == (-58224696)) goto L7;
            r0 = null;
            goto L10
        L7:
            r0 = new TL_phoneCallProtocol();
            goto L10
        }
    }

    public static class TL_groupCallStreamChannel extends TLObject {
        public static final int constructor = -2132064081;
        public int channel;
        public long last_timestamp_ms;
        public int scale;

        public TL_groupCallStreamChannel() {
        }

        public static TL_groupCallStreamChannel TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-2132064081) == r3) goto L5;
            TL_groupCallStreamChannel r0 = null;
        L7:
            return (TL_groupCallStreamChannel) TLObject.TLdeserialize(TL_groupCallStreamChannel.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_groupCallStreamChannel();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.channel = r2.readInt32(r3);
            this.scale = r2.readInt32(r3);
            this.last_timestamp_ms = r2.readInt64(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.channel);
            r3.writeInt32(this.scale);
            r3.writeInt64(this.last_timestamp_ms);
        }
    }

    public static class TL_phoneCall extends PhoneCall {
        public static final int constructor = 810769141;

        public TL_phoneCall() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.p2p_allowed = TLObject.hasFlag(r0, 32);
            this.video = TLObject.hasFlag(this.flags, 64);
            this.conference_supported = TLObject.hasFlag(this.flags, 256);
            this.id = r3.readInt64(r4);
            this.access_hash = r3.readInt64(r4);
            this.date = r3.readInt32(r4);
            this.admin_id = r3.readInt64(r4);
            this.participant_id = r3.readInt64(r4);
            this.g_a_or_b = r3.readByteArray(r4);
            this.key_fingerprint = r3.readInt64(r4);
            this.protocol = PhoneCallProtocol.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.connections = Vector.deserialize(r3, new TL_phone$TL_phoneCall$$ExternalSyntheticLambda0(), r4);
            this.start_date = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 128) == false) goto L6;
            this.custom_parameters = TLRPC.TL_dataJSON.TLdeserialize(r3, r3.readInt32(r4), r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 32, this.p2p_allowed);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 64, this.video);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 256, this.conference_supported);
            this.flags = r03;
            r4.writeInt32(r03);
            r4.writeInt64(this.id);
            r4.writeInt64(this.access_hash);
            r4.writeInt32(this.date);
            r4.writeInt64(this.admin_id);
            r4.writeInt64(this.participant_id);
            r4.writeByteArray(this.g_a_or_b);
            r4.writeInt64(this.key_fingerprint);
            this.protocol.serializeToStream(r4);
            Vector.serialize(r4, this.connections);
            r4.writeInt32(this.start_date);
            if (TLObject.hasFlag(this.flags, 128) == false) goto L6;
            this.custom_parameters.serializeToStream(r4);
            return;
        }
    }

    public static class TL_phoneCallAccepted extends PhoneCall {
        public static final int constructor = 912311057;

        public TL_phoneCallAccepted() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.video = TLObject.hasFlag(r0, 64);
            this.id = r3.readInt64(r4);
            this.access_hash = r3.readInt64(r4);
            this.date = r3.readInt32(r4);
            this.admin_id = r3.readInt64(r4);
            this.participant_id = r3.readInt64(r4);
            this.g_b = r3.readByteArray(r4);
            this.protocol = PhoneCallProtocol.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 64, this.video);
            this.flags = r0;
            r4.writeInt32(r0);
            r4.writeInt64(this.id);
            r4.writeInt64(this.access_hash);
            r4.writeInt32(this.date);
            r4.writeInt64(this.admin_id);
            r4.writeInt64(this.participant_id);
            r4.writeByteArray(this.g_b);
            this.protocol.serializeToStream(r4);
        }
    }

    public static class TL_phoneCallDiscarded extends PhoneCall {
        public static final int constructor = 1355435489;

        public TL_phoneCallDiscarded() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.need_rating = TLObject.hasFlag(r0, 4);
            this.need_debug = TLObject.hasFlag(this.flags, 8);
            this.video = TLObject.hasFlag(this.flags, 64);
            this.id = r3.readInt64(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.reason = TLRPC.PhoneCallDiscardReason.TLdeserialize(r3, r3.readInt32(r4), r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.duration = r3.readInt32(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 4, this.need_rating);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 8, this.need_debug);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 64, this.video);
            this.flags = r03;
            r4.writeInt32(r03);
            r4.writeInt64(this.id);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.reason.serializeToStream(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r4.writeInt32(this.duration);
            return;
        }
    }

    public static class TL_phoneCallEmpty extends PhoneCall {
        public static final int constructor = 1399245077;

        public TL_phoneCallEmpty() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.id = r1.readInt64(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.id);
        }
    }

    public static class TL_phoneCallProtocol extends PhoneCallProtocol {
        public static final int constructor = -58224696;

        public TL_phoneCallProtocol() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.udp_p2p = TLObject.hasFlag(r0, 1);
            this.udp_reflector = TLObject.hasFlag(this.flags, 2);
            this.min_layer = r3.readInt32(r4);
            this.max_layer = r3.readInt32(r4);
            this.library_versions = Vector.deserializeString(r3, r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.udp_p2p);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.udp_reflector);
            this.flags = r02;
            r4.writeInt32(r02);
            r4.writeInt32(this.min_layer);
            r4.writeInt32(this.max_layer);
            Vector.serializeString(r4, this.library_versions);
        }
    }

    public static class TL_phoneCallProtocol_layer110 extends TL_phoneCallProtocol {
        public static final int constructor = -1564789301;

        public TL_phoneCallProtocol_layer110() {
        }

        @Override // org.telegram.tgnet.tl.TL_phone.TL_phoneCallProtocol, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.udp_p2p = TLObject.hasFlag(r0, 1);
            this.udp_reflector = TLObject.hasFlag(this.flags, 2);
            this.min_layer = r3.readInt32(r4);
            this.max_layer = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.tl.TL_phone.TL_phoneCallProtocol, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.udp_p2p);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.udp_reflector);
            this.flags = r02;
            r4.writeInt32(r02);
            r4.writeInt32(this.min_layer);
            r4.writeInt32(this.max_layer);
        }
    }

    public static class TL_phoneCallWaiting extends PhoneCall {
        public static final int constructor = -987599081;

        public TL_phoneCallWaiting() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.video = TLObject.hasFlag(r0, 64);
            this.id = r3.readInt64(r4);
            this.access_hash = r3.readInt64(r4);
            this.date = r3.readInt32(r4);
            this.admin_id = r3.readInt64(r4);
            this.participant_id = r3.readInt64(r4);
            this.protocol = PhoneCallProtocol.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.receive_date = r3.readInt32(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 64, this.video);
            this.flags = r0;
            r4.writeInt32(r0);
            r4.writeInt64(this.id);
            r4.writeInt64(this.access_hash);
            r4.writeInt32(this.date);
            r4.writeInt64(this.admin_id);
            r4.writeInt64(this.participant_id);
            this.protocol.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r4.writeInt32(this.receive_date);
            return;
        }
    }

    public static class TL_phoneCall_layer176 extends TL_phoneCall {
        public static final int constructor = -1770029977;

        public TL_phoneCall_layer176() {
        }

        @Override // org.telegram.tgnet.tl.TL_phone.TL_phoneCall, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.p2p_allowed = TLObject.hasFlag(r0, 32);
            this.video = TLObject.hasFlag(this.flags, 64);
            this.id = r3.readInt64(r4);
            this.access_hash = r3.readInt64(r4);
            this.date = r3.readInt32(r4);
            this.admin_id = r3.readInt64(r4);
            this.participant_id = r3.readInt64(r4);
            this.g_a_or_b = r3.readByteArray(r4);
            this.key_fingerprint = r3.readInt64(r4);
            this.protocol = PhoneCallProtocol.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.connections = Vector.deserialize(r3, new TL_phone$TL_phoneCall$$ExternalSyntheticLambda0(), r4);
            this.start_date = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.tl.TL_phone.TL_phoneCall, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 32, this.p2p_allowed);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 64, this.video);
            this.flags = r02;
            r4.writeInt32(r02);
            r4.writeInt64(this.id);
            r4.writeInt64(this.access_hash);
            r4.writeInt32(this.date);
            r4.writeInt64(this.admin_id);
            r4.writeInt64(this.participant_id);
            r4.writeByteArray(this.g_a_or_b);
            r4.writeInt64(this.key_fingerprint);
            this.protocol.serializeToStream(r4);
            Vector.serialize(r4, this.connections);
            r4.writeInt32(this.start_date);
        }
    }

    public static class TL_phone_phoneCall extends TLObject {
        public static final int constructor = -326966976;
        public PhoneCall phone_call;
        public ArrayList<TLRPC.User> users;

        public TL_phone_phoneCall() {
            this.users = new ArrayList();
        }

        public static TL_phone_phoneCall TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-326966976) == r3) goto L5;
            TL_phone_phoneCall r0 = null;
        L7:
            return (TL_phone_phoneCall) TLObject.TLdeserialize(TL_phone_phoneCall.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_phone_phoneCall();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.phone_call = PhoneCall.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.users = Vector.deserialize(r2, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.phone_call.serializeToStream(r2);
            Vector.serialize(r2, this.users);
        }
    }

    public static class acceptCall extends TLObject {
        public static final int constructor = 1003664544;
        public byte[] g_b;
        public TLRPC.TL_inputPhoneCall peer;
        public TL_phoneCallProtocol protocol;

        public acceptCall() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_phone_phoneCall.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            r2.writeByteArray(this.g_b);
            this.protocol.serializeToStream(r2);
        }
    }

    public static class checkGroupCall extends TLObject {
        public static final int constructor = -1248003721;
        public TLRPC.InputGroupCall call;
        public ArrayList<Integer> sources;

        public checkGroupCall() {
            this.sources = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return Vector.TLDeserializeInt(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.call.serializeToStream(r2);
            Vector.serializeInt(r2, this.sources);
        }
    }

    public static class confirmCall extends TLObject {
        public static final int constructor = 788404002;
        public byte[] g_a;
        public long key_fingerprint;
        public TLRPC.TL_inputPhoneCall peer;
        public TL_phoneCallProtocol protocol;

        public confirmCall() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_phone_phoneCall.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            this.peer.serializeToStream(r3);
            r3.writeByteArray(this.g_a);
            r3.writeInt64(this.key_fingerprint);
            this.protocol.serializeToStream(r3);
        }
    }

    public static class createConferenceCall extends TLObject {
        public static final int constructor = 2097431739;
        public byte[] block;
        public int flags;
        public boolean join;
        public boolean muted;
        public TLRPC.TL_dataJSON params;
        public byte[] public_key;
        public int random_id;
        public boolean video_stopped;

        public createConferenceCall() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.muted);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 4, this.video_stopped);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 8, this.join);
            this.flags = r03;
            r4.writeInt32(r03);
            r4.writeInt32(this.random_id);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L6;
            r4.writeBytes(this.public_key);
            r4.writeByteArray(this.block);
            this.params.serializeToStream(r4);
            return;
        }
    }

    public static class createGroupCall extends TLObject {
        public static final int constructor = 1221445336;
        public int flags;
        public TLRPC.InputPeer peer;
        public int random_id;
        public int schedule_date;
        public String title;

        public createGroupCall() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            this.peer.serializeToStream(r3);
            r3.writeInt32(this.random_id);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeString(this.title);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r3.writeInt32(this.schedule_date);
            return;
        }
    }

    public static class declineConferenceCallInvite extends TLObject {
        public static final int constructor = 1011325297;
        public int msg_id;

        public declineConferenceCallInvite() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.msg_id);
        }
    }

    public static class deleteConferenceCallParticipants extends TLObject {
        public static final int constructor = -1935276763;
        public byte[] block;
        public TLRPC.InputGroupCall call;
        public int flags;
        public ArrayList<Long> ids;
        public boolean kick;
        public boolean only_left;

        public deleteConferenceCallParticipants() {
            this.ids = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.only_left);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.kick);
            this.flags = r02;
            r4.writeInt32(r02);
            this.call.serializeToStream(r4);
            Vector.serializeLong(r4, this.ids);
            r4.writeByteArray(this.block);
        }
    }

    public static class deleteGroupCallMessages extends TLMethod<TLRPC.Updates> {
        public static final int constructor = -162573065;
        public TLRPC.InputGroupCall call;
        public int flags;
        public ArrayList<Integer> messages;
        public boolean report_spam;

        public deleteGroupCallMessages() {
            this.messages = new ArrayList();
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
            int r0 = TLObject.setFlag(this.flags, 1, this.report_spam);
            this.flags = r0;
            r4.writeInt32(r0);
            this.call.serializeToStream(r4);
            Vector.serializeInt(r4, this.messages);
        }
    }

    public static class deleteGroupCallParticipantMessages extends TLMethod<TLRPC.Updates> {
        public static final int constructor = 499117216;
        public TLRPC.InputGroupCall call;
        public int flags;
        public TLRPC.InputPeer participant;
        public boolean report_spam;

        public deleteGroupCallParticipantMessages() {
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
            int r0 = TLObject.setFlag(this.flags, 1, this.report_spam);
            this.flags = r0;
            r4.writeInt32(r0);
            this.call.serializeToStream(r4);
            this.participant.serializeToStream(r4);
        }
    }

    public static class discardCall extends TLObject {
        public static final int constructor = -1295269440;
        public long connection_id;
        public int duration;
        public int flags;
        public TLRPC.TL_inputPhoneCall peer;
        public TLRPC.PhoneCallDiscardReason reason;
        public boolean video;

        public discardCall() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.video);
            this.flags = r0;
            r4.writeInt32(r0);
            this.peer.serializeToStream(r4);
            r4.writeInt32(this.duration);
            this.reason.serializeToStream(r4);
            r4.writeInt64(this.connection_id);
        }
    }

    public static class discardGroupCall extends TLObject {
        public static final int constructor = 2054648117;
        public TLRPC.InputGroupCall call;

        public discardGroupCall() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.call.serializeToStream(r2);
        }
    }

    public static class editGroupCallParticipant extends TLObject {
        public static final int constructor = -1524155713;
        public TLRPC.InputGroupCall call;
        public int flags;
        public boolean muted;
        public TLRPC.InputPeer participant;
        public boolean presentation_paused;
        public boolean raise_hand;
        public boolean video_paused;
        public boolean video_stopped;
        public int volume;

        public editGroupCallParticipant() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            this.call.serializeToStream(r3);
            this.participant.serializeToStream(r3);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeBool(this.muted);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r3.writeInt32(this.volume);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            r3.writeBool(this.raise_hand);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            r3.writeBool(this.video_stopped);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            r3.writeBool(this.video_paused);
        L18:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L21;
            r3.writeBool(this.presentation_paused);
            return;
        }
    }

    public static class editGroupCallTitle extends TLObject {
        public static final int constructor = 480685066;
        public TLRPC.InputGroupCall call;
        public String title;

        public editGroupCallTitle() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.call.serializeToStream(r2);
            r2.writeString(this.title);
        }
    }

    public static class exportGroupCallInvite extends TLObject {
        public static final int constructor = -425040769;
        public TLRPC.InputGroupCall call;
        public boolean can_self_unmute;
        public int flags;

        public exportGroupCallInvite() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return exportedGroupCallInvite.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.can_self_unmute);
            this.flags = r0;
            r4.writeInt32(r0);
            this.call.serializeToStream(r4);
        }
    }

    public static class exportedGroupCallInvite extends TLObject {
        public static final int constructor = 541839704;
        public String link;

        public exportedGroupCallInvite() {
        }

        public static exportedGroupCallInvite TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (541839704 == r3) goto L5;
            exportedGroupCallInvite r0 = null;
        L7:
            return (exportedGroupCallInvite) TLObject.TLdeserialize(exportedGroupCallInvite.class, r0, r2, r3, r4);
        L5:
            r0 = new exportedGroupCallInvite();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.link = r1.readString(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.link);
        }
    }

    public static class getCallConfig extends TLObject {
        public static final int constructor = 1430593449;

        public getCallConfig() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.TL_dataJSON.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class getGroupCall extends TLObject {
        public static final int constructor = 68699611;
        public TLRPC.InputGroupCall call;
        public int limit;

        public getGroupCall() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return groupCall.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.call.serializeToStream(r2);
            r2.writeInt32(this.limit);
        }
    }

    public static class getGroupCallChainBlocks extends TLObject {
        public static final int constructor = -291534682;
        public TLRPC.InputGroupCall call;
        public int limit;
        public int offset;
        public int sub_chain_id;

        public getGroupCallChainBlocks() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.call.serializeToStream(r2);
            r2.writeInt32(this.sub_chain_id);
            r2.writeInt32(this.offset);
            r2.writeInt32(this.limit);
        }
    }

    public static class getGroupCallJoinAs extends TLObject {
        public static final int constructor = -277077702;
        public TLRPC.InputPeer peer;

        public getGroupCallJoinAs() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return joinAsPeers.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
        }
    }

    public static class getGroupCallStars extends TLMethod<groupCallStars> {
        public static final int constructor = 1868784386;
        public TLRPC.InputGroupCall call;

        public getGroupCallStars() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public groupCallStars deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return groupCallStars.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.call.serializeToStream(r2);
        }
    }

    public static class getGroupCallStreamChannels extends TLObject {
        public static final int constructor = 447879488;
        public TLRPC.InputGroupCall call;

        public getGroupCallStreamChannels() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return groupCallStreamChannels.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.call.serializeToStream(r2);
        }
    }

    public static class getGroupCallStreamRtmpUrl extends TLObject {
        public static final int constructor = 1525991226;
        public int flags;
        public boolean live_story;
        public TLRPC.InputPeer peer;
        public boolean revoke;

        public getGroupCallStreamRtmpUrl() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return groupCallStreamRtmpUrl.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.live_story);
            this.flags = r0;
            r4.writeInt32(r0);
            this.peer.serializeToStream(r4);
            r4.writeBool(this.revoke);
        }
    }

    public static class getGroupParticipants extends TLObject {
        public static final int constructor = -984033109;
        public TLRPC.InputGroupCall call;
        public ArrayList<TLRPC.InputPeer> ids;
        public int limit;
        public String offset;
        public ArrayList<Integer> sources;

        public getGroupParticipants() {
            this.ids = new ArrayList();
            this.sources = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return groupParticipants.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.call.serializeToStream(r2);
            Vector.serialize(r2, this.ids);
            Vector.serializeInt(r2, this.sources);
            r2.writeString(this.offset);
            r2.writeInt32(this.limit);
        }
    }

    public static class groupCall extends TLObject {
        public static final int constructor = -1636664659;
        public TLRPC.GroupCall call;
        public ArrayList<TLRPC.Chat> chats;
        public ArrayList<TLRPC.GroupCallParticipant> participants;
        public String participants_next_offset;
        public ArrayList<TLRPC.User> users;

        public groupCall() {
            this.participants = new ArrayList();
            this.chats = new ArrayList();
            this.users = new ArrayList();
        }

        public static groupCall TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-1636664659) == r3) goto L5;
            groupCall r0 = null;
        L7:
            return (groupCall) TLObject.TLdeserialize(groupCall.class, r0, r2, r3, r4);
        L5:
            r0 = new groupCall();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.call = TLRPC.GroupCall.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.participants = Vector.deserialize(r2, new TL_phone$groupCall$$ExternalSyntheticLambda0(), r3);
            this.participants_next_offset = r2.readString(r3);
            this.chats = Vector.deserialize(r2, new TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1(), r3);
            this.users = Vector.deserialize(r2, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.call.serializeToStream(r2);
            Vector.serialize(r2, this.participants);
            r2.writeString(this.participants_next_offset);
            Vector.serialize(r2, this.chats);
            Vector.serialize(r2, this.users);
        }
    }

    public static class groupCallDonor extends TLObject {
        public static final int constructor = -297595771;
        public boolean anonymous;
        public int flags;
        public boolean my;
        public TLRPC.Peer peer_id;
        public long stars;
        public boolean top;

        public groupCallDonor() {
        }

        public static groupCallDonor TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-297595771)) goto L5;
            groupCallDonor r0 = new groupCallDonor();
        L7:
            return (groupCallDonor) TLObject.TLdeserialize(groupCallDonor.class, r0, r2, r3, r4);
        L5:
            r0 = null;
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.top = TLObject.hasFlag(r0, 1);
            this.my = TLObject.hasFlag(this.flags, 2);
            this.anonymous = TLObject.hasFlag(this.flags, 4);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L5;
            this.peer_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.stars = r3.readInt64(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.top);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.my);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 4, this.anonymous);
            this.flags = r03;
            r4.writeInt32(r03);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L5;
            this.peer_id.serializeToStream(r4);
        L5:
            r4.writeInt64(this.stars);
        }
    }

    public static class groupCallStars extends TLObject {
        public static final int constructor = -1658995418;
        public ArrayList<TLRPC.Chat> chats;
        public ArrayList<groupCallDonor> top_donors;
        public long total_stars;
        public ArrayList<TLRPC.User> users;

        public groupCallStars() {
            this.top_donors = new ArrayList();
            this.chats = new ArrayList();
            this.users = new ArrayList();
        }

        public static groupCallStars TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-1658995418)) goto L5;
            groupCallStars r0 = new groupCallStars();
        L7:
            return (groupCallStars) TLObject.TLdeserialize(groupCallStars.class, r0, r2, r3, r4);
        L5:
            r0 = null;
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.total_stars = r3.readInt64(r4);
            this.top_donors = Vector.deserialize(r3, new TL_phone$groupCallStars$$ExternalSyntheticLambda0(), r4);
            this.chats = Vector.deserialize(r3, new TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1(), r4);
            this.users = Vector.deserialize(r3, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.total_stars);
            Vector.serialize(r3, this.top_donors);
            Vector.serialize(r3, this.chats);
            Vector.serialize(r3, this.users);
        }
    }

    public static class groupCallStreamChannels extends TLObject {
        public static final int constructor = -790330702;
        public ArrayList<TL_groupCallStreamChannel> channels;

        public groupCallStreamChannels() {
            this.channels = new ArrayList();
        }

        public static groupCallStreamChannels TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-790330702) == r3) goto L5;
            groupCallStreamChannels r0 = null;
        L7:
            return (groupCallStreamChannels) TLObject.TLdeserialize(groupCallStreamChannels.class, r0, r2, r3, r4);
        L5:
            r0 = new groupCallStreamChannels();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.channels = Vector.deserialize(r2, new TL_phone$groupCallStreamChannels$$ExternalSyntheticLambda0(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.channels);
        }
    }

    public static class groupCallStreamRtmpUrl extends TLObject {
        public static final int constructor = 767505458;
        public String key;
        public String url;

        public groupCallStreamRtmpUrl() {
        }

        public static groupCallStreamRtmpUrl TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (767505458 == r3) goto L5;
            groupCallStreamRtmpUrl r0 = null;
        L7:
            return (groupCallStreamRtmpUrl) TLObject.TLdeserialize(groupCallStreamRtmpUrl.class, r0, r2, r3, r4);
        L5:
            r0 = new groupCallStreamRtmpUrl();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.url = r2.readString(r3);
            this.key = r2.readString(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.url);
            r2.writeString(this.key);
        }
    }

    public static class groupParticipants extends TLObject {
        public static final int constructor = -193506890;
        public ArrayList<TLRPC.Chat> chats;
        public int count;
        public String next_offset;
        public ArrayList<TLRPC.GroupCallParticipant> participants;
        public ArrayList<TLRPC.User> users;
        public int version;

        public groupParticipants() {
            this.participants = new ArrayList();
            this.chats = new ArrayList();
            this.users = new ArrayList();
        }

        public static groupParticipants TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-193506890) == r3) goto L5;
            groupParticipants r0 = null;
        L7:
            return (groupParticipants) TLObject.TLdeserialize(groupParticipants.class, r0, r2, r3, r4);
        L5:
            r0 = new groupParticipants();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.count = r2.readInt32(r3);
            this.participants = Vector.deserialize(r2, new TL_phone$groupCall$$ExternalSyntheticLambda0(), r3);
            this.next_offset = r2.readString(r3);
            this.chats = Vector.deserialize(r2, new TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1(), r3);
            this.users = Vector.deserialize(r2, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r3);
            this.version = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.count);
            Vector.serialize(r2, this.participants);
            r2.writeString(this.next_offset);
            Vector.serialize(r2, this.chats);
            Vector.serialize(r2, this.users);
            r2.writeInt32(this.version);
        }
    }

    public static class inviteConferenceCallParticipant extends TLObject {
        public static final int constructor = -1124981115;
        public TLRPC.InputGroupCall call;
        public int flags;
        public TLRPC.InputUser user_id;
        public boolean video;

        public inviteConferenceCallParticipant() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.video);
            this.flags = r0;
            r4.writeInt32(r0);
            this.call.serializeToStream(r4);
            this.user_id.serializeToStream(r4);
        }
    }

    public static class inviteToGroupCall extends TLObject {
        public static final int constructor = 2067345760;
        public TLRPC.InputGroupCall call;
        public ArrayList<TLRPC.InputUser> users;

        public inviteToGroupCall() {
            this.users = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.call.serializeToStream(r2);
            Vector.serialize(r2, this.users);
        }
    }

    public static class joinAsPeers extends TLObject {
        public static final int constructor = -1343921601;
        public ArrayList<TLRPC.Chat> chats;
        public ArrayList<TLRPC.Peer> peers;
        public ArrayList<TLRPC.User> users;

        public joinAsPeers() {
            this.peers = new ArrayList();
            this.chats = new ArrayList();
            this.users = new ArrayList();
        }

        public static joinAsPeers TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-1343921601) == r3) goto L5;
            joinAsPeers r0 = null;
        L7:
            return (joinAsPeers) TLObject.TLdeserialize(joinAsPeers.class, r0, r2, r3, r4);
        L5:
            r0 = new joinAsPeers();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.peers = Vector.deserialize(r2, new MessagePreviewParams$$ExternalSyntheticLambda0(), r3);
            this.chats = Vector.deserialize(r2, new TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1(), r3);
            this.users = Vector.deserialize(r2, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.peers);
            Vector.serialize(r2, this.chats);
            Vector.serialize(r2, this.users);
        }
    }

    public static class joinGroupCall extends TLObject {
        public static final int constructor = -1883951017;
        public byte[] block;
        public TLRPC.InputGroupCall call;
        public int flags;
        public String invite_hash;
        public TLRPC.InputPeer join_as;
        public boolean muted;
        public TLRPC.TL_dataJSON params;
        public byte[] public_key;
        public boolean video_stopped;

        public joinGroupCall() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.muted);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 4, this.video_stopped);
            this.flags = r02;
            r4.writeInt32(r02);
            this.call.serializeToStream(r4);
            this.join_as.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L6;
            r4.writeString(this.invite_hash);
        L6:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L8;
            r4.writeBytes(this.public_key);
            r4.writeByteArray(this.block);
        L8:
            this.params.serializeToStream(r4);
        }
    }

    public static class joinGroupCallPresentation extends TLObject {
        public static final int constructor = -873829436;
        public TLRPC.InputGroupCall call;
        public TLRPC.TL_dataJSON params;

        public joinGroupCallPresentation() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.call.serializeToStream(r2);
            this.params.serializeToStream(r2);
        }
    }

    public static class leaveGroupCall extends TLObject {
        public static final int constructor = 1342404601;
        public TLRPC.InputGroupCall call;
        public int source;

        public leaveGroupCall() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.call.serializeToStream(r2);
            r2.writeInt32(this.source);
        }
    }

    public static class leaveGroupCallPresentation extends TLObject {
        public static final int constructor = 475058500;
        public TLRPC.InputGroupCall call;

        public leaveGroupCallPresentation() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.call.serializeToStream(r2);
        }
    }

    public static class phoneCallRequested extends PhoneCall {
        public static final int constructor = 347139340;

        public phoneCallRequested() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.video = TLObject.hasFlag(r0, 64);
            this.id = r3.readInt64(r4);
            this.access_hash = r3.readInt64(r4);
            this.date = r3.readInt32(r4);
            this.admin_id = r3.readInt64(r4);
            this.participant_id = r3.readInt64(r4);
            this.g_a_hash = r3.readByteArray(r4);
            this.protocol = PhoneCallProtocol.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 64, this.video);
            this.flags = r0;
            r4.writeInt32(r0);
            r4.writeInt64(this.id);
            r4.writeInt64(this.access_hash);
            r4.writeInt32(this.date);
            r4.writeInt64(this.admin_id);
            r4.writeInt64(this.participant_id);
            r4.writeByteArray(this.g_a_hash);
            this.protocol.serializeToStream(r4);
        }
    }

    public static class receivedCall extends TLObject {
        public static final int constructor = 399855457;
        public TLRPC.TL_inputPhoneCall peer;

        public receivedCall() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
        }
    }

    public static class requestCall extends TLObject {
        public static final int constructor = 1124046573;
        public int flags;
        public byte[] g_a_hash;
        public TL_phoneCallProtocol protocol;
        public int random_id;
        public TLRPC.InputUser user_id;
        public boolean video;

        public requestCall() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_phone_phoneCall.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.video);
            this.flags = r0;
            r4.writeInt32(r0);
            this.user_id.serializeToStream(r4);
            r4.writeInt32(this.random_id);
            r4.writeByteArray(this.g_a_hash);
            this.protocol.serializeToStream(r4);
        }
    }

    public static class saveCallDebug extends TLObject {
        public static final int constructor = 662363518;
        public TLRPC.TL_dataJSON debug;
        public TLRPC.TL_inputPhoneCall peer;

        public saveCallDebug() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            this.debug.serializeToStream(r2);
        }
    }

    public static class saveCallLog extends TLObject {
        public static final int constructor = 1092913030;
        public TLRPC.InputFile file;
        public TLRPC.TL_inputPhoneCall peer;

        public saveCallLog() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            this.file.serializeToStream(r2);
        }
    }

    public static class saveDefaultGroupCallJoinAs extends TLObject {
        public static final int constructor = 1465786252;
        public TLRPC.InputPeer join_as;
        public TLRPC.InputPeer peer;

        public saveDefaultGroupCallJoinAs() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            this.join_as.serializeToStream(r2);
        }
    }

    public static class saveDefaultSendAs extends TLMethod<TLRPC.Bool> {
        public static final int constructor = 1097313745;
        public TLRPC.InputGroupCall call;
        public TLRPC.InputPeer send_as;

        public saveDefaultSendAs() {
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
            this.call.serializeToStream(r2);
            this.send_as.serializeToStream(r2);
        }
    }

    public static class sendConferenceCallBroadcast extends TLObject {
        public static final int constructor = -965732096;
        public byte[] block;
        public TLRPC.InputGroupCall call;

        public sendConferenceCallBroadcast() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.call.serializeToStream(r2);
            r2.writeByteArray(this.block);
        }
    }

    public static class sendGroupCallEncryptedMessage extends TLMethod<TLRPC.Bool> {
        public static final int constructor = -441473683;
        public TLRPC.InputGroupCall call;
        public byte[] encrypted_message;

        public sendGroupCallEncryptedMessage() {
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
            this.call.serializeToStream(r2);
            r2.writeByteArray(this.encrypted_message);
        }
    }

    public static class sendGroupCallMessage extends TLMethod<TLRPC.Updates> {
        public static final int constructor = -1311697904;
        public long allow_paid_stars;
        public TLRPC.InputGroupCall call;
        public int flags;
        public TLRPC.TL_textWithEntities message;
        public long random_id;
        public TLRPC.InputPeer send_as;

        public sendGroupCallMessage() {
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
        public void serializeToStream(OutputSerializedData r5) {
            r5.writeInt32(constructor);
            int r0 = this.flags;
            if (this.send_as == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 2, r1);
            this.flags = r02;
            r5.writeInt32(r02);
            this.call.serializeToStream(r5);
            r5.writeInt64(this.random_id);
            this.message.serializeToStream(r5);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L10;
            r5.writeInt64(this.allow_paid_stars);
        L10:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L13;
            this.send_as.serializeToStream(r5);
            return;
        L13:
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class sendSignalingData extends TLObject {
        public static final int constructor = -8744061;
        public byte[] data;
        public TLRPC.TL_inputPhoneCall peer;

        public sendSignalingData() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            r2.writeByteArray(this.data);
        }
    }

    public static class setCallRating extends TLObject {
        public static final int constructor = 1508562471;
        public String comment;
        public int flags;
        public TLRPC.TL_inputPhoneCall peer;
        public int rating;
        public boolean user_initiative;

        public setCallRating() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.user_initiative);
            this.flags = r0;
            r4.writeInt32(r0);
            this.peer.serializeToStream(r4);
            r4.writeInt32(this.rating);
            r4.writeString(this.comment);
        }
    }

    public static class startScheduledGroupCall extends TLObject {
        public static final int constructor = 1451287362;
        public TLRPC.InputGroupCall call;

        public startScheduledGroupCall() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.call.serializeToStream(r2);
        }
    }

    public static class toggleGroupCallRecord extends TLObject {
        public static final int constructor = -248985848;
        public TLRPC.InputGroupCall call;
        public int flags;
        public boolean start;
        public String title;
        public boolean video;
        public boolean video_portrait;

        public toggleGroupCallRecord() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.start);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 4, this.video);
            this.flags = r02;
            r4.writeInt32(r02);
            this.call.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L6;
            r4.writeString(this.title);
        L6:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            r4.writeBool(this.video_portrait);
            return;
        }
    }

    public static class toggleGroupCallSettings extends TLMethod<TLRPC.Updates> {
        public static final int constructor = -1757179150;
        public TLRPC.InputGroupCall call;
        public Boolean join_muted;
        public Boolean messages_enabled;
        public boolean reset_invite_hash;
        public Long send_paid_messages_stars;

        public toggleGroupCallSettings() {
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
            boolean r1 = false;
            if (this.join_muted == null) goto L5;
            boolean r0 = true;
        L6:
            int r02 = TLObject.setFlag(TLObject.setFlag(0, 1, r0), 2, this.reset_invite_hash);
            if (this.messages_enabled == null) goto L9;
            boolean r3 = true;
        L10:
            int r03 = TLObject.setFlag(r02, 4, r3);
            if (this.send_paid_messages_stars == null) goto L13;
            r1 = true;
        L13:
            int r04 = TLObject.setFlag(r03, 8, r1);
            r6.writeInt32(r04);
            this.call.serializeToStream(r6);
            if (TLObject.hasFlag(r04, 1) == false) goto L17;
            r6.writeBool(this.join_muted.booleanValue());
        L17:
            if (TLObject.hasFlag(r04, 4) == false) goto L20;
            r6.writeBool(this.messages_enabled.booleanValue());
        L20:
            if (TLObject.hasFlag(r04, 8) == false) goto L23;
            r6.writeInt64(this.send_paid_messages_stars.longValue());
            return;
        L23:
            return;
        L9:
            r3 = false;
            goto L10
        L5:
            r0 = false;
            goto L6
        }
    }

    public static class toggleGroupCallStartSubscription extends TLObject {
        public static final int constructor = 563885286;
        public TLRPC.InputGroupCall call;
        public boolean subscribed;

        public toggleGroupCallStartSubscription() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.call.serializeToStream(r2);
            r2.writeBool(this.subscribed);
        }
    }

    public TL_phone() {
    }
}
