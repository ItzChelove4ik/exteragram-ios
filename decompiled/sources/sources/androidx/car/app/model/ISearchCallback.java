package androidx.car.app.model;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import androidx.car.app.IOnDoneCallback;
import kotlin.text.Typography;

/* loaded from: classes3.dex */
public interface ISearchCallback extends IInterface {
    public static final String DESCRIPTOR = null;

    public static class Default implements ISearchCallback {
        public Default() {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // androidx.car.app.model.ISearchCallback
        public void onSearchSubmitted(String r1, IOnDoneCallback r2) {
        }

        @Override // androidx.car.app.model.ISearchCallback
        public void onSearchTextChanged(String r1, IOnDoneCallback r2) {
        }
    }

    public static abstract class Stub extends Binder implements ISearchCallback {
        static final int TRANSACTION_onSearchSubmitted = 3;
        static final int TRANSACTION_onSearchTextChanged = 2;

        public static class Proxy implements ISearchCallback {
            private IBinder mRemote;

            public Proxy(IBinder r1) {
                this.mRemote = r1;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ISearchCallback.DESCRIPTOR;
            }

            @Override // androidx.car.app.model.ISearchCallback
            public void onSearchSubmitted(String r3, IOnDoneCallback r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken(ISearchCallback.DESCRIPTOR);     // Catch: Throwable -> L6
                r0.writeString(r3);     // Catch: Throwable -> L6
                r0.writeStrongInterface(r4);     // Catch: Throwable -> L6
                this.mRemote.transact(3, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            @Override // androidx.car.app.model.ISearchCallback
            public void onSearchTextChanged(String r3, IOnDoneCallback r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken(ISearchCallback.DESCRIPTOR);     // Catch: Throwable -> L6
                r0.writeString(r3);     // Catch: Throwable -> L6
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
            attachInterface(this, ISearchCallback.DESCRIPTOR);
        }

        public static ISearchCallback asInterface(IBinder r2) {
            if (r2 != null) goto L5;
            return null;
        L5:
            IInterface r0 = r2.queryLocalInterface(ISearchCallback.DESCRIPTOR);
            if (r0 == null) goto L12;
            if ((r0 instanceof ISearchCallback) == false) goto L12;
            return (ISearchCallback) r0;
        L12:
            return new Proxy(r2);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int r4, Parcel r5, Parcel r6, int r7) {
            String r0 = ISearchCallback.DESCRIPTOR;
            if (r4 < 1) goto L8;
            if (r4 > 16777215) goto L8;
            r5.enforceInterface(r0);
        L8:
            if (r4 != 1598968902) goto L12;
            r6.writeString(r0);
            return true;
        L12:
            if (r4 != 2) goto L14;
            onSearchTextChanged(r5.readString(), IOnDoneCallback.Stub.asInterface(r5.readStrongBinder()));
        L19:
            return true;
        L14:
            if (r4 != 3) goto L16;
            onSearchSubmitted(r5.readString(), IOnDoneCallback.Stub.asInterface(r5.readStrongBinder()));
            goto L19
        L16:
            return super.onTransact(r4, r5, r6, r7);
        }
    }

    static {
        DESCRIPTOR = "androidx$car$app$model$ISearchCallback".replace(Typography.dollar, '.');
    }

    void onSearchSubmitted(String r1, IOnDoneCallback r2);

    void onSearchTextChanged(String r1, IOnDoneCallback r2);
}
