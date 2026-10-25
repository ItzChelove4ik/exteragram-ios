package org.telegram.ui;

import android.os.Bundle;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes6.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda114 implements RequestDelegate {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ Runnable f$1;
    public final /* synthetic */ int f$10;
    public final /* synthetic */ Bundle f$11;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ Long f$3;
    public final /* synthetic */ Browser.Progress f$4;
    public final /* synthetic */ Long f$5;
    public final /* synthetic */ Integer f$6;
    public final /* synthetic */ Integer f$7;
    public final /* synthetic */ byte[] f$8;
    public final /* synthetic */ BaseFragment f$9;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda114(LaunchActivity r1, Runnable r2, boolean r3, Long r4, Browser.Progress r5, Long r6, Integer r7, Integer r8, byte[] r9, BaseFragment r10, int r11, Bundle r12) {
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
        LaunchActivity.$r8$lambda$K0hB6BC0u6uMhRLlV839amIhRsQ(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11, r15, r16);
    }
}
