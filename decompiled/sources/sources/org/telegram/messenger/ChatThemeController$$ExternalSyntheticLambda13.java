package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class ChatThemeController$$ExternalSyntheticLambda13 implements RequestDelegate {
    public final /* synthetic */ ChatThemeController f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ Runnable f$4;

    public /* synthetic */ ChatThemeController$$ExternalSyntheticLambda13(ChatThemeController r1, long r2, boolean r4, String r5, Runnable r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
        this.f$4 = r6;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        ChatThemeController.$r8$lambda$tkK58zKdK6xmgJ5ztu1TsHvw3hc(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r9, r10);
    }
}
