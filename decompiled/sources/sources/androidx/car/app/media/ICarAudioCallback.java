package androidx.car.app.media;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import kotlin.text.Typography;

/* loaded from: classes3.dex */
public interface ICarAudioCallback extends IInterface {
    public static final String DESCRIPTOR = null;

    public static class Default implements ICarAudioCallback {
        public Default() {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // androidx.car.app.media.ICarAudioCallback
        public void onStopRecording() {
        }
    }

    public static abstract class Stub extends Binder implements ICarAudioCallback {
        static final int TRANSACTION_onStopRecording = 1;

        public static class Proxy implements ICarAudioCallback {
            private IBinder mRemote;

            public Proxy(IBinder r1) {
                this.mRemote = r1;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ICarAudioCallback.DESCRIPTOR;
            }

            @Override // androidx.car.app.media.ICarAudioCallback
            public void onStopRecording() {
                Parcel r0 = Parcel.obtain();
                Parcel r1 = Parcel.obtain();
                r0.writeInterfaceToken(ICarAudioCallback.DESCRIPTOR);     // Catch: Throwable -> L6
                this.mRemote.transact(1, r0, r1, 0);     // Catch: Throwable -> L6
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
            attachInterface(this, ICarAudioCallback.DESCRIPTOR);
        }

        public static ICarAudioCallback asInterface(IBinder r2) {
            if (r2 != null) goto L5;
            return null;
        L5:
            IInterface r0 = r2.queryLocalInterface(ICarAudioCallback.DESCRIPTOR);
            if (r0 == null) goto L12;
            if ((r0 instanceof ICarAudioCallback) == false) goto L12;
            return (ICarAudioCallback) r0;
        L12:
            return new Proxy(r2);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int r4, Parcel r5, Parcel r6, int r7) {
            String r0 = ICarAudioCallback.DESCRIPTOR;
            if (r4 < 1) goto L8;
            if (r4 > 16777215) goto L8;
            r5.enforceInterface(r0);
        L8:
            if (r4 != 1598968902) goto L11;
            r6.writeString(r0);
            return true;
        L11:
            if (r4 != 1) goto L13;
            onStopRecording();
            r6.writeNoException();
            return true;
        L13:
            return super.onTransact(r4, r5, r6, r7);
        }
    }

    static {
        DESCRIPTOR = "androidx$car$app$media$ICarAudioCallback".replace(Typography.dollar, '.');
    }

    void onStopRecording();
}
