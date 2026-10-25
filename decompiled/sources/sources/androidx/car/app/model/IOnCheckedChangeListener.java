package androidx.car.app.model;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import androidx.car.app.IOnDoneCallback;
import kotlin.text.Typography;

/* loaded from: classes3.dex */
public interface IOnCheckedChangeListener extends IInterface {
    public static final String DESCRIPTOR = null;

    public static class Default implements IOnCheckedChangeListener {
        public Default() {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // androidx.car.app.model.IOnCheckedChangeListener
        public void onCheckedChange(boolean r1, IOnDoneCallback r2) {
        }
    }

    public static abstract class Stub extends Binder implements IOnCheckedChangeListener {
        static final int TRANSACTION_onCheckedChange = 2;

        public static class Proxy implements IOnCheckedChangeListener {
            private IBinder mRemote;

            public Proxy(IBinder r1) {
                this.mRemote = r1;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOnCheckedChangeListener.DESCRIPTOR;
            }

            @Override // androidx.car.app.model.IOnCheckedChangeListener
            public void onCheckedChange(boolean r3, IOnDoneCallback r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken(IOnCheckedChangeListener.DESCRIPTOR);     // Catch: Throwable -> L6
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
            attachInterface(this, IOnCheckedChangeListener.DESCRIPTOR);
        }

        public static IOnCheckedChangeListener asInterface(IBinder r2) {
            if (r2 != null) goto L5;
            return null;
        L5:
            IInterface r0 = r2.queryLocalInterface(IOnCheckedChangeListener.DESCRIPTOR);
            if (r0 == null) goto L12;
            if ((r0 instanceof IOnCheckedChangeListener) == false) goto L12;
            return (IOnCheckedChangeListener) r0;
        L12:
            return new Proxy(r2);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int r4, Parcel r5, Parcel r6, int r7) {
            String r0 = IOnCheckedChangeListener.DESCRIPTOR;
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
            onCheckedChange(r42, IOnDoneCallback.Stub.asInterface(r5.readStrongBinder()));
            return true;
        L18:
            r42 = false;
            goto L19
        }
    }

    static {
        DESCRIPTOR = "androidx$car$app$model$IOnCheckedChangeListener".replace(Typography.dollar, '.');
    }

    void onCheckedChange(boolean r1, IOnDoneCallback r2);
}
