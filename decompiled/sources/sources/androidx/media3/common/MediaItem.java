package androidx.media3.common;

import android.net.Uri;
import android.os.Bundle;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.Util;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes3.dex */
public final class MediaItem {
    public static final MediaItem EMPTY = null;
    private static final String FIELD_CLIPPING_PROPERTIES = null;
    private static final String FIELD_LIVE_CONFIGURATION = null;
    private static final String FIELD_LOCAL_CONFIGURATION = null;
    private static final String FIELD_MEDIA_ID = null;
    private static final String FIELD_MEDIA_METADATA = null;
    private static final String FIELD_REQUEST_METADATA = null;
    public final ClippingConfiguration clippingConfiguration;

    @Deprecated
    public final ClippingProperties clippingProperties;
    public final LiveConfiguration liveConfiguration;
    public final LocalConfiguration localConfiguration;
    public final String mediaId;
    public final MediaMetadata mediaMetadata;

    @Deprecated
    public final LocalConfiguration playbackProperties;
    public final RequestMetadata requestMetadata;

    /* renamed from: androidx.media3.common.MediaItem$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class AdsConfiguration {
        private static final String FIELD_AD_TAG_URI = null;
        public final Uri adTagUri;
        public final Object adsId;

        public static final class Builder {
            private Uri adTagUri;
            private Object adsId;

            public Builder(Uri r1) {
                this.adTagUri = r1;
            }

            public static /* synthetic */ Uri access$1600(Builder r0) {
                return r0.adTagUri;
            }

            public static /* synthetic */ Object access$1700(Builder r0) {
                return r0.adsId;
            }

