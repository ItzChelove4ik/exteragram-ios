package androidx.media;

import androidx.versionedparcelable.VersionedParcel;

/* loaded from: classes3.dex */
public class AudioAttributesImplBaseParcelizer {
    public AudioAttributesImplBaseParcelizer() {
    }

    public static AudioAttributesImplBase read(VersionedParcel r3) {
        AudioAttributesImplBase r0 = new AudioAttributesImplBase();
        r0.mUsage = r3.readInt(r0.mUsage, 1);
        r0.mContentType = r3.readInt(r0.mContentType, 2);
        r0.mFlags = r3.readInt(r0.mFlags, 3);
        r0.mLegacyStream = r3.readInt(r0.mLegacyStream, 4);
        return r0;
    }

    public static void write(AudioAttributesImplBase r2, VersionedParcel r3) {
        r3.setSerializationFlags(false, false);
        r3.writeInt(r2.mUsage, 1);
        r3.writeInt(r2.mContentType, 2);
        r3.writeInt(r2.mFlags, 3);
        r3.writeInt(r2.mLegacyStream, 4);
    }
}
