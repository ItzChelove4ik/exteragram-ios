package org.telegram.ui;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.ScrimOptions;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda139 implements Runnable {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ ItemOptions f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ TLRPC.TL_messageEntityFormattedDate f$3;
    public final /* synthetic */ ArrayList f$4;
    public final /* synthetic */ ScrimOptions f$5;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda139(ChatActivity r1, ItemOptions r2, long r3, TLRPC.TL_messageEntityFormattedDate r5, ArrayList r6, ScrimOptions r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChatActivity.m7563$r8$lambda$xVluVk5qZD0bcnAJy9soX6n0k(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
