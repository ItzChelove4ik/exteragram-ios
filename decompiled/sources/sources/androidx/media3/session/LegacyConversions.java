package androidx.media3.session;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.HeartRating;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PercentageRating;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.Player;
import androidx.media3.common.Rating;
import androidx.media3.common.StarRating;
import androidx.media3.common.ThumbRating;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.session.legacy.AudioAttributesCompat;
import androidx.media3.session.legacy.MediaDescriptionCompat;
import androidx.media3.session.legacy.MediaMetadataCompat;
import androidx.media3.session.legacy.MediaSessionCompat;
import androidx.media3.session.legacy.RatingCompat;
import androidx.mediarouter.media.MediaItemMetadata;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
abstract class LegacyConversions {
    public static final ImmutableSet<String> KNOWN_METADATA_COMPAT_KEYS = null;

    static {
        KNOWN_METADATA_COMPAT_KEYS = ImmutableSet.of(MediaItemMetadata.KEY_TITLE, MediaItemMetadata.KEY_ARTIST, MediaItemMetadata.KEY_DURATION, "android.media.metadata.ALBUM", MediaItemMetadata.KEY_AUTHOR, "android.media.metadata.WRITER", new String[]{MediaItemMetadata.KEY_COMPOSER, "android.media.metadata.COMPILATION", "android.media.metadata.DATE", MediaItemMetadata.KEY_YEAR, "android.media.metadata.GENRE", MediaItemMetadata.KEY_TRACK_NUMBER, "android.media.metadata.NUM_TRACKS", MediaItemMetadata.KEY_DISC_NUMBER, MediaItemMetadata.KEY_ALBUM_ARTIST, "android.media.metadata.ART", "android.media.metadata.ART_URI", "android.media.metadata.ALBUM_ART", "android.media.metadata.ALBUM_ART_URI", "android.media.metadata.USER_RATING", "android.media.metadata.RATING", "android.media.metadata.DISPLAY_TITLE", "android.media.metadata.DISPLAY_SUBTITLE", "android.media.metadata.DISPLAY_DESCRIPTION", "android.media.metadata.DISPLAY_ICON", "android.media.metadata.DISPLAY_ICON_URI", "android.media.metadata.MEDIA_ID", "android.media.metadata.MEDIA_URI", "android.media.metadata.BT_FOLDER_TYPE", "android.media.metadata.ADVERTISEMENT", "android.media.metadata.DOWNLOAD_STATUS", "androidx.media3.session.EXTRAS_KEY_MEDIA_TYPE_COMPAT"});
    }

    public static AudioAttributesCompat convertToAudioAttributesCompat(AudioAttributes r2) {
        return new AudioAttributesCompat.Builder().setContentType(r2.contentType).setFlags(r2.flags).setUsage(r2.usage).build();
    }

    private static byte[] convertToByteArray(Bitmap r3) {
        ByteArrayOutputStream r0 = new ByteArrayOutputStream();
        r3.compress(Bitmap.CompressFormat.PNG, 0, r0);     // Catch: Throwable -> L6
        byte[] r32 = r0.toByteArray();     // Catch: Throwable -> L6
        r0.close();
        return r32;
    L6:
        th = move-exception;
        r0.close();     // Catch: Throwable -> L9
    L11:
        throw th;
    L9:
        th = move-exception;
        th.addSuppressed(th);
        goto L11
    }

    private static long convertToExtraBtFolderType(int r2) {
        switch(r2) {
            case 0: goto L17;
            case 1: goto L15;
            case 2: goto L13;
            case 3: goto L11;
            case 4: goto L9;
            case 5: goto L7;
            case 6: goto L5;
            default: goto L3;
        };
    L3:
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Unrecognized FolderType: ", r2);
        return 0;
    L5:
        return 6;
    L7:
        return 5;
    L9:
        return 4;
    L11:
        return 3;
    L13:
        return 2;
    L15:
        return 1;
    L17:
        return 0;
    }

    private static int convertToFolderType(long r4) {
        if (r4 != 0) goto L6;
        return 0;
    L6:
        if (r4 != 1) goto L10;
        return 1;
    L10:
        if (r4 != 2) goto L14;
        return 2;
    L14:
        if (r4 != 3) goto L18;
        return 3;
    L18:
        if (r4 != 4) goto L22;
        return 4;
    L22:
        if (r4 != 5) goto L26;
        return 5;
    L26:
        if (r4 != 6) goto L29;
        return 6;
    L29:
        return 0;
    }

