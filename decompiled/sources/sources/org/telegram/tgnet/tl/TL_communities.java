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

/* loaded from: classes6.dex */
public class TL_communities {

    /* loaded from: classes.dex */
    public static class CommunityPeer extends TLObject {
        public static final int constructor = 1981030077;
        public boolean can_view_history;
        public int flags;
        public TLRPC.Peer peer;
        public boolean visible;

        public CommunityPeer() {
        }

        public static CommunityPeer TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == 1981030077) goto L5;
            CommunityPeer r0 = null;
        L7:
            return (CommunityPeer) TLObject.TLdeserialize(CommunityPeer.class, r0, r2, r3, r4);
        L5:
            r0 = new CommunityPeer();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.can_view_history = TLObject.hasFlag(r0, 4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            this.visible = r3.readBool(r4);
        L5:
            this.peer = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 4, this.can_view_history);
            this.flags = r0;
            r4.writeInt32(r0);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            r4.writeBool(this.visible);
        L5:
            this.peer.serializeToStream(r4);
        }
    }

    public static class CommunityPeerRequest extends TLObject {
        public static final int constructor = 2078997125;
        public int date;
        public int flags;
        public TLRPC.Peer peer;
        public long requested_by;
        public boolean visible;

        public CommunityPeerRequest() {
        }

        public static CommunityPeerRequest TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == 2078997125) goto L5;
            CommunityPeerRequest r0 = null;
        L7:
            return (CommunityPeerRequest) TLObject.TLdeserialize(CommunityPeerRequest.class, r0, r2, r3, r4);
        L5:
            r0 = new CommunityPeerRequest();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.visible = TLObject.hasFlag(r0, 1);
            this.peer = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.requested_by = r3.readInt64(r4);
            this.date = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.visible);
            this.flags = r0;
            r4.writeInt32(r0);
            this.peer.serializeToStream(r4);
            r4.writeInt64(this.requested_by);
            r4.writeInt32(this.date);
        }
    }

    /* loaded from: classes.dex */
    public static class ParticipantJoinedChats extends TLObject {
        public static final int constructor = -1921494742;
        public ArrayList<TLRPC.Chat> chats;
        public ArrayList<Long> creator_chat_ids;
        public ArrayList<Long> joined_chat_ids;
        public ArrayList<TLRPC.User> users;

        public ParticipantJoinedChats() {
            this.creator_chat_ids = new ArrayList();
            this.joined_chat_ids = new ArrayList();
            this.chats = new ArrayList();
            this.users = new ArrayList();
        }

        public static ParticipantJoinedChats TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == (-1921494742)) goto L5;
            ParticipantJoinedChats r0 = null;
        L7:
            return (ParticipantJoinedChats) TLObject.TLdeserialize(ParticipantJoinedChats.class, r0, r2, r3, r4);
        L5:
            r0 = new ParticipantJoinedChats();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.creator_chat_ids = Vector.deserializeLong(r2, r3);
            this.joined_chat_ids = Vector.deserializeLong(r2, r3);
            this.chats = Vector.deserialize(r2, new TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1(), r3);
            this.users = Vector.deserialize(r2, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serializeLong(r2, this.creator_chat_ids);
            Vector.serializeLong(r2, this.joined_chat_ids);
            Vector.serialize(r2, this.chats);
            Vector.serialize(r2, this.users);
        }
    }

    /* loaded from: classes.dex */
    public static class PeerLinkRequests extends TLObject {
        public static final int constructor = 574926765;
        public ArrayList<TLRPC.Chat> chats;
        public int flags;
        public String next_offset;
        public ArrayList<CommunityPeerRequest> requests;
        public int total_count;
        public ArrayList<TLRPC.User> users;

        public PeerLinkRequests() {
            this.requests = new ArrayList();
            this.chats = new ArrayList();
            this.users = new ArrayList();
        }

        public static PeerLinkRequests TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == 574926765) goto L5;
            PeerLinkRequests r0 = null;
        L7:
            return (PeerLinkRequests) TLObject.TLdeserialize(PeerLinkRequests.class, r0, r2, r3, r4);
        L5:
            r0 = new PeerLinkRequests();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.total_count = r3.readInt32(r4);
            this.requests = Vector.deserialize(r3, new TL_communities$PeerLinkRequests$$ExternalSyntheticLambda0(), r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            this.next_offset = r3.readString(r4);
        L5:
            this.chats = Vector.deserialize(r3, new TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1(), r4);
            this.users = Vector.deserialize(r3, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = this.flags;
            if (this.next_offset == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 1, r1);
            this.flags = r02;
            r4.writeInt32(r02);
            r4.writeInt32(this.total_count);
            Vector.serialize(r4, this.requests);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L9;
            r4.writeString(this.next_offset);
        L9:
            Vector.serialize(r4, this.chats);
            Vector.serialize(r4, this.users);
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_communities_create extends TLMethod<TLRPC.Updates> {
        public static final int constructor = -1506256404;
        public String about;
        public int flags;
        public boolean hidden;
        public TLRPC.InputPeer peer;
        public String title;

        public TL_communities_create() {
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
            int r0 = TLObject.setFlag(this.flags, 2, this.hidden);
            this.flags = r0;
            if (this.about == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 1, r1);
            this.flags = r02;
            r4.writeInt32(r02);
            r4.writeString(this.title);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L9;
            r4.writeString(this.about);
        L9:
            this.peer.serializeToStream(r4);
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_communities_getJoinedCommunities extends TLMethod<TLRPC.messages_Chats> {
        public static final int constructor = -1503401936;

        public TL_communities_getJoinedCommunities() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public TLRPC.messages_Chats deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.messages_Chats.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_communities_getParticipantJoinedChats extends TLMethod<ParticipantJoinedChats> {
        public static final int constructor = -125916245;
        public TLRPC.InputChannel community;
        public TLRPC.InputPeer participant;

        public TL_communities_getParticipantJoinedChats() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public ParticipantJoinedChats deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return ParticipantJoinedChats.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.community.serializeToStream(r2);
            this.participant.serializeToStream(r2);
        }
    }

    public static class TL_communities_getPeerLinkRequests extends TLMethod<PeerLinkRequests> {
        public static final int constructor = -1820904636;
        public TLRPC.InputChannel community;
        public int limit;
        public String offset;

        public TL_communities_getPeerLinkRequests() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public PeerLinkRequests deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return PeerLinkRequests.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.community.serializeToStream(r2);
            r2.writeString(this.offset);
            r2.writeInt32(this.limit);
        }
    }

    public static class TL_communities_toggleAllPeerLinkRequestApproval extends TLMethod<TLRPC.Bool> {
        public static final int constructor = -1075585731;
        public TLRPC.InputChannel community;
        public int flags;
        public boolean reject;

        public TL_communities_toggleAllPeerLinkRequestApproval() {
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
            int r0 = TLObject.setFlag(this.flags, 1, this.reject);
            this.flags = r0;
            r4.writeInt32(r0);
            this.community.serializeToStream(r4);
        }
    }

    public static class TL_communities_toggleCommunityCollapsedInDialogs extends TLMethod<TLRPC.Updates> {
        public static final int constructor = -681122838;
        public boolean collapsed;
        public TLRPC.InputChannel community;
        public int flags;

        public TL_communities_toggleCommunityCollapsedInDialogs() {
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
            int r0 = TLObject.setFlag(this.flags, 1, this.collapsed);
            this.flags = r0;
            r4.writeInt32(r0);
            this.community.serializeToStream(r4);
        }
    }

    public static class TL_communities_toggleParticipantBanned extends TLMethod<TLRPC.Bool> {
        public static final int constructor = -1721258737;
        public TLRPC.InputChannel community;
        public int flags;
        public TLRPC.InputPeer participant;
        public boolean unban;

        public TL_communities_toggleParticipantBanned() {
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
            int r0 = TLObject.setFlag(this.flags, 1, this.unban);
            this.flags = r0;
            r4.writeInt32(r0);
            this.community.serializeToStream(r4);
            this.participant.serializeToStream(r4);
        }
    }

    public static class TL_communities_togglePeerLink extends TLMethod<TLRPC.Bool> {
        public static final int constructor = 1936576490;
        public TLRPC.InputChannel community;
        public boolean deleted;
        public int flags;
        public boolean hidden;
        public TLRPC.InputPeer peer;
        public boolean visible;

        public TL_communities_togglePeerLink() {
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
            int r0 = TLObject.setFlag(this.flags, 1, this.visible);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.hidden);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 4, this.deleted);
            this.flags = r03;
            r4.writeInt32(r03);
            this.community.serializeToStream(r4);
            this.peer.serializeToStream(r4);
        }
    }

    public static class TL_communities_togglePeerLinkRequestApproval extends TLMethod<TLRPC.Bool> {
        public static final int constructor = -1937630808;
        public TLRPC.InputChannel community;
        public int flags;
        public TLRPC.InputPeer peer;
        public boolean reject;

        public TL_communities_togglePeerLinkRequestApproval() {
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
            int r0 = TLObject.setFlag(this.flags, 1, this.reject);
            this.flags = r0;
            r4.writeInt32(r0);
            this.community.serializeToStream(r4);
            this.peer.serializeToStream(r4);
        }
    }

    private TL_communities() {
    }
}
