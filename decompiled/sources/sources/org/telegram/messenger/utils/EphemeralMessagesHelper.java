package org.telegram.messenger.utils;

import android.util.SparseIntArray;
import androidx.collection.LongSparseArray;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.telegram.messenger.BaseController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_bots;
import org.telegram.tgnet.tl.TL_ephemeral;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.tgnet.tl.TL_update;

/* loaded from: classes6.dex */
public class EphemeralMessagesHelper extends BaseController {
    private static volatile EphemeralMessagesHelper[] Instance;

    public static class EphemeralUpdates {
        public final StructBuilder ephemeralMessagesToAdd;
        public final StructBuilder ephemeralMessagesToEdit;
        public final StructBuilder welcomeMessagesAnchor;
        public final Struct welcomeMessagesToAdd;
        public final Struct welcomeMessagesToEdit;

        public static class Struct {
            public final LongSparseArray<TLRPC.TL_messages_messages> convertedByDialog;
            public final ArrayList<TL_ephemeral.EphemeralMessage> messages;
            public final LongSparseArray<ArrayList<MessageObject>> objectsByDialog;

            /* renamed from: -$$Nest$mput, reason: not valid java name */
            public static /* bridge */ /* synthetic */ void m5188$$Nest$mput(Struct r0, TL_ephemeral.EphemeralMessage r1, TLRPC.Message r2, MessageObject r3) {
                r0.put(r1, r2, r3);
            }

            public Struct() {
                this.messages = new ArrayList();
                this.convertedByDialog = new LongSparseArray();
                this.objectsByDialog = new LongSparseArray();
            }

            private void put(TL_ephemeral.EphemeralMessage r4, TLRPC.Message r5, MessageObject r6) {
                long r0 = MessageObject.getDialogId(r5);
                this.messages.add(r4);
                TLRPC.TL_messages_messages r42 = this.convertedByDialog.get(r0);
                if (r42 != null) goto L5;
                r42 = new TLRPC.TL_messages_messages();
                this.convertedByDialog.put(r0, r42);
            L5:
                r42.messages.add(r5);
                ArrayList<MessageObject> r43 = this.objectsByDialog.get(r0);
                if (r43 != null) goto L8;
                r43 = new ArrayList();
                this.objectsByDialog.put(r0, r43);
            L8:
                r43.add(r6);
            }
        }

        public static class StructBuilder extends Struct {
            public StructBuilder() {
            }

            public void build(int r12, AbstractMap<Long, TLRPC.User> r13, AbstractMap<Long, TLRPC.Chat> r14, int r15) {
                ArrayList<TL_ephemeral.EphemeralMessage> r0 = this.messages;
                int r1 = r0.size();
                int r2 = 0;
            L3:
                if (r2 >= r1) goto L14;
                TL_ephemeral.EphemeralMessage r3 = r0.get(r2);
                r2 = r2 + 1;
                TLRPC.TL_message r6 = EphemeralMessagesHelper.convertEphemeralToFakeDefault(r3);
                int r5 = r12;
                AbstractMap<Long, TLRPC.User> r7 = r13;
                AbstractMap<Long, TLRPC.Chat> r8 = r14;
                MessageObject r4 = new MessageObject(r5, r6, r7, r8, true, true);
                long r122 = MessageObject.getDialogId(r6);
                if (r15 == 0) goto L7;
                r6.edit_date = r15;
                r6.flags |= 32768;
            L7:
                TLRPC.TL_messages_messages r142 = this.convertedByDialog.get(r122);
                if (r142 != null) goto L10;
                r142 = new TLRPC.TL_messages_messages();
                this.convertedByDialog.put(r122, r142);
            L10:
                r142.messages.add(r6);
                ArrayList<MessageObject> r143 = this.objectsByDialog.get(r122);
                if (r143 != null) goto L13;
                r143 = new ArrayList();
                this.objectsByDialog.put(r122, r143);
            L13:
                r143.add(r4);
                r12 = r5;
                r13 = r7;
                r14 = r8;
                goto L3
            }

            public boolean isEmpty() {
                return this.messages.isEmpty();
            }

            public void put(TL_ephemeral.EphemeralMessage r1) {
                this.messages.add(r1);
            }
        }

        public EphemeralUpdates() {
            this.welcomeMessagesToAdd = new Struct();
            this.welcomeMessagesToEdit = new Struct();
            this.ephemeralMessagesToAdd = new StructBuilder();
            this.ephemeralMessagesToEdit = new StructBuilder();
            this.welcomeMessagesAnchor = new StructBuilder();
        }

