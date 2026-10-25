package android.support.v4.media;

import androidx.media.AudioAttributesImplBase;
import androidx.versionedparcelable.VersionedParcel;

/* loaded from: classes3.dex */
public final class AudioAttributesImplBaseParcelizer extends androidx.media.AudioAttributesImplBaseParcelizer {
    public AudioAttributesImplBaseParcelizer() {
    }

    public static AudioAttributesImplBase read(VersionedParcel r0) {
        return androidx.media.AudioAttributesImplBaseParcelizer.read(r0);
    }

    public static void write(AudioAttributesImplBase r0, VersionedParcel r1) {
        androidx.media.AudioAttributesImplBaseParcelizer.write(r0, r1);
    }
}
