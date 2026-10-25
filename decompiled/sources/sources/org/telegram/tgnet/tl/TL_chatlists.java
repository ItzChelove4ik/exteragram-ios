package org.telegram.tgnet.tl;

import java.util.ArrayList;
import org.telegram.messenger.MessagePreviewParams$$ExternalSyntheticLambda0;
import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1;
import org.telegram.tgnet.TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1;
import org.telegram.tgnet.Vector;

/* loaded from: classes6.dex */
public class TL_chatlists {

    public static class TL_chatlists_chatlistInvite extends chatlist_ChatlistInvite {
        public static final int constructor = -250687953;
        public ArrayList<TLRPC.Chat> chats;
        public String emoticon;
        public int flags;
        public ArrayList<TLRPC.Peer> peers;
        public TLRPC.TL_textWithEntities title;
        public boolean title_noanimate;
        public ArrayList<TLRPC.User> users;

        public TL_chatlists_chatlistInvite() {
            this.title = new TLRPC.TL_textWithEntities();
            this.peers = new ArrayList();
            this.chats = new ArrayList();
            this.users = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.title_noanimate = TLObject.hasFlag(r0, 2);
            this.title = TLRPC.TL_textWithEntities.TLdeserialize(r3, r3.readInt32(r4), r4);
            if ((this.flags & 1) <= 0) goto L5;
            this.emoticon = r3.readString(r4);
        L5:
            this.peers = Vector.deserialize(r3, new MessagePreviewParams$$ExternalSyntheticLambda0(), r4);
            this.chats = Vector.deserialize(r3, new TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1(), r4);
            this.users = Vector.deserialize(r3, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            boolean r0 = this.title_noanimate;
            int r1 = this.flags;
            if (r0 == false) goto L5;
            int r02 = r1 | 2;
        L6:
            this.flags = r02;
            r3.writeInt32(r02);
            this.title.serializeToStream(r3);
            if ((this.flags & 1) <= 0) goto L9;
            r3.writeString(this.emoticon);
        L9:
            Vector.serialize(r3, this.peers);
            Vector.serialize(r3, this.chats);
            Vector.serialize(r3, this.users);
            return;
        L5:
            r02 = r1 & (-3);
            goto L6
        }
    }

    public static class TL_chatlists_chatlistInviteAlready extends chatlist_ChatlistInvite {
        public static final int constructor = -91752871;
        public ArrayList<TLRPC.Peer> already_peers;
        public ArrayList<TLRPC.Chat> chats;
        public int filter_id;
        public ArrayList<TLRPC.Peer> missing_peers;
        public ArrayList<TLRPC.User> users;

        public TL_chatlists_chatlistInviteAlready() {
            this.missing_peers = new ArrayList();
            this.already_peers = new ArrayList();
            this.chats = new ArrayList();
            this.users = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.filter_id = r2.readInt32(r3);
            this.missing_peers = Vector.deserialize(r2, new MessagePreviewParams$$ExternalSyntheticLambda0(), r3);
            this.already_peers = Vector.deserialize(r2, new MessagePreviewParams$$ExternalSyntheticLambda0(), r3);
            this.chats = Vector.deserialize(r2, new TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1(), r3);
            this.users = Vector.deserialize(r2, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.filter_id);
            Vector.serialize(r2, this.missing_peers);
            Vector.serialize(r2, this.already_peers);
            Vector.serialize(r2, this.chats);
            Vector.serialize(r2, this.users);
        }
    }

    public static class TL_chatlists_chatlistInvite_layer195 extends TL_chatlists_chatlistInvite {
        public static final int constructor = 500007837;

        public TL_chatlists_chatlistInvite_layer195() {
        }

        @Override // org.telegram.tgnet.tl.TL_chatlists.TL_chatlists_chatlistInvite, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            TLRPC.TL_textWithEntities r0 = new TLRPC.TL_textWithEntities();
            this.title = r0;
            r0.text = r3.readString(r4);
            if ((this.flags & 1) <= 0) goto L5;
            this.emoticon = r3.readString(r4);
        L5:
            this.peers = Vector.deserialize(r3, new MessagePreviewParams$$ExternalSyntheticLambda0(), r4);
            this.chats = Vector.deserialize(r3, new TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1(), r4);
            this.users = Vector.deserialize(r3, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r4);
        }

