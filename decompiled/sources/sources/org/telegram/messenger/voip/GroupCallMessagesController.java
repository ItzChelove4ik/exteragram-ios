package org.telegram.messenger.voip;

import android.util.LongSparseArray;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BaseController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.json.TLJsonBuilder;
import org.telegram.tgnet.json.TLJsonParser;
import org.telegram.tgnet.tl.TL_phone;
import org.telegram.tgnet.tl.TL_update;

/* loaded from: classes.dex */
public class GroupCallMessagesController extends BaseController {
    private static volatile GroupCallMessagesController[] Instance;
    private final LongSparseArray<MessagesList> callMessagesList;
    private final LongSparseArray<List<CallMessageListener>> callMessagesListeners;

    public interface CallMessageListener {
        void onNewGroupCallMessage(long r1, GroupCallMessage r3);

        void onPopGroupCallMessage();
    }

    /* loaded from: classes6.dex */
    public static class MessagesList {
        private final List<GroupCallMessage> messages;
        private final Set<Long> randomIds;

        /* renamed from: -$$Nest$fgetmessages, reason: not valid java name */
        public static /* bridge */ /* synthetic */ List m5277$$Nest$fgetmessages(MessagesList r0) {
            return r0.messages;
        }

        private MessagesList() {
            this.messages = new ArrayList();
            this.randomIds = new HashSet();
        }

        public /* synthetic */ MessagesList(GroupCallMessagesControllerIA r1) {
            this();
        }

        public boolean isEmpty() {
            return this.messages.isEmpty();
        }

        public void pop() {
            if (this.messages.isEmpty() == true) goto L10;
            long r0 = this.messages.remove(r0.size() - 1).randomId;
            if (r0 == 0) goto L9;
            this.randomIds.remove(Long.valueOf(r0));
            return;
        L9:
            return;
        }

        public boolean push(GroupCallMessage r5) {
            long r0 = r5.randomId;
            if (r0 != 0) goto L5;
        L7:
            this.messages.add(0, r5);
            return true;
        L5:
            if (this.randomIds.add(Long.valueOf(r0)) == true) goto L7;
            return false;
        }
    }

    public static /* synthetic */ void $r8$lambda$4p_O6AWcWrC7sPi5rSl47liFAiY(GroupCallMessagesController r0, long r1, GroupCallMessage r3) {
        r0.lambda$processUpdate$0(r1, r3);
    }

