package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.Components.EmojiView;

/* loaded from: classes7.dex */
public final /* synthetic */ class EmojiView$EmojiSearchAdapter$4$1$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ EmojiView.EmojiSearchAdapter.AnonymousClass4.AnonymousClass1 f$0;
    public final /* synthetic */ AlertDialog[] f$1;
    public final /* synthetic */ BottomSheet.Builder f$2;

    public /* synthetic */ EmojiView$EmojiSearchAdapter$4$1$$ExternalSyntheticLambda0(EmojiView.EmojiSearchAdapter.AnonymousClass4.AnonymousClass1 r1, AlertDialog[] r2, BottomSheet.Builder r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        EmojiView.EmojiSearchAdapter.AnonymousClass4.AnonymousClass1.$r8$lambda$EeIbURzzoSgq8EibxzzlO3EAnK4(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
