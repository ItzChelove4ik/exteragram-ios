package androidx.car.app;

import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.car.app.ISurfaceCallback;
import androidx.car.app.serialization.Bundleable;
import kotlin.text.Typography;

/* loaded from: classes3.dex */
public interface IAppHost extends IInterface {
    public static final String DESCRIPTOR = null;

    public static class Default implements IAppHost {
        public Default() {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // androidx.car.app.IAppHost
        public void dismissAlert(int r1) {
        }

        @Override // androidx.car.app.IAppHost
        public void invalidate() {
        }

        @Override // androidx.car.app.IAppHost
        public Bundleable openMicrophone(Bundleable r1) {
            return null;
        }

        @Override // androidx.car.app.IAppHost
        public void sendLocation(Location r1) {
        }

        @Override // androidx.car.app.IAppHost
        public void setSurfaceCallback(ISurfaceCallback r1) {
        }

        @Override // androidx.car.app.IAppHost
        public void showAlert(Bundleable r1) {
        }

        @Override // androidx.car.app.IAppHost
        public void showToast(CharSequence r1, int r2) {
        }
    }

    public static abstract class Stub extends Binder implements IAppHost {
        static final int TRANSACTION_dismissAlert = 7;
        static final int TRANSACTION_invalidate = 2;
        static final int TRANSACTION_openMicrophone = 8;
        static final int TRANSACTION_sendLocation = 5;
        static final int TRANSACTION_setSurfaceCallback = 4;
        static final int TRANSACTION_showAlert = 6;
        static final int TRANSACTION_showToast = 3;

        public static class Proxy implements IAppHost {
            private IBinder mRemote;