        public void apply(TL_update.TL_updateDeleteEphemeralMessages r1) {
            DialogObject.getPeerDialogId(r1.peer);
        }

        public void apply(TL_update.TL_updateEditEphemeralMessage r9, int r10, AbstractMap<Long, TLRPC.User> r11, AbstractMap<Long, TLRPC.Chat> r12) {
            TL_ephemeral.EphemeralMessage r92 = r9.message;
            if (r92.anchor_msg_id == 0) goto L7;
            this.welcomeMessagesAnchor.put(r92);
            return;
        L7:
            if (r92.welcome == false) goto L10;
            TLRPC.TL_message r3 = EphemeralMessagesHelper.convertEphemeralToFakeDefault(r92);
            MessageObject r1 = new MessageObject(r10, r3, r11, r12, true, true);
            r3.edit_date = ConnectionsManager.getInstance(r10).getCurrentTime();
            r3.flags |= 32768;
            Struct.m5188$$Nest$mput(this.welcomeMessagesToEdit, r92, r3, r1);
            return;
        L10:
            this.ephemeralMessagesToEdit.put(r92);
        }

        public void apply(TL_update.TL_updateNewEphemeralMessage r9, int r10, AbstractMap<Long, TLRPC.User> r11, AbstractMap<Long, TLRPC.Chat> r12) {
            TL_ephemeral.EphemeralMessage r92 = r9.message;
            if (r92.anchor_msg_id == 0) goto L7;
            this.welcomeMessagesAnchor.put(r92);
            return;
        L7:
            if (r92.welcome == false) goto L10;
            TLRPC.TL_message r3 = EphemeralMessagesHelper.convertEphemeralToFakeDefault(r92);
            MessageObject r1 = new MessageObject(r10, r3, r11, r12, true, true);
            Struct.m5188$$Nest$mput(this.welcomeMessagesToAdd, r92, r3, r1);
            return;
        L10:
            this.ephemeralMessagesToAdd.put(r92);
        }
    }

    public static class WelcomeAnchorsState {
        private final LongSparseArray<SparseIntArray> state;

        public WelcomeAnchorsState() {
            this.state = new LongSparseArray();
        }

        public SparseIntArray getAnchorBindings(long r1) {
            return this.state.get(r1);
        }

        public void put(long r2, int r4, int r5) {
            SparseIntArray r0 = this.state.get(r2);
            if (r0 != null) goto L5;
            r0 = new SparseIntArray();
            this.state.put(r2, r0);
        L5:
            r0.put(r4, r5);
        }

        public void remove(long r3, int r5, int r6) {
            SparseIntArray r0 = this.state.get(r3);
            if (r0 != null) goto L6;
            return;
        L6:
            if (r0.get(r5, -1) != r6) goto L14;
            r0.delete(r5);
            if (r0.size() != 0) goto L12;
            this.state.remove(r3);
            return;
        L12:
            return;
        }
    }

    static {
        Instance = new EphemeralMessagesHelper[16];
    }

    private EphemeralMessagesHelper(int r1) {
        super(r1);
    }

    private static TLRPC.InputReplyTo applyReplyTo(TLRPC.InputReplyTo r2) {
        if ((r2 instanceof TLRPC.TL_inputReplyToMessage) == false) goto L8;
        TLRPC.TL_inputReplyToMessage r0 = (TLRPC.TL_inputReplyToMessage) r2;
        if (MessageObject.isEphemeralMessageId(r0.reply_to_msg_id) == false) goto L9;
        TLRPC.TL_inputReplyToEphemeralMessage r22 = new TLRPC.TL_inputReplyToEphemeralMessage();
        r22.id = MessageObject.ephemeralMessageIdUnpack(r0.reply_to_msg_id);
        return r22;
    L9:
        return r2;
    L8:
        return r2;
    }

