package androidx.media3.common;

import androidx.media3.common.MediaMetadata;
import androidx.media3.common.util.Util;
import com.google.common.primitives.Longs;
import java.util.Arrays;
import java.util.List;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes3.dex */
public final class Metadata {
    private final Entry[] entries;
    public final long presentationTimeUs;

    public interface Entry {
        default byte[] getWrappedMetadataBytes() {
            return null;
        }

        default Format getWrappedMetadataFormat() {
            return null;
        }

        default void populateMediaMetadata(MediaMetadata.Builder r1) {
        }
    }

    public Metadata(long r2, List<? extends Entry> r4) {
        this(r2, (Entry[]) r4.toArray(new Entry[0]));
    }

    public Metadata(long r1, Entry... r3) {
        this.presentationTimeUs = r1;
        this.entries = r3;
    }

    public Metadata(List<? extends Entry> r2) {
        this((Entry[]) r2.toArray(new Entry[0]));
    }

    public Metadata(Entry... r3) {
        this(-9223372036854775807L, r3);
    }

    public Metadata copyWithAppendedEntries(Entry... r4) {
        if (r4.length != 0) goto L6;
        return this;
    L6:
        return new Metadata(this.presentationTimeUs, (Entry[]) Util.nullSafeArrayConcatenation(this.entries, r4));
    }

    public Metadata copyWithAppendedEntriesFrom(Metadata r1) {
        if (r1 != null) goto L5;
        return this;
    L5:
        return copyWithAppendedEntries(r1.entries);
    }

    public Metadata copyWithPresentationTimeUs(long r3) {
        if (this.presentationTimeUs != r3) goto L6;
        return this;
    L6:
        return new Metadata(r3, this.entries);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L15:
        return false;
    L8:
        if (Metadata.class != r5.getClass()) goto L15;
        Metadata r52 = (Metadata) r5;
        if (Arrays.equals(this.entries, r52.entries) == false) goto L15;
        if (this.presentationTimeUs != r52.presentationTimeUs) goto L15;
        return true;
    }

    public Entry get(int r1) {
        return this.entries[r1];
    }

    public int hashCode() {
        return (Arrays.hashCode(this.entries) * 31) + Longs.hashCode(this.presentationTimeUs);
    }

    public int length() {
        return this.entries.length;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder("entries=");
        r0.append(Arrays.toString(this.entries));
        if (this.presentationTimeUs != (-9223372036854775807L)) goto L5;
        String r5 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        r0.append(r5);
        return r0.toString();
    L5:
        r5 = ", presentationTimeUs=" + this.presentationTimeUs;
        goto L6
    }
}
