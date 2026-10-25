package androidx.car.app;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.car.app.ICarHost;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.serialization.Bundleable;
import kotlin.text.Typography;

/* loaded from: classes3.dex */
public interface ICarApp extends IInterface {
    public static final String DESCRIPTOR = null;

    public static class Default implements ICarApp {
        public Default() {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // androidx.car.app.ICarApp
        public void getAppInfo(IOnDoneCallback r1) {
        }

        @Override // androidx.car.app.ICarApp
        public void getManager(String r1, IOnDoneCallback r2) {
        }

        @Override // androidx.car.app.ICarApp
        public void onAppCreate(ICarHost r1, Intent r2, Configuration r3, IOnDoneCallback r4) {
        }

        @Override // androidx.car.app.ICarApp
        public void onAppPause(IOnDoneCallback r1) {
        }

        @Override // androidx.car.app.ICarApp
        public void onAppResume(IOnDoneCallback r1) {
        }

        @Override // androidx.car.app.ICarApp
        public void onAppStart(IOnDoneCallback r1) {
        }

        @Override // androidx.car.app.ICarApp
        public void onAppStop(IOnDoneCallback r1) {
        }

        @Override // androidx.car.app.ICarApp
        public void onConfigurationChanged(Configuration r1, IOnDoneCallback r2) {
        }

        @Override // androidx.car.app.ICarApp
        public void onHandshakeCompleted(Bundleable r1, IOnDoneCallback r2) {
        }

        @Override // androidx.car.app.ICarApp
        public void onNewIntent(Intent r1, IOnDoneCallback r2) {
        }
    }

    public static abstract class Stub extends Binder implements ICarApp {
        static final int TRANSACTION_getAppInfo = 10;
        static final int TRANSACTION_getManager = 9;
        static final int TRANSACTION_onAppCreate = 2;
        static final int TRANSACTION_onAppPause = 5;
        static final int TRANSACTION_onAppResume = 4;
        static final int TRANSACTION_onAppStart = 3;
        static final int TRANSACTION_onAppStop = 6;
        static final int TRANSACTION_onConfigurationChanged = 8;
        static final int TRANSACTION_onHandshakeCompleted = 11;
        static final int TRANSACTION_onNewIntent = 7;

        public static class Proxy implements ICarApp {
            private IBinder mRemote;

