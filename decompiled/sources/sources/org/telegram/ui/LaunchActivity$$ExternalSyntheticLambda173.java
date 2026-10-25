package org.telegram.ui;

import android.os.Bundle;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes6.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda173 implements RequestDelegate {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ Browser.Progress f$1;
    public final /* synthetic */ int f$10;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ Integer f$4;
    public final /* synthetic */ byte[] f$5;
    public final /* synthetic */ BaseFragment f$6;
    public final /* synthetic */ Bundle f$7;
    public final /* synthetic */ ChatActivity f$8;
    public final /* synthetic */ String f$9;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda173(LaunchActivity r1, Browser.Progress r2, long r3, int r5, Integer r6, byte[] r7, BaseFragment r8, Bundle r9, ChatActivity r10, String r11, int r12) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
        this.f$7 = r9;
        this.f$8 = r10;
        this.f$9 = r11;
        this.f$10 = r12;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r15, TLRPC.TL_error r16) {
        LaunchActivity.$r8$lambda$bI8eT12rd1ohSsgWpj4Bq0XU_NM(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, r15, r16);
    }
}
