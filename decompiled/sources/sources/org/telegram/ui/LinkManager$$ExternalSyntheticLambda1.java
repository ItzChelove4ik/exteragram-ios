package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_aicompose;

/* loaded from: classes6.dex */
public final /* synthetic */ class LinkManager$$ExternalSyntheticLambda1 implements Utilities.Callback2 {
    public final /* synthetic */ LinkManager f$0;

    public /* synthetic */ LinkManager$$ExternalSyntheticLambda1(LinkManager r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        LinkManager.$r8$lambda$5KgVTrduXjiAqfIwbxgOCFjBQl4(this.f$0, (TL_aicompose.Tones) r1, (TLRPC.TL_error) r2);
    }
}