    public static TLRPC.TL_message convertEphemeralToFakeDefault(TL_ephemeral.EphemeralMessage r7) {
        TLRPC.TL_message r0 = new TLRPC.TL_message();
        r0.out = r7.out;
        r0.id = MessageObject.ephemeralMessageIdPack(r7.id);
        TLRPC.Peer r1 = r7.from_id;
        if (r1 == null) goto L5;
        r0.from_id = r1;
        r0.flags |= 256;
    L5:
        r0.peer_id = r7.peer_id;
        int r12 = r7.anchor_msg_id;
        r0.ephemeralAnchorMsgId = r12;
        if (r7.welcome == true) goto L10;
        if (r12 != 0) goto L10;
        r0.ephemeralReceiverBotId = r7.receiver_id;
    L11:
        r0.date = r7.date;
        r0.message = r7.message;
        if (r7.noforwards == false) goto L15;
        r0.noforwards = true;
        r0.flags |= 67108864;
    L15:
        if (r7.invert_media == false) goto L17;
        r0.invert_media = true;
        r0.flags |= 134217728;
    L17:
        TL_iv.RichMessage r13 = r7.rich_message;
        if (r13 == null) goto L20;
        r0.rich_message = r13;
        r0.flags2 |= 8192;
    L20:
        ArrayList<TLRPC.MessageEntity> r14 = r7.entities;
        if (r14 != null) goto L23;
    L25:
        TLRPC.MessageMedia r15 = r7.media;
        if (r15 != null) goto L28;
    L30:
        TLRPC.ReplyMarkup r16 = r7.reply_markup;
        if (r16 == null) goto L33;
        r0.reply_markup = r16;
        r0.flags |= 64;
    L33:
        long r3 = r7.via_bot_id;
        if (r3 == 0) goto L36;
        r0.via_bot_id = r3;
        r0.flags |= 2048;
    L36:
        TLRPC.MessageReplyHeader r17 = r7.reply_to;
        if (r17 == null) goto L50;
        TLRPC.MessageReplyHeader r18 = (TLRPC.MessageReplyHeader) TLObject.deepCopy(r17, new EphemeralMessagesHelper$$ExternalSyntheticLambda0());
        r0.reply_to = r18;
        if (r7.reply_to.reply_to_ephemeral == false) goto L43;
        int r32 = r18.reply_to_msg_id;
        if (r32 == 0) goto L43;
        r18.reply_to_msg_id = MessageObject.ephemeralMessageIdPack(r32);
        r0.reply_to.reply_to_msg_id |= 16;
    L43:
        TLRPC.MessageReplyHeader r19 = r0.reply_to;
        if (r19.reply_to_top_id != 0) goto L48;
        int r72 = r7.top_msg_id;
        if (r72 == 0) goto L48;
        r19.reply_to_top_id = r72;
        r19.forum_topic = true;
        r19.flags |= 2;
    L48:
        r0.flags |= 8;
    L52:
        MessageObject.getDialogId(r0);
        return r0;
    L50:
        if (r7.top_msg_id == 0) goto L52;
        TLRPC.TL_messageReplyHeader r110 = new TLRPC.TL_messageReplyHeader();
        r0.reply_to = r110;
        r110.reply_to_top_id = r7.top_msg_id;
        r110.forum_topic = true;
        r110.flags |= 2;
        r0.flags |= 8;
        goto L52
    L28:
        if (r7.rich_message != null) goto L30;
        r0.media = r15;
        r0.flags |= 512;
        goto L30
    L23:
        if (r14.isEmpty() == true) goto L25;
        r0.entities = r7.entities;
        r0.flags |= 128;
    L10:
        r0.ephemeralReceiverBotId = -1;
        goto L11
    }

    public static TL_ephemeral.EphemeralMessage convertFakeDefaultToEphemeral(TLRPC.Message r3, int r4) {
        TL_ephemeral.TL_ephemeralMessage r0 = new TL_ephemeral.TL_ephemeralMessage();
        r0.out = r3.out;
        r0.invert_media = r3.invert_media;
        r0.noforwards = r3.noforwards;
        r0.id = MessageObject.ephemeralMessageIdUnpack(r3.id);
        r0.from_id = r3.from_id;
        r0.peer_id = r3.peer_id;
        r0.receiver_id = r3.ephemeralReceiverBotId;
        if (r4 == 0) goto L5;
        r0.top_msg_id = r4;
        r0.flags |= 2;
    L5:
        r0.date = r3.date;
        r0.message = r3.message;
        r0.entities = r3.entities;
        r0.media = r3.media;
        r0.reply_markup = r3.reply_markup;
        r0.reply_to = r3.reply_to;
        r0.rich_message = r3.rich_message;
        r0.via_bot_id = r3.via_bot_id;
        r0.anchor_msg_id = r3.ephemeralAnchorMsgId;
        return r0;
    }

