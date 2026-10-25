package androidx.car.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import kotlin.text.Typography;

/* loaded from: classes3.dex */
public interface IOnRequestPermissionsListener extends IInterface {
    public static final String DESCRIPTOR = null;

    public static class Default implements IOnRequestPermissionsListener {
        public Default() {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // androidx.car.app.IOnRequestPermissionsListener
        public void onRequestPermissionsResult(String[] r1, String[] r2) {
        }
    }

    public static abstract class Stub extends Binder implements IOnRequestPermissionsListener {
        static final int TRANSACTION_onRequestPermissionsResult = 2;

        public static class Proxy implements IOnRequestPermissionsListener {
            private IBinder mRemote;

            public Proxy(IBinder r1) {
                this.mRemote = r1;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOnRequestPermissionsListener.DESCRIPTOR;
            }

            @Override // androidx.car.app.IOnRequestPermissionsListener
            public void onRequestPermissionsResult(String[] r4, String[] r5) {
                Parcel r0 = Parcel.obtain();
                Parcel r1 = Parcel.obtain();
                r0.writeInterfaceToken(IOnRequestPermissionsListener.DESCRIPTOR);     // Catch: Throwable -> L6
                r0.writeStringArray(r4);     // Catch: Throwable -> L6
                r0.writeStringArray(r5);     // Catch: Throwable -> L6
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
        }

        public Stub() {
            attachInterface(this, IOnRequestPermissionsListener.DESCRIPTOR);
        }

        public static IOnRequestPermissionsListener asInterface(IBinder r2) {
            if (r2 != null) goto L5;
            return null;
        L5:
            IInterface r0 = r2.queryLocalInterface(IOnRequestPermissionsListener.DESCRIPTOR);
            if (r0 == null) goto L12;
            if ((r0 instanceof IOnRequestPermissionsListener) == false) goto L12;
            return (IOnRequestPermissionsListener) r0;
        L12:
            return new Proxy(r2);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int r4, Parcel r5, Parcel r6, int r7) {
            String r0 = IOnRequestPermissionsListener.DESCRIPTOR;
            if (r4 < 1) goto L8;
            if (r4 > 16777215) goto L8;
            r5.enforceInterface(r0);
        L8:
            if (r4 != 1598968902) goto L12;
            r6.writeString(r0);
            return true;
        L12:
            if (r4 != 2) goto L14;
            onRequestPermissionsResult(r5.createStringArray(), r5.createStringArray());
            r6.writeNoException();
            return true;
        L14:
            return super.onTransact(r4, r5, r6, r7);
        }
    }

    static {
        DESCRIPTOR = "androidx$car$app$IOnRequestPermissionsListener".replace(Typography.dollar, '.');
    }

    void onRequestPermissionsResult(String[] r1, String[] r2);
}
