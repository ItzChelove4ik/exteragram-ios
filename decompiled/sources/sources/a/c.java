package a;

import c.g;
import dev.exterahook.runtime.bridge.JniBridgeBindings;
import java.lang.reflect.Member;
import kotlin.Unit;
import okhttp3.internal.url._UrlKt;
import org.lsposed.hiddenapibypass.HiddenApiBypass;

/* loaded from: classes.dex */
public final class c implements a {

    /* renamed from: a, reason: collision with root package name */
    public final g f7a;

    /* renamed from: b, reason: collision with root package name */
    public final b.a f8b;

    /* renamed from: c, reason: collision with root package name */
    public volatile boolean f9c;

    public c(g r1, b.b r2, b.a r3) {
        this.f7a = r1;
        this.f8b = r3;
    }

    public final void a() {
    L17:
        th = move-exception;
        this.f7a.a();
        throw th;
    L3:
        if (this.f9c == true) goto L15;
        monitor-enter(this);     // Catch: Throwable -> L17
    L11:
        th = move-exception;
        throw th;     // Catch: Throwable -> L17
    L7:
        if (this.f9c == false) goto L25;
    L13:
        Unit r0 = Unit.INSTANCE;     // Catch: Throwable -> L11
        monitor-exit(this);     // Catch: Throwable -> L17
        goto L15
    L25:
        HiddenApiBypass.addHiddenApiExemptions(new String[]{_UrlKt.FRAGMENT_ENCODE_SET});     // Catch: Throwable -> L23
    L10:
        this.f9c = true;     // Catch: Throwable -> L11
    L15:
        this.f7a.a();
    }

    @Override // a.a
    public final boolean a(Member r1) {
        a();
        return JniBridgeBindings.unhook0(r1);
    }
}