        @Override // org.telegram.tgnet.tl.TL_chatlists.TL_chatlists_chatlistInvite, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.flags);
            this.title.serializeToStream(r2);
            if ((this.flags & 1) <= 0) goto L5;
            r2.writeString(this.emoticon);
        L5:
            Vector.serialize(r2, this.peers);
            Vector.serialize(r2, this.chats);
            Vector.serialize(r2, this.users);
        }
    }

    /* loaded from: classes.dex */
    public static class TL_chatlists_chatlistUpdates extends TLObject {
        public static final int constructor = -1816295539;
        public ArrayList<TLRPC.Chat> chats;
        public ArrayList<TLRPC.Peer> missing_peers;
        public ArrayList<TLRPC.User> users;

        public TL_chatlists_chatlistUpdates() {
            this.missing_peers = new ArrayList();
            this.chats = new ArrayList();
            this.users = new ArrayList();
        }

        public static TL_chatlists_chatlistUpdates TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-1816295539) == r3) goto L5;
            TL_chatlists_chatlistUpdates r0 = null;
        L7:
            return (TL_chatlists_chatlistUpdates) TLObject.TLdeserialize(TL_chatlists_chatlistUpdates.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_chatlists_chatlistUpdates();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.missing_peers = Vector.deserialize(r2, new MessagePreviewParams$$ExternalSyntheticLambda0(), r3);
            this.chats = Vector.deserialize(r2, new TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1(), r3);
            this.users = Vector.deserialize(r2, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.missing_peers);
            Vector.serialize(r2, this.chats);
            Vector.serialize(r2, this.users);
        }
    }

    public static class TL_chatlists_checkChatlistInvite extends TLObject {
        public static final int constructor = 1103171583;
        public String slug;

        public TL_chatlists_checkChatlistInvite() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return chatlist_ChatlistInvite.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.slug);
        }
    }

    public static class TL_chatlists_deleteExportedInvite extends TLObject {
        public static final int constructor = 1906072670;
        public TL_inputChatlistDialogFilter chatlist;
        public String slug;

        public TL_chatlists_deleteExportedInvite() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.chatlist.serializeToStream(r2);
            r2.writeString(this.slug);
        }
    }

    public static class TL_chatlists_editExportedInvite extends TLObject {
        public static final int constructor = 1698543165;
        public TL_inputChatlistDialogFilter chatlist;
        public int flags;
        public ArrayList<TLRPC.InputPeer> peers;
        public boolean revoked;
        public String slug;
        public String title;

        public TL_chatlists_editExportedInvite() {
            this.peers = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_exportedChatlistInvite.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.revoked);
            this.flags = r0;
            r4.writeInt32(r0);
            this.chatlist.serializeToStream(r4);
            r4.writeString(this.slug);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L6;
            r4.writeString(this.title);
        L6:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            Vector.serialize(r4, this.peers);
            return;
        }
    }

    public static class TL_chatlists_exportChatlistInvite extends TLObject {
        public static final int constructor = -2072885362;
        public TL_inputChatlistDialogFilter chatlist;
        public ArrayList<TLRPC.InputPeer> peers;
        public String title;

        public TL_chatlists_exportChatlistInvite() {
            this.peers = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_chatlists_exportedChatlistInvite.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.chatlist.serializeToStream(r2);
            r2.writeString(this.title);
            Vector.serialize(r2, this.peers);
        }
    }

    public static class TL_chatlists_exportedChatlistInvite extends TLObject {
        public static final int constructor = 283567014;
        public TLRPC.DialogFilter filter;
        public TL_exportedChatlistInvite invite;

        public TL_chatlists_exportedChatlistInvite() {
        }

        public static TL_chatlists_exportedChatlistInvite TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (283567014 == r3) goto L5;
            TL_chatlists_exportedChatlistInvite r0 = null;
        L7:
            return (TL_chatlists_exportedChatlistInvite) TLObject.TLdeserialize(TL_chatlists_exportedChatlistInvite.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_chatlists_exportedChatlistInvite();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.filter = TLRPC.DialogFilter.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.invite = TL_exportedChatlistInvite.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.filter.serializeToStream(r2);
            this.invite.serializeToStream(r2);
        }
    }

    public static class TL_chatlists_exportedInvites extends TLObject {
        public static final int constructor = 279670215;
        public ArrayList<TLRPC.Chat> chats;
        public ArrayList<TL_exportedChatlistInvite> invites;
        public ArrayList<TLRPC.User> users;

        public TL_chatlists_exportedInvites() {
            this.invites = new ArrayList();
            this.chats = new ArrayList();
            this.users = new ArrayList();
        }

        public static TL_chatlists_exportedInvites TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (279670215 == r3) goto L5;
            TL_chatlists_exportedInvites r0 = null;
        L7:
            return (TL_chatlists_exportedInvites) TLObject.TLdeserialize(TL_chatlists_exportedInvites.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_chatlists_exportedInvites();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.invites = Vector.deserialize(r2, new TL_chatlists$TL_chatlists_exportedInvites$$ExternalSyntheticLambda0(), r3);
            this.chats = Vector.deserialize(r2, new TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1(), r3);
            this.users = Vector.deserialize(r2, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.invites);
            Vector.serialize(r2, this.chats);
            Vector.serialize(r2, this.users);
        }
    }

    public static class TL_chatlists_getChatlistUpdates extends TLObject {
        public static final int constructor = -1992190687;
        public TL_inputChatlistDialogFilter chatlist;

        public TL_chatlists_getChatlistUpdates() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_chatlists_chatlistUpdates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.chatlist.serializeToStream(r2);
        }
    }

    public static class TL_chatlists_getExportedInvites extends TLObject {
        public static final int constructor = -838608253;
        public TL_inputChatlistDialogFilter chatlist;

        public TL_chatlists_getExportedInvites() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_chatlists_exportedInvites.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.chatlist.serializeToStream(r2);
        }
    }

    public static class TL_chatlists_getLeaveChatlistSuggestions extends TLObject {
        public static final int constructor = -37955820;
        public TL_inputChatlistDialogFilter chatlist;

        public TL_chatlists_getLeaveChatlistSuggestions() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return Vector.TLDeserialize(r1, r2, r3, new MessagePreviewParams$$ExternalSyntheticLambda0());
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.chatlist.serializeToStream(r2);
        }
    }

    public static class TL_chatlists_hideChatlistUpdates extends TLObject {
        public static final int constructor = 1726252795;
        public TL_inputChatlistDialogFilter chatlist;

        public TL_chatlists_hideChatlistUpdates() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.chatlist.serializeToStream(r2);
        }
    }

    public static class TL_chatlists_joinChatlistInvite extends TLObject {
        public static final int constructor = -1498291302;
        public ArrayList<TLRPC.InputPeer> peers;
        public String slug;

        public TL_chatlists_joinChatlistInvite() {
            this.peers = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.slug);
            Vector.serialize(r2, this.peers);
        }
    }

    public static class TL_chatlists_joinChatlistUpdates extends TLObject {
        public static final int constructor = -527828747;
        public TL_inputChatlistDialogFilter chatlist;
        public ArrayList<TLRPC.InputPeer> peers;

        public TL_chatlists_joinChatlistUpdates() {
            this.peers = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.chatlist.serializeToStream(r2);
            Vector.serialize(r2, this.peers);
        }
    }

    public static class TL_chatlists_leaveChatlist extends TLObject {
        public static final int constructor = 1962598714;
        public TL_inputChatlistDialogFilter chatlist;
        public ArrayList<TLRPC.InputPeer> peers;

        public TL_chatlists_leaveChatlist() {
            this.peers = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.chatlist.serializeToStream(r2);
            Vector.serialize(r2, this.peers);
        }
    }

    public static class TL_exportedChatlistInvite extends TLObject {
        public static final int constructor = 206668204;
        public int flags;
        public ArrayList<TLRPC.Peer> peers;
        public boolean revoked;
        public String title;
        public String url;

        public TL_exportedChatlistInvite() {
            this.peers = new ArrayList();
        }

        public static TL_exportedChatlistInvite TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (206668204 == r3) goto L5;
            TL_exportedChatlistInvite r0 = null;
        L7:
            return (TL_exportedChatlistInvite) TLObject.TLdeserialize(TL_exportedChatlistInvite.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_exportedChatlistInvite();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.revoked = TLObject.hasFlag(r0, 1);
            this.title = r3.readString(r4);
            this.url = r3.readString(r4);
            this.peers = Vector.deserialize(r3, new MessagePreviewParams$$ExternalSyntheticLambda0(), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            boolean r0 = this.revoked;
            int r1 = this.flags;
            if (r0 == false) goto L5;
            int r02 = r1 | 1;
        L6:
            this.flags = r02;
            r3.writeInt32(r02);
            r3.writeString(this.title);
            r3.writeString(this.url);
            Vector.serialize(r3, this.peers);
            return;
        L5:
            r02 = r1 & (-2);
            goto L6
        }
    }

    public static class TL_inputChatlistDialogFilter extends TLObject {
        public static final int constructor = -203367885;
        public int filter_id;

        public TL_inputChatlistDialogFilter() {
        }

        public static TL_inputChatlistDialogFilter TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-203367885) == r3) goto L5;
            TL_inputChatlistDialogFilter r0 = null;
        L7:
            return (TL_inputChatlistDialogFilter) TLObject.TLdeserialize(TL_inputChatlistDialogFilter.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_inputChatlistDialogFilter();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.filter_id = r1.readInt32(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.filter_id);
        }
    }

    public static abstract class chatlist_ChatlistInvite extends TLObject {
        public chatlist_ChatlistInvite() {
        }

        public static chatlist_ChatlistInvite TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-250687953)) goto L5;
            TLObject r0 = new TL_chatlists_chatlistInvite();
        L13:
            return (chatlist_ChatlistInvite) TLObject.TLdeserialize(chatlist_ChatlistInvite.class, r0, r2, r3, r4);
        L5:
            if (r3 != (-91752871)) goto L7;
            r0 = new TL_chatlists_chatlistInviteAlready();
            goto L13
        L7:
            if (r3 == 500007837) goto L9;
            r0 = null;
            goto L13
        L9:
            r0 = new TL_chatlists_chatlistInvite_layer195();
            goto L13
        }
    }

    public TL_chatlists() {
    }
}
