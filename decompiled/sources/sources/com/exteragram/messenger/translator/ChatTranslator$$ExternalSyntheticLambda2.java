package com.exteragram.messenger.translator;

import com.exteragram.messenger.translator.ChatTranslator;
import com.exteragram.messenger.translator.core.HttpTranslator;
import com.exteragram.messenger.translator.core.TranslationDispatcher;
import com.exteragram.messenger.translator.core.TranslationError;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Ref;

/* loaded from: classes4.dex */
public final /* synthetic */ class ChatTranslator$$ExternalSyntheticLambda2 implements TranslationDispatcher.Completion {
    public final /* synthetic */ List f$0;
    public final /* synthetic */ Ref.ObjectRef f$1;
    public final /* synthetic */ Ref.IntRef f$2;
    public final /* synthetic */ HttpTranslator f$3;
    public final /* synthetic */ String f$4;
    public final /* synthetic */ List f$5;
    public final /* synthetic */ ArrayList f$6;
    public final /* synthetic */ String[] f$7;
    public final /* synthetic */ ChatTranslator.Callback f$8;

    public /* synthetic */ ChatTranslator$$ExternalSyntheticLambda2(List r1, Ref.ObjectRef r2, Ref.IntRef r3, HttpTranslator r4, String r5, List r6, ArrayList r7, String[] r8, ChatTranslator.Callback r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r9;
    }

    @Override // com.exteragram.messenger.translator.core.TranslationDispatcher.Completion
    public final void onDone(List r12, TranslationError r13) {
        ChatTranslator.m1736$r8$lambda$mytC8HTQw0Wntx8e7mXkUSUWpY(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, r12, r13);
    }
}