    public static EphemeralMessagesHelper getInstance(int r3) {
        EphemeralMessagesHelper r0 = Instance[r3];
        if (r0 == null) goto L5;
        return r0;
    L5:
        monitor-enter(EphemeralMessagesHelper.class);
        EphemeralMessagesHelper r02 = Instance[r3];     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        EphemeralMessagesHelper[] r03 = Instance;     // Catch: Throwable -> L9
        EphemeralMessagesHelper r2 = new EphemeralMessagesHelper(r3);     // Catch: Throwable -> L9
        r03[r3] = r2;     // Catch: Throwable -> L9
        r02 = r2;
    L11:
        monitor-exit(EphemeralMessagesHelper.class);     // Catch: Throwable -> L9
        return r02;
    L9:
        th = move-exception;
        throw th;
    }

    public boolean beforeSendingFinalRequest(TLObject r12, List<MessageObject> r13, Utilities.Callback<TLObject> r14) {
        if (r13 != null) goto L5;
    L46:
        return true;
    L5:
        if (r13.isEmpty() == true) goto L46;
        if ((r12 instanceof TL_ephemeral.TL_sendMessage) == false) goto L11;
        return true;
    L11:
        if ((r12 instanceof TLRPC.TL_messages_sendMessage) == false) goto L29;
        TLRPC.TL_messages_sendMessage r1 = (TLRPC.TL_messages_sendMessage) r12;
        if (r1.ephemeralReceiverBotId == 0) goto L20;
        TL_ephemeral.TL_sendMessage r122 = new TL_ephemeral.TL_sendMessage();
        r122.peer = r1.peer;
        if (r1.ephemeralReceiverBotId != (-1)) goto L17;
        r122.receiver_id = new TLRPC.TL_inputUserEmpty();
        r122.welcome = true;
    L18:
        r122.query_id = 0;
        r122.message = r1.message;
        r122.entities = r1.entities;
        r122.media = null;
        r122.reply_markup = r1.reply_markup;
        r122.rich_message = r1.rich_message;
        r122.random_id = r1.random_id;
        r122.reply_to = applyReplyTo(r1.reply_to);
        r122.rich_message = r1.rich_message;
        r122.invert_media = r1.invert_media;
        r14.run(r122);
        return false;
    L17:
        r122.receiver_id = getMessagesController().getInputUser(r1.ephemeralReceiverBotId);
        goto L18
    L20:
        long r8 = DialogObject.getPeerDialogId(r1.peer);
        if (r8 >= 0) goto L29;
        TLRPC.ChatFull r82 = getMessagesController().getChatFull(-r8);
        if (r82 == null) goto L29;
        long r83 = getEphemeralCommandBotId(r1.message, r82.bot_info);
        if (r83 == 0) goto L29;
        r1.ephemeralReceiverBotId = r83;
        return beforeSendingFinalRequest(r12, r13, r14);
    L29:
        if ((r12 instanceof TLRPC.TL_messages_sendMedia) == false) goto L46;
        TLRPC.TL_messages_sendMedia r15 = (TLRPC.TL_messages_sendMedia) r12;
        if (r15.ephemeralReceiverBotId == 0) goto L38;
        TL_ephemeral.TL_sendMessage r123 = new TL_ephemeral.TL_sendMessage();
        r123.peer = r15.peer;
        if (r15.ephemeralReceiverBotId != (-1)) goto L35;
        r123.receiver_id = new TLRPC.TL_inputUserEmpty();
        r123.welcome = true;
    L36:
        r123.query_id = 0;
        r123.message = r15.message;
        r123.entities = r15.entities;
        r123.media = r15.media;
        r123.reply_markup = r15.reply_markup;
        r123.rich_message = null;
        r123.random_id = r15.random_id;
        r123.reply_to = applyReplyTo(r15.reply_to);
        r123.invert_media = r15.invert_media;
        r14.run(r123);
        return false;
    L35:
        r123.receiver_id = getMessagesController().getInputUser(r15.ephemeralReceiverBotId);
        goto L36
    L38:
        long r2 = DialogObject.getPeerDialogId(r15.peer);
        if (r2 >= 0) goto L46;
        TLRPC.ChatFull r22 = getMessagesController().getChatFull(-r2);
        if (r22 == null) goto L46;
        long r23 = getEphemeralCommandBotId(r15.message, r22.bot_info);
        if (r23 == 0) goto L46;
        r15.ephemeralReceiverBotId = r23;
        return beforeSendingFinalRequest(r12, r13, r14);
    }