            public AdsConfiguration build() {
                return new AdsConfiguration(this, null);
            }
        }

        static {
            FIELD_AD_TAG_URI = Util.intToStringMaxRadix(0);
        }

        private AdsConfiguration(Builder r2) {
            this.adTagUri = Builder.access$1600(r2);
            this.adsId = Builder.access$1700(r2);
        }

        public /* synthetic */ AdsConfiguration(Builder r1, AnonymousClass1 r2) {
            this(r1);
        }

        public static AdsConfiguration fromBundle(Bundle r1) {
            Uri r12 = (Uri) r1.getParcelable(FIELD_AD_TAG_URI);
            Assertions.checkNotNull(r12);
            return new Builder(r12).build();
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof AdsConfiguration) == true) goto L8;
            return false;
        L8:
            AdsConfiguration r52 = (AdsConfiguration) r5;
            if (this.adTagUri.equals(r52.adTagUri) == true) goto L11;
        L13:
            return false;
        L11:
            if (Objects.equals(this.adsId, r52.adsId) == false) goto L13;
            return true;
        }

        public int hashCode() {
            int r0 = this.adTagUri.hashCode() * 31;
            Object r1 = this.adsId;
            if (r1 == null) goto L5;
            int r12 = r1.hashCode();
        L7:
            return r0 + r12;
        L5:
            r12 = 0;
            goto L7
        }

        public Bundle toBundle() {
            Bundle r0 = new Bundle();
            r0.putParcelable(FIELD_AD_TAG_URI, this.adTagUri);
            return r0;
        }
    }

    public static final class Builder {
        private AdsConfiguration adsConfiguration;
        private ClippingConfiguration.Builder clippingConfiguration;
        private String customCacheKey;
        private DrmConfiguration.Builder drmConfiguration;
        private long imageDurationMs;
        private LiveConfiguration.Builder liveConfiguration;
        private String mediaId;
        private MediaMetadata mediaMetadata;
        private String mimeType;
        private RequestMetadata requestMetadata;
        private List<StreamKey> streamKeys;
        private ImmutableList<SubtitleConfiguration> subtitleConfigurations;
        private Object tag;
        private Uri uri;

        public Builder() {
            this.clippingConfiguration = new ClippingConfiguration.Builder();
            this.drmConfiguration = new DrmConfiguration.Builder(null);
            this.streamKeys = Collections.EMPTY_LIST;
            this.subtitleConfigurations = ImmutableList.of();
            this.liveConfiguration = new LiveConfiguration.Builder();
            this.requestMetadata = RequestMetadata.EMPTY;
            this.imageDurationMs = -9223372036854775807L;
        }

        private Builder(MediaItem r3) {
            this();
            this.clippingConfiguration = r3.clippingConfiguration.buildUpon();
            this.mediaId = r3.mediaId;
            this.mediaMetadata = r3.mediaMetadata;
            this.liveConfiguration = r3.liveConfiguration.buildUpon();
            this.requestMetadata = r3.requestMetadata;
            LocalConfiguration r32 = r3.localConfiguration;
            if (r32 == null) goto L10;
            this.customCacheKey = r32.customCacheKey;
            this.mimeType = r32.mimeType;
            this.uri = r32.uri;
            this.streamKeys = r32.streamKeys;
            this.subtitleConfigurations = r32.subtitleConfigurations;
            this.tag = r32.tag;
            DrmConfiguration r0 = r32.drmConfiguration;
            if (r0 == null) goto L7;
            DrmConfiguration.Builder r02 = r0.buildUpon();
        L8:
            this.drmConfiguration = r02;
            this.adsConfiguration = r32.adsConfiguration;
            this.imageDurationMs = r32.imageDurationMs;
            return;
        L7:
            r02 = new DrmConfiguration.Builder(null);
            goto L8
        }

        public /* synthetic */ Builder(MediaItem r1, AnonymousClass1 r2) {
            this(r1);
        }

        public MediaItem build() {
            if (DrmConfiguration.Builder.access$200(this.drmConfiguration) != null) goto L5;
        L8:
            boolean r0 = true;
        L9:
            Assertions.checkState(r0);
            Uri r2 = this.uri;
            DrmConfiguration r02 = null;
            if (r2 == null) goto L15;
            String r3 = this.mimeType;
            if (DrmConfiguration.Builder.access$300(this.drmConfiguration) == null) goto L14;
            r02 = this.drmConfiguration.build();
        L14:
            LocalConfiguration r5 = new LocalConfiguration(r2, r3, r02, this.adsConfiguration, this.streamKeys, this.customCacheKey, this.subtitleConfigurations, this.tag, this.imageDurationMs, null);
        L16:
            String r03 = this.mediaId;
            if (r03 == null) goto L19;
        L18:
            String r32 = r03;
            ClippingProperties r4 = this.clippingConfiguration.buildClippingProperties();
            LiveConfiguration r6 = this.liveConfiguration.build();
            MediaMetadata r04 = this.mediaMetadata;
            if (r04 != null) goto L25;
            r04 = MediaMetadata.EMPTY;
        L25:
            return new MediaItem(r32, r4, r5, r6, r04, this.requestMetadata, null);
        L19:
            r03 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L18
        L15:
            r5 = null;
            goto L16
        L5:
            if (DrmConfiguration.Builder.access$300(this.drmConfiguration) != null) goto L8;
            r0 = false;
            goto L9
        }

        public Builder setLiveConfiguration(LiveConfiguration r1) {
            this.liveConfiguration = r1.buildUpon();
            return this;
        }

        public Builder setMediaId(String r1) {
            this.mediaId = (String) Assertions.checkNotNull(r1);
            return this;
        }

        public Builder setMediaMetadata(MediaMetadata r1) {
            this.mediaMetadata = r1;
            return this;
        }

        public Builder setRequestMetadata(RequestMetadata r1) {
            this.requestMetadata = r1;
            return this;
        }

        public Builder setSubtitleConfigurations(List<SubtitleConfiguration> r1) {
            this.subtitleConfigurations = ImmutableList.copyOf(r1);
            return this;
        }

        public Builder setTag(Object r1) {
            this.tag = r1;
            return this;
        }

        public Builder setUri(Uri r1) {
            this.uri = r1;
            return this;
        }

        public Builder setUri(String r1) {
            if (r1 != null) goto L4;
            Uri r12 = null;
        L6:
            return setUri(r12);
        L4:
            r12 = Uri.parse(r1);
            goto L6
        }
    }

    public static class ClippingConfiguration {
        private static final String FIELD_ALLOW_UNSEEKABLE_MEDIA = null;
        private static final String FIELD_END_POSITION_MS = null;
        static final String FIELD_END_POSITION_US = null;
        private static final String FIELD_RELATIVE_TO_DEFAULT_POSITION = null;
        private static final String FIELD_RELATIVE_TO_LIVE_WINDOW = null;
        private static final String FIELD_STARTS_AT_KEY_FRAME = null;
        private static final String FIELD_START_POSITION_MS = null;
        static final String FIELD_START_POSITION_US = null;
        public static final ClippingConfiguration UNSET = null;
        public final boolean allowUnseekableMedia;
        public final long endPositionMs;
        public final long endPositionUs;
        public final boolean relativeToDefaultPosition;
        public final boolean relativeToLiveWindow;
        public final long startPositionMs;
        public final long startPositionUs;
        public final boolean startsAtKeyFrame;

        public static final class Builder {
            private boolean allowUnseekableMedia;
            private long endPositionUs;
            private boolean relativeToDefaultPosition;
            private boolean relativeToLiveWindow;
            private long startPositionUs;
            private boolean startsAtKeyFrame;

            public Builder() {
                this.endPositionUs = Long.MIN_VALUE;
            }

            private Builder(ClippingConfiguration r3) {
                this.startPositionUs = r3.startPositionUs;
                this.endPositionUs = r3.endPositionUs;
                this.relativeToLiveWindow = r3.relativeToLiveWindow;
                this.relativeToDefaultPosition = r3.relativeToDefaultPosition;
                this.startsAtKeyFrame = r3.startsAtKeyFrame;
                this.allowUnseekableMedia = r3.allowUnseekableMedia;
            }

            public /* synthetic */ Builder(ClippingConfiguration r1, AnonymousClass1 r2) {
                this(r1);
            }

            public static /* synthetic */ long access$3900(Builder r2) {
                return r2.startPositionUs;
            }

            public static /* synthetic */ long access$4000(Builder r2) {
                return r2.endPositionUs;
            }

            public static /* synthetic */ boolean access$4100(Builder r0) {
                return r0.relativeToLiveWindow;
            }

            public static /* synthetic */ boolean access$4200(Builder r0) {
                return r0.relativeToDefaultPosition;
            }

            public static /* synthetic */ boolean access$4300(Builder r0) {
                return r0.startsAtKeyFrame;
            }

            public static /* synthetic */ boolean access$4400(Builder r0) {
                return r0.allowUnseekableMedia;
            }

            public ClippingConfiguration build() {
                return new ClippingConfiguration(this, null);
            }

            @Deprecated
            public ClippingProperties buildClippingProperties() {
                return new ClippingProperties(this, null);
            }

            public Builder setAllowUnseekableMedia(boolean r1) {
                this.allowUnseekableMedia = r1;
                return this;
            }

            public Builder setEndPositionMs(long r1) {
                return setEndPositionUs(Util.msToUs(r1));
            }

            public Builder setEndPositionUs(long r3) {
                if (r3 != Long.MIN_VALUE) goto L5;
            L8:
                boolean r0 = true;
            L9:
                Assertions.checkArgument(r0);
                this.endPositionUs = r3;
                return this;
            L5:
                if (r3 >= 0) goto L8;
                r0 = false;
                goto L9
            }

            public Builder setRelativeToDefaultPosition(boolean r1) {
                this.relativeToDefaultPosition = r1;
                return this;
            }

            public Builder setRelativeToLiveWindow(boolean r1) {
                this.relativeToLiveWindow = r1;
                return this;
            }

            public Builder setStartPositionMs(long r1) {
                return setStartPositionUs(Util.msToUs(r1));
            }

            public Builder setStartPositionUs(long r3) {
                if (r3 < 0) goto L5;
                boolean r0 = true;
            L6:
                Assertions.checkArgument(r0);
                this.startPositionUs = r3;
                return this;
            L5:
                r0 = false;
                goto L6
            }

            public Builder setStartsAtKeyFrame(boolean r1) {
                this.startsAtKeyFrame = r1;
                return this;
            }
        }

        static {
            UNSET = new Builder().build();
            FIELD_START_POSITION_MS = Util.intToStringMaxRadix(0);
            FIELD_END_POSITION_MS = Util.intToStringMaxRadix(1);
            FIELD_RELATIVE_TO_LIVE_WINDOW = Util.intToStringMaxRadix(2);
            FIELD_RELATIVE_TO_DEFAULT_POSITION = Util.intToStringMaxRadix(3);
            FIELD_STARTS_AT_KEY_FRAME = Util.intToStringMaxRadix(4);
            FIELD_START_POSITION_US = Util.intToStringMaxRadix(5);
            FIELD_END_POSITION_US = Util.intToStringMaxRadix(6);
            FIELD_ALLOW_UNSEEKABLE_MEDIA = Util.intToStringMaxRadix(7);
        }

        private ClippingConfiguration(Builder r3) {
            this.startPositionMs = Util.usToMs(Builder.access$3900(r3));
            this.endPositionMs = Util.usToMs(Builder.access$4000(r3));
            this.startPositionUs = Builder.access$3900(r3);
            this.endPositionUs = Builder.access$4000(r3);
            this.relativeToLiveWindow = Builder.access$4100(r3);
            this.relativeToDefaultPosition = Builder.access$4200(r3);
            this.startsAtKeyFrame = Builder.access$4300(r3);
            this.allowUnseekableMedia = Builder.access$4400(r3);
        }

        public /* synthetic */ ClippingConfiguration(Builder r1, AnonymousClass1 r2) {
            this(r1);
        }

        public static ClippingProperties fromBundle(Bundle r7) {
            Builder r0 = new Builder();
            String r1 = FIELD_START_POSITION_MS;
            ClippingConfiguration r2 = UNSET;
            Builder r02 = r0.setStartPositionMs(r7.getLong(r1, r2.startPositionMs)).setEndPositionMs(r7.getLong(FIELD_END_POSITION_MS, r2.endPositionMs)).setRelativeToLiveWindow(r7.getBoolean(FIELD_RELATIVE_TO_LIVE_WINDOW, r2.relativeToLiveWindow)).setRelativeToDefaultPosition(r7.getBoolean(FIELD_RELATIVE_TO_DEFAULT_POSITION, r2.relativeToDefaultPosition)).setStartsAtKeyFrame(r7.getBoolean(FIELD_STARTS_AT_KEY_FRAME, r2.startsAtKeyFrame)).setAllowUnseekableMedia(r7.getBoolean(FIELD_ALLOW_UNSEEKABLE_MEDIA, r2.allowUnseekableMedia));
            long r3 = r7.getLong(FIELD_START_POSITION_US, r2.startPositionUs);
            if (r3 == r2.startPositionUs) goto L5;
            r02.setStartPositionUs(r3);
        L5:
            long r32 = r7.getLong(FIELD_END_POSITION_US, r2.endPositionUs);
            if (r32 == r2.endPositionUs) goto L9;
            r02.setEndPositionUs(r32);
        L9:
            return r02.buildClippingProperties();
        }

        public Builder buildUpon() {
            return new Builder(this, null);
        }

        public boolean equals(Object r8) {
            if (this != r8) goto L6;
            return true;
        L6:
            if ((r8 instanceof ClippingConfiguration) == true) goto L8;
            return false;
        L8:
            ClippingConfiguration r82 = (ClippingConfiguration) r8;
            if (this.startPositionUs == r82.startPositionUs) goto L11;
        L21:
            return false;
        L11:
            if (this.endPositionUs != r82.endPositionUs) goto L21;
            if (this.relativeToLiveWindow != r82.relativeToLiveWindow) goto L21;
            if (this.relativeToDefaultPosition != r82.relativeToDefaultPosition) goto L21;
            if (this.startsAtKeyFrame != r82.startsAtKeyFrame) goto L21;
            if (this.allowUnseekableMedia != r82.allowUnseekableMedia) goto L21;
            return true;
        }

        public int hashCode() {
            long r0 = this.startPositionUs;
            int r02 = ((int) (r0 ^ (r0 >>> 32))) * 31;
            long r3 = this.endPositionUs;
            return ((((((((r02 + ((int) ((r3 >>> 32) ^ r3))) * 31) + (this.relativeToLiveWindow ? 1 : 0)) * 31) + (this.relativeToDefaultPosition ? 1 : 0)) * 31) + (this.startsAtKeyFrame ? 1 : 0)) * 31) + (this.allowUnseekableMedia ? 1 : 0);
        }

        public Bundle toBundle() {
            Bundle r0 = new Bundle();
            long r1 = this.startPositionMs;
            ClippingConfiguration r3 = UNSET;
            if (r1 == r3.startPositionMs) goto L5;
            r0.putLong(FIELD_START_POSITION_MS, r1);
        L5:
            long r12 = this.endPositionMs;
            if (r12 == r3.endPositionMs) goto L8;
            r0.putLong(FIELD_END_POSITION_MS, r12);
        L8:
            long r13 = this.startPositionUs;
            if (r13 == r3.startPositionUs) goto L11;
            r0.putLong(FIELD_START_POSITION_US, r13);
        L11:
            long r14 = this.endPositionUs;
            if (r14 == r3.endPositionUs) goto L14;
            r0.putLong(FIELD_END_POSITION_US, r14);
        L14:
            boolean r15 = this.relativeToLiveWindow;
            if (r15 == r3.relativeToLiveWindow) goto L17;
            r0.putBoolean(FIELD_RELATIVE_TO_LIVE_WINDOW, r15);
        L17:
            boolean r16 = this.relativeToDefaultPosition;
            if (r16 == r3.relativeToDefaultPosition) goto L20;
            r0.putBoolean(FIELD_RELATIVE_TO_DEFAULT_POSITION, r16);
        L20:
            boolean r17 = this.startsAtKeyFrame;
            if (r17 == r3.startsAtKeyFrame) goto L23;
            r0.putBoolean(FIELD_STARTS_AT_KEY_FRAME, r17);
        L23:
            boolean r6 = this.allowUnseekableMedia;
            if (r6 == r3.allowUnseekableMedia) goto L26;
            r0.putBoolean(FIELD_ALLOW_UNSEEKABLE_MEDIA, r6);
        L26:
            return r0;
        }
    }

    @Deprecated
    public static final class ClippingProperties extends ClippingConfiguration {
        public static final ClippingProperties UNSET = null;

        static {
            UNSET = new ClippingConfiguration.Builder().buildClippingProperties();
        }

        private ClippingProperties(ClippingConfiguration.Builder r2) {
            super(r2, null);
        }

        public /* synthetic */ ClippingProperties(ClippingConfiguration.Builder r1, AnonymousClass1 r2) {
            this(r1);
        }
    }

    public static final class DrmConfiguration {
        private static final String FIELD_FORCED_SESSION_TRACK_TYPES = null;
        private static final String FIELD_FORCE_DEFAULT_LICENSE_URI = null;
        private static final String FIELD_KEY_SET_ID = null;
        private static final String FIELD_LICENSE_REQUEST_HEADERS = null;
        private static final String FIELD_LICENSE_URI = null;
        private static final String FIELD_MULTI_SESSION = null;
        static final String FIELD_PLAY_CLEAR_CONTENT_WITHOUT_KEY = null;
        private static final String FIELD_SCHEME = null;
        public final boolean forceDefaultLicenseUri;
        public final ImmutableList<Integer> forcedSessionTrackTypes;
        private final byte[] keySetId;
        public final ImmutableMap<String, String> licenseRequestHeaders;
        public final Uri licenseUri;
        public final boolean multiSession;
        public final boolean playClearContentWithoutKey;

        @Deprecated
        public final ImmutableMap<String, String> requestHeaders;
        public final UUID scheme;

        @Deprecated
        public final ImmutableList<Integer> sessionForClearTypes;

        @Deprecated
        public final UUID uuid;

        public static final class Builder {
            private boolean forceDefaultLicenseUri;
            private ImmutableList<Integer> forcedSessionTrackTypes;
            private byte[] keySetId;
            private ImmutableMap<String, String> licenseRequestHeaders;
            private Uri licenseUri;
            private boolean multiSession;
            private boolean playClearContentWithoutKey;
            private UUID scheme;

            @Deprecated
            private Builder() {
                this.licenseRequestHeaders = ImmutableMap.of();
                this.playClearContentWithoutKey = true;
                this.forcedSessionTrackTypes = ImmutableList.of();
            }

            public /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder(DrmConfiguration r2) {
                this.scheme = r2.scheme;
                this.licenseUri = r2.licenseUri;
                this.licenseRequestHeaders = r2.licenseRequestHeaders;
                this.multiSession = r2.multiSession;
                this.playClearContentWithoutKey = r2.playClearContentWithoutKey;
                this.forceDefaultLicenseUri = r2.forceDefaultLicenseUri;
                this.forcedSessionTrackTypes = r2.forcedSessionTrackTypes;
                this.keySetId = DrmConfiguration.access$600(r2);
            }

            public /* synthetic */ Builder(DrmConfiguration r1, AnonymousClass1 r2) {
                this(r1);
            }

            public Builder(UUID r1) {
                this();
                this.scheme = r1;
            }

            public static /* synthetic */ boolean access$1000(Builder r0) {
                return r0.multiSession;
            }

            public static /* synthetic */ boolean access$1100(Builder r0) {
                return r0.playClearContentWithoutKey;
            }

            public static /* synthetic */ ImmutableList access$1200(Builder r0) {
                return r0.forcedSessionTrackTypes;
            }

            public static /* synthetic */ byte[] access$1300(Builder r0) {
                return r0.keySetId;
            }

            public static /* synthetic */ Uri access$200(Builder r0) {
                return r0.licenseUri;
            }

            public static /* synthetic */ UUID access$300(Builder r0) {
                return r0.scheme;
            }

            public static /* synthetic */ boolean access$800(Builder r0) {
                return r0.forceDefaultLicenseUri;
            }

            public static /* synthetic */ ImmutableMap access$900(Builder r0) {
                return r0.licenseRequestHeaders;
            }

            public DrmConfiguration build() {
                return new DrmConfiguration(this, null);
            }

            public Builder setForceDefaultLicenseUri(boolean r1) {
                this.forceDefaultLicenseUri = r1;
                return this;
            }

            public Builder setForcedSessionTrackTypes(List<Integer> r1) {
                this.forcedSessionTrackTypes = ImmutableList.copyOf(r1);
                return this;
            }

            public Builder setKeySetId(byte[] r2) {
                if (r2 == null) goto L4;
                byte[] r22 = Arrays.copyOf(r2, r2.length);
            L5:
                this.keySetId = r22;
                return this;
            L4:
                r22 = null;
                goto L5
            }

            public Builder setLicenseRequestHeaders(Map<String, String> r1) {
                this.licenseRequestHeaders = ImmutableMap.copyOf(r1);
                return this;
            }

            public Builder setLicenseUri(Uri r1) {
                this.licenseUri = r1;
                return this;
            }

            public Builder setMultiSession(boolean r1) {
                this.multiSession = r1;
                return this;
            }

            public Builder setPlayClearContentWithoutKey(boolean r1) {
                this.playClearContentWithoutKey = r1;
                return this;
            }
        }

        static {
            FIELD_SCHEME = Util.intToStringMaxRadix(0);
            FIELD_LICENSE_URI = Util.intToStringMaxRadix(1);
            FIELD_LICENSE_REQUEST_HEADERS = Util.intToStringMaxRadix(2);
            FIELD_MULTI_SESSION = Util.intToStringMaxRadix(3);
            FIELD_PLAY_CLEAR_CONTENT_WITHOUT_KEY = Util.intToStringMaxRadix(4);
            FIELD_FORCE_DEFAULT_LICENSE_URI = Util.intToStringMaxRadix(5);
            FIELD_FORCED_SESSION_TRACK_TYPES = Util.intToStringMaxRadix(6);
            FIELD_KEY_SET_ID = Util.intToStringMaxRadix(7);
        }

        private DrmConfiguration(Builder r2) {
            if (Builder.access$800(r2) == true) goto L5;
        L8:
            boolean r0 = true;
        L9:
            Assertions.checkState(r0);
            UUID r02 = (UUID) Assertions.checkNotNull(Builder.access$300(r2));
            this.scheme = r02;
            this.uuid = r02;
            this.licenseUri = Builder.access$200(r2);
            this.requestHeaders = Builder.access$900(r2);
            this.licenseRequestHeaders = Builder.access$900(r2);
            this.multiSession = Builder.access$1000(r2);
            this.forceDefaultLicenseUri = Builder.access$800(r2);
            this.playClearContentWithoutKey = Builder.access$1100(r2);
            this.sessionForClearTypes = Builder.access$1200(r2);
            this.forcedSessionTrackTypes = Builder.access$1200(r2);
            if (Builder.access$1300(r2) == null) goto L12;
            byte[] r22 = Arrays.copyOf(Builder.access$1300(r2), Builder.access$1300(r2).length);
        L13:
            this.keySetId = r22;
            return;
        L12:
            r22 = null;
            goto L13
        L5:
            if (Builder.access$200(r2) != null) goto L8;
            r0 = false;
            goto L9
        }

        public /* synthetic */ DrmConfiguration(Builder r1, AnonymousClass1 r2) {
            this(r1);
        }

        public static /* synthetic */ byte[] access$600(DrmConfiguration r0) {
            return r0.keySetId;
        }

        public static DrmConfiguration fromBundle(Bundle r8) {
            UUID r0 = UUID.fromString((String) Assertions.checkNotNull(r8.getString(FIELD_SCHEME)));
            Uri r1 = (Uri) r8.getParcelable(FIELD_LICENSE_URI);
            ImmutableMap<String, String> r2 = BundleCollectionUtil.bundleToStringImmutableMap(BundleCollectionUtil.getBundleWithDefault(r8, FIELD_LICENSE_REQUEST_HEADERS, Bundle.EMPTY));
            boolean r3 = r8.getBoolean(FIELD_MULTI_SESSION, false);
            boolean r5 = r8.getBoolean(FIELD_PLAY_CLEAR_CONTENT_WITHOUT_KEY, false);
            boolean r4 = r8.getBoolean(FIELD_FORCE_DEFAULT_LICENSE_URI, false);
            ImmutableList r6 = ImmutableList.copyOf(BundleCollectionUtil.getIntegerArrayListWithDefault(r8, FIELD_FORCED_SESSION_TRACK_TYPES, new ArrayList()));
            byte[] r82 = r8.getByteArray(FIELD_KEY_SET_ID);
            return new Builder(r0).setLicenseUri(r1).setLicenseRequestHeaders(r2).setMultiSession(r3).setForceDefaultLicenseUri(r4).setPlayClearContentWithoutKey(r5).setForcedSessionTrackTypes(r6).setKeySetId(r82).build();
        }

        public Builder buildUpon() {
            return new Builder(this, null);
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof DrmConfiguration) == true) goto L8;
            return false;
        L8:
            DrmConfiguration r52 = (DrmConfiguration) r5;
            if (this.scheme.equals(r52.scheme) == true) goto L11;
        L25:
            return false;
        L11:
            if (Objects.equals(this.licenseUri, r52.licenseUri) == false) goto L25;
            if (Objects.equals(this.licenseRequestHeaders, r52.licenseRequestHeaders) == false) goto L25;
            if (this.multiSession != r52.multiSession) goto L25;
            if (this.forceDefaultLicenseUri != r52.forceDefaultLicenseUri) goto L25;
            if (this.playClearContentWithoutKey != r52.playClearContentWithoutKey) goto L25;
            if (this.forcedSessionTrackTypes.equals(r52.forcedSessionTrackTypes) == false) goto L25;
            if (Arrays.equals(this.keySetId, r52.keySetId) == false) goto L25;
            return true;
        }

        public byte[] getKeySetId() {
            byte[] r1 = this.keySetId;
            if (r1 != null) goto L5;
            return null;
        L5:
            return Arrays.copyOf(r1, r1.length);
        }

        public int hashCode() {
            int r0 = this.scheme.hashCode() * 31;
            Uri r1 = this.licenseUri;
            if (r1 == null) goto L5;
            int r12 = r1.hashCode();
        L7:
            return ((((((((((((r0 + r12) * 31) + this.licenseRequestHeaders.hashCode()) * 31) + (this.multiSession ? 1 : 0)) * 31) + (this.forceDefaultLicenseUri ? 1 : 0)) * 31) + (this.playClearContentWithoutKey ? 1 : 0)) * 31) + this.forcedSessionTrackTypes.hashCode()) * 31) + Arrays.hashCode(this.keySetId);
        L5:
            r12 = 0;
            goto L7
        }

        public Bundle toBundle() {
            Bundle r0 = new Bundle();
            r0.putString(FIELD_SCHEME, this.scheme.toString());
            Uri r1 = this.licenseUri;
            if (r1 == null) goto L6;
            r0.putParcelable(FIELD_LICENSE_URI, r1);
        L6:
            if (this.licenseRequestHeaders.isEmpty() == true) goto L8;
            r0.putBundle(FIELD_LICENSE_REQUEST_HEADERS, BundleCollectionUtil.stringMapToBundle(this.licenseRequestHeaders));
        L8:
            boolean r12 = this.multiSession;
            if (r12 == false) goto L11;
            r0.putBoolean(FIELD_MULTI_SESSION, r12);
        L11:
            boolean r13 = this.playClearContentWithoutKey;
            if (r13 == false) goto L14;
            r0.putBoolean(FIELD_PLAY_CLEAR_CONTENT_WITHOUT_KEY, r13);
        L14:
            boolean r14 = this.forceDefaultLicenseUri;
            if (r14 == false) goto L18;
            r0.putBoolean(FIELD_FORCE_DEFAULT_LICENSE_URI, r14);
        L18:
            if (this.forcedSessionTrackTypes.isEmpty() == true) goto L20;
            r0.putIntegerArrayList(FIELD_FORCED_SESSION_TRACK_TYPES, new ArrayList(this.forcedSessionTrackTypes));
        L20:
            byte[] r4 = this.keySetId;
            if (r4 == null) goto L23;
            r0.putByteArray(FIELD_KEY_SET_ID, r4);
        L23:
            return r0;
        }
    }

    public static final class LiveConfiguration {
        private static final String FIELD_MAX_OFFSET_MS = null;
        private static final String FIELD_MAX_PLAYBACK_SPEED = null;
        private static final String FIELD_MIN_OFFSET_MS = null;
        private static final String FIELD_MIN_PLAYBACK_SPEED = null;
        private static final String FIELD_TARGET_OFFSET_MS = null;
        public static final LiveConfiguration UNSET = null;
        public final long maxOffsetMs;
        public final float maxPlaybackSpeed;
        public final long minOffsetMs;
        public final float minPlaybackSpeed;
        public final long targetOffsetMs;

        public static final class Builder {
            private long maxOffsetMs;
            private float maxPlaybackSpeed;
            private long minOffsetMs;
            private float minPlaybackSpeed;
            private long targetOffsetMs;

            public Builder() {
                this.targetOffsetMs = -9223372036854775807L;
                this.minOffsetMs = -9223372036854775807L;
                this.maxOffsetMs = -9223372036854775807L;
                this.minPlaybackSpeed = -3.4028235E38f;
                this.maxPlaybackSpeed = -3.4028235E38f;
            }

            private Builder(LiveConfiguration r3) {
                this.targetOffsetMs = r3.targetOffsetMs;
                this.minOffsetMs = r3.minOffsetMs;
                this.maxOffsetMs = r3.maxOffsetMs;
                this.minPlaybackSpeed = r3.minPlaybackSpeed;
                this.maxPlaybackSpeed = r3.maxPlaybackSpeed;
            }

            public /* synthetic */ Builder(LiveConfiguration r1, AnonymousClass1 r2) {
                this(r1);
            }

            public static /* synthetic */ long access$2000(Builder r2) {
                return r2.targetOffsetMs;
            }

            public static /* synthetic */ long access$2100(Builder r2) {
                return r2.minOffsetMs;
            }

            public static /* synthetic */ long access$2200(Builder r2) {
                return r2.maxOffsetMs;
            }

            public static /* synthetic */ float access$2300(Builder r0) {
                return r0.minPlaybackSpeed;
            }

            public static /* synthetic */ float access$2400(Builder r0) {
                return r0.maxPlaybackSpeed;
            }

            public LiveConfiguration build() {
                return new LiveConfiguration(this, null);
            }

            public Builder setMaxOffsetMs(long r1) {
                this.maxOffsetMs = r1;
                return this;
            }

            public Builder setMaxPlaybackSpeed(float r1) {
                this.maxPlaybackSpeed = r1;
                return this;
            }

            public Builder setMinOffsetMs(long r1) {
                this.minOffsetMs = r1;
                return this;
            }

            public Builder setMinPlaybackSpeed(float r1) {
                this.minPlaybackSpeed = r1;
                return this;
            }

            public Builder setTargetOffsetMs(long r1) {
                this.targetOffsetMs = r1;
                return this;
            }
        }

        static {
            UNSET = new Builder().build();
            FIELD_TARGET_OFFSET_MS = Util.intToStringMaxRadix(0);
            FIELD_MIN_OFFSET_MS = Util.intToStringMaxRadix(1);
            FIELD_MAX_OFFSET_MS = Util.intToStringMaxRadix(2);
            FIELD_MIN_PLAYBACK_SPEED = Util.intToStringMaxRadix(3);
            FIELD_MAX_PLAYBACK_SPEED = Util.intToStringMaxRadix(4);
        }

        @Deprecated
        public LiveConfiguration(long r1, long r3, long r5, float r7, float r8) {
            this.targetOffsetMs = r1;
            this.minOffsetMs = r3;
            this.maxOffsetMs = r5;
            this.minPlaybackSpeed = r7;
            this.maxPlaybackSpeed = r8;
        }

        private LiveConfiguration(Builder r10) {
            this(Builder.access$2000(r10), Builder.access$2100(r10), Builder.access$2200(r10), Builder.access$2300(r10), Builder.access$2400(r10));
        }

        public /* synthetic */ LiveConfiguration(Builder r1, AnonymousClass1 r2) {
            this(r1);
        }

        public static LiveConfiguration fromBundle(Bundle r5) {
            Builder r0 = new Builder();
            String r1 = FIELD_TARGET_OFFSET_MS;
            LiveConfiguration r2 = UNSET;
            return r0.setTargetOffsetMs(r5.getLong(r1, r2.targetOffsetMs)).setMinOffsetMs(r5.getLong(FIELD_MIN_OFFSET_MS, r2.minOffsetMs)).setMaxOffsetMs(r5.getLong(FIELD_MAX_OFFSET_MS, r2.maxOffsetMs)).setMinPlaybackSpeed(r5.getFloat(FIELD_MIN_PLAYBACK_SPEED, r2.minPlaybackSpeed)).setMaxPlaybackSpeed(r5.getFloat(FIELD_MAX_PLAYBACK_SPEED, r2.maxPlaybackSpeed)).build();
        }

        public Builder buildUpon() {
            return new Builder(this, null);
        }

        public boolean equals(Object r8) {
            if (this != r8) goto L6;
            return true;
        L6:
            if ((r8 instanceof LiveConfiguration) == true) goto L8;
            return false;
        L8:
            LiveConfiguration r82 = (LiveConfiguration) r8;
            if (this.targetOffsetMs == r82.targetOffsetMs) goto L11;
        L19:
            return false;
        L11:
            if (this.minOffsetMs != r82.minOffsetMs) goto L19;
            if (this.maxOffsetMs != r82.maxOffsetMs) goto L19;
            if (this.minPlaybackSpeed != r82.minPlaybackSpeed) goto L19;
            if (this.maxPlaybackSpeed != r82.maxPlaybackSpeed) goto L19;
            return true;
        }

        public int hashCode() {
            long r0 = this.targetOffsetMs;
            long r3 = this.minOffsetMs;
            int r02 = ((((int) (r0 ^ (r0 >>> 32))) * 31) + ((int) (r3 ^ (r3 >>> 32)))) * 31;
            long r32 = this.maxOffsetMs;
            int r03 = (r02 + ((int) ((r32 >>> 32) ^ r32))) * 31;
            float r1 = this.minPlaybackSpeed;
            int r4 = 0;
            if (r1 == 0.0f) goto L5;
            int r12 = Float.floatToIntBits(r1);
        L6:
            int r04 = (r03 + r12) * 31;
            float r7 = this.maxPlaybackSpeed;
            if (r7 == 0.0f) goto L10;
            r4 = Float.floatToIntBits(r7);
        L10:
            return r04 + r4;
        L5:
            r12 = 0;
            goto L6
        }

        public Bundle toBundle() {
            Bundle r0 = new Bundle();
            long r1 = this.targetOffsetMs;
            LiveConfiguration r3 = UNSET;
            if (r1 == r3.targetOffsetMs) goto L5;
            r0.putLong(FIELD_TARGET_OFFSET_MS, r1);
        L5:
            long r12 = this.minOffsetMs;
            if (r12 == r3.minOffsetMs) goto L8;
            r0.putLong(FIELD_MIN_OFFSET_MS, r12);
        L8:
            long r13 = this.maxOffsetMs;
            if (r13 == r3.maxOffsetMs) goto L11;
            r0.putLong(FIELD_MAX_OFFSET_MS, r13);
        L11:
            float r14 = this.minPlaybackSpeed;
            if (r14 == r3.minPlaybackSpeed) goto L14;
            r0.putFloat(FIELD_MIN_PLAYBACK_SPEED, r14);
        L14:
            float r6 = this.maxPlaybackSpeed;
            if (r6 == r3.maxPlaybackSpeed) goto L17;
            r0.putFloat(FIELD_MAX_PLAYBACK_SPEED, r6);
        L17:
            return r0;
        }
    }

    public static final class LocalConfiguration {
        private static final String FIELD_ADS_CONFIGURATION = null;
        private static final String FIELD_CUSTOM_CACHE_KEY = null;
        private static final String FIELD_DRM_CONFIGURATION = null;
        private static final String FIELD_IMAGE_DURATION_MS = null;
        private static final String FIELD_MIME_TYPE = null;
        private static final String FIELD_STREAM_KEYS = null;
        private static final String FIELD_SUBTITLE_CONFIGURATION = null;
        private static final String FIELD_URI = null;
        public final AdsConfiguration adsConfiguration;
        public final String customCacheKey;
        public final DrmConfiguration drmConfiguration;
        public final long imageDurationMs;
        public final String mimeType;
        public final List<StreamKey> streamKeys;
        public final ImmutableList<SubtitleConfiguration> subtitleConfigurations;

        @Deprecated
        public final List<Subtitle> subtitles;
        public final Object tag;
        public final Uri uri;

        static {
            FIELD_URI = Util.intToStringMaxRadix(0);
            FIELD_MIME_TYPE = Util.intToStringMaxRadix(1);
            FIELD_DRM_CONFIGURATION = Util.intToStringMaxRadix(2);
            FIELD_ADS_CONFIGURATION = Util.intToStringMaxRadix(3);
            FIELD_STREAM_KEYS = Util.intToStringMaxRadix(4);
            FIELD_CUSTOM_CACHE_KEY = Util.intToStringMaxRadix(5);
            FIELD_SUBTITLE_CONFIGURATION = Util.intToStringMaxRadix(6);
            FIELD_IMAGE_DURATION_MS = Util.intToStringMaxRadix(7);
        }

        private LocalConfiguration(Uri r1, String r2, DrmConfiguration r3, AdsConfiguration r4, List<StreamKey> r5, String r6, ImmutableList<SubtitleConfiguration> r7, Object r8, long r9) {
            this.uri = r1;
            this.mimeType = MimeTypes.normalizeMimeType(r2);
            this.drmConfiguration = r3;
            this.adsConfiguration = r4;
            this.streamKeys = r5;
            this.customCacheKey = r6;
            this.subtitleConfigurations = r7;
            ImmutableList.Builder r12 = ImmutableList.builder();
            int r22 = 0;
        L4:
            if (r22 >= r7.size()) goto L6;
            r12.add(SubtitleConfiguration.Builder.access$1800(r7.get(r22).buildUpon()));
            r22 = r22 + 1;
            goto L4
        L6:
            this.subtitles = r12.build();
            this.tag = r8;
            this.imageDurationMs = r9;
        }

        public /* synthetic */ LocalConfiguration(Uri r1, String r2, DrmConfiguration r3, AdsConfiguration r4, List r5, String r6, ImmutableList r7, Object r8, long r9, AnonymousClass1 r11) {
            this(r1, r2, r3, r4, r5, r6, r7, r8, r9);
        }

        public static LocalConfiguration fromBundle(Bundle r13) {
            Bundle r0 = r13.getBundle(FIELD_DRM_CONFIGURATION);
            AdsConfiguration r1 = null;
            if (r0 != null) goto L5;
            DrmConfiguration r5 = null;
        L6:
            Bundle r02 = r13.getBundle(FIELD_ADS_CONFIGURATION);
            if (r02 != null) goto L9;
        L8:
            AdsConfiguration r6 = r1;
            ArrayList r03 = r13.getParcelableArrayList(FIELD_STREAM_KEYS);
            if (r03 != null) goto L14;
            ImmutableList r04 = ImmutableList.of();
        L13:
            ImmutableList r7 = r04;
            ArrayList r05 = r13.getParcelableArrayList(FIELD_SUBTITLE_CONFIGURATION);
            if (r05 != null) goto L19;
            ImmutableList r06 = ImmutableList.of();
        L21:
            return new LocalConfiguration((Uri) Assertions.checkNotNull((Uri) r13.getParcelable(FIELD_URI)), r13.getString(FIELD_MIME_TYPE), r5, r6, r7, r13.getString(FIELD_CUSTOM_CACHE_KEY), r06, null, r13.getLong(FIELD_IMAGE_DURATION_MS, -9223372036854775807L));
        L19:
            r06 = BundleCollectionUtil.fromBundleList(new MediaItem$LocalConfiguration$$ExternalSyntheticLambda3(), r05);
            goto L21
        L14:
            r04 = BundleCollectionUtil.fromBundleList(new MediaItem$LocalConfiguration$$ExternalSyntheticLambda2(), r03);
            goto L13
        L9:
            r1 = AdsConfiguration.fromBundle(r02);
            goto L8
        L5:
            r5 = DrmConfiguration.fromBundle(r0);
            goto L6
        }

        public boolean equals(Object r6) {
            if (this != r6) goto L6;
            return true;
        L6:
            if ((r6 instanceof LocalConfiguration) == true) goto L8;
            return false;
        L8:
            LocalConfiguration r62 = (LocalConfiguration) r6;
            if (this.uri.equals(r62.uri) == true) goto L11;
        L27:
            return false;
        L11:
            if (Objects.equals(this.mimeType, r62.mimeType) == false) goto L27;
            if (Objects.equals(this.drmConfiguration, r62.drmConfiguration) == false) goto L27;
            if (Objects.equals(this.adsConfiguration, r62.adsConfiguration) == false) goto L27;
            if (this.streamKeys.equals(r62.streamKeys) == false) goto L27;
            if (Objects.equals(this.customCacheKey, r62.customCacheKey) == false) goto L27;
            if (this.subtitleConfigurations.equals(r62.subtitleConfigurations) == false) goto L27;
            if (Objects.equals(this.tag, r62.tag) == false) goto L27;
            if (this.imageDurationMs != r62.imageDurationMs) goto L27;
            return true;
        }

        public int hashCode() {
            int r0 = this.uri.hashCode() * 31;
            String r1 = this.mimeType;
            int r2 = 0;
            if (r1 != null) goto L5;
            int r12 = 0;
        L6:
            int r02 = (r0 + r12) * 31;
            DrmConfiguration r13 = this.drmConfiguration;
            if (r13 != null) goto L9;
            int r14 = 0;
        L10:
            int r03 = (r02 + r14) * 31;
            AdsConfiguration r15 = this.adsConfiguration;
            if (r15 != null) goto L13;
            int r16 = 0;
        L14:
            int r04 = (((r03 + r16) * 31) + this.streamKeys.hashCode()) * 31;
            String r17 = this.customCacheKey;
            if (r17 != null) goto L17;
            int r18 = 0;
        L18:
            int r05 = (((r04 + r18) * 31) + this.subtitleConfigurations.hashCode()) * 31;
            Object r19 = this.tag;
            if (r19 == null) goto L23;
            r2 = r19.hashCode();
        L23:
            return (int) (((r05 + r2) * 31) + this.imageDurationMs);
        L17:
            r18 = r17.hashCode();
            goto L18
        L13:
            r16 = r15.hashCode();
            goto L14
        L9:
            r14 = r13.hashCode();
            goto L10
        L5:
            r12 = r1.hashCode();
            goto L6
        }

        public Bundle toBundle() {
            Bundle r0 = new Bundle();
            r0.putParcelable(FIELD_URI, this.uri);
            String r1 = this.mimeType;
            if (r1 == null) goto L5;
            r0.putString(FIELD_MIME_TYPE, r1);
        L5:
            DrmConfiguration r12 = this.drmConfiguration;
            if (r12 == null) goto L8;
            r0.putBundle(FIELD_DRM_CONFIGURATION, r12.toBundle());
        L8:
            AdsConfiguration r13 = this.adsConfiguration;
            if (r13 == null) goto L12;
            r0.putBundle(FIELD_ADS_CONFIGURATION, r13.toBundle());
        L12:
            if (this.streamKeys.isEmpty() == true) goto L14;
            r0.putParcelableArrayList(FIELD_STREAM_KEYS, BundleCollectionUtil.toBundleArrayList(this.streamKeys, new MediaItem$LocalConfiguration$$ExternalSyntheticLambda0()));
        L14:
            String r14 = this.customCacheKey;
            if (r14 == null) goto L18;
            r0.putString(FIELD_CUSTOM_CACHE_KEY, r14);
        L18:
            if (this.subtitleConfigurations.isEmpty() == true) goto L20;
            r0.putParcelableArrayList(FIELD_SUBTITLE_CONFIGURATION, BundleCollectionUtil.toBundleArrayList(this.subtitleConfigurations, new MediaItem$LocalConfiguration$$ExternalSyntheticLambda1()));
        L20:
            long r15 = this.imageDurationMs;
            if (r15 == (-9223372036854775807L)) goto L23;
            r0.putLong(FIELD_IMAGE_DURATION_MS, r15);
        L23:
            return r0;
        }
    }

    public static final class RequestMetadata {
        public static final RequestMetadata EMPTY = null;
        private static final String FIELD_EXTRAS = null;
        private static final String FIELD_MEDIA_URI = null;
        private static final String FIELD_SEARCH_QUERY = null;
        public final Bundle extras;
        public final Uri mediaUri;
        public final String searchQuery;

        public static final class Builder {
            private Bundle extras;
            private Uri mediaUri;
            private String searchQuery;

            public Builder() {
            }

            public static /* synthetic */ Uri access$4700(Builder r0) {
                return r0.mediaUri;
            }

            public static /* synthetic */ String access$4800(Builder r0) {
                return r0.searchQuery;
            }

            public static /* synthetic */ Bundle access$4900(Builder r0) {
                return r0.extras;
            }

            public RequestMetadata build() {
                return new RequestMetadata(this, null);
            }

            public Builder setExtras(Bundle r1) {
                this.extras = r1;
                return this;
            }

            public Builder setMediaUri(Uri r1) {
                this.mediaUri = r1;
                return this;
            }

            public Builder setSearchQuery(String r1) {
                this.searchQuery = r1;
                return this;
            }
        }

        static {
            EMPTY = new Builder().build();
            FIELD_MEDIA_URI = Util.intToStringMaxRadix(0);
            FIELD_SEARCH_QUERY = Util.intToStringMaxRadix(1);
            FIELD_EXTRAS = Util.intToStringMaxRadix(2);
        }

        private RequestMetadata(Builder r2) {
            this.mediaUri = Builder.access$4700(r2);
            this.searchQuery = Builder.access$4800(r2);
            this.extras = Builder.access$4900(r2);
        }

        public /* synthetic */ RequestMetadata(Builder r1, AnonymousClass1 r2) {
            this(r1);
        }

        public static RequestMetadata fromBundle(Bundle r2) {
            return new Builder().setMediaUri((Uri) r2.getParcelable(FIELD_MEDIA_URI)).setSearchQuery(r2.getString(FIELD_SEARCH_QUERY)).setExtras(r2.getBundle(FIELD_EXTRAS)).build();
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof RequestMetadata) == true) goto L8;
            return false;
        L8:
            RequestMetadata r52 = (RequestMetadata) r5;
            if (Objects.equals(this.mediaUri, r52.mediaUri) == true) goto L11;
        L22:
            return false;
        L11:
            if (Objects.equals(this.searchQuery, r52.searchQuery) == false) goto L22;
            if (this.extras != null) goto L15;
            boolean r4 = true;
        L17:
            if (r52.extras != null) goto L19;
            boolean r53 = true;
        L20:
            if (r4 != r53) goto L22;
            return true;
        L19:
            r53 = false;
            goto L20
        L15:
            r4 = false;
            goto L17
        }

        public int hashCode() {
            Uri r0 = this.mediaUri;
            int r1 = 0;
            if (r0 != null) goto L5;
            int r02 = 0;
        L6:
            int r03 = r02 * 31;
            String r2 = this.searchQuery;
            if (r2 != null) goto L9;
            int r22 = 0;
        L10:
            int r04 = (r03 + r22) * 31;
            if (this.extras == null) goto L15;
            r1 = 1;
        L15:
            return r04 + r1;
        L9:
            r22 = r2.hashCode();
            goto L10
        L5:
            r02 = r0.hashCode();
            goto L6
        }

        public Bundle toBundle() {
            Bundle r0 = new Bundle();
            Uri r1 = this.mediaUri;
            if (r1 == null) goto L5;
            r0.putParcelable(FIELD_MEDIA_URI, r1);
        L5:
            String r12 = this.searchQuery;
            if (r12 == null) goto L8;
            r0.putString(FIELD_SEARCH_QUERY, r12);
        L8:
            Bundle r3 = this.extras;
            if (r3 == null) goto L11;
            r0.putBundle(FIELD_EXTRAS, r3);
        L11:
            return r0;
        }
    }

    @Deprecated
    public static final class Subtitle extends SubtitleConfiguration {
        private Subtitle(SubtitleConfiguration.Builder r2) {
            super(r2, null);
        }

        public /* synthetic */ Subtitle(SubtitleConfiguration.Builder r1, AnonymousClass1 r2) {
            this(r1);
        }
    }

    public static class SubtitleConfiguration {
        private static final String FIELD_ID = null;
        private static final String FIELD_LABEL = null;
        private static final String FIELD_LANGUAGE = null;
        private static final String FIELD_MIME_TYPE = null;
        private static final String FIELD_ROLE_FLAGS = null;
        private static final String FIELD_SELECTION_FLAGS = null;
        private static final String FIELD_URI = null;
        public final String id;
        public final String label;
        public final String language;
        public final String mimeType;
        public final int roleFlags;
        public final int selectionFlags;
        public final Uri uri;

        public static final class Builder {
            private String id;
            private String label;
            private String language;
            private String mimeType;
            private int roleFlags;
            private int selectionFlags;
            private Uri uri;

            public Builder(Uri r1) {
                this.uri = r1;
            }

            private Builder(SubtitleConfiguration r2) {
                this.uri = r2.uri;
                this.mimeType = r2.mimeType;
                this.language = r2.language;
                this.selectionFlags = r2.selectionFlags;
                this.roleFlags = r2.roleFlags;
                this.label = r2.label;
                this.id = r2.id;
            }

            public /* synthetic */ Builder(SubtitleConfiguration r1, AnonymousClass1 r2) {
                this(r1);
            }

            public static /* synthetic */ Subtitle access$1800(Builder r0) {
                return r0.buildSubtitle();
            }

            public static /* synthetic */ Uri access$2800(Builder r0) {
                return r0.uri;
            }

            public static /* synthetic */ String access$2900(Builder r0) {
                return r0.mimeType;
            }

            public static /* synthetic */ String access$3000(Builder r0) {
                return r0.language;
            }

            public static /* synthetic */ int access$3100(Builder r0) {
                return r0.selectionFlags;
            }

            public static /* synthetic */ int access$3200(Builder r0) {
                return r0.roleFlags;
            }

            public static /* synthetic */ String access$3300(Builder r0) {
                return r0.label;
            }

            public static /* synthetic */ String access$3400(Builder r0) {
                return r0.id;
            }

            private Subtitle buildSubtitle() {
                return new Subtitle(this, null);
            }

            public SubtitleConfiguration build() {
                return new SubtitleConfiguration(this, null);
            }

            public Builder setId(String r1) {
                this.id = r1;
                return this;
            }

            public Builder setLabel(String r1) {
                this.label = r1;
                return this;
            }

            public Builder setLanguage(String r1) {
                this.language = r1;
                return this;
            }

            public Builder setMimeType(String r1) {
                this.mimeType = MimeTypes.normalizeMimeType(r1);
                return this;
            }

            public Builder setRoleFlags(int r1) {
                this.roleFlags = r1;
                return this;
            }

            public Builder setSelectionFlags(int r1) {
                this.selectionFlags = r1;
                return this;
            }
        }

        static {
            FIELD_URI = Util.intToStringMaxRadix(0);
            FIELD_MIME_TYPE = Util.intToStringMaxRadix(1);
            FIELD_LANGUAGE = Util.intToStringMaxRadix(2);
            FIELD_SELECTION_FLAGS = Util.intToStringMaxRadix(3);
            FIELD_ROLE_FLAGS = Util.intToStringMaxRadix(4);
            FIELD_LABEL = Util.intToStringMaxRadix(5);
            FIELD_ID = Util.intToStringMaxRadix(6);
        }

        private SubtitleConfiguration(Builder r2) {
            this.uri = Builder.access$2800(r2);
            this.mimeType = Builder.access$2900(r2);
            this.language = Builder.access$3000(r2);
            this.selectionFlags = Builder.access$3100(r2);
            this.roleFlags = Builder.access$3200(r2);
            this.label = Builder.access$3300(r2);
            this.id = Builder.access$3400(r2);
        }

        public /* synthetic */ SubtitleConfiguration(Builder r1, AnonymousClass1 r2) {
            this(r1);
        }

        public static SubtitleConfiguration fromBundle(Bundle r7) {
            Uri r0 = (Uri) Assertions.checkNotNull((Uri) r7.getParcelable(FIELD_URI));
            String r1 = r7.getString(FIELD_MIME_TYPE);
            String r2 = r7.getString(FIELD_LANGUAGE);
            int r3 = r7.getInt(FIELD_SELECTION_FLAGS, 0);
            int r4 = r7.getInt(FIELD_ROLE_FLAGS, 0);
            String r5 = r7.getString(FIELD_LABEL);
            String r72 = r7.getString(FIELD_ID);
            return new Builder(r0).setMimeType(r1).setLanguage(r2).setSelectionFlags(r3).setRoleFlags(r4).setLabel(r5).setId(r72).build();
        }

        public Builder buildUpon() {
            return new Builder(this, null);
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof SubtitleConfiguration) == true) goto L8;
            return false;
        L8:
            SubtitleConfiguration r52 = (SubtitleConfiguration) r5;
            if (this.uri.equals(r52.uri) == true) goto L11;
        L23:
            return false;
        L11:
            if (Objects.equals(this.mimeType, r52.mimeType) == false) goto L23;
            if (Objects.equals(this.language, r52.language) == false) goto L23;
            if (this.selectionFlags != r52.selectionFlags) goto L23;
            if (this.roleFlags != r52.roleFlags) goto L23;
            if (Objects.equals(this.label, r52.label) == false) goto L23;
            if (Objects.equals(this.id, r52.id) == false) goto L23;
            return true;
        }

        public int hashCode() {
            int r0 = this.uri.hashCode() * 31;
            String r1 = this.mimeType;
            int r2 = 0;
            if (r1 != null) goto L5;
            int r12 = 0;
        L6:
            int r02 = (r0 + r12) * 31;
            String r13 = this.language;
            if (r13 != null) goto L9;
            int r14 = 0;
        L10:
            int r03 = (((((r02 + r14) * 31) + this.selectionFlags) * 31) + this.roleFlags) * 31;
            String r15 = this.label;
            if (r15 != null) goto L13;
            int r16 = 0;
        L14:
            int r04 = (r03 + r16) * 31;
            String r3 = this.id;
            if (r3 == null) goto L19;
            r2 = r3.hashCode();
        L19:
            return r04 + r2;
        L13:
            r16 = r15.hashCode();
            goto L14
        L9:
            r14 = r13.hashCode();
            goto L10
        L5:
            r12 = r1.hashCode();
            goto L6
        }

        public Bundle toBundle() {
            Bundle r0 = new Bundle();
            r0.putParcelable(FIELD_URI, this.uri);
            String r1 = this.mimeType;
            if (r1 == null) goto L5;
            r0.putString(FIELD_MIME_TYPE, r1);
        L5:
            String r12 = this.language;
            if (r12 == null) goto L8;
            r0.putString(FIELD_LANGUAGE, r12);
        L8:
            int r13 = this.selectionFlags;
            if (r13 == 0) goto L11;
            r0.putInt(FIELD_SELECTION_FLAGS, r13);
        L11:
            int r14 = this.roleFlags;
            if (r14 == 0) goto L14;
            r0.putInt(FIELD_ROLE_FLAGS, r14);
        L14:
            String r15 = this.label;
            if (r15 == null) goto L17;
            r0.putString(FIELD_LABEL, r15);
        L17:
            String r3 = this.id;
            if (r3 == null) goto L20;
            r0.putString(FIELD_ID, r3);
        L20:
            return r0;
        }
    }

    static {
        EMPTY = new Builder().build();
        FIELD_MEDIA_ID = Util.intToStringMaxRadix(0);
        FIELD_LIVE_CONFIGURATION = Util.intToStringMaxRadix(1);
        FIELD_MEDIA_METADATA = Util.intToStringMaxRadix(2);
        FIELD_CLIPPING_PROPERTIES = Util.intToStringMaxRadix(3);
        FIELD_REQUEST_METADATA = Util.intToStringMaxRadix(4);
        FIELD_LOCAL_CONFIGURATION = Util.intToStringMaxRadix(5);
    }

    private MediaItem(String r1, ClippingProperties r2, LocalConfiguration r3, LiveConfiguration r4, MediaMetadata r5, RequestMetadata r6) {
        this.mediaId = r1;
        this.localConfiguration = r3;
        this.playbackProperties = r3;
        this.liveConfiguration = r4;
        this.mediaMetadata = r5;
        this.clippingConfiguration = r2;
        this.clippingProperties = r2;
        this.requestMetadata = r6;
    }

    public /* synthetic */ MediaItem(String r1, ClippingProperties r2, LocalConfiguration r3, LiveConfiguration r4, MediaMetadata r5, RequestMetadata r6, AnonymousClass1 r7) {
        this(r1, r2, r3, r4, r5, r6);
    }

    public static MediaItem fromBundle(Bundle r8) {
        String r2 = (String) Assertions.checkNotNull(r8.getString(FIELD_MEDIA_ID, _UrlKt.FRAGMENT_ENCODE_SET));
        Bundle r0 = r8.getBundle(FIELD_LIVE_CONFIGURATION);
        if (r0 != null) goto L6;
        LiveConfiguration r02 = LiveConfiguration.UNSET;
    L5:
        LiveConfiguration r5 = r02;
        Bundle r03 = r8.getBundle(FIELD_MEDIA_METADATA);
        if (r03 != null) goto L11;
        MediaMetadata r04 = MediaMetadata.EMPTY;
    L10:
        MediaMetadata r6 = r04;
        Bundle r05 = r8.getBundle(FIELD_CLIPPING_PROPERTIES);
        if (r05 != null) goto L16;
        ClippingProperties r06 = ClippingProperties.UNSET;
    L15:
        ClippingProperties r3 = r06;
        Bundle r07 = r8.getBundle(FIELD_REQUEST_METADATA);
        if (r07 != null) goto L21;
        RequestMetadata r08 = RequestMetadata.EMPTY;
    L20:
        RequestMetadata r7 = r08;
        Bundle r82 = r8.getBundle(FIELD_LOCAL_CONFIGURATION);
        if (r82 != null) goto L26;
        LocalConfiguration r83 = null;
    L28:
        return new MediaItem(r2, r3, r83, r5, r6, r7);
    L26:
        r83 = LocalConfiguration.fromBundle(r82);
        goto L28
    L21:
        r08 = RequestMetadata.fromBundle(r07);
        goto L20
    L16:
        r06 = ClippingConfiguration.fromBundle(r05);
        goto L15
    L11:
        r04 = MediaMetadata.fromBundle(r03);
        goto L10
    L6:
        r02 = LiveConfiguration.fromBundle(r0);
        goto L5
    }

    public static MediaItem fromUri(Uri r1) {
        return new Builder().setUri(r1).build();
    }

    public static MediaItem fromUri(String r1) {
        return new Builder().setUri(r1).build();
    }

    private Bundle toBundle(boolean r4) {
        Bundle r0 = new Bundle();
        if (this.mediaId.equals(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L6;
        r0.putString(FIELD_MEDIA_ID, this.mediaId);
    L6:
        if (this.liveConfiguration.equals(LiveConfiguration.UNSET) == true) goto L9;
        r0.putBundle(FIELD_LIVE_CONFIGURATION, this.liveConfiguration.toBundle());
    L9:
        if (this.mediaMetadata.equals(MediaMetadata.EMPTY) == true) goto L12;
        r0.putBundle(FIELD_MEDIA_METADATA, this.mediaMetadata.toBundle());
    L12:
        if (this.clippingConfiguration.equals(ClippingConfiguration.UNSET) == true) goto L15;
        r0.putBundle(FIELD_CLIPPING_PROPERTIES, this.clippingConfiguration.toBundle());
    L15:
        if (this.requestMetadata.equals(RequestMetadata.EMPTY) == true) goto L17;
        r0.putBundle(FIELD_REQUEST_METADATA, this.requestMetadata.toBundle());
    L17:
        if (r4 == false) goto L21;
        LocalConfiguration r3 = this.localConfiguration;
        if (r3 == null) goto L21;
        r0.putBundle(FIELD_LOCAL_CONFIGURATION, r3.toBundle());
    L21:
        return r0;
    }

    public Builder buildUpon() {
        return new Builder(this, null);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof MediaItem) == true) goto L8;
        return false;
    L8:
        MediaItem r52 = (MediaItem) r5;
        if (Objects.equals(this.mediaId, r52.mediaId) == true) goto L11;
    L21:
        return false;
    L11:
        if (this.clippingConfiguration.equals(r52.clippingConfiguration) == false) goto L21;
        if (Objects.equals(this.localConfiguration, r52.localConfiguration) == false) goto L21;
        if (Objects.equals(this.liveConfiguration, r52.liveConfiguration) == false) goto L21;
        if (Objects.equals(this.mediaMetadata, r52.mediaMetadata) == false) goto L21;
        if (Objects.equals(this.requestMetadata, r52.requestMetadata) == false) goto L21;
        return true;
    }

    public int hashCode() {
        int r0 = this.mediaId.hashCode() * 31;
        LocalConfiguration r1 = this.localConfiguration;
        if (r1 == null) goto L5;
        int r12 = r1.hashCode();
    L7:
        return ((((((((r0 + r12) * 31) + this.liveConfiguration.hashCode()) * 31) + this.clippingConfiguration.hashCode()) * 31) + this.mediaMetadata.hashCode()) * 31) + this.requestMetadata.hashCode();
    L5:
        r12 = 0;
        goto L7
    }

    public Bundle toBundle() {
        return toBundle(false);
    }

    public Bundle toBundleIncludeLocalConfiguration() {
        return toBundle(true);
    }
}
