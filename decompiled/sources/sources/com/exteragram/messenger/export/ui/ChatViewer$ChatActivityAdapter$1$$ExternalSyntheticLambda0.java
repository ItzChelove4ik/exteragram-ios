package com.exteragram.messenger.export.ui;

import android.content.DialogInterface;
import com.exteragram.messenger.export.ui.ChatViewer;
import org.telegram.tgnet.tl.TL_keyboard;

/* loaded from: classes4.dex */
public final /* synthetic */ class ChatViewer$ChatActivityAdapter$1$$ExternalSyntheticLambda0 implements DialogInterface.OnClickListener {
    public final /* synthetic */ ChatViewer.ChatActivityAdapter.AnonymousClass1 f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ TL_keyboard.TL_inlineButtonTypeCallback f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ TL_keyboard.TL_inlineButtonTypeSwitchInline f$4;
    public final /* synthetic */ TL_keyboard.TL_inlineButtonTypeUserProfile f$5;

    public /* synthetic */ ChatViewer$ChatActivityAdapter$1$$ExternalSyntheticLambda0(ChatViewer.ChatActivityAdapter.AnonymousClass1 r1, String r2, TL_keyboard.TL_inlineButtonTypeCallback r3, String r4, TL_keyboard.TL_inlineButtonTypeSwitchInline r5, TL_keyboard.TL_inlineButtonTypeUserProfile r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface r9, int r10) {
        ChatViewer.ChatActivityAdapter.AnonymousClass1.m1404$r8$lambda$YrGL4FJv0PMQEsOjCRGnbBlgT4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r9, r10);
    }
}
