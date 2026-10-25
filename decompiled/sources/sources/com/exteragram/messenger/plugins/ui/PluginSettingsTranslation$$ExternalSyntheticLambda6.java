package com.exteragram.messenger.plugins.ui;

import com.exteragram.messenger.translator.ChatTranslator;
import com.exteragram.messenger.translator.core.TranslationError;
import java.util.List;
import kotlin.jvm.functions.Function1;

/* loaded from: classes4.dex */
public final /* synthetic */ class PluginSettingsTranslation$$ExternalSyntheticLambda6 implements ChatTranslator.Callback {
    public final /* synthetic */ Function1 f$0;

    public /* synthetic */ PluginSettingsTranslation$$ExternalSyntheticLambda6(Function1 r1) {
        this.f$0 = r1;
    }

    @Override // com.exteragram.messenger.translator.ChatTranslator.Callback
    public final void onResult(List r1, TranslationError r2) {
        PluginSettingsTranslation.m1587$r8$lambda$D96Ah5Br8D_rDrlskkOU_mZsZE(this.f$0, r1, r2);
    }
}
