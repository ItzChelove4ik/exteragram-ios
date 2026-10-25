package androidx.car.app.navigation.model;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import androidx.car.app.IOnDoneCallback;
import kotlin.text.Typography;

/* loaded from: classes3.dex */
public interface IPanModeListener extends IInterface {
    public static final String DESCRIPTOR = null;

    public static class Default implements IPanModeListener {
        public Default() {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // androidx.car.app.navigation.model.IPanModeListener
        public void onPanModeChanged(boolean r1, IOnDoneCallback r2) {
        }
    }

    public static abstract class Stub extends Binder implements IPanModeListener {
        static final int TRANSACTION_onPanModeChanged = 2;

        public static class Proxy implements IPanModeListener {
            private IBinder mRemote;

            public Proxy(IBinder r1) {
                this.mRemote = r1;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IPanModeListener.DESCRIPTOR;
            }

            @Override // androidx.car.app.navigation.model.IPanModeListener
            public void onPanModeChanged(boolean r3, IOnDoneCallback r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken(IPanModeListener.DESCRIPTOR);     // Catch: Throwable -> L6
                r0.writeInt(r3 ? 1 : 0);     // Catch: Throwable -> L6
                r0.writeStrongInterface(r4);     // Catch: Throwable -> L6
                this.mRemote.transact(2, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }
        }

        public Stub() {
            attachInterface(this, IPanModeListener.DESCRIPTOR);
        }

        public static IPanModeListener asInterface(IBinder r2) {
            if (r2 != null) goto L5;
            return null;
        L5:
            IInterface r0 = r2.queryLocalInterface(IPanModeListener.DESCRIPTOR);
            if (r0 == null) goto L12;
            if ((r0 instanceof IPanModeListener) == false) goto L12;
            return (IPanModeListener) r0;
        L12:
            return new Proxy(r2);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int r4, Parcel r5, Parcel r6, int r7) {
            String r0 = IPanModeListener.DESCRIPTOR;
            if (r4 < 1) goto L8;
            if (r4 > 16777215) goto L8;
            r5.enforceInterface(r0);
        L8:
            if (r4 != 1598968902) goto L12;
            r6.writeString(r0);
            return true;
        L12:
            if (r4 == 2) goto L16;
            return super.onTransact(r4, r5, r6, r7);
        L16:
            if (r5.readInt() == 0) goto L18;
            boolean r42 = true;
        L19:
            onPanModeChanged(r42, IOnDoneCallback.Stub.asInterface(r5.readStrongBinder()));
            return true;
        L18:
            r42 = false;
            goto L19
        }
    }

    static {
        DESCRIPTOR = "androidx$car$app$navigation$model$IPanModeListener".replace(Typography.dollar, '.');
    }

    void onPanModeChanged(boolean r1, IOnDoneCallback r2);
}