    public static int convertToLegacyErrorCode(int r2) {
        if (r2 != (-110)) goto L5;
        return 8;
    L5:
        if (r2 != (-109)) goto L7;
        return 11;
    L7:
        if (r2 != (-6)) goto L9;
        return 2;
    L9:
        if (r2 == (-2)) goto L28;
        if (r2 == 1) goto L26;
        switch(r2) {
            case -107: goto L24;
            case -106: goto L22;
            case -105: goto L20;
            case -104: goto L18;
            case -103: goto L16;
            case -102: goto L14;
            default: goto L12;
        };
    L12:
        return 0;
    L14:
        return 3;
    L16:
        return 4;
    L18:
        return 5;
    L20:
        return 6;
    L22:
        return 7;
    L24:
        return 9;
    L26:
        return 10;
    L28:
        return 1;
    }

    public static int convertToLegacyErrorCode(PlaybackException r0) {
        return convertToLegacyErrorCode(r0.errorCode);
    }

    public static MediaDescriptionCompat convertToMediaDescriptionCompat(MediaItem r10, Bitmap r11) {
        MediaDescriptionCompat.Builder r0 = new MediaDescriptionCompat.Builder();
        if (r10.mediaId.equals(_UrlKt.FRAGMENT_ENCODE_SET) == false) goto L5;
        String r1 = null;
    L6:
        MediaDescriptionCompat.Builder r02 = r0.setMediaId(r1);
        MediaMetadata r12 = r10.mediaMetadata;
        if (r11 == null) goto L9;
        r02.setIconBitmap(r11);
    L9:
        Bundle r112 = r12.extras;
        if (r112 == null) goto L12;
        r112 = new Bundle(r112);
    L12:
        Integer r2 = r12.folderType;
        if (r2 != null) goto L15;
    L17:
        boolean r22 = false;
    L19:
        if (r12.mediaType == null) goto L21;
        boolean r5 = true;
    L22:
        if (r22 == true) goto L24;
        if (r5 == true) goto L24;
    L31:
        if (r12.supportedCommands.isEmpty() == true) goto L35;
        if (r112 != null) goto L34;
        r112 = new Bundle();
    L34:
        r112.putStringArrayList("androidx.media.utils.extras.CUSTOM_BROWSER_ACTION_ID_LIST", new ArrayList(r12.supportedCommands));
    L35:
        CharSequence r23 = r12.displayTitle;
        if (r23 == null) goto L41;
        CharSequence r3 = r12.subtitle;
        CharSequence r4 = r12.description;
        if (r112 != null) goto L40;
        r112 = new Bundle();
    L40:
        r112.putCharSequence("androidx.media3.mediadescriptioncompat.title", r12.title);
    L51:
        return r02.setTitle(r23).setSubtitle(r3).setDescription(r4).setIconUri(r12.artworkUri).setMediaUri(r10.requestMetadata.mediaUri).setExtras(r112).build();
    L41:
        CharSequence[] r52 = new CharSequence[3];
        int r6 = 0;
        int r7 = 0;
    L42:
        if (r6 >= 3) goto L49;
        String[] r8 = MediaMetadataCompat.PREFERRED_DESCRIPTION_ORDER;
        if (r7 >= r8.length) goto L49;
        int r9 = r7 + 1;
        CharSequence r72 = getText(r8[r7], r12);
        if (TextUtils.isEmpty(r72) == true) goto L48;
        r52[r6] = r72;
        r6 = r6 + 1;
    L48:
        r7 = r9;
    L49:
        r23 = r52[0];
        r3 = r52[1];
        r4 = r52[2];
    L24:
        if (r112 != null) goto L26;
        r112 = new Bundle();
    L26:
        if (r22 == false) goto L28;
        r112.putLong("android.media.extra.BT_FOLDER_TYPE", convertToExtraBtFolderType(((Integer) Assertions.checkNotNull(r12.folderType)).intValue()));
    L28:
        if (r5 == false) goto L31;
        r112.putLong("androidx.media3.session.EXTRAS_KEY_MEDIA_TYPE_COMPAT", ((Integer) Assertions.checkNotNull(r12.mediaType)).intValue());
        goto L31
    L21:
        r5 = false;
        goto L22
    L15:
        if (r2.intValue() == (-1)) goto L17;
        r22 = true;
        goto L19
    L5:
        r1 = r10.mediaId;
        goto L6
    }

