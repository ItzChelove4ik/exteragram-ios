package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda133 implements Runnable {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ TLRPC.Message f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ ArrayList f$4;
    public final /* synthetic */ int f$5;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda133(SendMessagesHelper r1, boolean r2, TLRPC.Message r3, int r4, ArrayList r5, int r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.m4927$r8$lambda$cFEfd8IIC7CVQEfQylG6BQY98(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
