package com.google.android.gms.internal.vision;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.internal.vision.zzio;
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
abstract class zziz<T extends zzio> {
    private static final Logger zza = null;
    private static String zzb;

    static {
        zza = Logger.getLogger(zzii.class.getName());
        zzb = "com.google.protobuf.BlazeGeneratedExtensionRegistryLiteLoader";
    }

    public static <T extends zzio> T zza(Class<T> r11) {
        ClassLoader r2 = zziz.class.getClassLoader();
        if (r11.equals(zzio.class) == false) goto L5;
        String r0 = zzb;
    L65:
    L30:
        Iterator r1 = ServiceLoader.load(zziz.class, r2).iterator();
        ArrayList r22 = new ArrayList();
    L32:
        if (r1.hasNext() == false) goto L34;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1.next());     // Catch: ServiceConfigurationError -> L56
        throw null;
    L56:
        e = move-exception;
        Logger r5 = zza;
        Level r6 = Level.SEVERE;
        String r02 = r11.getSimpleName();
        if (r02.length() == 0) goto L61;
        String r03 = "Unable to load ".concat(r02);
    L62:
        r5.logp(r6, "com.google.protobuf.GeneratedExtensionRegistryLoader", "load", r03, e);
        goto L32
    L61:
        r03 = new String("Unable to load ");
        goto L62
    L34:
        if (r22.size() != 1) goto L38;
        return (T) r22.get(0);
    L38:
        if (r22.size() != 0) goto L68;
        return null;
    L68:
        return (T) r11.getMethod("combine", new Class[]{Collection.class}).invoke(null, new Object[]{r22});
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
    L66:
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
        if (r11.getPackage().equals(zziz.class.getPackage()) == true) goto L8;
        f$$ExternalSyntheticBUOutline1.m(r11.getName());
        return null;
    L8:
        r0 = String.format("%s.BlazeGenerated%sLoader", new Object[]{r11.getPackage().getName(), r11.getSimpleName()});
        goto L65
    }
}
