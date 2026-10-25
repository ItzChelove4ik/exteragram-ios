package org.telegram.messenger;

import android.content.Context;
import android.widget.FrameLayout;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes5.dex */
public final /* synthetic */ class MediaDataController$$ExternalSyntheticLambda232 implements Runnable {
    public final /* synthetic */ MediaDataController f$0;
    public final /* synthetic */ boolean[] f$1;
    public final /* synthetic */ int f$10;
    public final /* synthetic */ Context f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ BaseFragment f$4;
    public final /* synthetic */ FrameLayout f$5;
    public final /* synthetic */ boolean f$6;
    public final /* synthetic */ TLObject f$7;
    public final /* synthetic */ TLRPC.StickerSet f$8;
    public final /* synthetic */ TLRPC.Document f$9;

    public /* synthetic */ MediaDataController$$ExternalSyntheticLambda232(MediaDataController r1, boolean[] r2, Context r3, int r4, BaseFragment r5, FrameLayout r6, boolean r7, TLObject r8, TLRPC.StickerSet r9, TLRPC.Document r10, int r11) {
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
    }

    @Override // java.lang.Runnable
    public final void run() {
        MediaDataController.$r8$lambda$jTYtImWr6eV7uD4DpfYYhWE58y8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10);
    }
}
