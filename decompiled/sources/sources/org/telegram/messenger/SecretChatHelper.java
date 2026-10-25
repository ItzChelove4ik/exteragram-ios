package org.telegram.messenger;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.io.File;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import okhttp3.internal.url._UrlKt;
import org.telegram.SQLite.SQLiteCursor;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.support.LongSparseIntArray;
import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.TLClassStore;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_update;
import org.telegram.ui.AccountFrozenAlert;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes.dex */
public class SecretChatHelper extends BaseController {
    public static int CURRENT_SECRET_CHAT_LAYER = 151;
    private static volatile SecretChatHelper[] Instance;
    private SparseArray<TLRPC.EncryptedChat> acceptingChats;
    public ArrayList<TLRPC.Update> delayedEncryptedChatUpdates;
    private ArrayList<Long> pendingEncMessagesToDelete;
    private SparseArray<ArrayList<TLRPC.Update>> pendingSecretMessages;
    private SparseArray<SparseIntArray> requestedHoles;
    private SparseArray<ArrayList<TL_decryptedMessageHolder>> secretHolesQueue;
    private ArrayList<Integer> sendingNotifyLayer;
    private boolean startingSecretChat;

    /* loaded from: classes5.dex */
    public static class TL_decryptedMessageHolder extends TLObject {
        public static int constructor = 1431655929;
        public int date;
        public int decryptedWithVersion;
        public TLRPC.EncryptedFile file;
        public TLRPC.TL_decryptedMessageLayer layer;
        public boolean new_key_used;

        static {
        }

        public TL_decryptedMessageHolder() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            r2.readInt64(r3);
            this.date = r2.readInt32(r3);
            this.layer = TLRPC.TL_decryptedMessageLayer.TLdeserialize(r2, r2.readInt32(r3), r3);
            if (r2.readBool(r3) == false) goto L5;
            this.file = TLRPC.EncryptedFile.TLdeserialize(r2, r2.readInt32(r3), r3);
        L5:
            this.new_key_used = r2.readBool(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(0);
            r3.writeInt32(this.date);
            this.layer.serializeToStream(r3);
            if (this.file == null) goto L5;
            boolean r0 = true;
        L6:
            r3.writeBool(r0);
            TLRPC.EncryptedFile r02 = this.file;
            if (r02 == null) goto L9;
            r02.serializeToStream(r3);
        L9:
            r3.writeBool(this.new_key_used);
            return;
        L5:
            r0 = false;
            goto L6
        }
    }

    public static /* synthetic */ void $r8$lambda$23CPAcgUMN082WhDYTU9vOsR87s(SecretChatHelper r0, long r1) {
        r0.lambda$processDecryptedObject$11(r1);
    }

    public static /* synthetic */ void $r8$lambda$2acil8JgIm4UjrJfFZ9IcpcHmnA(SecretChatHelper r0, TLRPC.EncryptedChat r1) {
        r0.lambda$processAcceptedSecretChat$18(r1);
    }

    public static /* synthetic */ void $r8$lambda$5buV7gQWJCpLXiAF3LHXtpIlgOY(SecretChatHelper r0, Context r1, AlertDialog r2) {
        r0.lambda$startSecretChat$27(r1, r2);
    }

    /* renamed from: $r8$lambda$8w-29L7PAYRDVr3EfhEUKH6RRmQ, reason: not valid java name */
    public static /* synthetic */ void m4889$r8$lambda$8w29L7PAYRDVr3EfhEUKH6RRmQ(SecretChatHelper r0, TLRPC.Message r1) {
        r0.lambda$performSendEncryptedRequest$6(r1);
    }

    public static /* synthetic */ int $r8$lambda$9HPyq4DJV3h2LEHyGPsVqWe9r2g(TL_decryptedMessageHolder r0, TL_decryptedMessageHolder r1) {
        int r02 = r0.layer.out_seq_no;
        int r12 = r1.layer.out_seq_no;
        if (r02 <= r12) goto L6;
        return 1;
    L6:
        if (r02 >= r12) goto L9;
        return -1;
    L9:
        return 0;
    }

    public static /* synthetic */ void $r8$lambda$CTcYbcIJFlXPbsa1h4mQQxOFZbE(SecretChatHelper r0, ArrayList r1) {
        r0.lambda$processPendingEncMessages$0(r1);
    }

    /* renamed from: $r8$lambda$DXV1_QLdwfuEE8NLg-lIqlgASLk, reason: not valid java name */
    public static /* synthetic */ void m4890$r8$lambda$DXV1_QLdwfuEE8NLglIqlgASLk(SecretChatHelper r0, ArrayList r1) {
        r0.lambda$resendMessages$14(r1);
    }

    public static /* synthetic */ void $r8$lambda$G_E_4KGtL5CXKvqIZp5N1YSw3dM(SecretChatHelper r0, TLRPC.EncryptedChat r1) {
        r0.lambda$applyPeerLayer$9(r1);
    }

    /* renamed from: $r8$lambda$IMwzZVU9qADAC5JJqz-oGQJj2ec, reason: not valid java name */
    public static /* synthetic */ void m4891$r8$lambda$IMwzZVU9qADAC5JJqzoGQJj2ec(SecretChatHelper r0, TLRPC.Dialog r1, long r2) {
        r0.lambda$processUpdateEncryption$1(r1, r2);
    }

    /* renamed from: $r8$lambda$Ko9U4pDSKNvZ6ljoMw-9GJkuNO4, reason: not valid java name */
    public static /* synthetic */ void m4892$r8$lambda$Ko9U4pDSKNvZ6ljoMw9GJkuNO4(SecretChatHelper r0, long r1) {
        r0.lambda$processDecryptedObject$12(r1);
    }

    public static /* synthetic */ void $r8$lambda$NenD2faGeypKgd4GP14slhfDY8k(SecretChatHelper r0, long r1, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$declineSecretChat$20(r1, r3, r4);
    }

    /* renamed from: $r8$lambda$OP4VZPuwxaW9x5E9ygRV2sN-9M8, reason: not valid java name */
    public static /* synthetic */ void m4893$r8$lambda$OP4VZPuwxaW9x5E9ygRV2sN9M8(SecretChatHelper r0, TLRPC.EncryptedChat r1) {
        r0.lambda$acceptSecretChat$21(r1);
    }

    public static /* synthetic */ void $r8$lambda$Pd1cHZ0XtZC8F_y4X83M1gFHELY(SecretChatHelper r0) {
        r0.lambda$startSecretChat$25();
    }

