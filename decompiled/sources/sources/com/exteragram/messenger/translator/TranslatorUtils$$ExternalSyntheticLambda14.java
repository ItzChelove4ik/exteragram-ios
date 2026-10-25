package com.exteragram.messenger.translator;

import android.text.style.URLSpan;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ChatActivity;

/* loaded from: classes4.dex */
public final /* synthetic */ class TranslatorUtils$$ExternalSyntheticLambda14 implements Utilities.CallbackReturn {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ MessageObject f$1;

    public /* synthetic */ TranslatorUtils$$ExternalSyntheticLambda14(ChatActivity r1, MessageObject r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.CallbackReturn
    public final Object run(Object r2) {
        return TranslatorUtils.$r8$lambda$7fTBIuTIO9V2wJq9zH7Ge3YAw5E(this.f$0, this.f$1, (URLSpan) r2);
    }
}
