package org.telegram.messenger;

import android.content.Context;
import android.widget.FrameLayout;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes5.dex */
public final /* synthetic */ class MediaDataController$$ExternalSyntheticLambda108 implements RequestDelegate {
    public final /* synthetic */ MediaDataController f$0;
    public final /* synthetic */ TLRPC.StickerSet f$1;
    public final /* synthetic */ BaseFragment f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ int f$4;
    public final /* synthetic */ boolean f$5;
    public final /* synthetic */ FrameLayout f$6;
    public final /* synthetic */ Context f$7;
    public final /* synthetic */ TLObject f$8;
    public final /* synthetic */ TLRPC.Document f$9;

    public /* synthetic */ MediaDataController$$ExternalSyntheticLambda108(MediaDataController r1, TLRPC.StickerSet r2, BaseFragment r3, boolean r4, int r5, boolean r6, FrameLayout r7, Context r8, TLObject r9, TLRPC.Document r10) {
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
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r13, TLRPC.TL_error r14) {
        MediaDataController.$r8$lambda$GGECOU8yEd257MqUVDuQmMPqYo8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, r13, r14);
    }
}
