package com.exteragram.messenger.plugins.ui;

import com.exteragram.messenger.translator.ChatTranslator;
import com.exteragram.messenger.translator.core.TranslationError;
import java.util.List;
import kotlin.jvm.functions.Function0;

/* loaded from: classes4.dex */
public final /* synthetic */ class PluginSettingsTranslation$$ExternalSyntheticLambda3 implements ChatTranslator.Callback {
    public final /* synthetic */ List f$0;
    public final /* synthetic */ Function0 f$1;
    public final /* synthetic */ String[] f$2;

    public /* synthetic */ PluginSettingsTranslation$$ExternalSyntheticLambda3(List r1, Function0 r2, String[] r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // com.exteragram.messenger.translator.ChatTranslator.Callback
    public final void onResult(List r3, TranslationError r4) {
        PluginSettingsTranslation.$r8$lambda$BhislwJ_w_bX3GhCIz9I8vwOc8g(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
