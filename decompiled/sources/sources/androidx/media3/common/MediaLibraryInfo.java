package androidx.media3.common;

import java.util.HashSet;

/* loaded from: classes3.dex */
public abstract class MediaLibraryInfo {
    private static final HashSet<String> registeredModules = null;
    private static String registeredModulesString;

    static {
        registeredModules = new HashSet();
        registeredModulesString = "media3.common";
    }

    public static synchronized void registerModule(String r3) {
        monitor-enter(MediaLibraryInfo.class);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (registeredModules.add(r3) == false) goto L10;
        registeredModulesString += ", " + r3;
    L10:
        monitor-exit(MediaLibraryInfo.class);
    }

    public static synchronized String registeredModules() {
        monitor-enter(MediaLibraryInfo.class);
        String r1 = registeredModulesString;     // Catch: Throwable -> L7
        monitor-exit(MediaLibraryInfo.class);
        return r1;
    L7:
        th = move-exception;
        throw th;
    }
}
