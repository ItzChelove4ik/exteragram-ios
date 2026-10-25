package android.support.v4.os;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.os.IResultReceiver;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes3.dex */
public class ResultReceiver implements Parcelable {
    public static final Parcelable.Creator<ResultReceiver> CREATOR = null;
    final Handler mHandler;
    final boolean mLocal;
    IResultReceiver mReceiver;

    /* renamed from: android.support.v4.os.ResultReceiver$1, reason: invalid class name */
    public class AnonymousClass1 implements Parcelable.Creator<ResultReceiver> {
        public AnonymousClass1() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ResultReceiver createFromParcel(Parcel r1) {
            return new ResultReceiver(r1);
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ ResultReceiver createFromParcel(Parcel r1) {
            return createFromParcel(r1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ResultReceiver[] newArray(int r1) {
            return new ResultReceiver[r1];
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ ResultReceiver[] newArray(int r1) {
            return newArray(r1);
        }
    }

    public class MyResultReceiver extends IResultReceiver.Stub {
        final /* synthetic */ ResultReceiver this$0;

        public MyResultReceiver(ResultReceiver r1) {
            this.this$0 = r1;
        }

        @Override // android.support.v4.os.IResultReceiver
        public void send(int r3, Bundle r4) {
            ResultReceiver r2 = this.this$0;
            Handler r0 = r2.mHandler;
            if (r0 == null) goto L6;
            r0.post(new MyRunnable(r2, r3, r4));
            return;
        L6:
            r2.onReceiveResult(r3, r4);
        }
    }

    public class MyRunnable implements Runnable {
        final int mResultCode;
        final Bundle mResultData;
        final /* synthetic */ ResultReceiver this$0;

        public MyRunnable(ResultReceiver r1, int r2, Bundle r3) {
            this.this$0 = r1;
            this.mResultCode = r2;
            this.mResultData = r3;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.onReceiveResult(this.mResultCode, this.mResultData);
        }
    }

    static {
        CREATOR = new AnonymousClass1();
    }

    public ResultReceiver(Parcel r2) {
        this.mLocal = false;
        this.mHandler = null;
        this.mReceiver = IResultReceiver.Stub.asInterface(r2.readStrongBinder());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void onReceiveResult(int r1, Bundle r2) {
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r1, int r2) {
        monitor-enter(this);
    L6:
        th = move-exception;
        throw th;
    L4:
        if (this.mReceiver != null) goto L8;
        this.mReceiver = new MyResultReceiver(this);     // Catch: Throwable -> L6
    L8:
        r1.writeStrongBinder(this.mReceiver.asBinder());     // Catch: Throwable -> L6
        monitor-exit(this);     // Catch: Throwable -> L6
    }
}
