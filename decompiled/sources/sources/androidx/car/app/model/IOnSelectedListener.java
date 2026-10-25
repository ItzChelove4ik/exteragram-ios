package androidx.car.app.model;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import androidx.car.app.IOnDoneCallback;
import kotlin.text.Typography;

/* loaded from: classes3.dex */
public interface IOnSelectedListener extends IInterface {
    public static final String DESCRIPTOR = null;

    public static class Default implements IOnSelectedListener {
        public Default() {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // androidx.car.app.model.IOnSelectedListener
        public void onSelected(int r1, IOnDoneCallback r2) {
        }
    }

    public static abstract class Stub extends Binder implements IOnSelectedListener {
        static final int TRANSACTION_onSelected = 2;

        public static class Proxy implements IOnSelectedListener {
            private IBinder mRemote;

            public Proxy(IBinder r1) {
                this.mRemote = r1;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOnSelectedListener.DESCRIPTOR;
            }

            @Override // androidx.car.app.model.IOnSelectedListener
            public void onSelected(int r3, IOnDoneCallback r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken(IOnSelectedListener.DESCRIPTOR);     // Catch: Throwable -> L6
                r0.writeInt(r3);     // Catch: Throwable -> L6
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
            attachInterface(this, IOnSelectedListener.DESCRIPTOR);
        }

        public static IOnSelectedListener asInterface(IBinder r2) {
            if (r2 != null) goto L5;
            return null;
        L5:
            IInterface r0 = r2.queryLocalInterface(IOnSelectedListener.DESCRIPTOR);
            if (r0 == null) goto L12;
            if ((r0 instanceof IOnSelectedListener) == false) goto L12;
            return (IOnSelectedListener) r0;
        L12:
            return new Proxy(r2);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int r4, Parcel r5, Parcel r6, int r7) {
            String r0 = IOnSelectedListener.DESCRIPTOR;
            if (r4 < 1) goto L8;
            if (r4 > 16777215) goto L8;
            r5.enforceInterface(r0);
        L8:
            if (r4 != 1598968902) goto L12;
            r6.writeString(r0);
            return true;
        L12:
            if (r4 != 2) goto L14;
            onSelected(r5.readInt(), IOnDoneCallback.Stub.asInterface(r5.readStrongBinder()));
            return true;
        L14:
            return super.onTransact(r4, r5, r6, r7);
        }
    }

    static {
        DESCRIPTOR = "androidx$car$app$model$IOnSelectedListener".replace(Typography.dollar, '.');
    }

    void onSelected(int r1, IOnDoneCallback r2);
}
