package com.google.android.play.integrity.internal;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes5.dex */
public final class ae {

    /* renamed from: a, reason: collision with root package name */
    private static final Map f105a = null;

    /* renamed from: b, reason: collision with root package name */
    private final Context f106b;

    /* renamed from: c, reason: collision with root package name */
    private final s f107c;
    private final String d;
    private final List e;
    private final Set f;
    private final Object g;
    private boolean h;
    private final Intent i;
    private final z j;
    private final WeakReference k;
    private final IBinder.DeathRecipient l;
    private final AtomicInteger m;
    private ServiceConnection n;
    private IInterface o;

    static {
        f105a = new HashMap();
    }

    public ae(Context r2, s r3, String r4, Intent r5, z r6, y r7) {
        this.e = new ArrayList();
        this.f = new HashSet();
        this.g = new Object();
        this.l = new u(this);
        this.m = new AtomicInteger(0);
        this.f106b = r2;
        this.f107c = r3;
        this.d = r4;
        this.i = r5;
        this.j = r6;
        this.k = new WeakReference(null);
    }

    public static /* bridge */ /* synthetic */ Context a(ae r0) {
        return r0.f106b;
    }

    public static /* bridge */ /* synthetic */ ServiceConnection b(ae r0) {
        return r0.n;
    }

    public static /* bridge */ /* synthetic */ IInterface d(ae r0) {
        return r0.o;
    }

    public static /* bridge */ /* synthetic */ s f(ae r0) {
        return r0.f107c;
    }

    public static /* bridge */ /* synthetic */ z g(ae r0) {
        return r0.j;
    }

    public static /* bridge */ /* synthetic */ Object h(ae r0) {
        return r0.g;
    }

    public static /* bridge */ /* synthetic */ List i(ae r0) {
        return r0.e;
    }

    public static /* bridge */ /* synthetic */ AtomicInteger j(ae r0) {
        return r0.m;
    }

    public static /* synthetic */ void k(ae r3) {
        r3.f107c.d("reportBinderDeath", new Object[0]);
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r3.k.get());
        r3.f107c.d("%s : Binder has died.", new Object[]{r3.d});
        Iterator r0 = r3.e.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        ((t) r0.next()).a(r3.w());
        goto L4
    L6:
        r3.e.clear();
        Object r02 = r3.g;
        monitor-enter(r02);
        r3.x();     // Catch: Throwable -> L11
        monitor-exit(r02);     // Catch: Throwable -> L11
        return;
    L11:
        th = move-exception;
        throw th;
    }

    public static /* bridge */ /* synthetic */ void l(ae r0, ServiceConnection r1) {
        r0.n = null;
    }

    public static /* bridge */ /* synthetic */ void m(ae r0, boolean r1) {
        r0.h = false;
    }

    public static /* bridge */ /* synthetic */ void n(ae r0, IInterface r1) {
        r0.o = r1;
    }

    public static /* bridge */ /* synthetic */ void o(ae r2, TaskCompletionSource r3) {
        r2.f.add(r3);
        r3.getTask().addOnCompleteListener(new v(r2, r3));
    }

    public static /* bridge */ /* synthetic */ void p(ae r0) {
        r0.x();
    }

    public static /* bridge */ /* synthetic */ void q(ae r4, t r5) {
        if (r4.o != null) goto L15;
        if (r4.h == true) goto L15;
        r4.f107c.d("Initiate binding to the service.", new Object[0]);
        r4.e.add(r5);
        ad r52 = new ad(r4, null);
        r4.n = r52;
        r4.h = true;
        if (r4.f106b.bindService(r4.i, r52, 1) == true) goto L21;
        r4.f107c.d("Failed to bind to the service.", new Object[0]);
        r4.h = false;
        Iterator r53 = r4.e.iterator();
    L10:
        if (r53.hasNext() == false) goto L12;
        ((t) r53.next()).a(new af());
        goto L10
    L12:
        r4.e.clear();
        return;
    L21:
        return;
    L15:
        if (r4.h == false) goto L18;
        r4.f107c.d("Waiting to bind to the service.", new Object[0]);
        r4.e.add(r5);
        return;
    L18:
        r5.run();
    }

    public static /* bridge */ /* synthetic */ void r(ae r4) {
        r4.f107c.d("linkToDeath", new Object[0]);
        r4.o.asBinder().linkToDeath(r4.l, 0);     // Catch: RemoteException -> L5
        return;
    L5:
        e = move-exception;
        r4.f107c.c(e, "linkToDeath failed", new Object[0]);
    }

    public static /* bridge */ /* synthetic */ void s(ae r4) {
        r4.f107c.d("unlinkToDeath", new Object[0]);
        r4.o.asBinder().unlinkToDeath(r4.l, 0);
    }

    private final RemoteException w() {
        return new RemoteException(String.valueOf(this.d).concat(" : Binder has died."));
    }

    private final void x() {
        Iterator r0 = this.f.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        ((TaskCompletionSource) r0.next()).trySetException(w());
        goto L4
    L6:
        this.f.clear();
    }

    public final Handler c() {
        Map r0 = f105a;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (r0.containsKey(this.d) == true) goto L9;
        HandlerThread r1 = new HandlerThread(this.d, 10);     // Catch: Throwable -> L7
        r1.start();     // Catch: Throwable -> L7
        r0.put(this.d, new Handler(r1.getLooper()));     // Catch: Throwable -> L7
    L9:
        Handler r4 = (Handler) r0.get(this.d);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r4;
    }

    public final IInterface e() {
        return this.o;
    }

    public final void t(t r3, TaskCompletionSource r4) {
        w r0 = new w(this, r3.c(), r4, r3);
        c().post(r0);
    }

    public final /* synthetic */ void u(TaskCompletionSource r1, Task r2) {
        Object r22 = this.g;
        monitor-enter(r22);
        this.f.remove(r1);     // Catch: Throwable -> L7
        monitor-exit(r22);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public final void v(TaskCompletionSource r3) {
        Object r0 = this.g;
        monitor-enter(r0);
        this.f.remove(r3);     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        x r32 = new x(this);
        c().post(r32);
        return;
    L8:
        th = move-exception;
        throw th;
    }
}
