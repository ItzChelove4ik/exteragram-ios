package android.support.v4.media;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.media.MediaMetadata;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.util.Log;
import androidx.collection.ArrayMap;
import androidx.mediarouter.media.MediaItemMetadata;
import com.sun.jna.Native$$ExternalSyntheticBUOutline0;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes3.dex */
public final class MediaMetadataCompat implements Parcelable {
    public static final Parcelable.Creator<MediaMetadataCompat> CREATOR = null;
    static final ArrayMap<String, Integer> METADATA_KEYS_TYPE = null;
    private static final String[] PREFERRED_BITMAP_ORDER = null;
    private static final String[] PREFERRED_DESCRIPTION_ORDER = null;
    private static final String[] PREFERRED_URI_ORDER = null;
    final Bundle mBundle;
    private MediaDescriptionCompat mDescription;
    private MediaMetadata mMetadataFwk;

    /* renamed from: android.support.v4.media.MediaMetadataCompat$1, reason: invalid class name */
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
        private final Bundle mBundle;

        public Builder() {
            this.mBundle = new Bundle();
        }

        public Builder(MediaMetadataCompat r2) {
            Bundle r0 = new Bundle(r2.mBundle);
            this.mBundle = r0;
            MediaSessionCompat.ensureClassLoader(r0);
        }

        public MediaMetadataCompat build() {
            return new MediaMetadataCompat(this.mBundle);
        }

        public Builder putBitmap(String r3, Bitmap r4) {
            ArrayMap<String, Integer> r0 = MediaMetadataCompat.METADATA_KEYS_TYPE;
            if (r0.containsKey(r3) == true) goto L5;
        L9:
            this.mBundle.putParcelable(r3, r4);
            return this;
        L5:
            if (r0.get(r3).intValue() == 2) goto L9;
            Native$$ExternalSyntheticBUOutline0.m("The ", r3, " key cannot be used to put a Bitmap");
            return null;
        }

        public Builder putLong(String r3, long r4) {
            ArrayMap<String, Integer> r0 = MediaMetadataCompat.METADATA_KEYS_TYPE;
            if (r0.containsKey(r3) == true) goto L5;
        L9:
            this.mBundle.putLong(r3, r4);
            return this;
        L5:
            if (r0.get(r3).intValue() == 0) goto L9;
            Native$$ExternalSyntheticBUOutline0.m("The ", r3, " key cannot be used to put a long");
            return null;
        }

        public Builder putString(String r3, String r4) {
            ArrayMap<String, Integer> r0 = MediaMetadataCompat.METADATA_KEYS_TYPE;
            if (r0.containsKey(r3) == true) goto L5;
        L9:
            this.mBundle.putCharSequence(r3, r4);
            return this;
        L5:
            if (r0.get(r3).intValue() == 1) goto L9;
            Native$$ExternalSyntheticBUOutline0.m("The ", r3, " key cannot be used to put a String");
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
        PREFERRED_DESCRIPTION_ORDER = new String[]{MediaItemMetadata.KEY_TITLE, MediaItemMetadata.KEY_ARTIST, "android.media.metadata.ALBUM", MediaItemMetadata.KEY_ALBUM_ARTIST, "android.media.metadata.WRITER", MediaItemMetadata.KEY_AUTHOR, MediaItemMetadata.KEY_COMPOSER};
        PREFERRED_BITMAP_ORDER = new String[]{"android.media.metadata.DISPLAY_ICON", "android.media.metadata.ART", "android.media.metadata.ALBUM_ART"};
        PREFERRED_URI_ORDER = new String[]{"android.media.metadata.DISPLAY_ICON_URI", "android.media.metadata.ART_URI", "android.media.metadata.ALBUM_ART_URI"};
        CREATOR = new AnonymousClass1();
    }

    public MediaMetadataCompat(Bundle r2) {
        Bundle r0 = new Bundle(r2);
        this.mBundle = r0;
        MediaSessionCompat.ensureClassLoader(r0);
    }

    public MediaMetadataCompat(Parcel r2) {
        this.mBundle = r2.readBundle(MediaSessionCompat.class.getClassLoader());
    }

    public static MediaMetadataCompat fromMediaMetadata(Object r2) {
        if (r2 == null) goto L5;
        Parcel r0 = Parcel.obtain();
        MediaMetadata r22 = (MediaMetadata) r2;
        r22.writeToParcel(r0, 0);
        r0.setDataPosition(0);
        MediaMetadataCompat r1 = CREATOR.createFromParcel(r0);
        r0.recycle();
        r1.mMetadataFwk = r22;
        return r1;
    L5:
        return null;
    }

