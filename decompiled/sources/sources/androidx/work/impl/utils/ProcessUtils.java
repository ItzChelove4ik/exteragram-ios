package androidx.work.impl.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import androidx.work.Configuration;
import androidx.work.Logger;
import androidx.work.WorkManager;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u001a\u001d\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u0019\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0001\u001a\u00020\u0000H\u0003¢\u0006\u0004\b\b\u0010\t\"\u0014\u0010\n\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Landroid/content/Context;", "context", "Landroidx/work/Configuration;", "configuration", _UrlKt.FRAGMENT_ENCODE_SET, "isDefaultProcess", "(Landroid/content/Context;Landroidx/work/Configuration;)Z", _UrlKt.FRAGMENT_ENCODE_SET, "getProcessName", "(Landroid/content/Context;)Ljava/lang/String;", "TAG", "Ljava/lang/String;", "work-runtime_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
@JvmName(name = "ProcessUtils")
@SourceDebugExtension({"SMAP\nProcessUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ProcessUtils.kt\nandroidx/work/impl/utils/ProcessUtils\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,73:1\n1#2:74\n*E\n"})
/* loaded from: classes.dex */
public abstract class ProcessUtils {
    private static final String TAG = null;

    static {
        TAG = Logger.tagWithPrefix("ProcessUtils");
    }

    @SuppressLint({"PrivateApi", "DiscouragedPrivateApi"})
    private static final String getProcessName(Context r5) {
        if (Build.VERSION.SDK_INT < 28) goto L26;
        return Api28Impl.INSTANCE.getProcessName();
    L26:
        Method r1 = Class.forName("android.app.ActivityThread", false, WorkManager.class.getClassLoader()).getDeclaredMethod("currentProcessName", null);     // Catch: Throwable -> L11
        r1.setAccessible(true);     // Catch: Throwable -> L11
        Object r12 = r1.invoke(null, null);     // Catch: Throwable -> L11
        if ((r12 instanceof String) == false) goto L13;
        return (String) r12;
    L13:
        int r13 = Process.myPid();
        List<ActivityManager.RunningAppProcessInfo> r52 = ((ActivityManager) r5.getSystemService("activity")).getRunningAppProcesses();
        if (r52 == null) goto L31;
        Iterator<T> r53 = r52.iterator();
    L17:
        if (r53.hasNext() == false) goto L21;
        Object r2 = r53.next();
        if (((ActivityManager.RunningAppProcessInfo) r2).pid != r13) goto L17;
    L22:
        ActivityManager.RunningAppProcessInfo r22 = (ActivityManager.RunningAppProcessInfo) r2;
        if (r22 != null) goto L25;
        return null;
    L25:
        return r22.processName;
    L21:
        r2 = null;
        goto L22
    L31:
        return null;
    L11:
        th = move-exception;
        Logger.get().debug(TAG, "Unable to check ActivityThread for processName", th);
        goto L13
    }

    public static final boolean isDefaultProcess(Context r2, Configuration r3) {
        String r0 = getProcessName(r2);
        String r1 = r3.getDefaultProcessName();
        if (r1 == null) goto L10;
        if (r1.length() == 0) goto L10;
        return Intrinsics.areEqual(r0, r3.getDefaultProcessName());
    L10:
        return Intrinsics.areEqual(r0, r2.getApplicationInfo().processName);
    }
}
