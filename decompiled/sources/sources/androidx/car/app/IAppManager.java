package androidx.car.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import androidx.car.app.IOnDoneCallback;
import kotlin.text.Typography;

/* loaded from: classes3.dex */
public interface IAppManager extends IInterface {
    public static final String DESCRIPTOR = null;

    public static class Default implements IAppManager {
        public Default() {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // androidx.car.app.IAppManager
        public void getTemplate(IOnDoneCallback r1) {
        }

        @Override // androidx.car.app.IAppManager
        public void onBackPressed(IOnDoneCallback r1) {
        }

        @Override // androidx.car.app.IAppManager
        public void startLocationUpdates(IOnDoneCallback r1) {
        }

        @Override // androidx.car.app.IAppManager
        public void stopLocationUpdates(IOnDoneCallback r1) {
        }
    }

    public static abstract class Stub extends Binder implements IAppManager {
        static final int TRANSACTION_getTemplate = 2;
        static final int TRANSACTION_onBackPressed = 3;
        static final int TRANSACTION_startLocationUpdates = 4;
        static final int TRANSACTION_stopLocationUpdates = 5;

        public static class Proxy implements IAppManager {
            private IBinder mRemote;

            public Proxy(IBinder r1) {
                this.mRemote = r1;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IAppManager.DESCRIPTOR;
            }

            @Override // androidx.car.app.IAppManager
            public void getTemplate(IOnDoneCallback r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken(IAppManager.DESCRIPTOR);     // Catch: Throwable -> L6
                r0.writeStrongInterface(r4);     // Catch: Throwable -> L6
                this.mRemote.transact(2, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            @Override // androidx.car.app.IAppManager
            public void onBackPressed(IOnDoneCallback r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken(IAppManager.DESCRIPTOR);     // Catch: Throwable -> L6
                r0.writeStrongInterface(r4);     // Catch: Throwable -> L6
                this.mRemote.transact(3, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            @Override // androidx.car.app.IAppManager
            public void startLocationUpdates(IOnDoneCallback r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken(IAppManager.DESCRIPTOR);     // Catch: Throwable -> L6
                r0.writeStrongInterface(r4);     // Catch: Throwable -> L6
                this.mRemote.transact(4, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            @Override // androidx.car.app.IAppManager
            public void stopLocationUpdates(IOnDoneCallback r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken(IAppManager.DESCRIPTOR);     // Catch: Throwable -> L6
                r0.writeStrongInterface(r4);     // Catch: Throwable -> L6
                this.mRemote.transact(5, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }
        }

        public Stub() {
            attachInterface(this, IAppManager.DESCRIPTOR);
        }

        public static IAppManager asInterface(IBinder r2) {
            if (r2 != null) goto L5;
            return null;
        L5:
            IInterface r0 = r2.queryLocalInterface(IAppManager.DESCRIPTOR);
            if (r0 == null) goto L12;
            if ((r0 instanceof IAppManager) == false) goto L12;
            return (IAppManager) r0;
        L12:
            return new Proxy(r2);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int r4, Parcel r5, Parcel r6, int r7) {
            String r0 = IAppManager.DESCRIPTOR;
            if (r4 < 1) goto L8;
            if (r4 > 16777215) goto L8;
            r5.enforceInterface(r0);
        L8:
            if (r4 != 1598968902) goto L12;
            r6.writeString(r0);
            return true;
        L12:
            if (r4 != 2) goto L14;
            getTemplate(IOnDoneCallback.Stub.asInterface(r5.readStrongBinder()));
        L25:
            return true;
        L14:
            if (r4 != 3) goto L16;
            onBackPressed(IOnDoneCallback.Stub.asInterface(r5.readStrongBinder()));
            goto L25
        L16:
            if (r4 != 4) goto L18;
            startLocationUpdates(IOnDoneCallback.Stub.asInterface(r5.readStrongBinder()));
            goto L25
        L18:
            if (r4 != 5) goto L20;
            stopLocationUpdates(IOnDoneCallback.Stub.asInterface(r5.readStrongBinder()));
            goto L25
        L20:
            return super.onTransact(r4, r5, r6, r7);
        }
    }

    static {
        DESCRIPTOR = "androidx$car$app$IAppManager".replace(Typography.dollar, '.');
    }

    void getTemplate(IOnDoneCallback r1);

    void onBackPressed(IOnDoneCallback r1);

    void startLocationUpdates(IOnDoneCallback r1);

    void stopLocationUpdates(IOnDoneCallback r1);
}
