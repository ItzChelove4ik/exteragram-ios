package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.app.RemoteInput;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public class WearReplyReceiver extends BroadcastReceiver {
    public static /* synthetic */ void $r8$lambda$CPqFx5UX6eYXpQWWABdIpJ8CRp8(WearReplyReceiver r0, AccountInstance r1, long r2, CharSequence r4, long r5, int r7, int[] r8) {
        r0.lambda$onReceive$3(r1, r2, r4, r5, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$fBIZO0YQuSOmJ7Ku4kwGcjdrjM8(WearReplyReceiver r0, AccountInstance r1, TLRPC.User r2, CharSequence r3, long r4, long r6, int r8, int[] r9) {
        r0.lambda$onReceive$0(r1, r2, r3, r4, r6, r8, r9);
    }

    /* renamed from: $r8$lambda$gTS8Jg9XeY-9BlWs26lVNoYSQCo, reason: not valid java name */
    public static /* synthetic */ void m5053$r8$lambda$gTS8Jg9XeY9BlWs26lVNoYSQCo(WearReplyReceiver r0, AccountInstance r1, TLRPC.Chat r2, CharSequence r3, long r4, long r6, int r8, int[] r9) {
        r0.lambda$onReceive$2(r1, r2, r3, r4, r6, r8, r9);
    }

    public static /* synthetic */ void $r8$lambda$i8s2rbfr31qXWKjN37MYaKMynOU(WearReplyReceiver r0, AccountInstance r1, long r2, CharSequence r4, long r5, int r7, int[] r8) {
        r0.lambda$onReceive$1(r1, r2, r4, r5, r7, r8);
    }

    public WearReplyReceiver() {
    }

    private /* synthetic */ void lambda$onReceive$0(AccountInstance r3, TLRPC.User r4, CharSequence r5, long r6, long r8, int r10, int[] r11) {
        r3.getMessagesController().putUser(r4, true);
        sendMessage(r3, r5, r6, r8, r10, r11);
    }

    private /* synthetic */ void lambda$onReceive$1(AccountInstance r13, long r14, CharSequence r16, long r17, int r19, int[] r20) {
        AndroidUtilities.runOnUIThread(new WearReplyReceiver$$ExternalSyntheticLambda2(this, r13, r13.getMessagesStorage().getUserSync(r14), r16, r14, r17, r19, r20));
    }

    private /* synthetic */ void lambda$onReceive$2(AccountInstance r3, TLRPC.Chat r4, CharSequence r5, long r6, long r8, int r10, int[] r11) {
        r3.getMessagesController().putChat(r4, true);
        sendMessage(r3, r5, r6, r8, r10, r11);
    }

    private /* synthetic */ void lambda$onReceive$3(AccountInstance r13, long r14, CharSequence r16, long r17, int r19, int[] r20) {
        AndroidUtilities.runOnUIThread(new WearReplyReceiver$$ExternalSyntheticLambda3(this, r13, r13.getMessagesStorage().getChatSync(-r14), r16, r14, r17, r19, r20));
    }

    private void sendMessage(AccountInstance r20, CharSequence r21, long r22, long r24, int r26, int[] r27) {
        MessageObject r6 = null;
        if (r26 == 0) goto L5;
        TLRPC.TL_message r7 = new TLRPC.TL_message();
        r7.message = _UrlKt.FRAGMENT_ENCODE_SET;
        r7.id = r26;
        r7.peer_id = r20.getMessagesController().getPeer(r22);
        MessageObject r8 = new MessageObject(r20.getCurrentAccount(), r7, false, false);
    L7:
        if (r24 == 0) goto L9;
        TLRPC.TL_message r62 = new TLRPC.TL_message();
        r62.message = _UrlKt.FRAGMENT_ENCODE_SET;
        r62.id = (int) r24;
        r62.peer_id = r20.getMessagesController().getPeer(r22);
        TLRPC.TL_messageActionTopicCreate r72 = new TLRPC.TL_messageActionTopicCreate();
        r62.action = r72;
        r72.title = _UrlKt.FRAGMENT_ENCODE_SET;
        r6 = new MessageObject(r20.getCurrentAccount(), r62, false, false);
    L9:
        r20.getSendMessagesHelper().sendMessage(SendMessagesHelper.SendMessageParams.of(r21.toString(), r22, r8, r6, null, true, null, null, null, true, 0, 0, null, false));
        if (r27 != null) goto L12;
    L17:
        if (r24 != 0) goto L21;
        r20.getMessagesController().markDialogAsRead(r22, r26, r26, 0, false, r24, 0, true, 0);
        return;
    L21:
        return;
    L12:
        if (r27.length <= 0) goto L17;
        ArrayList<Integer> r3 = new ArrayList(r27.length);
        int r4 = r27.length;
        int r5 = 0;
    L14:
        if (r5 >= r4) goto L16;
        r3.add(Integer.valueOf(r27[r5]));
        r5 = r5 + 1;
        goto L14
    L16:
        r20.getMessagesStorage().markVoiceMessageContentAsRead(r22, r3);
        goto L17
    L5:
        r8 = null;
        goto L7
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context r12, Intent r13) {
        ApplicationLoader.postInitApplication();
        Bundle r122 = RemoteInput.getResultsFromIntent(r13);
        if (r122 == null) goto L33;
        CharSequence r6 = r122.getCharSequence(NotificationsController.EXTRA_VOICE_REPLY);
        if (TextUtils.isEmpty(r6) == true) goto L34;
        long r4 = r13.getLongExtra("dialog_id", 0);
        int r9 = r13.getIntExtra("max_id", 0);
        long r7 = r13.getLongExtra("topic_id", 0);
        int r123 = r13.getIntExtra("currentAccount", 0);
        int[] r10 = r13.getIntArrayExtra("voice_msg_ids");
        if (r4 == 0) goto L31;
        if (r9 != 0) goto L12;
        return;
    L12:
        if (UserConfig.isValidAccount(r123) == false) goto L35;
        AccountInstance r3 = AccountInstance.getInstance(r123);
        if (DialogObject.isUserDialog(r4) == false) goto L23;
        if (r3.getMessagesController().getUser(Long.valueOf(r4)) != null) goto L20;
        Utilities.globalQueue.postRunnable(new WearReplyReceiver$$ExternalSyntheticLambda0(this, r3, r4, r6, r7, r9, r10));
        return;
    L20:
        WearReplyReceiver r1 = this;
    L29:
        r1.sendMessage(r3, r6, r4, r7, r9, r10);
        return;
    L23:
        if (DialogObject.isChatDialog(r4) == true) goto L25;
    L28:
        r1 = this;
        goto L29
    L25:
        if (r3.getMessagesController().getChat(Long.valueOf(-r4)) != null) goto L28;
        Utilities.globalQueue.postRunnable(new WearReplyReceiver$$ExternalSyntheticLambda1(this, r3, r4, r6, r7, r9, r10));
        return;
    L35:
        return;
    L31:
        return;
    L34:
        return;
    }
}
