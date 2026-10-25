package com.exteragram.messenger.translator.core;

import com.exteragram.messenger.translator.core.TranslationDispatcher;
import java.util.List;

/* loaded from: classes4.dex */
public final /* synthetic */ class TranslationDispatcher$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ boolean f$0;
    public final /* synthetic */ TranslationDispatcher.Job f$1;
    public final /* synthetic */ List f$2;
    public final /* synthetic */ TranslationError f$3;

    public /* synthetic */ TranslationDispatcher$$ExternalSyntheticLambda6(boolean r1, TranslationDispatcher.Job r2, List r3, TranslationError r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        TranslationDispatcher.$r8$lambda$fKvaJXZz4Z9eg26HlE3NWmdUvgY(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
