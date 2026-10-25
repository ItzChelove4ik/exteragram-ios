package org.telegram.ui;

import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;
import org.telegram.messenger.ChatObject;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class GroupCallActivity$$ExternalSyntheticLambda61 implements RequestDelegate {
    public final /* synthetic */ GroupCallActivity f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ HashSet f$2;
    public final /* synthetic */ AtomicInteger f$3;
    public final /* synthetic */ int f$4;
    public final /* synthetic */ ChatObject.Call f$5;
    public final /* synthetic */ String f$6;

    public /* synthetic */ GroupCallActivity$$ExternalSyntheticLambda61(GroupCallActivity r1, long r2, HashSet r4, AtomicInteger r5, int r6, ChatObject.Call r7, String r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r11, TLRPC.TL_error r12) {
        GroupCallActivity.$r8$lambda$ohreGzJ60Pj1cDtCv10KIgejMUA(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, r11, r12);
    }
}
