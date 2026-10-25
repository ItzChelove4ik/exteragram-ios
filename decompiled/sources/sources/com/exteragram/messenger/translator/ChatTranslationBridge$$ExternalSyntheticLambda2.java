package com.exteragram.messenger.translator;

import com.exteragram.messenger.translator.ChatTranslator;
import com.exteragram.messenger.translator.core.TranslationError;
import java.util.List;
import kotlin.jvm.functions.Function2;

/* loaded from: classes4.dex */
public final /* synthetic */ class ChatTranslationBridge$$ExternalSyntheticLambda2 implements ChatTranslator.Callback {
    public final /* synthetic */ List f$0;
    public final /* synthetic */ Function2 f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ ChatTranslationBridge$$ExternalSyntheticLambda2(List r1, Function2 r2, long r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // com.exteragram.messenger.translator.ChatTranslator.Callback
    public final void onResult(List r7, TranslationError r8) {
        ChatTranslationBridge.$r8$lambda$qFx9JU0PIK7NSKHEqlnh55oJOqM(this.f$0, this.f$1, this.f$2, r7, r8);
    }
}
