package androidx.media3.session;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.Surface;
import androidx.media3.session.IMediaController;

/* loaded from: classes4.dex */
public interface IMediaSession extends IInterface {

    public static abstract class Stub extends Binder implements IMediaSession {
        public Stub() {
            attachInterface(this, "androidx.media3.session.IMediaSession");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int r10, Parcel r11, Parcel r12, int r13) {
            if (r10 < 1) goto L8;
            if (r10 > 16777215) goto L8;
            r11.enforceInterface("androidx.media3.session.IMediaSession");
        L8:
            if (r10 != 1598968902) goto L11;
            r12.writeString("androidx.media3.session.IMediaSession");
            return true;
        L11:
            boolean r3 = false;
            switch(r10) {
                case 3002: goto L99;
                case 3003: goto L98;
                case 3004: goto L97;
                case 3005: goto L96;
                case 3006: goto L92;
                case 3007: goto L91;
                case 3008: goto L90;
                case 3009: goto L86;
                case 3010: goto L85;
                case 3011: goto L81;
                case 3012: goto L80;
                case 3013: goto L76;
                case 3014: goto L75;
                case 3015: goto L74;
                case 3016: goto L73;
                case 3017: goto L72;
                case 3018: goto L68;
                case 3019: goto L67;
                case 3020: goto L66;
                case 3021: goto L65;
                case 3022: goto L64;
                case 3023: goto L63;
                case 3024: goto L62;
                case 3025: goto L61;
                case 3026: goto L60;
                case 3027: goto L59;
                case 3028: goto L58;
                case 3029: goto L57;
                case 3030: goto L56;
                case 3031: goto L55;
                case 3032: goto L54;
                case 3033: goto L53;
                case 3034: goto L52;
                case 3035: goto L51;
                case 3036: goto L50;
                case 3037: goto L49;
                case 3038: goto L48;
                case 3039: goto L47;
                case 3040: goto L46;
                case 3041: goto L45;
                case 3042: goto L44;
                case 3043: goto L43;
                case 3044: goto L42;
                case 3045: goto L41;
                case 3046: goto L40;
                case 3047: goto L39;
                case 3048: goto L38;
                case 3049: goto L37;
                case 3050: goto L36;
                case 3051: goto L35;
                case 3052: goto L34;
                case 3053: goto L33;
                case 3054: goto L29;
                case 3055: goto L28;
                case 3056: goto L27;
                case 3057: goto L23;
                default: goto L13;
            };
        L13:
            switch(r10) {
                case 4001: goto L22;
                case 4002: goto L21;
                case 4003: goto L20;
                case 4004: goto L19;
                case 4005: goto L18;
                case 4006: goto L17;
                case 4007: goto L16;
                default: goto L15;
            };
        L16:
            unsubscribe(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), r11.readString());
        L100:
            return true;
        L17:
            subscribe(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), r11.readString(), (Bundle) _Parcel.access$000(r11, Bundle.CREATOR));
            goto L100
        L18:
            getSearchResult(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), r11.readString(), r11.readInt(), r11.readInt(), (Bundle) _Parcel.access$000(r11, Bundle.CREATOR));
            goto L100
        L19:
            search(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), r11.readString(), (Bundle) _Parcel.access$000(r11, Bundle.CREATOR));
            goto L100
        L20:
            getChildren(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), r11.readString(), r11.readInt(), r11.readInt(), (Bundle) _Parcel.access$000(r11, Bundle.CREATOR));
            goto L100
        L21:
            getItem(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), r11.readString());
            goto L100
        L22:
            getLibraryRoot(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), (Bundle) _Parcel.access$000(r11, Bundle.CREATOR));
            goto L100
        L15:
            return super.onTransact(r10, r11, r12, r13);
        L23:
            IMediaController r1 = IMediaController.Stub.asInterface(r11.readStrongBinder());
            int r4 = r11.readInt();
            Bundle r5 = (Bundle) _Parcel.access$000(r11, Bundle.CREATOR);
            if (r11.readInt() == 0) goto L26;
            r3 = true;
        L26:
            setAudioAttributes(r1, r4, r5, r3);
            goto L100
        L27:
            replaceMediaItems(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), r11.readInt(), r11.readInt(), r11.readStrongBinder());
            goto L100
        L28:
            replaceMediaItem(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), r11.readInt(), (Bundle) _Parcel.access$000(r11, Bundle.CREATOR));
            goto L100
        L29:
            IMediaController r14 = IMediaController.Stub.asInterface(r11.readStrongBinder());
            int r2 = r11.readInt();
            if (r11.readInt() == 0) goto L32;
            r3 = true;
        L32:
            setDeviceMutedWithFlags(r14, r2, r3, r11.readInt());
            goto L100
        L33:
            decreaseDeviceVolumeWithFlags(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), r11.readInt());
            goto L100
        L34:
            increaseDeviceVolumeWithFlags(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), r11.readInt());
            goto L100
        L35:
            setDeviceVolumeWithFlags(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), r11.readInt(), r11.readInt());
            goto L100
        L36:
            setRating(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), (Bundle) _Parcel.access$000(r11, Bundle.CREATOR));
            goto L100
        L37:
            setRatingWithMediaId(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), r11.readString(), (Bundle) _Parcel.access$000(r11, Bundle.CREATOR));
            goto L100
        L38:
            setTrackSelectionParameters(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), (Bundle) _Parcel.access$000(r11, Bundle.CREATOR));
            goto L100
        L39:
            seekToNext(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt());
            goto L100
        L40:
            seekToPrevious(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt());
            goto L100
        L41:
            flushCommandQueue(IMediaController.Stub.asInterface(r11.readStrongBinder()));
            goto L100
        L42:
            setVideoSurface(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), (Surface) _Parcel.access$000(r11, Surface.CREATOR));
            goto L100
        L43:
            seekToNextMediaItem(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt());
            goto L100
        L44:
            seekToPreviousMediaItem(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt());
            goto L100
        L45:
            seekForward(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt());
            goto L100
        L46:
            seekBack(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt());
            goto L100
        L47:
            seekToWithMediaItemIndex(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), r11.readInt(), r11.readLong());
            goto L100
        L48:
            seekTo(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), r11.readLong());
            goto L100
        L49:
            seekToDefaultPositionWithMediaItemIndex(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), r11.readInt());
            goto L100
        L50:
            seekToDefaultPosition(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt());
            goto L100
        L51:
            release(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt());
            goto L100
        L52:
            stop(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt());
            goto L100
        L53:
            setPlaylistMetadata(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), (Bundle) _Parcel.access$000(r11, Bundle.CREATOR));
            goto L100
        L54:
            addMediaItemsWithIndex(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), r11.readInt(), r11.readStrongBinder());
            goto L100
        L55:
            addMediaItems(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), r11.readStrongBinder());
            goto L100
        L56:
            addMediaItemWithIndex(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), r11.readInt(), (Bundle) _Parcel.access$000(r11, Bundle.CREATOR));
            goto L100
        L57:
            addMediaItem(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), (Bundle) _Parcel.access$000(r11, Bundle.CREATOR));
            goto L100
        L58:
            setPlaybackSpeed(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), r11.readFloat());
            goto L100
        L59:
            setPlaybackParameters(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), (Bundle) _Parcel.access$000(r11, Bundle.CREATOR));
            goto L100
        L60:
            prepare(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt());
            goto L100
        L61:
            pause(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt());
            goto L100
        L62:
            play(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt());
            goto L100
        L63:
            moveMediaItems(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), r11.readInt(), r11.readInt(), r11.readInt());
            goto L100
        L64:
            moveMediaItem(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), r11.readInt(), r11.readInt());
            goto L100
        L65:
            clearMediaItems(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt());
            goto L100
        L66:
            removeMediaItems(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), r11.readInt(), r11.readInt());
            goto L100
        L67:
            removeMediaItem(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), r11.readInt());
            goto L100
        L68:
            IMediaController r15 = IMediaController.Stub.asInterface(r11.readStrongBinder());
            int r22 = r11.readInt();
            if (r11.readInt() == 0) goto L71;
            r3 = true;
        L71:
            setShuffleModeEnabled(r15, r22, r3);
            goto L100
        L72:
            setRepeatMode(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), r11.readInt());
            goto L100
        L73:
            IMediaController r16 = IMediaController.Stub.asInterface(r11.readStrongBinder());
            int r23 = r11.readInt();
            Parcelable.Creator r32 = Bundle.CREATOR;
            onCustomCommand(r16, r23, (Bundle) _Parcel.access$000(r11, r32), (Bundle) _Parcel.access$000(r11, r32));
            goto L100
        L74:
            connect(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), (Bundle) _Parcel.access$000(r11, Bundle.CREATOR));
            goto L100
        L75:
            onControllerResult(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), (Bundle) _Parcel.access$000(r11, Bundle.CREATOR));
            goto L100
        L76:
            IMediaController r17 = IMediaController.Stub.asInterface(r11.readStrongBinder());
            int r24 = r11.readInt();
            if (r11.readInt() == 0) goto L79;
            r3 = true;
        L79:
            setPlayWhenReady(r17, r24, r3);
            goto L100
        L80:
            setMediaItemsWithStartIndex(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), r11.readStrongBinder(), r11.readInt(), r11.readLong());
            goto L100
        L81:
            IMediaController r18 = IMediaController.Stub.asInterface(r11.readStrongBinder());
            int r25 = r11.readInt();
            IBinder r42 = r11.readStrongBinder();
            if (r11.readInt() == 0) goto L84;
            r3 = true;
        L84:
            setMediaItemsWithResetPosition(r18, r25, r42, r3);
            goto L100
        L85:
            setMediaItems(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), r11.readStrongBinder());
            goto L100
        L86:
            IMediaController r19 = IMediaController.Stub.asInterface(r11.readStrongBinder());
            int r26 = r11.readInt();
            Bundle r43 = (Bundle) _Parcel.access$000(r11, Bundle.CREATOR);
            if (r11.readInt() == 0) goto L89;
            r3 = true;
        L89:
            setMediaItemWithResetPosition(r19, r26, r43, r3);
            goto L100
        L90:
            setMediaItemWithStartPosition(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), (Bundle) _Parcel.access$000(r11, Bundle.CREATOR), r11.readLong());
            goto L100
        L91:
            setMediaItem(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), (Bundle) _Parcel.access$000(r11, Bundle.CREATOR));
            goto L100
        L92:
            IMediaController r110 = IMediaController.Stub.asInterface(r11.readStrongBinder());
            int r27 = r11.readInt();
            if (r11.readInt() == 0) goto L95;
            r3 = true;
        L95:
            setDeviceMuted(r110, r27, r3);
            goto L100
        L96:
            decreaseDeviceVolume(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt());
            goto L100
        L97:
            increaseDeviceVolume(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt());
            goto L100
        L98:
            setDeviceVolume(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), r11.readInt());
            goto L100
        L99:
            setVolume(IMediaController.Stub.asInterface(r11.readStrongBinder()), r11.readInt(), r11.readFloat());
            goto L100
        }
    }

    public static class _Parcel {
        public static /* synthetic */ Object access$000(Parcel r0, Parcelable.Creator r1) {
            return readTypedObject(r0, r1);
        }

        private static <T> T readTypedObject(Parcel r1, Parcelable.Creator<T> r2) {
            if (r1.readInt() != 0) goto L5;
            return null;
        L5:
            return r2.createFromParcel(r1);
        }
    }

    void addMediaItem(IMediaController r1, int r2, Bundle r3);

    void addMediaItemWithIndex(IMediaController r1, int r2, int r3, Bundle r4);

    void addMediaItems(IMediaController r1, int r2, IBinder r3);

    void addMediaItemsWithIndex(IMediaController r1, int r2, int r3, IBinder r4);

    void clearMediaItems(IMediaController r1, int r2);

    void connect(IMediaController r1, int r2, Bundle r3);

    void decreaseDeviceVolume(IMediaController r1, int r2);

    void decreaseDeviceVolumeWithFlags(IMediaController r1, int r2, int r3);

    void flushCommandQueue(IMediaController r1);

    void getChildren(IMediaController r1, int r2, String r3, int r4, int r5, Bundle r6);

    void getItem(IMediaController r1, int r2, String r3);

    void getLibraryRoot(IMediaController r1, int r2, Bundle r3);

    void getSearchResult(IMediaController r1, int r2, String r3, int r4, int r5, Bundle r6);

    void increaseDeviceVolume(IMediaController r1, int r2);

    void increaseDeviceVolumeWithFlags(IMediaController r1, int r2, int r3);

    void moveMediaItem(IMediaController r1, int r2, int r3, int r4);

    void moveMediaItems(IMediaController r1, int r2, int r3, int r4, int r5);

    void onControllerResult(IMediaController r1, int r2, Bundle r3);

    void onCustomCommand(IMediaController r1, int r2, Bundle r3, Bundle r4);

    void pause(IMediaController r1, int r2);

    void play(IMediaController r1, int r2);

    void prepare(IMediaController r1, int r2);

    void release(IMediaController r1, int r2);

    void removeMediaItem(IMediaController r1, int r2, int r3);

    void removeMediaItems(IMediaController r1, int r2, int r3, int r4);

    void replaceMediaItem(IMediaController r1, int r2, int r3, Bundle r4);

    void replaceMediaItems(IMediaController r1, int r2, int r3, int r4, IBinder r5);

    void search(IMediaController r1, int r2, String r3, Bundle r4);

    void seekBack(IMediaController r1, int r2);

    void seekForward(IMediaController r1, int r2);

    void seekTo(IMediaController r1, int r2, long r3);

    void seekToDefaultPosition(IMediaController r1, int r2);

    void seekToDefaultPositionWithMediaItemIndex(IMediaController r1, int r2, int r3);

    void seekToNext(IMediaController r1, int r2);

    void seekToNextMediaItem(IMediaController r1, int r2);

    void seekToPrevious(IMediaController r1, int r2);

    void seekToPreviousMediaItem(IMediaController r1, int r2);

    void seekToWithMediaItemIndex(IMediaController r1, int r2, int r3, long r4);

    void setAudioAttributes(IMediaController r1, int r2, Bundle r3, boolean r4);

    void setDeviceMuted(IMediaController r1, int r2, boolean r3);

    void setDeviceMutedWithFlags(IMediaController r1, int r2, boolean r3, int r4);

    void setDeviceVolume(IMediaController r1, int r2, int r3);

    void setDeviceVolumeWithFlags(IMediaController r1, int r2, int r3, int r4);

    void setMediaItem(IMediaController r1, int r2, Bundle r3);

    void setMediaItemWithResetPosition(IMediaController r1, int r2, Bundle r3, boolean r4);

    void setMediaItemWithStartPosition(IMediaController r1, int r2, Bundle r3, long r4);

    void setMediaItems(IMediaController r1, int r2, IBinder r3);

    void setMediaItemsWithResetPosition(IMediaController r1, int r2, IBinder r3, boolean r4);

    void setMediaItemsWithStartIndex(IMediaController r1, int r2, IBinder r3, int r4, long r5);

    void setPlayWhenReady(IMediaController r1, int r2, boolean r3);

    void setPlaybackParameters(IMediaController r1, int r2, Bundle r3);

    void setPlaybackSpeed(IMediaController r1, int r2, float r3);

    void setPlaylistMetadata(IMediaController r1, int r2, Bundle r3);

    void setRating(IMediaController r1, int r2, Bundle r3);

    void setRatingWithMediaId(IMediaController r1, int r2, String r3, Bundle r4);

    void setRepeatMode(IMediaController r1, int r2, int r3);

    void setShuffleModeEnabled(IMediaController r1, int r2, boolean r3);

    void setTrackSelectionParameters(IMediaController r1, int r2, Bundle r3);

    void setVideoSurface(IMediaController r1, int r2, Surface r3);

    void setVolume(IMediaController r1, int r2, float r3);

    void stop(IMediaController r1, int r2);

    void subscribe(IMediaController r1, int r2, String r3, Bundle r4);

    void unsubscribe(IMediaController r1, int r2, String r3);
}
