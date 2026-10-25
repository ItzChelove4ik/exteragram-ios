package org.telegram.ui.Components;

import java.io.File;
import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ChatActivity;

/* loaded from: classes7.dex */
public final /* synthetic */ class StickersAlert$$ExternalSyntheticLambda44 implements Runnable {
    public final /* synthetic */ File f$0;
    public final /* synthetic */ ArrayList f$1;
    public final /* synthetic */ BaseFragment f$2;
    public final /* synthetic */ ChatActivity f$3;
    public final /* synthetic */ TLRPC.Document f$4;
    public final /* synthetic */ boolean f$5;
    public final /* synthetic */ TLRPC.TL_messages_stickerSet f$6;

    public /* synthetic */ StickersAlert$$ExternalSyntheticLambda44(File r1, ArrayList r2, BaseFragment r3, ChatActivity r4, TLRPC.Document r5, boolean r6, TLRPC.TL_messages_stickerSet r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StickersAlert.$r8$lambda$hXwG5Me0eVxrtsH3nkHV35L1UnM(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
