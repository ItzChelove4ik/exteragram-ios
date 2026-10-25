package com.google.mlkit.vision.label.defaults.thin;

import com.google.android.gms.internal.mlkit_vision_label.zzbe;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.vision.common.internal.MultiFlavorDetectorCreator;
import java.util.List;

/* loaded from: classes.dex */
public class ThinLabelRegistrar implements ComponentRegistrar {
    public ThinLabelRegistrar() {
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public final List getComponents() {
        return zzbe.zzj(Component.builder(zze.class).add(Dependency.required(MlKitContext.class)).factory(zzi.zza).build(), Component.builder(zzd.class).add(Dependency.required(zze.class)).add(Dependency.required(ExecutorSelector.class)).factory(zzj.zza).build(), Component.intoSetBuilder(MultiFlavorDetectorCreator.Registration.class).add(Dependency.requiredProvider(zzd.class)).factory(zzk.zza).build());
    }
}
