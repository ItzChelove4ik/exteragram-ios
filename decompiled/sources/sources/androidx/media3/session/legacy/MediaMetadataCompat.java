package androidx.media3.session.legacy;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.media.MediaMetadata;
import android.media.Rating;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.collection.ArrayMap;
import androidx.media3.common.util.Assertions;
import androidx.mediarouter.media.MediaItemMetadata;
import com.sun.jna.Native$$ExternalSyntheticBUOutline0;
import java.util.Iterator;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes4.dex */
public final class MediaMetadataCompat implements Parcelable {
    public static final Parcelable.Creator<MediaMetadataCompat> CREATOR = null;
    static final ArrayMap<String, Integer> METADATA_KEYS_TYPE = null;
    public static final String[] PREFERRED_DESCRIPTION_ORDER = null;
    final Bundle bundle;
    private MediaMetadata metadataFwk;

    /* renamed from: androidx.media3.session.legacy.MediaMetadataCompat$1, reason: invalid class name */
    public class AnonymousClass1 implements Parcelable.Creator<MediaMetadataCompat> {
        public AnonymousClass1() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaMetadataCompat createFromParcel(Parcel r1) {
            return new MediaMetadataCompat(r1);
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ MediaMetadataCompat createFromParcel(Parcel r1) {
            return createFromParcel(r1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaMetadataCompat[] newArray(int r1) {
            return new MediaMetadataCompat[r1];
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ MediaMetadataCompat[] newArray(int r1) {
            return newArray(r1);
        }
    }

    public static final class Builder {
        private final Bundle bundle;

        public Builder() {
            this.bundle = new Bundle();
        }

        public MediaMetadataCompat build() {
            return new MediaMetadataCompat(this.bundle);
        }

        public Builder putBitmap(String r3, Bitmap r4) {
            Integer r0 = MediaMetadataCompat.METADATA_KEYS_TYPE.get(r3);
            if (r0 != null) goto L5;
        L9:
            this.bundle.putParcelable(r3, r4);
            return this;
        L5:
            if (r0.intValue() == 2) goto L9;
            Native$$ExternalSyntheticBUOutline0.m("The ", r3, " key cannot be used to put a Bitmap");
            return null;
        }

        public Builder putLong(String r2, long r3) {
            Integer r0 = MediaMetadataCompat.METADATA_KEYS_TYPE.get(r2);
            if (r0 != null) goto L5;
        L9:
            this.bundle.putLong(r2, r3);
            return this;
        L5:
            if (r0.intValue() == 0) goto L9;
            Native$$ExternalSyntheticBUOutline0.m("The ", r2, " key cannot be used to put a long");
            return null;
        }

        public Builder putRating(String r3, RatingCompat r4) {
            Integer r0 = MediaMetadataCompat.METADATA_KEYS_TYPE.get(r3);
            if (r0 != null) goto L5;
        L9:
            this.bundle.putParcelable(r3, (Parcelable) r4.getRating());
            return this;
        L5:
            if (r0.intValue() == 3) goto L9;
            Native$$ExternalSyntheticBUOutline0.m("The ", r3, " key cannot be used to put a Rating");
            return null;
        }

        public Builder putString(String r3, String r4) {
            Integer r0 = MediaMetadataCompat.METADATA_KEYS_TYPE.get(r3);
            if (r0 != null) goto L5;
        L9:
            this.bundle.putCharSequence(r3, r4);
            return this;
        L5:
            if (r0.intValue() == 1) goto L9;
            Native$$ExternalSyntheticBUOutline0.m("The ", r3, " key cannot be used to put a String");
            return null;
        }

        public Builder putText(String r3, CharSequence r4) {
            Integer r0 = MediaMetadataCompat.METADATA_KEYS_TYPE.get(r3);
            if (r0 != null) goto L5;
        L9:
            this.bundle.putCharSequence(r3, r4);
            return this;
        L5:
            if (r0.intValue() == 1) goto L9;
            Native$$ExternalSyntheticBUOutline0.m("The ", r3, " key cannot be used to put a CharSequence");
            return null;
        }
    }

    static {
        ArrayMap<String, Integer> r0 = new ArrayMap();
        METADATA_KEYS_TYPE = r0;
        r0.put(MediaItemMetadata.KEY_TITLE, 1);
        r0.put(MediaItemMetadata.KEY_ARTIST, 1);
        r0.put(MediaItemMetadata.KEY_DURATION, 0);
        r0.put("android.media.metadata.ALBUM", 1);
        r0.put(MediaItemMetadata.KEY_AUTHOR, 1);
        r0.put("android.media.metadata.WRITER", 1);
        r0.put(MediaItemMetadata.KEY_COMPOSER, 1);
        r0.put("android.media.metadata.COMPILATION", 1);
        r0.put("android.media.metadata.DATE", 1);
        r0.put(MediaItemMetadata.KEY_YEAR, 0);
        r0.put("android.media.metadata.GENRE", 1);
        r0.put(MediaItemMetadata.KEY_TRACK_NUMBER, 0);
        r0.put("android.media.metadata.NUM_TRACKS", 0);
        r0.put(MediaItemMetadata.KEY_DISC_NUMBER, 0);
        r0.put(MediaItemMetadata.KEY_ALBUM_ARTIST, 1);
        r0.put("android.media.metadata.ART", 2);
        r0.put("android.media.metadata.ART_URI", 1);
        r0.put("android.media.metadata.ALBUM_ART", 2);
        r0.put("android.media.metadata.ALBUM_ART_URI", 1);
        r0.put("android.media.metadata.USER_RATING", 3);
        r0.put("android.media.metadata.RATING", 3);
        r0.put("android.media.metadata.DISPLAY_TITLE", 1);
        r0.put("android.media.metadata.DISPLAY_SUBTITLE", 1);
        r0.put("android.media.metadata.DISPLAY_DESCRIPTION", 1);
        r0.put("android.media.metadata.DISPLAY_ICON", 2);
        r0.put("android.media.metadata.DISPLAY_ICON_URI", 1);
        r0.put("android.media.metadata.MEDIA_ID", 1);
        r0.put("android.media.metadata.BT_FOLDER_TYPE", 0);
        r0.put("android.media.metadata.MEDIA_URI", 1);
        r0.put("android.media.metadata.ADVERTISEMENT", 0);
        r0.put("android.media.metadata.DOWNLOAD_STATUS", 0);
        PREFERRED_DESCRIPTION_ORDER = new String[]{MediaItemMetadata.KEY_TITLE, MediaItemMetadata.KEY_ARTIST, "android.media.metadata.ALBUM", MediaItemMetadata.KEY_ALBUM_ARTIST, "android.media.metadata.WRITER", MediaItemMetadata.KEY_AUTHOR, MediaItemMetadata.KEY_COMPOSER, "android.media.metadata.DISPLAY_SUBTITLE", "android.media.metadata.DISPLAY_DESCRIPTION"};
        CREATOR = new AnonymousClass1();
    }

    public MediaMetadataCompat(Bundle r2) {
        Bundle r0 = new Bundle(r2);
        this.bundle = r0;
        MediaSessionCompat.ensureClassLoader(r0);
    }

    public MediaMetadataCompat(Parcel r2) {
        this.bundle = (Bundle) Assertions.checkNotNull(r2.readBundle(MediaSessionCompat.class.getClassLoader()));
    }

    public boolean containsKey(String r1) {
        return this.bundle.containsKey(r1);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public long getLong(String r3) {
        return this.bundle.getLong(r3, 0);
    }

    public MediaMetadata getMediaMetadata() {
        if (this.metadataFwk != null) goto L33;
        MediaMetadata.Builder r0 = new MediaMetadata.Builder();
        Iterator<String> r1 = this.bundle.keySet().iterator();
    L6:
        if (r1.hasNext() == false) goto L31;
        String r2 = r1.next();
        Integer r3 = METADATA_KEYS_TYPE.get(r2);
        if (r3 != null) goto L10;
        r3 = -1;
    L10:
        int r32 = r3.intValue();
        if (r32 != 0) goto L13;
        r0.putLong(r2, this.bundle.getLong(r2));
        goto L6
    L13:
        if (r32 != 1) goto L15;
        r0.putText(r2, this.bundle.getString(r2));
        goto L6
    L15:
        if (r32 != 2) goto L16;
        r0.putBitmap(r2, (Bitmap) this.bundle.getParcelable(r2));
        goto L6
    L16:
        Bundle r4 = this.bundle;
        if (r32 != 3) goto L18;
        r0.putRating(r2, (Rating) r4.getParcelable(r2));
        goto L6
    L18:
        Object r33 = r4.get(r2);
        if (r33 == null) goto L26;
        if ((r33 instanceof CharSequence) == true) goto L26;
        if ((r33 instanceof Long) == false) goto L6;
        r0.putLong(r2, ((Long) r33).longValue());
    L26:
        r0.putText(r2, (CharSequence) r33);
        goto L6
    L31:
        this.metadataFwk = r0.build();
    L33:
        return this.metadataFwk;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r1, int r2) {
        r1.writeBundle(this.bundle);
    }
}