    public static MediaItem convertToMediaItem(MediaDescriptionCompat r2) {
        Assertions.checkNotNull(r2);
        return convertToMediaItem(r2, false, true);
    }

    private static MediaItem convertToMediaItem(MediaDescriptionCompat r3, boolean r4, boolean r5) {
        String r0 = r3.getMediaId();
        MediaItem.Builder r1 = new MediaItem.Builder();
        if (r0 != null) goto L6;
        r0 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        return r1.setMediaId(r0).setRequestMetadata(new MediaItem.RequestMetadata.Builder().setMediaUri(r3.getMediaUri()).build()).setMediaMetadata(convertToMediaMetadata(r3, 0, r4, r5)).build();
    }

    public static List<MediaItem> convertToMediaItemList(Timeline r4) {
        ArrayList r0 = new ArrayList();
        Timeline.Window r1 = new Timeline.Window();
        int r2 = 0;
    L4:
        if (r2 >= r4.getWindowCount()) goto L6;
        r0.add(r4.getWindow(r2, r1).mediaItem);
        r2 = r2 + 1;
        goto L4
    L6:
        return r0;
    }

    private static MediaMetadata convertToMediaMetadata(MediaDescriptionCompat r4, int r5, boolean r6, boolean r7) {
        if (r4 == null) goto L4;
        MediaMetadata.Builder r0 = new MediaMetadata.Builder();
        r0.setSubtitle(r4.getSubtitle()).setDescription(r4.getDescription()).setArtworkUri(r4.getIconUri()).setUserRating(convertToRating(RatingCompat.newUnratedRating(r5)));
        Bitmap r52 = r4.getIconBitmap();
        Bundle r1 = null;
        if (r52 != null) goto L40;
    L12:
        Bundle r53 = r4.getExtras();
        if (r53 == null) goto L16;
        r1 = new Bundle(r53);
    L16:
        if (r1 != null) goto L18;
    L20:
        r0.setIsBrowsable(Boolean.valueOf(r6));
        if (r1 != null) goto L23;
    L25:
        if (r1 != null) goto L27;
    L29:
        if (r1 != null) goto L31;
    L33:
        r0.setTitle(r4.getTitle());
    L34:
        if (r1 != null) goto L36;
    L38:
        r0.setIsPlayable(Boolean.valueOf(r7));
        return r0.build();
    L36:
        if (r1.isEmpty() == true) goto L38;
        r0.setExtras(r1);
        goto L38
    L31:
        if (r1.containsKey("androidx.media3.mediadescriptioncompat.title") == false) goto L33;
        r0.setTitle(r1.getCharSequence("androidx.media3.mediadescriptioncompat.title"));
        r0.setDisplayTitle(r4.getTitle());
        r1.remove("androidx.media3.mediadescriptioncompat.title");
        goto L34
    L27:
        if (r1.containsKey("androidx.media.utils.extras.CUSTOM_BROWSER_ACTION_ID_LIST") == false) goto L29;
        r0.setSupportedCommands(ImmutableList.copyOf((Collection) Assertions.checkNotNull(r1.getStringArrayList("androidx.media.utils.extras.CUSTOM_BROWSER_ACTION_ID_LIST"))));
        goto L29
    L23:
        if (r1.containsKey("androidx.media3.session.EXTRAS_KEY_MEDIA_TYPE_COMPAT") == false) goto L25;
        r0.setMediaType(Integer.valueOf((int) r1.getLong("androidx.media3.session.EXTRAS_KEY_MEDIA_TYPE_COMPAT")));
        r1.remove("androidx.media3.session.EXTRAS_KEY_MEDIA_TYPE_COMPAT");
        goto L25
    L18:
        if (r1.containsKey("android.media.extra.BT_FOLDER_TYPE") == false) goto L20;
        r0.setFolderType(Integer.valueOf(convertToFolderType(r1.getLong("android.media.extra.BT_FOLDER_TYPE"))));
        r1.remove("android.media.extra.BT_FOLDER_TYPE");
        goto L20
    L40:
        byte[] r54 = convertToByteArray(r52);     // Catch: IOException -> L9
    L11:
        r0.setArtworkData(r54, 3);
    L9:
        e = move-exception;
        Log.w("LegacyConversions", "Failed to convert iconBitmap to artworkData", e);
        r54 = null;
        goto L11
    L4:
        return MediaMetadata.EMPTY;
    }

