package com.google.android.gms.cast;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class MediaMetadata extends AbstractSafeParcelable {
    public static final Parcelable.Creator<MediaMetadata> CREATOR = null;
    public static final String KEY_ALBUM_ARTIST = "com.google.android.gms.cast.metadata.ALBUM_ARTIST";
    public static final String KEY_ALBUM_TITLE = "com.google.android.gms.cast.metadata.ALBUM_TITLE";
    public static final String KEY_ARTIST = "com.google.android.gms.cast.metadata.ARTIST";
    public static final String KEY_BOOK_TITLE = "com.google.android.gms.cast.metadata.BOOK_TITLE";
    public static final String KEY_BROADCAST_DATE = "com.google.android.gms.cast.metadata.BROADCAST_DATE";
    public static final String KEY_CHAPTER_NUMBER = "com.google.android.gms.cast.metadata.CHAPTER_NUMBER";
    public static final String KEY_CHAPTER_TITLE = "com.google.android.gms.cast.metadata.CHAPTER_TITLE";
    public static final String KEY_COMPOSER = "com.google.android.gms.cast.metadata.COMPOSER";
    public static final String KEY_CREATION_DATE = "com.google.android.gms.cast.metadata.CREATION_DATE";
    public static final String KEY_DISC_NUMBER = "com.google.android.gms.cast.metadata.DISC_NUMBER";
    public static final String KEY_EPISODE_NUMBER = "com.google.android.gms.cast.metadata.EPISODE_NUMBER";
    public static final String KEY_HEIGHT = "com.google.android.gms.cast.metadata.HEIGHT";
    public static final String KEY_LOCATION_LATITUDE = "com.google.android.gms.cast.metadata.LOCATION_LATITUDE";
    public static final String KEY_LOCATION_LONGITUDE = "com.google.android.gms.cast.metadata.LOCATION_LONGITUDE";
    public static final String KEY_LOCATION_NAME = "com.google.android.gms.cast.metadata.LOCATION_NAME";
    public static final String KEY_QUEUE_ITEM_ID = "com.google.android.gms.cast.metadata.QUEUE_ITEM_ID";
    public static final String KEY_RELEASE_DATE = "com.google.android.gms.cast.metadata.RELEASE_DATE";
    public static final String KEY_SEASON_NUMBER = "com.google.android.gms.cast.metadata.SEASON_NUMBER";
    public static final String KEY_SECTION_DURATION = "com.google.android.gms.cast.metadata.SECTION_DURATION";
    public static final String KEY_SECTION_START_ABSOLUTE_TIME = "com.google.android.gms.cast.metadata.SECTION_START_ABSOLUTE_TIME";
    public static final String KEY_SECTION_START_TIME_IN_CONTAINER = "com.google.android.gms.cast.metadata.SECTION_START_TIME_IN_CONTAINER";
    public static final String KEY_SECTION_START_TIME_IN_MEDIA = "com.google.android.gms.cast.metadata.SECTION_START_TIME_IN_MEDIA";
    public static final String KEY_SERIES_TITLE = "com.google.android.gms.cast.metadata.SERIES_TITLE";
    public static final String KEY_STUDIO = "com.google.android.gms.cast.metadata.STUDIO";
    public static final String KEY_SUBTITLE = "com.google.android.gms.cast.metadata.SUBTITLE";
    public static final String KEY_TITLE = "com.google.android.gms.cast.metadata.TITLE";
    public static final String KEY_TRACK_NUMBER = "com.google.android.gms.cast.metadata.TRACK_NUMBER";
    public static final String KEY_WIDTH = "com.google.android.gms.cast.metadata.WIDTH";
    public static final int MEDIA_TYPE_AUDIOBOOK_CHAPTER = 5;
    public static final int MEDIA_TYPE_GENERIC = 0;
    public static final int MEDIA_TYPE_MOVIE = 1;
    public static final int MEDIA_TYPE_MUSIC_TRACK = 3;
    public static final int MEDIA_TYPE_PHOTO = 4;
    public static final int MEDIA_TYPE_TV_SHOW = 2;
    public static final int MEDIA_TYPE_USER = 100;
    private static final String[] zzb = null;
    private static final zzbu zzc = null;
    final Bundle zza;
    private final List zzd;
    private int zze;
    private final Writer zzf;

    public class Writer {
        final /* synthetic */ MediaMetadata zza;

        public Writer(MediaMetadata r1) {
            Objects.requireNonNull(r1);
            this.zza = r1;
        }

        public Object getRawValue(String r1) {
            return this.zza.zza.get(r1);
        }

        public void remove(String r1) {
            this.zza.zza.remove(r1);
        }

        public void setMediaType(int r1) {
            this.zza.zzc(r1);
        }
    }

    static {
        zzb = new String[]{"none", "String", "int", "double", "ISO-8601 date String", "Time in milliseconds as long"};
        CREATOR = new zzbv();
        zzbu r0 = new zzbu();
        r0.zza(KEY_CREATION_DATE, "creationDateTime", 4);
        r0.zza(KEY_RELEASE_DATE, "releaseDate", 4);
        r0.zza(KEY_BROADCAST_DATE, "originalAirdate", 4);
        r0.zza(KEY_TITLE, "title", 1);
        r0.zza(KEY_SUBTITLE, MediaTrack.ROLE_SUBTITLE, 1);
        r0.zza(KEY_ARTIST, "artist", 1);
        r0.zza(KEY_ALBUM_ARTIST, "albumArtist", 1);
        r0.zza(KEY_ALBUM_TITLE, "albumName", 1);
        r0.zza(KEY_COMPOSER, "composer", 1);
        r0.zza(KEY_DISC_NUMBER, "discNumber", 2);
        r0.zza(KEY_TRACK_NUMBER, "trackNumber", 2);
        r0.zza(KEY_SEASON_NUMBER, "season", 2);
        r0.zza(KEY_EPISODE_NUMBER, "episode", 2);
        r0.zza(KEY_SERIES_TITLE, "seriesTitle", 1);
        r0.zza(KEY_STUDIO, "studio", 1);
        r0.zza(KEY_WIDTH, "width", 2);
        r0.zza(KEY_HEIGHT, "height", 2);
        r0.zza(KEY_LOCATION_NAME, "location", 1);
        r0.zza(KEY_LOCATION_LATITUDE, "latitude", 3);
        r0.zza(KEY_LOCATION_LONGITUDE, "longitude", 3);
        r0.zza(KEY_SECTION_DURATION, "sectionDuration", 5);
        r0.zza(KEY_SECTION_START_TIME_IN_MEDIA, "sectionStartTimeInMedia", 5);
        r0.zza(KEY_SECTION_START_ABSOLUTE_TIME, "sectionStartAbsoluteTime", 5);
        r0.zza(KEY_SECTION_START_TIME_IN_CONTAINER, "sectionStartTimeInContainer", 5);
        r0.zza(KEY_QUEUE_ITEM_ID, "queueItemId", 2);
        r0.zza(KEY_BOOK_TITLE, "bookTitle", 1);
        r0.zza(KEY_CHAPTER_NUMBER, "chapterNumber", 2);
        r0.zza(KEY_CHAPTER_TITLE, "chapterTitle", 1);
        zzc = r0;
    }

    public MediaMetadata() {
        this(0);
    }

    public MediaMetadata(int r3) {
        this(new ArrayList(), new Bundle(), r3);
    }

    public MediaMetadata(List r2, Bundle r3, int r4) {
        this.zzf = new Writer(this);
        this.zzd = r2;
        this.zza = r3;
        this.zze = r4;
    }

    public static int getTypeForKey(String r1) {
        if (TextUtils.isEmpty(r1) == false) goto L5;
        f$$ExternalSyntheticBUOutline1.m("null and empty keys are not allowed");
        return 0;
    L5:
        return zzc.zzd(r1);
    }

    public static void throwIfWrongType(String r3, int r4) {
        if (TextUtils.isEmpty(r3) == true) goto L11;
        int r0 = zzc.zzd(r3);
        if (r0 == r4) goto L10;
        if (r0 == 0) goto L13;
        String r42 = zzb[r4];
        int r1 = String.valueOf(r3).length();
        StringBuilder r2 = new StringBuilder((r1 + 21) + String.valueOf(r42).length());
        r2.append("Value for ");
        r2.append(r3);
        r2.append(" must be a ");
        r2.append(r42);
        throw new IllegalArgumentException(r2.toString());
    L13:
        return;
    L10:
        return;
    L11:
        f$$ExternalSyntheticBUOutline1.m("null and empty keys are not allowed");
    }

    private final boolean zzd(Bundle r8, Bundle r9) {
        if (r8.size() == r9.size()) goto L5;
        return false;
    L5:
        Iterator<String> r0 = r8.keySet().iterator();
    L7:
        if (r0.hasNext() == false) goto L23;
        String r1 = r0.next();
        Object r3 = r8.get(r1);
        Object r4 = r9.get(r1);
        if ((r3 instanceof Bundle) == true) goto L11;
    L15:
        if (r3 == null) goto L16;
        if (r3.equals(r4) == true) goto L7;
        return false;
    L16:
        if (r4 != null) goto L19;
        if (r9.containsKey(r1) == true) goto L7;
    L19:
        return false;
    L11:
        if ((r4 instanceof Bundle) == false) goto L15;
        if (zzd((Bundle) r3, (Bundle) r4) == true) goto L15;
        return false;
    L23:
        return true;
    }

    public void addImage(WebImage r1) {
        this.zzd.add(r1);
    }

    public void clear() {
        this.zza.clear();
        this.zzd.clear();
    }

    public void clearImages() {
        this.zzd.clear();
    }

    public boolean containsKey(String r1) {
        return this.zza.containsKey(r1);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof MediaMetadata) == true) goto L8;
        return false;
    L8:
        MediaMetadata r52 = (MediaMetadata) r5;
        if (zzd(this.zza, r52.zza) == true) goto L11;
    L13:
        return false;
    L11:
        if (this.zzd.equals(r52.zzd) == false) goto L13;
        return true;
    }

    public Calendar getDate(String r2) {
        throwIfWrongType(r2, 4);
        String r1 = this.zza.getString(r2);
        if (r1 != null) goto L5;
        return null;
    L5:
        return com.google.android.gms.cast.internal.media.zza.zzd(r1);
    }

    public String getDateAsString(String r2) {
        throwIfWrongType(r2, 4);
        return this.zza.getString(r2);
    }

    public double getDouble(String r2) {
        throwIfWrongType(r2, 3);
        return this.zza.getDouble(r2);
    }

    public List<WebImage> getImages() {
        return this.zzd;
    }

    public int getInt(String r2) {
        throwIfWrongType(r2, 2);
        return this.zza.getInt(r2);
    }

    public int getMediaType() {
        return this.zze;
    }

    public String getString(String r2) {
        throwIfWrongType(r2, 1);
        return this.zza.getString(r2);
    }

    public long getTimeMillis(String r2) {
        throwIfWrongType(r2, 5);
        return this.zza.getLong(r2);
    }

    public Writer getWriter() {
        return this.zzf;
    }

    public boolean hasImages() {
        List r0 = this.zzd;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isEmpty() == true) goto L10;
        return true;
    L10:
        return false;
    }

    public int hashCode() {
        Bundle r0 = this.zza;
        int r1 = 17;
        if (r0 == null) goto L13;
        Iterator<String> r2 = r0.keySet().iterator();
    L6:
        if (r2.hasNext() == false) goto L13;
        Object r3 = r0.get(r2.next());
        int r12 = r1 * 31;
        if (r3 == null) goto L10;
        int r32 = r3.hashCode();
    L11:
        r1 = r12 + r32;
        goto L6
    L10:
        r32 = 0;
    L13:
        return (r1 * 31) + this.zzd.hashCode();
    }

    public Set<String> keySet() {
        return this.zza.keySet();
    }

    public void putDate(String r2, Calendar r3) {
        throwIfWrongType(r2, 4);
        String r32 = com.google.android.gms.cast.internal.media.zza.zzc(r3);
        this.zza.putString(r2, r32);
    }

    public void putDouble(String r2, double r3) {
        throwIfWrongType(r2, 3);
        this.zza.putDouble(r2, r3);
    }

    public void putInt(String r2, int r3) {
        throwIfWrongType(r2, 2);
        this.zza.putInt(r2, r3);
    }

    public void putString(String r2, String r3) {
        throwIfWrongType(r2, 1);
        this.zza.putString(r2, r3);
    }

    public void putTimeMillis(String r2, long r3) {
        throwIfWrongType(r2, 5);
        this.zza.putLong(r2, r3);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeTypedList(r4, 2, getImages(), false);
        SafeParcelWriter.writeBundle(r4, 3, this.zza, false);
        SafeParcelWriter.writeInt(r4, 4, getMediaType());
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }

    public final JSONObject zza() {
        JSONObject r1 = new JSONObject();
        r1.put("metadataType", this.zze);     // Catch: JSONException -> L57
    L4:
        JSONArray r2 = com.google.android.gms.cast.internal.media.zza.zzb(this.zzd);
        if (r2.length() != 0) goto L64;
    L7:
        ArrayList r22 = new ArrayList();
        int r3 = this.zze;
        if (r3 == 0) goto L20;
        if (r3 == 1) goto L19;
        if (r3 == 2) goto L18;
        if (r3 == 3) goto L17;
        if (r3 == 4) goto L16;
        if (r3 != 5) goto L21;
        Collections.addAll(r22, new String[]{KEY_CHAPTER_TITLE, KEY_CHAPTER_NUMBER, KEY_TITLE, KEY_BOOK_TITLE, KEY_SUBTITLE});
    L21:
        Collections.addAll(r22, new String[]{KEY_SECTION_DURATION, KEY_SECTION_START_TIME_IN_MEDIA, KEY_SECTION_START_ABSOLUTE_TIME, KEY_SECTION_START_TIME_IN_CONTAINER, KEY_QUEUE_ITEM_ID});
        int r32 = r22.size();     // Catch: JSONException -> L59
        int r4 = 0;
    L23:
        if (r4 >= r32) goto L41;
        Object r9 = r22.get(r4);     // Catch: JSONException -> L59
        r4 = r4 + 1;     // Catch: JSONException -> L59
        String r92 = (String) r9;     // Catch: JSONException -> L59
        if (r92 == null) goto L23;
        Bundle r11 = this.zza;     // Catch: JSONException -> L59
        if (r11.containsKey(r92) == false) goto L23;
        zzbu r12 = zzc;     // Catch: JSONException -> L59
        String r13 = r12.zzb(r92);     // Catch: JSONException -> L59
        if (r13 == null) goto L23;
        int r122 = r12.zzd(r92);     // Catch: JSONException -> L59
        if (r122 == 1) goto L40;
        if (r122 != 2) goto L33;
        r1.put(r13, r11.getInt(r92));     // Catch: JSONException -> L59
        goto L23
    L33:
        if (r122 != 3) goto L34;
        r1.put(r13, r11.getDouble(r92));     // Catch: JSONException -> L59
        goto L23
    L34:
        if (r122 == 4) goto L40;
        if (r122 != 5) goto L23;
        r1.put(r13, CastUtils.millisecToSec(r11.getLong(r92)));     // Catch: JSONException -> L59
    L40:
        r1.put(r13, r11.getString(r92));     // Catch: JSONException -> L59
        goto L23
    L41:
        Bundle r0 = this.zza;     // Catch: JSONException -> L59
        Iterator<String> r23 = r0.keySet().iterator();     // Catch: JSONException -> L59
    L42:
        if (r23.hasNext() == false) goto L56;
        String r33 = r23.next();     // Catch: JSONException -> L59
        if (r33.startsWith("com.google.") == true) goto L42;
        Object r42 = r0.get(r33);     // Catch: JSONException -> L59
        if ((r42 instanceof String) == true) goto L48;
        if ((r42 instanceof Integer) == true) goto L51;
        if ((r42 instanceof Double) == false) goto L42;
        r1.put(r33, r42);     // Catch: JSONException -> L59
        goto L42
    L51:
        r1.put(r33, r42);     // Catch: JSONException -> L59
        goto L42
    L48:
        r1.put(r33, r42);     // Catch: JSONException -> L59
    L56:
        return r1;
    L16:
        Collections.addAll(r22, new String[]{KEY_TITLE, KEY_ARTIST, KEY_LOCATION_NAME, KEY_LOCATION_LATITUDE, KEY_LOCATION_LONGITUDE, KEY_WIDTH, KEY_HEIGHT, KEY_CREATION_DATE});
        goto L21
    L17:
        Collections.addAll(r22, new String[]{KEY_TITLE, KEY_ARTIST, KEY_ALBUM_TITLE, KEY_ALBUM_ARTIST, KEY_COMPOSER, KEY_TRACK_NUMBER, KEY_DISC_NUMBER, KEY_RELEASE_DATE});
        goto L21
    L18:
        Collections.addAll(r22, new String[]{KEY_TITLE, KEY_SERIES_TITLE, KEY_SEASON_NUMBER, KEY_EPISODE_NUMBER, KEY_BROADCAST_DATE});
        goto L21
    L19:
        Collections.addAll(r22, new String[]{KEY_TITLE, KEY_STUDIO, KEY_SUBTITLE, KEY_RELEASE_DATE});
        goto L21
    L20:
        Collections.addAll(r22, new String[]{KEY_TITLE, KEY_ARTIST, KEY_SUBTITLE, KEY_RELEASE_DATE});
        goto L21
    L64:
        r1.put("images", r2);     // Catch: JSONException -> L58
        goto L7
    }

    public final void zzb(JSONObject r20) {
        clear();
        this.zze = 0;
        this.zze = r20.getInt("metadataType");     // Catch: JSONException -> L69
    L4:
        JSONArray r3 = r20.optJSONArray("images");
        if (r3 == null) goto L7;
        com.google.android.gms.cast.internal.media.zza.zza(this.zzd, r3);
    L7:
        ArrayList r32 = new ArrayList();
        int r4 = this.zze;
        if (r4 == 0) goto L20;
        if (r4 == 1) goto L19;
        if (r4 == 2) goto L18;
        if (r4 == 3) goto L17;
        if (r4 == 4) goto L16;
        if (r4 != 5) goto L21;
        Collections.addAll(r32, new String[]{KEY_CHAPTER_TITLE, KEY_CHAPTER_NUMBER, KEY_TITLE, KEY_BOOK_TITLE, KEY_SUBTITLE});
    L21:
        Collections.addAll(r32, new String[]{KEY_SECTION_DURATION, KEY_SECTION_START_TIME_IN_MEDIA, KEY_SECTION_START_ABSOLUTE_TIME, KEY_SECTION_START_TIME_IN_CONTAINER, KEY_QUEUE_ITEM_ID});
        HashSet r42 = new HashSet(r32);
        Iterator<String> r33 = r20.keys();     // Catch: JSONException -> L71
    L23:
        if (r33.hasNext() == false) goto L125;
        String r5 = r33.next();     // Catch: JSONException -> L71
        if (r5 == null) goto L23;
        if ("metadataType".equals(r5) == true) goto L23;
        zzbu r11 = zzc;     // Catch: JSONException -> L71
        String r12 = r11.zzc(r5);     // Catch: JSONException -> L71
        if (r12 != null) goto L32;
        Object r112 = r20.get(r5);     // Catch: JSONException -> L71
        if ((r112 instanceof String) == true) goto L60;
        if ((r112 instanceof Integer) == true) goto L63;
        if ((r112 instanceof Double) == false) goto L23;
        this.zza.putDouble(r5, ((Double) r112).doubleValue());     // Catch: JSONException -> L71
        goto L23
    L63:
        this.zza.putInt(r5, ((Integer) r112).intValue());     // Catch: JSONException -> L71
        goto L23
    L60:
        this.zza.putString(r5, (String) r112);     // Catch: JSONException -> L71
        goto L23
    L32:
        if (r42.contains(r12) == false) goto L23;
        Object r13 = r20.get(r5);     // Catch: JSONException -> L70
        if (r13 == null) goto L23;
        int r113 = r11.zzd(r12);     // Catch: JSONException -> L70
        if (r113 != 1) goto L37;
        if ((r13 instanceof String) == false) goto L23;
        this.zza.putString(r12, (String) r13);     // Catch: JSONException -> L70
        goto L23
    L37:
        if (r113 != 2) goto L38;
        if ((r13 instanceof Integer) == false) goto L23;
        this.zza.putInt(r12, ((Integer) r13).intValue());     // Catch: JSONException -> L70
        goto L23
    L38:
        if (r113 != 3) goto L39;
        double r132 = r20.optDouble(r5);     // Catch: JSONException -> L70
        if (Double.isNaN(r132) == true) goto L23;
        this.zza.putDouble(r12, r132);     // Catch: JSONException -> L70
        goto L23
    L39:
        if (r113 != 4) goto L40;
        if ((r13 instanceof String) == false) goto L23;
        String r133 = (String) r13;     // Catch: JSONException -> L70
        if (com.google.android.gms.cast.internal.media.zza.zzd(r133) == null) goto L23;
        this.zza.putString(r12, r133);     // Catch: JSONException -> L70
        goto L23
    L40:
        if (r113 != 5) goto L23;
        this.zza.putLong(r12, CastUtils.secToMillisec(r20.optLong(r5)));     // Catch: JSONException -> L70
        goto L23
    L125:
        return;
    L68:
        return;
    L16:
        Collections.addAll(r32, new String[]{KEY_TITLE, KEY_ARTIST, KEY_LOCATION_NAME, KEY_LOCATION_LATITUDE, KEY_LOCATION_LONGITUDE, KEY_WIDTH, KEY_HEIGHT, KEY_CREATION_DATE});
        goto L21
    L17:
        Collections.addAll(r32, new String[]{KEY_TITLE, KEY_ALBUM_TITLE, KEY_ARTIST, KEY_ALBUM_ARTIST, KEY_COMPOSER, KEY_TRACK_NUMBER, KEY_DISC_NUMBER, KEY_RELEASE_DATE});
        goto L21
    L18:
        Collections.addAll(r32, new String[]{KEY_TITLE, KEY_SERIES_TITLE, KEY_SEASON_NUMBER, KEY_EPISODE_NUMBER, KEY_BROADCAST_DATE});
        goto L21
    L19:
        Collections.addAll(r32, new String[]{KEY_TITLE, KEY_STUDIO, KEY_SUBTITLE, KEY_RELEASE_DATE});
        goto L21
    L20:
        Collections.addAll(r32, new String[]{KEY_TITLE, KEY_ARTIST, KEY_SUBTITLE, KEY_RELEASE_DATE});
        goto L21
    }

    public final /* synthetic */ void zzc(int r1) {
        this.zze = r1;
    }
}
