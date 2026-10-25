package org.telegram.messenger;

import android.content.Context;
import android.widget.FrameLayout;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes5.dex */
public final /* synthetic */ class MediaDataController$$ExternalSyntheticLambda163 implements Runnable {
    public final /* synthetic */ MediaDataController f$0;
    public final /* synthetic */ TLRPC.StickerSet f$1;
    public final /* synthetic */ TLObject f$10;
    public final /* synthetic */ TLRPC.Document f$11;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ BaseFragment f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ int f$5;
    public final /* synthetic */ TLRPC.TL_error f$6;
    public final /* synthetic */ boolean f$7;
    public final /* synthetic */ FrameLayout f$8;
    public final /* synthetic */ Context f$9;

    public /* synthetic */ MediaDataController$$ExternalSyntheticLambda163(MediaDataController r1, TLRPC.StickerSet r2, TLObject r3, BaseFragment r4, boolean r5, int r6, TLRPC.TL_error r7, boolean r8, FrameLayout r9, Context r10, TLObject r11, TLRPC.Document r12) {
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

    @Override // java.lang.Runnable
    public final void run() {
        MediaDataController.$r8$lambda$vfrQC3lNNWPJLLzNvjaM8DQjnCk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11);
    }
}
