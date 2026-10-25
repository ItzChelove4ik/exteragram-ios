package androidx.media3.common;

import android.net.Uri;
import android.os.Bundle;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class MediaMetadata {
    public static final MediaMetadata EMPTY = null;
    private static final String FIELD_ALBUM_ARTIST = null;
    private static final String FIELD_ALBUM_TITLE = null;
    private static final String FIELD_ARTIST = null;
    private static final String FIELD_ARTWORK_DATA = null;
    private static final String FIELD_ARTWORK_DATA_TYPE = null;
    private static final String FIELD_ARTWORK_URI = null;
    private static final String FIELD_COMPILATION = null;
    private static final String FIELD_COMPOSER = null;
    private static final String FIELD_CONDUCTOR = null;
    private static final String FIELD_DESCRIPTION = null;
    private static final String FIELD_DISC_NUMBER = null;
    private static final String FIELD_DISPLAY_TITLE = null;
    private static final String FIELD_DURATION_MS = null;
    private static final String FIELD_EXTRAS = null;
    private static final String FIELD_FOLDER_TYPE = null;
    private static final String FIELD_GENRE = null;
    private static final String FIELD_IS_BROWSABLE = null;
    private static final String FIELD_IS_PLAYABLE = null;
    private static final String FIELD_MEDIA_TYPE = null;
    private static final String FIELD_OVERALL_RATING = null;
    private static final String FIELD_RECORDING_DAY = null;
    private static final String FIELD_RECORDING_MONTH = null;
    private static final String FIELD_RECORDING_YEAR = null;
    private static final String FIELD_RELEASE_DAY = null;
    private static final String FIELD_RELEASE_MONTH = null;
    private static final String FIELD_RELEASE_YEAR = null;
    private static final String FIELD_STATION = null;
    private static final String FIELD_SUBTITLE = null;
    private static final String FIELD_SUPPORTED_COMMANDS = null;
    private static final String FIELD_TITLE = null;
    private static final String FIELD_TOTAL_DISC_COUNT = null;
    private static final String FIELD_TOTAL_TRACK_COUNT = null;
    private static final String FIELD_TRACK_NUMBER = null;
    private static final String FIELD_USER_RATING = null;
    private static final String FIELD_WRITER = null;
    public final CharSequence albumArtist;
    public final CharSequence albumTitle;
    public final CharSequence artist;
    public final byte[] artworkData;
    public final Integer artworkDataType;
    public final Uri artworkUri;
    public final CharSequence compilation;
    public final CharSequence composer;
    public final CharSequence conductor;
    public final CharSequence description;
    public final Integer discNumber;
    public final CharSequence displayTitle;
    public final Long durationMs;
    public final Bundle extras;

    @Deprecated
    public final Integer folderType;
    public final CharSequence genre;
    public final Boolean isBrowsable;
    public final Boolean isPlayable;
    public final Integer mediaType;
    public final Rating overallRating;
    public final Integer recordingDay;
    public final Integer recordingMonth;
    public final Integer recordingYear;
    public final Integer releaseDay;
    public final Integer releaseMonth;
    public final Integer releaseYear;
    public final CharSequence station;
    public final CharSequence subtitle;
    public final ImmutableList<String> supportedCommands;
    public final CharSequence title;
    public final Integer totalDiscCount;
    public final Integer totalTrackCount;
    public final Integer trackNumber;
    public final Rating userRating;
    public final CharSequence writer;

    @Deprecated
    public final Integer year;

    /* renamed from: androidx.media3.common.MediaMetadata$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder {
        private CharSequence albumArtist;
        private CharSequence albumTitle;
        private CharSequence artist;
        private byte[] artworkData;
        private Integer artworkDataType;
        private Uri artworkUri;
        private CharSequence compilation;
        private CharSequence composer;
        private CharSequence conductor;
        private CharSequence description;
        private Integer discNumber;
        private CharSequence displayTitle;
        private Long durationMs;
        private Bundle extras;
        private Integer folderType;
        private CharSequence genre;
        private Boolean isBrowsable;
        private Boolean isPlayable;
        private Integer mediaType;
        private Rating overallRating;
        private Integer recordingDay;
        private Integer recordingMonth;
        private Integer recordingYear;
        private Integer releaseDay;
        private Integer releaseMonth;
        private Integer releaseYear;
        private CharSequence station;
        private CharSequence subtitle;
        private ImmutableList<String> supportedCommands;
        private CharSequence title;
        private Integer totalDiscCount;
        private Integer totalTrackCount;
        private Integer trackNumber;
        private Rating userRating;
        private CharSequence writer;

        public Builder() {
            this.supportedCommands = ImmutableList.of();
        }

        private Builder(MediaMetadata r2) {
            this.title = r2.title;
            this.artist = r2.artist;
            this.albumTitle = r2.albumTitle;
            this.albumArtist = r2.albumArtist;
            this.displayTitle = r2.displayTitle;
            this.subtitle = r2.subtitle;
            this.description = r2.description;
            this.durationMs = r2.durationMs;
            this.userRating = r2.userRating;
            this.overallRating = r2.overallRating;
            this.artworkData = r2.artworkData;
            this.artworkDataType = r2.artworkDataType;
            this.artworkUri = r2.artworkUri;
            this.trackNumber = r2.trackNumber;
            this.totalTrackCount = r2.totalTrackCount;
            this.folderType = r2.folderType;
            this.isBrowsable = r2.isBrowsable;
            this.isPlayable = r2.isPlayable;
            this.recordingYear = r2.recordingYear;
            this.recordingMonth = r2.recordingMonth;
            this.recordingDay = r2.recordingDay;
            this.releaseYear = r2.releaseYear;
            this.releaseMonth = r2.releaseMonth;
            this.releaseDay = r2.releaseDay;
            this.writer = r2.writer;
            this.composer = r2.composer;
            this.conductor = r2.conductor;
            this.discNumber = r2.discNumber;
            this.totalDiscCount = r2.totalDiscCount;
            this.genre = r2.genre;
            this.compilation = r2.compilation;
            this.station = r2.station;
            this.mediaType = r2.mediaType;
            this.supportedCommands = r2.supportedCommands;
            this.extras = r2.extras;
        }

        public /* synthetic */ Builder(MediaMetadata r1, AnonymousClass1 r2) {
            this(r1);
        }

        public static /* synthetic */ Boolean access$100(Builder r0) {
            return r0.isBrowsable;
        }

        public static /* synthetic */ CharSequence access$1000(Builder r0) {
            return r0.description;
        }

        public static /* synthetic */ Long access$1100(Builder r0) {
            return r0.durationMs;
        }

        public static /* synthetic */ Rating access$1200(Builder r0) {
            return r0.userRating;
        }

        public static /* synthetic */ Rating access$1300(Builder r0) {
            return r0.overallRating;
        }

        public static /* synthetic */ byte[] access$1400(Builder r0) {
            return r0.artworkData;
        }

        public static /* synthetic */ Integer access$1500(Builder r0) {
            return r0.artworkDataType;
        }

        public static /* synthetic */ Uri access$1600(Builder r0) {
            return r0.artworkUri;
        }

        public static /* synthetic */ Integer access$1700(Builder r0) {
            return r0.trackNumber;
        }

        public static /* synthetic */ Integer access$1800(Builder r0) {
            return r0.totalTrackCount;
        }

        public static /* synthetic */ Boolean access$1900(Builder r0) {
            return r0.isPlayable;
        }

        public static /* synthetic */ Integer access$200(Builder r0) {
            return r0.folderType;
        }

        public static /* synthetic */ Integer access$2000(Builder r0) {
            return r0.recordingYear;
        }

        public static /* synthetic */ Integer access$2100(Builder r0) {
            return r0.recordingMonth;
        }

        public static /* synthetic */ Integer access$2200(Builder r0) {
            return r0.recordingDay;
        }

        public static /* synthetic */ Integer access$2300(Builder r0) {
            return r0.releaseYear;
        }

        public static /* synthetic */ Integer access$2400(Builder r0) {
            return r0.releaseMonth;
        }

        public static /* synthetic */ Integer access$2500(Builder r0) {
            return r0.releaseDay;
        }

        public static /* synthetic */ CharSequence access$2600(Builder r0) {
            return r0.writer;
        }

        public static /* synthetic */ CharSequence access$2700(Builder r0) {
            return r0.composer;
        }

        public static /* synthetic */ CharSequence access$2800(Builder r0) {
            return r0.conductor;
        }

        public static /* synthetic */ Integer access$2900(Builder r0) {
            return r0.discNumber;
        }

        public static /* synthetic */ Integer access$300(Builder r0) {
            return r0.mediaType;
        }

        public static /* synthetic */ Integer access$3000(Builder r0) {
            return r0.totalDiscCount;
        }

        public static /* synthetic */ CharSequence access$3100(Builder r0) {
            return r0.genre;
        }

        public static /* synthetic */ CharSequence access$3200(Builder r0) {
            return r0.compilation;
        }

        public static /* synthetic */ CharSequence access$3300(Builder r0) {
            return r0.station;
        }

        public static /* synthetic */ ImmutableList access$3400(Builder r0) {
            return r0.supportedCommands;
        }

        public static /* synthetic */ Bundle access$3500(Builder r0) {
            return r0.extras;
        }

        public static /* synthetic */ CharSequence access$400(Builder r0) {
            return r0.title;
        }

        public static /* synthetic */ CharSequence access$500(Builder r0) {
            return r0.artist;
        }

        public static /* synthetic */ CharSequence access$600(Builder r0) {
            return r0.albumTitle;
        }

        public static /* synthetic */ CharSequence access$700(Builder r0) {
            return r0.albumArtist;
        }

        public static /* synthetic */ CharSequence access$800(Builder r0) {
            return r0.displayTitle;
        }

        public static /* synthetic */ CharSequence access$900(Builder r0) {
            return r0.subtitle;
        }

        public MediaMetadata build() {
            return new MediaMetadata(this, null);
        }

        public Builder maybeSetArtworkData(byte[] r3, int r4) {
            if (this.artworkData != null) goto L5;
        L10:
            this.artworkData = (byte[]) r3.clone();
            this.artworkDataType = Integer.valueOf(r4);
            return this;
        L5:
            if (r4 == 3) goto L10;
            if (Objects.equals(this.artworkDataType, 3) == false) goto L10;
            return this;
        }

        public Builder populate(MediaMetadata r3) {
            if (r3 == null) goto L108;
            CharSequence r0 = r3.title;
            if (r0 == null) goto L7;
            setTitle(r0);
        L7:
            CharSequence r02 = r3.artist;
            if (r02 == null) goto L10;
            setArtist(r02);
        L10:
            CharSequence r03 = r3.albumTitle;
            if (r03 == null) goto L13;
            setAlbumTitle(r03);
        L13:
            CharSequence r04 = r3.albumArtist;
            if (r04 == null) goto L16;
            setAlbumArtist(r04);
        L16:
            CharSequence r05 = r3.displayTitle;
            if (r05 == null) goto L19;
            setDisplayTitle(r05);
        L19:
            CharSequence r06 = r3.subtitle;
            if (r06 == null) goto L22;
            setSubtitle(r06);
        L22:
            CharSequence r07 = r3.description;
            if (r07 == null) goto L25;
            setDescription(r07);
        L25:
            Long r08 = r3.durationMs;
            if (r08 == null) goto L28;
            setDurationMs(r08);
        L28:
            Rating r09 = r3.userRating;
            if (r09 == null) goto L31;
            setUserRating(r09);
        L31:
            Rating r010 = r3.overallRating;
            if (r010 == null) goto L34;
            setOverallRating(r010);
        L34:
            Uri r011 = r3.artworkUri;
            if (r011 == null) goto L37;
        L38:
            setArtworkUri(r011);
            setArtworkData(r3.artworkData, r3.artworkDataType);
        L39:
            Integer r012 = r3.trackNumber;
            if (r012 == null) goto L42;
            setTrackNumber(r012);
        L42:
            Integer r013 = r3.totalTrackCount;
            if (r013 == null) goto L45;
            setTotalTrackCount(r013);
        L45:
            Integer r014 = r3.folderType;
            if (r014 == null) goto L48;
            setFolderType(r014);
        L48:
            Boolean r015 = r3.isBrowsable;
            if (r015 == null) goto L51;
            setIsBrowsable(r015);
        L51:
            Boolean r016 = r3.isPlayable;
            if (r016 == null) goto L54;
            setIsPlayable(r016);
        L54:
            Integer r017 = r3.year;
            if (r017 == null) goto L57;
            setRecordingYear(r017);
        L57:
            Integer r018 = r3.recordingYear;
            if (r018 == null) goto L60;
            setRecordingYear(r018);
        L60:
            Integer r019 = r3.recordingMonth;
            if (r019 == null) goto L63;
            setRecordingMonth(r019);
        L63:
            Integer r020 = r3.recordingDay;
            if (r020 == null) goto L66;
            setRecordingDay(r020);
        L66:
            Integer r021 = r3.releaseYear;
            if (r021 == null) goto L69;
            setReleaseYear(r021);
        L69:
            Integer r022 = r3.releaseMonth;
            if (r022 == null) goto L72;
            setReleaseMonth(r022);
        L72:
            Integer r023 = r3.releaseDay;
            if (r023 == null) goto L75;
            setReleaseDay(r023);
        L75:
            CharSequence r024 = r3.writer;
            if (r024 == null) goto L78;
            setWriter(r024);
        L78:
            CharSequence r025 = r3.composer;
            if (r025 == null) goto L81;
            setComposer(r025);
        L81:
            CharSequence r026 = r3.conductor;
            if (r026 == null) goto L84;
            setConductor(r026);
        L84:
            Integer r027 = r3.discNumber;
            if (r027 == null) goto L87;
            setDiscNumber(r027);
        L87:
            Integer r028 = r3.totalDiscCount;
            if (r028 == null) goto L90;
            setTotalDiscCount(r028);
        L90:
            CharSequence r029 = r3.genre;
            if (r029 == null) goto L93;
            setGenre(r029);
        L93:
            CharSequence r030 = r3.compilation;
            if (r030 == null) goto L96;
            setCompilation(r030);
        L96:
            CharSequence r031 = r3.station;
            if (r031 == null) goto L99;
            setStation(r031);
        L99:
            Integer r032 = r3.mediaType;
            if (r032 == null) goto L102;
            setMediaType(r032);
        L102:
            Bundle r033 = r3.extras;
            if (r033 == null) goto L106;
            setExtras(r033);
        L106:
            if (r3.supportedCommands.isEmpty() == true) goto L108;
            setSupportedCommands(r3.supportedCommands);
            goto L108
        L37:
            if (r3.artworkData == null) goto L39;
        L108:
            return this;
        }

        public Builder populateFromMetadata(Metadata r3) {
            int r0 = 0;
        L4:
            if (r0 >= r3.length()) goto L6;
            r3.get(r0).populateMediaMetadata(this);
            r0 = r0 + 1;
            goto L4
        L6:
            return this;
        }

        public Builder populateFromMetadata(List<Metadata> r6) {
            int r1 = 0;
        L4:
            if (r1 >= r6.size()) goto L10;
            Metadata r2 = r6.get(r1);
            int r3 = 0;
        L7:
            if (r3 >= r2.length()) goto L9;
            r2.get(r3).populateMediaMetadata(this);
            r3 = r3 + 1;
            goto L7
        L9:
            r1 = r1 + 1;
            goto L4
        L10:
            return this;
        }

        public Builder setAlbumArtist(CharSequence r1) {
            this.albumArtist = r1;
            return this;
        }

        public Builder setAlbumTitle(CharSequence r1) {
            this.albumTitle = r1;
            return this;
        }

        public Builder setArtist(CharSequence r1) {
            this.artist = r1;
            return this;
        }

        public Builder setArtworkData(byte[] r1, Integer r2) {
            if (r1 != null) goto L4;
            byte[] r12 = null;
        L5:
            this.artworkData = r12;
            this.artworkDataType = r2;
            return this;
        L4:
            r12 = (byte[]) r1.clone();
            goto L5
        }

        public Builder setArtworkUri(Uri r1) {
            this.artworkUri = r1;
            return this;
        }

        public Builder setCompilation(CharSequence r1) {
            this.compilation = r1;
            return this;
        }

        public Builder setComposer(CharSequence r1) {
            this.composer = r1;
            return this;
        }

        public Builder setConductor(CharSequence r1) {
            this.conductor = r1;
            return this;
        }

        public Builder setDescription(CharSequence r1) {
            this.description = r1;
            return this;
        }

        public Builder setDiscNumber(Integer r1) {
            this.discNumber = r1;
            return this;
        }

        public Builder setDisplayTitle(CharSequence r1) {
            this.displayTitle = r1;
            return this;
        }

        public Builder setDurationMs(Long r5) {
            if (r5 != null) goto L4;
        L7:
            boolean r0 = true;
        L8:
            Assertions.checkArgument(r0);
            this.durationMs = r5;
            return this;
        L4:
            if (r5.longValue() >= 0) goto L7;
            r0 = false;
            goto L8
        }

        public Builder setExtras(Bundle r1) {
            this.extras = r1;
            return this;
        }

        @Deprecated
        public Builder setFolderType(Integer r1) {
            this.folderType = r1;
            return this;
        }

        public Builder setGenre(CharSequence r1) {
            this.genre = r1;
            return this;
        }

        public Builder setIsBrowsable(Boolean r1) {
            this.isBrowsable = r1;
            return this;
        }

        public Builder setIsPlayable(Boolean r1) {
            this.isPlayable = r1;
            return this;
        }

        public Builder setMediaType(Integer r1) {
            this.mediaType = r1;
            return this;
        }

        public Builder setOverallRating(Rating r1) {
            this.overallRating = r1;
            return this;
        }

        public Builder setRecordingDay(Integer r1) {
            this.recordingDay = r1;
            return this;
        }

        public Builder setRecordingMonth(Integer r1) {
            this.recordingMonth = r1;
            return this;
        }

        public Builder setRecordingYear(Integer r1) {
            this.recordingYear = r1;
            return this;
        }

        public Builder setReleaseDay(Integer r1) {
            this.releaseDay = r1;
            return this;
        }

        public Builder setReleaseMonth(Integer r1) {
            this.releaseMonth = r1;
            return this;
        }

        public Builder setReleaseYear(Integer r1) {
            this.releaseYear = r1;
            return this;
        }

        public Builder setStation(CharSequence r1) {
            this.station = r1;
            return this;
        }

        public Builder setSubtitle(CharSequence r1) {
            this.subtitle = r1;
            return this;
        }

        public Builder setSupportedCommands(List<String> r1) {
            this.supportedCommands = ImmutableList.copyOf(r1);
            return this;
        }

        public Builder setTitle(CharSequence r1) {
            this.title = r1;
            return this;
        }

        public Builder setTotalDiscCount(Integer r1) {
            this.totalDiscCount = r1;
            return this;
        }

        public Builder setTotalTrackCount(Integer r1) {
            this.totalTrackCount = r1;
            return this;
        }

        public Builder setTrackNumber(Integer r1) {
            this.trackNumber = r1;
            return this;
        }

        public Builder setUserRating(Rating r1) {
            this.userRating = r1;
            return this;
        }

        public Builder setWriter(CharSequence r1) {
            this.writer = r1;
            return this;
        }
    }

    static {
        EMPTY = new Builder().build();
        FIELD_TITLE = Util.intToStringMaxRadix(0);
        FIELD_ARTIST = Util.intToStringMaxRadix(1);
        FIELD_ALBUM_TITLE = Util.intToStringMaxRadix(2);
        FIELD_ALBUM_ARTIST = Util.intToStringMaxRadix(3);
        FIELD_DISPLAY_TITLE = Util.intToStringMaxRadix(4);
        FIELD_SUBTITLE = Util.intToStringMaxRadix(5);
        FIELD_DESCRIPTION = Util.intToStringMaxRadix(6);
        FIELD_USER_RATING = Util.intToStringMaxRadix(8);
        FIELD_OVERALL_RATING = Util.intToStringMaxRadix(9);
        FIELD_ARTWORK_DATA = Util.intToStringMaxRadix(10);
        FIELD_ARTWORK_URI = Util.intToStringMaxRadix(11);
        FIELD_TRACK_NUMBER = Util.intToStringMaxRadix(12);
        FIELD_TOTAL_TRACK_COUNT = Util.intToStringMaxRadix(13);
        FIELD_FOLDER_TYPE = Util.intToStringMaxRadix(14);
        FIELD_IS_PLAYABLE = Util.intToStringMaxRadix(15);
        FIELD_RECORDING_YEAR = Util.intToStringMaxRadix(16);
        FIELD_RECORDING_MONTH = Util.intToStringMaxRadix(17);
        FIELD_RECORDING_DAY = Util.intToStringMaxRadix(18);
        FIELD_RELEASE_YEAR = Util.intToStringMaxRadix(19);
        FIELD_RELEASE_MONTH = Util.intToStringMaxRadix(20);
        FIELD_RELEASE_DAY = Util.intToStringMaxRadix(21);
        FIELD_WRITER = Util.intToStringMaxRadix(22);
        FIELD_COMPOSER = Util.intToStringMaxRadix(23);
        FIELD_CONDUCTOR = Util.intToStringMaxRadix(24);
        FIELD_DISC_NUMBER = Util.intToStringMaxRadix(25);
        FIELD_TOTAL_DISC_COUNT = Util.intToStringMaxRadix(26);
        FIELD_GENRE = Util.intToStringMaxRadix(27);
        FIELD_COMPILATION = Util.intToStringMaxRadix(28);
        FIELD_ARTWORK_DATA_TYPE = Util.intToStringMaxRadix(29);
        FIELD_STATION = Util.intToStringMaxRadix(30);
        FIELD_MEDIA_TYPE = Util.intToStringMaxRadix(31);
        FIELD_IS_BROWSABLE = Util.intToStringMaxRadix(32);
        FIELD_DURATION_MS = Util.intToStringMaxRadix(33);
        FIELD_SUPPORTED_COMMANDS = Util.intToStringMaxRadix(34);
        FIELD_EXTRAS = Util.intToStringMaxRadix(1000);
    }

    private MediaMetadata(Builder r7) {
        Boolean r0 = Builder.access$100(r7);
        Integer r1 = Builder.access$200(r7);
        Integer r2 = Builder.access$300(r7);
        boolean r3 = false;
        int r32 = 0;
        if (r0 != null) goto L5;
        if (r1 != null) goto L15;
    L21:
        this.title = Builder.access$400(r7);
        this.artist = Builder.access$500(r7);
        this.albumTitle = Builder.access$600(r7);
        this.albumArtist = Builder.access$700(r7);
        this.displayTitle = Builder.access$800(r7);
        this.subtitle = Builder.access$900(r7);
        this.description = Builder.access$1000(r7);
        this.durationMs = Builder.access$1100(r7);
        this.userRating = Builder.access$1200(r7);
        this.overallRating = Builder.access$1300(r7);
        this.artworkData = Builder.access$1400(r7);
        this.artworkDataType = Builder.access$1500(r7);
        this.artworkUri = Builder.access$1600(r7);
        this.trackNumber = Builder.access$1700(r7);
        this.totalTrackCount = Builder.access$1800(r7);
        this.folderType = r1;
        this.isBrowsable = r0;
        this.isPlayable = Builder.access$1900(r7);
        this.year = Builder.access$2000(r7);
        this.recordingYear = Builder.access$2000(r7);
        this.recordingMonth = Builder.access$2100(r7);
        this.recordingDay = Builder.access$2200(r7);
        this.releaseYear = Builder.access$2300(r7);
        this.releaseMonth = Builder.access$2400(r7);
        this.releaseDay = Builder.access$2500(r7);
        this.writer = Builder.access$2600(r7);
        this.composer = Builder.access$2700(r7);
        this.conductor = Builder.access$2800(r7);
        this.discNumber = Builder.access$2900(r7);
        this.totalDiscCount = Builder.access$3000(r7);
        this.genre = Builder.access$3100(r7);
        this.compilation = Builder.access$3200(r7);
        this.station = Builder.access$3300(r7);
        this.mediaType = r2;
        this.supportedCommands = Builder.access$3400(r7);
        this.extras = Builder.access$3500(r7);
        return;
    L15:
        if (r1.intValue() == (-1)) goto L17;
        r3 = true;
    L17:
        r0 = Boolean.valueOf(r3);
        if (r3 == false) goto L21;
        if (r2 != null) goto L21;
        r2 = Integer.valueOf(getMediaTypeFromFolderType(r1.intValue()));
        goto L21
    L5:
        if (r0.booleanValue() == true) goto L7;
        r1 = -1;
        goto L21
    L7:
        if (r1 != null) goto L9;
    L10:
        if (r2 == null) goto L12;
        r32 = getFolderTypeFromMediaType(r2.intValue());
    L12:
        r1 = Integer.valueOf(r32);
        goto L21
    L9:
        if (r1.intValue() != (-1)) goto L21;
        goto L10
    }

    public /* synthetic */ MediaMetadata(Builder r1, AnonymousClass1 r2) {
        this(r1);
    }

    public static MediaMetadata fromBundle(Bundle r5) {
        Builder r0 = new Builder();
        Builder r1 = r0.setTitle(r5.getCharSequence(FIELD_TITLE)).setArtist(r5.getCharSequence(FIELD_ARTIST)).setAlbumTitle(r5.getCharSequence(FIELD_ALBUM_TITLE)).setAlbumArtist(r5.getCharSequence(FIELD_ALBUM_ARTIST)).setDisplayTitle(r5.getCharSequence(FIELD_DISPLAY_TITLE)).setSubtitle(r5.getCharSequence(FIELD_SUBTITLE)).setDescription(r5.getCharSequence(FIELD_DESCRIPTION));
        byte[] r2 = r5.getByteArray(FIELD_ARTWORK_DATA);
        String r3 = FIELD_ARTWORK_DATA_TYPE;
        if (r5.containsKey(r3) == false) goto L5;
        Integer r32 = Integer.valueOf(r5.getInt(r3));
    L6:
        r1.setArtworkData(r2, r32).setArtworkUri((Uri) r5.getParcelable(FIELD_ARTWORK_URI)).setWriter(r5.getCharSequence(FIELD_WRITER)).setComposer(r5.getCharSequence(FIELD_COMPOSER)).setConductor(r5.getCharSequence(FIELD_CONDUCTOR)).setGenre(r5.getCharSequence(FIELD_GENRE)).setCompilation(r5.getCharSequence(FIELD_COMPILATION)).setStation(r5.getCharSequence(FIELD_STATION)).setExtras(r5.getBundle(FIELD_EXTRAS));
        String r12 = FIELD_USER_RATING;
        if (r5.containsKey(r12) == false) goto L11;
        Bundle r13 = r5.getBundle(r12);
        if (r13 == null) goto L11;
        r0.setUserRating(Rating.fromBundle(r13));
    L11:
        String r14 = FIELD_OVERALL_RATING;
        if (r5.containsKey(r14) == false) goto L16;
        Bundle r15 = r5.getBundle(r14);
        if (r15 == null) goto L16;
        r0.setOverallRating(Rating.fromBundle(r15));
    L16:
        String r16 = FIELD_DURATION_MS;
        if (r5.containsKey(r16) == false) goto L19;
        r0.setDurationMs(Long.valueOf(r5.getLong(r16)));
    L19:
        String r17 = FIELD_TRACK_NUMBER;
        if (r5.containsKey(r17) == false) goto L22;
        r0.setTrackNumber(Integer.valueOf(r5.getInt(r17)));
    L22:
        String r18 = FIELD_TOTAL_TRACK_COUNT;
        if (r5.containsKey(r18) == false) goto L25;
        r0.setTotalTrackCount(Integer.valueOf(r5.getInt(r18)));
    L25:
        String r19 = FIELD_FOLDER_TYPE;
        if (r5.containsKey(r19) == false) goto L28;
        r0.setFolderType(Integer.valueOf(r5.getInt(r19)));
    L28:
        String r110 = FIELD_IS_BROWSABLE;
        if (r5.containsKey(r110) == false) goto L31;
        r0.setIsBrowsable(Boolean.valueOf(r5.getBoolean(r110)));
    L31:
        String r111 = FIELD_IS_PLAYABLE;
        if (r5.containsKey(r111) == false) goto L34;
        r0.setIsPlayable(Boolean.valueOf(r5.getBoolean(r111)));
    L34:
        String r112 = FIELD_RECORDING_YEAR;
        if (r5.containsKey(r112) == false) goto L37;
        r0.setRecordingYear(Integer.valueOf(r5.getInt(r112)));
    L37:
        String r113 = FIELD_RECORDING_MONTH;
        if (r5.containsKey(r113) == false) goto L40;
        r0.setRecordingMonth(Integer.valueOf(r5.getInt(r113)));
    L40:
        String r114 = FIELD_RECORDING_DAY;
        if (r5.containsKey(r114) == false) goto L43;
        r0.setRecordingDay(Integer.valueOf(r5.getInt(r114)));
    L43:
        String r115 = FIELD_RELEASE_YEAR;
        if (r5.containsKey(r115) == false) goto L46;
        r0.setReleaseYear(Integer.valueOf(r5.getInt(r115)));
    L46:
        String r116 = FIELD_RELEASE_MONTH;
        if (r5.containsKey(r116) == false) goto L49;
        r0.setReleaseMonth(Integer.valueOf(r5.getInt(r116)));
    L49:
        String r117 = FIELD_RELEASE_DAY;
        if (r5.containsKey(r117) == false) goto L52;
        r0.setReleaseDay(Integer.valueOf(r5.getInt(r117)));
    L52:
        String r118 = FIELD_DISC_NUMBER;
        if (r5.containsKey(r118) == false) goto L55;
        r0.setDiscNumber(Integer.valueOf(r5.getInt(r118)));
    L55:
        String r119 = FIELD_TOTAL_DISC_COUNT;
        if (r5.containsKey(r119) == false) goto L58;
        r0.setTotalDiscCount(Integer.valueOf(r5.getInt(r119)));
    L58:
        String r120 = FIELD_MEDIA_TYPE;
        if (r5.containsKey(r120) == false) goto L61;
        r0.setMediaType(Integer.valueOf(r5.getInt(r120)));
    L61:
        ArrayList<String> r52 = r5.getStringArrayList(FIELD_SUPPORTED_COMMANDS);
        if (r52 == null) goto L65;
        r0.setSupportedCommands(r52);
    L65:
        return r0.build();
    L5:
        r32 = null;
        goto L6
    }

    private static int getFolderTypeFromMediaType(int r0) {
        switch(r0) {
            case 1: goto L15;
            case 2: goto L15;
            case 3: goto L15;
            case 4: goto L15;
            case 5: goto L15;
            case 6: goto L15;
            case 7: goto L15;
            case 8: goto L15;
            case 9: goto L15;
            case 10: goto L15;
            case 11: goto L15;
            case 12: goto L15;
            case 13: goto L15;
            case 14: goto L15;
            case 15: goto L15;
            case 16: goto L15;
            case 17: goto L15;
            case 18: goto L15;
            case 19: goto L15;
            case 20: goto L3;
            case 21: goto L13;
            case 22: goto L11;
            case 23: goto L9;
            case 24: goto L7;
            case 25: goto L5;
            case 26: goto L3;
            case 27: goto L3;
            case 28: goto L3;
            case 29: goto L3;
            case 30: goto L3;
            case 31: goto L15;
            case 32: goto L15;
            case 33: goto L15;
            case 34: goto L15;
            case 35: goto L15;
            default: goto L3;
        };
    L3:
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
    }

    private static int getMediaTypeFromFolderType(int r0) {
        switch(r0) {
            case 1: goto L15;
            case 2: goto L13;
            case 3: goto L11;
            case 4: goto L9;
            case 5: goto L7;
            case 6: goto L5;
            default: goto L3;
        };
    L3:
        return 20;
    L5:
        return 25;
    L7:
        return 24;
    L9:
        return 23;
    L11:
        return 22;
    L13:
        return 21;
    L15:
        return 0;
    }

    public Builder buildUpon() {
        return new Builder(this, null);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L88:
        return false;
    L8:
        if (MediaMetadata.class != r5.getClass()) goto L88;
        MediaMetadata r52 = (MediaMetadata) r5;
        if (Objects.equals(this.title, r52.title) == false) goto L88;
        if (Objects.equals(this.artist, r52.artist) == false) goto L88;
        if (Objects.equals(this.albumTitle, r52.albumTitle) == false) goto L88;
        if (Objects.equals(this.albumArtist, r52.albumArtist) == false) goto L88;
        if (Objects.equals(this.displayTitle, r52.displayTitle) == false) goto L88;
        if (Objects.equals(this.subtitle, r52.subtitle) == false) goto L88;
        if (Objects.equals(this.description, r52.description) == false) goto L88;
        if (Objects.equals(this.durationMs, r52.durationMs) == false) goto L88;
        if (Objects.equals(this.userRating, r52.userRating) == false) goto L88;
        if (Objects.equals(this.overallRating, r52.overallRating) == false) goto L88;
        if (Arrays.equals(this.artworkData, r52.artworkData) == false) goto L88;
        if (Objects.equals(this.artworkDataType, r52.artworkDataType) == false) goto L88;
        if (Objects.equals(this.artworkUri, r52.artworkUri) == false) goto L88;
        if (Objects.equals(this.trackNumber, r52.trackNumber) == false) goto L88;
        if (Objects.equals(this.totalTrackCount, r52.totalTrackCount) == false) goto L88;
        if (Objects.equals(this.folderType, r52.folderType) == false) goto L88;
        if (Objects.equals(this.isBrowsable, r52.isBrowsable) == false) goto L88;
        if (Objects.equals(this.isPlayable, r52.isPlayable) == false) goto L88;
        if (Objects.equals(this.recordingYear, r52.recordingYear) == false) goto L88;
        if (Objects.equals(this.recordingMonth, r52.recordingMonth) == false) goto L88;
        if (Objects.equals(this.recordingDay, r52.recordingDay) == false) goto L88;
        if (Objects.equals(this.releaseYear, r52.releaseYear) == false) goto L88;
        if (Objects.equals(this.releaseMonth, r52.releaseMonth) == false) goto L88;
        if (Objects.equals(this.releaseDay, r52.releaseDay) == false) goto L88;
        if (Objects.equals(this.writer, r52.writer) == false) goto L88;
        if (Objects.equals(this.composer, r52.composer) == false) goto L88;
        if (Objects.equals(this.conductor, r52.conductor) == false) goto L88;
        if (Objects.equals(this.discNumber, r52.discNumber) == false) goto L88;
        if (Objects.equals(this.totalDiscCount, r52.totalDiscCount) == false) goto L88;
        if (Objects.equals(this.genre, r52.genre) == false) goto L88;
        if (Objects.equals(this.compilation, r52.compilation) == false) goto L88;
        if (Objects.equals(this.station, r52.station) == false) goto L88;
        if (Objects.equals(this.mediaType, r52.mediaType) == false) goto L88;
        if (Objects.equals(this.supportedCommands, r52.supportedCommands) == false) goto L88;
        if (this.extras != null) goto L81;
        boolean r4 = true;
    L83:
        if (r52.extras != null) goto L85;
        boolean r53 = true;
    L86:
        if (r4 != r53) goto L88;
        return true;
    L85:
        r53 = false;
        goto L86
    L81:
        r4 = false;
        goto L83
    }

    public int hashCode() {
        CharSequence r1 = this.title;
        CharSequence r2 = this.artist;
        CharSequence r3 = this.albumTitle;
        CharSequence r4 = this.albumArtist;
        CharSequence r5 = this.displayTitle;
        CharSequence r6 = this.subtitle;
        CharSequence r7 = this.description;
        Long r8 = this.durationMs;
        Rating r9 = this.userRating;
        Rating r10 = this.overallRating;
        Integer r11 = Integer.valueOf(Arrays.hashCode(this.artworkData));
        Integer r12 = this.artworkDataType;
        Uri r13 = this.artworkUri;
        Integer r14 = this.trackNumber;
        Integer r15 = this.totalTrackCount;
        Integer r16 = this.folderType;
        Boolean r17 = this.isBrowsable;
        Boolean r18 = this.isPlayable;
        Integer r19 = this.recordingYear;
        Integer r110 = this.recordingMonth;
        Integer r111 = this.recordingDay;
        Integer r112 = this.releaseYear;
        Integer r113 = this.releaseMonth;
        Integer r114 = this.releaseDay;
        CharSequence r115 = this.writer;
        CharSequence r116 = this.composer;
        CharSequence r117 = this.conductor;
        Integer r118 = this.discNumber;
        Integer r119 = this.totalDiscCount;
        CharSequence r120 = this.genre;
        CharSequence r121 = this.compilation;
        CharSequence r122 = this.station;
        Integer r123 = this.mediaType;
        if (this.extras != null) goto L5;
        boolean r124 = true;
    L7:
        return Objects.hash(new Object[]{r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r110, r111, r112, r113, r114, r115, r116, r117, r118, r119, r120, r121, r122, r123, Boolean.valueOf(r124), this.supportedCommands});
    L5:
        r124 = false;
        goto L7
    }

    public Bundle toBundle() {
        Bundle r0 = new Bundle();
        CharSequence r1 = this.title;
        if (r1 == null) goto L5;
        r0.putCharSequence(FIELD_TITLE, r1);
    L5:
        CharSequence r12 = this.artist;
        if (r12 == null) goto L8;
        r0.putCharSequence(FIELD_ARTIST, r12);
    L8:
        CharSequence r13 = this.albumTitle;
        if (r13 == null) goto L11;
        r0.putCharSequence(FIELD_ALBUM_TITLE, r13);
    L11:
        CharSequence r14 = this.albumArtist;
        if (r14 == null) goto L14;
        r0.putCharSequence(FIELD_ALBUM_ARTIST, r14);
    L14:
        CharSequence r15 = this.displayTitle;
        if (r15 == null) goto L17;
        r0.putCharSequence(FIELD_DISPLAY_TITLE, r15);
    L17:
        CharSequence r16 = this.subtitle;
        if (r16 == null) goto L20;
        r0.putCharSequence(FIELD_SUBTITLE, r16);
    L20:
        CharSequence r17 = this.description;
        if (r17 == null) goto L23;
        r0.putCharSequence(FIELD_DESCRIPTION, r17);
    L23:
        Long r18 = this.durationMs;
        if (r18 == null) goto L26;
        r0.putLong(FIELD_DURATION_MS, r18.longValue());
    L26:
        byte[] r19 = this.artworkData;
        if (r19 == null) goto L29;
        r0.putByteArray(FIELD_ARTWORK_DATA, r19);
    L29:
        Uri r110 = this.artworkUri;
        if (r110 == null) goto L32;
        r0.putParcelable(FIELD_ARTWORK_URI, r110);
    L32:
        CharSequence r111 = this.writer;
        if (r111 == null) goto L35;
        r0.putCharSequence(FIELD_WRITER, r111);
    L35:
        CharSequence r112 = this.composer;
        if (r112 == null) goto L38;
        r0.putCharSequence(FIELD_COMPOSER, r112);
    L38:
        CharSequence r113 = this.conductor;
        if (r113 == null) goto L41;
        r0.putCharSequence(FIELD_CONDUCTOR, r113);
    L41:
        CharSequence r114 = this.genre;
        if (r114 == null) goto L44;
        r0.putCharSequence(FIELD_GENRE, r114);
    L44:
        CharSequence r115 = this.compilation;
        if (r115 == null) goto L47;
        r0.putCharSequence(FIELD_COMPILATION, r115);
    L47:
        CharSequence r116 = this.station;
        if (r116 == null) goto L50;
        r0.putCharSequence(FIELD_STATION, r116);
    L50:
        Rating r117 = this.userRating;
        if (r117 == null) goto L53;
        r0.putBundle(FIELD_USER_RATING, r117.toBundle());
    L53:
        Rating r118 = this.overallRating;
        if (r118 == null) goto L56;
        r0.putBundle(FIELD_OVERALL_RATING, r118.toBundle());
    L56:
        Integer r119 = this.trackNumber;
        if (r119 == null) goto L59;
        r0.putInt(FIELD_TRACK_NUMBER, r119.intValue());
    L59:
        Integer r120 = this.totalTrackCount;
        if (r120 == null) goto L62;
        r0.putInt(FIELD_TOTAL_TRACK_COUNT, r120.intValue());
    L62:
        Integer r121 = this.folderType;
        if (r121 == null) goto L65;
        r0.putInt(FIELD_FOLDER_TYPE, r121.intValue());
    L65:
        Boolean r122 = this.isBrowsable;
        if (r122 == null) goto L68;
        r0.putBoolean(FIELD_IS_BROWSABLE, r122.booleanValue());
    L68:
        Boolean r123 = this.isPlayable;
        if (r123 == null) goto L71;
        r0.putBoolean(FIELD_IS_PLAYABLE, r123.booleanValue());
    L71:
        Integer r124 = this.recordingYear;
        if (r124 == null) goto L74;
        r0.putInt(FIELD_RECORDING_YEAR, r124.intValue());
    L74:
        Integer r125 = this.recordingMonth;
        if (r125 == null) goto L77;
        r0.putInt(FIELD_RECORDING_MONTH, r125.intValue());
    L77:
        Integer r126 = this.recordingDay;
        if (r126 == null) goto L80;
        r0.putInt(FIELD_RECORDING_DAY, r126.intValue());
    L80:
        Integer r127 = this.releaseYear;
        if (r127 == null) goto L83;
        r0.putInt(FIELD_RELEASE_YEAR, r127.intValue());
    L83:
        Integer r128 = this.releaseMonth;
        if (r128 == null) goto L86;
        r0.putInt(FIELD_RELEASE_MONTH, r128.intValue());
    L86:
        Integer r129 = this.releaseDay;
        if (r129 == null) goto L89;
        r0.putInt(FIELD_RELEASE_DAY, r129.intValue());
    L89:
        Integer r130 = this.discNumber;
        if (r130 == null) goto L92;
        r0.putInt(FIELD_DISC_NUMBER, r130.intValue());
    L92:
        Integer r131 = this.totalDiscCount;
        if (r131 == null) goto L95;
        r0.putInt(FIELD_TOTAL_DISC_COUNT, r131.intValue());
    L95:
        Integer r132 = this.artworkDataType;
        if (r132 == null) goto L98;
        r0.putInt(FIELD_ARTWORK_DATA_TYPE, r132.intValue());
    L98:
        Integer r133 = this.mediaType;
        if (r133 == null) goto L102;
        r0.putInt(FIELD_MEDIA_TYPE, r133.intValue());
    L102:
        if (this.supportedCommands.isEmpty() == true) goto L104;
        r0.putStringArrayList(FIELD_SUPPORTED_COMMANDS, new ArrayList(this.supportedCommands));
    L104:
        Bundle r5 = this.extras;
        if (r5 == null) goto L107;
        r0.putBundle(FIELD_EXTRAS, r5);
    L107:
        return r0;
    }
}
