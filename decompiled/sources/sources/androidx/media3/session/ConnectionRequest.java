package androidx.media3.session;

import android.os.Bundle;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;

/* loaded from: classes4.dex */
class ConnectionRequest {
    private static final String FIELD_CONNECTION_HINTS = null;
    private static final String FIELD_CONTROLLER_INTERFACE_VERSION = null;
    private static final String FIELD_LIBRARY_VERSION = null;
    private static final String FIELD_MAX_COMMANDS_FOR_MEDIA_ITEM = null;
    private static final String FIELD_PACKAGE_NAME = null;
    private static final String FIELD_PID = null;
    public final Bundle connectionHints;
    public final int controllerInterfaceVersion;
    public final int libraryVersion;
    public final int maxCommandsForMediaItems;
    public final String packageName;
    public final int pid;

    static {
        FIELD_LIBRARY_VERSION = Util.intToStringMaxRadix(0);
        FIELD_PACKAGE_NAME = Util.intToStringMaxRadix(1);
        FIELD_PID = Util.intToStringMaxRadix(2);
        FIELD_CONNECTION_HINTS = Util.intToStringMaxRadix(3);
        FIELD_CONTROLLER_INTERFACE_VERSION = Util.intToStringMaxRadix(4);
        FIELD_MAX_COMMANDS_FOR_MEDIA_ITEM = Util.intToStringMaxRadix(5);
    }

    private ConnectionRequest(int r1, int r2, String r3, int r4, Bundle r5, int r6) {
        this.libraryVersion = r1;
        this.controllerInterfaceVersion = r2;
        this.packageName = r3;
        this.pid = r4;
        this.connectionHints = r5;
        this.maxCommandsForMediaItems = r6;
    }

    public static ConnectionRequest fromBundle(Bundle r9) {
        int r3 = r9.getInt(FIELD_LIBRARY_VERSION, 0);
        int r4 = r9.getInt(FIELD_CONTROLLER_INTERFACE_VERSION, 0);
        String r5 = (String) Assertions.checkNotNull(r9.getString(FIELD_PACKAGE_NAME));
        String r0 = FIELD_PID;
        Assertions.checkArgument(r9.containsKey(r0));
        int r6 = r9.getInt(r0);
        Bundle r02 = r9.getBundle(FIELD_CONNECTION_HINTS);
        int r8 = r9.getInt(FIELD_MAX_COMMANDS_FOR_MEDIA_ITEM, 0);
        if (r02 != null) goto L6;
        r02 = Bundle.EMPTY;
    L6:
        return new ConnectionRequest(r3, r4, r5, r6, r02, r8);
    }
}
