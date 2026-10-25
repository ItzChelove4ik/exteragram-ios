package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.GroupCallActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class GroupCallActivity$AvatarUpdaterDelegate$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ GroupCallActivity.AvatarUpdaterDelegate f$0;
    public final /* synthetic */ TLRPC.InputFile f$1;
    public final /* synthetic */ TLRPC.InputFile f$2;
    public final /* synthetic */ TLRPC.VideoSize f$3;
    public final /* synthetic */ double f$4;
    public final /* synthetic */ String f$5;
    public final /* synthetic */ TLRPC.PhotoSize f$6;
    public final /* synthetic */ TLRPC.PhotoSize f$7;

    public /* synthetic */ GroupCallActivity$AvatarUpdaterDelegate$$ExternalSyntheticLambda0(GroupCallActivity.AvatarUpdaterDelegate r1, TLRPC.InputFile r2, TLRPC.InputFile r3, TLRPC.VideoSize r4, double r5, String r7, TLRPC.PhotoSize r8, TLRPC.PhotoSize r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r7;
        this.f$6 = r8;
        this.f$7 = r9;
    }

    @Override // java.lang.Runnable
    public final void run() {
        GroupCallActivity.AvatarUpdaterDelegate.m15301$r8$lambda$uJG0TxANVXl9Am5m5pxwE0Hhe4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7);
    }
}
