package com.google.mlkit.genai.prompt;

import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.genai.prompt.ImagePart;
import kotlin.jvm.functions.Function0;

/* loaded from: classes5.dex */
final /* synthetic */ class zze implements Function0 {
    static final /* synthetic */ zze zza = null;

    static {
        zza = new zze();
    }

    private /* synthetic */ zze() {
    }

    @Override // kotlin.jvm.functions.Function0
    public final /* synthetic */ Object invoke() {
        ImagePart.Companion r0 = ImagePart.Companion;
        return MlKitContext.getInstance().getApplicationContext();
    }
}
