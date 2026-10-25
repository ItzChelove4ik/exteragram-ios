package android.support.v4.media;

import androidx.media.AudioAttributesImplApi26;
import androidx.versionedparcelable.VersionedParcel;

/* loaded from: classes3.dex */
public final class AudioAttributesImplApi26Parcelizer extends androidx.media.AudioAttributesImplApi26Parcelizer {
    public AudioAttributesImplApi26Parcelizer() {
    }

    public static AudioAttributesImplApi26 read(VersionedParcel r0) {
        return androidx.media.AudioAttributesImplApi26Parcelizer.read(r0);
    }

    public static void write(AudioAttributesImplApi26 r0, VersionedParcel r1) {
        androidx.media.AudioAttributesImplApi26Parcelizer.write(r0, r1);
    }
}
