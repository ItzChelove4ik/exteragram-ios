package androidx.media;

import android.media.AudioAttributes;
import androidx.versionedparcelable.VersionedParcel;

/* loaded from: classes3.dex */
public class AudioAttributesImplApi26Parcelizer {
    public AudioAttributesImplApi26Parcelizer() {
    }

    public static AudioAttributesImplApi26 read(VersionedParcel r3) {
        AudioAttributesImplApi26 r0 = new AudioAttributesImplApi26();
        r0.mAudioAttributes = (AudioAttributes) r3.readParcelable(r0.mAudioAttributes, 1);
        r0.mLegacyStreamType = r3.readInt(r0.mLegacyStreamType, 2);
        return r0;
    }

    public static void write(AudioAttributesImplApi26 r2, VersionedParcel r3) {
        r3.setSerializationFlags(false, false);
        r3.writeParcelable(r2.mAudioAttributes, 1);
        r3.writeInt(r2.mLegacyStreamType, 2);
    }
}
