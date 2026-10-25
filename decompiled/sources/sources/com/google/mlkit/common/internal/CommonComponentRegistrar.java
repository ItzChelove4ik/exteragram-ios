package com.google.mlkit.common.internal;

import com.google.android.gms.internal.mlkit_common.zzaf;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.mlkit.common.model.RemoteModelManager;
import com.google.mlkit.common.sdkinternal.Cleaner;
import com.google.mlkit.common.sdkinternal.CloseGuard$Factory;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.MlKitThreadPool;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import com.google.mlkit.common.sdkinternal.model.ModelFileHelper;
import java.util.List;

/* loaded from: classes.dex */
public class CommonComponentRegistrar implements ComponentRegistrar {
    public CommonComponentRegistrar() {
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public final List getComponents() {
        return zzaf.zzi(SharedPrefManager.COMPONENT, Component.builder(ModelFileHelper.class).add(Dependency.required(MlKitContext.class)).factory(new zza()).build(), Component.builder(MlKitThreadPool.class).factory(new zzb()).build(), Component.builder(RemoteModelManager.class).add(Dependency.setOf(RemoteModelManager.RemoteModelManagerRegistration.class)).factory(new zzc()).build(), Component.builder(ExecutorSelector.class).add(Dependency.requiredProvider(MlKitThreadPool.class)).factory(new zzd()).build(), Component.builder(Cleaner.class).factory(new zze()).build(), Component.builder(CloseGuard$Factory.class).add(Dependency.required(Cleaner.class)).factory(new zzf()).build(), Component.builder(com.google.mlkit.common.internal.model.zzg.class).add(Dependency.required(MlKitContext.class)).factory(new zzg()).build(), Component.intoSetBuilder(RemoteModelManager.RemoteModelManagerRegistration.class).add(Dependency.requiredProvider(com.google.mlkit.common.internal.model.zzg.class)).factory(new zzh()).build());
    }
}
