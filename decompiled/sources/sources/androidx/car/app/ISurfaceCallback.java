package androidx.car.app;

import android.graphics.Rect;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.serialization.Bundleable;
import kotlin.text.Typography;

/* loaded from: classes3.dex */
public interface ISurfaceCallback extends IInterface {
    public static final String DESCRIPTOR = null;

    public static class Default implements ISurfaceCallback {
        public Default() {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // androidx.car.app.ISurfaceCallback
        public void onClick(float r1, float r2) {
        }

        @Override // androidx.car.app.ISurfaceCallback
        public void onFling(float r1, float r2) {
        }

        @Override // androidx.car.app.ISurfaceCallback
        public void onScale(float r1, float r2, float r3) {
        }

        @Override // androidx.car.app.ISurfaceCallback
        public void onScroll(float r1, float r2) {
        }

        @Override // androidx.car.app.ISurfaceCallback
        public void onStableAreaChanged(Rect r1, IOnDoneCallback r2) {
        }

        @Override // androidx.car.app.ISurfaceCallback
        public void onSurfaceAvailable(Bundleable r1, IOnDoneCallback r2) {
        }

        @Override // androidx.car.app.ISurfaceCallback
        public void onSurfaceDestroyed(Bundleable r1, IOnDoneCallback r2) {
        }

        @Override // androidx.car.app.ISurfaceCallback
        public void onVisibleAreaChanged(Rect r1, IOnDoneCallback r2) {
        }
    }

    public static abstract class Stub extends Binder implements ISurfaceCallback {
        static final int TRANSACTION_onClick = 9;
        static final int TRANSACTION_onFling = 7;
        static final int TRANSACTION_onScale = 8;
        static final int TRANSACTION_onScroll = 6;
        static final int TRANSACTION_onStableAreaChanged = 4;
        static final int TRANSACTION_onSurfaceAvailable = 2;
        static final int TRANSACTION_onSurfaceDestroyed = 5;
        static final int TRANSACTION_onVisibleAreaChanged = 3;

        public static class Proxy implements ISurfaceCallback {
            private IBinder mRemote;

            public Proxy(IBinder r1) {
                this.mRemote = r1;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ISurfaceCallback.DESCRIPTOR;
            }