    public boolean beforeSendingFinalRequest(TLObject r1, MessageObject r2, Utilities.Callback<TLObject> r3) {
        return beforeSendingFinalRequest(r1, Collections.singletonList(r2), r3);
    }

    public long getEphemeralCommandBotId(String r4, long r5) {
        if (r5 >= 0) goto L8;
        TLRPC.ChatFull r52 = getMessagesController().getChatFull(-r5);
        if (r52 == null) goto L8;
        return getEphemeralCommandBotId(r4, r52.bot_info);
    L8:
        return 0;
    }

    public long getEphemeralCommandBotId(String r16, LongSparseArray<TL_bots.BotInfo> r17) {
        if (r16 == null) goto L55;
        if (r17 == null) goto L55;
        if (r17.isEmpty() == true) goto L55;
        if (r16.startsWith("/") == false) goto L55;
        if (r16.length() < 2) goto L55;
        int r4 = r16.indexOf(32);
        if (r4 == (-1)) goto L15;
        String r0 = r16.substring(1, r4);
    L16:
        int r42 = r0.indexOf(64);
        if (r42 == (-1)) goto L19;
        String r5 = r0.substring(0, r42);
        String r43 = r0.substring(r42 + 1);
        r0 = r5;
    L21:
        if (r0.isEmpty() == false) goto L23;
        return 0;
    L23:
        if (r43 == null) goto L41;
        int r52 = 0;
    L26:
        if (r52 >= r17.size()) goto L40;
        TL_bots.BotInfo r6 = r17.valueAt(r52);
        if (UserObject.hasPublicUsername(getMessagesController().getUser(Long.valueOf(r6.user_id)), r43) == false) goto L39;
        ArrayList<TLRPC.BotCommand> r8 = r6.commands;
        int r9 = r8.size();
        int r10 = 0;
    L31:
        if (r10 >= r9) goto L39;
        TLRPC.BotCommand r11 = r8.get(r10);
        r10 = r10 + 1;
        TLRPC.BotCommand r112 = r11;
        if (r112.command.equalsIgnoreCase(r0) == false) goto L31;
        if (r112.ephemeral == true) goto L37;
        return 0;
    L37:
        return r6.user_id;
    L39:
        r52 = r52 + 1;
        goto L26
    L40:
        return 0;
    L41:
        long r82 = 0;
        int r44 = 0;
        boolean r53 = false;
    L43:
        if (r44 >= r17.size()) goto L53;
        TL_bots.BotInfo r62 = r17.valueAt(r44);
        ArrayList<TLRPC.BotCommand> r102 = r62.commands;
        int r113 = r102.size();
        int r12 = 0;
    L45:
        if (r12 >= r113) goto L52;
        TLRPC.BotCommand r13 = r102.get(r12);
        r12 = r12 + 1;
        TLRPC.BotCommand r132 = r13;
        if (r132.command.equalsIgnoreCase(r0) == false) goto L45;
        if (r82 != 0) goto L50;
        r82 = r62.user_id;
        r53 = r132.ephemeral;
        goto L45
    L50:
        return 0;
    L52:
        r44 = r44 + 1;
        goto L43
    L53:
        if (r53 == false) goto L55;
        return r82;
    L19:
        r43 = null;
        goto L21
    L15:
        r0 = r16.substring(1);
    L55:
        return 0;
    }

    public long getEphemeralCommandBotId(String r5, List<TL_bots.BotInfo> r6) {
        if (r5 == null) goto L17;
        if (r6 != null) goto L5;
        return 0;
    L5:
        if (r6.isEmpty() == false) goto L7;
        return 0;
    L7:
        if (r5.startsWith("/") == true) goto L9;
        return 0;
    L9:
        if (r5.length() < 2) goto L23;
        LongSparseArray<TL_bots.BotInfo> r0 = new LongSparseArray(r6.size());
        Iterator<TL_bots.BotInfo> r62 = r6.iterator();
    L13:
        if (r62.hasNext() == false) goto L16;
        TL_bots.BotInfo r1 = r62.next();
        r0.put(r1.user_id, r1);
        goto L13
    L16:
        return getEphemeralCommandBotId(r5, r0);
    L23:
        return 0;
    L17:
        return 0;
    }

    public boolean isEphemeralCommand(String r3, LongSparseArray<TL_bots.BotInfo> r4) {
        if (getEphemeralCommandBotId(r3, r4) <= 0) goto L6;
        return true;
    L6:
        return false;
    }
}
