package org.telegram.ui.web;

import android.content.DialogInterface;
import java.util.concurrent.atomic.AtomicBoolean;
import org.telegram.ui.web.BotWebViewContainer;

/* loaded from: classes6.dex */
public final /* synthetic */ class BotWebViewContainer$$ExternalSyntheticLambda24 implements DialogInterface.OnDismissListener {
    public final /* synthetic */ BotWebViewContainer f$0;
    public final /* synthetic */ AtomicBoolean f$1;
    public final /* synthetic */ BotWebViewContainer.WebRequestContext f$2;

    public /* synthetic */ BotWebViewContainer$$ExternalSyntheticLambda24(BotWebViewContainer r1, AtomicBoolean r2, BotWebViewContainer.WebRequestContext r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface r3) {
        BotWebViewContainer.m21964$r8$lambda$ZRjCv3V3Qax8gXmiIgnqLA3x8w(this.f$0, this.f$1, this.f$2, r3);
    }
}
