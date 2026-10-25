package org.telegram.messenger;

import org.telegram.messenger.HashtagSearchController;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class HashtagSearchController$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ HashtagSearchController f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ int[] f$3;
    public final /* synthetic */ HashtagSearchController.SearchResult f$4;
    public final /* synthetic */ int f$5;
    public final /* synthetic */ int f$6;
    public final /* synthetic */ int f$7;

    public /* synthetic */ HashtagSearchController$$ExternalSyntheticLambda1(HashtagSearchController r1, int r2, String r3, int[] r4, HashtagSearchController.SearchResult r5, int r6, int r7, int r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r11, TLRPC.TL_error r12) {
        HashtagSearchController.m4126$r8$lambda$4qSxZ4dJjfKGNfhtjvVef8gVzs(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, r11, r12);
    }
}
