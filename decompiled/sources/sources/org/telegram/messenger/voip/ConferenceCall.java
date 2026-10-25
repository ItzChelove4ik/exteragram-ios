package org.telegram.messenger.voip;

import android.text.TextUtils;
import android.util.LongSparseArray;
import androidx.annotation.Keep;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_phone;
import org.telegram.tgnet.tl.TL_update;

@Keep
/* loaded from: classes6.dex */
public class ConferenceCall {
    public static final int PERMISSION_ADD = 1;
    public static final int PERMISSION_REMOVE = 2;
    private final LongSparseArray<byte[]>[] blocksQueue;
    private long call_id;
    private int currentAccount;
    public boolean destroyed;
    public TLRPC.GroupCall groupCall;
    public TLRPC.InputGroupCall inputGroupCall;
    public boolean joined;
    public final HashSet<Long> joiningBlockchainParticipants;
    private HashSet<Long> lastParticipants;
    private String[] lastVerificationEmojis;
    private byte[] last_block;
    private final int[] last_offset;
    private long my_private_key_id;
    private byte[] my_public_key;
    private long my_public_key_id;
    private long my_user_id;
    private final int[] pollRequestId;
    private final Runnable pollRunnable;
    private boolean polling;
    private CallState state;
    private byte[] zero_block;

    @Keep
    public static final class CallParticipant {
        int permissions;
        long public_key_id;
        long user_id;

        public CallParticipant() {
        }

        public String toString() {
            return "CallParticipant{user_id=" + this.user_id + ", public_key_id=" + this.public_key_id + "}";
        }
    }

    @Keep
    public static final class CallState {
        int height;
        CallParticipant[] participants;

        public CallState() {
        }

        public CallParticipant find(long r5) {
            int r0 = 0;
        L3:
            CallParticipant[] r1 = this.participants;
            if (r0 >= r1.length) goto L9;
            CallParticipant r12 = r1[r0];
            if (r12.user_id == r5) goto L7;
            r0 = r0 + 1;
            goto L3
        L7:
            return r12;
        L9:
            return null;
        }

        public String toString() {
            StringBuilder r0 = new StringBuilder("CallState{height=" + this.height + ", participants=[");
            int r1 = 0;
        L4:
            if (r1 >= this.participants.length) goto L12;
            if (r1 <= 0) goto L7;
            r0.append(", ");
        L7:
            CallParticipant r2 = this.participants[r1];
            if (r2 != null) goto L10;
            r0.append("null");
        L11:
            r1 = r1 + 1;
            goto L4
        L10:
            r0.append(r2.toString());
            goto L11
        L12:
            r0.append("]}");
            return r0.toString();
        }
    }

    @Keep
    public static final class CallVerificationState {
        byte[] emoji_hash;
        int height;

        public CallVerificationState() {
        }

        public String toString() {
            StringBuilder r0 = new StringBuilder("CallVerificationState{height=");
            r0.append(this.height);
            r0.append(", emoji_hash=");
            if (this.emoji_hash != null) goto L5;
            String r4 = null;
        L6:
            r0.append(r4);
            r0.append("}");
            return r0.toString();
        L5:
            r4 = "{" + Utilities.bytesToHex(this.emoji_hash) + "}";
            goto L6
        }
    }

    @Keep
    public static final class CallVerificationWords {
        int height;
        String[] words;

        public CallVerificationWords() {
        }

        public String toString() {
            StringBuilder r0 = new StringBuilder("CallVerificationWords{height=" + this.height + ", words=[");
            int r1 = 0;
        L4:
            if (r1 >= this.words.length) goto L8;
            if (r1 <= 0) goto L7;
            r0.append(", ");
        L7:
            r0.append(this.words[r1]);
            r1 = r1 + 1;
            goto L4
        L8:
            r0.append("]}");
            return r0.toString();
        }
    }

    public static /* synthetic */ void $r8$lambda$0YziHsDs4nH3JaE4LSmoAwJMgQ8(ConferenceCall r0) {
        r0.poll();
    }

