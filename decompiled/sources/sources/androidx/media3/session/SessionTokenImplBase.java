package androidx.media3.session;

import android.content.ComponentName;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import androidx.core.app.BundleCompat;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.session.SessionToken;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
final class SessionTokenImplBase implements SessionToken.SessionTokenImpl {
    private static final String FIELD_COMPONENT_NAME = null;
    private static final String FIELD_EXTRAS = null;
    private static final String FIELD_INTERFACE_VERSION = null;
    private static final String FIELD_ISESSION = null;
    private static final String FIELD_LIBRARY_VERSION = null;
    private static final String FIELD_PACKAGE_NAME = null;
    private static final String FIELD_PLATFORM_TOKEN = null;
    private static final String FIELD_SERVICE_NAME = null;
    private static final String FIELD_TYPE = null;
    private static final String FIELD_UID = null;
    private final ComponentName componentName;
    private final Bundle extras;
    private final IBinder iSession;
    private final int interfaceVersion;
    private final int libraryVersion;
    private final String packageName;
    private final MediaSession.Token platformToken;
    private final String serviceName;
    private final int type;
    private final int uid;

    static {
        FIELD_UID = Util.intToStringMaxRadix(0);
        FIELD_TYPE = Util.intToStringMaxRadix(1);
        FIELD_LIBRARY_VERSION = Util.intToStringMaxRadix(2);
        FIELD_PACKAGE_NAME = Util.intToStringMaxRadix(3);
        FIELD_SERVICE_NAME = Util.intToStringMaxRadix(4);
        FIELD_COMPONENT_NAME = Util.intToStringMaxRadix(5);
        FIELD_ISESSION = Util.intToStringMaxRadix(6);
        FIELD_EXTRAS = Util.intToStringMaxRadix(7);
        FIELD_INTERFACE_VERSION = Util.intToStringMaxRadix(8);
        FIELD_PLATFORM_TOKEN = Util.intToStringMaxRadix(9);
    }

    public SessionTokenImplBase(int r13, int r14, int r15, int r16, String r17, IMediaSession r18, Bundle r19, MediaSession.Token r20) {
        this(r13, r14, r15, r16, (String) Assertions.checkNotNull(r17), _UrlKt.FRAGMENT_ENCODE_SET, null, r18.asBinder(), (Bundle) Assertions.checkNotNull(r19), r20);
    }

    private SessionTokenImplBase(int r1, int r2, int r3, int r4, String r5, String r6, ComponentName r7, IBinder r8, Bundle r9, MediaSession.Token r10) {
        this.uid = r1;
        this.type = r2;
        this.libraryVersion = r3;
        this.interfaceVersion = r4;
        this.packageName = r5;
        this.serviceName = r6;
        this.componentName = r7;
        this.iSession = r8;
        this.extras = r9;
        this.platformToken = r10;
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof SessionTokenImplBase) == true) goto L5;
        return false;
    L5:
        SessionTokenImplBase r42 = (SessionTokenImplBase) r4;
        if (this.uid == r42.uid) goto L8;
    L25:
        return false;
    L8:
        if (this.type != r42.type) goto L25;
        if (this.libraryVersion != r42.libraryVersion) goto L25;
        if (this.interfaceVersion != r42.interfaceVersion) goto L25;
        if (TextUtils.equals(this.packageName, r42.packageName) == false) goto L25;
        if (TextUtils.equals(this.serviceName, r42.serviceName) == false) goto L25;
        if (Objects.equals(this.componentName, r42.componentName) == false) goto L25;
        if (Objects.equals(this.iSession, r42.iSession) == false) goto L25;
        if (Objects.equals(this.platformToken, r42.platformToken) == false) goto L25;
        return true;
    }

    @Override // androidx.media3.session.SessionToken.SessionTokenImpl
    public Bundle getExtras() {
        return new Bundle(this.extras);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.uid), Integer.valueOf(this.type), Integer.valueOf(this.libraryVersion), Integer.valueOf(this.interfaceVersion), this.packageName, this.serviceName, this.componentName, this.iSession, this.platformToken});
    }

    @Override // androidx.media3.session.SessionToken.SessionTokenImpl
    public Bundle toBundle() {
        Bundle r0 = new Bundle();
        r0.putInt(FIELD_UID, this.uid);
        r0.putInt(FIELD_TYPE, this.type);
        r0.putInt(FIELD_LIBRARY_VERSION, this.libraryVersion);
        r0.putString(FIELD_PACKAGE_NAME, this.packageName);
        r0.putString(FIELD_SERVICE_NAME, this.serviceName);
        BundleCompat.putBinder(r0, FIELD_ISESSION, this.iSession);
        r0.putParcelable(FIELD_COMPONENT_NAME, this.componentName);
        r0.putBundle(FIELD_EXTRAS, this.extras);
        r0.putInt(FIELD_INTERFACE_VERSION, this.interfaceVersion);
        MediaSession.Token r3 = this.platformToken;
        if (r3 == null) goto L5;
        r0.putParcelable(FIELD_PLATFORM_TOKEN, r3);
    L5:
        return r0;
    }

    public String toString() {
        return "SessionToken {pkg=" + this.packageName + " type=" + this.type + " libraryVersion=" + this.libraryVersion + " interfaceVersion=" + this.interfaceVersion + " service=" + this.serviceName + " IMediaSession=" + this.iSession + " extras=" + this.extras + "}";
    }
}
