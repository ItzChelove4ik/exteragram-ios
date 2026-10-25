package org.telegram.ui;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatUsersActivity$$ExternalSyntheticLambda3 implements RequestDelegate {
    public final /* synthetic */ ArrayList f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ AtomicInteger f$2;
    public final /* synthetic */ ArrayList f$3;
    public final /* synthetic */ Runnable f$4;

    public /* synthetic */ ChatUsersActivity$$ExternalSyntheticLambda3(ArrayList r1, int r2, AtomicInteger r3, ArrayList r4, Runnable r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        ChatUsersActivity.$r8$lambda$kejzDe4gxrMtpEuK1vGlmHGcSd4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
