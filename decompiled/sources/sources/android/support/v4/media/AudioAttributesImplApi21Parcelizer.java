package android.support.v4.media;

import androidx.media.AudioAttributesImplApi21;
import androidx.versionedparcelable.VersionedParcel;

/* loaded from: classes3.dex */
public final class AudioAttributesImplApi21Parcelizer extends androidx.media.AudioAttributesImplApi21Parcelizer {
    public AudioAttributesImplApi21Parcelizer() {
    }

    public static AudioAttributesImplApi21 read(VersionedParcel r0) {
        return androidx.media.AudioAttributesImplApi21Parcelizer.read(r0);
    }

    public static void write(AudioAttributesImplApi21 r0, VersionedParcel r1) {
        androidx.media.AudioAttributesImplApi21Parcelizer.write(r0, r1);
    }
}
