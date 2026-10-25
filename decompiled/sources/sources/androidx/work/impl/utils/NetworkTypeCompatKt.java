package androidx.work.impl.utils;

import android.net.NetworkRequest;
import android.os.Build;
import androidx.work.NetworkType;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0007¨\u0006\u0003"}, d2 = {"toNetworkRequest", "Landroid/net/NetworkRequest;", "Landroidx/work/NetworkType;", "work-runtime_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public abstract class NetworkTypeCompatKt {

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[NetworkType.values().length];
            r0[NetworkType.METERED.ordinal()] = 1;     // Catch: NoSuchFieldError -> L8
        L11:
            r0[NetworkType.UNMETERED.ordinal()] = 2;     // Catch: NoSuchFieldError -> L9
        L15:
            r0[NetworkType.NOT_ROAMING.ordinal()] = 3;     // Catch: NoSuchFieldError -> L10
        L6:
            $EnumSwitchMapping$0 = r0;
        }
    }

    public static final NetworkRequest toNetworkRequest(NetworkType r3) {
        if (r3 != NetworkType.NOT_REQUIRED) goto L6;
        return null;
    L6:
        NetworkRequest.Builder r0 = new NetworkRequest.Builder().addCapability(12).addCapability(16).removeCapability(15).removeCapability(13);
        if (Build.VERSION.SDK_INT >= 30) goto L9;
    L12:
        int r32 = WhenMappings.$EnumSwitchMapping$0[r3.ordinal()];
        if (r32 != 1) goto L15;
        r0 = r0.addTransportType(0);
    L23:
        return r0.build();
    L15:
        if (r32 != 2) goto L17;
        r0 = r0.addCapability(11);
        goto L23
    L17:
        if (r32 != 3) goto L23;
        r0 = r0.addCapability(18);
        goto L23
    L9:
        if (r3 != NetworkType.TEMPORARILY_UNMETERED) goto L12;
        return r0.addCapability(25).build();
    }
}
