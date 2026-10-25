package com.google.android.play.core.integrity;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.core.integrity.StandardIntegrityManager;
import com.google.android.play.integrity.internal.ae;
import com.google.android.play.integrity.internal.s;
import java.util.ArrayList;

/* loaded from: classes5.dex */
final class bn {

    /* renamed from: a, reason: collision with root package name */
    final ae f68a;

    /* renamed from: b, reason: collision with root package name */
    private final s f69b;

    /* renamed from: c, reason: collision with root package name */
    private final String f70c;
    private final TaskCompletionSource d;
    private final at e;
    private final k f;

    public bn(Context r10, s r11, at r12, k r13) {
        TaskCompletionSource r0 = new TaskCompletionSource();
        this.d = r0;
        this.f70c = r10.getPackageName();
        this.f69b = r11;
        this.e = r12;
        this.f = r13;
        ae r2 = new ae(r10, r11, "ExpressIntegrityService", bo.f71a, new bd(), null);
        this.f68a = r2;
        be r102 = new be(this, r0, r10);
        r2.c().post(r102);
    }

    public static /* bridge */ /* synthetic */ Bundle a(bn r1, StandardIntegrityManager.StandardIntegrityTokenRequest r2, long r3, long r5, int r7) {
        Bundle r72 = new Bundle();
        r72.putString("package.name", r1.f70c);
        r72.putLong("cloud.prj", r3);
        r72.putString("nonce", r2.requestHash());
        r72.putLong("warm.up.sid", r5);
        r72.putInt("playcore.integrity.version.major", 1);
        r72.putInt("playcore.integrity.version.minor", 4);
        r72.putInt("playcore.integrity.version.patch", 0);
        r72.putInt("webview.request.mode", 0);
        r72.putIntegerArrayList("request.verdict.opt.out", new ArrayList(r2.verdictOptOut()));
        ArrayList r12 = new ArrayList();
        com.google.android.play.integrity.internal.d.b(5, r12);
        r72.putParcelableArrayList("event_timestamps", new ArrayList(com.google.android.play.integrity.internal.d.a(r12)));
        return r72;
    }

    public static /* bridge */ /* synthetic */ Bundle b(bn r1, long r2, int r4) {
        Bundle r42 = new Bundle();
        r42.putString("package.name", r1.f70c);
        r42.putLong("cloud.prj", r2);
        r42.putInt("playcore.integrity.version.major", 1);
        r42.putInt("playcore.integrity.version.minor", 4);
        r42.putInt("playcore.integrity.version.patch", 0);
        r42.putInt("webview.request.mode", 0);
        ArrayList r12 = new ArrayList();
        com.google.android.play.integrity.internal.d.b(4, r12);
        r42.putParcelableArrayList("event_timestamps", new ArrayList(com.google.android.play.integrity.internal.d.a(r12)));
        return r42;
    }

    public static /* bridge */ /* synthetic */ TaskCompletionSource f(bn r0) {
        return r0.d;
    }

    public static /* bridge */ /* synthetic */ k g(bn r0) {
        return r0.f;
    }

    public static /* bridge */ /* synthetic */ s i(bn r0) {
        return r0.f69b;
    }

    public static /* bridge */ /* synthetic */ String j(bn r0) {
        return r0.f70c;
    }

    public static /* bridge */ /* synthetic */ boolean k(bn r0, int r1) {
        if (r0.d.getTask().isSuccessful() == true) goto L5;
        return false;
    L5:
        if (((Integer) r0.d.getTask().getResult()).intValue() >= 83420000) goto L10;
        return true;
    L10:
        return false;
    }

    public static /* bridge */ /* synthetic */ boolean l(bn r1) {
        if (r1.d.getTask().isSuccessful() == true) goto L5;
        return false;
    L5:
        if (((Integer) r1.d.getTask().getResult()).intValue() != 0) goto L10;
        return true;
    L10:
        return false;
    }

    public final Task d(StandardIntegrityManager.StandardIntegrityTokenRequest r11, long r12, long r14, int r16) {
        Object[] r0 = {Long.valueOf(r14)};
        this.f69b.d("requestExpressIntegrityToken(%s)", r0);
        TaskCompletionSource r2 = new TaskCompletionSource();
        bg r02 = new bg(this, r2, 0, r11, r12, r14, r2);
        this.f68a.t(r02, r2);
        return r2.getTask();
    }

    public final Task e(long r10, int r12) {
        Object[] r122 = {Long.valueOf(r10)};
        this.f69b.d("warmUpIntegrityToken(%s)", r122);
        TaskCompletionSource r4 = new TaskCompletionSource();
        bf r2 = new bf(this, r4, 0, r10, r4);
        this.f68a.t(r2, r4);
        return r4.getTask();
    }
}