    public static /* synthetic */ void $r8$lambda$2sXCvmJRE96Bq3OCBu1Yz6cAvKo(ConferenceCall r0, long r1, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$updateParticipants$10(r1, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$3uPIItAP1uGVKyrNE8bkls1FhE8(ConferenceCall r0) {
        r0.lambda$checkEmojiHash$0();
    }

    public static /* synthetic */ void $r8$lambda$8GIeGLSxMmbhX51Xeiq_1VoOqG0(ConferenceCall r0, long r1, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$pull_outbound$6(r1, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$DsYIBbqwyFoeonADC4ntvSIUkvA(ConferenceCall r0) {
        r0.lambda$checkParticipants$1();
    }

    /* renamed from: $r8$lambda$E-enY1sEDILiNrHGqTgGa4aWlhA, reason: not valid java name */
    public static /* synthetic */ void m5269$r8$lambda$EenY1sEDILiNrHGqTgGa4aWlhA(ConferenceCall r0, long r1, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$updateParticipants$11(r1, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$GuKuTzOD0vBuormEitsjl3IVJmA(ConferenceCall r0, long r1, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$kick$13(r1, r3, r4);
    }

    /* renamed from: $r8$lambda$JG3-ekOsA1eClXwOxvusPIcG9E4, reason: not valid java name */
    public static /* synthetic */ void m5270$r8$lambda$JG3ekOsA1eClXwOxvusPIcG9E4(ConferenceCall r0, long r1, Runnable r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$requestLastBlock$3(r1, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$UuhO7Fu5r4VaZ_VQ1ku4mjeHljc(ConferenceCall r0, long r1, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$pull_outbound$5(r1, r3, r4);
    }

    /* renamed from: $r8$lambda$Ybw-SXuzM0AL1L1v7XRk9fRzl08, reason: not valid java name */
    public static /* synthetic */ void m5271$r8$lambda$YbwSXuzM0AL1L1v7XRk9fRzl08(ConferenceCall r0, long r1, TLObject r3, TLRPC.TL_error r4, Runnable r5) {
        r0.lambda$requestLastBlock$2(r1, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$bV5_ZVfFPqN2jdnB54hu8CBGofE(ConferenceCall r0, TLObject r1) {
        r0.lambda$processUpdates$4(r1);
    }

    /* renamed from: $r8$lambda$l2DLYo9iQuRY1Qymj89-k3Qq7zM, reason: not valid java name */
    public static /* synthetic */ String m5272$r8$lambda$l2DLYo9iQuRY1Qymj89k3Qq7zM(CallParticipant r3) {
        return "[" + r3.user_id + "]: " + DialogObject.getName(r3.user_id);
    }

    public static /* synthetic */ void $r8$lambda$r2aYHK4qZxpQzTZK1XjIg74a0FU(ConferenceCall r0, TL_phone.getGroupCallChainBlocks r1, long r2, AtomicBoolean r4, AtomicInteger r5, TLObject r6, TLRPC.TL_error r7) {
        r0.lambda$poll$8(r1, r2, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$v_k5jGpB96_rMGmGxJTqxojjQ00(ConferenceCall r0, TL_phone.getGroupCallChainBlocks r1, long r2, TLObject r4, TLRPC.TL_error r5, AtomicBoolean r6, AtomicInteger r7) {
        r0.lambda$poll$7(r1, r2, r4, r5, r6, r7);
    }

    /* renamed from: $r8$lambda$vdI_FB4oa2NvHgg-gn5V2Af0xPc, reason: not valid java name */
    public static /* synthetic */ void m5273$r8$lambda$vdI_FB4oa2NvHgggn5V2Af0xPc(ConferenceCall r0, long r1, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$kick$12(r1, r3, r4);
    }

    public ConferenceCall(int r3, long r4) {
        this.call_id = -1;
        this.joiningBlockchainParticipants = new HashSet();
        this.lastParticipants = null;
        this.last_offset = new int[]{-1, -1};
        this.blocksQueue = new LongSparseArray[]{new LongSparseArray(), new LongSparseArray()};
        this.pollRunnable = new ConferenceCall$$ExternalSyntheticLambda9(this);
        this.pollRequestId = new int[2];
        this.currentAccount = r3;
        this.my_user_id = r4;
        init();
    }

    private static String blockStr(byte[] r2) {
        return "Block{" + Utilities.bytesToHex(r2) + "}";
    }

    public static native CallState call_apply_block(long r0, byte[] r2);

    public static native long call_create(long r0, long r2, byte[] r4);

    public static native byte[] call_create_change_state_block(long r0, CallState r2);

    public static native byte[] call_create_self_add_block(long r0, byte[] r2, CallParticipant r3);

    public static native byte[] call_create_zero_block(long r0, CallState r2);

    public static native String call_describe(long r0);

    public static native String call_describe_block(byte[] r0);

    public static native String call_describe_message(byte[] r0);

    public static native void call_destroy(long r0);

    public static native void call_destroy_all();

    public static native int call_get_height(long r0);

    public static native CallState call_get_state(long r0);

    public static native CallVerificationState call_get_verification_state(long r0);

    public static native CallVerificationWords call_get_verification_words(long r0);

    public static native byte[][] call_pull_outbound_messages(long r0);

    public static native CallVerificationState call_receive_inbound_message(long r0, byte[] r2);

    private void checkEmojiHash() {
        String[] r0 = getVerificationEmojis();
        if (eq(r0, this.lastVerificationEmojis) == true) goto L6;
        this.lastVerificationEmojis = r0;
        AndroidUtilities.runOnUIThread(new ConferenceCall$$ExternalSyntheticLambda3(this));
        return;
    }

    private void checkParticipants() {
        long r0 = this.call_id;
        HashSet<Long> r3 = null;
        if (r0 < 0) goto L20;
        CallState r02 = call_get_state(r0);     // Catch: Exception -> L17
        if (r02 == null) goto L20;
        if (r02.participants.length <= 0) goto L20;
        HashSet<Long> r1 = new HashSet();     // Catch: Exception -> L17
        int r2 = 0;
    L39:
        CallParticipant[] r32 = r02.participants;     // Catch: Exception -> L14
        if (r2 >= r32.length) goto L16;
        r1.add(Long.valueOf(r32[r2].user_id));     // Catch: Exception -> L14
        r2 = r2 + 1;
        goto L39
    L16:
        r3 = r1;
    L14:
        e = e;
        r3 = r1;
    L18:
        FileLog.e(e);
    L17:
        e = e;
    L20:
        if (eq(r3, this.lastParticipants) == false) goto L22;
        return;
    L22:
        if (this.lastParticipants == null) goto L36;
        if (r3 == null) goto L36;
        Iterator<Long> r03 = r3.iterator();
    L26:
        if (r03.hasNext() == false) goto L30;
        Long r12 = r03.next();
        r12.longValue();
        if (this.lastParticipants.contains(r12) == true) goto L26;
        this.joiningBlockchainParticipants.add(r12);
        goto L26
    L30:
        Iterator<Long> r04 = this.joiningBlockchainParticipants.iterator();
    L32:
        if (r04.hasNext() == false) goto L37;
        Long r13 = r04.next();
        r13.longValue();
        if (this.lastParticipants.contains(r13) == false) goto L32;
        r04.remove();
    L37:
        this.lastParticipants = r3;
        AndroidUtilities.runOnUIThread(new ConferenceCall$$ExternalSyntheticLambda12(this));
        return;
    L36:
        this.joiningBlockchainParticipants.clear();
        goto L37
    }

    private boolean eq(HashSet<Long> r4, HashSet<Long> r5) {
        if (r4 != r5) goto L5;
        return true;
    L5:
        if (r4 != null) goto L9;
        if (r5 != null) goto L9;
        return true;
    L9:
        if (r4 == null) goto L22;
        if (r5 == null) goto L22;
        if (r4.size() == r5.size()) goto L15;
        return false;
    L15:
        Iterator<Long> r42 = r4.iterator();
    L17:
        if (r42.hasNext() == false) goto L21;
        Long r1 = r42.next();
        r1.longValue();
        if (r5.contains(r1) == true) goto L17;
        return false;
    L21:
        return true;
    L22:
        return false;
    }

    private boolean eq(String[] r5, String[] r6) {
        if (r5 != r6) goto L5;
        return true;
    L5:
        if (r5 != null) goto L9;
        if (r6 != null) goto L9;
        return true;
    L9:
        if (r5 == null) goto L23;
        if (r6 == null) goto L23;
        if (r5.length == r6.length) goto L15;
        return false;
    L15:
        int r1 = 0;
    L17:
        if (r1 >= r5.length) goto L22;
        if (TextUtils.equals(r5[r1], r6[r1]) == false) goto L20;
        r1 = r1 + 1;
        goto L17
    L20:
        return false;
    L22:
        return true;
    L23:
        return false;
    }

    private long getPollTimeout() {
        if (getVerificationEmojis() != null) goto L6;
        return 1000;
    L6:
        return 5000;
    }

    private String[] getVerificationEmojis() {
        long r0 = this.call_id;
        if (r0 >= 0) goto L16;
        return null;
    L16:
        byte[] r4 = call_get_verification_state(r0).emoji_hash;     // Catch: Exception -> L7
    L9:
        if (r4 != null) goto L12;
        return null;
    L12:
        if (r4.length <= 32) goto L15;
        byte[] r02 = new byte[32];
        System.arraycopy(r4, 0, r02, 0, 32);
        r4 = r02;
    L15:
        return EncryptionKeyEmojifier.emojifyForCall(r4);
    L7:
        e = move-exception;
        FileLog.e(e);
        r4 = null;
        goto L9
    }

    private void init() {
        long r0 = key_generate_temporary_private_key();
        this.my_private_key_id = r0;
        byte[] r02 = key_to_public_key(r0);
        this.my_public_key = r02;
        this.my_public_key_id = key_from_public_key(r02);
        CallState r03 = new CallState();
        this.state = r03;
        r03.height = 1;
        r03.participants = new CallParticipant[]{new CallParticipant()};
        CallParticipant r04 = this.state.participants[0];
        r04.user_id = this.my_user_id;
        r04.public_key_id = this.my_public_key_id;
        r04.permissions = 3;
    }

    public static native long key_from_public_key(byte[] r0);

    public static native long key_generate_temporary_private_key();

    public static native byte[] key_to_public_key(long r0);

    private /* synthetic */ void lambda$checkEmojiHash$0() {
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.conferenceEmojiUpdated, new Object[0]);
    }

    private /* synthetic */ void lambda$checkParticipants$1() {
        if (this.groupCall == null) goto L19;
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 == null) goto L16;
        ChatObject.Call r1 = r0.groupCall;
        if (r1 == null) goto L17;
        TLRPC.GroupCall r2 = r1.call;
        if (r2 != null) goto L12;
        return;
    L12:
        if (r2.id != this.groupCall.id) goto L20;
        updateParticipants(r1.sortedParticipants, false);
        r0.groupCall.shadyLeftParticipants.clear();
        ChatObject.Call r12 = r0.groupCall;
        r12.shadyLeftParticipants.addAll(r0.conference.getShadyLeftParticipants(r12.sortedParticipants));
        r0.groupCall.shadyJoinParticipants.clear();
        ChatObject.Call r13 = r0.groupCall;
        r13.shadyJoinParticipants.addAll(r0.conference.getShadyJoiningParticipants(r13.sortedParticipants));
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.groupCallUpdated, new Object[]{0L, Long.valueOf(this.groupCall.id), Boolean.FALSE});
        return;
    L20:
        return;
    L17:
        return;
    L16:
        return;
    }

    private /* synthetic */ void lambda$kick$12(long r2, TLObject r4, TLRPC.TL_error r5) {
        processUpdates(null, Long.valueOf(r2), r4, r5);
    }

    private /* synthetic */ void lambda$kick$13(long r7, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new ConferenceCall$$ExternalSyntheticLambda11(this, r7, r9, r10));
    }

    private /* synthetic */ void lambda$poll$7(TL_phone.getGroupCallChainBlocks r1, long r2, TLObject r4, TLRPC.TL_error r5, AtomicBoolean r6, AtomicInteger r7) {
        if (processUpdates(Integer.valueOf(r1.offset), Long.valueOf(r2), r4, r5) == false) goto L6;
        r6.set(true);
    L6:
        if (r7.incrementAndGet() != 2) goto L13;
        this.polling = false;
        if (r6.get() == false) goto L11;
        forcePoll();
        return;
    L11:
        AndroidUtilities.cancelRunOnUIThread(this.pollRunnable);
        AndroidUtilities.runOnUIThread(this.pollRunnable, getPollTimeout());
        return;
    }

    private /* synthetic */ void lambda$poll$8(TL_phone.getGroupCallChainBlocks r10, long r11, AtomicBoolean r13, AtomicInteger r14, TLObject r15, TLRPC.TL_error r16) {
        AndroidUtilities.runOnUIThread(new ConferenceCall$$ExternalSyntheticLambda4(this, r10, r11, r15, r16, r13, r14));
    }

    private /* synthetic */ void lambda$processUpdates$4(TLObject r2) {
        MessagesController.getInstance(this.currentAccount).processUpdates((TLRPC.Updates) r2, false);
    }

    private /* synthetic */ void lambda$pull_outbound$5(long r2, TLObject r4, TLRPC.TL_error r5) {
        processUpdates(null, Long.valueOf(r2), r4, r5);
    }

    private /* synthetic */ void lambda$pull_outbound$6(long r7, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new ConferenceCall$$ExternalSyntheticLambda0(this, r7, r9, r10));
    }

    private /* synthetic */ void lambda$requestLastBlock$2(long r2, TLObject r4, TLRPC.TL_error r5, Runnable r6) {
        processUpdates(-1, Long.valueOf(r2), r4, r5);
        if (r6 == null) goto L6;
        r6.run();
        return;
    }

    private /* synthetic */ void lambda$requestLastBlock$3(long r8, Runnable r10, TLObject r11, TLRPC.TL_error r12) {
        AndroidUtilities.runOnUIThread(new ConferenceCall$$ExternalSyntheticLambda5(this, r8, r11, r12, r10));
    }

    private /* synthetic */ void lambda$updateParticipants$10(long r2, TLObject r4, TLRPC.TL_error r5) {
        processUpdates(null, Long.valueOf(r2), r4, r5);
    }

    private /* synthetic */ void lambda$updateParticipants$11(long r7, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new ConferenceCall$$ExternalSyntheticLambda7(this, r7, r9, r10));
    }

    private void poll() {
        if (this.destroyed == false) goto L7;
        FileLog.d("[tde2e] conference.poll but destroyed!");
        return;
    L7:
        if (this.joined == true) goto L10;
        FileLog.d("[tde2e] conference.poll but not joined!");
        return;
    L10:
        this.polling = true;
        AtomicInteger r7 = new AtomicInteger(0);
        AtomicBoolean r6 = new AtomicBoolean(false);
        int r8 = 0;
    L12:
        if (r8 >= 2) goto L14;
        TL_phone.getGroupCallChainBlocks r3 = new TL_phone.getGroupCallChainBlocks();
        r3.call = this.inputGroupCall;
        r3.sub_chain_id = r8;
        r3.offset = Math.max(0, this.last_offset[r8]);
        r3.limit = 10;
        FileLog.d("[tde2e] requesting getGroupCallChainBlocks sub_chain_id=" + r3.sub_chain_id + " offset=" + r3.offset + " limit=10");
        ConferenceCall r2 = this;
        this.pollRequestId[r8] = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r3, new ConferenceCall$$ExternalSyntheticLambda13(r2, r3, System.currentTimeMillis(), r6, r7));
        r8 = r8 + 1;
        ConferenceCall this = r2;
        goto L12
    L14:
        ConferenceCall r22 = this;
        if (r22.call_id >= 0) goto L28;
    L24:
        r22.checkEmojiHash();
        r22.checkParticipants();
        return;
    L28:
        FileLog.d("[tde2e] state = " + call_get_verification_state(r22.call_id));     // Catch: Exception -> L18
    L26:
        FileLog.d("[tde2e] call_describe(" + r22.call_id + "): " + call_describe(r22.call_id));     // Catch: Exception -> L22
        StringBuilder r11 = new StringBuilder();     // Catch: Exception -> L22
        r11.append("[tde2e] call users:\n ");     // Catch: Exception -> L22
        r11.append(TextUtils.join("\n ", (Iterable) Arrays.stream(call_get_state(r22.call_id).participants).map(new ConferenceCall$$ExternalSyntheticLambda14()).collect(Collectors.toSet())));     // Catch: Exception -> L22
        FileLog.d(r11.toString());     // Catch: Exception -> L22
    L22:
        e = move-exception;
        FileLog.e(e);
    L18:
        e = move-exception;
        FileLog.e(e);
        goto L26
    }

    private boolean processUpdates(Integer r6, Long r7, TLObject r8, TLRPC.TL_error r9) {
        if ((r8 instanceof TLRPC.Updates) == false) goto L11;
        ArrayList r92 = MessagesController.findUpdatesAndRemove((TLRPC.Updates) r8, TL_update.TL_updateGroupCallChainBlocks.class);
        int r1 = r92.size();
        boolean r2 = false;
        int r3 = 0;
    L5:
        if (r3 >= r1) goto L9;
        Object r4 = r92.get(r3);
        r3 = r3 + 1;
        if (applyUpdate(r6, (TL_update.TL_updateGroupCallChainBlocks) r4, false, r7) == false) goto L5;
        r2 = true;
        goto L5
    L9:
        Utilities.stageQueue.postRunnable(new ConferenceCall$$ExternalSyntheticLambda10(this, r8));
        return r2;
    L11:
        return false;
    }

    private void pull_outbound() {
        if (this.destroyed == false) goto L6;
        FileLog.d("[tde2e] conference.pull_outbound but destroyed!");
        return;
    L6:
        long r1 = this.call_id;
        if (r1 < 0) goto L41;
        int r3 = 0;
        byte[][] r12 = call_pull_outbound_messages(r1);     // Catch: Exception -> L18
        FileLog.d("[tde2e] call_pull_outbound_messages(" + this.call_id + ") = " + r12.length + " blocks");     // Catch: Exception -> L18
        int r2 = 0;
    L38:
        if (r3 >= r12.length) goto L34;
        TL_phone.sendConferenceCallBroadcast r4 = new TL_phone.sendConferenceCallBroadcast();     // Catch: Exception -> L16
        r4.call = this.inputGroupCall;     // Catch: Exception -> L16
        r4.block = r12[r3];     // Catch: Exception -> L16
        FileLog.d("[tde2e] pull outbound block to server!");     // Catch: Exception -> L16
        FileLog.d("[tde2e] call_pull_outbound_messages(" + this.call_id + ")[" + r3 + "] = " + call_describe_message(r12[r3]));     // Catch: Exception -> L16
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r4, new ConferenceCall$$ExternalSyntheticLambda2(this, System.currentTimeMillis()), 64);     // Catch: Exception -> L16
        r3 = r3 + 1;
        r2 = 1;
    L34:
        FileLog.d("[tde2e] state = " + call_get_verification_state(this.call_id));     // Catch: Exception -> L22
    L32:
        FileLog.d("[tde2e] call_describe(" + this.call_id + "): " + call_describe(this.call_id));     // Catch: Exception -> L26
    L28:
        checkEmojiHash();
        checkParticipants();
        if (r2 == 0) goto L40;
        forcePoll();
        return;
    L40:
        return;
    L26:
        e = move-exception;
        FileLog.e(e);
    L22:
        e = move-exception;
        FileLog.e(e);
    L16:
        e = e;
        r3 = r2;
    L19:
        FileLog.e(e);
        r2 = r3;
    L18:
        e = e;
        goto L19
    }

    private void readQueue(int r13) {
        if (this.destroyed == false) goto L7;
        FileLog.d("[tde2e] conference.readQueue(" + r13 + ") but destroyed!");
        return;
    L7:
        if (r13 != 0) goto L9;
    L12:
        int r0 = this.last_offset[r13];
        if (r0 != (-1)) goto L16;
        FileLog.d("[tde2e] conference.readQueue(" + r13 + ") but last_offset == -1!");
        return;
    L16:
        int r02 = Math.max(0, r0);
        FileLog.d("[tde2e] {subchain: " + r13 + "} processing blocks queue from " + r02);
    L17:
        long r5 = r02;
        byte[] r1 = this.blocksQueue[r13].get(r5);
        if (r1 == null) goto L19;
        FileLog.d("[tde2e] {subchain: " + r13 + "} processing #" + r02 + " block from queue");     // Catch: Exception -> L26
        this.blocksQueue[r13].remove(r5);     // Catch: Exception -> L26
        long r52 = this.call_id;     // Catch: Exception -> L26
        if (r52 >= 0) goto L29;
        FileLog.d("[tde2e] #" + r02 + " call_create block=" + call_describe_block(r1));     // Catch: Exception -> L26
        long r53 = this.my_user_id;     // Catch: Exception -> L26
        long r7 = this.my_private_key_id;     // Catch: Exception -> L26
        this.last_block = r1;     // Catch: Exception -> L26
        long r54 = call_create(r53, r7, r1);     // Catch: Exception -> L26
        this.call_id = r54;     // Catch: Exception -> L26
        gotCallId(r54);     // Catch: Exception -> L26
    L37:
        r02 = r02 + 1;     // Catch: Exception -> L26
        this.last_offset[r13] = r02;     // Catch: Exception -> L26
        goto L17
    L29:
        if (r13 != 0) goto L35;
        if (r02 <= call_get_height(r52)) goto L33;
        FileLog.d("[tde2e] #" + r02 + " call_apply_block block=" + call_describe_block(r1));     // Catch: Exception -> L26
        FileLog.d("[tde2e] #" + r02 + " call_apply_block(" + this.call_id + ", " + blockStr(r1) + ") = " + call_apply_block(this.call_id, r1));     // Catch: Exception -> L26
        goto L37
    L33:
        FileLog.d("[tde2e] #" + r02 + " block from queue is under call's height!");     // Catch: Exception -> L26
        goto L37
    L35:
        if (r13 != 1) goto L37;
        FileLog.d("[tde2e] #" + r02 + " call_receive_inbound_message message=" + call_describe_message(r1));     // Catch: Exception -> L26
        FileLog.d("[tde2e] #" + r02 + " call_receive_inbound_message(" + this.call_id + ", " + blockStr(r1) + ") = " + call_receive_inbound_message(this.call_id, r1));     // Catch: Exception -> L26
    L26:
        e = move-exception;
        FileLog.e("[tde2e] {subchain: " + r13 + "} #" + r02 + " block got into error: ", e);
        return;
    L19:
        FileLog.d("[tde2e] {subchain: " + r13 + "} got into hole (might be the end) in " + r13 + " subchain at #" + r02 + ", when our last_offset[" + r13 + "] = " + this.last_offset[r13]);
        this.last_offset[r13] = r02;
        return;
    L9:
        if (this.call_id >= 0) goto L12;
        FileLog.d("[tde2e] conference.readQueue(" + r13 + ") but there is no call yet!");
    }

    public boolean applyUpdate(Integer r9, TL_update.TL_updateGroupCallChainBlocks r10, boolean r11, Long r12) {
        if (this.destroyed == false) goto L6;
        FileLog.d("[tde2e] conference.applyUpdate but destroyed!");
        return false;
    L6:
        if (r10 != null) goto L8;
        return false;
    L8:
        TLRPC.GroupCall r0 = this.groupCall;
        if (r0 != null) goto L13;
        FileLog.d("[tde2e] received updateGroupCallChainBlocks but we dont have groupcall yet!");
        return false;
    L13:
        if (r10.call.id == r0.id) goto L16;
        FileLog.d("[tde2e] received updateGroupCallChainBlocks for " + r10.call.id + " but we have " + this.groupCall.id);
        return false;
    L16:
        StringBuilder r02 = new StringBuilder("[tde2e] received update with ");
        r02.append(r10.blocks.size());
        r02.append(" blocks for ");
        r02.append(r10.sub_chain_id);
        r02.append(" subchain, next_offset=");
        r02.append(r10.next_offset);
        r02.append(" requested_offset=");
        r02.append(r9);
        if (r12 == null) goto L19;
        String r122 = " in " + (System.currentTimeMillis() - r12.longValue()) + "ms";
    L20:
        r02.append(r122);
        FileLog.d(r02.toString());
        int r123 = r10.sub_chain_id;
        int r03 = r10.next_offset;
        if (r123 == 0) goto L23;
        if (r123 == 1) goto L23;
    L62:
        if (r123 != 1) goto L64;
        pull_outbound();
    L64:
        checkEmojiHash();
        checkParticipants();
        if (r11 == false) goto L70;
        if (r10.blocks.size() <= 0) goto L70;
        forcePoll();
    L70:
        if (r10.blocks.size() <= 0) goto L72;
        return true;
    L72:
        return false;
    L23:
        int r3 = 0;
    L25:
        if (r3 >= r10.blocks.size()) goto L38;
        byte[] r4 = r10.blocks.get(r3);
        int r6 = (r03 - r10.blocks.size()) + r3;
        if (r9 == null) goto L33;
        if (r9.intValue() != (-1)) goto L33;
        if (r123 != 0) goto L36;
        this.last_block = r4;
    L36:
        r3 = r3 + 1;
    L33:
        if (r6 < this.last_offset[r123]) goto L35;
        FileLog.d("[tde2e] {subchain: " + r123 + "} put #" + r6 + " into queue");
        this.blocksQueue[r123].put((long) r6, r4);
        goto L36
    L35:
        FileLog.d("[tde2e] {subchain: " + r123 + "} received #" + r6 + " that was already processed from queue");
        goto L36
    L38:
        if (this.last_offset[r123] != (-1)) goto L49;
        if (r9 != null) goto L41;
    L43:
        if (r9 != null) goto L45;
    L47:
        FileLog.e("[tde2e] received update where we can't know what the start offset is of " + r123 + " sub chain (we requested " + r9 + ")");
        goto L49
    L45:
        if (r9.intValue() != (-1)) goto L47;
        FileLog.d("[tde2e] no offset, but we were asking for last block anyway");
        goto L49
    L41:
        if (r9.intValue() != 0) goto L43;
        this.last_offset[r123] = r03 - r10.blocks.size();
    L49:
        if (this.last_offset[r123] == (-1)) goto L62;
        if (this.call_id < 0) goto L53;
        boolean r92 = true;
    L54:
        readQueue(r123);
        if (this.call_id < 0) goto L57;
        boolean r04 = true;
    L58:
        if (r123 != 0) goto L62;
        if (r92 == true) goto L62;
        if (r04 == false) goto L62;
        readQueue(1);
        goto L62
    L57:
        r04 = false;
        goto L58
    L53:
        r92 = false;
        goto L54
    L19:
        r122 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L20
    }

    public void destroy() {
        this.destroyed = true;
        reset();
    }

    public void forcePoll() {
        if (this.destroyed == false) goto L7;
        FileLog.d("[tde2e] conference.forcePoll but destroyed!");
        return;
    L7:
        if (this.joined == true) goto L11;
        FileLog.d("[tde2e] conference.forcePoll but not joined!");
        return;
    L11:
        if (this.polling == false) goto L13;
        return;
    L13:
        AndroidUtilities.cancelRunOnUIThread(this.pollRunnable);
        AndroidUtilities.runOnUIThread(this.pollRunnable);
    }

    public byte[] generateAddSelfBlock() {
        CallParticipant r2 = new CallParticipant();
        r2.user_id = this.my_user_id;
        r2.public_key_id = this.my_public_key_id;
        r2.permissions = 3;
        byte[] r3 = this.last_block;
        if (r3 != null) goto L11;
        CallState r0 = new CallState();
        this.state = r0;
        r0.height = 1;
        r0.participants = new CallParticipant[]{r2};
        byte[] r02 = call_create_zero_block(this.my_private_key_id, r0);
        this.zero_block = r02;
        this.last_block = r02;
        FileLog.d("[tde2e] call_create_zero_block(" + this.my_private_key_id + ", " + this.state + ")");
    L10:
        return this.last_block;
    L11:
        byte[] r22 = call_create_self_add_block(this.my_private_key_id, r3, r2);     // Catch: Exception -> L7
        FileLog.d("[tde2e] call_create_self_add_block(" + this.my_private_key_id + ", " + blockStr(this.last_block) + ", " + this.state + ") = " + blockStr(r22));     // Catch: Exception -> L7
        StringBuilder r1 = new StringBuilder("[tde2e] call_create_self_add_block last_block=");     // Catch: Exception -> L7
        r1.append(call_describe_block(this.last_block));     // Catch: Exception -> L7
        r1.append(" new_block=");     // Catch: Exception -> L7
        r1.append(call_describe_block(r22));     // Catch: Exception -> L7
        FileLog.d(r1.toString());     // Catch: Exception -> L7
        this.last_block = r22;     // Catch: Exception -> L7
    L7:
        e = move-exception;
        FileLog.e(e);
        goto L10
    }

    public HashSet<Long> getBlockchainParticipants() {
        HashSet<Long> r0 = new HashSet();
        if (this.destroyed == false) goto L6;
        FileLog.d("[tde2e] conference.getBlockchainParticipants but destroyed!");
        return r0;
    L6:
        long r1 = this.call_id;
        if (r1 >= 0) goto L19;
    L18:
        return r0;
    L19:
        CallState r5 = call_get_state(r1);     // Catch: Exception -> L11
    L13:
        if (r5 == null) goto L18;
        int r12 = 0;
    L15:
        CallParticipant[] r2 = r5.participants;
        if (r12 >= r2.length) goto L18;
        r0.add(Long.valueOf(r2[r12].user_id));
        r12 = r12 + 1;
    L11:
        e = move-exception;
        FileLog.e(e);
        r5 = null;
        goto L13
    }

    public long getCallId() {
        return this.call_id;
    }

    public String[] getEmojis() {
        return this.lastVerificationEmojis;
    }

    public byte[] getLastBlock() {
        return this.last_block;
    }

    public byte[] getMyPublicKey() {
        return this.my_public_key;
    }

    public HashSet<Long> getShadyJoiningParticipants(ArrayList<TLRPC.GroupCallParticipant> r11) {
        HashSet<Long> r0 = new HashSet();
        if (this.destroyed == false) goto L6;
        FileLog.d("[tde2e] conference.getShadyJoiningParticipants but destroyed!");
        return r0;
    L6:
        long r1 = this.call_id;
        if (r1 >= 0) goto L36;
    L35:
        return r0;
    L36:
        CallState r12 = call_get_state(r1);     // Catch: Exception -> L12
    L14:
        if (r12 == null) goto L35;
        int r4 = 0;
    L16:
        CallParticipant[] r5 = r12.participants;
        if (r4 >= r5.length) goto L35;
        long r52 = r5[r4].user_id;
        int r7 = 0;
    L19:
        if (r11 != null) goto L21;
        int r8 = 0;
    L22:
        if (r7 >= r8) goto L27;
        if (r52 == DialogObject.getPeerDialogId(r11.get(r7).peer)) goto L25;
        r7 = r7 + 1;
        goto L19
    L25:
        TLRPC.GroupCallParticipant r72 = r11.get(r7);
    L28:
        if (r72 != null) goto L34;
        if (r52 == this.my_user_id) goto L34;
        if (this.joiningBlockchainParticipants.contains(Long.valueOf(r52)) == false) goto L34;
        r0.add(Long.valueOf(r52));
    L34:
        r4 = r4 + 1;
        goto L16
    L27:
        r72 = null;
        goto L28
    L21:
        r8 = r11.size();
    L12:
        e = move-exception;
        FileLog.e(e);
        r12 = null;
        goto L14
    }

    public HashSet<Long> getShadyLeftParticipants(ArrayList<TLRPC.GroupCallParticipant> r11) {
        HashSet<Long> r0 = new HashSet();
        if (this.destroyed == false) goto L6;
        FileLog.d("[tde2e] conference.getShadyLeftParticipants but destroyed!");
        return r0;
    L6:
        long r1 = this.call_id;
        if (r1 >= 0) goto L36;
    L35:
        return r0;
    L36:
        CallState r12 = call_get_state(r1);     // Catch: Exception -> L12
    L14:
        if (r12 == null) goto L35;
        int r4 = 0;
    L16:
        CallParticipant[] r5 = r12.participants;
        if (r4 >= r5.length) goto L35;
        long r52 = r5[r4].user_id;
        int r7 = 0;
    L19:
        if (r11 != null) goto L21;
        int r8 = 0;
    L22:
        if (r7 >= r8) goto L27;
        if (r52 == DialogObject.getPeerDialogId(r11.get(r7).peer)) goto L25;
        r7 = r7 + 1;
        goto L19
    L25:
        TLRPC.GroupCallParticipant r72 = r11.get(r7);
    L28:
        if (r72 != null) goto L34;
        if (r52 == this.my_user_id) goto L34;
        if (this.joiningBlockchainParticipants.contains(Long.valueOf(r52)) == true) goto L34;
        r0.add(Long.valueOf(r52));
    L34:
        r4 = r4 + 1;
        goto L16
    L27:
        r72 = null;
        goto L28
    L21:
        r8 = r11.size();
    L12:
        e = move-exception;
        FileLog.e(e);
        r12 = null;
        goto L14
    }

    public CallVerificationState getVerificationState() {
        return call_get_verification_state(this.call_id);
    }

    public CallVerificationWords getVerificationWords() {
        return call_get_verification_words(this.call_id);
    }

    public void gotCallId(long r1) {
    }

    public void joined() {
        this.joined = true;
    }

    public void kick(long r11) {
        if (this.destroyed == false) goto L7;
        FileLog.d("[tde2e] conference.kick but destroyed!");
        return;
    L7:
        if (this.call_id >= 0) goto L10;
        return;
    L10:
        if (getBlockchainParticipants().contains(Long.valueOf(r11)) == true) goto L12;
        return;
    L12:
        CallState r0 = call_get_state(this.call_id);
        CallState r1 = new CallState();
        r1.height = r0.height + 1;
        ArrayList r2 = new ArrayList();
        int r5 = 0;
    L13:
        CallParticipant[] r6 = r0.participants;
        if (r5 >= r6.length) goto L20;
        if (r11 == r6[r5].user_id) goto L19;
        CallParticipant r62 = new CallParticipant();
        CallParticipant r7 = r0.participants[r5];
        r62.user_id = r7.user_id;
        r62.public_key_id = r7.public_key_id;
        r62.permissions = r7.permissions;
        r2.add(r62);
    L19:
        r5 = r5 + 1;
        goto L13
    L20:
        r1.participants = (CallParticipant[]) r2.toArray(new CallParticipant[0]);
        FileLog.d("[tde2e] kick: call_create_change_state_block from " + this.state + " to " + r1);
        byte[] r02 = call_create_change_state_block(this.call_id, r1);
        StringBuilder r22 = new StringBuilder("[tde2e] kick: call_create_change_state_block returns ");
        r22.append(call_describe_block(r02));
        FileLog.d(r22.toString());
        this.state = r1;
        TL_phone.deleteConferenceCallParticipants r12 = new TL_phone.deleteConferenceCallParticipants();
        r12.kick = true;
        r12.call = this.inputGroupCall;
        r12.block = r02;
        r12.ids.add(Long.valueOf(r11));
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r12, new ConferenceCall$$ExternalSyntheticLambda8(this, System.currentTimeMillis()));
    }

    public void requestLastBlock(Runnable r6) {
        long r0 = System.currentTimeMillis();
        TL_phone.getGroupCallChainBlocks r2 = new TL_phone.getGroupCallChainBlocks();
        r2.call = this.inputGroupCall;
        r2.sub_chain_id = 0;
        r2.offset = -1;
        r2.limit = 1;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r2, new ConferenceCall$$ExternalSyntheticLambda6(this, r0, r6));
    }

    public void reset() {
        AndroidUtilities.cancelRunOnUIThread(this.pollRunnable);
        if (this.call_id == (-1)) goto L12;
        int r0 = 0;
    L6:
        if (r0 >= 2) goto L11;
        if (this.pollRequestId[r0] == 0) goto L10;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.pollRequestId[r0], true);
        this.pollRequestId[r0] = 0;
    L10:
        r0 = r0 + 1;
        goto L6
    L11:
        call_destroy(this.call_id);
        FileLog.d("[tde2e] call_destroy(" + this.call_id + ")");
        this.call_id = -1;
    L12:
        int[] r02 = this.last_offset;
        r02[0] = -1;
        r02[1] = -1;
        this.blocksQueue[0].clear();
        this.blocksQueue[1].clear();
        init();
    }

    public void updateParticipants(ArrayList<TLRPC.GroupCallParticipant> r11, boolean r12) {
        if (this.destroyed == false) goto L6;
        FileLog.d("[tde2e] conference.updateParticipants but destroyed!");
        return;
    L6:
        int r0 = r11.size();
        int r2 = 0;
    L7:
        if (r2 >= r0) goto L9;
        TLRPC.GroupCallParticipant r3 = r11.get(r2);
        r2 = r2 + 1;
        this.joiningBlockchainParticipants.remove(Long.valueOf(DialogObject.getPeerDialogId(r3.peer)));
        goto L7
    L9:
        HashSet<Long> r112 = getShadyLeftParticipants(r11);
        if (r112.isEmpty() == false) goto L27;
    L24:
        if (r12 == false) goto L33;
        forcePoll();
        return;
    L33:
        return;
    L27:
        CallState r02 = call_get_state(this.call_id);     // Catch: Exception -> L19
        CallState r22 = new CallState();     // Catch: Exception -> L19
        r22.height = r02.height + 1;     // Catch: Exception -> L19
        ArrayList r32 = new ArrayList();     // Catch: Exception -> L19
        int r5 = 0;
    L12:
        CallParticipant[] r6 = r02.participants;     // Catch: Exception -> L19
        if (r5 >= r6.length) goto L21;
        if (r112.contains(Long.valueOf(r6[r5].user_id)) == true) goto L18;
        CallParticipant r62 = new CallParticipant();     // Catch: Exception -> L19
        CallParticipant r7 = r02.participants[r5];     // Catch: Exception -> L19
        r62.user_id = r7.user_id;     // Catch: Exception -> L19
        r62.public_key_id = r7.public_key_id;     // Catch: Exception -> L19
        r62.permissions = r7.permissions;     // Catch: Exception -> L19
        r32.add(r62);     // Catch: Exception -> L19
    L18:
        r5 = r5 + 1;     // Catch: Exception -> L19
        goto L12
    L21:
        r22.participants = (CallParticipant[]) r32.toArray(new CallParticipant[0]);     // Catch: Exception -> L19
        FileLog.d("[tde2e] call_create_change_state_block from " + this.state + " to " + r22);     // Catch: Exception -> L19
        byte[] r03 = call_create_change_state_block(this.call_id, r22);     // Catch: Exception -> L19
        StringBuilder r1 = new StringBuilder();     // Catch: Exception -> L19
        r1.append("[tde2e] call_create_change_state_block returns ");     // Catch: Exception -> L19
        r1.append(call_describe_block(r03));     // Catch: Exception -> L19
        FileLog.d(r1.toString());     // Catch: Exception -> L19
        this.state = r22;     // Catch: Exception -> L19
        TL_phone.deleteConferenceCallParticipants r13 = new TL_phone.deleteConferenceCallParticipants();     // Catch: Exception -> L19
        r13.only_left = true;     // Catch: Exception -> L19
        r13.call = this.inputGroupCall;     // Catch: Exception -> L19
        r13.block = r03;     // Catch: Exception -> L19
        r13.ids.addAll(r112);     // Catch: Exception -> L19
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r13, new ConferenceCall$$ExternalSyntheticLambda1(this, System.currentTimeMillis()));     // Catch: Exception -> L19
    L19:
        e = move-exception;
        FileLog.e(e);
        goto L24
    }
}