            public Proxy(IBinder r1) {
                this.mRemote = r1;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // androidx.car.app.IAppHost
            public void dismissAlert(int r4) {
                Parcel r0 = Parcel.obtain();
                Parcel r1 = Parcel.obtain();
                r0.writeInterfaceToken(IAppHost.DESCRIPTOR);     // Catch: Throwable -> L6
                r0.writeInt(r4);     // Catch: Throwable -> L6
                this.mRemote.transact(7, r0, r1, 0);     // Catch: Throwable -> L6
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
                return IAppHost.DESCRIPTOR;
            }

            @Override // androidx.car.app.IAppHost
            public void invalidate() {
                Parcel r0 = Parcel.obtain();
                Parcel r1 = Parcel.obtain();
                r0.writeInterfaceToken(IAppHost.DESCRIPTOR);     // Catch: Throwable -> L6
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

            @Override // androidx.car.app.IAppHost
            public Bundleable openMicrophone(Bundleable r4) {
                Parcel r0 = Parcel.obtain();
                Parcel r1 = Parcel.obtain();
                r0.writeInterfaceToken(IAppHost.DESCRIPTOR);     // Catch: Throwable -> L6
                _Parcel.access$100(r0, r4, 0);     // Catch: Throwable -> L6
                this.mRemote.transact(8, r0, r1, 0);     // Catch: Throwable -> L6
                r1.readException();     // Catch: Throwable -> L6
                Bundleable r3 = (Bundleable) _Parcel.access$000(r1, Bundleable.CREATOR);     // Catch: Throwable -> L6
                r1.recycle();
                r0.recycle();
                return r3;
            L6:
                th = move-exception;
                r1.recycle();
                r0.recycle();
                throw th;
            }

            @Override // androidx.car.app.IAppHost
            public void sendLocation(Location r4) {
                Parcel r0 = Parcel.obtain();
                Parcel r1 = Parcel.obtain();
                r0.writeInterfaceToken(IAppHost.DESCRIPTOR);     // Catch: Throwable -> L6
                _Parcel.access$100(r0, r4, 0);     // Catch: Throwable -> L6
                this.mRemote.transact(5, r0, r1, 0);     // Catch: Throwable -> L6
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

            @Override // androidx.car.app.IAppHost
            public void setSurfaceCallback(ISurfaceCallback r4) {
                Parcel r0 = Parcel.obtain();
                Parcel r1 = Parcel.obtain();
                r0.writeInterfaceToken(IAppHost.DESCRIPTOR);     // Catch: Throwable -> L6
                r0.writeStrongInterface(r4);     // Catch: Throwable -> L6
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

            @Override // androidx.car.app.IAppHost
            public void showAlert(Bundleable r4) {
                Parcel r0 = Parcel.obtain();
                Parcel r1 = Parcel.obtain();
                r0.writeInterfaceToken(IAppHost.DESCRIPTOR);     // Catch: Throwable -> L6
                _Parcel.access$100(r0, r4, 0);     // Catch: Throwable -> L6
                this.mRemote.transact(6, r0, r1, 0);     // Catch: Throwable -> L6
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

            @Override // androidx.car.app.IAppHost
            public void showToast(CharSequence r5, int r6) {
                Parcel r0 = Parcel.obtain();
                Parcel r1 = Parcel.obtain();
                r0.writeInterfaceToken(IAppHost.DESCRIPTOR);     // Catch: Throwable -> L6
                if (r5 == null) goto L8;
                r0.writeInt(1);     // Catch: Throwable -> L6
                TextUtils.writeToParcel(r5, r0, 0);     // Catch: Throwable -> L6
            L9:
                r0.writeInt(r6);     // Catch: Throwable -> L6
                this.mRemote.transact(3, r0, r1, 0);     // Catch: Throwable -> L6
                r1.readException();     // Catch: Throwable -> L6
                r1.recycle();
                r0.recycle();
                return;
            L8:
                r0.writeInt(0);     // Catch: Throwable -> L6
            L6:
                th = move-exception;
                r1.recycle();
                r0.recycle();
                throw th;
            }
        }

        public Stub() {
            attachInterface(this, IAppHost.DESCRIPTOR);
        }

        public static IAppHost asInterface(IBinder r2) {
            if (r2 != null) goto L5;
            return null;
        L5:
            IInterface r0 = r2.queryLocalInterface(IAppHost.DESCRIPTOR);
            if (r0 == null) goto L12;
            if ((r0 instanceof IAppHost) == false) goto L12;
            return (IAppHost) r0;
        L12:
            return new Proxy(r2);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int r4, Parcel r5, Parcel r6, int r7) {
            String r0 = IAppHost.DESCRIPTOR;
            if (r4 < 1) goto L8;
            if (r4 > 16777215) goto L8;
            r5.enforceInterface(r0);
        L8:
            if (r4 != 1598968902) goto L11;
            r6.writeString(r0);
            return true;
        L11:
            switch(r4) {
                case 2: goto L20;
                case 3: goto L19;
                case 4: goto L18;
                case 5: goto L17;
                case 6: goto L16;
                case 7: goto L15;
                case 8: goto L14;
                default: goto L13;
            };
        L14:
            Bundleable r3 = openMicrophone((Bundleable) _Parcel.access$000(r5, Bundleable.CREATOR));
            r6.writeNoException();
            _Parcel.access$100(r6, r3, 1);
        L21:
            return true;
        L15:
            dismissAlert(r5.readInt());
            r6.writeNoException();
            goto L21
        L16:
            showAlert((Bundleable) _Parcel.access$000(r5, Bundleable.CREATOR));
            r6.writeNoException();
            goto L21
        L17:
            sendLocation((Location) _Parcel.access$000(r5, Location.CREATOR));
            r6.writeNoException();
            goto L21
        L18:
            setSurfaceCallback(ISurfaceCallback.Stub.asInterface(r5.readStrongBinder()));
            r6.writeNoException();
            goto L21
        L19:
            showToast((CharSequence) _Parcel.access$000(r5, TextUtils.CHAR_SEQUENCE_CREATOR), r5.readInt());
            r6.writeNoException();
            goto L21
        L20:
            invalidate();
            r6.writeNoException();
            goto L21
        L13:
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
        DESCRIPTOR = "androidx$car$app$IAppHost".replace(Typography.dollar, '.');
    }

    void dismissAlert(int r1);

    void invalidate();

    Bundleable openMicrophone(Bundleable r1);

    void sendLocation(Location r1);

    void setSurfaceCallback(ISurfaceCallback r1);

    void showAlert(Bundleable r1);

    void showToast(CharSequence r1, int r2);
}
