package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class JoinGroupAlert$$ExternalSyntheticLambda3 implements Utilities.Callback2 {
    public final /* synthetic */ JoinGroupAlert f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ TLRPC.TL_messages_importChatInvite f$3;

    public /* synthetic */ JoinGroupAlert$$ExternalSyntheticLambda3(JoinGroupAlert r1, long r2, int r4, TLRPC.TL_messages_importChatInvite r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r8, Object r9) {
        JoinGroupAlert.$r8$lambda$rXlV93SCXGe8SJFjGHWIZL4vum0(this.f$0, this.f$1, this.f$2, this.f$3, (TLRPC.ChatInviteJoinResult) r8, (TLRPC.TL_error) r9);
    }
}
