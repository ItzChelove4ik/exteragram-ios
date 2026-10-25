package androidx.media3.session;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes4.dex */
public interface IMediaController extends IInterface {

    public static abstract class Stub extends Binder implements IMediaController {

        public static class Proxy implements IMediaController {
            private IBinder mRemote;

            public Proxy(IBinder r1) {
                this.mRemote = r1;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // androidx.media3.session.IMediaController
            public void onAvailableCommandsChangedFromPlayer(int r3, Bundle r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken("androidx.media3.session.IMediaController");     // Catch: Throwable -> L6
                r0.writeInt(r3);     // Catch: Throwable -> L6
                _Parcel.access$100(r0, r4, 0);     // Catch: Throwable -> L6
                this.mRemote.transact(3009, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            @Override // androidx.media3.session.IMediaController
            public void onConnected(int r3, Bundle r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken("androidx.media3.session.IMediaController");     // Catch: Throwable -> L6
                r0.writeInt(r3);     // Catch: Throwable -> L6
                _Parcel.access$100(r0, r4, 0);     // Catch: Throwable -> L6
                this.mRemote.transact(3001, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            @Override // androidx.media3.session.IMediaController
            public void onCustomCommand(int r3, Bundle r4, Bundle r5) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken("androidx.media3.session.IMediaController");     // Catch: Throwable -> L6
                r0.writeInt(r3);     // Catch: Throwable -> L6
                _Parcel.access$100(r0, r4, 0);     // Catch: Throwable -> L6
                _Parcel.access$100(r0, r5, 0);     // Catch: Throwable -> L6
                this.mRemote.transact(3005, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            @Override // androidx.media3.session.IMediaController
            public void onDisconnected(int r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken("androidx.media3.session.IMediaController");     // Catch: Throwable -> L6
                r0.writeInt(r4);     // Catch: Throwable -> L6
                this.mRemote.transact(3006, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            @Override // androidx.media3.session.IMediaController
            public void onLibraryResult(int r3, Bundle r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken("androidx.media3.session.IMediaController");     // Catch: Throwable -> L6
                r0.writeInt(r3);     // Catch: Throwable -> L6
                _Parcel.access$100(r0, r4, 0);     // Catch: Throwable -> L6
                this.mRemote.transact(3003, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            @Override // androidx.media3.session.IMediaController
            public void onPeriodicSessionPositionInfoChanged(int r3, Bundle r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken("androidx.media3.session.IMediaController");     // Catch: Throwable -> L6
                r0.writeInt(r3);     // Catch: Throwable -> L6
                _Parcel.access$100(r0, r4, 0);     // Catch: Throwable -> L6
                this.mRemote.transact(3008, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            @Override // androidx.media3.session.IMediaController
            public void onPlayerInfoChanged(int r3, Bundle r4, boolean r5) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken("androidx.media3.session.IMediaController");     // Catch: Throwable -> L6
                r0.writeInt(r3);     // Catch: Throwable -> L6
                _Parcel.access$100(r0, r4, 0);     // Catch: Throwable -> L6
                r0.writeInt(r5 ? 1 : 0);     // Catch: Throwable -> L6
                this.mRemote.transact(3007, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            @Override // androidx.media3.session.IMediaController
            public void onPlayerInfoChangedWithExclusions(int r3, Bundle r4, Bundle r5) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken("androidx.media3.session.IMediaController");     // Catch: Throwable -> L6
                r0.writeInt(r3);     // Catch: Throwable -> L6
                _Parcel.access$100(r0, r4, 0);     // Catch: Throwable -> L6
                _Parcel.access$100(r0, r5, 0);     // Catch: Throwable -> L6
                this.mRemote.transact(3013, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            @Override // androidx.media3.session.IMediaController
            public void onRenderedFirstFrame(int r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken("androidx.media3.session.IMediaController");     // Catch: Throwable -> L6
                r0.writeInt(r4);     // Catch: Throwable -> L6
                this.mRemote.transact(3011, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            @Override // androidx.media3.session.IMediaController
            public void onSessionResult(int r3, Bundle r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken("androidx.media3.session.IMediaController");     // Catch: Throwable -> L6
                r0.writeInt(r3);     // Catch: Throwable -> L6
                _Parcel.access$100(r0, r4, 0);     // Catch: Throwable -> L6
                this.mRemote.transact(3002, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }
        }

        public static IMediaController asInterface(IBinder r2) {
            if (r2 != null) goto L5;
            return null;
        L5:
            IInterface r0 = r2.queryLocalInterface("androidx.media3.session.IMediaController");
            if (r0 == null) goto L12;
            if ((r0 instanceof IMediaController) == false) goto L12;
            return (IMediaController) r0;
        L12:
            return new Proxy(r2);
        }
    }

    public static class _Parcel {
        public static /* synthetic */ void access$100(Parcel r0, Parcelable r1, int r2) {
            writeTypedObject(r0, r1, r2);
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

    void onAvailableCommandsChangedFromPlayer(int r1, Bundle r2);

    void onConnected(int r1, Bundle r2);

    void onCustomCommand(int r1, Bundle r2, Bundle r3);

    void onDisconnected(int r1);

    void onLibraryResult(int r1, Bundle r2);

    void onPeriodicSessionPositionInfoChanged(int r1, Bundle r2);

    void onPlayerInfoChanged(int r1, Bundle r2, boolean r3);

    void onPlayerInfoChangedWithExclusions(int r1, Bundle r2, Bundle r3);

    void onRenderedFirstFrame(int r1);

    void onSessionResult(int r1, Bundle r2);
}
