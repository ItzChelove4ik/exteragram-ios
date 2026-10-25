package com.exteragram.messenger.plugins;

import com.exteragram.messenger.plugins.hooks.HookRecord;
import de.robv.android.xposed.XC_MethodHook;
import kotlin.jvm.functions.Function1;

/* loaded from: classes4.dex */
public final /* synthetic */ class PluginsController$$ExternalSyntheticLambda6 implements Function1 {
    public final /* synthetic */ XC_MethodHook.Unhook f$0;

    public /* synthetic */ PluginsController$$ExternalSyntheticLambda6(XC_MethodHook.Unhook r1) {
        this.f$0 = r1;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r1) {
        return Boolean.valueOf(PluginsController.$r8$lambda$htrrOUG8xUM_bIJfiVnNVxY_i18(this.f$0, (HookRecord) r1));
    }
}