            @Override // androidx.car.app.ISurfaceCallback
            public void onClick(float r3, float r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken(ISurfaceCallback.DESCRIPTOR);     // Catch: Throwable -> L6
                r0.writeFloat(r3);     // Catch: Throwable -> L6
                r0.writeFloat(r4);     // Catch: Throwable -> L6
                this.mRemote.transact(9, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            @Override // androidx.car.app.ISurfaceCallback
            public void onFling(float r3, float r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken(ISurfaceCallback.DESCRIPTOR);     // Catch: Throwable -> L6
                r0.writeFloat(r3);     // Catch: Throwable -> L6
                r0.writeFloat(r4);     // Catch: Throwable -> L6
                this.mRemote.transact(7, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            @Override // androidx.car.app.ISurfaceCallback
            public void onScale(float r3, float r4, float r5) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken(ISurfaceCallback.DESCRIPTOR);     // Catch: Throwable -> L6
                r0.writeFloat(r3);     // Catch: Throwable -> L6
                r0.writeFloat(r4);     // Catch: Throwable -> L6
                r0.writeFloat(r5);     // Catch: Throwable -> L6
                this.mRemote.transact(8, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            @Override // androidx.car.app.ISurfaceCallback
            public void onScroll(float r3, float r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken(ISurfaceCallback.DESCRIPTOR);     // Catch: Throwable -> L6
                r0.writeFloat(r3);     // Catch: Throwable -> L6
                r0.writeFloat(r4);     // Catch: Throwable -> L6
                this.mRemote.transact(6, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            @Override // androidx.car.app.ISurfaceCallback
            public void onStableAreaChanged(Rect r3, IOnDoneCallback r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken(ISurfaceCallback.DESCRIPTOR);     // Catch: Throwable -> L6
                _Parcel.access$100(r0, r3, 0);     // Catch: Throwable -> L6
                r0.writeStrongInterface(r4);     // Catch: Throwable -> L6
                this.mRemote.transact(4, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            @Override // androidx.car.app.ISurfaceCallback
            public void onSurfaceAvailable(Bundleable r3, IOnDoneCallback r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken(ISurfaceCallback.DESCRIPTOR);     // Catch: Throwable -> L6
                _Parcel.access$100(r0, r3, 0);     // Catch: Throwable -> L6
                r0.writeStrongInterface(r4);     // Catch: Throwable -> L6
                this.mRemote.transact(2, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            @Override // androidx.car.app.ISurfaceCallback
            public void onSurfaceDestroyed(Bundleable r3, IOnDoneCallback r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken(ISurfaceCallback.DESCRIPTOR);     // Catch: Throwable -> L6
                _Parcel.access$100(r0, r3, 0);     // Catch: Throwable -> L6
                r0.writeStrongInterface(r4);     // Catch: Throwable -> L6
                this.mRemote.transact(5, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            @Override // androidx.car.app.ISurfaceCallback
            public void onVisibleAreaChanged(Rect r3, IOnDoneCallback r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken(ISurfaceCallback.DESCRIPTOR);     // Catch: Throwable -> L6
                _Parcel.access$100(r0, r3, 0);     // Catch: Throwable -> L6
                r0.writeStrongInterface(r4);     // Catch: Throwable -> L6
                this.mRemote.transact(3, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }
        }

        public Stub() {
            attachInterface(this, ISurfaceCallback.DESCRIPTOR);
        }

        public static ISurfaceCallback asInterface(IBinder r2) {
            if (r2 != null) goto L5;
            return null;
        L5:
            IInterface r0 = r2.queryLocalInterface(ISurfaceCallback.DESCRIPTOR);
            if (r0 == null) goto L12;
            if ((r0 instanceof ISurfaceCallback) == false) goto L12;
            return (ISurfaceCallback) r0;
        L12:
            return new Proxy(r2);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int r4, Parcel r5, Parcel r6, int r7) {
            String r0 = ISurfaceCallback.DESCRIPTOR;
            if (r4 < 1) goto L8;
            if (r4 > 16777215) goto L8;
            r5.enforceInterface(r0);
        L8:
            if (r4 != 1598968902) goto L11;
            r6.writeString(r0);
            return true;
        L11:
            switch(r4) {
                case 2: goto L21;
                case 3: goto L20;
                case 4: goto L19;
                case 5: goto L18;
                case 6: goto L17;
                case 7: goto L16;
                case 8: goto L15;
                case 9: goto L14;
                default: goto L13;
            };
        L14:
            onClick(r5.readFloat(), r5.readFloat());
        L22:
            return true;
        L15:
            onScale(r5.readFloat(), r5.readFloat(), r5.readFloat());
            goto L22
        L16:
            onFling(r5.readFloat(), r5.readFloat());
            goto L22
        L17:
            onScroll(r5.readFloat(), r5.readFloat());
            goto L22
        L18:
            onSurfaceDestroyed((Bundleable) _Parcel.access$000(r5, Bundleable.CREATOR), IOnDoneCallback.Stub.asInterface(r5.readStrongBinder()));
            goto L22
        L19:
            onStableAreaChanged((Rect) _Parcel.access$000(r5, Rect.CREATOR), IOnDoneCallback.Stub.asInterface(r5.readStrongBinder()));
            goto L22
        L20:
            onVisibleAreaChanged((Rect) _Parcel.access$000(r5, Rect.CREATOR), IOnDoneCallback.Stub.asInterface(r5.readStrongBinder()));
            goto L22
        L21:
            onSurfaceAvailable((Bundleable) _Parcel.access$000(r5, Bundleable.CREATOR), IOnDoneCallback.Stub.asInterface(r5.readStrongBinder()));
            goto L22
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
        DESCRIPTOR = "androidx$car$app$ISurfaceCallback".replace(Typography.dollar, '.');
    }

    void onClick(float r1, float r2);

    void onFling(float r1, float r2);

    void onScale(float r1, float r2, float r3);

    void onScroll(float r1, float r2);

    void onStableAreaChanged(Rect r1, IOnDoneCallback r2);

    void onSurfaceAvailable(Bundleable r1, IOnDoneCallback r2);

    void onSurfaceDestroyed(Bundleable r1, IOnDoneCallback r2);

    void onVisibleAreaChanged(Rect r1, IOnDoneCallback r2);
}
