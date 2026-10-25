package androidx.car.app.hardware;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.car.app.hardware.ICarHardwareResult;
import androidx.car.app.serialization.Bundleable;
import kotlin.text.Typography;

/* loaded from: classes3.dex */
public interface ICarHardwareHost extends IInterface {
    public static final String DESCRIPTOR = null;

    public static class Default implements ICarHardwareHost {
        public Default() {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // androidx.car.app.hardware.ICarHardwareHost
        public void getCarHardwareResult(int r1, Bundleable r2, ICarHardwareResult r3) {
        }

        @Override // androidx.car.app.hardware.ICarHardwareHost
        public void subscribeCarHardwareResult(int r1, Bundleable r2, ICarHardwareResult r3) {
        }

        @Override // androidx.car.app.hardware.ICarHardwareHost
        public void unsubscribeCarHardwareResult(int r1, Bundleable r2) {
        }
    }

    public static abstract class Stub extends Binder implements ICarHardwareHost {
        static final int TRANSACTION_getCarHardwareResult = 2;
        static final int TRANSACTION_subscribeCarHardwareResult = 3;
        static final int TRANSACTION_unsubscribeCarHardwareResult = 4;

        public static class Proxy implements ICarHardwareHost {
            private IBinder mRemote;

            public Proxy(IBinder r1) {
                this.mRemote = r1;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // androidx.car.app.hardware.ICarHardwareHost
            public void getCarHardwareResult(int r4, Bundleable r5, ICarHardwareResult r6) {
                Parcel r0 = Parcel.obtain();
                Parcel r1 = Parcel.obtain();
                r0.writeInterfaceToken(ICarHardwareHost.DESCRIPTOR);     // Catch: Throwable -> L6
                r0.writeInt(r4);     // Catch: Throwable -> L6
                _Parcel.access$100(r0, r5, 0);     // Catch: Throwable -> L6
                r0.writeStrongInterface(r6);     // Catch: Throwable -> L6
                this.mRemote.transact(2, r0, r1, 0);     // Catch: Throwable -> L6
                r1.readException();     // Catch: Throwable -> L6
                r1.recycle();
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r1.recycle();
                r0.recycle();
                throw th;
            }

            public String getInterfaceDescriptor() {
                return ICarHardwareHost.DESCRIPTOR;
            }

            @Override // androidx.car.app.hardware.ICarHardwareHost
            public void subscribeCarHardwareResult(int r4, Bundleable r5, ICarHardwareResult r6) {
                Parcel r0 = Parcel.obtain();
                Parcel r1 = Parcel.obtain();
                r0.writeInterfaceToken(ICarHardwareHost.DESCRIPTOR);     // Catch: Throwable -> L6
                r0.writeInt(r4);     // Catch: Throwable -> L6
                _Parcel.access$100(r0, r5, 0);     // Catch: Throwable -> L6
                r0.writeStrongInterface(r6);     // Catch: Throwable -> L6
                this.mRemote.transact(3, r0, r1, 0);     // Catch: Throwable -> L6
                r1.readException();     // Catch: Throwable -> L6
                r1.recycle();
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r1.recycle();
                r0.recycle();
                throw th;
            }

            @Override // androidx.car.app.hardware.ICarHardwareHost
            public void unsubscribeCarHardwareResult(int r4, Bundleable r5) {
                Parcel r0 = Parcel.obtain();
                Parcel r1 = Parcel.obtain();
                r0.writeInterfaceToken(ICarHardwareHost.DESCRIPTOR);     // Catch: Throwable -> L6
                r0.writeInt(r4);     // Catch: Throwable -> L6
                _Parcel.access$100(r0, r5, 0);     // Catch: Throwable -> L6
                this.mRemote.transact(4, r0, r1, 0);     // Catch: Throwable -> L6
                r1.readException();     // Catch: Throwable -> L6
                r1.recycle();
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r1.recycle();
                r0.recycle();
                throw th;
            }
        }

        public Stub() {
            attachInterface(this, ICarHardwareHost.DESCRIPTOR);
        }

        public static ICarHardwareHost asInterface(IBinder r2) {
            if (r2 != null) goto L5;
            return null;
        L5:
            IInterface r0 = r2.queryLocalInterface(ICarHardwareHost.DESCRIPTOR);
            if (r0 == null) goto L12;
            if ((r0 instanceof ICarHardwareHost) == false) goto L12;
            return (ICarHardwareHost) r0;
        L12:
            return new Proxy(r2);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int r4, Parcel r5, Parcel r6, int r7) {
            String r0 = ICarHardwareHost.DESCRIPTOR;
            if (r4 < 1) goto L8;
            if (r4 > 16777215) goto L8;
            r5.enforceInterface(r0);
        L8:
            if (r4 != 1598968902) goto L12;
            r6.writeString(r0);
            return true;
        L12:
            if (r4 != 2) goto L14;
            getCarHardwareResult(r5.readInt(), (Bundleable) _Parcel.access$000(r5, Bundleable.CREATOR), ICarHardwareResult.Stub.asInterface(r5.readStrongBinder()));
            r6.writeNoException();
        L22:
            return true;
        L14:
            if (r4 != 3) goto L16;
            subscribeCarHardwareResult(r5.readInt(), (Bundleable) _Parcel.access$000(r5, Bundleable.CREATOR), ICarHardwareResult.Stub.asInterface(r5.readStrongBinder()));
            r6.writeNoException();
            goto L22
        L16:
            if (r4 != 4) goto L18;
            unsubscribeCarHardwareResult(r5.readInt(), (Bundleable) _Parcel.access$000(r5, Bundleable.CREATOR));
            r6.writeNoException();
            goto L22
        L18:
            return super.onTransact(r4, r5, r6, r7);
        }
    }

    public static class _Parcel {
        public static /* synthetic */ Object access$000(Parcel r0, Parcelable.Creator r1) {
            return readTypedObject(r0, r1);
        }

        public static /* synthetic */ void access$100(Parcel r0, Parcelable r1, int r2) {
            writeTypedObject(r0, r1, r2);
        }

        private static <T> T readTypedObject(Parcel r1, Parcelable.Creator<T> r2) {
            if (r1.readInt() != 0) goto L5;
            return null;
        L5:
            return r2.createFromParcel(r1);
        }

        private static <T extends Parcelable> void writeTypedObject(Parcel r1, T r2, int r3) {
            if (r2 == null) goto L5;
            r1.writeInt(1);
            r2.writeToParcel(r1, r3);
            return;
        L5:
            r1.writeInt(0);
        }
    }

    static {
        DESCRIPTOR = "androidx$car$app$hardware$ICarHardwareHost".replace(Typography.dollar, '.');
    }

    void getCarHardwareResult(int r1, Bundleable r2, ICarHardwareResult r3);

    void subscribeCarHardwareResult(int r1, Bundleable r2, ICarHardwareResult r3);

    void unsubscribeCarHardwareResult(int r1, Bundleable r2);
}
