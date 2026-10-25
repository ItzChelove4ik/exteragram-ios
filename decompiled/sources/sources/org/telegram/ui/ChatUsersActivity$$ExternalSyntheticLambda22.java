package org.telegram.ui;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatUsersActivity$$ExternalSyntheticLambda22 implements Runnable {
    public final /* synthetic */ TLRPC.TL_error f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ ArrayList f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ AtomicInteger f$4;
    public final /* synthetic */ ArrayList f$5;
    public final /* synthetic */ Runnable f$6;

    public /* synthetic */ ChatUsersActivity$$ExternalSyntheticLambda22(TLRPC.TL_error r1, TLObject r2, ArrayList r3, int r4, AtomicInteger r5, ArrayList r6, Runnable r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChatUsersActivity.m8501$r8$lambda$55ORjWHfEEiMA1jWmi42a1piM(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