    public static MediaMetadataCompat convertToMediaMetadataCompat(MediaMetadata r3, String r4, Uri r5, long r6, Bitmap r8) {
        MediaMetadataCompat.Builder r42 = new MediaMetadataCompat.Builder().putString("android.media.metadata.MEDIA_ID", r4);
        CharSequence r0 = r3.title;
        if (r0 == null) goto L5;
        r42.putText(MediaItemMetadata.KEY_TITLE, r0);
    L5:
        CharSequence r02 = r3.displayTitle;
        if (r02 == null) goto L8;
        r42.putText("android.media.metadata.DISPLAY_TITLE", r02);
    L8:
        CharSequence r03 = r3.subtitle;
        if (r03 == null) goto L11;
        r42.putText("android.media.metadata.DISPLAY_SUBTITLE", r03);
    L11:
        CharSequence r04 = r3.description;
        if (r04 == null) goto L14;
        r42.putText("android.media.metadata.DISPLAY_DESCRIPTION", r04);
    L14:
        CharSequence r05 = r3.artist;
        if (r05 == null) goto L17;
        r42.putText(MediaItemMetadata.KEY_ARTIST, r05);
    L17:
        CharSequence r06 = r3.albumTitle;
        if (r06 == null) goto L20;
        r42.putText("android.media.metadata.ALBUM", r06);
    L20:
        CharSequence r07 = r3.albumArtist;
        if (r07 == null) goto L24;
        r42.putText(MediaItemMetadata.KEY_ALBUM_ARTIST, r07);
    L24:
        if (r3.recordingYear == null) goto L26;
        r42.putLong(MediaItemMetadata.KEY_YEAR, r0.intValue());
    L26:
        if (r5 == null) goto L28;
        r42.putString("android.media.metadata.MEDIA_URI", r5.toString());
    L28:
        Uri r52 = r3.artworkUri;
        if (r52 == null) goto L31;
        r42.putString("android.media.metadata.DISPLAY_ICON_URI", r52.toString());
        r42.putString("android.media.metadata.ALBUM_ART_URI", r3.artworkUri.toString());
        r42.putString("android.media.metadata.ART_URI", r3.artworkUri.toString());
    L31:
        if (r8 == null) goto L33;
        r42.putBitmap("android.media.metadata.DISPLAY_ICON", r8);
        r42.putBitmap("android.media.metadata.ALBUM_ART", r8);
    L33:
        Integer r53 = r3.folderType;
        if (r53 == null) goto L39;
        if (r53.intValue() == (-1)) goto L39;
        r42.putLong("android.media.metadata.BT_FOLDER_TYPE", convertToExtraBtFolderType(r3.folderType.intValue()));
    L39:
        if (r6 != (-9223372036854775807L)) goto L44;
        Long r54 = r3.durationMs;
        if (r54 == null) goto L44;
        r6 = r54.longValue();
    L44:
        if (r6 != (-9223372036854775807L)) goto L47;
        r6 = -1;
    L47:
        r42.putLong(MediaItemMetadata.KEY_DURATION, r6);
        RatingCompat r55 = convertToRatingCompat(r3.userRating);
        if (r55 == null) goto L50;
        r42.putRating("android.media.metadata.USER_RATING", r55);
    L50:
        RatingCompat r56 = convertToRatingCompat(r3.overallRating);
        if (r56 == null) goto L54;
        r42.putRating("android.media.metadata.RATING", r56);
    L54:
        if (r3.mediaType == null) goto L56;
        r42.putLong("androidx.media3.session.EXTRAS_KEY_MEDIA_TYPE_COMPAT", r5.intValue());
    L56:
        Bundle r57 = r3.extras;
        if (r57 == null) goto L77;
        Iterator<String> r58 = r57.keySet().iterator();
    L60:
        if (r58.hasNext() == false) goto L77;
        String r62 = r58.next();
        Object r7 = r3.extras.get(r62);
        if (r7 == null) goto L75;
        if ((r7 instanceof CharSequence) == true) goto L75;
        if ((r7 instanceof Byte) == true) goto L74;
        if ((r7 instanceof Short) == true) goto L74;
        if ((r7 instanceof Integer) == true) goto L74;
        if ((r7 instanceof Long) == false) goto L60;
    L74:
        r42.putLong(r62, ((Number) r7).longValue());
    L75:
        r42.putText(r62, (CharSequence) r7);
    L77:
        return r42.build();
    }

