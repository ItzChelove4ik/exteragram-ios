package androidx.camera.camera2.compat.workaround;

import androidx.camera.camera2.compat.quirk.CameraQuirks;
import androidx.camera.camera2.compat.workaround.TemplateParamsOverride;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class TemplateParamsOverride_Bindings_Companion_ProvideTemplateParamsOverrideFactory implements Provider {
    public static TemplateParamsOverride provideTemplateParamsOverride(CameraQuirks r1) {
        return (TemplateParamsOverride) Preconditions.checkNotNullFromProvides(TemplateParamsOverride.Bindings.Companion.provideTemplateParamsOverride(r1));
    }
}
