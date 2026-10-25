package android.support.v4.media.session;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import java.util.List;

/* loaded from: classes3.dex */
public interface IMediaControllerCallback extends IInterface {

    public static abstract class Stub extends Binder implements IMediaControllerCallback {

        public static class Proxy implements IMediaControllerCallback {
            private IBinder mRemote;

            public Proxy(IBinder r1) {
                this.mRemote = r1;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void onPlaybackStateChanged(PlaybackStateCompat r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");     // Catch: Throwable -> L6
                _Parcel.access$100(r0, r4, 0);     // Catch: Throwable -> L6
                this.mRemote.transact(3, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void onRepeatModeChanged(int r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");     // Catch: Throwable -> L6
                r0.writeInt(r4);     // Catch: Throwable -> L6
                this.mRemote.transact(9, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void onShuffleModeChanged(int r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");     // Catch: Throwable -> L6
                r0.writeInt(r4);     // Catch: Throwable -> L6
                this.mRemote.transact(12, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }
        }

        public Stub() {
            attachInterface(this, "android.support.v4.media.session.IMediaControllerCallback");
        }

        public static IMediaControllerCallback asInterface(IBinder r2) {
            if (r2 != null) goto L5;
            return null;
        L5:
            IInterface r0 = r2.queryLocalInterface("android.support.v4.media.session.IMediaControllerCallback");
            if (r0 == null) goto L12;
            if ((r0 instanceof IMediaControllerCallback) == false) goto L12;
            return (IMediaControllerCallback) r0;
        L12:
            return new Proxy(r2);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int r4, Parcel r5, Parcel r6, int r7) {
            if (r4 < 1) goto L8;
            if (r4 > 16777215) goto L8;
            r5.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
        L8:
            if (r4 != 1598968902) goto L11;
            r6.writeString("android.support.v4.media.session.IMediaControllerCallback");
            return true;
        L11:
            boolean r0 = false;
            switch(r4) {
                case 1: goto L33;
                case 2: goto L32;
                case 3: goto L31;
                case 4: goto L30;
                case 5: goto L29;
                case 6: goto L28;
                case 7: goto L27;
                case 8: goto L26;
                case 9: goto L25;
                case 10: goto L22;
                case 11: goto L18;
                case 12: goto L16;
                case 13: goto L15;
                default: goto L14;
            };
        L15:
            onSessionReady();
        L34:
            return true;
        L16:
            onShuffleModeChanged(r5.readInt());
            goto L34
        L25:
            onRepeatModeChanged(r5.readInt());
            goto L34
        L26:
            onVolumeInfoChanged((ParcelableVolumeInfo) _Parcel.access$000(r5, ParcelableVolumeInfo.CREATOR));
            goto L34
        L27:
            onExtrasChanged((Bundle) _Parcel.access$000(r5, Bundle.CREATOR));
            goto L34
        L28:
            onQueueTitleChanged((CharSequence) _Parcel.access$000(r5, TextUtils.CHAR_SEQUENCE_CREATOR));
            goto L34
        L29:
            onQueueChanged(r5.createTypedArrayList(MediaSessionCompat.QueueItem.CREATOR));
            goto L34
        L30:
            onMetadataChanged((MediaMetadataCompat) _Parcel.access$000(r5, MediaMetadataCompat.CREATOR));
            goto L34
        L31:
            onPlaybackStateChanged((PlaybackStateCompat) _Parcel.access$000(r5, PlaybackStateCompat.CREATOR));
            goto L34
        L32:
            onSessionDestroyed();
            goto L34
        L33:
            onEvent(r5.readString(), (Bundle) _Parcel.access$000(r5, Bundle.CREATOR));
            goto L34
        L14:
            return super.onTransact(r4, r5, r6, r7);
        L18:
            if (r5.readInt() == 0) goto L20;
            r0 = true;
        L20:
            onCaptioningEnabledChanged(r0);
            goto L34
        L22:
            if (r5.readInt() == 0) goto L24;
            r0 = true;
        L24:
            onShuffleModeChangedRemoved(r0);
            goto L34
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

    void onCaptioningEnabledChanged(boolean r1);

    void onEvent(String r1, Bundle r2);

    void onExtrasChanged(Bundle r1);

    void onMetadataChanged(MediaMetadataCompat r1);

    void onPlaybackStateChanged(PlaybackStateCompat r1);

    void onQueueChanged(List<MediaSessionCompat.QueueItem> r1);

    void onQueueTitleChanged(CharSequence r1);

    void onRepeatModeChanged(int r1);

    void onSessionDestroyed();

    void onSessionReady();

    void onShuffleModeChanged(int r1);

    void onShuffleModeChangedRemoved(boolean r1);

    void onVolumeInfoChanged(ParcelableVolumeInfo r1);
}
