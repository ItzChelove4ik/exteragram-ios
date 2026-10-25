package org.telegram.tgnet.tl;

import java.util.ArrayList;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.MessagesStorage$$ExternalSyntheticLambda117;
import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.TLRPC$TL_channel$$ExternalSyntheticLambda1;
import org.telegram.tgnet.TLRPC$TL_config$$ExternalSyntheticLambda0;
import org.telegram.tgnet.TLRPC$TL_messageMediaPaidMedia$$ExternalSyntheticLambda0;
import org.telegram.tgnet.TLRPC$TL_messages_getDialogUnreadMarks$$ExternalSyntheticLambda0;
import org.telegram.tgnet.TLRPC$TL_messages_quickReplies$$ExternalSyntheticLambda0;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.Vector$$ExternalSyntheticLambda7;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_ephemeral;
import org.telegram.tgnet.tl.TL_phone;
import org.telegram.tgnet.tl.TL_stars;

/* loaded from: classes.dex */
public class TL_update {

    /* loaded from: classes6.dex */
    public static class TL_updateAiComposeTones extends TLRPC.Update {
        public static final int constructor = -1945136645;

        public TL_updateAiComposeTones() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateAttachMenuBots extends TLRPC.Update {
        public static final int constructor = 397910539;

        public TL_updateAttachMenuBots() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_updateBotCommands extends TLRPC.Update {
        public static final int constructor = 1299263278;
        public long bot_id;
        public ArrayList<TLRPC.BotCommand> commands;
        public TLRPC.Peer peer;

        public TL_updateBotCommands() {
            this.commands = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.peer = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.bot_id = r3.readInt64(r4);
            this.commands = Vector.deserialize(r3, new TL_bots$TL_botInfo$$ExternalSyntheticLambda0(), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            this.peer.serializeToStream(r3);
            r3.writeInt64(this.bot_id);
            Vector.serialize(r3, this.commands);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateBotPurchasedPaidMedia extends TLRPC.Update {
        public static final int constructor = 675009298;
        public String payload;
        public int qts;
        public long user_id;

        public TL_updateBotPurchasedPaidMedia() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.user_id = r3.readInt64(r4);
            this.payload = r3.readString(r4);
            this.qts = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.user_id);
            r3.writeString(this.payload);
            r3.writeInt32(this.qts);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateBotSubscriptionExpire extends TLRPC.Update {
        public static final int constructor = -1464975695;
        public String payload;
        public int qts;
        public int until_date;
        public long user_id;

        public TL_updateBotSubscriptionExpire() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.user_id = r3.readInt64(r4);
            this.payload = r3.readString(r4);
            this.until_date = r3.readInt32(r4);
            this.qts = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.user_id);
            r3.writeString(this.payload);
            r3.writeInt32(this.until_date);
            r3.writeInt32(this.qts);
        }
    }

    public static class TL_updateChannel extends TLRPC.Update {
        public static final int constructor = 1666927625;
        public long channel_id;

        public TL_updateChannel() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.channel_id = r1.readInt64(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.channel_id);
        }
    }

    public static class TL_updateChannelAvailableMessages extends TLRPC.Update {
        public static final int constructor = -1304443240;
        public int available_min_id;
        public long channel_id;

        public TL_updateChannelAvailableMessages() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.channel_id = r3.readInt64(r4);
            this.available_min_id = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.channel_id);
            r3.writeInt32(this.available_min_id);
        }
    }

    public static class TL_updateChannelMessageForwards extends TLRPC.Update {
        public static final int constructor = -761649164;
        public long channel_id;
        public int forwards;
        public int id;