    public boolean containsKey(String r1) {
        return this.mBundle.containsKey(r1);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Bitmap getBitmap(String r2) {
        return (Bitmap) this.mBundle.getParcelable(r2);
    L4:
        e = move-exception;
        Log.w("MediaMetadata", "Failed to retrieve a key as Bitmap.", e);
        return null;
    }

    public MediaDescriptionCompat getDescription() {
        MediaDescriptionCompat r0 = this.mDescription;
        if (r0 == null) goto L5;
        return r0;
    L5:
        String r02 = getString("android.media.metadata.MEDIA_ID");
        CharSequence[] r2 = new CharSequence[3];
        CharSequence r3 = getText("android.media.metadata.DISPLAY_TITLE");
        if (TextUtils.isEmpty(r3) == true) goto L8;
        r2[0] = r3;
        r2[1] = getText("android.media.metadata.DISPLAY_SUBTITLE");
        r2[2] = getText("android.media.metadata.DISPLAY_DESCRIPTION");
    L16:
        int r1 = 0;
    L17:
        String[] r32 = PREFERRED_BITMAP_ORDER;
        Uri r8 = null;
        if (r1 >= r32.length) goto L23;
        Bitmap r33 = getBitmap(r32[r1]);
        if (r33 != null) goto L24;
        r1 = r1 + 1;
    L24:
        int r12 = 0;
    L25:
        String[] r4 = PREFERRED_URI_ORDER;
        if (r12 >= r4.length) goto L31;
        String r42 = getString(r4[r12]);
        if (TextUtils.isEmpty(r42) == false) goto L29;
        r12 = r12 + 1;
        goto L25
    L29:
        Uri r13 = Uri.parse(r42);
    L32:
        String r43 = getString("android.media.metadata.MEDIA_URI");
        if (TextUtils.isEmpty(r43) == true) goto L35;
        r8 = Uri.parse(r43);
    L35:
        MediaDescriptionCompat.Builder r44 = new MediaDescriptionCompat.Builder();
        r44.setMediaId(r02);
        r44.setTitle(r2[0]);
        r44.setSubtitle(r2[1]);
        r44.setDescription(r2[2]);
        r44.setIconBitmap(r33);
        r44.setIconUri(r13);
        r44.setMediaUri(r8);
        Bundle r03 = new Bundle();
        if (this.mBundle.containsKey("android.media.metadata.BT_FOLDER_TYPE") == false) goto L39;
        r03.putLong("android.media.extra.BT_FOLDER_TYPE", getLong("android.media.metadata.BT_FOLDER_TYPE"));
    L39:
        if (this.mBundle.containsKey("android.media.metadata.DOWNLOAD_STATUS") == false) goto L42;
        r03.putLong("android.media.extra.DOWNLOAD_STATUS", getLong("android.media.metadata.DOWNLOAD_STATUS"));
    L42:
        if (r03.isEmpty() == true) goto L44;
        r44.setExtras(r03);
    L44:
        MediaDescriptionCompat r04 = r44.build();
        this.mDescription = r04;
        return r04;
    L31:
        r13 = null;
        goto L32
    L23:
        r33 = null;
        goto L24
    L8:
        int r34 = 0;
        int r45 = 0;
    L9:
        if (r34 >= 3) goto L16;
        String[] r82 = PREFERRED_DESCRIPTION_ORDER;
        if (r45 >= r82.length) goto L16;
        int r9 = r45 + 1;
        CharSequence r46 = getText(r82[r45]);
        if (TextUtils.isEmpty(r46) == true) goto L15;
        r2[r34] = r46;
        r34 = r34 + 1;
    L15:
        r45 = r9;
        goto L9
    }

    public long getLong(String r3) {
        return this.mBundle.getLong(r3, 0);
    }

    public Object getMediaMetadata() {
        if (this.mMetadataFwk != null) goto L6;
        Parcel r0 = Parcel.obtain();
        writeToParcel(r0, 0);
        r0.setDataPosition(0);
        this.mMetadataFwk = (MediaMetadata) MediaMetadata.CREATOR.createFromParcel(r0);
        r0.recycle();
    L6:
        return this.mMetadataFwk;
    }

    public String getString(String r1) {
        CharSequence r0 = this.mBundle.getCharSequence(r1);
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.toString();
    }

    public CharSequence getText(String r1) {
        return this.mBundle.getCharSequence(r1);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r1, int r2) {
        r1.writeBundle(this.mBundle);
    }
}
