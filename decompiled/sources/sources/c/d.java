package c;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import java.lang.reflect.Member;
import java.util.HashMap;

/* loaded from: classes.dex */
public final class d {

    /* renamed from: a, reason: collision with root package name */
    public final HashMap f13a;

    public d() {
        this.f13a = new HashMap();
    }

    public final XposedBridge.HookInfo a(Member r3, XC_MethodHook r4, b r5) {
        XposedBridge.HookInfo r0 = new XposedBridge.HookInfo(r3);
        r0.getCallbacks().add(r4);
        this.f13a.put(r3, r0);
        Member r42 = r5.a(r0, r3);     // Catch: Throwable -> L7
        if (r42 == null) goto L10;
        r0.setBackup(r42);     // Catch: Throwable -> L7
        return r0;
    L10:
        throw new IllegalStateException("Failed to hook method");     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        this.f13a.remove(r3);
        r0.markInstallFailed();
        throw th;
    }
}