    public static int convertToPlaybackStateCompatRepeatMode(int r3) {
        if (r3 == 0) goto L11;
        int r1 = 1;
        if (r3 == 1) goto L10;
        r1 = 2;
        if (r3 == 2) goto L10;
        Log.w("LegacyConversions", "Unrecognized RepeatMode: " + r3 + " was converted to `PlaybackStateCompat.REPEAT_MODE_NONE`");
        return 0;
    L10:
        return r1;
    L11:
        return 0;
    }

    public static int convertToPlaybackStateCompatShuffleMode(boolean r0) {
        return r0 ? 1 : 0;
    }

    public static int convertToPlaybackStateCompatState(Player r4, boolean r5) {
        if (r4.getPlayerError() == null) goto L6;
        return 7;
    L6:
        int r42 = r4.getPlaybackState();
        if (r42 != 1) goto L9;
        return 0;
    L9:
        if (r42 != 2) goto L11;
        if (r5 == false) goto L22;
        return 2;
    L22:
        return 6;
    L11:
        if (r42 != 3) goto L13;
        if (r5 == false) goto L19;
        return 2;
    L19:
        return 3;
    L13:
        if (r42 != 4) goto L15;
        return 1;
    L15:
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Unrecognized State: ", r42);
        return 0;
    }

    public static MediaSessionCompat.QueueItem convertToQueueItem(MediaItem r1, int r2, Bitmap r3) {
        return new MediaSessionCompat.QueueItem(convertToMediaDescriptionCompat(r1, r3), convertToQueueItemId(r2));
    }

    public static long convertToQueueItemId(int r2) {
        if (r2 != (-1)) goto L7;
        return -1;
    L7:
        return r2;
    }

    public static Rating convertToRating(RatingCompat r2) {
        if (r2 != null) goto L6;
        return null;
    L6:
        switch(r2.getRatingStyle()) {
            case 1: goto L39;
            case 2: goto L33;
            case 3: goto L27;
            case 4: goto L21;
            case 5: goto L15;
            case 6: goto L9;
            default: goto L7;
        };
    L7:
        return null;
    L9:
        if (r2.isRated() == false) goto L13;
        return new PercentageRating(r2.getPercentRating());
    L13:
        return new PercentageRating();
    L15:
        if (r2.isRated() == false) goto L19;
        return new StarRating(5, r2.getStarRating());
    L19:
        return new StarRating(5);
    L21:
        if (r2.isRated() == false) goto L25;
        return new StarRating(4, r2.getStarRating());
    L25:
        return new StarRating(4);
    L27:
        if (r2.isRated() == false) goto L31;
        return new StarRating(3, r2.getStarRating());
    L31:
        return new StarRating(3);
    L33:
        if (r2.isRated() == false) goto L37;
        return new ThumbRating(r2.isThumbUp());
    L37:
        return new ThumbRating();
    L39:
        if (r2.isRated() == false) goto L43;
        return new HeartRating(r2.hasHeart());
    L43:
        return new HeartRating();
    }

    @SuppressLint({"WrongConstant"})
    public static RatingCompat convertToRatingCompat(Rating r3) {
        if (r3 != null) goto L5;
        return null;
    L5:
        int r1 = getRatingCompatStyle(r3);
        if (r3.isRated() == false) goto L8;
        switch(r1) {
            case 1: goto L18;
            case 2: goto L16;
            case 3: goto L14;
            case 4: goto L14;
            case 5: goto L14;
            case 6: goto L12;
            default: goto L10;
        };
    L10:
        return null;
    L12:
        return RatingCompat.newPercentageRating(((PercentageRating) r3).getPercent());
    L14:
        return RatingCompat.newStarRating(r1, ((StarRating) r3).getStarRating());
    L16:
        return RatingCompat.newThumbRating(((ThumbRating) r3).isThumbsUp());
    L18:
        return RatingCompat.newHeartRating(((HeartRating) r3).isHeart());
    L8:
        return RatingCompat.newUnratedRating(r1);
    }

