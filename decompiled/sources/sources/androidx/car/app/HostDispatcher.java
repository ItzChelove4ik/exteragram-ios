package androidx.car.app;

import android.os.IInterface;
import android.util.Log;
import androidx.car.app.IAppHost;
import androidx.car.app.constraints.IConstraintHost;
import androidx.car.app.media.IMediaPlaybackHost;
import androidx.car.app.navigation.INavigationHost;
import androidx.car.app.suggestion.ISuggestionHost;
import androidx.car.app.utils.RemoteUtils;
import androidx.car.app.utils.ThreadUtils;
import com.chaquo.python.internal.Common;
import java.security.InvalidParameterException;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class HostDispatcher {
    private IAppHost mAppHost;
    private ICarHost mCarHost;
    private IConstraintHost mConstraintHost;
    private INavigationHost mNavigationHost;
    private IMediaPlaybackHost mPlaybackMediaHost;
    private ISuggestionHost mSuggestionHost;

    public static /* synthetic */ IMediaPlaybackHost $r8$lambda$8w5CDveJSdeBcrRSDyC0QplFTHg(HostDispatcher r1) {
        ICarHost r12 = r1.mCarHost;
        Objects.requireNonNull(r12);
        return IMediaPlaybackHost.Stub.asInterface(r12.getHost("media_playback"));
    }

    public static /* synthetic */ INavigationHost $r8$lambda$JbnOiXNnUGgRwrRQRy5aIgSrvN0(HostDispatcher r1) {
        ICarHost r12 = r1.mCarHost;
        Objects.requireNonNull(r12);
        return INavigationHost.Stub.asInterface(r12.getHost("navigation"));
    }

    public static /* synthetic */ IConstraintHost $r8$lambda$hTonFC9rqQH4rH5typAaZw3qlJU(HostDispatcher r1) {
        ICarHost r12 = r1.mCarHost;
        Objects.requireNonNull(r12);
        return IConstraintHost.Stub.asInterface(r12.getHost("constraints"));
    }

    public static /* synthetic */ ISuggestionHost $r8$lambda$j8DL6KSzD_S_fWmiSleKmSJ8GSQ(HostDispatcher r1) {
        ICarHost r12 = r1.mCarHost;
        Objects.requireNonNull(r12);
        return ISuggestionHost.Stub.asInterface(r12.getHost("suggestion"));
    }

    /* renamed from: $r8$lambda$qGjqOY3HWLrXXhC-aw0gszQD800, reason: not valid java name */
    public static /* synthetic */ IAppHost m635$r8$lambda$qGjqOY3HWLrXXhCaw0gszQD800(HostDispatcher r1) {
        ICarHost r12 = r1.mCarHost;
        Objects.requireNonNull(r12);
        return IAppHost.Stub.asInterface(r12.getHost(Common.ASSET_APP));
    }

    public static /* synthetic */ Object $r8$lambda$s4xJ5LW05J7W13ehpZbJ4VKDjTE(HostDispatcher r0, String r1, String r2, HostCall r3) {
        IInterface r02 = r0.getHost(r1);
        if (r02 != null) goto L6;
        Log.e("CarApp.Dispatch", "Could not retrieve host while dispatching call " + r2);
        return null;
    L6:
        r3.dispatch(r02);
        return null;
    }

    /* renamed from: $r8$lambda$x-LnTW9-SXkfbULQApW6HLJtW2U, reason: not valid java name */
    public static /* synthetic */ Object m636$r8$lambda$xLnTW9SXkfbULQApW6HLJtW2U(HostDispatcher r0, String r1, String r2, HostCall r3) {
        IInterface r02 = r0.getHost(r1);
        if (r02 != null) goto L7;
        Log.e("CarApp.Dispatch", "Could not retrieve host while dispatching call " + r2);
        return null;
    L7:
        return r3.dispatch(r02);
    }

    public HostDispatcher() {
    }

    public <ServiceT, ReturnT> void dispatch(String r2, String r3, HostCall<ServiceT, ReturnT> r4) {
        RemoteUtils.dispatchCallToHost(r3, new HostDispatcher$$ExternalSyntheticLambda0(this, r2, r3, r4));
    }

    public <ServiceT, ReturnT> ReturnT dispatchForResult(String r2, String r3, HostCall<ServiceT, ReturnT> r4) {
        return (ReturnT) RemoteUtils.dispatchCallToHostForResult(r3, new HostDispatcher$$ExternalSyntheticLambda1(this, r2, r3, r4));
    }

    public IInterface getHost(String r3) {
        if (this.mCarHost != null) goto L6;
        Log.e("CarApp.Dispatch", "Host is not bound when attempting to retrieve host service");
        return null;
    L6:
        r3.getClass();
        char r1 = 65535;
        switch(r3.hashCode()) {
            case -1606703562: goto L30;
            case 96801: goto L26;
            case 98260: goto L22;
            case 1197722116: goto L18;
            case 1569536022: goto L14;
            case 1862666772: goto L10;
            default: goto L33;
        };
    L33:
        switch(r1) {
            case 0: goto L59;
            case 1: goto L54;
            case 2: goto L52;
            case 3: goto L47;
            case 4: goto L42;
            case 5: goto L37;
            default: goto L35;
        };
    L35:
        throw new InvalidParameterException("Invalid host type: ".concat(r3));
    L37:
        if (this.mNavigationHost != null) goto L40;
        this.mNavigationHost = (INavigationHost) RemoteUtils.dispatchCallToHostForResult("getHost(Navigation)", new HostDispatcher$$ExternalSyntheticLambda6(this));
    L40:
        return this.mNavigationHost;
    L42:
        if (this.mPlaybackMediaHost != null) goto L45;
        this.mPlaybackMediaHost = (IMediaPlaybackHost) RemoteUtils.dispatchCallToHostForResult("getHost(Media)", new HostDispatcher$$ExternalSyntheticLambda5(this));
    L45:
        return this.mPlaybackMediaHost;
    L47:
        if (this.mSuggestionHost != null) goto L50;
        this.mSuggestionHost = (ISuggestionHost) RemoteUtils.dispatchCallToHostForResult("getHost(Suggestion)", new HostDispatcher$$ExternalSyntheticLambda4(this));
    L50:
        return this.mSuggestionHost;
    L52:
        return this.mCarHost;
    L54:
        if (this.mAppHost != null) goto L57;
        this.mAppHost = (IAppHost) RemoteUtils.dispatchCallToHostForResult("getHost(App)", new HostDispatcher$$ExternalSyntheticLambda2(this));
    L57:
        return this.mAppHost;
    L59:
        if (this.mConstraintHost != null) goto L62;
        this.mConstraintHost = (IConstraintHost) RemoteUtils.dispatchCallToHostForResult("getHost(Constraints)", new HostDispatcher$$ExternalSyntheticLambda3(this));
    L62:
        return this.mConstraintHost;
    L10:
        if (r3.equals("navigation") == false) goto L33;
        r1 = 5;
        goto L33
    L14:
        if (r3.equals("media_playback") == false) goto L33;
        r1 = 4;
        goto L33
    L18:
        if (r3.equals("suggestion") == false) goto L33;
        r1 = 3;
        goto L33
    L22:
        if (r3.equals("car") == false) goto L33;
        r1 = 2;
        goto L33
    L26:
        if (r3.equals(Common.ASSET_APP) == false) goto L33;
        r1 = 1;
        goto L33
    L30:
        if (r3.equals("constraints") == false) goto L33;
        r1 = 0;
        goto L33
    }

    public void resetHosts() {
        ThreadUtils.checkMainThread();
        this.mCarHost = null;
        this.mAppHost = null;
        this.mNavigationHost = null;
    }

    public void setCarHost(ICarHost r1) {
        ThreadUtils.checkMainThread();
        resetHosts();
        this.mCarHost = r1;
    }
}
