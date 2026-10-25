package org.telegram.ui;

import android.graphics.Bitmap;
import android.view.View;
import org.telegram.messenger.Utilities;

/* loaded from: classes6.dex */
public final /* synthetic */ class MessageSendPreview$$ExternalSyntheticLambda1 implements Utilities.Callback2 {
    public final /* synthetic */ MessageSendPreview f$0;
    public final /* synthetic */ float f$1;
    public final /* synthetic */ View f$2;

    public /* synthetic */ MessageSendPreview$$ExternalSyntheticLambda1(MessageSendPreview r1, float r2, View r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r3, Object r4) {
        MessageSendPreview.$r8$lambda$tnFZ578TD_ho7jZLDglm93iATys(this.f$0, this.f$1, this.f$2, (Bitmap) r3, (Bitmap) r4);
    }
}
