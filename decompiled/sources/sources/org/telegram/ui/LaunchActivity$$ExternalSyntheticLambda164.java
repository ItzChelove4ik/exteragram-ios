package org.telegram.ui;

import java.util.ArrayList;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda164 implements RequestDelegate {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ ArrayList f$10;
    public final /* synthetic */ int f$11;
    public final /* synthetic */ TLRPC.Chat f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ int f$4;
    public final /* synthetic */ Runnable f$5;
    public final /* synthetic */ String f$6;
    public final /* synthetic */ Integer f$7;
    public final /* synthetic */ byte[] f$8;
    public final /* synthetic */ int f$9;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda164(LaunchActivity r1, int r2, TLRPC.Chat r3, int r4, int r5, Runnable r6, String r7, Integer r8, byte[] r9, int r10, ArrayList r11, int r12) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r9;
        this.f$9 = r10;
        this.f$10 = r11;
        this.f$11 = r12;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r15, TLRPC.TL_error r16) {
        LaunchActivity.m15646$r8$lambda$wMn9VfBBpkb2PS_nsaS8o1Z6iQ(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11, r15, r16);
    }
}
