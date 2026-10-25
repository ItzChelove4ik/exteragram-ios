package com.google.mlkit.common.sdkinternal.model;

import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public class ModelFileHelper {
    public static final String zza = null;
    public static final String zzb = null;
    static final String zzc = null;
    private static final GmsLogger zzd = null;
    private final MlKitContext zze;

    static {
        zzd = new GmsLogger("ModelFileHelper", _UrlKt.FRAGMENT_ENCODE_SET);
        zza = String.format("com.google.mlkit.%s.models", new Object[]{"translate"});
        zzb = String.format("com.google.mlkit.%s.models", new Object[]{PluginsConstants.Settings.TYPE_CUSTOM});
        zzc = String.format("com.google.mlkit.%s.models", new Object[]{"base"});
    }

    public ModelFileHelper(MlKitContext r1) {
        this.zze = r1;
    }
}
