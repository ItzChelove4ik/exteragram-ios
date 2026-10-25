package com.google.android.gms.internal.mlkit_genai_prompt;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import com.google.mlkit.genai.schema.guided.GenerableProvider;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ServiceLoader;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.ranges.RangesKt;

/* loaded from: classes4.dex */
public abstract class zzyg {
    private static final Lazy zzb = null;

    static {
        zzb = LazyKt.lazy(zzyf.zza);
    }

    public static /* synthetic */ Map zza() {
        return zzc();
    }

    public static final Map zzb() {
        return (Map) zzb.getValue();
    }

    private static final Map zzc() {
        ServiceLoader r0 = ServiceLoader.load(GenerableProvider.class, zzyg.class.getClassLoader());
        LinkedHashMap r2 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(r0, 10)), 16));
        Iterator r02 = r0.iterator();
        if (r02.hasNext() == true) goto L5;
        return r2;
    L5:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r02.next());
        throw null;
    }
}
