package org.telegram.messenger;

import android.text.TextUtils;
import android.util.LongSparseArray;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_update;

/* loaded from: classes.dex */
public class MemberRequestsController extends BaseController {
    private static final MemberRequestsController[] instances = null;
    private final LongSparseArray<TLRPC.TL_messages_chatInviteImporters> firstImportersCache;

    public static /* synthetic */ void $r8$lambda$ZPdMXgGIptd2L8ZHunQOibnjR1U(MemberRequestsController r0, TLRPC.TL_error r1, TLObject r2, TLRPC.TL_chatInviteImporter r3, boolean r4, long r5, RequestDelegate r7) {
        r0.lambda$getImporters$0(r1, r2, r3, r4, r5, r7);
    }

    public static /* synthetic */ void $r8$lambda$yU0iYAag51Ic1Xd0gczUH4WQUFg(MemberRequestsController r0, TLRPC.TL_chatInviteImporter r1, boolean r2, long r3, RequestDelegate r5, TLObject r6, TLRPC.TL_error r7) {
        r0.lambda$getImporters$1(r1, r2, r3, r5, r6, r7);
    }

    static {
        instances = new MemberRequestsController[16];
    }

    public MemberRequestsController(int r1) {
        super(r1);
        this.firstImportersCache = new LongSparseArray();
    }

    public static MemberRequestsController getInstance(int r3) {
        MemberRequestsController[] r0 = instances;
        MemberRequestsController r1 = r0[r3];
        if (r1 == null) goto L5;
        return r1;
    L5:
        monitor-enter(MemberRequestsController.class);
        MemberRequestsController r12 = r0[r3];     // Catch: Throwable -> L9
        if (r12 != null) goto L11;
        r12 = new MemberRequestsController(r3);     // Catch: Throwable -> L9
        r0[r3] = r12;     // Catch: Throwable -> L9
    L11:
        monitor-exit(MemberRequestsController.class);     // Catch: Throwable -> L9
        return r12;
    L9:
        th = move-exception;
        throw th;
    }

    private /* synthetic */ void lambda$getImporters$0(TLRPC.TL_error r2, TLObject r3, TLRPC.TL_chatInviteImporter r4, boolean r5, long r6, RequestDelegate r8) {
        if (r2 != null) goto L7;
        TLRPC.TL_messages_chatInviteImporters r0 = (TLRPC.TL_messages_chatInviteImporters) r3;
        if (r4 != null) goto L7;
        if (r5 == false) goto L7;
        this.firstImportersCache.put(r6, r0);
    L7:
        r8.run(r3, r2);
    }

    private /* synthetic */ void lambda$getImporters$1(TLRPC.TL_chatInviteImporter r10, boolean r11, long r12, RequestDelegate r14, TLObject r15, TLRPC.TL_error r16) {
        AndroidUtilities.runOnUIThread(new MemberRequestsController$$ExternalSyntheticLambda0(this, r16, r15, r10, r11, r12, r14));
    }

    public TLRPC.TL_messages_chatInviteImporters getCachedImporters(long r1) {
        return this.firstImportersCache.get(r1);
    }

    public int getImporters(long r8, String r10, TLRPC.TL_chatInviteImporter r11, LongSparseArray<TLRPC.User> r12, RequestDelegate r13) {
        boolean r102 = TextUtils.isEmpty(r10);
        TLRPC.TL_messages_getChatInviteImporters r1 = new TLRPC.TL_messages_getChatInviteImporters();
        r1.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(-r8);
        r1.requested = true;
        r1.limit = 30;
        if (r102 == true) goto L5;
        r1.q = r10;
        r1.flags |= 4;
    L5:
        if (r11 != null) goto L7;
        r1.offset_user = new TLRPC.TL_inputUserEmpty();
    L9:
        return getConnectionsManager().sendRequest(r1, new MemberRequestsController$$ExternalSyntheticLambda1(this, r11, r102, r8, r13));
    L7:
        r1.offset_user = getMessagesController().getInputUser(r12.get(r11.user_id));
        r1.offset_date = r11.date;
        goto L9
    }

    public void onPendingRequestsUpdated(TL_update.TL_updatePendingJoinRequests r5) {
        long r0 = -MessageObject.getPeerId(r5.peer);
        this.firstImportersCache.put(r0, null);
        TLRPC.ChatFull r02 = getMessagesController().getChatFull(r0);
        if (r02 == null) goto L6;
        r02.requests_pending = r5.requests_pending;
        r02.recent_requesters = r5.recent_requesters;
        r02.flags |= 131072;
        getMessagesStorage().updateChatInfo(r02, false);
        NotificationCenter r4 = getNotificationCenter();
        int r52 = NotificationCenter.chatInfoDidLoad;
        Boolean r2 = Boolean.FALSE;
        r4.postNotificationName(r52, new Object[]{r02, 0, r2, r2});
        return;
    }
}
