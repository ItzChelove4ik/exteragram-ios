package com.google.android.gms.internal.mlkit_genai_prompt;

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
public abstract class zzaws {
    public static zzawl zzb(Class r12) {
        ClassLoader r2 = zzaws.class.getClassLoader();
        if (r12.equals(zzawl.class) == true) goto L37;
    L11:
        Iterator r1 = ServiceLoader.load(zzaws.class, r2).iterator();
        ArrayList r22 = new ArrayList();
    L13:
        if (r1.hasNext() == false) goto L15;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1.next());     // Catch: ServiceConfigurationError -> L28
        throw null;
    L28:
        e = move-exception;
        Logger.getLogger(zzawf.class.getName()).logp(Level.SEVERE, "com.google.protobuf.GeneratedExtensionRegistryLoader", "load", "Unable to load ".concat(r12.getSimpleName()), e);
        goto L13
    L15:
        if (r22.size() != 1) goto L19;
        return (zzawl) r22.get(0);
    L19:
        if (r22.size() != 0) goto L35;
        return null;
    L35:
        return (zzawl) r12.getMethod("combine", new Class[]{Collection.class}).invoke(null, new Object[]{r22});
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
