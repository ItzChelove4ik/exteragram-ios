package com.google.android.gms.internal.measurement;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import com.google.protobuf.UnsafeUtil$$ExternalSyntheticBUOutline0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes4.dex */
public abstract class zzadn {
    public static zzadf zzb(Class r12) {
        ClassLoader r2 = zzadn.class.getClassLoader();
        if (r12.equals(zzadf.class) == true) goto L37;
    L11:
        Iterator r1 = ServiceLoader.load(zzadn.class, r2).iterator();
        ArrayList r22 = new ArrayList();
    L13:
        if (r1.hasNext() == false) goto L15;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1.next());     // Catch: ServiceConfigurationError -> L28
        throw null;
    L28:
        e = move-exception;
        Logger.getLogger(zzada.class.getName()).logp(Level.SEVERE, "com.google.protobuf.GeneratedExtensionRegistryLoader", "load", "Unable to load ".concat(r12.getSimpleName()), e);
        goto L13
    L15:
        if (r22.size() != 1) goto L19;
        return (zzadf) r22.get(0);
    L19:
        if (r22.size() != 0) goto L35;
        return null;
    L35:
        return (zzadf) r12.getMethod("combine", new Class[]{Collection.class}).invoke(null, new Object[]{r22});
    L23:
        e = move-exception;
        UnsafeUtil$$ExternalSyntheticBUOutline0.m(e);
        return null;
    L37:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(Class.forName("com.google.protobuf.BlazeGeneratedExtensionRegistryLiteLoader", true, r2).getConstructor(null).newInstance(null));     // Catch: ReflectiveOperationException -> L8
        throw null;
    L8:
        e = move-exception;
        throw new IllegalStateException(e);     // Catch: ClassNotFoundException -> L30
    }
}