        public TL_updateChannelMessageForwards() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.channel_id = r3.readInt64(r4);
            this.id = r3.readInt32(r4);
            this.forwards = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.channel_id);
            r3.writeInt32(this.id);
            r3.writeInt32(this.forwards);
        }
    }

    public static class TL_updateChannelMessageViews extends TLRPC.Update {
        public static final int constructor = -232346616;
        public long channel_id;
        public int id;
        public int views;

        public TL_updateChannelMessageViews() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.channel_id = r3.readInt64(r4);
            this.id = r3.readInt32(r4);
            this.views = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.channel_id);
            r3.writeInt32(this.id);
            r3.writeInt32(this.views);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateChannelParticipant extends TLRPC.Update {
        public static final int constructor = -1738720581;
        public long actor_id;
        public long channel_id;
        public int date;
        public int flags;
        public TLRPC.ExportedChatInvite invite;
        public TLRPC.ChannelParticipant new_participant;
        public TLRPC.ChannelParticipant prev_participant;
        public int qts;
        public long user_id;
        public boolean via_chatlist;

        public TL_updateChannelParticipant() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.channel_id = r3.readInt64(r4);
            this.date = r3.readInt32(r4);
            this.actor_id = r3.readInt64(r4);
            this.user_id = r3.readInt64(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.prev_participant = TLRPC.ChannelParticipant.TLdeserialize(r3, r3.readInt32(r4), r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.new_participant = TLRPC.ChannelParticipant.TLdeserialize(r3, r3.readInt32(r4), r4);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L11;
            this.invite = TLRPC.ExportedChatInvite.TLdeserialize(r3, r3.readInt32(r4), r4);
        L11:
            this.via_chatlist = TLObject.hasFlag(this.flags, 8);
            this.qts = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 8, this.via_chatlist);
            this.flags = r0;
            r4.writeInt32(r0);
            r4.writeInt64(this.channel_id);
            r4.writeInt32(this.date);
            r4.writeInt64(this.actor_id);
            r4.writeInt64(this.user_id);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.prev_participant.serializeToStream(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.new_participant.serializeToStream(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L11;
            this.invite.serializeToStream(r4);
        L11:
            r4.writeInt32(this.qts);
        }
    }

    public static class TL_updateChannelReadMessagesContents extends TLRPC.Update {
        public static final int constructor = 636691703;
        public long channel_id;
        public int flags;
        public ArrayList<Integer> messages;
        public TLRPC.Peer saved_peer_id;
        public int top_msg_id;

        public TL_updateChannelReadMessagesContents() {
            this.messages = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.channel_id = r3.readInt64(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.top_msg_id = r3.readInt32(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L8;
            this.saved_peer_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L8:
            this.messages = Vector.deserializeInt(r3, r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeInt64(this.channel_id);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeInt32(this.top_msg_id);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L8;
            this.saved_peer_id.serializeToStream(r3);
        L8:
            Vector.serializeInt(r3, this.messages);
        }
    }

    public static class TL_updateChannelTooLong extends TLRPC.Update {
        public static final int constructor = 277713951;
        public long channel_id;
        public int flags;
        public int pts;

        public TL_updateChannelTooLong() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.channel_id = r3.readInt64(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.pts = r3.readInt32(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeInt64(this.channel_id);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeInt32(this.pts);
            return;
        }
    }

    public static class TL_updateChannelUserTyping extends TLRPC.Update {
        public static final int constructor = -1937192669;
        public TLRPC.SendMessageAction action;
        public long channel_id;
        public int flags;
        public TLRPC.Peer from_id;
        public int top_msg_id;

        public TL_updateChannelUserTyping() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.channel_id = r3.readInt64(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            this.top_msg_id = r3.readInt32(r4);
        L5:
            this.from_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.action = TLRPC.SendMessageAction.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeInt64(this.channel_id);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            r3.writeInt32(this.top_msg_id);
        L5:
            this.from_id.serializeToStream(r3);
            this.action.serializeToStream(r3);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateChannelViewForumAsMessages extends TLRPC.Update {
        public static final int constructor = 129403168;
        public long channel_id;
        public boolean enabled;

        public TL_updateChannelViewForumAsMessages() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.channel_id = r3.readInt64(r4);
            this.enabled = r3.readBool(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.channel_id);
            r3.writeBool(this.enabled);
        }
    }

    public static class TL_updateChannelWebPage extends TLRPC.Update {
        public static final int constructor = 791390623;
        public long channel_id;
        public int pts;
        public int pts_count;
        public TLRPC.WebPage webpage;

        public TL_updateChannelWebPage() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.channel_id = r3.readInt64(r4);
            this.webpage = TLRPC.WebPage.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.pts = r3.readInt32(r4);
            this.pts_count = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.channel_id);
            this.webpage.serializeToStream(r3);
            r3.writeInt32(this.pts);
            r3.writeInt32(this.pts_count);
        }
    }

    public static class TL_updateChat extends TLRPC.Update {
        public static final int constructor = -124097970;
        public long chat_id;

        public TL_updateChat() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.chat_id = r1.readInt64(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.chat_id);
        }
    }

    public static class TL_updateChatDefaultBannedRights extends TLRPC.Update {
        public static final int constructor = 1421875280;
        public TLRPC.TL_chatBannedRights default_banned_rights;
        public TLRPC.Peer peer;
        public int version;

        public TL_updateChatDefaultBannedRights() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.peer = TLRPC.Peer.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.default_banned_rights = TLRPC.TL_chatBannedRights.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.version = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            this.default_banned_rights.serializeToStream(r2);
            r2.writeInt32(this.version);
        }
    }

    public static class TL_updateChatParticipantAdd extends TLRPC.Update {
        public static final int constructor = 1037718609;
        public long chat_id;
        public int date;
        public long inviter_id;
        public long user_id;
        public int version;

        public TL_updateChatParticipantAdd() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.chat_id = r3.readInt64(r4);
            this.user_id = r3.readInt64(r4);
            this.inviter_id = r3.readInt64(r4);
            this.date = r3.readInt32(r4);
            this.version = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.chat_id);
            r3.writeInt64(this.user_id);
            r3.writeInt64(this.inviter_id);
            r3.writeInt32(this.date);
            r3.writeInt32(this.version);
        }
    }

    public static class TL_updateChatParticipantAdmin extends TLRPC.Update {
        public static final int constructor = -674602590;
        public long chat_id;
        public boolean is_admin;
        public long user_id;
        public int version;

        public TL_updateChatParticipantAdmin() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.chat_id = r3.readInt64(r4);
            this.user_id = r3.readInt64(r4);
            this.is_admin = r3.readBool(r4);
            this.version = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.chat_id);
            r3.writeInt64(this.user_id);
            r3.writeBool(this.is_admin);
            r3.writeInt32(this.version);
        }
    }

    public static class TL_updateChatParticipantDelete extends TLRPC.Update {
        public static final int constructor = -483443337;
        public long chat_id;
        public long user_id;
        public int version;

        public TL_updateChatParticipantDelete() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.chat_id = r3.readInt64(r4);
            this.user_id = r3.readInt64(r4);
            this.version = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.chat_id);
            r3.writeInt64(this.user_id);
            r3.writeInt32(this.version);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateChatParticipantRank extends TLRPC.Update {
        public static final int constructor = -1115461703;
        public long chat_id;
        public String rank;
        public long user_id;
        public int version;

        public TL_updateChatParticipantRank() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.chat_id = r3.readInt64(r4);
            this.user_id = r3.readInt64(r4);
            this.rank = r3.readString(r4);
            this.version = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.chat_id);
            r3.writeInt64(this.user_id);
            r3.writeString(this.rank);
            r3.writeInt32(this.version);
        }
    }

    public static class TL_updateChatParticipants extends TLRPC.Update {
        public static final int constructor = 125178264;
        public TLRPC.ChatParticipants participants;

        public TL_updateChatParticipants() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.participants = TLRPC.ChatParticipants.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.participants.serializeToStream(r2);
        }
    }

    public static class TL_updateChatUserTyping extends TLRPC.Update {
        public static final int constructor = -2092401936;
        public TLRPC.SendMessageAction action;
        public long chat_id;
        public TLRPC.Peer from_id;

        public TL_updateChatUserTyping() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.chat_id = r3.readInt64(r4);
            this.from_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.action = TLRPC.SendMessageAction.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.chat_id);
            this.from_id.serializeToStream(r3);
            this.action.serializeToStream(r3);
        }
    }

    public static class TL_updateConfig extends TLRPC.Update {
        public static final int constructor = -1574314746;

        public TL_updateConfig() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_updateContactsReset extends TLRPC.Update {
        public static final int constructor = 1887741886;

        public TL_updateContactsReset() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_updateDcOptions extends TLRPC.Update {
        public static final int constructor = -1906403213;
        public ArrayList<TLRPC.TL_dcOption> dc_options;

        public TL_updateDcOptions() {
            this.dc_options = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.dc_options = Vector.deserialize(r2, new TLRPC$TL_config$$ExternalSyntheticLambda0(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.dc_options);
        }
    }

    public static class TL_updateDeleteChannelMessages extends TLRPC.Update {
        public static final int constructor = -1020437742;
        public long channel_id;
        public ArrayList<Integer> messages;
        public int pts;
        public int pts_count;

        public TL_updateDeleteChannelMessages() {
            this.messages = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.channel_id = r3.readInt64(r4);
            this.messages = Vector.deserializeInt(r3, r4);
            this.pts = r3.readInt32(r4);
            this.pts_count = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.channel_id);
            Vector.serializeInt(r3, this.messages);
            r3.writeInt32(this.pts);
            r3.writeInt32(this.pts_count);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateDeleteEphemeralMessages extends TLRPC.Update {
        public static final int constructor = 1457257720;
        public ArrayList<Integer> ids;
        public TLRPC.Peer peer;

        public TL_updateDeleteEphemeralMessages() {
            this.ids = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.peer = TLRPC.Peer.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.ids = Vector.deserializeInt(r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            Vector.serializeInt(r2, this.ids);
        }
    }

    public static class TL_updateDeleteGroupCallMessages extends TLRPC.Update {
        public static final int constructor = 1048963372;
        public TLRPC.InputGroupCall call;
        public ArrayList<Integer> messages;

        public TL_updateDeleteGroupCallMessages() {
            this.messages = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.call = TLRPC.InputGroupCall.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.messages = Vector.deserializeInt(r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.call.serializeToStream(r2);
            Vector.serializeInt(r2, this.messages);
        }
    }

    public static class TL_updateDeleteMessages extends TLRPC.Update {
        public static final int constructor = -1576161051;
        public ArrayList<Integer> messages;
        public int pts;
        public int pts_count;

        public TL_updateDeleteMessages() {
            this.messages = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.messages = Vector.deserializeInt(r2, r3);
            this.pts = r2.readInt32(r3);
            this.pts_count = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serializeInt(r2, this.messages);
            r2.writeInt32(this.pts);
            r2.writeInt32(this.pts_count);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateDeleteQuickReply extends TLRPC.Update {
        public static final int constructor = 1407644140;
        public int shortcut_id;

        public TL_updateDeleteQuickReply() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.shortcut_id = r1.readInt32(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.shortcut_id);
        }
    }

    public static class TL_updateDeleteQuickReplyMessages extends TLRPC.Update {
        public static final int constructor = 1450174413;
        public ArrayList<Integer> messages;
        public int shortcut_id;

        public TL_updateDeleteQuickReplyMessages() {
            this.messages = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.shortcut_id = r2.readInt32(r3);
            this.messages = Vector.deserializeInt(r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.shortcut_id);
            Vector.serializeInt(r2, this.messages);
        }
    }

    public static class TL_updateDeleteScheduledMessages extends TLRPC.Update {
        public static final int constructor = -223929981;
        public int flags;
        public ArrayList<Integer> messages;
        public TLRPC.Peer peer;
        public ArrayList<Integer> sent_messages;

        public TL_updateDeleteScheduledMessages() {
            this.messages = new ArrayList();
            this.sent_messages = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.peer = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.messages = Vector.deserializeInt(r3, r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.sent_messages = Vector.deserializeInt(r3, r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            this.peer.serializeToStream(r3);
            Vector.serializeInt(r3, this.messages);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            Vector.serializeInt(r3, this.sent_messages);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateDialogFilter extends TLRPC.Update {
        public static final int constructor = 654302845;
        public TLRPC.DialogFilter filter;
        public int flags;
        public int id;

        public TL_updateDialogFilter() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.id = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.filter = TLRPC.DialogFilter.TLdeserialize(r3, r3.readInt32(r4), r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeInt32(this.id);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.filter.serializeToStream(r3);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateDialogFilterOrder extends TLRPC.Update {
        public static final int constructor = -1512627963;
        public ArrayList<Integer> order;

        public TL_updateDialogFilterOrder() {
            this.order = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.order = Vector.deserializeInt(r1, r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serializeInt(r2, this.order);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateDialogFilters extends TLRPC.Update {
        public static final int constructor = 889491791;

        public TL_updateDialogFilters() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_updateDialogPinned extends TLRPC.Update {
        public static final int constructor = 1852826908;
        public int flags;
        public int folder_id;
        public TLRPC.DialogPeer peer;
        public boolean pinned;

        public TL_updateDialogPinned() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.pinned = TLObject.hasFlag(r0, 1);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L5;
            this.folder_id = r3.readInt32(r4);
        L5:
            this.peer = TLRPC.DialogPeer.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.pinned);
            this.flags = r0;
            r4.writeInt32(r0);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L5;
            r4.writeInt32(this.folder_id);
        L5:
            this.peer.serializeToStream(r4);
        }
    }

    public static class TL_updateDialogUnreadMark extends TLRPC.Update {
        public static final int constructor = -1235684802;
        public int flags;
        public TLRPC.DialogPeer peer;
        public TLRPC.Peer saved_peer_id;
        public boolean unread;

        public TL_updateDialogUnreadMark() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.unread = TLObject.hasFlag(r0, 1);
            this.peer = TLRPC.DialogPeer.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L6;
            this.saved_peer_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.unread);
            this.flags = r0;
            r4.writeInt32(r0);
            this.peer.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L6;
            this.saved_peer_id.serializeToStream(r4);
            return;
        }
    }

    public static class TL_updateDraftMessage extends TLRPC.Update {
        public static final int constructor = -302247650;
        public TLRPC.DraftMessage draft;
        public int flags;
        public TLRPC.Peer peer;
        public TLRPC.Peer saved_peer_id;
        public int top_msg_id;

        public TL_updateDraftMessage() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.peer = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.top_msg_id = r3.readInt32(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L8;
            this.saved_peer_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L8:
            this.draft = TLRPC.DraftMessage.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            this.peer.serializeToStream(r3);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeInt32(this.top_msg_id);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L8;
            this.saved_peer_id.serializeToStream(r3);
        L8:
            this.draft.serializeToStream(r3);
        }
    }

    public static class TL_updateEditChannelMessage extends TLRPC.Update {
        public static final int constructor = 457133559;
        public TLRPC.Message message;
        public int pts;
        public int pts_count;

        public TL_updateEditChannelMessage() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.message = TLRPC.Message.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.pts = r2.readInt32(r3);
            this.pts_count = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.message.serializeToStream(r2);
            r2.writeInt32(this.pts);
            r2.writeInt32(this.pts_count);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateEditEphemeralMessage extends TLRPC.Update {
        public static final int constructor = 1270583041;
        public TL_ephemeral.EphemeralMessage message;

        public TL_updateEditEphemeralMessage() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.message = TL_ephemeral.EphemeralMessage.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.message.serializeToStream(r2);
        }
    }

    public static class TL_updateEditMessage extends TLRPC.Update {
        public static final int constructor = -469536605;
        public TLRPC.Message message;
        public int pts;
        public int pts_count;

        public TL_updateEditMessage() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.message = TLRPC.Message.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.pts = r2.readInt32(r3);
            this.pts_count = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.message.serializeToStream(r2);
            r2.writeInt32(this.pts);
            r2.writeInt32(this.pts_count);
        }
    }

    public static class TL_updateEmojiGameInfo extends TLRPC.Update {
        public static final int constructor = -73640838;
        public TLRPC.EmojiGameInfo info;

        public TL_updateEmojiGameInfo() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.info = TLRPC.EmojiGameInfo.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.info.serializeToStream(r2);
        }
    }

    public static class TL_updateEncryptedChatTyping extends TLRPC.Update {
        public static final int constructor = 386986326;
        public int chat_id;

        public TL_updateEncryptedChatTyping() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.chat_id = r1.readInt32(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.chat_id);
        }
    }

    public static class TL_updateEncryptedMessagesRead extends TLRPC.Update {
        public static final int constructor = 956179895;
        public int chat_id;
        public int date;
        public int max_date;

        public TL_updateEncryptedMessagesRead() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.chat_id = r2.readInt32(r3);
            this.max_date = r2.readInt32(r3);
            this.date = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.chat_id);
            r2.writeInt32(this.max_date);
            r2.writeInt32(this.date);
        }
    }

    public static class TL_updateEncryption extends TLRPC.Update {
        public static final int constructor = -1264392051;
        public TLRPC.EncryptedChat chat;
        public int date;

        public TL_updateEncryption() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.chat = TLRPC.EncryptedChat.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.date = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.chat.serializeToStream(r2);
            r2.writeInt32(this.date);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateEphemeralBotCallbackQuery extends TLRPC.Update {
        public static final int constructor = 2081454550;
        public long chat_instance;
        public byte[] data;
        public int flags;
        public TL_ephemeral.EphemeralMessage message;
        public int msg_id;
        public TLRPC.Peer peer;
        public long query_id;
        public long user_id;

        public TL_updateEphemeralBotCallbackQuery() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.query_id = r3.readInt64(r4);
            this.user_id = r3.readInt64(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            this.peer = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.msg_id = r3.readInt32(r4);
            this.data = r3.readByteArray(r4);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L8;
            this.chat_instance = r3.readInt64(r4);
        L8:
            this.message = TL_ephemeral.EphemeralMessage.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r9) {
            r9.writeInt32(constructor);
            int r0 = this.flags;
            boolean r2 = false;
            if (this.peer == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 1, r1);
            this.flags = r02;
            if (this.chat_instance == 0) goto L9;
            r2 = true;
        L9:
            int r03 = TLObject.setFlag(r02, 2, r2);
            this.flags = r03;
            r9.writeInt32(r03);
            r9.writeInt64(this.query_id);
            r9.writeInt64(this.user_id);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L12;
            this.peer.serializeToStream(r9);
        L12:
            r9.writeInt32(this.msg_id);
            r9.writeByteArray(this.data);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L15;
            r9.writeInt64(this.chat_instance);
        L15:
            this.message.serializeToStream(r9);
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_updateFavedStickers extends TLRPC.Update {
        public static final int constructor = -451831443;

        public TL_updateFavedStickers() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_updateFolderPeers extends TLRPC.Update {
        public static final int constructor = 422972864;
        public ArrayList<TLRPC.TL_folderPeer> folder_peers;
        public int pts;
        public int pts_count;

        public TL_updateFolderPeers() {
            this.folder_peers = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.folder_peers = Vector.deserialize(r2, new TL_update$TL_updateFolderPeers$$ExternalSyntheticLambda0(), r3);
            this.pts = r2.readInt32(r3);
            this.pts_count = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.folder_peers);
            r2.writeInt32(this.pts);
            r2.writeInt32(this.pts_count);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateGeoLiveViewed extends TLRPC.Update {
        public static final int constructor = -2027964103;
        public int msg_id;
        public TLRPC.Peer peer;

        public TL_updateGeoLiveViewed() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.peer = TLRPC.Peer.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.msg_id = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            r2.writeInt32(this.msg_id);
        }
    }

    public static class TL_updateGroupCall extends TLRPC.Update {
        public static final int constructor = -1658710304;
        public TLRPC.GroupCall call;
        public int flags;
        public boolean live_story;
        public TLRPC.Peer peer;

        public TL_updateGroupCall() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.live_story = TLObject.hasFlag(r0, 4);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L5;
            this.peer = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.call = TLRPC.GroupCall.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 4, this.live_story);
            this.flags = r0;
            r4.writeInt32(r0);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L5;
            this.peer.serializeToStream(r4);
        L5:
            this.call.serializeToStream(r4);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateGroupCallChainBlocks extends TLRPC.Update {
        public static final int constructor = -1535694705;
        public ArrayList<byte[]> blocks;
        public TLRPC.InputGroupCall call;
        public int next_offset;
        public int sub_chain_id;

        public TL_updateGroupCallChainBlocks() {
            this.blocks = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.call = TLRPC.InputGroupCall.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.sub_chain_id = r2.readInt32(r3);
            this.blocks = Vector.deserializeByteArray(r2, r3);
            this.next_offset = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            this.call.serializeToStream(r3);
            r3.writeInt32(this.sub_chain_id);
            Vector.serialize(r3, new Vector$$ExternalSyntheticLambda7(r3), this.blocks);
            r3.writeInt32(this.next_offset);
        }
    }

    public static class TL_updateGroupCallConnection extends TLRPC.Update {
        public static final int constructor = 192428418;
        public int flags;
        public TLRPC.TL_dataJSON params;
        public boolean presentation;

        public TL_updateGroupCallConnection() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.presentation = TLObject.hasFlag(r0, 1);
            this.params = TLRPC.TL_dataJSON.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.presentation);
            this.flags = r0;
            r4.writeInt32(r0);
            this.params.serializeToStream(r4);
        }
    }

    public static class TL_updateGroupCallEncryptedMessage extends TLRPC.Update {
        public static final int constructor = -917002394;
        public TLRPC.InputGroupCall call;
        public byte[] encrypted_message;
        public TLRPC.Peer from_id;

        public TL_updateGroupCallEncryptedMessage() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.call = TLRPC.InputGroupCall.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.from_id = TLRPC.Peer.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.encrypted_message = r2.readByteArray(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.call.serializeToStream(r2);
            this.from_id.serializeToStream(r2);
            r2.writeByteArray(this.encrypted_message);
        }
    }

    public static class TL_updateGroupCallMessage extends TLRPC.Update {
        public static final int constructor = -667783411;
        public TLRPC.InputGroupCall call;
        public TLRPC.GroupCallMessage message;

        public TL_updateGroupCallMessage() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.call = TLRPC.InputGroupCall.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.message = TLRPC.GroupCallMessage.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.call.serializeToStream(r2);
            this.message.serializeToStream(r2);
        }
    }

    public static class TL_updateGroupCallParticipants extends TLRPC.Update {
        public static final int constructor = -219423922;
        public TLRPC.InputGroupCall call;
        public ArrayList<TLRPC.GroupCallParticipant> participants;
        public int version;

        public TL_updateGroupCallParticipants() {
            this.participants = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.call = TLRPC.InputGroupCall.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.participants = Vector.deserialize(r2, new TL_phone$groupCall$$ExternalSyntheticLambda0(), r3);
            this.version = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.call.serializeToStream(r2);
            Vector.serialize(r2, this.participants);
            r2.writeInt32(this.version);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateGroupCall_layer216 extends TL_updateGroupCall {
        public static final int constructor = -1747565759;

        public TL_updateGroupCall_layer216() {
        }

        @Override // org.telegram.tgnet.tl.TL_update.TL_updateGroupCall, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r4, boolean r5) {
            int r0 = r4.readInt32(r5);
            this.flags = r0;
            this.live_story = TLObject.hasFlag(r0, 4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            long r02 = r4.readInt64(r5);
            TLRPC.TL_peerChannel r2 = new TLRPC.TL_peerChannel();
            this.peer = r2;
            r2.channel_id = -r02;
        L5:
            this.call = TLRPC.GroupCall.TLdeserialize(r4, r4.readInt32(r5), r5);
        }

        @Override // org.telegram.tgnet.tl.TL_update.TL_updateGroupCall, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 4, this.live_story);
            this.flags = r0;
            r4.writeInt32(r0);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            r4.writeInt64(-DialogObject.getPeerDialogId(this.peer));
        L5:
            this.call.serializeToStream(r4);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateJoinChatWebViewDecision extends TLRPC.Update {
        public static final int constructor = -1112768912;
        public TLRPC.Peer peer;
        public long query_id;
        public TLRPC.JoinChatBotResult result;

        public TL_updateJoinChatWebViewDecision() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.peer = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.query_id = r3.readInt64(r4);
            this.result = TLRPC.JoinChatBotResult.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            this.peer.serializeToStream(r3);
            r3.writeInt64(this.query_id);
            this.result.serializeToStream(r3);
        }
    }

    public static class TL_updateLangPack extends TLRPC.Update {
        public static final int constructor = 1442983757;
        public TLRPC.TL_langPackDifference difference;

        public TL_updateLangPack() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.difference = TLRPC.TL_langPackDifference.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.difference.serializeToStream(r2);
        }
    }

    public static class TL_updateLangPackTooLong extends TLRPC.Update {
        public static final int constructor = 1180041828;
        public String lang_code;

        public TL_updateLangPackTooLong() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.lang_code = r1.readString(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.lang_code);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateLoginToken extends TLRPC.Update {
        public static final int constructor = 1448076945;

        public TL_updateLoginToken() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateManagedBot extends TLRPC.Update {
        public static final int constructor = 1216408986;
        public long bot_id;
        public int qts;
        public long user_id;

        public TL_updateManagedBot() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.user_id = r3.readInt64(r4);
            this.bot_id = r3.readInt64(r4);
            this.qts = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.user_id);
            r3.writeInt64(this.bot_id);
            r3.writeInt32(this.qts);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateMessageExtendedMedia extends TLRPC.Update {
        public static final int constructor = -710666460;
        public ArrayList<TLRPC.MessageExtendedMedia> extended_media;
        public int msg_id;
        public TLRPC.Peer peer;

        public TL_updateMessageExtendedMedia() {
            this.extended_media = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.peer = TLRPC.Peer.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.msg_id = r2.readInt32(r3);
            this.extended_media = Vector.deserialize(r2, new TLRPC$TL_messageMediaPaidMedia$$ExternalSyntheticLambda0(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            r2.writeInt32(this.msg_id);
            Vector.serialize(r2, this.extended_media);
        }
    }

    public static class TL_updateMessageID extends TLRPC.Update {
        public static final int constructor = 1318109142;
        public int id;
        public long random_id;

        public TL_updateMessageID() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.id = r2.readInt32(r3);
            this.random_id = r2.readInt64(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.id);
            r3.writeInt64(this.random_id);
        }
    }

    public static class TL_updateMessagePoll extends TLRPC.Update {
        public static final int constructor = -699641301;
        public int flags;
        public int msg_id;
        public TLRPC.Peer peer;
        public TLRPC.Poll poll;
        public long poll_id;
        public TLRPC.PollResults results;
        public int top_msg_id;

        public TL_updateMessagePoll() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            if (TLObject.hasFlag(r0, 2) == false) goto L6;
            this.peer = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.msg_id = r3.readInt32(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L8;
            this.top_msg_id = r3.readInt32(r4);
        L8:
            this.poll_id = r3.readInt64(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L11;
            this.poll = TLRPC.Poll.TLdeserialize(r3, r4);
        L11:
            this.results = TLRPC.PollResults.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r5) {
            r5.writeInt32(constructor);
            int r0 = this.flags;
            boolean r2 = false;
            if (this.poll == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 1, r1);
            this.flags = r02;
            if (this.peer == null) goto L9;
            r2 = true;
        L9:
            int r03 = TLObject.setFlag(r02, 2, r2);
            this.flags = r03;
            r5.writeInt32(r03);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L13;
            this.peer.serializeToStream(r5);
            r5.writeInt32(this.msg_id);
        L13:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L15;
            r5.writeInt32(this.top_msg_id);
        L15:
            r5.writeInt64(this.poll_id);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L18;
            this.poll.serializeToStream(r5);
        L18:
            this.results.serializeToStream(r5);
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateMessagePollVote extends TLRPC.Update {
        public static final int constructor = 1989799956;
        public ArrayList<byte[]> options;
        public TLRPC.Peer peer;
        public long poll_id;
        public ArrayList<Integer> positions;
        public int qts;

        public TL_updateMessagePollVote() {
            this.options = new ArrayList();
            this.positions = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.poll_id = r3.readInt64(r4);
            this.peer = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.options = Vector.deserializeByteArray(r3, r4);
            this.positions = Vector.deserializeInt(r3, r4);
            this.qts = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.poll_id);
            this.peer.serializeToStream(r3);
            Vector.serializeByteArray(r3, this.options);
            Vector.serializeInt(r3, this.positions);
            r3.writeInt32(this.qts);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateMessageReactions extends TLRPC.Update {
        public static final int constructor = 506035194;
        public int flags;
        public int msg_id;
        public TLRPC.Peer peer;
        public TLRPC.TL_messageReactions reactions;
        public TLRPC.Peer saved_peer_id;
        public int top_msg_id;
        public boolean updateUnreadState;

        public TL_updateMessageReactions() {
            this.updateUnreadState = true;
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.peer = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.msg_id = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.top_msg_id = r3.readInt32(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L8;
            this.saved_peer_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L8:
            this.reactions = TLRPC.MessageReactions.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            this.peer.serializeToStream(r3);
            r3.writeInt32(this.msg_id);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeInt32(this.top_msg_id);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L8;
            this.saved_peer_id.serializeToStream(r3);
        L8:
            this.reactions.serializeToStream(r3);
        }
    }

    public static class TL_updateMonoForumNoPaidException extends TLRPC.Update {
        public static final int constructor = -1618924792;
        public long channel_id;
        public boolean exception;
        public int flags;
        public TLRPC.Peer saved_peer_id;

        public TL_updateMonoForumNoPaidException() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.exception = TLObject.hasFlag(r0, 1);
            this.channel_id = r3.readInt64(r4);
            this.saved_peer_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.exception);
            this.flags = r0;
            r4.writeInt32(r0);
            r4.writeInt64(this.channel_id);
            this.saved_peer_id.serializeToStream(r4);
        }
    }

    public static class TL_updateMoveStickerSetToTop extends TLRPC.Update {
        public static final int constructor = -2030252155;
        public boolean emojis;
        public int flags;
        public boolean masks;
        public long stickerset;

        public TL_updateMoveStickerSetToTop() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.masks = TLObject.hasFlag(r0, 1);
            this.emojis = TLObject.hasFlag(this.flags, 2);
            this.stickerset = r3.readInt64(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.masks);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.emojis);
            this.flags = r02;
            r4.writeInt32(r02);
            r4.writeInt64(this.stickerset);
        }
    }

    public static class TL_updateNewAuthorization extends TLRPC.Update {
        public static final int constructor = -1991136273;
        public int date;
        public String device;
        public int flags;
        public long hash;
        public String location;
        public boolean unconfirmed;

        public TL_updateNewAuthorization() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r5, boolean r6) {
            int r0 = r5.readInt32(r6);
            this.flags = r0;
            this.unconfirmed = TLObject.hasFlag(r0, 1);
            this.hash = r5.readInt64(r6);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.date = r5.readInt32(r6);
            this.device = r5.readString(r6);
            this.location = r5.readString(r6);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeInt64(this.hash);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeInt32(this.date);
            r3.writeString(this.device);
            r3.writeString(this.location);
            return;
        }
    }

    public static class TL_updateNewBotConnection extends TLRPC.Update {
        public static final int constructor = -1306491994;
        public long bot_id;
        public boolean confirmed;
        public int date;
        public String device;
        public int flags;
        public String location;

        public TL_updateNewBotConnection() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.confirmed = TLObject.hasFlag(r0, 1);
            this.bot_id = r3.readInt64(r4);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L6;
            this.date = r3.readInt32(r4);
            this.device = r3.readString(r4);
            this.location = r3.readString(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.confirmed);
            this.flags = r0;
            r4.writeInt32(r0);
            r4.writeInt64(this.bot_id);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L6;
            r4.writeInt32(this.date);
            r4.writeString(this.device);
            r4.writeString(this.location);
            return;
        }
    }

    public static class TL_updateNewChannelMessage extends TLRPC.Update {
        public static final int constructor = 1656358105;
        public TLRPC.Message message;
        public int pts;
        public int pts_count;

        public TL_updateNewChannelMessage() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.message = TLRPC.Message.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.pts = r2.readInt32(r3);
            this.pts_count = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.message.serializeToStream(r2);
            r2.writeInt32(this.pts);
            r2.writeInt32(this.pts_count);
        }
    }

    public static class TL_updateNewEncryptedMessage extends TLRPC.Update {
        public static final int constructor = 314359194;
        public TLRPC.EncryptedMessage message;
        public int qts;

        public TL_updateNewEncryptedMessage() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.message = TLRPC.EncryptedMessage.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.qts = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.message.serializeToStream(r2);
            r2.writeInt32(this.qts);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateNewEphemeralMessage extends TLRPC.Update {
        public static final int constructor = 549239713;
        public TL_ephemeral.EphemeralMessage message;

        public TL_updateNewEphemeralMessage() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.message = TL_ephemeral.EphemeralMessage.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.message.serializeToStream(r2);
        }
    }

    public static class TL_updateNewMessage extends TLRPC.Update {
        public static final int constructor = 522914557;
        public TLRPC.Message message;
        public int pts;
        public int pts_count;

        public TL_updateNewMessage() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.message = TLRPC.Message.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.pts = r2.readInt32(r3);
            this.pts_count = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.message.serializeToStream(r2);
            r2.writeInt32(this.pts);
            r2.writeInt32(this.pts_count);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateNewQuickReply extends TLRPC.Update {
        public static final int constructor = -180508905;
        public TLRPC.TL_quickReply quick_reply;

        public TL_updateNewQuickReply() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.quick_reply = TLRPC.TL_quickReply.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.quick_reply.serializeToStream(r2);
        }
    }

    public static class TL_updateNewScheduledMessage extends TLRPC.Update {
        public static final int constructor = 967122427;
        public TLRPC.Message message;

        public TL_updateNewScheduledMessage() {
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

    public static class TL_updateNewStickerSet extends TLRPC.Update {
        public static final int constructor = 1753886890;
        public TLRPC.TL_messages_stickerSet stickerset;

        public TL_updateNewStickerSet() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.stickerset = TLRPC.messages_StickerSet.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.stickerset.serializeToStream(r2);
        }
    }

    public static class TL_updateNewStoryReaction extends TLRPC.Update {
        public static final int constructor = 405070859;
        public TLRPC.Peer peer;
        public TLRPC.Reaction reaction;
        public int story_id;

        public TL_updateNewStoryReaction() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.story_id = r2.readInt32(r3);
            this.peer = TLRPC.Peer.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.reaction = TLRPC.Reaction.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.story_id);
            this.peer.serializeToStream(r2);
            this.reaction.serializeToStream(r2);
        }
    }

    public static class TL_updateNotifySettings extends TLRPC.Update {
        public static final int constructor = -1094555409;
        public TLRPC.PeerNotifySettings notify_settings;
        public TLRPC.NotifyPeer peer;

        public TL_updateNotifySettings() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.peer = TLRPC.NotifyPeer.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.notify_settings = TLRPC.PeerNotifySettings.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            this.notify_settings.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updatePaidReactionPrivacy extends TLRPC.Update {
        public static final int constructor = -1955438642;
        public TL_stars.PaidReactionPrivacy privacy;

        public TL_updatePaidReactionPrivacy() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.privacy = TL_stars.PaidReactionPrivacy.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.privacy.serializeToStream(r2);
        }
    }

    public static class TL_updatePeerBlocked extends TLRPC.Update {
        public static final int constructor = -337610926;
        public boolean blocked;
        public boolean blocked_my_stories_from;
        public int flags;
        public TLRPC.Peer peer_id;

        public TL_updatePeerBlocked() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.blocked = TLObject.hasFlag(r0, 1);
            this.blocked_my_stories_from = TLObject.hasFlag(this.flags, 2);
            this.peer_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.blocked);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.blocked_my_stories_from);
            this.flags = r02;
            r4.writeInt32(r02);
            this.peer_id.serializeToStream(r4);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updatePeerHistoryTTL extends TLRPC.Update {
        public static final int constructor = -1147422299;
        public int flags;
        public TLRPC.Peer peer;
        public int ttl_period;

        public TL_updatePeerHistoryTTL() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.peer = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.ttl_period = r3.readInt32(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            this.peer.serializeToStream(r3);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeInt32(this.ttl_period);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updatePeerLocated extends TLRPC.Update {
        public static final int constructor = -1263546448;
        public ArrayList<TLRPC.PeerLocated> peers;

        public TL_updatePeerLocated() {
            this.peers = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.peers = Vector.deserialize(r2, new TL_update$TL_updatePeerLocated$$ExternalSyntheticLambda0(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.peers);
        }
    }

    public static class TL_updatePeerSettings extends TLRPC.Update {
        public static final int constructor = 1786671974;
        public TLRPC.Peer peer;
        public TLRPC.PeerSettings settings;

        public TL_updatePeerSettings() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.peer = TLRPC.Peer.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.settings = TLRPC.PeerSettings.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            this.settings.serializeToStream(r2);
        }
    }

    public static class TL_updatePeerWallpaper extends TLRPC.Update {
        public static final int constructor = -1371598819;
        public int flags;
        public TLRPC.Peer peer;
        public TLRPC.WallPaper wallpaper;
        public boolean wallpaper_overridden;

        public TL_updatePeerWallpaper() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.wallpaper_overridden = TLObject.hasFlag(r0, 2);
            this.peer = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.wallpaper = TLRPC.WallPaper.TLdeserialize(r3, r3.readInt32(r4), r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.wallpaper_overridden);
            this.flags = r0;
            r4.writeInt32(r0);
            this.peer.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.wallpaper.serializeToStream(r4);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updatePendingJoinRequests extends TLRPC.Update {
        public static final int constructor = 1885586395;
        public TLRPC.Peer peer;
        public ArrayList<Long> recent_requesters;
        public int requests_pending;

        public TL_updatePendingJoinRequests() {
            this.recent_requesters = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.peer = TLRPC.Peer.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.requests_pending = r2.readInt32(r3);
            this.recent_requesters = Vector.deserializeLong(r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            r2.writeInt32(this.requests_pending);
            Vector.serializeLong(r2, this.recent_requesters);
        }
    }

    public static class TL_updatePhoneCall extends TLRPC.Update {
        public static final int constructor = -1425052898;
        public TL_phone.PhoneCall phone_call;

        public TL_updatePhoneCall() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.phone_call = TL_phone.PhoneCall.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.phone_call.serializeToStream(r2);
        }
    }

    public static class TL_updatePhoneCallSignalingData extends TLRPC.Update {
        public static final int constructor = 643940105;
        public byte[] data;
        public long phone_call_id;

        public TL_updatePhoneCallSignalingData() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.phone_call_id = r3.readInt64(r4);
            this.data = r3.readByteArray(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.phone_call_id);
            r3.writeByteArray(this.data);
        }
    }

    public static class TL_updatePinnedChannelMessages extends TLRPC.Update {
        public static final int constructor = 1538885128;
        public long channel_id;
        public int flags;
        public ArrayList<Integer> messages;
        public boolean pinned;
        public int pts;
        public int pts_count;

        public TL_updatePinnedChannelMessages() {
            this.messages = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.pinned = TLObject.hasFlag(r0, 1);
            this.channel_id = r3.readInt64(r4);
            this.messages = Vector.deserializeInt(r3, r4);
            this.pts = r3.readInt32(r4);
            this.pts_count = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.pinned);
            this.flags = r0;
            r4.writeInt32(r0);
            r4.writeInt64(this.channel_id);
            Vector.serializeInt(r4, this.messages);
            r4.writeInt32(this.pts);
            r4.writeInt32(this.pts_count);
        }
    }

    public static class TL_updatePinnedDialogs extends TLRPC.Update {
        public static final int constructor = -99664734;
        public int flags;
        public int folder_id;
        public ArrayList<TLRPC.DialogPeer> order;

        public TL_updatePinnedDialogs() {
            this.order = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            if (TLObject.hasFlag(r0, 2) == false) goto L6;
            this.folder_id = r3.readInt32(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 1) == false) goto L9;
            this.order = Vector.deserialize(r3, new TLRPC$TL_messages_getDialogUnreadMarks$$ExternalSyntheticLambda0(), r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L6;
            r3.writeInt32(this.folder_id);
        L6:
            if (TLObject.hasFlag(this.flags, 1) == false) goto L9;
            Vector.serialize(r3, this.order);
            return;
        }
    }

    public static class TL_updatePinnedForumTopic extends TLRPC.Update {
        public static final int constructor = 1748708434;
        public TLRPC.Peer peer;
        public boolean pinned;
        public int topic_id;

        public TL_updatePinnedForumTopic() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.pinned = TLObject.hasFlag(r3.readInt32(r4), 1);
            this.peer = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.topic_id = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            r4.writeInt32(TLObject.setFlag(0, 1, this.pinned));
            this.peer.serializeToStream(r4);
            r4.writeInt32(this.topic_id);
        }
    }

    public static class TL_updatePinnedForumTopics extends TLRPC.Update {
        public static final int constructor = -554613808;
        public int flags;
        public ArrayList<Integer> order;
        public TLRPC.Peer peer;

        public TL_updatePinnedForumTopics() {
            this.order = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.peer = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.order = Vector.deserializeInt(r3, r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            this.peer.serializeToStream(r3);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            Vector.serializeInt(r3, this.order);
            return;
        }
    }

    public static class TL_updatePinnedMessages extends TLRPC.Update {
        public static final int constructor = -309990731;
        public int flags;
        public ArrayList<Integer> messages;
        public TLRPC.Peer peer;
        public boolean pinned;
        public int pts;
        public int pts_count;

        public TL_updatePinnedMessages() {
            this.messages = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.pinned = TLObject.hasFlag(r0, 1);
            this.peer = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.messages = Vector.deserializeInt(r3, r4);
            this.pts = r3.readInt32(r4);
            this.pts_count = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.pinned);
            this.flags = r0;
            r4.writeInt32(r0);
            this.peer.serializeToStream(r4);
            Vector.serializeInt(r4, this.messages);
            r4.writeInt32(this.pts);
            r4.writeInt32(this.pts_count);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updatePinnedSavedDialogs extends TLRPC.Update {
        public static final int constructor = 1751942566;
        public int flags;
        public ArrayList<TLRPC.DialogPeer> order;

        public TL_updatePinnedSavedDialogs() {
            this.order = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            if (TLObject.hasFlag(r0, 1) == false) goto L6;
            this.order = Vector.deserialize(r3, new TLRPC$TL_messages_getDialogUnreadMarks$$ExternalSyntheticLambda0(), r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            Vector.serialize(r3, this.order);
            return;
        }
    }

    public static class TL_updatePrivacy extends TLRPC.Update {
        public static final int constructor = -298113238;
        public TLRPC.PrivacyKey key;
        public ArrayList<TLRPC.PrivacyRule> rules;

        public TL_updatePrivacy() {
            this.rules = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.key = TLRPC.PrivacyKey.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.rules = Vector.deserialize(r2, new TL_account$privacyRules$$ExternalSyntheticLambda0(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.key.serializeToStream(r2);
            Vector.serialize(r2, this.rules);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateQuickReplies extends TLRPC.Update {
        public static final int constructor = -112784718;
        public ArrayList<TLRPC.TL_quickReply> quick_replies;

        public TL_updateQuickReplies() {
            this.quick_replies = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.quick_replies = Vector.deserialize(r2, new TLRPC$TL_messages_quickReplies$$ExternalSyntheticLambda0(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.quick_replies);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateQuickReplyMessage extends TLRPC.Update {
        public static final int constructor = 1040518415;
        public TLRPC.Message message;

        public TL_updateQuickReplyMessage() {
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

    /* loaded from: classes6.dex */
    public static class TL_updateReadChannelDiscussionInbox extends TLRPC.Update {
        public static final int constructor = -693004986;
        public long broadcast_id;
        public int broadcast_post;
        public long channel_id;
        public int flags;
        public int read_max_id;
        public int top_msg_id;

        public TL_updateReadChannelDiscussionInbox() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r5, boolean r6) {
            this.flags = r5.readInt32(r6);
            this.channel_id = r5.readInt64(r6);
            this.top_msg_id = r5.readInt32(r6);
            this.read_max_id = r5.readInt32(r6);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.broadcast_id = r5.readInt64(r6);
        L6:
            if (TLObject.hasFlag(this.flags, 1) == false) goto L9;
            this.broadcast_post = r5.readInt32(r6);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r5) {
            r5.writeInt32(constructor);
            r5.writeInt32(this.flags);
            r5.writeInt64(this.channel_id);
            r5.writeInt32(this.top_msg_id);
            r5.writeInt32(this.read_max_id);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r5.writeInt64(this.broadcast_id);
        L6:
            if (TLObject.hasFlag(this.flags, 1) == false) goto L9;
            r5.writeInt32(this.broadcast_post);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateReadChannelDiscussionOutbox extends TLRPC.Update {
        public static final int constructor = 1767677564;
        public long channel_id;
        public int read_max_id;
        public int top_msg_id;

        public TL_updateReadChannelDiscussionOutbox() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.channel_id = r3.readInt64(r4);
            this.top_msg_id = r3.readInt32(r4);
            this.read_max_id = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.channel_id);
            r3.writeInt32(this.top_msg_id);
            r3.writeInt32(this.read_max_id);
        }
    }

    public static class TL_updateReadChannelInbox extends TLRPC.Update {
        public static final int constructor = -1842450928;
        public long channel_id;
        public int flags;
        public int folder_id;
        public int max_id;
        public int pts;
        public int still_unread_count;

        public TL_updateReadChannelInbox() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            if (TLObject.hasFlag(r0, 1) == false) goto L5;
            this.folder_id = r3.readInt32(r4);
        L5:
            this.channel_id = r3.readInt64(r4);
            this.max_id = r3.readInt32(r4);
            this.still_unread_count = r3.readInt32(r4);
            this.pts = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            r3.writeInt32(this.folder_id);
        L5:
            r3.writeInt64(this.channel_id);
            r3.writeInt32(this.max_id);
            r3.writeInt32(this.still_unread_count);
            r3.writeInt32(this.pts);
        }
    }

    public static class TL_updateReadChannelOutbox extends TLRPC.Update {
        public static final int constructor = -1218471511;
        public long channel_id;
        public int max_id;

        public TL_updateReadChannelOutbox() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.channel_id = r3.readInt64(r4);
            this.max_id = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.channel_id);
            r3.writeInt32(this.max_id);
        }
    }

    public static class TL_updateReadFeaturedEmojiStickers extends TLRPC.Update {
        public static final int constructor = -78886548;

        public TL_updateReadFeaturedEmojiStickers() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_updateReadFeaturedStickers extends TLRPC.Update {
        public static final int constructor = 1461528386;

        public TL_updateReadFeaturedStickers() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_updateReadHistoryInbox extends TLRPC.Update {
        public static final int constructor = -1635468135;
        public int flags;
        public int folder_id;
        public int max_id;
        public TLRPC.Peer peer;
        public int pts;
        public int pts_count;
        public int still_unread_count;
        public int top_msg_id;

        public TL_updateReadHistoryInbox() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            if (TLObject.hasFlag(r0, 1) == false) goto L5;
            this.folder_id = r3.readInt32(r4);
        L5:
            this.peer = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L8;
            this.top_msg_id = r3.readInt32(r4);
        L8:
            this.max_id = r3.readInt32(r4);
            this.still_unread_count = r3.readInt32(r4);
            this.pts = r3.readInt32(r4);
            this.pts_count = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            r3.writeInt32(this.folder_id);
        L5:
            this.peer.serializeToStream(r3);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L8;
            r3.writeInt32(this.top_msg_id);
        L8:
            r3.writeInt32(this.max_id);
            r3.writeInt32(this.still_unread_count);
            r3.writeInt32(this.pts);
            r3.writeInt32(this.pts_count);
        }
    }

    public static class TL_updateReadHistoryOutbox extends TLRPC.Update {
        public static final int constructor = 791617983;
        public int max_id;
        public TLRPC.Peer peer;
        public int pts;
        public int pts_count;

        public TL_updateReadHistoryOutbox() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.peer = TLRPC.Peer.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.max_id = r2.readInt32(r3);
            this.pts = r2.readInt32(r3);
            this.pts_count = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            r2.writeInt32(this.max_id);
            r2.writeInt32(this.pts);
            r2.writeInt32(this.pts_count);
        }
    }

    public static class TL_updateReadMessagesContents extends TLRPC.Update {
        public static final int constructor = -131960447;
        public int date;
        public int flags;
        public ArrayList<Integer> messages;
        public int pts;
        public int pts_count;

        public TL_updateReadMessagesContents() {
            this.messages = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.messages = Vector.deserializeInt(r3, r4);
            this.pts = r3.readInt32(r4);
            this.pts_count = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.date = r3.readInt32(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            Vector.serializeInt(r3, this.messages);
            r3.writeInt32(this.pts);
            r3.writeInt32(this.pts_count);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeInt32(this.date);
            return;
        }
    }

    public static class TL_updateReadMonoForumInbox extends TLRPC.Update {
        public static final int constructor = 2008081266;
        public long channel_id;
        public int read_max_id;
        public TLRPC.Peer saved_peer_id;

        public TL_updateReadMonoForumInbox() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.channel_id = r3.readInt64(r4);
            this.saved_peer_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.read_max_id = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.channel_id);
            this.saved_peer_id.serializeToStream(r3);
            r3.writeInt32(this.read_max_id);
        }
    }

    public static class TL_updateReadMonoForumOutbox extends TLRPC.Update {
        public static final int constructor = -1532521610;
        public long channel_id;
        public int read_max_id;
        public TLRPC.Peer saved_peer_id;

        public TL_updateReadMonoForumOutbox() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.channel_id = r3.readInt64(r4);
            this.saved_peer_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.read_max_id = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.channel_id);
            this.saved_peer_id.serializeToStream(r3);
            r3.writeInt32(this.read_max_id);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateRecentEmojiStatuses extends TLRPC.Update {
        public static final int constructor = 821314523;

        public TL_updateRecentEmojiStatuses() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_updateRecentReactions extends TLRPC.Update {
        public static final int constructor = 1870160884;

        public TL_updateRecentReactions() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_updateRecentStickers extends TLRPC.Update {
        public static final int constructor = -1706939360;

        public TL_updateRecentStickers() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateSavedDialogPinned extends TLRPC.Update {
        public static final int constructor = -1364222348;
        public int flags;
        public TLRPC.DialogPeer peer;
        public boolean pinned;

        public TL_updateSavedDialogPinned() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.pinned = TLObject.hasFlag(r0, 1);
            this.peer = TLRPC.DialogPeer.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.pinned);
            this.flags = r0;
            r4.writeInt32(r0);
            this.peer.serializeToStream(r4);
        }
    }

    public static class TL_updateSavedGifs extends TLRPC.Update {
        public static final int constructor = -1821035490;

        public TL_updateSavedGifs() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_updateSavedReactionTags extends TLRPC.Update {
        public static final int constructor = 969307186;

        public TL_updateSavedReactionTags() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateSavedRingtones extends TLRPC.Update {
        public static final int constructor = 1960361625;

        public TL_updateSavedRingtones() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateSentPhoneCode extends TLRPC.Update {
        public static final int constructor = 1347068303;
        public TLRPC.auth_SentCode sent_code;

        public TL_updateSentPhoneCode() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.sent_code = TLRPC.auth_SentCode.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.sent_code.serializeToStream(r2);
        }
    }

    public static class TL_updateSentStoryReaction extends TLRPC.Update {
        public static final int constructor = 2103604867;
        public TLRPC.Peer peer;
        public TLRPC.Reaction reaction;
        public int story_id;

        public TL_updateSentStoryReaction() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.peer = TLRPC.Peer.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.story_id = r2.readInt32(r3);
            this.reaction = TLRPC.Reaction.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            r2.writeInt32(this.story_id);
            this.reaction.serializeToStream(r2);
        }
    }

    public static class TL_updateServiceNotification extends TLRPC.Update {
        public static final int constructor = -337352679;
        public ArrayList<TLRPC.MessageEntity> entities;
        public int flags;
        public int inbox_date;
        public boolean invert_media;
        public TLRPC.MessageMedia media;
        public String message;
        public boolean popup;
        public String type;

        public TL_updateServiceNotification() {
            this.entities = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.popup = TLObject.hasFlag(r0, 1);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L5;
            this.inbox_date = r3.readInt32(r4);
        L5:
            this.invert_media = TLObject.hasFlag(this.flags, 4);
            this.type = r3.readString(r4);
            this.message = r3.readString(r4);
            this.media = TLRPC.MessageMedia.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.entities = Vector.deserialize(r3, new MessagesStorage$$ExternalSyntheticLambda117(), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.popup);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 4, this.invert_media);
            this.flags = r02;
            r4.writeInt32(r02);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L5;
            r4.writeInt32(this.inbox_date);
        L5:
            r4.writeString(this.type);
            r4.writeString(this.message);
            this.media.serializeToStream(r4);
            Vector.serialize(r4, this.entities);
        }
    }

    public static class TL_updateStarGiftAuctionState extends TLRPC.Update {
        public static final int constructor = 1222788802;
        public long gift_id;
        public TL_stars.StarGiftAuctionState state;

        public TL_updateStarGiftAuctionState() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.gift_id = r3.readInt64(r4);
            this.state = TL_stars.StarGiftAuctionState.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.gift_id);
            this.state.serializeToStream(r3);
        }
    }

    public static class TL_updateStarGiftAuctionUserState extends TLRPC.Update {
        public static final int constructor = -598150370;
        public long gift_id;
        public TL_stars.TL_StarGiftAuctionUserState user_state;

        public TL_updateStarGiftAuctionUserState() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.gift_id = r3.readInt64(r4);
            this.user_state = TL_stars.TL_StarGiftAuctionUserState.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.gift_id);
            this.user_state.serializeToStream(r3);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateStarGiftCraftFail extends TLRPC.Update {
        public static final int constructor = -1408818108;

        public TL_updateStarGiftCraftFail() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            super.readParams(r1, r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_updateStarsBalance extends TLRPC.Update {
        public static final int constructor = 1317053305;
        public TL_stars.StarsAmount balance;

        public TL_updateStarsBalance() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.balance = TL_stars.StarsAmount.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.balance.serializeToStream(r2);
        }
    }

    public static class TL_updateStarsRevenueStatus extends TLRPC.Update {
        public static final int constructor = -1518030823;
        public TLRPC.Peer peer;
        public TLRPC.TL_starsRevenueStatus status;

        public TL_updateStarsRevenueStatus() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.peer = TLRPC.Peer.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.status = TLRPC.TL_starsRevenueStatus.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            this.status.serializeToStream(r2);
        }
    }

    public static class TL_updateStickerSets extends TLRPC.Update {
        public static final int constructor = 834816008;
        public boolean emojis;
        public int flags;
        public boolean masks;

        public TL_updateStickerSets() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            int r12 = r1.readInt32(r2);
            this.flags = r12;
            this.masks = TLObject.hasFlag(r12, 1);
            this.emojis = TLObject.hasFlag(this.flags, 2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.masks);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.emojis);
            this.flags = r02;
            r4.writeInt32(r02);
        }
    }

    public static class TL_updateStickerSetsOrder extends TLRPC.Update {
        public static final int constructor = 196268545;
        public boolean emojis;
        public int flags;
        public boolean masks;
        public ArrayList<Long> order;

        public TL_updateStickerSetsOrder() {
            this.order = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.masks = TLObject.hasFlag(r0, 1);
            this.emojis = TLObject.hasFlag(this.flags, 2);
            this.order = Vector.deserializeLong(r3, r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.masks);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.emojis);
            this.flags = r02;
            r4.writeInt32(r02);
            Vector.serializeLong(r4, this.order);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateStoryID extends TLRPC.Update {
        public static final int constructor = 468923833;
        public int id;
        public long random_id;

        public TL_updateStoryID() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.id = r2.readInt32(r3);
            this.random_id = r2.readInt64(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.id);
            r3.writeInt64(this.random_id);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateTheme extends TLRPC.Update {
        public static final int constructor = -2112423005;
        public TLRPC.Theme theme;

        public TL_updateTheme() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.theme = TLRPC.Theme.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.theme.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateTranscribeAudio extends TLRPC.Update {
        public static final int constructor = -2006880112;
        public int flags;
        public boolean isFinal;
        public String text;
        public long transcription_id;

        public TL_updateTranscribeAudio() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.isFinal = TLObject.hasFlag(r0, 1);
            this.transcription_id = r3.readInt64(r4);
            this.text = r3.readString(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.isFinal);
            this.flags = r0;
            r4.writeInt32(r0);
            r4.writeInt64(this.transcription_id);
            r4.writeString(this.text);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateTranscribedAudio extends TLRPC.Update {
        public static final int constructor = 8703322;
        public int flags;
        public int msg_id;
        public TLRPC.Peer peer;
        public boolean pending;
        public String text;
        public long transcription_id;

        public TL_updateTranscribedAudio() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.pending = TLObject.hasFlag(r0, 1);
            this.peer = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.msg_id = r3.readInt32(r4);
            this.transcription_id = r3.readInt64(r4);
            this.text = r3.readString(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.pending);
            this.flags = r0;
            r4.writeInt32(r0);
            this.peer.serializeToStream(r4);
            r4.writeInt32(this.msg_id);
            r4.writeInt64(this.transcription_id);
            r4.writeString(this.text);
        }
    }

    public static class TL_updateUser extends TLRPC.Update {
        public static final int constructor = 542282808;
        public long user_id;

        public TL_updateUser() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.user_id = r1.readInt64(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.user_id);
        }
    }

    public static class TL_updateUserEmojiStatus extends TLRPC.Update {
        public static final int constructor = 674706841;
        public TLRPC.EmojiStatus emoji_status;
        public long user_id;

        public TL_updateUserEmojiStatus() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.user_id = r3.readInt64(r4);
            this.emoji_status = TLRPC.EmojiStatus.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.user_id);
            this.emoji_status.serializeToStream(r3);
        }
    }

    public static class TL_updateUserName extends TLRPC.Update {
        public static final int constructor = -1484486364;
        public String first_name;
        public String last_name;
        public long user_id;
        public ArrayList<TLRPC.TL_username> usernames;

        public TL_updateUserName() {
            this.usernames = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.user_id = r3.readInt64(r4);
            this.first_name = r3.readString(r4);
            this.last_name = r3.readString(r4);
            this.usernames = Vector.deserialize(r3, new TLRPC$TL_channel$$ExternalSyntheticLambda1(), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.user_id);
            r3.writeString(this.first_name);
            r3.writeString(this.last_name);
            Vector.serialize(r3, this.usernames);
        }
    }

    public static class TL_updateUserPhone extends TLRPC.Update {
        public static final int constructor = 88680979;
        public String phone;
        public long user_id;

        public TL_updateUserPhone() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.user_id = r3.readInt64(r4);
            this.phone = r3.readString(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.user_id);
            r3.writeString(this.phone);
        }
    }

    public static class TL_updateUserPhoto extends TLRPC.Update {
        public static final int constructor = -232290676;
        public int date;
        public TLRPC.UserProfilePhoto photo;
        public boolean previous;
        public long user_id;

        public TL_updateUserPhoto() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.user_id = r3.readInt64(r4);
            this.date = r3.readInt32(r4);
            this.photo = TLRPC.UserProfilePhoto.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.previous = r3.readBool(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.user_id);
            r3.writeInt32(this.date);
            this.photo.serializeToStream(r3);
            r3.writeBool(this.previous);
        }
    }

    public static class TL_updateUserStatus extends TLRPC.Update {
        public static final int constructor = -440534818;
        public TLRPC.UserStatus status;
        public long user_id;

        public TL_updateUserStatus() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.user_id = r3.readInt64(r4);
            this.status = TLRPC.UserStatus.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.user_id);
            this.status.serializeToStream(r3);
        }
    }

    public static class TL_updateUserTyping extends TLRPC.Update {
        public static final int constructor = 706199388;
        public TLRPC.SendMessageAction action;
        public int flags;
        public int top_msg_id;
        public long user_id;

        public TL_updateUserTyping() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.user_id = r3.readInt64(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            this.top_msg_id = r3.readInt32(r4);
        L5:
            this.action = TLRPC.SendMessageAction.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeInt64(this.user_id);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            r3.writeInt32(this.top_msg_id);
        L5:
            this.action.serializeToStream(r3);
        }
    }

    public static class TL_updateWebBrowserException extends TLRPC.Update {
        public static final int constructor = 335872721;
        public boolean delete;
        public TL_account.WebDomainException exception;
        public int flags;
        public boolean open_external_browser;

        public TL_updateWebBrowserException() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.delete = TLObject.hasFlag(r0, 2);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            this.open_external_browser = r3.readBool(r4);
        L5:
            this.exception = TL_account.WebDomainException.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.delete);
            this.flags = r0;
            r4.writeInt32(r0);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            r4.writeBool(this.open_external_browser);
        L5:
            this.exception.serializeToStream(r4);
        }
    }

    public static class TL_updateWebBrowserSettings extends TLRPC.Update {
        public static final int constructor = -1013306658;
        public boolean display_close_button;
        public int flags;
        public boolean open_external_browser;

        public TL_updateWebBrowserSettings() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            int r12 = r1.readInt32(r2);
            this.flags = r12;
            this.open_external_browser = TLObject.hasFlag(r12, 1);
            this.display_close_button = TLObject.hasFlag(this.flags, 2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.open_external_browser);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.display_close_button);
            this.flags = r02;
            r4.writeInt32(r02);
        }
    }

    public static class TL_updateWebPage extends TLRPC.Update {
        public static final int constructor = 2139689491;
        public int pts;
        public int pts_count;
        public TLRPC.WebPage webpage;

        public TL_updateWebPage() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.webpage = TLRPC.WebPage.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.pts = r2.readInt32(r3);
            this.pts_count = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.webpage.serializeToStream(r2);
            r2.writeInt32(this.pts);
            r2.writeInt32(this.pts_count);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateWebViewResultSent extends TLRPC.Update {
        public static final int constructor = 361936797;
        public long query_id;

        public TL_updateWebViewResultSent() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.query_id = r1.readInt64(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.query_id);
        }
    }

    private TL_update() {
    }
}
