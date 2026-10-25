package org.telegram.messenger.voip;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_phone;

/* loaded from: classes6.dex */
public final /* synthetic */ class ConferenceCall$$ExternalSyntheticLambda13 implements RequestDelegate {
    public final /* synthetic */ ConferenceCall f$0;
    public final /* synthetic */ TL_phone.getGroupCallChainBlocks f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ AtomicBoolean f$3;
    public final /* synthetic */ AtomicInteger f$4;

    public /* synthetic */ ConferenceCall$$ExternalSyntheticLambda13(ConferenceCall r1, TL_phone.getGroupCallChainBlocks r2, long r3, AtomicBoolean r5, AtomicInteger r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r6;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        ConferenceCall.$r8$lambda$r2aYHK4qZxpQzTZK1XjIg74a0FU(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r9, r10);
    }
}
