package androidx.car.app;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import kotlin.text.Typography;

/* loaded from: classes3.dex */
public interface IStartCarApp extends IInterface {
    public static final String DESCRIPTOR = null;

    public static class Default implements IStartCarApp {
        public Default() {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // androidx.car.app.IStartCarApp
        public void startCarApp(Intent r1) {
        }
    }

    public static abstract class Stub extends Binder implements IStartCarApp {
        static final int TRANSACTION_startCarApp = 2;

        public static class Proxy implements IStartCarApp {
            private IBinder mRemote;

            public Proxy(IBinder r1) {
                this.mRemote = r1;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IStartCarApp.DESCRIPTOR;
            }

            @Override // androidx.car.app.IStartCarApp
            public void startCarApp(Intent r4) {
                Parcel r0 = Parcel.obtain();
                Parcel r1 = Parcel.obtain();
                r0.writeInterfaceToken(IStartCarApp.DESCRIPTOR);     // Catch: Throwable -> L6
                _Parcel.access$100(r0, r4, 0);     // Catch: Throwable -> L6
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
            attachInterface(this, IStartCarApp.DESCRIPTOR);
        }

        public static IStartCarApp asInterface(IBinder r2) {
            if (r2 != null) goto L5;
            return null;
        L5:
            IInterface r0 = r2.queryLocalInterface(IStartCarApp.DESCRIPTOR);
            if (r0 == null) goto L12;
            if ((r0 instanceof IStartCarApp) == false) goto L12;
            return (IStartCarApp) r0;
        L12:
            return new Proxy(r2);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int r4, Parcel r5, Parcel r6, int r7) {
            String r0 = IStartCarApp.DESCRIPTOR;
            if (r4 < 1) goto L8;
            if (r4 > 16777215) goto L8;
            r5.enforceInterface(r0);
        L8:
            if (r4 != 1598968902) goto L12;
            r6.writeString(r0);
            return true;
        L12:
            if (r4 != 2) goto L14;
            startCarApp((Intent) _Parcel.access$000(r5, Intent.CREATOR));
            r6.writeNoException();
            return true;
        L14:
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
        DESCRIPTOR = "androidx$car$app$IStartCarApp".replace(Typography.dollar, '.');
    }

    void startCarApp(Intent r1);
}
