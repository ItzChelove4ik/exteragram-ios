package com.google.mlkit.nl.languageid.internal;

import android.content.Context;
import com.google.android.gms.internal.mlkit_language_id_common.zzu;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.nl.languageid.internal.LanguageIdentifierImpl;
import java.util.List;

/* loaded from: classes.dex */
public class LanguageIdRegistrar implements ComponentRegistrar {
    public LanguageIdRegistrar() {
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public final List getComponents() {
        return zzu.zzi(Component.builder(zzg.class).add(Dependency.required(Context.class)).add(Dependency.setOf(LanguageIdentifierCreatorDelegate.class)).factory(zza.zza).build(), Component.builder(LanguageIdentifierImpl.Factory.class).add(Dependency.required(zzg.class)).add(Dependency.required(ExecutorSelector.class)).factory(zzb.zza).build());
    }
}