    /* renamed from: $r8$lambda$BBVlTPteaRtHqy0HClJaE-GYjgA, reason: not valid java name */
    public static /* synthetic */ void m5274$r8$lambda$BBVlTPteaRtHqy0HClJaEGYjgA(GroupCallMessagesController r0, Runnable r1, GroupCallMessage r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$sendCallMessage$5(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$BoiMzYkL9iUych8eS3-PH9Yfbqk, reason: not valid java name */
    public static /* synthetic */ void m5275$r8$lambda$BoiMzYkL9iUych8eS3PH9Yfbqk(GroupCallMessage r1) {
        r1.setIsSendDelayed(true);
        r1.notifyStateUpdate();
    }

    public static /* synthetic */ void $r8$lambda$DdsMNUebRwOa650w1tdwuYJLQYA(GroupCallMessagesController r0, long r1, long r3, byte[] r5) {
        r0.lambda$processUpdate$3(r1, r3, r5);
    }

    /* renamed from: $r8$lambda$DjiVM9aNpXAsr-WGOKlox4Hy4oY, reason: not valid java name */
    public static /* synthetic */ void m5276$r8$lambda$DjiVM9aNpXAsrWGOKlox4Hy4oY(GroupCallMessagesController r0, long r1, GroupCallMessage r3) {
        r0.lambda$processUpdate$2(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$KDo6njc8CDHoK9vDJDdcyYxmqeI(GroupCallMessagesController r0, long r1, GroupCallMessage r3) {
        r0.lambda$processUpdate$1(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$LaDEPVhIfAp63SyIuEv0NKQJbC4(GroupCallMessagesController r0, long r1) {
        r0.lambda$pushMessageToList$6(r1);
    }

    static {
        Instance = new GroupCallMessagesController[16];
    }

    private GroupCallMessagesController(int r1) {
        super(r1);
        this.callMessagesListeners = new LongSparseArray();
        this.callMessagesList = new LongSparseArray();
    }

    public static GroupCallMessagesController getInstance(int r3) {
        GroupCallMessagesController r0 = Instance[r3];
        if (r0 == null) goto L5;
        return r0;
    L5:
        monitor-enter(GroupCallMessagesController.class);
        GroupCallMessagesController r02 = Instance[r3];     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        GroupCallMessagesController[] r03 = Instance;     // Catch: Throwable -> L9
        GroupCallMessagesController r2 = new GroupCallMessagesController(r3);     // Catch: Throwable -> L9
        r03[r3] = r2;     // Catch: Throwable -> L9
        r02 = r2;
    L11:
        monitor-exit(GroupCallMessagesController.class);     // Catch: Throwable -> L9
        return r02;
    L9:
        th = move-exception;
        throw th;
    }

    private byte[] groupCallMessageDecrypt(long r5, long r7, byte[] r9) {
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 != null) goto L5;
    L20:
        return null;
    L5:
        if (r0.getAccount() != this.currentAccount) goto L20;
        ConferenceCall r4 = r0.conference;
        if (r4 == null) goto L20;
        TLRPC.GroupCall r02 = r4.groupCall;
        if (r02 == null) goto L20;
        if (r02.id == r5) goto L15;
        return null;
    L15:
        long r42 = r4.getCallId();
        if (r42 != (-1)) goto L19;
        return null;
    L19:
        return groupCallMessageDecryptImpl(r42, r7, r9);
    }

    private static native byte[] groupCallMessageDecryptImpl(long r0, long r2, byte[] r4);

    private static native byte[] groupCallMessageEncryptImpl(long r0, byte[] r2);

    private /* synthetic */ void lambda$processUpdate$0(long r1, GroupCallMessage r3) {
        pushMessageToList(r1, r3);
    }

    private /* synthetic */ void lambda$processUpdate$1(long r1, GroupCallMessage r3) {
        pushMessageToList(r1, r3);
    }

    private /* synthetic */ void lambda$processUpdate$2(long r1, GroupCallMessage r3) {
        pushMessageToList(r1, r3);
    }

    private /* synthetic */ void lambda$processUpdate$3(long r10, long r12, byte[] r14) {
        TLRPC.TL_groupCallMessage r1 = null;
        byte[] r142 = groupCallMessageDecrypt(r10, r12, r14);     // Catch: Exception -> L10
        long r4 = r12;
        if (r142 != null) goto L19;
    L13:
        if (r1 == null) goto L15;
        AndroidUtilities.runOnUIThread(new GroupCallMessagesController$$ExternalSyntheticLambda4(this, r10, new GroupCallMessage(this.currentAccount, r4, r1.random_id, r1.message)));
        return;
    L15:
        TLRPC.TL_groupCallMessage r122 = new TLRPC.TL_groupCallMessage();
        TLRPC.TL_textWithEntities r13 = new TLRPC.TL_textWithEntities();
        r122.message = r13;
        r13.text = LocaleController.getString(R.string.GroupCalMessageDecryptionError);
        AndroidUtilities.runOnUIThread(new GroupCallMessagesController$$ExternalSyntheticLambda5(this, r10, new GroupCallMessage(this.currentAccount, r4, 0, r122.message)));
        return;
    L19:
        r1 = TLRPC.TL_groupCallMessage.TLJsonDeserialize(new TLJsonParser(new JSONObject(new String(r142))));     // Catch: Exception -> L8
    L8:
        e = e;
    L12:
        FileLog.e(e);
    L10:
        e = e;
        r4 = r12;
        goto L12
    }

    private /* synthetic */ void lambda$pushMessageToList$6(long r1) {
        popMessageFromList(r1);
    }

    private /* synthetic */ void lambda$sendCallMessage$5(Runnable r2, GroupCallMessage r3, TLObject r4, TLRPC.TL_error r5) {
        AndroidUtilities.cancelRunOnUIThread(r2);
        r3.setIsSendDelayed(false);
        if ((r4 instanceof TLRPC.Bool) == false) goto L9;
        if ((r4 instanceof TLRPC.TL_boolTrue) == false) goto L7;
        r3.setIsSendConfirmed(true);
    L11:
        AndroidUtilities.runOnUIThread(new GroupCallMessagesController$$ExternalSyntheticLambda0(r3));
        return;
    L7:
        r3.setIsSendError(true);
        goto L11
    L9:
        if ((r4 instanceof TLRPC.Updates) == false) goto L11;
        r3.setIsSendConfirmed(true);
        getMessagesController().processUpdates((TLRPC.Updates) r4, false);
        goto L11
    }

    private void popMessageFromList(long r2) {
        MessagesList r0 = this.callMessagesList.get(r2);
        if (r0 == null) goto L23;
        r0.pop();
        if (r0.isEmpty() == false) goto L8;
        this.callMessagesList.remove(r2);
    L8:
        List<CallMessageListener> r22 = this.callMessagesListeners.get(r2);
        if (r22 == null) goto L14;
        Iterator<CallMessageListener> r23 = r22.iterator();
    L12:
        if (r23.hasNext() == false) goto L14;
        r23.next().onPopGroupCallMessage();
    L14:
        List<CallMessageListener> r1 = this.callMessagesListeners.get(0);
        if (r1 == null) goto L20;
        Iterator<CallMessageListener> r12 = r1.iterator();
    L18:
        if (r12.hasNext() == false) goto L24;
        r12.next().onPopGroupCallMessage();
        goto L18
    L24:
        return;
    L20:
        return;
    }

    private void pushMessageToList(long r4, GroupCallMessage r6) {
        MessagesList r0 = this.callMessagesList.get(r4);
        if (r0 != null) goto L6;
        r0 = new MessagesList(null);
        this.callMessagesList.put(r4, r0);
    L6:
        if (r0.push(r6) == true) goto L8;
        return;
    L8:
        List<CallMessageListener> r02 = this.callMessagesListeners.get(r4);
        if (r02 == null) goto L14;
        Iterator<CallMessageListener> r03 = r02.iterator();
    L12:
        if (r03.hasNext() == false) goto L14;
        r03.next().onNewGroupCallMessage(r4, r6);
    L14:
        List<CallMessageListener> r04 = this.callMessagesListeners.get(0);
        if (r04 == null) goto L20;
        Iterator<CallMessageListener> r05 = r04.iterator();
    L18:
        if (r05.hasNext() == false) goto L20;
        r05.next().onNewGroupCallMessage(r4, r6);
    L20:
        AndroidUtilities.runOnUIThread(new GroupCallMessagesController$$ExternalSyntheticLambda1(this, r4), getAppGlobalConfig().groupCallMessageTtl.get(TimeUnit.MILLISECONDS));
    }

    public List<GroupCallMessage> getCallMessages(long r1) {
        MessagesList r0 = this.callMessagesList.get(r1);
        if (r0 == null) goto L7;
        return new ArrayList(MessagesList.m5277$$Nest$fgetmessages(r0));
    L7:
        return new ArrayList();
    }

    public void processUpdate(TL_update.TL_updateGroupCallEncryptedMessage r9) {
        long r3 = r9.call.id;
        long r5 = DialogObject.getPeerDialogId(r9.from_id);
        byte[] r7 = r9.encrypted_message;
        if (getUserConfig().clientUserId != r5) goto L5;
        return;
    L5:
        Utilities.globalQueue.postRunnable(new GroupCallMessagesController$$ExternalSyntheticLambda3(this, r3, r5, r7));
    }

    public void processUpdate(TL_update.TL_updateGroupCallMessage r11) {
        long r0 = r11.call.id;
        long r5 = DialogObject.getPeerDialogId(r11.message.from_id);
        long r7 = r11.message.id;
        if (getUserConfig().clientUserId != r5) goto L5;
        return;
    L5:
        AndroidUtilities.runOnUIThread(new GroupCallMessagesController$$ExternalSyntheticLambda2(this, r0, new GroupCallMessage(this.currentAccount, r5, r7, r11.message.message)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean sendCallMessage(long r14, TLRPC.TL_textWithEntities r16, long r17, TLRPC.InputGroupCall r19) {
        VoIPService r1 = VoIPService.getSharedInstance();
        if (r1 != null) goto L5;
    L32:
        return false;
    L5:
        if (r1.getAccount() != this.currentAccount) goto L32;
        long r4 = getSendMessagesHelper().getNextRandomId();
        if (r1.isConference() == false) goto L29;
        ConferenceCall r12 = r1.conference;
        if (r12 == null) goto L28;
        TLRPC.GroupCall r3 = r12.groupCall;
        if (r3 == null) goto L28;
        if (r3.id == r17) goto L17;
        return false;
    L17:
        long r9 = r12.getCallId();
        if (r9 != (-1)) goto L20;
        return false;
    L20:
        TLRPC.TL_groupCallMessage r13 = new TLRPC.TL_groupCallMessage();
        r13.message = r16;
        r13.random_id = r4;
        JSONObject r15 = TLJsonBuilder.serialize(r13);
        if (r15 != null) goto L23;
        return false;
    L23:
        byte[] r18 = groupCallMessageEncryptImpl(r9, r15.toString().getBytes(StandardCharsets.UTF_8));
        if (r18 != null) goto L26;
        return false;
    L26:
        TL_phone.sendGroupCallEncryptedMessage r2 = new TL_phone.sendGroupCallEncryptedMessage();
        r2.call = r19;
        r2.encrypted_message = r18;
        TL_phone.sendGroupCallMessage r22 = r2;
    L27:
        TL_phone.sendGroupCallMessage r92 = r22;
        GroupCallMessage r0 = new GroupCallMessage(this.currentAccount, r14, r4, r16);
        r0.setIsOut(true);
        pushMessageToList(r17, r0);
        GroupCallMessagesController$$ExternalSyntheticLambda6 r152 = new GroupCallMessagesController$$ExternalSyntheticLambda6(r0);
        AndroidUtilities.runOnUIThread(r152, 1000);
        getConnectionsManager().sendRequest(r92, new GroupCallMessagesController$$ExternalSyntheticLambda7(this, r152, r0));
        return true;
    L28:
        return false;
    L29:
        TL_phone.sendGroupCallMessage r23 = new TL_phone.sendGroupCallMessage();
        r23.call = r19;
        r23.message = r16;
        r23.random_id = r4;
        r22 = r23;
        goto L27
    }

    public void subscribeToCallMessages(long r2, CallMessageListener r4) {
        List<CallMessageListener> r0 = this.callMessagesListeners.get(r2);
        if (r0 != null) goto L5;
        r0 = new ArrayList();
        this.callMessagesListeners.put(r2, r0);
    L5:
        r0.add(r4);
    }

    public void unsubscribeFromCallMessages(long r2, CallMessageListener r4) {
        List<CallMessageListener> r0 = this.callMessagesListeners.get(r2);
        if (r0 == null) goto L10;
        r0.remove(r4);
        if (r0.isEmpty() == false) goto L9;
        this.callMessagesListeners.remove(r2);
        return;
    L9:
        return;
    }
}