            public Proxy(IBinder r1) {
                this.mRemote = r1;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // androidx.car.app.ICarApp
            public void getAppInfo(IOnDoneCallback r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken(ICarApp.DESCRIPTOR);     // Catch: Throwable -> L6
                r0.writeStrongInterface(r4);     // Catch: Throwable -> L6
                this.mRemote.transact(10, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            public String getInterfaceDescriptor() {
                return ICarApp.DESCRIPTOR;
            }

            @Override // androidx.car.app.ICarApp
            public void getManager(String r3, IOnDoneCallback r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken(ICarApp.DESCRIPTOR);     // Catch: Throwable -> L6
                r0.writeString(r3);     // Catch: Throwable -> L6
                r0.writeStrongInterface(r4);     // Catch: Throwable -> L6
                this.mRemote.transact(9, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            @Override // androidx.car.app.ICarApp
            public void onAppCreate(ICarHost r3, Intent r4, Configuration r5, IOnDoneCallback r6) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken(ICarApp.DESCRIPTOR);     // Catch: Throwable -> L6
                r0.writeStrongInterface(r3);     // Catch: Throwable -> L6
                _Parcel.access$100(r0, r4, 0);     // Catch: Throwable -> L6
                _Parcel.access$100(r0, r5, 0);     // Catch: Throwable -> L6
                r0.writeStrongInterface(r6);     // Catch: Throwable -> L6
                this.mRemote.transact(2, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            @Override // androidx.car.app.ICarApp
            public void onAppPause(IOnDoneCallback r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken(ICarApp.DESCRIPTOR);     // Catch: Throwable -> L6
                r0.writeStrongInterface(r4);     // Catch: Throwable -> L6
                this.mRemote.transact(5, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            @Override // androidx.car.app.ICarApp
            public void onAppResume(IOnDoneCallback r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken(ICarApp.DESCRIPTOR);     // Catch: Throwable -> L6
                r0.writeStrongInterface(r4);     // Catch: Throwable -> L6
                this.mRemote.transact(4, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            @Override // androidx.car.app.ICarApp
            public void onAppStart(IOnDoneCallback r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken(ICarApp.DESCRIPTOR);     // Catch: Throwable -> L6
                r0.writeStrongInterface(r4);     // Catch: Throwable -> L6
                this.mRemote.transact(3, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            @Override // androidx.car.app.ICarApp
            public void onAppStop(IOnDoneCallback r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken(ICarApp.DESCRIPTOR);     // Catch: Throwable -> L6
                r0.writeStrongInterface(r4);     // Catch: Throwable -> L6
                this.mRemote.transact(6, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            @Override // androidx.car.app.ICarApp
            public void onConfigurationChanged(Configuration r3, IOnDoneCallback r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken(ICarApp.DESCRIPTOR);     // Catch: Throwable -> L6
                _Parcel.access$100(r0, r3, 0);     // Catch: Throwable -> L6
                r0.writeStrongInterface(r4);     // Catch: Throwable -> L6
                this.mRemote.transact(8, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            @Override // androidx.car.app.ICarApp
            public void onHandshakeCompleted(Bundleable r3, IOnDoneCallback r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken(ICarApp.DESCRIPTOR);     // Catch: Throwable -> L6
                _Parcel.access$100(r0, r3, 0);     // Catch: Throwable -> L6
                r0.writeStrongInterface(r4);     // Catch: Throwable -> L6
                this.mRemote.transact(11, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            @Override // androidx.car.app.ICarApp
            public void onNewIntent(Intent r3, IOnDoneCallback r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken(ICarApp.DESCRIPTOR);     // Catch: Throwable -> L6
                _Parcel.access$100(r0, r3, 0);     // Catch: Throwable -> L6
                r0.writeStrongInterface(r4);     // Catch: Throwable -> L6
                this.mRemote.transact(7, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }
        }

        public Stub() {
            attachInterface(this, ICarApp.DESCRIPTOR);
        }

        public static ICarApp asInterface(IBinder r2) {
            if (r2 != null) goto L5;
            return null;
        L5:
            IInterface r0 = r2.queryLocalInterface(ICarApp.DESCRIPTOR);
            if (r0 == null) goto L12;
            if ((r0 instanceof ICarApp) == false) goto L12;
            return (ICarApp) r0;
        L12:
            return new Proxy(r2);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int r4, Parcel r5, Parcel r6, int r7) {
            String r0 = ICarApp.DESCRIPTOR;
            if (r4 < 1) goto L8;
            if (r4 > 16777215) goto L8;
            r5.enforceInterface(r0);
        L8:
            if (r4 != 1598968902) goto L11;
            r6.writeString(r0);
            return true;
        L11:
            switch(r4) {
                case 2: goto L23;
                case 3: goto L22;
                case 4: goto L21;
                case 5: goto L20;
                case 6: goto L19;
                case 7: goto L18;
                case 8: goto L17;
                case 9: goto L16;
                case 10: goto L15;
                case 11: goto L14;
                default: goto L13;
            };
        L14:
            onHandshakeCompleted((Bundleable) _Parcel.access$000(r5, Bundleable.CREATOR), IOnDoneCallback.Stub.asInterface(r5.readStrongBinder()));
        L24:
            return true;
        L15:
            getAppInfo(IOnDoneCallback.Stub.asInterface(r5.readStrongBinder()));
            goto L24
        L16:
            getManager(r5.readString(), IOnDoneCallback.Stub.asInterface(r5.readStrongBinder()));
            goto L24
        L17:
            onConfigurationChanged((Configuration) _Parcel.access$000(r5, Configuration.CREATOR), IOnDoneCallback.Stub.asInterface(r5.readStrongBinder()));
            goto L24
        L18:
            onNewIntent((Intent) _Parcel.access$000(r5, Intent.CREATOR), IOnDoneCallback.Stub.asInterface(r5.readStrongBinder()));
            goto L24
        L19:
            onAppStop(IOnDoneCallback.Stub.asInterface(r5.readStrongBinder()));
            goto L24
        L20:
            onAppPause(IOnDoneCallback.Stub.asInterface(r5.readStrongBinder()));
            goto L24
        L21:
            onAppResume(IOnDoneCallback.Stub.asInterface(r5.readStrongBinder()));
            goto L24
        L22:
            onAppStart(IOnDoneCallback.Stub.asInterface(r5.readStrongBinder()));
            goto L24
        L23:
            onAppCreate(ICarHost.Stub.asInterface(r5.readStrongBinder()), (Intent) _Parcel.access$000(r5, Intent.CREATOR), (Configuration) _Parcel.access$000(r5, Configuration.CREATOR), IOnDoneCallback.Stub.asInterface(r5.readStrongBinder()));
            goto L24
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
        DESCRIPTOR = "androidx$car$app$ICarApp".replace(Typography.dollar, '.');
    }

    void getAppInfo(IOnDoneCallback r1);

    void getManager(String r1, IOnDoneCallback r2);

    void onAppCreate(ICarHost r1, Intent r2, Configuration r3, IOnDoneCallback r4);

    void onAppPause(IOnDoneCallback r1);

    void onAppResume(IOnDoneCallback r1);

    void onAppStart(IOnDoneCallback r1);

    void onAppStop(IOnDoneCallback r1);

    void onConfigurationChanged(Configuration r1, IOnDoneCallback r2);

    void onHandshakeCompleted(Bundleable r1, IOnDoneCallback r2);

    void onNewIntent(Intent r1, IOnDoneCallback r2);
}
