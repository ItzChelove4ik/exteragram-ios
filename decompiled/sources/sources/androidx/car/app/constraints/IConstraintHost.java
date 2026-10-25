package androidx.car.app.constraints;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import kotlin.text.Typography;

/* loaded from: classes3.dex */
public interface IConstraintHost extends IInterface {
    public static final String DESCRIPTOR = null;

    public static class Default implements IConstraintHost {
        public Default() {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // androidx.car.app.constraints.IConstraintHost
        public int getContentLimit(int r1) {
            return 0;
        }

        @Override // androidx.car.app.constraints.IConstraintHost
        public boolean isAppDrivenRefreshEnabled() {
            return false;
        }
    }

    public static abstract class Stub extends Binder implements IConstraintHost {
        static final int TRANSACTION_getContentLimit = 2;
        static final int TRANSACTION_isAppDrivenRefreshEnabled = 3;

        public static class Proxy implements IConstraintHost {
            private IBinder mRemote;

            public Proxy(IBinder r1) {
                this.mRemote = r1;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // androidx.car.app.constraints.IConstraintHost
            public int getContentLimit(int r4) {
                Parcel r0 = Parcel.obtain();
                Parcel r1 = Parcel.obtain();
                r0.writeInterfaceToken(IConstraintHost.DESCRIPTOR);     // Catch: Throwable -> L6
                r0.writeInt(r4);     // Catch: Throwable -> L6
                this.mRemote.transact(2, r0, r1, 0);     // Catch: Throwable -> L6
                r1.readException();     // Catch: Throwable -> L6
                int r3 = r1.readInt();     // Catch: Throwable -> L6
                r1.recycle();
                r0.recycle();
                return r3;
            L6:
                th = move-exception;
                r1.recycle();
                r0.recycle();
                throw th;
            }

            public String getInterfaceDescriptor() {
                return IConstraintHost.DESCRIPTOR;
            }

            @Override // androidx.car.app.constraints.IConstraintHost
            public boolean isAppDrivenRefreshEnabled() {
                Parcel r0 = Parcel.obtain();
                Parcel r1 = Parcel.obtain();
                r0.writeInterfaceToken(IConstraintHost.DESCRIPTOR);     // Catch: Throwable -> L8
                boolean r3 = false;
                this.mRemote.transact(3, r0, r1, 0);     // Catch: Throwable -> L8
                r1.readException();     // Catch: Throwable -> L8
                if (r1.readInt() == 0) goto L6;
                r3 = true;
            L6:
                r1.recycle();
                r0.recycle();
                return r3;
            L8:
                th = move-exception;
                r1.recycle();
                r0.recycle();
                throw th;
            }
        }

        public Stub() {
            attachInterface(this, IConstraintHost.DESCRIPTOR);
        }

        public static IConstraintHost asInterface(IBinder r2) {
            if (r2 != null) goto L5;
            return null;
        L5:
            IInterface r0 = r2.queryLocalInterface(IConstraintHost.DESCRIPTOR);
            if (r0 == null) goto L12;
            if ((r0 instanceof IConstraintHost) == false) goto L12;
            return (IConstraintHost) r0;
        L12:
            return new Proxy(r2);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int r4, Parcel r5, Parcel r6, int r7) {
            String r0 = IConstraintHost.DESCRIPTOR;
            if (r4 < 1) goto L8;
            if (r4 > 16777215) goto L8;
            r5.enforceInterface(r0);
        L8:
            if (r4 != 1598968902) goto L12;
            r6.writeString(r0);
            return true;
        L12:
            if (r4 != 2) goto L14;
            int r3 = getContentLimit(r5.readInt());
            r6.writeNoException();
            r6.writeInt(r3);
        L19:
            return true;
        L14:
            if (r4 != 3) goto L16;
            boolean r32 = isAppDrivenRefreshEnabled();
            r6.writeNoException();
            r6.writeInt(r32 ? 1 : 0);
            goto L19
        L16:
            return super.onTransact(r4, r5, r6, r7);
        }
    }

    static {
        DESCRIPTOR = "androidx$car$app$constraints$IConstraintHost".replace(Typography.dollar, '.');
    }

    int getContentLimit(int r1);

    boolean isAppDrivenRefreshEnabled();
}