    public static /* synthetic */ void $r8$lambda$RdiPzfgKCMjR7Y7SWu3L1D58kRI(SecretChatHelper r0, TLRPC.EncryptedChat r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$acceptSecretChat$22(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$T2OTgsLVooghjRUKdl4vQ1x0h50(SecretChatHelper r0, TLRPC.Message r1, int r2) {
        r0.lambda$performSendEncryptedRequest$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$UcyhURz9Wt8Gk_Y3ixWPYm509po(SecretChatHelper r0, Context r1, AlertDialog r2, TLObject r3, byte[] r4, TLRPC.User r5) {
        r0.lambda$startSecretChat$26(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$UlhCiUxK3YjepvemcIK_i14Cx1E(SecretChatHelper r0, TLRPC.TL_encryptedChatDiscarded r1) {
        r0.lambda$processAcceptedSecretChat$19(r1);
    }

    public static /* synthetic */ void $r8$lambda$WrdUnptw0UEgDN2RYEX3cE0Pyu8(SecretChatHelper r0, long r1) {
        r0.lambda$processDecryptedObject$10(r1);
    }

    /* renamed from: $r8$lambda$YqWuikQ_EiNlArv-mwjHadr-TP0, reason: not valid java name */
    public static /* synthetic */ void m4894$r8$lambda$YqWuikQ_EiNlArvmwjHadrTP0(Context r0, AlertDialog r1) {
    L6:
        e = move-exception;
        FileLog.e(e);
        return;
    L3:
        if (((Activity) r0).isFinishing() == true) goto L11;
        r1.dismiss();     // Catch: Exception -> L6
        return;
    }

    public static /* synthetic */ void $r8$lambda$btaiAOXJpbzdznCWySvL2FR9mFI(SecretChatHelper r0, TLRPC.EncryptedChat r1, TLRPC.EncryptedChat r2) {
        r0.lambda$processUpdateEncryption$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$dnrADGYuptFvIsfDYtfgCoNhjd8(SecretChatHelper r0, TLRPC.DecryptedMessage r1, TLRPC.EncryptedChat r2, TLRPC.Message r3, MessageObject r4, String r5, TLObject r6, TLRPC.TL_error r7) {
        r0.lambda$performSendEncryptedRequest$7(r1, r2, r3, r4, r5, r6, r7);
    }

    /* renamed from: $r8$lambda$eAtQREXpykXRCTEyCxR2-SHfyXE, reason: not valid java name */
    public static /* synthetic */ void m4895$r8$lambda$eAtQREXpykXRCTEyCxR2SHfyXE(SecretChatHelper r0, TLRPC.TL_encryptedChatDiscarded r1) {
        r0.lambda$decryptMessage$17(r1);
    }

    public static /* synthetic */ void $r8$lambda$hevp8BUMDScEDB5Own7J88RyfnI(SecretChatHelper r0, Context r1, AlertDialog r2) {
        r0.lambda$startSecretChat$29(r1, r2);
    }

    /* renamed from: $r8$lambda$iMge1-erIwjdXjPHfeorR9kRCuk, reason: not valid java name */
    public static /* synthetic */ void m4896$r8$lambda$iMge1erIwjdXjPHfeorR9kRCuk(SecretChatHelper r0, Context r1, AlertDialog r2, TLRPC.User r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$startSecretChat$30(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$juRsnTFjN9GNa0b8KqhX4CoamN8(SecretChatHelper r0, long r1) {
        r0.lambda$processUpdateEncryption$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$kDQAbv69pIJcU5j5ukdWC5mX8Gc(SecretChatHelper r0, TLRPC.Message r1, TLRPC.messages_SentEncryptedMessage r2, int r3) {
        r0.lambda$performSendEncryptedRequest$5(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$m0jFKNlkHgeq-GOda24AiqtYip4, reason: not valid java name */
    public static /* synthetic */ int m4897$r8$lambda$m0jFKNlkHgeqGOda24AiqtYip4(TLRPC.Message r0, TLRPC.Message r1) {
        return AndroidUtilities.compare(r0.seq_out, r1.seq_out);
    }

    public static /* synthetic */ void $r8$lambda$qXTRGILKCe9PNk8cgvylgGdwBGc(SecretChatHelper r0, int r1, DialogInterface r2) {
        r0.lambda$startSecretChat$31(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ue3LqHm9DuOFAf_yZWfAl9SWphA(SecretChatHelper r0, TLRPC.EncryptedChat r1, TLRPC.DecryptedMessage r2, TLRPC.Message r3, TLRPC.InputEncryptedFile r4, MessageObject r5, String r6) {
        r0.lambda$performSendEncryptedRequest$8(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$xgmaC2sVh5giKNGzem_oGBwMW0U(SecretChatHelper r0, long r1, TLRPC.EncryptedChat r3, long r4) {
        r0.lambda$resendMessages$15(r1, r3, r4);
    }

    /* renamed from: $r8$lambda$zcecztImSWB5kLlzIQ41wG8-X-I, reason: not valid java name */
    public static /* synthetic */ void m4898$r8$lambda$zcecztImSWB5kLlzIQ41wG8XI(SecretChatHelper r0, TLRPC.EncryptedChat r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$acceptSecretChat$23(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$zgI9rEgJ-1YmI4axq1GJS_TFzqk, reason: not valid java name */
    public static /* synthetic */ void m4899$r8$lambda$zgI9rEgJ1YmI4axq1GJS_TFzqk(SecretChatHelper r0, Context r1, AlertDialog r2, byte[] r3, TLRPC.User r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$startSecretChat$28(r1, r2, r3, r4, r5, r6);
    }

    static {
        Instance = new SecretChatHelper[16];
    }

    public SecretChatHelper(int r1) {
        super(r1);
        this.sendingNotifyLayer = new ArrayList();
        this.secretHolesQueue = new SparseArray();
        this.pendingSecretMessages = new SparseArray();
        this.requestedHoles = new SparseArray();
        this.acceptingChats = new SparseArray();
        this.delayedEncryptedChatUpdates = new ArrayList();
        this.pendingEncMessagesToDelete = new ArrayList();
        this.startingSecretChat = false;
    }

    private void applyPeerLayer(TLRPC.EncryptedChat r7, int r8) {
        int r0 = AndroidUtilities.getPeerLayerVersion(r7.layer);
        if (r8 > r0) goto L6;
        return;
    L6:
        if (r7.key_hash.length == 16) goto L16;
    L11:
        r7.layer = AndroidUtilities.setPeerLayerVersion(r7.layer, r8);
        getMessagesStorage().updateEncryptedChatLayer(r7);
        if (r0 >= CURRENT_SECRET_CHAT_LAYER) goto L14;
        sendNotifyLayerMessage(r7, null);
    L14:
        AndroidUtilities.runOnUIThread(new SecretChatHelper$$ExternalSyntheticLambda4(this, r7));
        return;
    L16:
        byte[] r1 = Utilities.computeSHA256(r7.auth_key, 0, r1.length);     // Catch: Throwable -> L9
        byte[] r3 = new byte[36];     // Catch: Throwable -> L9
        System.arraycopy(r7.key_hash, 0, r3, 0, 16);     // Catch: Throwable -> L9
        System.arraycopy(r1, 0, r3, 16, 20);     // Catch: Throwable -> L9
        r7.key_hash = r3;     // Catch: Throwable -> L9
        getMessagesStorage().updateEncryptedChat(r7);     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        FileLog.e(th);
        goto L11
    }

    private TLRPC.Message createDeleteMessage(int r4, int r5, int r6, long r7, TLRPC.EncryptedChat r9) {
        TLRPC.TL_messageService r0 = new TLRPC.TL_messageService();
        TLRPC.TL_messageEncryptedAction r1 = new TLRPC.TL_messageEncryptedAction();
        r0.action = r1;
        r1.encryptedAction = new TLRPC.TL_decryptedMessageActionDeleteMessages();
        r0.action.encryptedAction.random_ids.add(Long.valueOf(r7));
        r0.id = r4;
        r0.local_id = r4;
        TLRPC.TL_peerUser r42 = new TLRPC.TL_peerUser();
        r0.from_id = r42;
        r42.user_id = getUserConfig().getClientUserId();
        r0.unread = true;
        r0.out = true;
        r0.flags = 256;
        r0.dialog_id = DialogObject.makeEncryptedDialogId(r9.id);
        r0.send_state = 1;
        r0.seq_in = r6;
        r0.seq_out = r5;
        r0.peer_id = new TLRPC.TL_peerUser();
        long r43 = r9.participant_id;
        long r12 = getUserConfig().getClientUserId();
        TLRPC.Peer r44 = r0.peer_id;
        if (r43 != r12) goto L5;
        r44.user_id = r9.admin_id;
    L6:
        r0.date = 0;
        r0.random_id = r7;
        return r0;
    L5:
        r44.user_id = r9.participant_id;
        goto L6
    }

    private TLRPC.TL_messageService createServiceSecretMessage(TLRPC.EncryptedChat r13, TLRPC.DecryptedMessageAction r14) {
        TLRPC.TL_messageService r0 = new TLRPC.TL_messageService();
        TLRPC.TL_messageEncryptedAction r1 = new TLRPC.TL_messageEncryptedAction();
        r0.action = r1;
        r1.encryptedAction = r14;
        int r12 = getUserConfig().getNewMessageId();
        r0.id = r12;
        r0.local_id = r12;
        TLRPC.TL_peerUser r15 = new TLRPC.TL_peerUser();
        r0.from_id = r15;
        r15.user_id = getUserConfig().getClientUserId();
        r0.unread = true;
        r0.out = true;
        r0.flags = 256;
        r0.dialog_id = DialogObject.makeEncryptedDialogId(r13.id);
        r0.peer_id = new TLRPC.TL_peerUser();
        r0.send_state = 1;
        long r16 = r13.participant_id;
        long r3 = getUserConfig().getClientUserId();
        TLRPC.Peer r2 = r0.peer_id;
        if (r16 != r3) goto L5;
        r2.user_id = r13.admin_id;
    L7:
        if ((r14 instanceof TLRPC.TL_decryptedMessageActionScreenshotMessages) == false) goto L9;
    L12:
        r0.date = getConnectionsManager().getCurrentTime();
    L13:
        r0.random_id = getSendMessagesHelper().getNextRandomId();
        getUserConfig().saveConfig(false);
        ArrayList<TLRPC.Message> r32 = new ArrayList();
        r32.add(r0);
        getMessagesStorage().putMessages(r32, false, true, true, 0, false, 0, 0);
        return r0;
    L9:
        if ((r14 instanceof TLRPC.TL_decryptedMessageActionSetMessageTTL) == true) goto L12;
        r0.date = 0;
        goto L13
    L5:
        r2.user_id = r13.participant_id;
        goto L7
    }

    private boolean decryptWithMtProtoVersion(NativeByteBuffer r21, byte[] r22, byte[] r23, int r24, boolean r25, boolean r26) {
        boolean r3 = false;
        if (r24 != 1) goto L6;
        boolean r5 = false;
    L7:
        MessageKeyData r12 = MessageKeyData.generateMessageKeyData(r22, r23, r5, r24);
        Utilities.aesIgeEncryption(r21.buffer, r12.aesKey, r12.aesIv, false, false, 24, r21.limit() - 24);
        int r13 = r21.readInt32(false);
        if (r24 == 2) goto L10;
        boolean r20 = true;
        int r52 = r13 + 28;
        if (r52 >= (r21.buffer.limit() - 15)) goto L21;
    L22:
        r52 = r21.buffer.limit();
    L24:
        if (Utilities.arraysEquals(r23, 0, Utilities.computeSHA1(r21.buffer, 24, r52), r5.length - 16) == true) goto L27;
        if (r26 == true) goto L26;
    L17:
        r3 = r20;
        goto L27
    L26:
        Utilities.aesIgeEncryption(r21.buffer, r12.aesKey, r12.aesIv, true, false, 24, r21.limit() - 24);
        r21.position(24);
    L27:
        if (r13 > 0) goto L30;
        r3 = r20;
    L30:
        if (r13 <= (r21.limit() - 28)) goto L32;
        r3 = r20;
    L32:
        int r0 = (r21.limit() - 28) - r13;
        if (r24 == 2) goto L35;
        if (r0 <= 15) goto L43;
    L39:
        r3 = r20;
    L43:
        return !r3;
    L35:
        if (r0 >= 12) goto L38;
        r3 = r20;
    L38:
        if (r0 <= 1024) goto L43;
    L21:
        if (r52 <= r21.buffer.limit()) goto L24;
    L10:
        if (r5 == false) goto L12;
        int r53 = 8;
    L13:
        ByteBuffer r9 = r21.buffer;
        r20 = true;
        if (Utilities.arraysEquals(r23, 0, Utilities.computeSHA256(r22, r53 + 88, 32, r9, 24, r9.limit()), 8) == true) goto L27;
        if (r26 == false) goto L17;
        Utilities.aesIgeEncryption(r21.buffer, r12.aesKey, r12.aesIv, true, false, 24, r21.limit() - 24);
        r21.position(24);
        goto L17
    L12:
        r53 = 0;
        goto L13
    L6:
        r5 = r25;
        goto L7
    }

    public static SecretChatHelper getInstance(int r3) {
        SecretChatHelper r0 = Instance[r3];
        if (r0 == null) goto L5;
        return r0;
    L5:
        monitor-enter(SecretChatHelper.class);
        SecretChatHelper r02 = Instance[r3];     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        SecretChatHelper[] r03 = Instance;     // Catch: Throwable -> L9
        SecretChatHelper r2 = new SecretChatHelper(r3);     // Catch: Throwable -> L9
        r03[r3] = r2;     // Catch: Throwable -> L9
        r02 = r2;
    L11:
        monitor-exit(SecretChatHelper.class);     // Catch: Throwable -> L9
        return r02;
    L9:
        th = move-exception;
        throw th;
    }

    public static boolean isSecretInvisibleMessage(TLRPC.Message r1) {
        TLRPC.MessageAction r12 = r1.action;
        if ((r12 instanceof TLRPC.TL_messageEncryptedAction) == false) goto L10;
        TLRPC.DecryptedMessageAction r13 = r12.encryptedAction;
        if ((r13 instanceof TLRPC.TL_decryptedMessageActionScreenshotMessages) == false) goto L7;
        return false;
    L7:
        if ((r13 instanceof TLRPC.TL_decryptedMessageActionSetMessageTTL) == true) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    public static boolean isSecretVisibleMessage(TLRPC.Message r1) {
        TLRPC.MessageAction r12 = r1.action;
        if ((r12 instanceof TLRPC.TL_messageEncryptedAction) == false) goto L10;
        TLRPC.DecryptedMessageAction r13 = r12.encryptedAction;
        if ((r13 instanceof TLRPC.TL_decryptedMessageActionScreenshotMessages) == false) goto L7;
        return true;
    L7:
        if ((r13 instanceof TLRPC.TL_decryptedMessageActionSetMessageTTL) == false) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    private /* synthetic */ void lambda$acceptSecretChat$21(TLRPC.EncryptedChat r4) {
        getNotificationCenter().postNotificationName(NotificationCenter.encryptedChatUpdated, new Object[]{r4});
        sendNotifyLayerMessage(r4, null);
    }

    private /* synthetic */ void lambda$acceptSecretChat$22(TLRPC.EncryptedChat r3, TLObject r4, TLRPC.TL_error r5) {
        this.acceptingChats.remove(r3.id);
        if (r5 != null) goto L6;
        TLRPC.EncryptedChat r42 = (TLRPC.EncryptedChat) r4;
        r42.auth_key = r3.auth_key;
        r42.user_id = r3.user_id;
        r42.seq_in = r3.seq_in;
        r42.seq_out = r3.seq_out;
        r42.key_create_date = r3.key_create_date;
        r42.key_use_count_in = r3.key_use_count_in;
        r42.key_use_count_out = r3.key_use_count_out;
        getMessagesStorage().updateEncryptedChat(r42);
        getMessagesController().putEncryptedChat(r42, false);
        AndroidUtilities.runOnUIThread(new SecretChatHelper$$ExternalSyntheticLambda19(this, r42));
        return;
    }

    private /* synthetic */ void lambda$acceptSecretChat$23(TLRPC.EncryptedChat r8, TLObject r9, TLRPC.TL_error r10) {
        if (r10 != null) goto L32;
        TLRPC.messages_DhConfig r102 = (TLRPC.messages_DhConfig) r9;
        if ((r9 instanceof TLRPC.TL_messages_dhConfig) == true) goto L6;
    L10:
        byte[] r1 = new byte[256];
        int r2 = 0;
    L11:
        if (r2 >= 256) goto L13;
        r1[r2] = (byte) (((byte) (Utilities.random.nextDouble() * 256.0d)) ^ r102.random[r2]);
        r2 = r2 + 1;
        goto L11
    L13:
        r8.a_or_b = r1;
        r8.seq_in = -1;
        r8.seq_out = 0;
        BigInteger r103 = new BigInteger(1, getMessagesStorage().getSecretPBytes());
        BigInteger r22 = BigInteger.valueOf(getMessagesStorage().getSecretG()).modPow(new BigInteger(1, r1), r103);
        BigInteger r4 = new BigInteger(1, r8.g_a);
        if (Utilities.isGoodGaAndGb(r4, r103) == true) goto L17;
        this.acceptingChats.remove(r8.id);
        declineSecretChat(r8.id, false);
        return;
    L17:
        byte[] r23 = r22.toByteArray();
        if (r23.length <= 256) goto L20;
        byte[] r5 = new byte[256];
        System.arraycopy(r23, 1, r5, 0, 256);
        r23 = r5;
    L20:
        byte[] r104 = r4.modPow(new BigInteger(1, r1), r103).toByteArray();
        if (r104.length <= 256) goto L25;
        byte[] r12 = new byte[256];
        System.arraycopy(r104, r104.length - 256, r12, 0, 256);
    L23:
        r104 = r12;
    L30:
        byte[] r92 = Utilities.computeSHA1(r104);
        byte[] r3 = new byte[8];
        System.arraycopy(r92, r92.length - 8, r3, 0, 8);
        r8.auth_key = r104;
        r8.key_create_date = getConnectionsManager().getCurrentTime();
        TLRPC.TL_messages_acceptEncryption r93 = new TLRPC.TL_messages_acceptEncryption();
        r93.g_b = r23;
        TLRPC.TL_inputEncryptedChat r105 = new TLRPC.TL_inputEncryptedChat();
        r93.peer = r105;
        r105.chat_id = r8.id;
        r105.access_hash = r8.access_hash;
        r93.key_fingerprint = Utilities.bytesToLong(r3);
        getConnectionsManager().sendRequest(r93, new SecretChatHelper$$ExternalSyntheticLambda0(this, r8), 64);
        return;
    L25:
        if (r104.length >= 256) goto L30;
        r12 = new byte[256];
        System.arraycopy(r104, 0, r12, 256 - r104.length, r104.length);
        int r32 = 0;
    L28:
        if (r32 >= (256 - r104.length)) goto L23;
        r12[r32] = 0;
        r32 = r32 + 1;
        goto L28
    L6:
        if (Utilities.isGoodPrime(r102.p, r102.g) == true) goto L9;
        this.acceptingChats.remove(r8.id);
        declineSecretChat(r8.id, false);
        return;
    L9:
        getMessagesStorage().setSecretPBytes(r102.p);
        getMessagesStorage().setSecretG(r102.g);
        getMessagesStorage().setLastSecretVersion(r102.version);
        getMessagesStorage().saveSecretParams(getMessagesStorage().getLastSecretVersion(), getMessagesStorage().getSecretG(), getMessagesStorage().getSecretPBytes());
        goto L10
    L32:
        this.acceptingChats.remove(r8.id);
    }

    private /* synthetic */ void lambda$applyPeerLayer$9(TLRPC.EncryptedChat r2) {
        getNotificationCenter().postNotificationName(NotificationCenter.encryptedChatUpdated, new Object[]{r2});
    }

    private /* synthetic */ void lambda$declineSecretChat$20(long r1, TLObject r3, TLRPC.TL_error r4) {
        if (r1 == 0) goto L6;
        getMessagesStorage().removePendingTask(r1);
        return;
    }

    private /* synthetic */ void lambda$decryptMessage$17(TLRPC.TL_encryptedChatDiscarded r3) {
        getMessagesController().putEncryptedChat(r3, false);
        getMessagesStorage().updateEncryptedChat(r3);
        getNotificationCenter().postNotificationName(NotificationCenter.encryptedChatUpdated, new Object[]{r3});
    }

    private /* synthetic */ void lambda$performSendEncryptedRequest$4(TLRPC.Message r13, int r14) {
        r13.send_state = 0;
        NotificationCenter r8 = getNotificationCenter();
        int r9 = NotificationCenter.messageReceivedByServer;
        Integer r0 = Integer.valueOf(r13.id);
        Integer r1 = Integer.valueOf(r13.id);
        Long r3 = Long.valueOf(r13.dialog_id);
        Integer r5 = Integer.valueOf(r14);
        Boolean r6 = Boolean.FALSE;
        r8.postNotificationName(r9, new Object[]{r0, r1, r13, r3, 0L, r5, r6});
        getNotificationCenter().postNotificationName(NotificationCenter.messageReceivedByServer2, new Object[]{Integer.valueOf(r13.id), Integer.valueOf(r13.id), r13, Long.valueOf(r13.dialog_id), 0L, Integer.valueOf(r14), r6});
        getSendMessagesHelper().processSentMessage(r13.id);
        getSendMessagesHelper().removeFromSendingMessages(r13.id, false);
    }

    private /* synthetic */ void lambda$performSendEncryptedRequest$5(TLRPC.Message r13, TLRPC.messages_SentEncryptedMessage r14, int r15) {
        if (isSecretInvisibleMessage(r13) == false) goto L5;
        r14.date = 0;
    L5:
        getMessagesStorage().updateMessageStateAndId(r13.random_id, 0, Integer.valueOf(r13.id), r13.id, r14.date, false, 0, 0);
        AndroidUtilities.runOnUIThread(new SecretChatHelper$$ExternalSyntheticLambda13(this, r13, r15));
    }

    private /* synthetic */ void lambda$performSendEncryptedRequest$6(TLRPC.Message r4) {
        r4.send_state = 2;
        getNotificationCenter().postNotificationName(NotificationCenter.messageSendError, new Object[]{Integer.valueOf(r4.id)});
        getSendMessagesHelper().processSentMessage(r4.id);
        getSendMessagesHelper().removeFromSendingMessages(r4.id, false);
    }

    private /* synthetic */ void lambda$performSendEncryptedRequest$7(TLRPC.DecryptedMessage r7, TLRPC.EncryptedChat r8, TLRPC.Message r9, MessageObject r10, String r11, TLObject r12, TLRPC.TL_error r13) {
        int r0 = 0;
        if (r13 == null) goto L5;
    L19:
        if (r13 != null) goto L28;
        String r82 = r9.attachPath;
        TLRPC.messages_SentEncryptedMessage r122 = (TLRPC.messages_SentEncryptedMessage) r12;
        if (isSecretVisibleMessage(r9) == false) goto L23;
        r9.date = r122.date;
    L23:
        if (r10 == null) goto L27;
        TLRPC.EncryptedFile r83 = r122.file;
        if ((r83 instanceof TLRPC.TL_encryptedFile) == false) goto L27;
        updateMediaPaths(r10, r83, r7, r11);
        r0 = r10.getMediaExistanceFlags();
    L27:
        getMessagesStorage().getStorageQueue().postRunnable(new SecretChatHelper$$ExternalSyntheticLambda6(this, r9, r122, r0));
        return;
    L28:
        getMessagesStorage().markMessageAsSendError(r9, 0);
        AndroidUtilities.runOnUIThread(new SecretChatHelper$$ExternalSyntheticLambda7(this, r9));
        return;
    L5:
        if ((r7.action instanceof TLRPC.TL_decryptedMessageActionNotifyLayer) == false) goto L19;
        TLRPC.EncryptedChat r1 = getMessagesController().getEncryptedChat(Integer.valueOf(r8.id));
        if (r1 != null) goto L10;
        r1 = r8;
    L10:
        if (r1.key_hash != null) goto L13;
        r1.key_hash = AndroidUtilities.calcAuthKeyHash(r1.auth_key);
    L13:
        if (r1.key_hash.length == 16) goto L30;
    L18:
        this.sendingNotifyLayer.remove(Integer.valueOf(r1.id));
        r1.layer = AndroidUtilities.setMyLayerVersion(r1.layer, CURRENT_SECRET_CHAT_LAYER);
        getMessagesStorage().updateEncryptedChatLayer(r1);
        goto L19
    L30:
        byte[] r2 = Utilities.computeSHA256(r8.auth_key, 0, r2.length);     // Catch: Throwable -> L16
        byte[] r4 = new byte[36];     // Catch: Throwable -> L16
        System.arraycopy(r8.key_hash, 0, r4, 0, 16);     // Catch: Throwable -> L16
        System.arraycopy(r2, 0, r4, 16, 20);     // Catch: Throwable -> L16
        r1.key_hash = r4;     // Catch: Throwable -> L16
        getMessagesStorage().updateEncryptedChat(r1);     // Catch: Throwable -> L16
    L16:
        th = move-exception;
        FileLog.e(th);
        goto L18
    }

    /* JADX WARN: Multi-variable type inference failed */
    private /* synthetic */ void lambda$performSendEncryptedRequest$8(TLRPC.EncryptedChat r20, TLRPC.DecryptedMessage r21, TLRPC.Message r22, TLRPC.InputEncryptedFile r23, MessageObject r24, String r25) {
        TLRPC.TL_decryptedMessageLayer r1 = new TLRPC.TL_decryptedMessageLayer();     // Catch: Exception -> L61
        r1.layer = Math.min(Math.max(46, AndroidUtilities.getMyLayerVersion(r20.layer)), Math.max(46, AndroidUtilities.getPeerLayerVersion(r20.layer)));     // Catch: Exception -> L61
        r1.message = r21;     // Catch: Exception -> L61
        byte[] r5 = new byte[15];     // Catch: Exception -> L61
        r1.random_bytes = r5;     // Catch: Exception -> L61
        Utilities.random.nextBytes(r5);     // Catch: Exception -> L61
        boolean r6 = true;
        if (r20.seq_in == 0) goto L6;
    L11:
        int r52 = r22.seq_in;     // Catch: Exception -> L61
        if (r52 == 0) goto L14;
    L32:
        r1.in_seq_no = r52;     // Catch: Exception -> L61
        r1.out_seq_no = r22.seq_out;     // Catch: Exception -> L61
    L34:
        if (BuildVars.LOGS_ENABLED == false) goto L36;
        FileLog.d(r21 + " send message with in_seq = " + r1.in_seq_no + " out_seq = " + r1.out_seq_no);     // Catch: Exception -> L61
    L36:
        int r53 = r1.getObjectSize();     // Catch: Exception -> L61
        NativeByteBuffer r9 = new NativeByteBuffer(r53 + 4);     // Catch: Exception -> L61
        r9.writeInt32(r53);     // Catch: Exception -> L61
        r1.serializeToStream(r9);     // Catch: Exception -> L61
        int r12 = r9.length();     // Catch: Exception -> L61
        if ((r12 % 16) == 0) goto L39;
        int r54 = 16 - (r12 % 16);     // Catch: Exception -> L61
    L40:
        int r55 = r54 + ((Utilities.random.nextInt(3) + 2) * 16);     // Catch: Exception -> L61
        NativeByteBuffer r11 = new NativeByteBuffer(r12 + r55);     // Catch: Exception -> L61
        r9.position(0);     // Catch: Exception -> L61
        r11.writeBytes(r9);     // Catch: Exception -> L61
        if (r55 == 0) goto L43;
        byte[] r13 = new byte[r55];     // Catch: Exception -> L61
        Utilities.random.nextBytes(r13);     // Catch: Exception -> L61
        r11.writeBytes(r13);     // Catch: Exception -> L61
    L43:
        byte[] r14 = new byte[16];     // Catch: Exception -> L61
        if (r20.admin_id != getUserConfig().getClientUserId()) goto L47;
        r6 = false;
    L47:
        byte[] r122 = r20.auth_key;     // Catch: Exception -> L61
        if (r6 == false) goto L50;
        int r132 = 8;
    L51:
        ByteBuffer r15 = r11.buffer;     // Catch: Exception -> L61
        System.arraycopy(Utilities.computeSHA256(r122, r132 + 88, 32, r15, 0, r15.limit()), 8, r14, 0, 16);     // Catch: Exception -> L61
        r9.reuse();     // Catch: Exception -> L61
        MessageKeyData r56 = MessageKeyData.generateMessageKeyData(r20.auth_key, r14, r6, 2);     // Catch: Exception -> L61
        Utilities.aesIgeEncryption(r11.buffer, r56.aesKey, r56.aesIv, true, false, 0, r11.limit());     // Catch: Exception -> L61
        NativeByteBuffer r57 = new NativeByteBuffer(24 + r11.length());     // Catch: Exception -> L61
        r11.position(0);     // Catch: Exception -> L61
        r57.writeInt64(r20.key_fingerprint);     // Catch: Exception -> L61
        r57.writeBytes(r14);     // Catch: Exception -> L61
        r57.writeBytes(r11);     // Catch: Exception -> L61
        r11.reuse();     // Catch: Exception -> L61
        r57.position(0);     // Catch: Exception -> L61
        if (r23 == null) goto L54;
        TLRPC.TL_messages_sendEncryptedFile r16 = new TLRPC.TL_messages_sendEncryptedFile();     // Catch: Exception -> L61
        r16.silent = r22.silent;     // Catch: Exception -> L61
        r16.data = r57;     // Catch: Exception -> L61
        r16.random_id = r21.random_id;     // Catch: Exception -> L61
        TLRPC.TL_inputEncryptedChat r58 = new TLRPC.TL_inputEncryptedChat();     // Catch: Exception -> L61
        r16.peer = r58;     // Catch: Exception -> L61
        r58.chat_id = r20.id;     // Catch: Exception -> L61
        r58.access_hash = r20.access_hash;     // Catch: Exception -> L61
        r16.file = r23;     // Catch: Exception -> L61
        TLRPC.TL_messages_sendEncryptedFile r7 = r16;
    L59:
        getConnectionsManager().sendRequest(r7, new SecretChatHelper$$ExternalSyntheticLambda11(this, r21, r20, r22, r24, r25), 64);     // Catch: Exception -> L61
        return;
    L54:
        if ((r21 instanceof TLRPC.TL_decryptedMessageService) == false) goto L57;
        TLRPC.TL_messages_sendEncryptedService r0 = new TLRPC.TL_messages_sendEncryptedService();     // Catch: Exception -> L61
        r0.data = r57;     // Catch: Exception -> L61
        r0.random_id = r21.random_id;     // Catch: Exception -> L61
        TLRPC.TL_inputEncryptedChat r17 = new TLRPC.TL_inputEncryptedChat();     // Catch: Exception -> L61
        r0.peer = r17;     // Catch: Exception -> L61
        r17.chat_id = r20.id;     // Catch: Exception -> L61
        r17.access_hash = r20.access_hash;     // Catch: Exception -> L61
        TLRPC.TL_messages_sendEncryptedFile r02 = r0;
    L56:
        r7 = r02;
        goto L59
    L57:
        TLRPC.TL_messages_sendEncrypted r03 = new TLRPC.TL_messages_sendEncrypted();     // Catch: Exception -> L61
        r03.silent = r22.silent;     // Catch: Exception -> L61
        r03.data = r57;     // Catch: Exception -> L61
        r03.random_id = r21.random_id;     // Catch: Exception -> L61
        TLRPC.TL_inputEncryptedChat r18 = new TLRPC.TL_inputEncryptedChat();     // Catch: Exception -> L61
        r03.peer = r18;     // Catch: Exception -> L61
        r18.chat_id = r20.id;     // Catch: Exception -> L61
        r18.access_hash = r20.access_hash;     // Catch: Exception -> L61
        r02 = r03;
        goto L56
    L50:
        r132 = 0;
        goto L51
    L39:
        r54 = 0;
        goto L40
    L14:
        if (r22.seq_out != 0) goto L32;
        int r59 = r20.seq_in;     // Catch: Exception -> L61
        if (r59 > 0) goto L19;
        r59 = r59 + 2;     // Catch: Exception -> L61
    L19:
        r1.in_seq_no = r59;     // Catch: Exception -> L61
        int r510 = r20.seq_out;     // Catch: Exception -> L61
        r1.out_seq_no = r510;     // Catch: Exception -> L61
        r20.seq_out = r510 + 2;     // Catch: Exception -> L61
        if (r20.key_create_date != 0) goto L22;
        r20.key_create_date = getConnectionsManager().getCurrentTime();     // Catch: Exception -> L61
    L22:
        short r511 = (short) (r20.key_use_count_out + 1);     // Catch: Exception -> L61
        r20.key_use_count_out = r511;     // Catch: Exception -> L61
        if (r511 >= 100) goto L27;
        if (r20.key_create_date < (getConnectionsManager().getCurrentTime() - 604800)) goto L27;
    L31:
        getMessagesStorage().updateEncryptedChatSeq(r20, false);     // Catch: Exception -> L61
        r22.seq_in = r1.in_seq_no;     // Catch: Exception -> L61
        r22.seq_out = r1.out_seq_no;     // Catch: Exception -> L61
        getMessagesStorage().setMessageSeq(r22.id, r22.seq_in, r22.seq_out);     // Catch: Exception -> L61
    L27:
        if (r20.exchange_id != 0) goto L31;
        if (r20.future_key_fingerprint != 0) goto L31;
        requestNewSecretChatKey(r20);     // Catch: Exception -> L61
        goto L31
    L6:
        if (r20.seq_out != 0) goto L11;
        if (r20.admin_id != getUserConfig().getClientUserId()) goto L10;
        r20.seq_out = 1;     // Catch: Exception -> L61
        r20.seq_in = -2;     // Catch: Exception -> L61
        goto L11
    L10:
        r20.seq_in = -1;     // Catch: Exception -> L61
    L61:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$processAcceptedSecretChat$18(TLRPC.EncryptedChat r4) {
        getNotificationCenter().postNotificationName(NotificationCenter.encryptedChatUpdated, new Object[]{r4});
        sendNotifyLayerMessage(r4, null);
    }

    private /* synthetic */ void lambda$processAcceptedSecretChat$19(TLRPC.TL_encryptedChatDiscarded r3) {
        getMessagesController().putEncryptedChat(r3, false);
        getNotificationCenter().postNotificationName(NotificationCenter.encryptedChatUpdated, new Object[]{r3});
    }

    private /* synthetic */ void lambda$processDecryptedObject$10(long r8) {
        getNotificationsController().processReadMessages(null, r8, 0, Integer.MAX_VALUE, false);
        LongSparseIntArray r82 = new LongSparseIntArray(1);
        r82.put(r8, 0);
        getNotificationsController().processDialogsUpdateRead(r82);
    }

    private /* synthetic */ void lambda$processDecryptedObject$11(long r2) {
        AndroidUtilities.runOnUIThread(new SecretChatHelper$$ExternalSyntheticLambda25(this, r2));
    }

    private /* synthetic */ void lambda$processDecryptedObject$12(long r6) {
        TLRPC.Dialog r0 = getMessagesController().dialogs_dict.get(r6);
        if (r0 == null) goto L5;
        r0.unread_count = 0;
        getMessagesController().dialogMessage.remove(r0.id);
    L5:
        getMessagesStorage().getStorageQueue().postRunnable(new SecretChatHelper$$ExternalSyntheticLambda30(this, r6));
        getMessagesStorage().deleteDialog(r6, 1);
        getNotificationCenter().postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
        getNotificationCenter().postNotificationName(NotificationCenter.removeAllMessagesFromDialog, new Object[]{Long.valueOf(r6), Boolean.FALSE, null});
    }

    private /* synthetic */ void lambda$processPendingEncMessages$0(ArrayList r5) {
        int r0 = 0;
    L4:
        if (r0 >= r5.size()) goto L9;
        MessageObject r1 = getMessagesController().dialogMessagesByRandomIds.get(((Long) r5.get(r0)).longValue());
        if (r1 == null) goto L8;
        r1.deleted = true;
    L8:
        r0 = r0 + 1;
        goto L4
    }

    private /* synthetic */ void lambda$processUpdateEncryption$1(TLRPC.Dialog r5, long r6) {
        if (r5.folder_id != 1) goto L5;
        SharedPreferences.Editor r0 = MessagesController.getNotificationsSettings(this.currentAccount).edit();
        r0.putBoolean("dialog_bar_archived" + r6, true);
        r0.apply();
    L5:
        getMessagesController().dialogs_dict.put(r5.id, r5);
        getMessagesController().allDialogs.add(r5);
        getMessagesController().sortDialogs(null);
        getNotificationCenter().postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
    }

    private /* synthetic */ void lambda$processUpdateEncryption$2(TLRPC.EncryptedChat r2, TLRPC.EncryptedChat r3) {
        if (r2 == null) goto L4;
        getMessagesController().putEncryptedChat(r3, false);
    L4:
        getMessagesStorage().updateEncryptedChat(r3);
        getNotificationCenter().postNotificationName(NotificationCenter.encryptedChatUpdated, new Object[]{r3});
    }

    private /* synthetic */ void lambda$processUpdateEncryption$3(long r2) {
        getMessagesController().deleteDialog(r2, 0);
    }

    private /* synthetic */ void lambda$resendMessages$14(ArrayList r9) {
        int r1 = 0;
    L4:
        if (r1 >= r9.size()) goto L6;
        MessageObject r3 = new MessageObject(this.currentAccount, (TLRPC.Message) r9.get(r1), false, true);
        r3.resendAsIs = true;
        getSendMessagesHelper().retrySendMessage(r3, true, 0);
        r1 = r1 + 1;
        goto L4
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v23, types: [org.telegram.tgnet.InputSerializedData, org.telegram.tgnet.NativeByteBuffer] */
    /* JADX WARN: Type inference failed for: r14v1, types: [org.telegram.SQLite.SQLiteCursor] */
    /* JADX WARN: Type inference failed for: r18v0, types: [org.telegram.messenger.SendMessagesHelper] */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v12, types: [org.telegram.tgnet.TLRPC$Message] */
    /* JADX WARN: Type inference failed for: r1v9, types: [org.telegram.tgnet.TLRPC$Message] */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r7v7 */
    private /* synthetic */ void lambda$resendMessages$15(long r25, TLRPC.EncryptedChat r27, long r28) {
        TLRPC.EncryptedChat r6 = r27;
    L40:
        e = move-exception;
        FileLog.e(e);
        return;
    L4:
        if (r6.admin_id == getUserConfig().getClientUserId()) goto L6;
    L8:
        long r2 = r25;
    L10:
        if (r2 > r28) goto L14;
        int r9 = (int) r2;     // Catch: Exception -> L40
        int r10 = (int) r28;     // Catch: Exception -> L40
        ?? r7 = 0;
        SQLiteCursor r4 = getMessagesStorage().getDatabase().queryFinalized(String.format(Locale.US, "SELECT uid FROM requested_holes WHERE uid = %d AND ((seq_out_start >= %d AND %d <= seq_out_end) OR (seq_out_start >= %d AND %d <= seq_out_end))", new Object[]{Integer.valueOf(r6.id), Integer.valueOf(r9), Integer.valueOf(r9), Integer.valueOf(r10), Integer.valueOf(r10)}), new Object[0]);     // Catch: Exception -> L40
        boolean r5 = r4.next();     // Catch: Exception -> L40
        r4.dispose();     // Catch: Exception -> L40
        if (r5 == true) goto L50;
        long r11 = DialogObject.makeEncryptedDialogId(r6.id);     // Catch: Exception -> L40
        SparseArray r8 = new SparseArray();     // Catch: Exception -> L40
        ArrayList r13 = new ArrayList();     // Catch: Exception -> L40
    L17:
        if (r2 > r28) goto L19;
        r8.put((int) r2, null);     // Catch: Exception -> L40
        r2 = r2 + 2;     // Catch: Exception -> L40
        goto L17
    L19:
        ?? r14 = getMessagesStorage().getDatabase().queryFinalized(String.format(Locale.US, "SELECT m.data, r.random_id, s.seq_in, s.seq_out, m.ttl, s.mid FROM messages_seq as s LEFT JOIN randoms_v2 as r ON r.mid = s.mid LEFT JOIN messages_v2 as m ON m.mid = s.mid WHERE m.uid = %d AND m.out = 1 AND s.seq_out >= %d AND s.seq_out <= %d ORDER BY seq_out ASC", new Object[]{Long.valueOf(r11), Integer.valueOf(r9), Integer.valueOf(r10)}), new Object[0]);     // Catch: Exception -> L40
    L21:
        if (r14.next() == false) goto L30;
        long r0 = r14.longValue(1);     // Catch: Exception -> L40
        if (r0 != 0) goto L25;
        r0 = Utilities.random.nextLong();     // Catch: Exception -> L40
    L25:
        long r42 = r0;
        int r3 = r14.intValue(2);     // Catch: Exception -> L40
        int r22 = r14.intValue(3);     // Catch: Exception -> L40
        int r1 = r14.intValue(5);     // Catch: Exception -> L40
        ?? r02 = r14.byteBufferValue(r7);     // Catch: Exception -> L40
        if (r02 == 0) goto L28;
        ?? r12 = TLRPC.Message.TLdeserialize(r02, r02.readInt32(r7), r7);     // Catch: Exception -> L40
        SparseArray r26 = r8;
        r12.readAttachPath(r02, getUserConfig().clientUserId);     // Catch: Exception -> L40
        r02.reuse();     // Catch: Exception -> L40
        r12.random_id = r42;     // Catch: Exception -> L40
        r12.dialog_id = r11;     // Catch: Exception -> L40
        r12.seq_in = r3;     // Catch: Exception -> L40
        r12.seq_out = r22;     // Catch: Exception -> L40
        r12.ttl = r14.intValue(4);     // Catch: Exception -> L40
    L29:
        r13.add(r12);     // Catch: Exception -> L40
        SparseArray r72 = r26;
        r72.remove(r22);     // Catch: Exception -> L40
        r6 = r27;
        r8 = r72;
        r7 = 0;
        goto L21
    L28:
        r26 = r8;
        r12 = createDeleteMessage(r1, r22, r3, r42, r6);     // Catch: Exception -> L40
        goto L29
    L30:
        SparseArray r73 = r8;
        r14.dispose();     // Catch: Exception -> L40
        if (r73.size() == 0) goto L37;
        int r82 = 0;
    L34:
        if (r82 >= r73.size()) goto L36;
        int r23 = r73.keyAt(r82);     // Catch: Exception -> L40
        r13.add(createDeleteMessage(getUserConfig().getNewMessageId(), r23, r23 + 1, Utilities.random.nextLong(), r27));     // Catch: Exception -> L40
        r82 = r82 + 1;     // Catch: Exception -> L40
        goto L34
    L36:
        TLRPC.EncryptedChat r62 = r27;
        getUserConfig().saveConfig(false);     // Catch: Exception -> L40
    L38:
        Collections.sort(r13, new SecretChatHelper$$ExternalSyntheticLambda21());     // Catch: Exception -> L40
        ArrayList r03 = new ArrayList();     // Catch: Exception -> L40
        r03.add(r62);     // Catch: Exception -> L40
        AndroidUtilities.runOnUIThread(new SecretChatHelper$$ExternalSyntheticLambda22(this, r13));     // Catch: Exception -> L40
        getSendMessagesHelper().processUnsentMessages(r13, null, new ArrayList(), new ArrayList(), r03);     // Catch: Exception -> L40
        getMessagesStorage().getDatabase().executeFast(String.format(Locale.US, "REPLACE INTO requested_holes VALUES(%d, %d, %d)", new Object[]{Integer.valueOf(r62.id), Integer.valueOf(r9), Integer.valueOf(r10)})).stepThis().dispose();     // Catch: Exception -> L40
        return;
    L37:
        r62 = r27;
        goto L38
    L50:
        return;
    L14:
        return;
    L6:
        if ((r25 & 1) != 0) goto L8;
        r2 = r25 + 1;     // Catch: Exception -> L40
        goto L10
    }

    private /* synthetic */ void lambda$startSecretChat$25() {
        if (this.delayedEncryptedChatUpdates.isEmpty() == true) goto L6;
        getMessagesController().processUpdateArray(this.delayedEncryptedChatUpdates, null, null, false, 0);
        this.delayedEncryptedChatUpdates.clear();
        return;
    }

    private /* synthetic */ void lambda$startSecretChat$26(Context r4, AlertDialog r5, TLObject r6, byte[] r7, TLRPC.User r8) {
        this.startingSecretChat = false;
        if (((Activity) r4).isFinishing() == false) goto L10;
    L8:
        TLRPC.EncryptedChat r62 = (TLRPC.EncryptedChat) r6;
        r62.user_id = r62.participant_id;
        r62.seq_in = -2;
        r62.seq_out = 1;
        r62.a_or_b = r7;
        getMessagesController().putEncryptedChat(r62, false);
        TLRPC.TL_dialog r42 = new TLRPC.TL_dialog();
        r42.id = DialogObject.makeEncryptedDialogId(r62.id);
        r42.unread_count = 0;
        r42.top_message = 0;
        r42.last_message_date = getConnectionsManager().getCurrentTime();
        getMessagesController().dialogs_dict.put(r42.id, r42);
        getMessagesController().allDialogs.add(r42);
        getMessagesController().sortDialogs(null);
        getMessagesStorage().putEncryptedChat(r62, r8, r42);
        getNotificationCenter().postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
        getNotificationCenter().postNotificationName(NotificationCenter.encryptedChatCreated, new Object[]{r62});
        Utilities.stageQueue.postRunnable(new SecretChatHelper$$ExternalSyntheticLambda20(this));
        return;
    L10:
        r5.dismiss();     // Catch: Exception -> L6
    L6:
        e = move-exception;
        FileLog.e(e);
        goto L8
    }

    private /* synthetic */ void lambda$startSecretChat$27(Context r2, AlertDialog r3) {
        if (((Activity) r2).isFinishing() == true) goto L13;
        this.startingSecretChat = false;
        r3.dismiss();     // Catch: Exception -> L7
    L9:
        AlertDialog.Builder r1 = new AlertDialog.Builder(r2);
        r1.setTitle(LocaleController.getString(R.string.AppName));
        r1.setMessage(LocaleController.getString(R.string.CreateEncryptedChatError));
        r1.setPositiveButton(LocaleController.getString(R.string.OK), null);
        r1.show().setCanceledOnTouchOutside(true);
        return;
    L7:
        e = move-exception;
        FileLog.e(e);
        goto L9
    }

    private /* synthetic */ void lambda$startSecretChat$28(Context r1, AlertDialog r2, byte[] r3, TLRPC.User r4, TLObject r5, TLRPC.TL_error r6) {
        if (r6 != null) goto L5;
        AndroidUtilities.runOnUIThread(new SecretChatHelper$$ExternalSyntheticLambda14(this, r1, r2, r5, r3, r4));
        return;
    L5:
        this.delayedEncryptedChatUpdates.clear();
        AndroidUtilities.runOnUIThread(new SecretChatHelper$$ExternalSyntheticLambda15(this, r1, r2));
    }

    private /* synthetic */ void lambda$startSecretChat$29(Context r2, AlertDialog r3) {
        this.startingSecretChat = false;
        if (((Activity) r2).isFinishing() == true) goto L11;
        r3.dismiss();     // Catch: Exception -> L6
        return;
    L6:
        e = move-exception;
        FileLog.e(e);
        return;
    }

    private /* synthetic */ void lambda$startSecretChat$30(Context r8, AlertDialog r9, TLRPC.User r10, TLObject r11, TLRPC.TL_error r12) {
        if (r12 != null) goto L18;
        TLRPC.messages_DhConfig r122 = (TLRPC.messages_DhConfig) r11;
        if ((r11 instanceof TLRPC.TL_messages_dhConfig) == true) goto L6;
    L10:
        byte[] r4 = new byte[256];
        int r1 = 0;
    L11:
        if (r1 >= 256) goto L13;
        r4[r1] = (byte) (((byte) (Utilities.random.nextDouble() * 256.0d)) ^ r122.random[r1]);
        r1 = r1 + 1;
        goto L11
    L13:
        byte[] r123 = BigInteger.valueOf(getMessagesStorage().getSecretG()).modPow(new BigInteger(1, r4), new BigInteger(1, getMessagesStorage().getSecretPBytes())).toByteArray();
        if (r123.length <= 256) goto L16;
        byte[] r13 = new byte[256];
        System.arraycopy(r123, 1, r13, 0, 256);
        r123 = r13;
    L16:
        TLRPC.TL_messages_requestEncryption r112 = new TLRPC.TL_messages_requestEncryption();
        r112.g_a = r123;
        r112.user_id = getMessagesController().getInputUser(r10);
        r112.random_id = Utilities.random.nextInt();
        getConnectionsManager().sendRequest(r112, new SecretChatHelper$$ExternalSyntheticLambda27(this, r8, r9, r4, r10), 2);
        return;
    L6:
        if (Utilities.isGoodPrime(r122.p, r122.g) == true) goto L9;
        AndroidUtilities.runOnUIThread(new SecretChatHelper$$ExternalSyntheticLambda26(r8, r9));
        return;
    L9:
        getMessagesStorage().setSecretPBytes(r122.p);
        getMessagesStorage().setSecretG(r122.g);
        getMessagesStorage().setLastSecretVersion(r122.version);
        getMessagesStorage().saveSecretParams(getMessagesStorage().getLastSecretVersion(), getMessagesStorage().getSecretG(), getMessagesStorage().getSecretPBytes());
        goto L10
    L18:
        this.delayedEncryptedChatUpdates.clear();
        AndroidUtilities.runOnUIThread(new SecretChatHelper$$ExternalSyntheticLambda28(this, r8, r9));
    }

    private /* synthetic */ void lambda$startSecretChat$31(int r1, DialogInterface r2) {
        getConnectionsManager().cancelRequest(r1, true);
    }

    private void resendMessages(int r10, int r11, TLRPC.EncryptedChat r12) {
        if (r12 == null) goto L21;
        long r2 = r10;
        long r5 = r11;
        long r102 = r5 - r2;
        if (r2 >= 0) goto L7;
        return;
    L7:
        if (r5 >= 0) goto L9;
        return;
    L9:
        if (r5 < 2147483647L) goto L11;
        return;
    L11:
        if (r102 >= 0) goto L13;
        return;
    L13:
        if (r102 > NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS) goto L22;
        getMessagesStorage().getStorageQueue().postRunnable(new SecretChatHelper$$ExternalSyntheticLambda8(this, r2, r12, r5));
        return;
    L22:
        return;
    }

    private void updateMediaPaths(MessageObject r17, TLRPC.EncryptedFile r18, TLRPC.DecryptedMessage r19, String r20) {
        TLRPC.Message r3 = r17.messageOwner;
        if (r18 == null) goto L26;
        TLRPC.MessageMedia r4 = r3.media;
        if ((r4 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L11;
        TLRPC.Photo r5 = r4.photo;
        if (r5 == null) goto L11;
        ArrayList<TLRPC.PhotoSize> r0 = r5.sizes;
        TLRPC.PhotoSize r02 = r0.get(r0.size() - 1);
        String r42 = r02.location.volume_id + "_" + r02.location.local_id;
        TLRPC.TL_fileEncryptedLocation r8 = new TLRPC.TL_fileEncryptedLocation();
        r02.location = r8;
        TLRPC.DecryptedMessageMedia r2 = r19.media;
        r8.key = r2.key;
        r8.iv = r2.iv;
        r8.dc_id = r18.dc_id;
        r8.volume_id = r18.id;
        r8.secret = r18.access_hash;
        r8.local_id = r18.key_fingerprint;
        String r1 = r02.location.volume_id + "_" + r02.location.local_id;
        new File(FileLoader.getDirectory(4), r42.concat(".jpg")).renameTo(getFileLoader().getPathToAttach(r02));
        ImageLoader.getInstance().replaceImageInCache(r42, r1, ImageLocation.getForPhoto(r02, r3.media.photo), true);
        ArrayList<TLRPC.Message> r7 = new ArrayList();
        r7.add(r3);
        getMessagesStorage().putMessages(r7, false, true, false, 0, false, 0, 0);
        return;
    L11:
        if ((r4 instanceof TLRPC.TL_messageMediaDocument) == false) goto L27;
        TLRPC.Document r52 = r4.document;
        if (r52 == null) goto L28;
        r4.document = new TLRPC.TL_documentEncrypted();
        TLRPC.Document r43 = r3.media.document;
        r43.id = r18.id;
        r43.access_hash = r18.access_hash;
        r43.date = r52.date;
        r43.attributes = r52.attributes;
        r43.mime_type = r52.mime_type;
        r43.size = r18.size;
        TLRPC.DecryptedMessageMedia r22 = r19.media;
        r43.key = r22.key;
        r43.iv = r22.iv;
        ArrayList<TLRPC.PhotoSize> r23 = r52.thumbs;
        r43.thumbs = r23;
        r43.dc_id = r18.dc_id;
        if (r23.isEmpty() == false) goto L17;
        TLRPC.TL_photoSizeEmpty r12 = new TLRPC.TL_photoSizeEmpty();
        r12.type = "s";
        r3.media.document.thumbs.add(r12);
    L17:
        String r13 = r3.attachPath;
        if (r13 != null) goto L20;
    L24:
        ArrayList<TLRPC.Message> r53 = new ArrayList();
        r53.add(r3);
        getMessagesStorage().putMessages(r53, false, true, false, 0, 0, 0);
        return;
    L20:
        if (r13.startsWith(FileLoader.getDirectory(4).getAbsolutePath()) == false) goto L24;
        if (new File(r3.attachPath).renameTo(getFileLoader().getPathToAttach(r3.media.document)) == false) goto L24;
        r17.mediaExists = r17.attachPathExists;
        r17.attachPathExists = false;
        r3.attachPath = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L24
    L28:
        return;
    L27:
        return;
    }

    public void acceptSecretChat(TLRPC.EncryptedChat r4) {
        if (this.acceptingChats.get(r4.id) == null) goto L5;
        return;
    L5:
        this.acceptingChats.put(r4.id, r4);
        TLRPC.TL_messages_getDhConfig r0 = new TLRPC.TL_messages_getDhConfig();
        r0.random_length = 256;
        r0.version = getMessagesStorage().getLastSecretVersion();
        getConnectionsManager().sendRequest(r0, new SecretChatHelper$$ExternalSyntheticLambda23(this, r4));
    }

    public void checkSecretHoles(TLRPC.EncryptedChat r12, ArrayList<TLRPC.Message> r13) {
        ArrayList<TL_decryptedMessageHolder> r0 = this.secretHolesQueue.get(r12.id);
        if (r0 == null) goto L32;
        Collections.sort(r0, new SecretChatHelper$$ExternalSyntheticLambda5());
        boolean r2 = false;
    L7:
        if (r0.size() <= 0) goto L13;
        TL_decryptedMessageHolder r3 = r0.get(0);
        TLRPC.TL_decryptedMessageLayer r5 = r3.layer;
        int r6 = r5.out_seq_no;
        int r7 = r12.seq_in;
        if (r6 != r7) goto L11;
    L14:
        this.applyPeerLayer(r12, r5.layer);
        TLRPC.TL_decryptedMessageLayer r22 = r3.layer;
        r12.seq_in = r22.out_seq_no;
        r12.in_seq_no = r22.in_seq_no;
        r0.remove(0);
        if (r3.decryptedWithVersion != 2) goto L17;
        r12.mtproto_seq = Math.min(r12.mtproto_seq, r12.seq_in);
    L17:
        SecretChatHelper r52 = this;
        TLRPC.EncryptedChat r62 = r12;
        TLRPC.Message r11 = r52.processDecryptedObject(r62, r3.file, r3.date, r3.layer.message, r3.new_key_used);
        if (r11 == null) goto L20;
        r13.add(r11);
    L20:
        r2 = true;
        SecretChatHelper this = r52;
        r12 = r62;
        goto L7
    L11:
        if (r7 == (r6 - 2)) goto L14;
    L13:
        SecretChatHelper r53 = this;
        TLRPC.EncryptedChat r63 = r12;
        if (r0.isEmpty() == false) goto L24;
        r53.secretHolesQueue.remove(r63.id);
    L24:
        if (r2 == false) goto L31;
        r53.getMessagesStorage().updateEncryptedChatSeq(r63, true);
        return;
    L31:
        return;
    }

    public void cleanup() {
        this.sendingNotifyLayer.clear();
        this.acceptingChats.clear();
        this.secretHolesQueue.clear();
        this.pendingSecretMessages.clear();
        this.requestedHoles.clear();
        this.delayedEncryptedChatUpdates.clear();
        this.pendingEncMessagesToDelete.clear();
        this.startingSecretChat = false;
    }

    public void declineSecretChat(int r3, boolean r4) {
        declineSecretChat(r3, r4, 0);
    }

    public void declineSecretChat(int r4, boolean r5, long r6) {
        if (r6 == 0) goto L17;
    L15:
        TLRPC.TL_messages_discardEncryption r0 = new TLRPC.TL_messages_discardEncryption();
        r0.chat_id = r4;
        r0.delete_history = r5;
        getConnectionsManager().sendRequest(r0, new SecretChatHelper$$ExternalSyntheticLambda29(this, r6));
        return;
    L17:
        NativeByteBuffer r7 = new NativeByteBuffer(12);     // Catch: Exception -> L11
        r7.writeInt32(100);     // Catch: Exception -> L9
        r7.writeInt32(r4);     // Catch: Exception -> L9
        r7.writeBool(r5);     // Catch: Exception -> L9
    L14:
        r6 = getMessagesStorage().createPendingTask(r7);
    L9:
        Exception e = e;
    L13:
        FileLog.e(e);
    L11:
        e = move-exception;
        r7 = null;
        e = e;
        goto L13
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r16v3 */
    /* JADX WARN: Type inference failed for: r16v4, types: [java.util.ArrayList<org.telegram.tgnet.TLRPC$Message>] */
    /* JADX WARN: Type inference failed for: r16v9 */
    public ArrayList<TLRPC.Message> decryptMessage(TLRPC.EncryptedMessage r20) {
        TLRPC.EncryptedChat r12 = getMessagesController().getEncryptedChatDB(r20.chat_id, true);
        if (r12 == null) goto L110;
        if ((r12 instanceof TLRPC.TL_encryptedChatDiscarded) == true) goto L110;
    L12:
        e = move-exception;
        FileLog.e(e);
    L108:
        return null;
    L8:
        if ((r12 instanceof TLRPC.TL_encryptedChatWaiting) == false) goto L16;
        ArrayList<TLRPC.Update> r1 = this.pendingSecretMessages.get(r12.id);     // Catch: Exception -> L12
        if (r1 != null) goto L14;
        r1 = new ArrayList();     // Catch: Exception -> L12
        this.pendingSecretMessages.put(r12.id, r1);     // Catch: Exception -> L12
    L14:
        TL_update.TL_updateNewEncryptedMessage r0 = new TL_update.TL_updateNewEncryptedMessage();     // Catch: Exception -> L12
        r0.message = r20;     // Catch: Exception -> L12
        r1.add(r0);     // Catch: Exception -> L12
        return null;
    L16:
        NativeByteBuffer r13 = new NativeByteBuffer(r20.bytes.length);     // Catch: Exception -> L12
        r13.writeBytes(r20.bytes);     // Catch: Exception -> L12
        r13.position(0);     // Catch: Exception -> L12
        long r2 = r13.readInt64(false);     // Catch: Exception -> L12
        if (r12.key_fingerprint != r2) goto L20;
        byte[] r4 = r12.auth_key;     // Catch: Exception -> L12
    L19:
        boolean r15 = false;
    L26:
        if (r4 == null) goto L105;
        byte[] r3 = r13.readData(16, false);     // Catch: Exception -> L12
        if (r12.admin_id != getUserConfig().getClientUserId()) goto L30;
        boolean r5 = true;
    L32:
        if (r12.mtproto_seq == 0) goto L35;
        boolean r6 = false;
    L34:
        byte[] r22 = r4;
        if (decryptWithMtProtoVersion(r13, r22, r3, 2, r5, r6) == true) goto L45;
        if (r6 == false) goto L44;
        TLRPC.Message r16 = 0;
        int r132 = 2;
        SecretChatHelper r02 = this;
        if (r02.decryptWithMtProtoVersion(r13, r22, r3, 1, r5, false) == false) goto L112;
        int r23 = 1;
    L46:
        TLObject r32 = TLClassStore.Instance().TLdeserialize(r13, r13.readInt32(false), false);     // Catch: Exception -> L12
        r13.reuse();     // Catch: Exception -> L12
        if (r15 == true) goto L50;
        r12.key_use_count_in = (short) (r12.key_use_count_in + 1);     // Catch: Exception -> L12
    L50:
        if ((r32 instanceof TLRPC.TL_decryptedMessageLayer) == false) goto L95;
        TLRPC.TL_decryptedMessageLayer r33 = (TLRPC.TL_decryptedMessageLayer) r32;     // Catch: Exception -> L12
        if (r12.seq_in != 0) goto L60;
        if (r12.seq_out != 0) goto L60;
        if (r12.admin_id != r02.getUserConfig().getClientUserId()) goto L58;
        r12.seq_out = 1;     // Catch: Exception -> L12
        r12.seq_in = -2;     // Catch: Exception -> L12
        goto L60
    L58:
        r12.seq_in = -1;     // Catch: Exception -> L12
    L60:
        if (r33.random_bytes.length >= 15) goto L66;
        if (BuildVars.LOGS_ENABLED == false) goto L64;
        FileLog.e("got random bytes less than needed");     // Catch: Exception -> L12
    L64:
        return r16;
    L66:
        if (BuildVars.LOGS_ENABLED == false) goto L68;
        FileLog.d("current chat in_seq = " + r12.seq_in + " out_seq = " + r12.seq_out);     // Catch: Exception -> L12
        FileLog.d("got message with in_seq = " + r33.in_seq_no + " out_seq = " + r33.out_seq_no);     // Catch: Exception -> L12
    L68:
        int r14 = r33.out_seq_no;     // Catch: Exception -> L12
        int r42 = r12.seq_in;     // Catch: Exception -> L12
        if (r14 > r42) goto L71;
        return r16;
    L71:
        if (r23 != 1) goto L77;
        int r52 = r12.mtproto_seq;     // Catch: Exception -> L12
        if (r52 == 0) goto L77;
        if (r14 < r52) goto L77;
        return r16;
    L77:
        if (r42 != (r14 - r132)) goto L79;
        if (r23 != r132) goto L92;
        r12.mtproto_seq = Math.min(r12.mtproto_seq, r42);     // Catch: Exception -> L12
    L92:
        r02.applyPeerLayer(r12, r33.layer);     // Catch: Exception -> L12
        r12.seq_in = r33.out_seq_no;     // Catch: Exception -> L12
        r12.in_seq_no = r33.in_seq_no;     // Catch: Exception -> L12
        r02.getMessagesStorage().updateEncryptedChatSeq(r12, true);     // Catch: Exception -> L12
        r32 = r33.message;     // Catch: Exception -> L12
    L93:
        TLObject r43 = r32;
        ArrayList<TLRPC.Message> r62 = new ArrayList();     // Catch: Exception -> L12
        TLRPC.Message r24 = r02.processDecryptedObject(r12, r20.file, r20.date, r43, r15);     // Catch: Exception -> L12
        if (r24 == null) goto L102;
        r62.add(r24);     // Catch: Exception -> L12
    L102:
        r02.checkSecretHoles(r12, r62);     // Catch: Exception -> L12
        return r62;
    L79:
        if (BuildVars.LOGS_ENABLED == false) goto L81;
        FileLog.e("got hole");     // Catch: Exception -> L12
    L81:
        r02.sendResendMessage(r12, r12.seq_in + r132, r33.out_seq_no - r132, r16);     // Catch: Exception -> L12
        ArrayList<TL_decryptedMessageHolder> r17 = r02.secretHolesQueue.get(r12.id);     // Catch: Exception -> L12
        if (r17 != null) goto L85;
        r17 = new ArrayList();     // Catch: Exception -> L12
        r02.secretHolesQueue.put(r12.id, r17);     // Catch: Exception -> L12
    L85:
        if (r17.size() < 4) goto L88;
        r02.secretHolesQueue.remove(r12.id);     // Catch: Exception -> L12
        TLRPC.TL_encryptedChatDiscarded r18 = new TLRPC.TL_encryptedChatDiscarded();     // Catch: Exception -> L12
        r18.id = r12.id;     // Catch: Exception -> L12
        r18.user_id = r12.user_id;     // Catch: Exception -> L12
        r18.auth_key = r12.auth_key;     // Catch: Exception -> L12
        r18.key_create_date = r12.key_create_date;     // Catch: Exception -> L12
        r18.key_use_count_in = r12.key_use_count_in;     // Catch: Exception -> L12
        r18.key_use_count_out = r12.key_use_count_out;     // Catch: Exception -> L12
        r18.seq_in = r12.seq_in;     // Catch: Exception -> L12
        r18.seq_out = r12.seq_out;     // Catch: Exception -> L12
        AndroidUtilities.runOnUIThread(new SecretChatHelper$$ExternalSyntheticLambda31(r02, r18));     // Catch: Exception -> L12
        r02.declineSecretChat(r12.id, false);     // Catch: Exception -> L12
        return null;
    L88:
        TL_decryptedMessageHolder r03 = new TL_decryptedMessageHolder();     // Catch: Exception -> L12
        r03.layer = r33;     // Catch: Exception -> L12
        r03.file = r20.file;     // Catch: Exception -> L12
        r03.date = r20.date;     // Catch: Exception -> L12
        r03.new_key_used = r15;     // Catch: Exception -> L12
        r03.decryptedWithVersion = r23;     // Catch: Exception -> L12
        r17.add(r03);     // Catch: Exception -> L12
        return null;
    L95:
        if ((r32 instanceof TLRPC.TL_decryptedMessageService) == false) goto L104;
        if ((((TLRPC.TL_decryptedMessageService) r32).action instanceof TLRPC.TL_decryptedMessageActionNotifyLayer) == true) goto L93;
    L104:
        return null;
    L112:
        return null;
    L44:
        return null;
    L45:
        r16 = 0;
        r132 = 2;
        r02 = this;
        r23 = 2;
        goto L46
    L35:
        r6 = true;
        goto L34
    L30:
        r5 = false;
        goto L32
    L105:
        r13.reuse();     // Catch: Exception -> L12
        if (BuildVars.LOGS_ENABLED == false) goto L108;
        FileLog.e(String.format("fingerprint mismatch %x", new Object[]{Long.valueOf(r2)}));     // Catch: Exception -> L12
        goto L108
    L20:
        long r44 = r12.future_key_fingerprint;     // Catch: Exception -> L12
        if (r44 != 0) goto L23;
    L25:
        r4 = null;
        goto L19
    L23:
        if (r44 != r2) goto L25;
        r4 = r12.future_auth_key;     // Catch: Exception -> L12
        r15 = true;
    L110:
        return null;
    }

    public void performSendEncryptedRequest(TLRPC.DecryptedMessage r10, TLRPC.Message r11, TLRPC.EncryptedChat r12, TLRPC.InputEncryptedFile r13, String r14, MessageObject r15) {
        if (r10 != null) goto L4;
        return;
    L4:
        if (r12.auth_key != null) goto L6;
        return;
    L6:
        if ((r12 instanceof TLRPC.TL_encryptedChatRequested) == false) goto L8;
        return;
    L8:
        if ((r12 instanceof TLRPC.TL_encryptedChatWaiting) == false) goto L10;
        return;
    L10:
        getSendMessagesHelper().putToSendingMessages(r11, false);
        Utilities.stageQueue.postRunnable(new SecretChatHelper$$ExternalSyntheticLambda18(this, r12, r10, r11, r13, r15, r14));
    }

    public void performSendEncryptedRequest(TLRPC.TL_messages_sendEncryptedMultiMedia r10, SendMessagesHelper.DelayedMessage r11) {
        int r0 = 0;
    L4:
        if (r0 >= r10.files.size()) goto L6;
        performSendEncryptedRequest(r10.messages.get(r0), r11.messages.get(r0), r11.encryptedChat, r10.files.get(r0), r11.originalPaths.get(r0), r11.messageObjects.get(r0));
        r0 = r0 + 1;
        goto L4
    }

    public void processAcceptedSecretChat(TLRPC.EncryptedChat r10) {
        BigInteger r0 = new BigInteger(1, getMessagesStorage().getSecretPBytes());
        BigInteger r1 = new BigInteger(1, r10.g_a_or_b);
        if (Utilities.isGoodGaAndGb(r1, r0) == true) goto L6;
        declineSecretChat(r10.id, false);
        return;
    L6:
        byte[] r02 = r1.modPow(new BigInteger(1, r10.a_or_b), r0).toByteArray();
        if (r02.length <= 256) goto L11;
        byte[] r12 = new byte[256];
        System.arraycopy(r02, r02.length - 256, r12, 0, 256);
    L9:
        r02 = r12;
    L16:
        byte[] r13 = Utilities.computeSHA1(r02);
        byte[] r5 = new byte[8];
        System.arraycopy(r13, r13.length - 8, r5, 0, 8);
        if (r10.key_fingerprint != Utilities.bytesToLong(r5)) goto L23;
        r10.auth_key = r02;
        r10.key_create_date = getConnectionsManager().getCurrentTime();
        r10.seq_in = -2;
        r10.seq_out = 1;
        getMessagesStorage().updateEncryptedChat(r10);
        getMessagesController().putEncryptedChat(r10, false);
        ArrayList<TLRPC.Update> r2 = this.pendingSecretMessages.get(r10.id);
        if (r2 == null) goto L21;
        getMessagesController().processUpdateArray(r2, null, null, false, 0);
        this.pendingSecretMessages.remove(r10.id);
    L21:
        AndroidUtilities.runOnUIThread(new SecretChatHelper$$ExternalSyntheticLambda16(this, r10));
        return;
    L23:
        TLRPC.TL_encryptedChatDiscarded r03 = new TLRPC.TL_encryptedChatDiscarded();
        r03.id = r10.id;
        r03.user_id = r10.user_id;
        r03.auth_key = r10.auth_key;
        r03.key_create_date = r10.key_create_date;
        r03.key_use_count_in = r10.key_use_count_in;
        r03.key_use_count_out = r10.key_use_count_out;
        r03.seq_in = r10.seq_in;
        r03.seq_out = r10.seq_out;
        r03.admin_id = r10.admin_id;
        r03.mtproto_seq = r10.mtproto_seq;
        getMessagesStorage().updateEncryptedChat(r03);
        AndroidUtilities.runOnUIThread(new SecretChatHelper$$ExternalSyntheticLambda17(this, r03));
        declineSecretChat(r10.id, false);
        return;
    L11:
        if (r02.length >= 256) goto L16;
        r12 = new byte[256];
        System.arraycopy(r02, 0, r12, 256 - r02.length, r02.length);
        int r52 = 0;
    L14:
        if (r52 >= (256 - r02.length)) goto L9;
        r12[r52] = 0;
        r52 = r52 + 1;
        goto L14
    }

    public TLRPC.Message processDecryptedObject(TLRPC.EncryptedChat r18, TLRPC.EncryptedFile r19, int r20, TLObject r21, boolean r22) {
        if (r21 == null) goto L352;
        long r6 = r18.admin_id;
        if (r6 != getUserConfig().getClientUserId()) goto L8;
        r6 = r18.participant_id;
    L8:
        if (r18.exchange_id == 0) goto L10;
    L14:
        long r8 = r18.exchange_id;
        int r13 = 0;
        long r15 = r6;
        if (r8 != 0) goto L20;
        TLRPC.Message r12 = null;
        if (r18.future_key_fingerprint == 0) goto L22;
        if (r22 == true) goto L22;
        r18.future_auth_key = new byte[256];
        r18.future_key_fingerprint = 0;
        getMessagesStorage().updateEncryptedChat(r18);
    L26:
        if ((r21 instanceof TLRPC.TL_decryptedMessage) == false) goto L231;
        TLRPC.TL_decryptedMessage r4 = (TLRPC.TL_decryptedMessage) r21;
        TLRPC.TL_message_secret r5 = new TLRPC.TL_message_secret();
        r5.ttl = r4.ttl;
        r5.entities = r4.entities;
        r5.message = r4.message;
        r5.date = r20;
        int r82 = getUserConfig().getNewMessageId();
        r5.id = r82;
        r5.local_id = r82;
        r5.silent = r4.silent;
        getUserConfig().saveConfig(false);
        TLRPC.TL_peerUser r83 = new TLRPC.TL_peerUser();
        r5.from_id = r83;
        r83.user_id = r15;
        TLRPC.TL_peerUser r84 = new TLRPC.TL_peerUser();
        r5.peer_id = r84;
        r84.user_id = getUserConfig().getClientUserId();
        r5.random_id = r4.random_id;
        r5.unread = true;
        r5.flags = 768;
        String r85 = r4.via_bot_name;
        if (r85 != null) goto L30;
    L32:
        long r86 = r4.grouped_id;
        if (r86 == 0) goto L35;
        r5.grouped_id = r86;
        r5.flags |= 131072;
    L35:
        r5.dialog_id = DialogObject.makeEncryptedDialogId(r18.id);
        if (r4.reply_to_random_id == 0) goto L38;
        TLRPC.TL_messageReplyHeader r1 = new TLRPC.TL_messageReplyHeader();
        r5.reply_to = r1;
        r1.reply_to_random_id = r4.reply_to_random_id;
        r5.flags |= 8;
    L38:
        TLRPC.DecryptedMessageMedia r14 = r4.media;
        if (r14 != null) goto L41;
    L221:
        r5.media = new TLRPC.TL_messageMediaEmpty();
    L222:
        int r0 = r5.ttl;
        if (r0 == 0) goto L227;
        TLRPC.MessageMedia r16 = r5.media;
        if (r16.ttl_seconds != 0) goto L227;
        r16.ttl_seconds = r0;
        r16.flags |= 4;
    L227:
        String r02 = r5.message;
        if (r02 == null) goto L230;
        r5.message = r02.replace(8238, ' ');
    L230:
        return r5;
    L41:
        if ((r14 instanceof TLRPC.TL_decryptedMessageMediaEmpty) == true) goto L221;
        if ((r14 instanceof TLRPC.TL_decryptedMessageMediaWebPage) == false) goto L46;
        TLRPC.TL_messageMediaWebPage r03 = new TLRPC.TL_messageMediaWebPage();
        r5.media = r03;
        r03.webpage = new TLRPC.TL_webPageUrlPending();
        r5.media.webpage.url = r4.media.url;
        goto L222
    L46:
        boolean r87 = r14 instanceof TLRPC.TL_decryptedMessageMediaContact;
        String r9 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r87 == false) goto L50;
        TLRPC.TL_messageMediaContact r04 = new TLRPC.TL_messageMediaContact();
        r5.media = r04;
        TLRPC.DecryptedMessageMedia r17 = r4.media;
        r04.last_name = r17.last_name;
        r04.first_name = r17.first_name;
        r04.phone_number = r17.phone_number;
        r04.user_id = r17.user_id;
        r04.vcard = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L222
    L50:
        if ((r14 instanceof TLRPC.TL_decryptedMessageMediaGeoPoint) == false) goto L52;
        TLRPC.TL_messageMediaGeo r05 = new TLRPC.TL_messageMediaGeo();
        r5.media = r05;
        r05.geo = new TLRPC.TL_geoPoint();
        TLRPC.GeoPoint r06 = r5.media.geo;
        TLRPC.DecryptedMessageMedia r110 = r4.media;
        r06.lat = r110.lat;
        r06._long = r110._long;
        goto L222
    L52:
        TLRPC.Message r162 = r12;
        if ((r14 instanceof TLRPC.TL_decryptedMessageMediaPhoto) == false) goto L86;
        byte[] r07 = r14.key;
        if (r07 != null) goto L57;
    L84:
        return r162;
    L57:
        if (r07.length != 32) goto L84;
        byte[] r08 = r14.iv;
        if (r08 == null) goto L84;
        if (r08.length != 32) goto L84;
        TLRPC.TL_messageMediaPhoto r09 = new TLRPC.TL_messageMediaPhoto();
        r5.media = r09;
        r09.flags |= 3;
        if (TextUtils.isEmpty(r5.message) == false) goto L69;
        String r010 = r4.media.caption;
        if (r010 == null) goto L68;
        r9 = r010;
    L68:
        r5.message = r9;
    L69:
        r5.media.photo = new TLRPC.TL_photo();
        TLRPC.Photo r011 = r5.media.photo;
        r011.file_reference = new byte[0];
        r011.date = r5.date;
        TLRPC.DecryptedMessageMedia r012 = r4.media;
        byte[] r111 = ((TLRPC.TL_decryptedMessageMediaPhoto) r012).thumb;
        if (r111 != null) goto L72;
    L80:
        int r013 = r5.ttl;
        if (r013 == 0) goto L83;
        TLRPC.MessageMedia r112 = r5.media;
        r112.ttl_seconds = r013;
        r112.flags |= 4;
    L83:
        TLRPC.TL_photoSize_layer127 r014 = new TLRPC.TL_photoSize_layer127();
        TLRPC.DecryptedMessageMedia r113 = r4.media;
        r014.w = r113.w;
        r014.h = r113.h;
        r014.type = "x";
        r014.size = (int) r19.size;
        TLRPC.TL_fileEncryptedLocation r114 = new TLRPC.TL_fileEncryptedLocation();
        r014.location = r114;
        TLRPC.DecryptedMessageMedia r3 = r4.media;
        r114.key = r3.key;
        r114.iv = r3.iv;
        r114.dc_id = r19.dc_id;
        r114.volume_id = r19.id;
        r114.secret = r19.access_hash;
        r114.local_id = r19.key_fingerprint;
        r5.media.photo.sizes.add(r014);
        goto L222
    L72:
        if (r111.length == 0) goto L80;
        if (r111.length > 6000) goto L80;
        if (r012.thumb_w > 100) goto L80;
        if (r012.thumb_h > 100) goto L80;
        TLRPC.TL_photoCachedSize r015 = new TLRPC.TL_photoCachedSize();
        TLRPC.DecryptedMessageMedia r32 = r4.media;
        r015.w = r32.thumb_w;
        r015.h = r32.thumb_h;
        r015.bytes = r111;
        r015.type = "s";
        r015.location = new TLRPC.TL_fileLocationUnavailable();
        r5.media.photo.sizes.add(r015);
        goto L80
    L86:
        if ((r14 instanceof TLRPC.TL_decryptedMessageMediaVideo) == false) goto L124;
        byte[] r016 = r14.key;
        if (r016 != null) goto L90;
    L122:
        return r162;
    L90:
        if (r016.length != 32) goto L122;
        byte[] r017 = r14.iv;
        if (r017 == null) goto L122;
        if (r017.length != 32) goto L122;
        TLRPC.TL_messageMediaDocument r018 = new TLRPC.TL_messageMediaDocument();
        r5.media = r018;
        r018.flags |= 3;
        r018.document = new TLRPC.TL_documentEncrypted();
        TLRPC.Document r019 = r5.media.document;
        TLRPC.DecryptedMessageMedia r115 = r4.media;
        r019.key = r115.key;
        r019.iv = r115.iv;
        r019.dc_id = r19.dc_id;
        if (TextUtils.isEmpty(r5.message) == false) goto L102;
        String r020 = r4.media.caption;
        if (r020 == null) goto L101;
        r9 = r020;
    L101:
        r5.message = r9;
    L102:
        TLRPC.Document r021 = r5.media.document;
        r021.date = r20;
        r021.size = r19.size;
        r021.id = r19.id;
        r021.access_hash = r19.access_hash;
        TLRPC.DecryptedMessageMedia r116 = r4.media;
        String r2 = r116.mime_type;
        r021.mime_type = r2;
        if (r2 != null) goto L105;
        r021.mime_type = "video/mp4";
    L105:
        byte[] r022 = ((TLRPC.TL_decryptedMessageMediaVideo) r116).thumb;
        if (r022 != null) goto L108;
    L116:
        TLRPC.PhotoSize r117 = new TLRPC.TL_photoSizeEmpty();
        r117.type = "s";
    L117:
        r5.media.document.thumbs.add(r117);
        r5.media.document.flags |= 1;
        TLRPC.TL_documentAttributeVideo_layer159 r023 = new TLRPC.TL_documentAttributeVideo_layer159();
        TLRPC.DecryptedMessageMedia r118 = r4.media;
        r023.w = r118.w;
        r023.h = r118.h;
        r023.duration = r118.duration;
        r023.supports_streaming = false;
        r5.media.document.attributes.add(r023);
        int r024 = r5.ttl;
        if (r024 == 0) goto L120;
        TLRPC.MessageMedia r119 = r5.media;
        r119.ttl_seconds = r024;
        r119.flags |= 4;
    L120:
        if (r024 == 0) goto L222;
        r5.ttl = Math.max(r4.media.duration + 1, r024);
        goto L222
    L108:
        if (r022.length == 0) goto L116;
        if (r022.length > 6000) goto L116;
        if (r116.thumb_w > 100) goto L116;
        if (r116.thumb_h > 100) goto L116;
        r117 = new TLRPC.TL_photoCachedSize();
        r117.bytes = r022;
        TLRPC.DecryptedMessageMedia r025 = r4.media;
        r117.w = r025.thumb_w;
        r117.h = r025.thumb_h;
        r117.type = "s";
        r117.location = new TLRPC.TL_fileLocationUnavailable();
        goto L117
    L124:
        if ((r14 instanceof TLRPC.TL_decryptedMessageMediaDocument) == false) goto L183;
        byte[] r026 = r14.key;
        if (r026 != null) goto L128;
    L181:
        return r162;
    L128:
        if (r026.length != 32) goto L181;
        byte[] r027 = r14.iv;
        if (r027 == null) goto L181;
        if (r027.length != 32) goto L181;
        TLRPC.TL_messageMediaDocument r028 = new TLRPC.TL_messageMediaDocument();
        r5.media = r028;
        r028.flags |= 3;
        if (TextUtils.isEmpty(r5.message) == false) goto L141;
        String r029 = r4.media.caption;
        if (r029 != null) goto L140;
        r029 = _UrlKt.FRAGMENT_ENCODE_SET;
    L140:
        r5.message = r029;
    L141:
        r5.media.document = new TLRPC.TL_documentEncrypted();
        TLRPC.Document r030 = r5.media.document;
        r030.id = r19.id;
        r030.access_hash = r19.access_hash;
        r030.date = r20;
        TLRPC.DecryptedMessageMedia r120 = r4.media;
        r030.mime_type = r120.mime_type;
        if ((r120 instanceof TLRPC.TL_decryptedMessageMediaDocument_layer8) == false) goto L144;
        TLRPC.TL_documentAttributeFilename r031 = new TLRPC.TL_documentAttributeFilename();
        r031.file_name = r4.media.file_name;
        r5.media.document.attributes.add(r031);
    L146:
        if (r5.ttl <= 0) goto L157;
        int r032 = r5.media.document.attributes.size();
    L148:
        if (r13 >= r032) goto L156;
        TLRPC.DocumentAttribute r121 = r5.media.document.attributes.get(r13);
        if ((r121 instanceof TLRPC.TL_documentAttributeAudio) == true) goto L155;
        if ((r121 instanceof TLRPC.TL_documentAttributeVideo) == true) goto L155;
        r13 = r13 + 1;
    L155:
        r5.ttl = (int) Math.max(r121.duration + 1.0d, r5.ttl);
    L156:
        r5.ttl = Math.max(r4.media.duration + 1, r5.ttl);
    L157:
        TLRPC.Document r033 = r5.media.document;
        long r132 = r4.media.size;
        long r10 = r19.size;
        if (r132 == 0) goto L160;
        r10 = Math.min(r132, r10);
    L160:
        r033.size = r10;
        TLRPC.Document r034 = r5.media.document;
        TLRPC.DecryptedMessageMedia r122 = r4.media;
        r034.key = r122.key;
        r034.iv = r122.iv;
        String r123 = r034.mime_type;
        if (r123 != null) goto L164;
        r034.mime_type = _UrlKt.FRAGMENT_ENCODE_SET;
    L168:
        byte[] r035 = ((TLRPC.TL_decryptedMessageMediaDocument) r4.media).thumb;
        if (r035 != null) goto L171;
    L175:
        TLRPC.PhotoSize r124 = new TLRPC.TL_photoSizeEmpty();
        r124.type = "s";
    L176:
        r5.media.document.thumbs.add(r124);
        TLRPC.Document r036 = r5.media.document;
        r036.flags |= 1;
        r036.dc_id = r19.dc_id;
        if (MessageObject.isVoiceMessage(r5) == false) goto L179;
    L180:
        r5.media_unread = true;
        goto L222
    L179:
        if (MessageObject.isRoundVideoMessage(r5) == false) goto L222;
    L171:
        if (r035.length == 0) goto L175;
        if (r035.length > 20000) goto L175;
        r124 = new TLRPC.TL_photoCachedSize();
        r124.bytes = r035;
        TLRPC.DecryptedMessageMedia r037 = r4.media;
        r124.w = r037.thumb_w;
        r124.h = r037.thumb_h;
        r124.type = "s";
        r124.location = new TLRPC.TL_fileLocationUnavailable();
        goto L176
    L164:
        if ("application/x-tgsticker".equals(r123) == false) goto L166;
    L167:
        r5.media.document.mime_type = "application/x-bad_tgsticker";
        goto L168
    L166:
        if ("application/x-tgsdice".equals(r5.media.document.mime_type) == false) goto L168;
    L144:
        r030.attributes = r120.attributes;
        goto L146
    L183:
        if ((r14 instanceof TLRPC.TL_decryptedMessageMediaExternalDocument) == false) goto L191;
        TLRPC.TL_messageMediaDocument r125 = new TLRPC.TL_messageMediaDocument();
        r5.media = r125;
        r125.flags |= 3;
        r5.message = _UrlKt.FRAGMENT_ENCODE_SET;
        r125.document = new TLRPC.TL_document();
        TLRPC.Document r126 = r5.media.document;
        TLRPC.DecryptedMessageMedia r23 = r4.media;
        r126.id = r23.id;
        r126.access_hash = r23.access_hash;
        r126.file_reference = new byte[0];
        r126.date = r23.date;
        r126.attributes = r23.attributes;
        r126.mime_type = r23.mime_type;
        r126.dc_id = r23.dc_id;
        r126.size = r23.size;
        r126.thumbs.add(((TLRPC.TL_decryptedMessageMediaExternalDocument) r23).thumb);
        TLRPC.Document r127 = r5.media.document;
        r127.flags |= 1;
        if (r127.mime_type != null) goto L188;
        r127.mime_type = _UrlKt.FRAGMENT_ENCODE_SET;
    L188:
        if (MessageObject.isAnimatedStickerMessage(r5) == false) goto L222;
        r5.stickerVerified = 0;
        getMediaDataController().verifyAnimatedStickerMessage(r5, true);
        goto L222
    L191:
        if ((r14 instanceof TLRPC.TL_decryptedMessageMediaAudio) == false) goto L218;
        byte[] r038 = r14.key;
        if (r038 != null) goto L195;
    L216:
        return r162;
    L195:
        if (r038.length != 32) goto L216;
        byte[] r039 = r14.iv;
        if (r039 == null) goto L216;
        if (r039.length != 32) goto L216;
        TLRPC.TL_messageMediaDocument r040 = new TLRPC.TL_messageMediaDocument();
        r5.media = r040;
        r040.flags |= 3;
        r040.document = new TLRPC.TL_documentEncrypted();
        TLRPC.Document r041 = r5.media.document;
        TLRPC.DecryptedMessageMedia r128 = r4.media;
        r041.key = r128.key;
        r041.iv = r128.iv;
        r041.id = r19.id;
        r041.access_hash = r19.access_hash;
        r041.date = r20;
        r041.size = r19.size;
        r041.dc_id = r19.dc_id;
        r041.mime_type = r128.mime_type;
        if (TextUtils.isEmpty(r5.message) == false) goto L207;
        String r042 = r4.media.caption;
        if (r042 == null) goto L206;
        r9 = r042;
    L206:
        r5.message = r9;
    L207:
        TLRPC.Document r043 = r5.media.document;
        if (r043.mime_type != null) goto L210;
        r043.mime_type = "audio/ogg";
    L210:
        TLRPC.TL_documentAttributeAudio r044 = new TLRPC.TL_documentAttributeAudio();
        r044.duration = r4.media.duration;
        r044.voice = true;
        r5.media.document.attributes.add(r044);
        int r045 = r5.ttl;
        if (r045 == 0) goto L214;
        r5.ttl = Math.max(r4.media.duration + 1, r045);
    L214:
        if (r5.media.document.thumbs.isEmpty() == false) goto L222;
        TLRPC.TL_photoSizeEmpty r046 = new TLRPC.TL_photoSizeEmpty();
        r046.type = "s";
        r5.media.document.thumbs.add(r046);
        goto L222
    L218:
        if ((r14 instanceof TLRPC.TL_decryptedMessageMediaVenue) == false) goto L220;
        TLRPC.TL_messageMediaVenue r047 = new TLRPC.TL_messageMediaVenue();
        r5.media = r047;
        r047.geo = new TLRPC.TL_geoPoint();
        TLRPC.MessageMedia r048 = r5.media;
        TLRPC.GeoPoint r129 = r048.geo;
        TLRPC.DecryptedMessageMedia r24 = r4.media;
        r129.lat = r24.lat;
        r129._long = r24._long;
        r048.title = r24.title;
        r048.address = r24.address;
        r048.provider = r24.provider;
        r048.venue_id = r24.venue_id;
        r048.venue_type = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L222
    L220:
        return r162;
    L30:
        if (r85.length() <= 0) goto L32;
        r5.via_bot_name = r4.via_bot_name;
        r5.flags |= 2048;
        goto L32
    L231:
        TLRPC.Message r163 = r12;
        if ((r21 instanceof TLRPC.TL_decryptedMessageService) == false) goto L348;
        TLRPC.TL_decryptedMessageService r25 = (TLRPC.TL_decryptedMessageService) r21;
        TLRPC.DecryptedMessageAction r42 = r25.action;
        if ((r42 instanceof TLRPC.TL_decryptedMessageActionSetMessageTTL) == false) goto L236;
    L337:
        TLRPC.TL_messageService r43 = new TLRPC.TL_messageService();
        if ((r25.action instanceof TLRPC.TL_decryptedMessageActionSetMessageTTL) == false) goto L344;
        TLRPC.TL_messageEncryptedAction r52 = new TLRPC.TL_messageEncryptedAction();
        r43.action = r52;
        TLRPC.DecryptedMessageAction r26 = r25.action;
        int r62 = r26.ttl_seconds;
        if (r62 < 0) goto L342;
        if (r62 > 31536000) goto L342;
    L343:
        r18.ttl = r26.ttl_seconds;
        r52.encryptedAction = r26;
        getMessagesStorage().updateEncryptedChatTTL(r18);
    L345:
        int r27 = getUserConfig().getNewMessageId();
        r43.id = r27;
        r43.local_id = r27;
        getUserConfig().saveConfig(false);
        r43.unread = true;
        r43.flags = 256;
        r43.date = r20;
        TLRPC.TL_peerUser r28 = new TLRPC.TL_peerUser();
        r43.from_id = r28;
        r28.user_id = r15;
        TLRPC.TL_peerUser r29 = new TLRPC.TL_peerUser();
        r43.peer_id = r29;
        r29.user_id = getUserConfig().getClientUserId();
        r43.dialog_id = DialogObject.makeEncryptedDialogId(r18.id);
        return r43;
    L342:
        r26.ttl_seconds = 31536000;
        goto L343
    L344:
        TLRPC.TL_messageEncryptedAction r53 = new TLRPC.TL_messageEncryptedAction();
        r43.action = r53;
        r53.encryptedAction = r25.action;
        goto L345
    L236:
        if ((r42 instanceof TLRPC.TL_decryptedMessageActionScreenshotMessages) == true) goto L337;
        if ((r42 instanceof TLRPC.TL_decryptedMessageActionFlushHistory) == false) goto L243;
        AndroidUtilities.runOnUIThread(new SecretChatHelper$$ExternalSyntheticLambda12(this, DialogObject.makeEncryptedDialogId(r18.id)));
        return r163;
    L243:
        if ((r42 instanceof TLRPC.TL_decryptedMessageActionDeleteMessages) == false) goto L249;
        if (r42.random_ids.isEmpty() == true) goto L247;
        this.pendingEncMessagesToDelete.addAll(r25.action.random_ids);
    L247:
        return r163;
    L249:
        if ((r42 instanceof TLRPC.TL_decryptedMessageActionReadMessages) == false) goto L254;
        if (r42.random_ids.isEmpty() == true) goto L361;
        int r33 = getConnectionsManager().getCurrentTime();
        getMessagesStorage().createTaskForSecretChat(r18.id, r33, r33, 1, r25.action.random_ids);
        return null;
    L361:
        return null;
    L254:
        if ((r42 instanceof TLRPC.TL_decryptedMessageActionNotifyLayer) == false) goto L257;
        applyPeerLayer(r18, r42.layer);
        return null;
    L257:
        if ((r42 instanceof TLRPC.TL_decryptedMessageActionRequestKey) == false) goto L286;
        long r88 = r18.exchange_id;
        if (r88 != 0) goto L261;
    L267:
        byte[] r34 = new byte[256];
        Utilities.random.nextBytes(r34);
        BigInteger r44 = new BigInteger(1, getMessagesStorage().getSecretPBytes());
        BigInteger r54 = BigInteger.valueOf(getMessagesStorage().getSecretG()).modPow(new BigInteger(1, r34), r44);
        BigInteger r89 = new BigInteger(1, r25.action.g_a);
        if (Utilities.isGoodGaAndGb(r89, r44) == true) goto L271;
        sendAbortKeyMessage(r18, null, r25.action.exchange_id);
        return null;
    L271:
        byte[] r55 = r54.toByteArray();
        if (r55.length <= 256) goto L274;
        byte[] r92 = new byte[256];
        System.arraycopy(r55, 1, r92, 0, 256);
        r55 = r92;
    L274:
        byte[] r35 = r89.modPow(new BigInteger(1, r34), r44).toByteArray();
        if (r35.length <= 256) goto L279;
        byte[] r45 = new byte[256];
        System.arraycopy(r35, r35.length - 256, r45, 0, 256);
    L277:
        r35 = r45;
    L284:
        byte[] r46 = Utilities.computeSHA1(r35);
        byte[] r7 = new byte[8];
        System.arraycopy(r46, r46.length - 8, r7, 0, 8);
        r18.exchange_id = r25.action.exchange_id;
        r18.future_auth_key = r35;
        r18.future_key_fingerprint = Utilities.bytesToLong(r7);
        r18.g_a_or_b = r55;
        getMessagesStorage().updateEncryptedChat(r18);
        sendAcceptKeyMessage(r18, null);
        return null;
    L279:
        if (r35.length >= 256) goto L284;
        r45 = new byte[256];
        System.arraycopy(r35, 0, r45, 256 - r35.length, r35.length);
        int r72 = 0;
    L282:
        if (r72 >= (256 - r35.length)) goto L277;
        r45[r72] = 0;
        r72 = r72 + 1;
        goto L282
    L261:
        if (r88 > r42.exchange_id) goto L263;
        sendAbortKeyMessage(r18, r163, r88);
        goto L267
    L263:
        if (BuildVars.LOGS_ENABLED == false) goto L265;
        FileLog.d("we already have request key with higher exchange_id");
    L265:
        return r163;
    L286:
        if ((r42 instanceof TLRPC.TL_decryptedMessageActionAcceptKey) == false) goto L309;
        if (r18.exchange_id != r42.exchange_id) goto L307;
        BigInteger r36 = new BigInteger(1, getMessagesStorage().getSecretPBytes());
        BigInteger r47 = new BigInteger(1, r25.action.g_b);
        if (Utilities.isGoodGaAndGb(r47, r36) == true) goto L293;
        r18.future_auth_key = new byte[256];
        r18.future_key_fingerprint = 0;
        r18.exchange_id = 0;
        getMessagesStorage().updateEncryptedChat(r18);
        sendAbortKeyMessage(r18, null, r25.action.exchange_id);
        return null;
    L293:
        byte[] r37 = r47.modPow(new BigInteger(1, r18.a_or_b), r36).toByteArray();
        if (r37.length <= 256) goto L298;
        byte[] r48 = new byte[256];
        System.arraycopy(r37, r37.length - 256, r48, 0, 256);
    L296:
        r37 = r48;
    L303:
        byte[] r49 = Utilities.computeSHA1(r37);
        byte[] r56 = new byte[8];
        System.arraycopy(r49, r49.length - 8, r56, 0, 8);
        long r410 = Utilities.bytesToLong(r56);
        if (r25.action.key_fingerprint != r410) goto L306;
        r18.future_auth_key = r37;
        r18.future_key_fingerprint = r410;
        getMessagesStorage().updateEncryptedChat(r18);
        sendCommitKeyMessage(r18, null);
        return null;
    L306:
        r18.future_auth_key = new byte[256];
        r18.future_key_fingerprint = 0;
        r18.exchange_id = 0;
        getMessagesStorage().updateEncryptedChat(r18);
        sendAbortKeyMessage(r18, null, r25.action.exchange_id);
        return null;
    L298:
        if (r37.length >= 256) goto L303;
        r48 = new byte[256];
        System.arraycopy(r37, 0, r48, 256 - r37.length, r37.length);
        int r57 = 0;
    L301:
        if (r57 >= (256 - r37.length)) goto L296;
        r48[r57] = 0;
        r57 = r57 + 1;
        goto L301
    L307:
        r18.future_auth_key = new byte[256];
        r18.future_key_fingerprint = 0;
        r18.exchange_id = 0;
        getMessagesStorage().updateEncryptedChat(r18);
        sendAbortKeyMessage(r18, null, r25.action.exchange_id);
        return null;
    L309:
        if ((r42 instanceof TLRPC.TL_decryptedMessageActionCommitKey) == false) goto L317;
        if (r18.exchange_id != r42.exchange_id) goto L315;
        long r58 = r18.future_key_fingerprint;
        if (r58 != r42.key_fingerprint) goto L315;
        long r210 = r18.key_fingerprint;
        byte[] r411 = r18.auth_key;
        r18.key_fingerprint = r58;
        r18.auth_key = r18.future_auth_key;
        r18.key_create_date = getConnectionsManager().getCurrentTime();
        r18.future_auth_key = r411;
        r18.future_key_fingerprint = r210;
        r18.key_use_count_in = 0;
        r18.key_use_count_out = 0;
        r18.exchange_id = 0;
        getMessagesStorage().updateEncryptedChat(r18);
        sendNoopMessage(r18, null);
        return null;
    L315:
        r18.future_auth_key = new byte[256];
        r18.future_key_fingerprint = 0;
        r18.exchange_id = 0;
        getMessagesStorage().updateEncryptedChat(r18);
        sendAbortKeyMessage(r18, null, r25.action.exchange_id);
        return null;
    L317:
        if ((r42 instanceof TLRPC.TL_decryptedMessageActionAbortKey) == false) goto L322;
        if (r18.exchange_id != r42.exchange_id) goto L370;
        r18.future_auth_key = new byte[256];
        r18.future_key_fingerprint = 0;
        r18.exchange_id = 0;
        getMessagesStorage().updateEncryptedChat(r18);
        return null;
    L370:
        return null;
    L322:
        if ((r42 instanceof TLRPC.TL_decryptedMessageActionNoop) == false) goto L325;
        return null;
    L325:
        if ((r42 instanceof TLRPC.TL_decryptedMessageActionResend) == false) goto L335;
        int r211 = r42.end_seq_no;
        int r38 = r18.in_seq_no;
        if (r211 < r38) goto L330;
        int r59 = r42.start_seq_no;
        if (r211 < r59) goto L360;
        if (r59 >= r38) goto L333;
        r42.start_seq_no = r38;
    L333:
        resendMessages(r42.start_seq_no, r211, r18);
        return null;
    L360:
        return null;
    L330:
        return null;
    L335:
        return null;
    L348:
        if (BuildVars.LOGS_ENABLED == false) goto L373;
        FileLog.e("unknown message " + r21);
        return null;
    L373:
        return null;
    L22:
        if (r8 == 0) goto L26;
        if (r22 == false) goto L26;
        r18.key_fingerprint = r18.future_key_fingerprint;
        r18.auth_key = r18.future_auth_key;
        r18.key_create_date = getConnectionsManager().getCurrentTime();
        r18.future_auth_key = new byte[256];
        r18.future_key_fingerprint = 0;
        r18.key_use_count_in = 0;
        r18.key_use_count_out = 0;
        r18.exchange_id = 0;
        getMessagesStorage().updateEncryptedChat(r18);
        goto L26
    L20:
        r12 = null;
        goto L22
    L10:
        if (r18.future_key_fingerprint != 0) goto L14;
        if (r18.key_use_count_in < 120) goto L14;
        requestNewSecretChatKey(r18);
        goto L14
    L352:
        if (BuildVars.LOGS_ENABLED == false) goto L374;
        FileLog.e("unknown TLObject");
        return null;
    L374:
        return null;
    }

    public void processPendingEncMessages() {
        if (this.pendingEncMessagesToDelete.isEmpty() == true) goto L6;
        AndroidUtilities.runOnUIThread(new SecretChatHelper$$ExternalSyntheticLambda24(this, new ArrayList(this.pendingEncMessagesToDelete)));
        ArrayList<Long> r0 = new ArrayList(this.pendingEncMessagesToDelete);
        getMessagesStorage().markMessagesAsDeletedByRandoms(r0);
        this.pendingEncMessagesToDelete.clear();
        return;
    }

    public void processUpdateEncryption(TL_update.TL_updateEncryption r9, ConcurrentHashMap<Long, TLRPC.User> r10) {
        TLRPC.EncryptedChat r0 = r9.chat;
        long r1 = DialogObject.makeEncryptedDialogId(r0.id);
        TLRPC.EncryptedChat r3 = getMessagesController().getEncryptedChatDB(r0.id, false);
        if ((r0 instanceof TLRPC.TL_encryptedChatRequested) == false) goto L13;
        if (r3 != null) goto L13;
        long r32 = r0.participant_id;
        if (r32 != getUserConfig().getClientUserId()) goto L8;
        r32 = r0.admin_id;
    L8:
        TLRPC.User r6 = getMessagesController().getUser(Long.valueOf(r32));
        if (r6 != null) goto L11;
        r6 = r10.get(Long.valueOf(r32));
    L11:
        r0.user_id = r32;
        TLRPC.TL_dialog r102 = new TLRPC.TL_dialog();
        r102.id = r1;
        r102.folder_id = r0.folder_id;
        r102.unread_count = 0;
        r102.top_message = 0;
        r102.last_message_date = r9.date;
        getMessagesController().putEncryptedChat(r0, false);
        AndroidUtilities.runOnUIThread(new SecretChatHelper$$ExternalSyntheticLambda1(this, r102, r1));
        getMessagesStorage().putEncryptedChat(r0, r6, r102);
        acceptSecretChat(r0);
    L29:
        if ((r0 instanceof TLRPC.TL_encryptedChatDiscarded) == true) goto L31;
        return;
    L31:
        if (r0.history_deleted == false) goto L35;
        AndroidUtilities.runOnUIThread(new SecretChatHelper$$ExternalSyntheticLambda3(this, r1));
        return;
    L35:
        return;
    L13:
        if ((r0 instanceof TLRPC.TL_encryptedChat) == true) goto L15;
        if (r3 == null) goto L27;
        r0.user_id = r3.user_id;
        r0.auth_key = r3.auth_key;
        r0.key_create_date = r3.key_create_date;
        r0.key_use_count_in = r3.key_use_count_in;
        r0.key_use_count_out = r3.key_use_count_out;
        r0.ttl = r3.ttl;
        r0.seq_in = r3.seq_in;
        r0.seq_out = r3.seq_out;
        r0.admin_id = r3.admin_id;
        r0.mtproto_seq = r3.mtproto_seq;
    L27:
        AndroidUtilities.runOnUIThread(new SecretChatHelper$$ExternalSyntheticLambda2(this, r3, r0));
        goto L29
    L15:
        if ((r3 instanceof TLRPC.TL_encryptedChatWaiting) == false) goto L21;
        byte[] r103 = r3.auth_key;
        if (r103 != null) goto L19;
    L20:
        r0.a_or_b = r3.a_or_b;
        r0.user_id = r3.user_id;
        processAcceptedSecretChat(r0);
        goto L29
    L19:
        if (r103.length == 1) goto L20;
    L21:
        if (r3 != null) goto L29;
        if (this.startingSecretChat == false) goto L29;
        this.delayedEncryptedChatUpdates.add(r9);
        goto L29
    }

    public void requestNewSecretChatKey(TLRPC.EncryptedChat r8) {
        byte[] r1 = new byte[256];
        Utilities.random.nextBytes(r1);
        byte[] r2 = BigInteger.valueOf(getMessagesStorage().getSecretG()).modPow(new BigInteger(1, r1), new BigInteger(1, getMessagesStorage().getSecretPBytes())).toByteArray();
        if (r2.length <= 256) goto L5;
        byte[] r3 = new byte[256];
        System.arraycopy(r2, 1, r3, 0, 256);
        r2 = r3;
    L5:
        r8.exchange_id = getSendMessagesHelper().getNextRandomId();
        r8.a_or_b = r1;
        r8.g_a = r2;
        getMessagesStorage().updateEncryptedChat(r8);
        sendRequestKeyMessage(r8, null);
    }

    public void sendAbortKeyMessage(TLRPC.EncryptedChat r9, TLRPC.Message r10, long r11) {
        if ((r9 instanceof TLRPC.TL_encryptedChat) == true) goto L5;
        return;
    L5:
        TLRPC.TL_decryptedMessageService r2 = new TLRPC.TL_decryptedMessageService();
        if (r10 == null) goto L9;
        r2.action = r10.action.encryptedAction;
    L8:
        TLRPC.Message r3 = r10;
        r2.random_id = r3.random_id;
        performSendEncryptedRequest(r2, r3, r9, null, null, null);
        return;
    L9:
        TLRPC.TL_decryptedMessageActionAbortKey r102 = new TLRPC.TL_decryptedMessageActionAbortKey();
        r2.action = r102;
        r102.exchange_id = r11;
        r10 = createServiceSecretMessage(r9, r102);
        goto L8
    }

    public void sendAcceptKeyMessage(TLRPC.EncryptedChat r9, TLRPC.Message r10) {
        if ((r9 instanceof TLRPC.TL_encryptedChat) == true) goto L5;
        return;
    L5:
        TLRPC.TL_decryptedMessageService r2 = new TLRPC.TL_decryptedMessageService();
        if (r10 == null) goto L9;
        r2.action = r10.action.encryptedAction;
    L8:
        TLRPC.Message r3 = r10;
        r2.random_id = r3.random_id;
        performSendEncryptedRequest(r2, r3, r9, null, null, null);
        return;
    L9:
        TLRPC.TL_decryptedMessageActionAcceptKey r102 = new TLRPC.TL_decryptedMessageActionAcceptKey();
        r2.action = r102;
        r102.exchange_id = r9.exchange_id;
        r102.key_fingerprint = r9.future_key_fingerprint;
        r102.g_b = r9.g_a_or_b;
        r10 = createServiceSecretMessage(r9, r102);
        goto L8
    }

    public void sendClearHistoryMessage(TLRPC.EncryptedChat r9, TLRPC.Message r10) {
        if ((r9 instanceof TLRPC.TL_encryptedChat) == true) goto L5;
        return;
    L5:
        TLRPC.TL_decryptedMessageService r2 = new TLRPC.TL_decryptedMessageService();
        if (r10 == null) goto L9;
        r2.action = r10.action.encryptedAction;
    L8:
        TLRPC.Message r3 = r10;
        r2.random_id = r3.random_id;
        performSendEncryptedRequest(r2, r3, r9, null, null, null);
        return;
    L9:
        TLRPC.TL_decryptedMessageActionFlushHistory r102 = new TLRPC.TL_decryptedMessageActionFlushHistory();
        r2.action = r102;
        r10 = createServiceSecretMessage(r9, r102);
        goto L8
    }

    public void sendCommitKeyMessage(TLRPC.EncryptedChat r9, TLRPC.Message r10) {
        if ((r9 instanceof TLRPC.TL_encryptedChat) == true) goto L5;
        return;
    L5:
        TLRPC.TL_decryptedMessageService r2 = new TLRPC.TL_decryptedMessageService();
        if (r10 == null) goto L9;
        r2.action = r10.action.encryptedAction;
    L8:
        TLRPC.Message r3 = r10;
        r2.random_id = r3.random_id;
        performSendEncryptedRequest(r2, r3, r9, null, null, null);
        return;
    L9:
        TLRPC.TL_decryptedMessageActionCommitKey r102 = new TLRPC.TL_decryptedMessageActionCommitKey();
        r2.action = r102;
        r102.exchange_id = r9.exchange_id;
        r102.key_fingerprint = r9.future_key_fingerprint;
        r10 = createServiceSecretMessage(r9, r102);
        goto L8
    }

    public void sendMessagesDeleteMessage(TLRPC.EncryptedChat r9, ArrayList<Long> r10, TLRPC.Message r11) {
        if ((r9 instanceof TLRPC.TL_encryptedChat) == true) goto L5;
        return;
    L5:
        TLRPC.TL_decryptedMessageService r2 = new TLRPC.TL_decryptedMessageService();
        if (r11 == null) goto L9;
        r2.action = r11.action.encryptedAction;
    L8:
        TLRPC.Message r3 = r11;
        r2.random_id = r3.random_id;
        performSendEncryptedRequest(r2, r3, r9, null, null, null);
        return;
    L9:
        TLRPC.TL_decryptedMessageActionDeleteMessages r112 = new TLRPC.TL_decryptedMessageActionDeleteMessages();
        r2.action = r112;
        r112.random_ids = r10;
        r11 = createServiceSecretMessage(r9, r112);
        goto L8
    }

    public void sendMessagesReadMessage(TLRPC.EncryptedChat r9, ArrayList<Long> r10, TLRPC.Message r11) {
        if ((r9 instanceof TLRPC.TL_encryptedChat) == true) goto L5;
        return;
    L5:
        TLRPC.TL_decryptedMessageService r2 = new TLRPC.TL_decryptedMessageService();
        if (r11 == null) goto L9;
        r2.action = r11.action.encryptedAction;
    L8:
        TLRPC.Message r3 = r11;
        r2.random_id = r3.random_id;
        performSendEncryptedRequest(r2, r3, r9, null, null, null);
        return;
    L9:
        TLRPC.TL_decryptedMessageActionReadMessages r112 = new TLRPC.TL_decryptedMessageActionReadMessages();
        r2.action = r112;
        r112.random_ids = r10;
        r11 = createServiceSecretMessage(r9, r112);
        goto L8
    }

    public void sendNoopMessage(TLRPC.EncryptedChat r9, TLRPC.Message r10) {
        if ((r9 instanceof TLRPC.TL_encryptedChat) == true) goto L5;
        return;
    L5:
        TLRPC.TL_decryptedMessageService r2 = new TLRPC.TL_decryptedMessageService();
        if (r10 == null) goto L9;
        r2.action = r10.action.encryptedAction;
    L8:
        TLRPC.Message r3 = r10;
        r2.random_id = r3.random_id;
        performSendEncryptedRequest(r2, r3, r9, null, null, null);
        return;
    L9:
        TLRPC.TL_decryptedMessageActionNoop r102 = new TLRPC.TL_decryptedMessageActionNoop();
        r2.action = r102;
        r10 = createServiceSecretMessage(r9, r102);
        goto L8
    }

    public void sendNotifyLayerMessage(TLRPC.EncryptedChat r10, TLRPC.Message r11) {
        if ((r10 instanceof TLRPC.TL_encryptedChat) == true) goto L6;
        return;
    L6:
        if (this.sendingNotifyLayer.contains(Integer.valueOf(r10.id)) == false) goto L8;
        return;
    L8:
        this.sendingNotifyLayer.add(Integer.valueOf(r10.id));
        TLRPC.TL_decryptedMessageService r3 = new TLRPC.TL_decryptedMessageService();
        if (r11 == null) goto L12;
        r3.action = r11.action.encryptedAction;
    L11:
        TLRPC.Message r4 = r11;
        r3.random_id = r4.random_id;
        performSendEncryptedRequest(r3, r4, r10, null, null, null);
        return;
    L12:
        TLRPC.TL_decryptedMessageActionNotifyLayer r112 = new TLRPC.TL_decryptedMessageActionNotifyLayer();
        r3.action = r112;
        r112.layer = CURRENT_SECRET_CHAT_LAYER;
        r11 = createServiceSecretMessage(r10, r112);
        goto L11
    }

    public void sendRequestKeyMessage(TLRPC.EncryptedChat r9, TLRPC.Message r10) {
        if ((r9 instanceof TLRPC.TL_encryptedChat) == true) goto L5;
        return;
    L5:
        TLRPC.TL_decryptedMessageService r2 = new TLRPC.TL_decryptedMessageService();
        if (r10 == null) goto L9;
        r2.action = r10.action.encryptedAction;
    L8:
        TLRPC.Message r3 = r10;
        r2.random_id = r3.random_id;
        performSendEncryptedRequest(r2, r3, r9, null, null, null);
        return;
    L9:
        TLRPC.TL_decryptedMessageActionRequestKey r102 = new TLRPC.TL_decryptedMessageActionRequestKey();
        r2.action = r102;
        r102.exchange_id = r9.exchange_id;
        r102.g_a = r9.g_a;
        r10 = createServiceSecretMessage(r9, r102);
        goto L8
    }

    public void sendResendMessage(TLRPC.EncryptedChat r9, int r10, int r11, TLRPC.Message r12) {
        if ((r9 instanceof TLRPC.TL_encryptedChat) == false) goto L19;
        SparseIntArray r0 = this.requestedHoles.get(r9.id);
        if (r0 != null) goto L8;
    L10:
        if (r0 != null) goto L12;
        r0 = new SparseIntArray();
        this.requestedHoles.put(r9.id, r0);
    L12:
        r0.put(r10, r11);
        TLRPC.TL_decryptedMessageService r2 = new TLRPC.TL_decryptedMessageService();
        if (r12 == null) goto L16;
        r2.action = r12.action.encryptedAction;
    L15:
        TLRPC.Message r3 = r12;
        r2.random_id = r3.random_id;
        performSendEncryptedRequest(r2, r3, r9, null, null, null);
        return;
    L16:
        TLRPC.TL_decryptedMessageActionResend r122 = new TLRPC.TL_decryptedMessageActionResend();
        r2.action = r122;
        r122.start_seq_no = r10;
        r122.end_seq_no = r11;
        r12 = createServiceSecretMessage(r9, r122);
        goto L15
    L8:
        if (r0.indexOfKey(r10) < 0) goto L10;
        return;
    }

    public void sendScreenshotMessage(TLRPC.EncryptedChat r9, ArrayList<Long> r10, TLRPC.Message r11) {
        if ((r9 instanceof TLRPC.TL_encryptedChat) == true) goto L5;
        return;
    L5:
        TLRPC.TL_decryptedMessageService r2 = new TLRPC.TL_decryptedMessageService();
        if (r11 == null) goto L9;
        r2.action = r11.action.encryptedAction;
    L8:
        TLRPC.Message r3 = r11;
        r2.random_id = r3.random_id;
        performSendEncryptedRequest(r2, r3, r9, null, null, null);
        return;
    L9:
        TLRPC.TL_decryptedMessageActionScreenshotMessages r112 = new TLRPC.TL_decryptedMessageActionScreenshotMessages();
        r2.action = r112;
        r112.random_ids = r10;
        r11 = createServiceSecretMessage(r9, r112);
        MessageObject r102 = new MessageObject(this.currentAccount, r11, false, false);
        r102.messageOwner.send_state = 1;
        r102.wasJustSent = true;
        ArrayList<MessageObject> r0 = new ArrayList();
        r0.add(r102);
        getMessagesController().updateInterfaceWithMessages(r11.dialog_id, r0, 0);
        getNotificationCenter().postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
        goto L8
    }

    public void sendTTLMessage(TLRPC.EncryptedChat r9, TLRPC.Message r10) {
        if ((r9 instanceof TLRPC.TL_encryptedChat) == true) goto L5;
        return;
    L5:
        TLRPC.TL_decryptedMessageService r2 = new TLRPC.TL_decryptedMessageService();
        if (r10 == null) goto L9;
        r2.action = r10.action.encryptedAction;
    L8:
        TLRPC.Message r3 = r10;
        r2.random_id = r3.random_id;
        performSendEncryptedRequest(r2, r3, r9, null, null, null);
        return;
    L9:
        TLRPC.TL_decryptedMessageActionSetMessageTTL r102 = new TLRPC.TL_decryptedMessageActionSetMessageTTL();
        r2.action = r102;
        r102.ttl_seconds = r9.ttl;
        r10 = createServiceSecretMessage(r9, r102);
        MessageObject r0 = new MessageObject(this.currentAccount, r10, false, false);
        r0.messageOwner.send_state = 1;
        r0.wasJustSent = true;
        ArrayList<MessageObject> r1 = new ArrayList();
        r1.add(r0);
        getMessagesController().updateInterfaceWithMessages(r10.dialog_id, r1, 0);
        getNotificationCenter().postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
        goto L8
    }

    public void startSecretChat(Context r5, TLRPC.User r6) {
        if (r6 == null) goto L15;
        if (r5 != null) goto L6;
        return;
    L6:
        if (getMessagesController().isFrozen() == false) goto L9;
        AccountFrozenAlert.show(this.currentAccount);
        return;
    L9:
        this.startingSecretChat = true;
        AlertDialog r0 = new AlertDialog(r5, 3);
        TLRPC.TL_messages_getDhConfig r1 = new TLRPC.TL_messages_getDhConfig();
        r1.random_length = 256;
        r1.version = getMessagesStorage().getLastSecretVersion();
        r0.setOnCancelListener(new SecretChatHelper$$ExternalSyntheticLambda10(this, getConnectionsManager().sendRequest(r1, new SecretChatHelper$$ExternalSyntheticLambda9(this, r5, r0, r6), 2)));
        r0.show();     // Catch: Exception -> L12
        return;
    L17:
        return;
    }
}
