package com.google.android.gms.internal.play_billing;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.protobuf.UnsafeUtil$$ExternalSyntheticBUOutline0;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes5.dex */
public abstract class zzfc {
    public static zzeu zzb(Class r11) {
        ClassLoader r2 = zzfc.class.getClassLoader();
        if (r11.equals(zzeu.class) == false) goto L5;
        String r0 = "com.google.protobuf.BlazeGeneratedExtensionRegistryLiteLoader";
    L60:
    L30:
        Iterator r1 = ServiceLoader.load(zzfc.class, r2).iterator();
        ArrayList r22 = new ArrayList();
    L32:
        if (r1.hasNext() == false) goto L34;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1.next());     // Catch: ServiceConfigurationError -> L56
        throw null;
    L56:
        e = move-exception;
        Logger.getLogger(zzep.class.getName()).logp(Level.SEVERE, "com.google.protobuf.GeneratedExtensionRegistryLoader", "load", "Unable to load ".concat(r11.getSimpleName()), e);
        goto L32
    L34:
        if (r22.size() != 1) goto L38;
        return (zzeu) r22.get(0);
    L38:
        if (r22.size() != 0) goto L63;
        return null;
    L63:
        return (zzeu) r11.getMethod("combine", new Class[]{Collection.class}).invoke(null, new Object[]{r22});
    L44:
        e = move-exception;
        UnsafeUtil$$ExternalSyntheticBUOutline0.m(e);
        return null;
    L46:
        e = move-exception;
        UnsafeUtil$$ExternalSyntheticBUOutline0.m(e);
        return null;
    L42:
        e = move-exception;
        UnsafeUtil$$ExternalSyntheticBUOutline0.m(e);
        return null;
    L61:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(Class.forName(r0, true, r2).getConstructor(null).newInstance(null));     // Catch: InvocationTargetException -> L14 IllegalAccessException -> L16 InstantiationException -> L18 NoSuchMethodException -> L20 ClassNotFoundException -> L30
        throw null;
    L18:
        e = move-exception;
        throw new IllegalStateException(e);     // Catch: ClassNotFoundException -> L30
    L14:
        e = move-exception;
        throw new IllegalStateException(e);     // Catch: ClassNotFoundException -> L30
    L16:
        e = move-exception;
        throw new IllegalStateException(e);     // Catch: ClassNotFoundException -> L30
    L20:
        e = move-exception;
        throw new IllegalStateException(e);     // Catch: ClassNotFoundException -> L30
    L5:
        if (r11.getPackage().equals(zzfc.class.getPackage()) == true) goto L8;
        f$$ExternalSyntheticBUOutline1.m(r11.getName());
        return null;
    L8:
        r0 = String.format("%s.BlazeGenerated%sLoader", new Object[]{r11.getPackage().getName(), r11.getSimpleName()});
        goto L60
    }
}
