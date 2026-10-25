package android.support.v4.media.session;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.session.IMediaControllerCallback;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import java.util.List;

/* loaded from: classes3.dex */
public interface IMediaSession extends IInterface {

    public static abstract class Stub extends Binder implements IMediaSession {

        public static class Proxy implements IMediaSession {
            private IBinder mRemote;

            public Proxy(IBinder r1) {
                this.mRemote = r1;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public PlaybackStateCompat getPlaybackState() {
                Parcel r0 = Parcel.obtain();
                Parcel r1 = Parcel.obtain();
                r0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");     // Catch: Throwable -> L6
                this.mRemote.transact(28, r0, r1, 0);     // Catch: Throwable -> L6
                r1.readException();     // Catch: Throwable -> L6
                PlaybackStateCompat r4 = (PlaybackStateCompat) _Parcel.access$000(r1, PlaybackStateCompat.CREATOR);     // Catch: Throwable -> L6
                r1.recycle();
                r0.recycle();
                return r4;
            L6:
                th = move-exception;
                r1.recycle();
                r0.recycle();
                throw th;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void registerCallbackListener(IMediaControllerCallback r4) {
                Parcel r0 = Parcel.obtain();
                Parcel r1 = Parcel.obtain();
                r0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");     // Catch: Throwable -> L6
                r0.writeStrongInterface(r4);     // Catch: Throwable -> L6
                this.mRemote.transact(3, r0, r1, 0);     // Catch: Throwable -> L6
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

            @Override // android.support.v4.media.session.IMediaSession
            public void unregisterCallbackListener(IMediaControllerCallback r4) {
                Parcel r0 = Parcel.obtain();
                Parcel r1 = Parcel.obtain();
                r0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");     // Catch: Throwable -> L6
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
        }

        public Stub() {
            attachInterface(this, "android.support.v4.media.session.IMediaSession");
        }

        public static IMediaSession asInterface(IBinder r2) {
            if (r2 != null) goto L5;
            return null;
        L5:
            IInterface r0 = r2.queryLocalInterface("android.support.v4.media.session.IMediaSession");
            if (r0 == null) goto L12;
            if ((r0 instanceof IMediaSession) == false) goto L12;
            return (IMediaSession) r0;
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
            r5.enforceInterface("android.support.v4.media.session.IMediaSession");
        L8:
            if (r4 != 1598968902) goto L11;
            r6.writeString("android.support.v4.media.session.IMediaSession");
            return true;
        L11:
            boolean r0 = false;
            switch(r4) {
                case 1: goto L74;
                case 2: goto L73;
                case 3: goto L72;
                case 4: goto L71;
                case 5: goto L70;
                case 6: goto L69;
                case 7: goto L68;
                case 8: goto L67;
                case 9: goto L66;
                case 10: goto L65;
                case 11: goto L64;
                case 12: goto L63;
                case 13: goto L62;
                case 14: goto L61;
                case 15: goto L60;
                case 16: goto L59;
                case 17: goto L58;
                case 18: goto L57;
                case 19: goto L56;
                case 20: goto L55;
                case 21: goto L54;
                case 22: goto L53;
                case 23: goto L52;
                case 24: goto L51;
                case 25: goto L50;
                case 26: goto L49;
                case 27: goto L48;
                case 28: goto L47;
                case 29: goto L46;
                case 30: goto L42;
                case 31: goto L41;
                case 32: goto L40;
                case 33: goto L39;
                case 34: goto L38;
                case 35: goto L37;
                case 36: goto L36;
                case 37: goto L35;
                case 38: goto L34;
                case 39: goto L33;
                case 40: goto L30;
                case 41: goto L28;
                case 42: goto L27;
                case 43: goto L26;
                case 44: goto L25;
                case 45: goto L24;
                case 46: goto L21;
                case 47: goto L19;
                case 48: goto L18;
                case 49: goto L17;
                case 50: goto L16;
                case 51: goto L15;
                default: goto L14;
            };
        L15:
            rateWithExtras((RatingCompat) _Parcel.access$000(r5, RatingCompat.CREATOR), (Bundle) _Parcel.access$000(r5, Bundle.CREATOR));
            r6.writeNoException();
        L75:
            return true;
        L16:
            Bundle r3 = getSessionInfo();
            r6.writeNoException();
            _Parcel.access$100(r6, r3, 1);
            goto L75
        L17:
            setPlaybackSpeed(r5.readFloat());
            r6.writeNoException();
            goto L75
        L18:
            setShuffleMode(r5.readInt());
            r6.writeNoException();
            goto L75
        L19:
            int r32 = getShuffleMode();
            r6.writeNoException();
            r6.writeInt(r32);
            goto L75
        L24:
            boolean r33 = isCaptioningEnabled();
            r6.writeNoException();
            r6.writeInt(r33 ? 1 : 0);
            goto L75
        L25:
            removeQueueItemAt(r5.readInt());
            r6.writeNoException();
            goto L75
        L26:
            removeQueueItem((MediaDescriptionCompat) _Parcel.access$000(r5, MediaDescriptionCompat.CREATOR));
            r6.writeNoException();
            goto L75
        L27:
            addQueueItemAt((MediaDescriptionCompat) _Parcel.access$000(r5, MediaDescriptionCompat.CREATOR), r5.readInt());
            r6.writeNoException();
            goto L75
        L28:
            addQueueItem((MediaDescriptionCompat) _Parcel.access$000(r5, MediaDescriptionCompat.CREATOR));
            r6.writeNoException();
            goto L75
        L33:
            setRepeatMode(r5.readInt());
            r6.writeNoException();
            goto L75
        L34:
            boolean r34 = isShuffleModeEnabledRemoved();
            r6.writeNoException();
            r6.writeInt(r34 ? 1 : 0);
            goto L75
        L35:
            int r35 = getRepeatMode();
            r6.writeNoException();
            r6.writeInt(r35);
            goto L75
        L36:
            prepareFromUri((Uri) _Parcel.access$000(r5, Uri.CREATOR), (Bundle) _Parcel.access$000(r5, Bundle.CREATOR));
            r6.writeNoException();
            goto L75
        L37:
            prepareFromSearch(r5.readString(), (Bundle) _Parcel.access$000(r5, Bundle.CREATOR));
            r6.writeNoException();
            goto L75
        L38:
            prepareFromMediaId(r5.readString(), (Bundle) _Parcel.access$000(r5, Bundle.CREATOR));
            r6.writeNoException();
            goto L75
        L39:
            prepare();
            r6.writeNoException();
            goto L75
        L40:
            int r36 = getRatingType();
            r6.writeNoException();
            r6.writeInt(r36);
            goto L75
        L41:
            Bundle r37 = getExtras();
            r6.writeNoException();
            _Parcel.access$100(r6, r37, 1);
            goto L75
        L42:
            CharSequence r38 = getQueueTitle();
            r6.writeNoException();
            if (r38 == null) goto L45;
            r6.writeInt(1);
            TextUtils.writeToParcel(r38, r6, 1);
            goto L75
        L45:
            r6.writeInt(0);
            goto L75
        L46:
            List<MediaSessionCompat.QueueItem> r39 = getQueue();
            r6.writeNoException();
            _Parcel.access$200(r6, r39, 1);
            goto L75
        L47:
            PlaybackStateCompat r310 = getPlaybackState();
            r6.writeNoException();
            _Parcel.access$100(r6, r310, 1);
            goto L75
        L48:
            MediaMetadataCompat r311 = getMetadata();
            r6.writeNoException();
            _Parcel.access$100(r6, r311, 1);
            goto L75
        L49:
            sendCustomAction(r5.readString(), (Bundle) _Parcel.access$000(r5, Bundle.CREATOR));
            r6.writeNoException();
            goto L75
        L50:
            rate((RatingCompat) _Parcel.access$000(r5, RatingCompat.CREATOR));
            r6.writeNoException();
            goto L75
        L51:
            seekTo(r5.readLong());
            r6.writeNoException();
            goto L75
        L52:
            rewind();
            r6.writeNoException();
            goto L75
        L53:
            fastForward();
            r6.writeNoException();
            goto L75
        L54:
            previous();
            r6.writeNoException();
            goto L75
        L55:
            next();
            r6.writeNoException();
            goto L75
        L56:
            stop();
            r6.writeNoException();
            goto L75
        L57:
            pause();
            r6.writeNoException();
            goto L75
        L58:
            skipToQueueItem(r5.readLong());
            r6.writeNoException();
            goto L75
        L59:
            playFromUri((Uri) _Parcel.access$000(r5, Uri.CREATOR), (Bundle) _Parcel.access$000(r5, Bundle.CREATOR));
            r6.writeNoException();
            goto L75
        L60:
            playFromSearch(r5.readString(), (Bundle) _Parcel.access$000(r5, Bundle.CREATOR));
            r6.writeNoException();
            goto L75
        L61:
            playFromMediaId(r5.readString(), (Bundle) _Parcel.access$000(r5, Bundle.CREATOR));
            r6.writeNoException();
            goto L75
        L62:
            play();
            r6.writeNoException();
            goto L75
        L63:
            setVolumeTo(r5.readInt(), r5.readInt(), r5.readString());
            r6.writeNoException();
            goto L75
        L64:
            adjustVolume(r5.readInt(), r5.readInt(), r5.readString());
            r6.writeNoException();
            goto L75
        L65:
            ParcelableVolumeInfo r312 = getVolumeAttributes();
            r6.writeNoException();
            _Parcel.access$100(r6, r312, 1);
            goto L75
        L66:
            long r313 = getFlags();
            r6.writeNoException();
            r6.writeLong(r313);
            goto L75
        L67:
            PendingIntent r314 = getLaunchPendingIntent();
            r6.writeNoException();
            _Parcel.access$100(r6, r314, 1);
            goto L75
        L68:
            String r315 = getTag();
            r6.writeNoException();
            r6.writeString(r315);
            goto L75
        L69:
            String r316 = getPackageName();
            r6.writeNoException();
            r6.writeString(r316);
            goto L75
        L70:
            boolean r317 = isTransportControlEnabled();
            r6.writeNoException();
            r6.writeInt(r317 ? 1 : 0);
            goto L75
        L71:
            unregisterCallbackListener(IMediaControllerCallback.Stub.asInterface(r5.readStrongBinder()));
            r6.writeNoException();
            goto L75
        L72:
            registerCallbackListener(IMediaControllerCallback.Stub.asInterface(r5.readStrongBinder()));
            r6.writeNoException();
            goto L75
        L73:
            boolean r318 = sendMediaButton((KeyEvent) _Parcel.access$000(r5, KeyEvent.CREATOR));
            r6.writeNoException();
            r6.writeInt(r318 ? 1 : 0);
            goto L75
        L74:
            sendCommand(r5.readString(), (Bundle) _Parcel.access$000(r5, Bundle.CREATOR), (MediaSessionCompat.ResultReceiverWrapper) _Parcel.access$000(r5, MediaSessionCompat.ResultReceiverWrapper.CREATOR));
            r6.writeNoException();
            goto L75
        L14:
            return super.onTransact(r4, r5, r6, r7);
        L21:
            if (r5.readInt() == 0) goto L23;
            r0 = true;
        L23:
            setCaptioningEnabled(r0);
            r6.writeNoException();
            goto L75
        L30:
            if (r5.readInt() == 0) goto L32;
            r0 = true;
        L32:
            setShuffleModeEnabledRemoved(r0);
            r6.writeNoException();
            goto L75
        }
    }

    public static class _Parcel {
        public static /* synthetic */ Object access$000(Parcel r0, Parcelable.Creator r1) {
            return readTypedObject(r0, r1);
        }

        public static /* synthetic */ void access$100(Parcel r0, Parcelable r1, int r2) {
            writeTypedObject(r0, r1, r2);
        }

        public static /* synthetic */ void access$200(Parcel r0, List r1, int r2) {
            writeTypedList(r0, r1, r2);
        }

        private static <T> T readTypedObject(Parcel r1, Parcelable.Creator<T> r2) {
            if (r1.readInt() != 0) goto L5;
            return null;
        L5:
            return r2.createFromParcel(r1);
        }

        private static <T extends Parcelable> void writeTypedList(Parcel r3, List<T> r4, int r5) {
            if (r4 != null) goto L5;
            r3.writeInt(-1);
            return;
        L5:
            int r0 = r4.size();
            r3.writeInt(r0);
            int r1 = 0;
        L6:
            if (r1 >= r0) goto L8;
            writeTypedObject(r3, r4.get(r1), r5);
            r1 = r1 + 1;
            goto L6
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

    void addQueueItem(MediaDescriptionCompat r1);

    void addQueueItemAt(MediaDescriptionCompat r1, int r2);

    void adjustVolume(int r1, int r2, String r3);

    void fastForward();

    Bundle getExtras();

    long getFlags();

    PendingIntent getLaunchPendingIntent();

    MediaMetadataCompat getMetadata();

    String getPackageName();

    PlaybackStateCompat getPlaybackState();

    List<MediaSessionCompat.QueueItem> getQueue();

    CharSequence getQueueTitle();

    int getRatingType();

    int getRepeatMode();

    Bundle getSessionInfo();

    int getShuffleMode();

    String getTag();

    ParcelableVolumeInfo getVolumeAttributes();

    boolean isCaptioningEnabled();

    boolean isShuffleModeEnabledRemoved();

    boolean isTransportControlEnabled();

    void next();

    void pause();

    void play();

    void playFromMediaId(String r1, Bundle r2);

    void playFromSearch(String r1, Bundle r2);

    void playFromUri(Uri r1, Bundle r2);

    void prepare();

    void prepareFromMediaId(String r1, Bundle r2);

    void prepareFromSearch(String r1, Bundle r2);

    void prepareFromUri(Uri r1, Bundle r2);

    void previous();

    void rate(RatingCompat r1);

    void rateWithExtras(RatingCompat r1, Bundle r2);

    void registerCallbackListener(IMediaControllerCallback r1);

    void removeQueueItem(MediaDescriptionCompat r1);

    void removeQueueItemAt(int r1);

    void rewind();

    void seekTo(long r1);

    void sendCommand(String r1, Bundle r2, MediaSessionCompat.ResultReceiverWrapper r3);

    void sendCustomAction(String r1, Bundle r2);

    boolean sendMediaButton(KeyEvent r1);

    void setCaptioningEnabled(boolean r1);

    void setPlaybackSpeed(float r1);

    void setRepeatMode(int r1);

    void setShuffleMode(int r1);

    void setShuffleModeEnabledRemoved(boolean r1);

    void setVolumeTo(int r1, int r2, String r3);

    void skipToQueueItem(long r1);

    void stop();

    void unregisterCallbackListener(IMediaControllerCallback r1);
}
