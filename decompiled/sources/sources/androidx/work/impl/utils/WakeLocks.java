package androidx.work.impl.utils;

import android.content.Context;
import android.os.PowerManager;
import androidx.work.Logger;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a#\u0010\u0006\u001a\u00060\u0004R\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0006\u0010\u0007\"\u0014\u0010\b\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Landroid/content/Context;", "context", _UrlKt.FRAGMENT_ENCODE_SET, "tag", "Landroid/os/PowerManager$WakeLock;", "Landroid/os/PowerManager;", "newWakeLock", "(Landroid/content/Context;Ljava/lang/String;)Landroid/os/PowerManager$WakeLock;", "TAG", "Ljava/lang/String;", "work-runtime_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
@JvmName(name = "WakeLocks")
@SourceDebugExtension({"SMAP\nWakeLocks.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WakeLocks.kt\nandroidx/work/impl/utils/WakeLocks\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,71:1\n1#2:72\n216#3,2:73\n*S KotlinDebug\n*F\n+ 1 WakeLocks.kt\nandroidx/work/impl/utils/WakeLocks\n*L\n63#1:73,2\n*E\n"})
/* loaded from: classes4.dex */
public abstract class WakeLocks {
    private static final String TAG = null;

    static {
        TAG = Logger.tagWithPrefix("WakeLocks");
    }

    public static final PowerManager.WakeLock newWakeLock(Context r2, String r3) {
        String r32 = "WorkManager: " + r3;
        PowerManager.WakeLock r22 = ((PowerManager) r2.getApplicationContext().getSystemService("power")).newWakeLock(1, r32);
        WakeLocksHolder r0 = WakeLocksHolder.INSTANCE;
        monitor-enter(r0);
        String r33 = r0.getWakeLocks().put(r22, r32);     // Catch: Throwable -> L7
        monitor-exit(r0);
        return r22;
    L7:
        th = move-exception;
        throw th;
    }
}
