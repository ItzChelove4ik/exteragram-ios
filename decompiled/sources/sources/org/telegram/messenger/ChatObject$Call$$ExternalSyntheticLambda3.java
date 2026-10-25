package org.telegram.messenger;

import java.util.Comparator;
import org.telegram.messenger.ChatObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class ChatObject$Call$$ExternalSyntheticLambda3 implements Comparator {
    public final /* synthetic */ ChatObject.Call f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ ChatObject$Call$$ExternalSyntheticLambda3(ChatObject.Call r1, long r2, boolean r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
    }

    @Override // java.util.Comparator
    public final int compare(Object r7, Object r8) {
        return ChatObject.Call.m3921$r8$lambda$zWgzLHuYSE3MrgKycL7ydcx7E4(this.f$0, this.f$1, this.f$2, (TLRPC.GroupCallParticipant) r7, (TLRPC.GroupCallParticipant) r8);
    }
}
