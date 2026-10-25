package org.telegram.messenger.voip;

import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPService$$ExternalSyntheticLambda113 implements RequestDelegate {
    public final /* synthetic */ VoIPService f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ HashSet f$2;
    public final /* synthetic */ AtomicInteger f$3;
    public final /* synthetic */ int f$4;
    public final /* synthetic */ String f$5;

    public /* synthetic */ VoIPService$$ExternalSyntheticLambda113(VoIPService r1, long r2, HashSet r4, AtomicInteger r5, int r6, String r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r10, TLRPC.TL_error r11) {
        VoIPService.m5311$r8$lambda$XufQx4k_OXvrPNGioYM1748SsM(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r10, r11);
    }
}
