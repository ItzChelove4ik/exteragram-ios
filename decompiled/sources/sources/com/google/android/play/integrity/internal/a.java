package com.google.android.play.integrity.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes5.dex */
public abstract class a implements IInterface {

    /* renamed from: a, reason: collision with root package name */
    private final IBinder f99a;

    /* renamed from: b, reason: collision with root package name */
    private final String f100b;

    public a(IBinder r1, String r2) {
        this.f99a = r1;
        this.f100b = r2;
    }

    public final Parcel a() {
        Parcel r0 = Parcel.obtain();
        r0.writeInterfaceToken(this.f100b);
        return r0;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.f99a;
    }

    public final void b(int r3, Parcel r4) {
        this.f99a.transact(r3, r4, null, 1);     // Catch: Throwable -> L5
        r4.recycle();
        return;
    L5:
        th = move-exception;
        r4.recycle();
        throw th;
    }
}
