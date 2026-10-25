package org.webrtc;

/* loaded from: classes6.dex */
class NativeLibrary {
    private static String TAG = "NativeLibrary";
    private static boolean libraryLoaded;
    private static Object lock;

    public static class DefaultLoader implements NativeLibraryLoader {
        public DefaultLoader() {
        }

        @Override // org.webrtc.NativeLibraryLoader
        public boolean load(String r3) {
            Logging.d(NativeLibrary.m22358$$Nest$sfgetTAG(), "Loading library: " + r3);
            System.loadLibrary(r3);
            return true;
        }
    }

    /* renamed from: -$$Nest$sfgetTAG, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m22358$$Nest$sfgetTAG() {
        return TAG;
    }

    static {
        lock = new Object();
    }

    public NativeLibrary() {
    }

    public static void initialize(NativeLibraryLoader r4, String r5) {
        Object r1 = lock;
        monitor-enter(r1);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (libraryLoaded == false) goto L11;
        Logging.d(TAG, "Native library has already been loaded.");     // Catch: Throwable -> L9
        monitor-exit(r1);     // Catch: Throwable -> L9
        return;
    L11:
        Logging.d(TAG, "Loading native library: " + r5);     // Catch: Throwable -> L9
        libraryLoaded = r4.load(r5);     // Catch: Throwable -> L9
        monitor-exit(r1);     // Catch: Throwable -> L9
    }

    public static boolean isLoaded() {
        Object r0 = lock;
        monitor-enter(r0);
        boolean r1 = libraryLoaded;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }
}
