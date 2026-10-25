package org.telegram.messenger.voip;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_phone;

/* loaded from: classes6.dex */
public final /* synthetic */ class ConferenceCall$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ ConferenceCall f$0;
    public final /* synthetic */ TL_phone.getGroupCallChainBlocks f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ TLObject f$3;
    public final /* synthetic */ TLRPC.TL_error f$4;
    public final /* synthetic */ AtomicBoolean f$5;
    public final /* synthetic */ AtomicInteger f$6;

    public /* synthetic */ ConferenceCall$$ExternalSyntheticLambda4(ConferenceCall r1, TL_phone.getGroupCallChainBlocks r2, long r3, TLObject r5, TLRPC.TL_error r6, AtomicBoolean r7, AtomicInteger r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ConferenceCall.$r8$lambda$v_k5jGpB96_rMGmGxJTqxojjQ00(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