    public static int convertToRepeatMode(int r3) {
        if (r3 == (-1)) goto L14;
        if (r3 == 0) goto L14;
        int r0 = 1;
        if (r3 == 1) goto L13;
        r0 = 2;
        if (r3 == 2) goto L13;
        if (r3 == 3) goto L13;
        Log.w("LegacyConversions", "Unrecognized PlaybackStateCompat.RepeatMode: " + r3 + " was converted to `Player.REPEAT_MODE_OFF`");
        return 0;
    L13:
        return r0;
    L14:
        return 0;
    }

    public static boolean convertToShuffleModeEnabled(int r2) {
        if (r2 == (-1)) goto L13;
        if (r2 != 0) goto L6;
        return false;
    L6:
        if (r2 != 1) goto L8;
    L12:
        return true;
    L8:
        if (r2 == 2) goto L12;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Unrecognized ShuffleMode: ", r2);
        return false;
    L13:
        return false;
    }

    public static int getLegacyStreamType(AudioAttributes r1) {
        int r12 = convertToAudioAttributesCompat(r1).getLegacyStreamType();
        if (r12 != Integer.MIN_VALUE) goto L6;
        return 3;
    L6:
        return r12;
    }

    public static int getRatingCompatStyle(Rating r1) {
        if ((r1 instanceof HeartRating) == false) goto L7;
        return 1;
    L7:
        if ((r1 instanceof ThumbRating) == false) goto L11;
        return 2;
    L11:
        if ((r1 instanceof StarRating) == false) goto L21;
        int r12 = ((StarRating) r1).getMaxStars();
        int r0 = 3;
        if (r12 == 3) goto L19;
        r0 = 4;
        if (r12 == 4) goto L19;
        r0 = 5;
        if (r12 == 5) goto L19;
        return 0;
    L19:
        return r0;
    L21:
        if ((r1 instanceof PercentageRating) == false) goto L24;
        return 6;
    L24:
        return 0;
    }

    private static CharSequence getText(String r2, MediaMetadata r3) {
        r2.getClass();
        char r1 = 65535;
        switch(r2.hashCode()) {
            case -1853648227: goto L30;
            case -1224124471: goto L26;
            case 194702059: goto L22;
            case 1684534006: goto L18;
            case 1879671865: goto L14;
            case 1897146402: goto L10;
            case 1965214221: goto L6;
            default: goto L33;
        };
    L33:
        switch(r1) {
            case 0: goto L49;
            case 1: goto L47;
            case 2: goto L45;
            case 3: goto L43;
            case 4: goto L41;
            case 5: goto L39;
            case 6: goto L37;
            default: goto L34;
        };
    L34:
        return null;
    L37:
        return r3.albumArtist;
    L39:
        return r3.title;
    L41:
        return r3.albumTitle;
    L43:
        return r3.composer;
    L45:
        return r3.subtitle;
    L47:
        return r3.writer;
    L49:
        return r3.artist;
    L6:
        if (r2.equals(MediaItemMetadata.KEY_ALBUM_ARTIST) == false) goto L33;
        r1 = 6;
        goto L33
    L10:
        if (r2.equals(MediaItemMetadata.KEY_TITLE) == false) goto L33;
        r1 = 5;
        goto L33
    L14:
        if (r2.equals("android.media.metadata.ALBUM") == false) goto L33;
        r1 = 4;
        goto L33
    L18:
        if (r2.equals(MediaItemMetadata.KEY_COMPOSER) == false) goto L33;
        r1 = 3;
        goto L33
    L22:
        if (r2.equals("android.media.metadata.DISPLAY_SUBTITLE") == false) goto L33;
        r1 = 2;
        goto L33
    L26:
        if (r2.equals("android.media.metadata.WRITER") == false) goto L33;
        r1 = 1;
        goto L33
    L30:
        if (r2.equals(MediaItemMetadata.KEY_ARTIST) == false) goto L33;
        r1 = 0;
        goto L33
    }
}
