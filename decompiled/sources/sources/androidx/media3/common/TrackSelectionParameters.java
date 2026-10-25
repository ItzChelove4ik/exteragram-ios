package androidx.media3.common;

import android.os.Bundle;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.Util;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.primitives.Ints;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* loaded from: classes3.dex */
public class TrackSelectionParameters {
    public static final TrackSelectionParameters DEFAULT = null;

    @Deprecated
    public static final TrackSelectionParameters DEFAULT_WITHOUT_CONTEXT = null;
    private static final String FIELD_AUDIO_OFFLOAD_MODE_PREFERENCE = null;
    private static final String FIELD_AUDIO_OFFLOAD_PREFERENCES = null;
    private static final String FIELD_DISABLED_TRACK_TYPE = null;
    private static final String FIELD_FORCE_HIGHEST_SUPPORTED_BITRATE = null;
    private static final String FIELD_FORCE_LOWEST_BITRATE = null;
    private static final String FIELD_IGNORED_TEXT_SELECTION_FLAGS = null;
    private static final String FIELD_IS_GAPLESS_SUPPORT_REQUIRED = null;
    private static final String FIELD_IS_PREFER_IMAGE_OVER_VIDEO_ENABLED = null;
    private static final String FIELD_IS_SPEED_CHANGE_SUPPORT_REQUIRED = null;
    private static final String FIELD_IS_VIEWPORT_SIZE_LIMITED_BY_PHYSICAL_DISPLAY_SIZE = null;
    private static final String FIELD_MAX_AUDIO_BITRATE = null;
    private static final String FIELD_MAX_AUDIO_CHANNEL_COUNT = null;
    private static final String FIELD_MAX_VIDEO_BITRATE = null;
    private static final String FIELD_MAX_VIDEO_FRAMERATE = null;
    private static final String FIELD_MAX_VIDEO_HEIGHT = null;
    private static final String FIELD_MAX_VIDEO_WIDTH = null;
    private static final String FIELD_MIN_VIDEO_BITRATE = null;
    private static final String FIELD_MIN_VIDEO_FRAMERATE = null;
    private static final String FIELD_MIN_VIDEO_HEIGHT = null;
    private static final String FIELD_MIN_VIDEO_WIDTH = null;
    private static final String FIELD_PREFERRED_AUDIO_LANGUAGES = null;
    private static final String FIELD_PREFERRED_AUDIO_MIME_TYPES = null;
    private static final String FIELD_PREFERRED_AUDIO_ROLE_FLAGS = null;
    private static final String FIELD_PREFERRED_TEXT_LANGUAGES = null;
    private static final String FIELD_PREFERRED_TEXT_ROLE_FLAGS = null;
    private static final String FIELD_PREFERRED_VIDEO_LANGUAGES = null;
    private static final String FIELD_PREFERRED_VIDEO_MIMETYPES = null;
    private static final String FIELD_PREFERRED_VIDEO_ROLE_FLAGS = null;
    private static final String FIELD_SELECTION_OVERRIDES = null;
    private static final String FIELD_SELECT_UNDETERMINED_TEXT_LANGUAGE = null;
    private static final String FIELD_USE_PREFERRED_TEXT_LANGUAGES_AND_ROLE_FLAGS_FROM_CAPTIONING_MANAGER = null;
    private static final String FIELD_VIEWPORT_HEIGHT = null;
    private static final String FIELD_VIEWPORT_ORIENTATION_MAY_CHANGE = null;
    private static final String FIELD_VIEWPORT_WIDTH = null;
    public final AudioOffloadPreferences audioOffloadPreferences;
    public final ImmutableSet<Integer> disabledTrackTypes;
    public final boolean forceHighestSupportedBitrate;
    public final boolean forceLowestBitrate;
    public final int ignoredTextSelectionFlags;
    public final boolean isPrioritizeImageOverVideoEnabled;
    public final boolean isViewportSizeLimitedByPhysicalDisplaySize;
    public final int maxAudioBitrate;
    public final int maxAudioChannelCount;
    public final int maxVideoBitrate;
    public final int maxVideoFrameRate;
    public final int maxVideoHeight;
    public final int maxVideoWidth;
    public final int minVideoBitrate;
    public final int minVideoFrameRate;
    public final int minVideoHeight;
    public final int minVideoWidth;
    public final ImmutableMap<TrackGroup, TrackSelectionOverride> overrides;
    public final ImmutableList<String> preferredAudioLanguages;
    public final ImmutableList<String> preferredAudioMimeTypes;
    public final int preferredAudioRoleFlags;
    public final ImmutableList<String> preferredTextLanguages;
    public final int preferredTextRoleFlags;
    public final ImmutableList<String> preferredVideoLanguages;
    public final ImmutableList<String> preferredVideoMimeTypes;
    public final int preferredVideoRoleFlags;
    public final boolean selectUndeterminedTextLanguage;
    public final boolean usePreferredTextLanguagesAndRoleFlagsFromCaptioningManager;
    public final int viewportHeight;
    public final boolean viewportOrientationMayChange;
    public final int viewportWidth;

    /* renamed from: androidx.media3.common.TrackSelectionParameters$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class AudioOffloadPreferences {
        public static final AudioOffloadPreferences DEFAULT = null;
        private static final String FIELD_AUDIO_OFFLOAD_MODE_PREFERENCE = null;
        private static final String FIELD_IS_GAPLESS_SUPPORT_REQUIRED = null;
        private static final String FIELD_IS_SPEED_CHANGE_SUPPORT_REQUIRED = null;
        public final int audioOffloadMode;
        public final boolean isGaplessSupportRequired;
        public final boolean isSpeedChangeSupportRequired;

        public static final class Builder {
            private int audioOffloadMode;
            private boolean isGaplessSupportRequired;
            private boolean isSpeedChangeSupportRequired;

            public Builder() {
                this.audioOffloadMode = 0;
                this.isGaplessSupportRequired = false;
                this.isSpeedChangeSupportRequired = false;
            }

            public static /* synthetic */ int access$3500(Builder r0) {
                return r0.audioOffloadMode;
            }

            public static /* synthetic */ boolean access$3600(Builder r0) {
                return r0.isGaplessSupportRequired;
            }

            public static /* synthetic */ boolean access$3700(Builder r0) {
                return r0.isSpeedChangeSupportRequired;
            }

            public AudioOffloadPreferences build() {
                return new AudioOffloadPreferences(this, null);
            }

            public Builder setAudioOffloadMode(int r1) {
                this.audioOffloadMode = r1;
                return this;
            }

            public Builder setIsGaplessSupportRequired(boolean r1) {
                this.isGaplessSupportRequired = r1;
                return this;
            }

            public Builder setIsSpeedChangeSupportRequired(boolean r1) {
                this.isSpeedChangeSupportRequired = r1;
                return this;
            }
        }

        static {
            DEFAULT = new Builder().build();
            FIELD_AUDIO_OFFLOAD_MODE_PREFERENCE = Util.intToStringMaxRadix(1);
            FIELD_IS_GAPLESS_SUPPORT_REQUIRED = Util.intToStringMaxRadix(2);
            FIELD_IS_SPEED_CHANGE_SUPPORT_REQUIRED = Util.intToStringMaxRadix(3);
        }

        private AudioOffloadPreferences(Builder r2) {
            this.audioOffloadMode = Builder.access$3500(r2);
            this.isGaplessSupportRequired = Builder.access$3600(r2);
            this.isSpeedChangeSupportRequired = Builder.access$3700(r2);
        }

        public /* synthetic */ AudioOffloadPreferences(Builder r1, AnonymousClass1 r2) {
            this(r1);
        }

        public static AudioOffloadPreferences fromBundle(Bundle r4) {
            Builder r0 = new Builder();
            String r1 = FIELD_AUDIO_OFFLOAD_MODE_PREFERENCE;
            AudioOffloadPreferences r2 = DEFAULT;
            return r0.setAudioOffloadMode(r4.getInt(r1, r2.audioOffloadMode)).setIsGaplessSupportRequired(r4.getBoolean(FIELD_IS_GAPLESS_SUPPORT_REQUIRED, r2.isGaplessSupportRequired)).setIsSpeedChangeSupportRequired(r4.getBoolean(FIELD_IS_SPEED_CHANGE_SUPPORT_REQUIRED, r2.isSpeedChangeSupportRequired)).build();
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if (r5 != null) goto L8;
        L17:
            return false;
        L8:
            if (AudioOffloadPreferences.class != r5.getClass()) goto L17;
            AudioOffloadPreferences r52 = (AudioOffloadPreferences) r5;
            if (this.audioOffloadMode != r52.audioOffloadMode) goto L17;
            if (this.isGaplessSupportRequired != r52.isGaplessSupportRequired) goto L17;
            if (this.isSpeedChangeSupportRequired != r52.isSpeedChangeSupportRequired) goto L17;
            return true;
        }

        public int hashCode() {
            return ((((this.audioOffloadMode + 31) * 31) + (this.isGaplessSupportRequired ? 1 : 0)) * 31) + (this.isSpeedChangeSupportRequired ? 1 : 0);
        }

        public Bundle toBundle() {
            Bundle r0 = new Bundle();
            r0.putInt(FIELD_AUDIO_OFFLOAD_MODE_PREFERENCE, this.audioOffloadMode);
            r0.putBoolean(FIELD_IS_GAPLESS_SUPPORT_REQUIRED, this.isGaplessSupportRequired);
            r0.putBoolean(FIELD_IS_SPEED_CHANGE_SUPPORT_REQUIRED, this.isSpeedChangeSupportRequired);
            return r0;
        }
    }

    public static class Builder {
        private AudioOffloadPreferences audioOffloadPreferences;
        private HashSet<Integer> disabledTrackTypes;
        private boolean forceHighestSupportedBitrate;
        private boolean forceLowestBitrate;
        private int ignoredTextSelectionFlags;
        private boolean isPrioritizeImageOverVideoEnabled;
        private boolean isViewportSizeLimitedByPhysicalDisplaySize;
        private int maxAudioBitrate;
        private int maxAudioChannelCount;
        private int maxVideoBitrate;
        private int maxVideoFrameRate;
        private int maxVideoHeight;
        private int maxVideoWidth;
        private int minVideoBitrate;
        private int minVideoFrameRate;
        private int minVideoHeight;
        private int minVideoWidth;
        private HashMap<TrackGroup, TrackSelectionOverride> overrides;
        private ImmutableList<String> preferredAudioLanguages;
        private ImmutableList<String> preferredAudioMimeTypes;
        private int preferredAudioRoleFlags;
        private ImmutableList<String> preferredTextLanguages;
        private int preferredTextRoleFlags;
        private ImmutableList<String> preferredVideoLanguages;
        private ImmutableList<String> preferredVideoMimeTypes;
        private int preferredVideoRoleFlags;
        private boolean selectUndeterminedTextLanguage;
        private boolean usePreferredTextLanguagesAndRoleFlagsFromCaptioningManager;
        private int viewportHeight;
        private boolean viewportOrientationMayChange;
        private int viewportWidth;

        public Builder() {
            this.maxVideoWidth = Integer.MAX_VALUE;
            this.maxVideoHeight = Integer.MAX_VALUE;
            this.maxVideoFrameRate = Integer.MAX_VALUE;
            this.maxVideoBitrate = Integer.MAX_VALUE;
            this.viewportWidth = Integer.MAX_VALUE;
            this.viewportHeight = Integer.MAX_VALUE;
            this.isViewportSizeLimitedByPhysicalDisplaySize = true;
            this.viewportOrientationMayChange = true;
            this.preferredVideoMimeTypes = ImmutableList.of();
            this.preferredVideoLanguages = ImmutableList.of();
            this.preferredVideoRoleFlags = 0;
            this.preferredAudioLanguages = ImmutableList.of();
            this.preferredAudioRoleFlags = 0;
            this.maxAudioChannelCount = Integer.MAX_VALUE;
            this.maxAudioBitrate = Integer.MAX_VALUE;
            this.preferredAudioMimeTypes = ImmutableList.of();
            this.audioOffloadPreferences = AudioOffloadPreferences.DEFAULT;
            this.preferredTextLanguages = ImmutableList.of();
            this.preferredTextRoleFlags = 0;
            this.usePreferredTextLanguagesAndRoleFlagsFromCaptioningManager = true;
            this.ignoredTextSelectionFlags = 0;
            this.selectUndeterminedTextLanguage = false;
            this.isPrioritizeImageOverVideoEnabled = false;
            this.forceLowestBitrate = false;
            this.forceHighestSupportedBitrate = false;
            this.overrides = new HashMap();
            this.disabledTrackTypes = new HashSet();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Builder(Bundle r7) {
            String r0 = TrackSelectionParameters.access$000();
            TrackSelectionParameters r1 = TrackSelectionParameters.DEFAULT;
            this.maxVideoWidth = r7.getInt(r0, r1.maxVideoWidth);
            this.maxVideoHeight = r7.getInt(TrackSelectionParameters.access$100(), r1.maxVideoHeight);
            this.maxVideoFrameRate = r7.getInt(TrackSelectionParameters.access$200(), r1.maxVideoFrameRate);
            this.maxVideoBitrate = r7.getInt(TrackSelectionParameters.access$300(), r1.maxVideoBitrate);
            this.minVideoWidth = r7.getInt(TrackSelectionParameters.access$400(), r1.minVideoWidth);
            this.minVideoHeight = r7.getInt(TrackSelectionParameters.access$500(), r1.minVideoHeight);
            this.minVideoFrameRate = r7.getInt(TrackSelectionParameters.access$600(), r1.minVideoFrameRate);
            this.minVideoBitrate = r7.getInt(TrackSelectionParameters.access$700(), r1.minVideoBitrate);
            this.viewportWidth = r7.getInt(TrackSelectionParameters.access$800(), r1.viewportWidth);
            int r02 = r7.getInt(TrackSelectionParameters.access$900(), r1.viewportHeight);
            this.viewportHeight = r02;
            boolean r3 = true;
            int r4 = 0;
            if (this.viewportWidth != Integer.MAX_VALUE) goto L8;
            if (r02 != Integer.MAX_VALUE) goto L8;
            if (r7.getBoolean(TrackSelectionParameters.access$1000(), r1.isViewportSizeLimitedByPhysicalDisplaySize) == false) goto L8;
            boolean r03 = true;
        L9:
            this.isViewportSizeLimitedByPhysicalDisplaySize = r03;
            this.viewportOrientationMayChange = r7.getBoolean(TrackSelectionParameters.access$1100(), r1.viewportOrientationMayChange);
            this.preferredVideoMimeTypes = ImmutableList.copyOf((String[]) MoreObjects.firstNonNull(r7.getStringArray(TrackSelectionParameters.access$1200()), new String[0]));
            this.preferredVideoLanguages = ImmutableList.copyOf((String[]) MoreObjects.firstNonNull(r7.getStringArray(TrackSelectionParameters.access$1300()), new String[0]));
            this.preferredVideoRoleFlags = r7.getInt(TrackSelectionParameters.access$1400(), r1.preferredVideoRoleFlags);
            this.preferredAudioLanguages = normalizeLanguageCodes((String[]) MoreObjects.firstNonNull(r7.getStringArray(TrackSelectionParameters.access$1500()), new String[0]));
            this.preferredAudioRoleFlags = r7.getInt(TrackSelectionParameters.access$1600(), r1.preferredAudioRoleFlags);
            this.maxAudioChannelCount = r7.getInt(TrackSelectionParameters.access$1700(), r1.maxAudioChannelCount);
            this.maxAudioBitrate = r7.getInt(TrackSelectionParameters.access$1800(), r1.maxAudioBitrate);
            this.preferredAudioMimeTypes = ImmutableList.copyOf((String[]) MoreObjects.firstNonNull(r7.getStringArray(TrackSelectionParameters.access$1900()), new String[0]));
            this.audioOffloadPreferences = getAudioOffloadPreferencesFromBundle(r7);
            this.preferredTextLanguages = normalizeLanguageCodes((String[]) MoreObjects.firstNonNull(r7.getStringArray(TrackSelectionParameters.access$2000()), new String[0]));
            this.preferredTextRoleFlags = r7.getInt(TrackSelectionParameters.access$2100(), r1.preferredTextRoleFlags);
            if (this.preferredTextLanguages.isEmpty() == true) goto L12;
        L16:
            r3 = false;
        L17:
            this.usePreferredTextLanguagesAndRoleFlagsFromCaptioningManager = r3;
            this.ignoredTextSelectionFlags = r7.getInt(TrackSelectionParameters.access$2300(), r1.ignoredTextSelectionFlags);
            this.selectUndeterminedTextLanguage = r7.getBoolean(TrackSelectionParameters.access$2400(), r1.selectUndeterminedTextLanguage);
            this.isPrioritizeImageOverVideoEnabled = r7.getBoolean(TrackSelectionParameters.access$2500(), r1.isPrioritizeImageOverVideoEnabled);
            this.forceLowestBitrate = r7.getBoolean(TrackSelectionParameters.access$2600(), r1.forceLowestBitrate);
            this.forceHighestSupportedBitrate = r7.getBoolean(TrackSelectionParameters.access$2700(), r1.forceHighestSupportedBitrate);
            ArrayList r04 = r7.getParcelableArrayList(TrackSelectionParameters.access$2800());
            if (r04 != null) goto L20;
            ImmutableList r05 = ImmutableList.of();
        L21:
            this.overrides = new HashMap();
            int r12 = 0;
        L23:
            if (r12 >= r05.size()) goto L25;
            TrackSelectionOverride r2 = (TrackSelectionOverride) r05.get(r12);
            this.overrides.put(r2.mediaTrackGroup, r2);
            r12 = r12 + 1;
            goto L23
        L25:
            int[] r72 = (int[]) MoreObjects.firstNonNull(r7.getIntArray(TrackSelectionParameters.access$2900()), new int[0]);
            this.disabledTrackTypes = new HashSet();
            int r06 = r72.length;
        L26:
            if (r4 >= r06) goto L28;
            this.disabledTrackTypes.add(Integer.valueOf(r72[r4]));
            r4 = r4 + 1;
            goto L26
        L28:
            return;
        L20:
            r05 = BundleCollectionUtil.fromBundleList(new TrackSelectionParameters$Builder$$ExternalSyntheticLambda0(), r04);
            goto L21
        L12:
            if (this.preferredTextRoleFlags != 0) goto L16;
            if (r7.getBoolean(TrackSelectionParameters.access$2200(), r1.usePreferredTextLanguagesAndRoleFlagsFromCaptioningManager) == false) goto L16;
        L8:
            r03 = false;
            goto L9
        }

        public Builder(TrackSelectionParameters r1) {
            init(r1);
        }

        public static /* synthetic */ int access$3800(Builder r0) {
            return r0.maxVideoWidth;
        }

        public static /* synthetic */ int access$3900(Builder r0) {
            return r0.maxVideoHeight;
        }

        public static /* synthetic */ int access$4000(Builder r0) {
            return r0.maxVideoFrameRate;
        }

        public static /* synthetic */ int access$4100(Builder r0) {
            return r0.maxVideoBitrate;
        }

        public static /* synthetic */ int access$4200(Builder r0) {
            return r0.minVideoWidth;
        }

        public static /* synthetic */ int access$4300(Builder r0) {
            return r0.minVideoHeight;
        }

        public static /* synthetic */ int access$4400(Builder r0) {
            return r0.minVideoFrameRate;
        }

        public static /* synthetic */ int access$4500(Builder r0) {
            return r0.minVideoBitrate;
        }

        public static /* synthetic */ int access$4600(Builder r0) {
            return r0.viewportWidth;
        }

        public static /* synthetic */ int access$4700(Builder r0) {
            return r0.viewportHeight;
        }

        public static /* synthetic */ boolean access$4800(Builder r0) {
            return r0.isViewportSizeLimitedByPhysicalDisplaySize;
        }

        public static /* synthetic */ boolean access$4900(Builder r0) {
            return r0.viewportOrientationMayChange;
        }

        public static /* synthetic */ ImmutableList access$5000(Builder r0) {
            return r0.preferredVideoMimeTypes;
        }

        public static /* synthetic */ ImmutableList access$5100(Builder r0) {
            return r0.preferredVideoLanguages;
        }

        public static /* synthetic */ int access$5200(Builder r0) {
            return r0.preferredVideoRoleFlags;
        }

        public static /* synthetic */ ImmutableList access$5300(Builder r0) {
            return r0.preferredAudioLanguages;
        }

        public static /* synthetic */ int access$5400(Builder r0) {
            return r0.preferredAudioRoleFlags;
        }

        public static /* synthetic */ int access$5500(Builder r0) {
            return r0.maxAudioChannelCount;
        }

        public static /* synthetic */ int access$5600(Builder r0) {
            return r0.maxAudioBitrate;
        }

        public static /* synthetic */ ImmutableList access$5700(Builder r0) {
            return r0.preferredAudioMimeTypes;
        }

        public static /* synthetic */ AudioOffloadPreferences access$5800(Builder r0) {
            return r0.audioOffloadPreferences;
        }

        public static /* synthetic */ ImmutableList access$5900(Builder r0) {
            return r0.preferredTextLanguages;
        }

        public static /* synthetic */ int access$6000(Builder r0) {
            return r0.preferredTextRoleFlags;
        }

        public static /* synthetic */ boolean access$6100(Builder r0) {
            return r0.usePreferredTextLanguagesAndRoleFlagsFromCaptioningManager;
        }

        public static /* synthetic */ int access$6200(Builder r0) {
            return r0.ignoredTextSelectionFlags;
        }

        public static /* synthetic */ boolean access$6300(Builder r0) {
            return r0.selectUndeterminedTextLanguage;
        }

        public static /* synthetic */ boolean access$6400(Builder r0) {
            return r0.isPrioritizeImageOverVideoEnabled;
        }

        public static /* synthetic */ boolean access$6500(Builder r0) {
            return r0.forceLowestBitrate;
        }

        public static /* synthetic */ boolean access$6600(Builder r0) {
            return r0.forceHighestSupportedBitrate;
        }

        public static /* synthetic */ HashMap access$6700(Builder r0) {
            return r0.overrides;
        }

        public static /* synthetic */ HashSet access$6800(Builder r0) {
            return r0.disabledTrackTypes;
        }

        private static AudioOffloadPreferences getAudioOffloadPreferencesFromBundle(Bundle r4) {
            Bundle r0 = r4.getBundle(TrackSelectionParameters.access$3000());
            if (r0 != null) goto L5;
            AudioOffloadPreferences.Builder r02 = new AudioOffloadPreferences.Builder();
            String r1 = TrackSelectionParameters.access$3300();
            AudioOffloadPreferences r2 = AudioOffloadPreferences.DEFAULT;
            return r02.setAudioOffloadMode(r4.getInt(r1, r2.audioOffloadMode)).setIsGaplessSupportRequired(r4.getBoolean(TrackSelectionParameters.access$3200(), r2.isGaplessSupportRequired)).setIsSpeedChangeSupportRequired(r4.getBoolean(TrackSelectionParameters.access$3100(), r2.isSpeedChangeSupportRequired)).build();
        L5:
            return AudioOffloadPreferences.fromBundle(r0);
        }

        @EnsuresNonNull({"preferredVideoMimeTypes", "preferredVideoLanguages", "preferredAudioLanguages", "preferredAudioMimeTypes", "audioOffloadPreferences", "preferredTextLanguages", "overrides", "disabledTrackTypes"})
        private void init(TrackSelectionParameters r3) {
            this.maxVideoWidth = r3.maxVideoWidth;
            this.maxVideoHeight = r3.maxVideoHeight;
            this.maxVideoFrameRate = r3.maxVideoFrameRate;
            this.maxVideoBitrate = r3.maxVideoBitrate;
            this.minVideoWidth = r3.minVideoWidth;
            this.minVideoHeight = r3.minVideoHeight;
            this.minVideoFrameRate = r3.minVideoFrameRate;
            this.minVideoBitrate = r3.minVideoBitrate;
            this.viewportWidth = r3.viewportWidth;
            this.viewportHeight = r3.viewportHeight;
            this.isViewportSizeLimitedByPhysicalDisplaySize = r3.isViewportSizeLimitedByPhysicalDisplaySize;
            this.viewportOrientationMayChange = r3.viewportOrientationMayChange;
            this.preferredVideoMimeTypes = r3.preferredVideoMimeTypes;
            this.preferredVideoLanguages = r3.preferredVideoLanguages;
            this.preferredVideoRoleFlags = r3.preferredVideoRoleFlags;
            this.preferredAudioLanguages = r3.preferredAudioLanguages;
            this.preferredAudioRoleFlags = r3.preferredAudioRoleFlags;
            this.maxAudioChannelCount = r3.maxAudioChannelCount;
            this.maxAudioBitrate = r3.maxAudioBitrate;
            this.preferredAudioMimeTypes = r3.preferredAudioMimeTypes;
            this.audioOffloadPreferences = r3.audioOffloadPreferences;
            this.preferredTextLanguages = r3.preferredTextLanguages;
            this.preferredTextRoleFlags = r3.preferredTextRoleFlags;
            this.usePreferredTextLanguagesAndRoleFlagsFromCaptioningManager = r3.usePreferredTextLanguagesAndRoleFlagsFromCaptioningManager;
            this.ignoredTextSelectionFlags = r3.ignoredTextSelectionFlags;
            this.selectUndeterminedTextLanguage = r3.selectUndeterminedTextLanguage;
            this.isPrioritizeImageOverVideoEnabled = r3.isPrioritizeImageOverVideoEnabled;
            this.forceLowestBitrate = r3.forceLowestBitrate;
            this.forceHighestSupportedBitrate = r3.forceHighestSupportedBitrate;
            this.disabledTrackTypes = new HashSet(r3.disabledTrackTypes);
            this.overrides = new HashMap(r3.overrides);
        }

        private static ImmutableList<String> normalizeLanguageCodes(String[] r4) {
            ImmutableList.Builder r0 = ImmutableList.builder();
            String[] r42 = (String[]) Assertions.checkNotNull(r4);
            int r1 = r42.length;
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L6;
            r0.add(Util.normalizeLanguageCode((String) Assertions.checkNotNull(r42[r2])));
            r2 = r2 + 1;
            goto L3
        L6:
            return r0.build();
        }

        public Builder addOverride(TrackSelectionOverride r3) {
            this.overrides.put(r3.mediaTrackGroup, r3);
            return this;
        }

        public TrackSelectionParameters build() {
            return new TrackSelectionParameters(this);
        }

        public Builder clearOverrides() {
            this.overrides.clear();
            return this;
        }

        public Builder set(TrackSelectionParameters r1) {
            init(r1);
            return this;
        }

        public Builder setDisabledTrackTypes(Set<Integer> r2) {
            this.disabledTrackTypes.clear();
            this.disabledTrackTypes.addAll(r2);
            return this;
        }

        public Builder setTrackTypeDisabled(int r2, boolean r3) {
            HashSet<Integer> r0 = this.disabledTrackTypes;
            if (r3 == false) goto L6;
            r0.add(Integer.valueOf(r2));
            return this;
        L6:
            r0.remove(Integer.valueOf(r2));
            return this;
        }
    }

    static {
        TrackSelectionParameters r0 = new Builder().build();
        DEFAULT = r0;
        DEFAULT_WITHOUT_CONTEXT = r0;
        FIELD_PREFERRED_AUDIO_LANGUAGES = Util.intToStringMaxRadix(1);
        FIELD_PREFERRED_AUDIO_ROLE_FLAGS = Util.intToStringMaxRadix(2);
        FIELD_PREFERRED_TEXT_LANGUAGES = Util.intToStringMaxRadix(3);
        FIELD_PREFERRED_TEXT_ROLE_FLAGS = Util.intToStringMaxRadix(4);
        FIELD_SELECT_UNDETERMINED_TEXT_LANGUAGE = Util.intToStringMaxRadix(5);
        FIELD_MAX_VIDEO_WIDTH = Util.intToStringMaxRadix(6);
        FIELD_MAX_VIDEO_HEIGHT = Util.intToStringMaxRadix(7);
        FIELD_MAX_VIDEO_FRAMERATE = Util.intToStringMaxRadix(8);
        FIELD_MAX_VIDEO_BITRATE = Util.intToStringMaxRadix(9);
        FIELD_MIN_VIDEO_WIDTH = Util.intToStringMaxRadix(10);
        FIELD_MIN_VIDEO_HEIGHT = Util.intToStringMaxRadix(11);
        FIELD_MIN_VIDEO_FRAMERATE = Util.intToStringMaxRadix(12);
        FIELD_MIN_VIDEO_BITRATE = Util.intToStringMaxRadix(13);
        FIELD_VIEWPORT_WIDTH = Util.intToStringMaxRadix(14);
        FIELD_VIEWPORT_HEIGHT = Util.intToStringMaxRadix(15);
        FIELD_VIEWPORT_ORIENTATION_MAY_CHANGE = Util.intToStringMaxRadix(16);
        FIELD_PREFERRED_VIDEO_MIMETYPES = Util.intToStringMaxRadix(17);
        FIELD_MAX_AUDIO_CHANNEL_COUNT = Util.intToStringMaxRadix(18);
        FIELD_MAX_AUDIO_BITRATE = Util.intToStringMaxRadix(19);
        FIELD_PREFERRED_AUDIO_MIME_TYPES = Util.intToStringMaxRadix(20);
        FIELD_FORCE_LOWEST_BITRATE = Util.intToStringMaxRadix(21);
        FIELD_FORCE_HIGHEST_SUPPORTED_BITRATE = Util.intToStringMaxRadix(22);
        FIELD_SELECTION_OVERRIDES = Util.intToStringMaxRadix(23);
        FIELD_DISABLED_TRACK_TYPE = Util.intToStringMaxRadix(24);
        FIELD_PREFERRED_VIDEO_ROLE_FLAGS = Util.intToStringMaxRadix(25);
        FIELD_IGNORED_TEXT_SELECTION_FLAGS = Util.intToStringMaxRadix(26);
        FIELD_AUDIO_OFFLOAD_MODE_PREFERENCE = Util.intToStringMaxRadix(27);
        FIELD_IS_GAPLESS_SUPPORT_REQUIRED = Util.intToStringMaxRadix(28);
        FIELD_IS_SPEED_CHANGE_SUPPORT_REQUIRED = Util.intToStringMaxRadix(29);
        FIELD_AUDIO_OFFLOAD_PREFERENCES = Util.intToStringMaxRadix(30);
        FIELD_IS_PREFER_IMAGE_OVER_VIDEO_ENABLED = Util.intToStringMaxRadix(31);
        FIELD_PREFERRED_VIDEO_LANGUAGES = Util.intToStringMaxRadix(32);
        FIELD_IS_VIEWPORT_SIZE_LIMITED_BY_PHYSICAL_DISPLAY_SIZE = Util.intToStringMaxRadix(33);
        FIELD_USE_PREFERRED_TEXT_LANGUAGES_AND_ROLE_FLAGS_FROM_CAPTIONING_MANAGER = Util.intToStringMaxRadix(34);
    }

    public TrackSelectionParameters(Builder r2) {
        this.maxVideoWidth = Builder.access$3800(r2);
        this.maxVideoHeight = Builder.access$3900(r2);
        this.maxVideoFrameRate = Builder.access$4000(r2);
        this.maxVideoBitrate = Builder.access$4100(r2);
        this.minVideoWidth = Builder.access$4200(r2);
        this.minVideoHeight = Builder.access$4300(r2);
        this.minVideoFrameRate = Builder.access$4400(r2);
        this.minVideoBitrate = Builder.access$4500(r2);
        this.viewportWidth = Builder.access$4600(r2);
        this.viewportHeight = Builder.access$4700(r2);
        this.isViewportSizeLimitedByPhysicalDisplaySize = Builder.access$4800(r2);
        this.viewportOrientationMayChange = Builder.access$4900(r2);
        this.preferredVideoMimeTypes = Builder.access$5000(r2);
        this.preferredVideoLanguages = Builder.access$5100(r2);
        this.preferredVideoRoleFlags = Builder.access$5200(r2);
        this.preferredAudioLanguages = Builder.access$5300(r2);
        this.preferredAudioRoleFlags = Builder.access$5400(r2);
        this.maxAudioChannelCount = Builder.access$5500(r2);
        this.maxAudioBitrate = Builder.access$5600(r2);
        this.preferredAudioMimeTypes = Builder.access$5700(r2);
        this.audioOffloadPreferences = Builder.access$5800(r2);
        this.preferredTextLanguages = Builder.access$5900(r2);
        this.preferredTextRoleFlags = Builder.access$6000(r2);
        this.usePreferredTextLanguagesAndRoleFlagsFromCaptioningManager = Builder.access$6100(r2);
        this.ignoredTextSelectionFlags = Builder.access$6200(r2);
        this.selectUndeterminedTextLanguage = Builder.access$6300(r2);
        this.isPrioritizeImageOverVideoEnabled = Builder.access$6400(r2);
        this.forceLowestBitrate = Builder.access$6500(r2);
        this.forceHighestSupportedBitrate = Builder.access$6600(r2);
        this.overrides = ImmutableMap.copyOf(Builder.access$6700(r2));
        this.disabledTrackTypes = ImmutableSet.copyOf(Builder.access$6800(r2));
    }

    public static /* synthetic */ String access$000() {
        return FIELD_MAX_VIDEO_WIDTH;
    }

    public static /* synthetic */ String access$100() {
        return FIELD_MAX_VIDEO_HEIGHT;
    }

    public static /* synthetic */ String access$1000() {
        return FIELD_IS_VIEWPORT_SIZE_LIMITED_BY_PHYSICAL_DISPLAY_SIZE;
    }

    public static /* synthetic */ String access$1100() {
        return FIELD_VIEWPORT_ORIENTATION_MAY_CHANGE;
    }

    public static /* synthetic */ String access$1200() {
        return FIELD_PREFERRED_VIDEO_MIMETYPES;
    }

    public static /* synthetic */ String access$1300() {
        return FIELD_PREFERRED_VIDEO_LANGUAGES;
    }

    public static /* synthetic */ String access$1400() {
        return FIELD_PREFERRED_VIDEO_ROLE_FLAGS;
    }

    public static /* synthetic */ String access$1500() {
        return FIELD_PREFERRED_AUDIO_LANGUAGES;
    }

    public static /* synthetic */ String access$1600() {
        return FIELD_PREFERRED_AUDIO_ROLE_FLAGS;
    }

    public static /* synthetic */ String access$1700() {
        return FIELD_MAX_AUDIO_CHANNEL_COUNT;
    }

    public static /* synthetic */ String access$1800() {
        return FIELD_MAX_AUDIO_BITRATE;
    }

    public static /* synthetic */ String access$1900() {
        return FIELD_PREFERRED_AUDIO_MIME_TYPES;
    }

    public static /* synthetic */ String access$200() {
        return FIELD_MAX_VIDEO_FRAMERATE;
    }

    public static /* synthetic */ String access$2000() {
        return FIELD_PREFERRED_TEXT_LANGUAGES;
    }

    public static /* synthetic */ String access$2100() {
        return FIELD_PREFERRED_TEXT_ROLE_FLAGS;
    }

    public static /* synthetic */ String access$2200() {
        return FIELD_USE_PREFERRED_TEXT_LANGUAGES_AND_ROLE_FLAGS_FROM_CAPTIONING_MANAGER;
    }

    public static /* synthetic */ String access$2300() {
        return FIELD_IGNORED_TEXT_SELECTION_FLAGS;
    }

    public static /* synthetic */ String access$2400() {
        return FIELD_SELECT_UNDETERMINED_TEXT_LANGUAGE;
    }

    public static /* synthetic */ String access$2500() {
        return FIELD_IS_PREFER_IMAGE_OVER_VIDEO_ENABLED;
    }

    public static /* synthetic */ String access$2600() {
        return FIELD_FORCE_LOWEST_BITRATE;
    }

    public static /* synthetic */ String access$2700() {
        return FIELD_FORCE_HIGHEST_SUPPORTED_BITRATE;
    }

    public static /* synthetic */ String access$2800() {
        return FIELD_SELECTION_OVERRIDES;
    }

    public static /* synthetic */ String access$2900() {
        return FIELD_DISABLED_TRACK_TYPE;
    }

    public static /* synthetic */ String access$300() {
        return FIELD_MAX_VIDEO_BITRATE;
    }

    public static /* synthetic */ String access$3000() {
        return FIELD_AUDIO_OFFLOAD_PREFERENCES;
    }

    public static /* synthetic */ String access$3100() {
        return FIELD_IS_SPEED_CHANGE_SUPPORT_REQUIRED;
    }

    public static /* synthetic */ String access$3200() {
        return FIELD_IS_GAPLESS_SUPPORT_REQUIRED;
    }

    public static /* synthetic */ String access$3300() {
        return FIELD_AUDIO_OFFLOAD_MODE_PREFERENCE;
    }

    public static /* synthetic */ String access$400() {
        return FIELD_MIN_VIDEO_WIDTH;
    }

    public static /* synthetic */ String access$500() {
        return FIELD_MIN_VIDEO_HEIGHT;
    }

    public static /* synthetic */ String access$600() {
        return FIELD_MIN_VIDEO_FRAMERATE;
    }

    public static /* synthetic */ String access$700() {
        return FIELD_MIN_VIDEO_BITRATE;
    }

    public static /* synthetic */ String access$800() {
        return FIELD_VIEWPORT_WIDTH;
    }

    public static /* synthetic */ String access$900() {
        return FIELD_VIEWPORT_HEIGHT;
    }

    public static TrackSelectionParameters fromBundle(Bundle r1) {
        return new Builder(r1).build();
    }

    public Builder buildUpon() {
        return new Builder(this);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L73:
        return false;
    L8:
        if (getClass() != r5.getClass()) goto L73;
        TrackSelectionParameters r52 = (TrackSelectionParameters) r5;
        if (this.maxVideoWidth != r52.maxVideoWidth) goto L73;
        if (this.maxVideoHeight != r52.maxVideoHeight) goto L73;
        if (this.maxVideoFrameRate != r52.maxVideoFrameRate) goto L73;
        if (this.maxVideoBitrate != r52.maxVideoBitrate) goto L73;
        if (this.minVideoWidth != r52.minVideoWidth) goto L73;
        if (this.minVideoHeight != r52.minVideoHeight) goto L73;
        if (this.minVideoFrameRate != r52.minVideoFrameRate) goto L73;
        if (this.minVideoBitrate != r52.minVideoBitrate) goto L73;
        if (this.viewportOrientationMayChange != r52.viewportOrientationMayChange) goto L73;
        if (this.viewportWidth != r52.viewportWidth) goto L73;
        if (this.viewportHeight != r52.viewportHeight) goto L73;
        if (this.isViewportSizeLimitedByPhysicalDisplaySize != r52.isViewportSizeLimitedByPhysicalDisplaySize) goto L73;
        if (this.preferredVideoMimeTypes.equals(r52.preferredVideoMimeTypes) == false) goto L73;
        if (this.preferredVideoLanguages.equals(r52.preferredVideoLanguages) == false) goto L73;
        if (this.preferredVideoRoleFlags != r52.preferredVideoRoleFlags) goto L73;
        if (this.preferredAudioLanguages.equals(r52.preferredAudioLanguages) == false) goto L73;
        if (this.preferredAudioRoleFlags != r52.preferredAudioRoleFlags) goto L73;
        if (this.maxAudioChannelCount != r52.maxAudioChannelCount) goto L73;
        if (this.maxAudioBitrate != r52.maxAudioBitrate) goto L73;
        if (this.preferredAudioMimeTypes.equals(r52.preferredAudioMimeTypes) == false) goto L73;
        if (this.audioOffloadPreferences.equals(r52.audioOffloadPreferences) == false) goto L73;
        if (this.preferredTextLanguages.equals(r52.preferredTextLanguages) == false) goto L73;
        if (this.preferredTextRoleFlags != r52.preferredTextRoleFlags) goto L73;
        if (this.usePreferredTextLanguagesAndRoleFlagsFromCaptioningManager != r52.usePreferredTextLanguagesAndRoleFlagsFromCaptioningManager) goto L73;
        if (this.ignoredTextSelectionFlags != r52.ignoredTextSelectionFlags) goto L73;
        if (this.selectUndeterminedTextLanguage != r52.selectUndeterminedTextLanguage) goto L73;
        if (this.isPrioritizeImageOverVideoEnabled != r52.isPrioritizeImageOverVideoEnabled) goto L73;
        if (this.forceLowestBitrate != r52.forceLowestBitrate) goto L73;
        if (this.forceHighestSupportedBitrate != r52.forceHighestSupportedBitrate) goto L73;
        if (this.overrides.equals(r52.overrides) == false) goto L73;
        if (this.disabledTrackTypes.equals(r52.disabledTrackTypes) == false) goto L73;
        return true;
    }

    public int hashCode() {
        return ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((this.maxVideoWidth + 31) * 31) + this.maxVideoHeight) * 31) + this.maxVideoFrameRate) * 31) + this.maxVideoBitrate) * 31) + this.minVideoWidth) * 31) + this.minVideoHeight) * 31) + this.minVideoFrameRate) * 31) + this.minVideoBitrate) * 31) + (this.viewportOrientationMayChange ? 1 : 0)) * 31) + this.viewportWidth) * 31) + this.viewportHeight) * 31) + (this.isViewportSizeLimitedByPhysicalDisplaySize ? 1 : 0)) * 31) + this.preferredVideoMimeTypes.hashCode()) * 31) + this.preferredVideoLanguages.hashCode()) * 31) + this.preferredVideoRoleFlags) * 31) + this.preferredAudioLanguages.hashCode()) * 31) + this.preferredAudioRoleFlags) * 31) + this.maxAudioChannelCount) * 31) + this.maxAudioBitrate) * 31) + this.preferredAudioMimeTypes.hashCode()) * 31) + this.audioOffloadPreferences.hashCode()) * 31) + this.preferredTextLanguages.hashCode()) * 31) + this.preferredTextRoleFlags) * 31) + (this.usePreferredTextLanguagesAndRoleFlagsFromCaptioningManager ? 1 : 0)) * 31) + this.ignoredTextSelectionFlags) * 31) + (this.selectUndeterminedTextLanguage ? 1 : 0)) * 31) + (this.isPrioritizeImageOverVideoEnabled ? 1 : 0)) * 31) + (this.forceLowestBitrate ? 1 : 0)) * 31) + (this.forceHighestSupportedBitrate ? 1 : 0)) * 31) + this.overrides.hashCode()) * 31) + this.disabledTrackTypes.hashCode();
    }

    public Bundle toBundle() {
        Bundle r0 = new Bundle();
        r0.putInt(FIELD_MAX_VIDEO_WIDTH, this.maxVideoWidth);
        r0.putInt(FIELD_MAX_VIDEO_HEIGHT, this.maxVideoHeight);
        r0.putInt(FIELD_MAX_VIDEO_FRAMERATE, this.maxVideoFrameRate);
        r0.putInt(FIELD_MAX_VIDEO_BITRATE, this.maxVideoBitrate);
        r0.putInt(FIELD_MIN_VIDEO_WIDTH, this.minVideoWidth);
        r0.putInt(FIELD_MIN_VIDEO_HEIGHT, this.minVideoHeight);
        r0.putInt(FIELD_MIN_VIDEO_FRAMERATE, this.minVideoFrameRate);
        r0.putInt(FIELD_MIN_VIDEO_BITRATE, this.minVideoBitrate);
        r0.putInt(FIELD_VIEWPORT_WIDTH, this.viewportWidth);
        r0.putInt(FIELD_VIEWPORT_HEIGHT, this.viewportHeight);
        r0.putBoolean(FIELD_IS_VIEWPORT_SIZE_LIMITED_BY_PHYSICAL_DISPLAY_SIZE, this.isViewportSizeLimitedByPhysicalDisplaySize);
        r0.putBoolean(FIELD_VIEWPORT_ORIENTATION_MAY_CHANGE, this.viewportOrientationMayChange);
        r0.putStringArray(FIELD_PREFERRED_VIDEO_MIMETYPES, (String[]) this.preferredVideoMimeTypes.toArray(new String[0]));
        r0.putStringArray(FIELD_PREFERRED_VIDEO_LANGUAGES, (String[]) this.preferredVideoLanguages.toArray(new String[0]));
        r0.putInt(FIELD_PREFERRED_VIDEO_ROLE_FLAGS, this.preferredVideoRoleFlags);
        r0.putStringArray(FIELD_PREFERRED_AUDIO_LANGUAGES, (String[]) this.preferredAudioLanguages.toArray(new String[0]));
        r0.putInt(FIELD_PREFERRED_AUDIO_ROLE_FLAGS, this.preferredAudioRoleFlags);
        r0.putInt(FIELD_MAX_AUDIO_CHANNEL_COUNT, this.maxAudioChannelCount);
        r0.putInt(FIELD_MAX_AUDIO_BITRATE, this.maxAudioBitrate);
        r0.putStringArray(FIELD_PREFERRED_AUDIO_MIME_TYPES, (String[]) this.preferredAudioMimeTypes.toArray(new String[0]));
        r0.putStringArray(FIELD_PREFERRED_TEXT_LANGUAGES, (String[]) this.preferredTextLanguages.toArray(new String[0]));
        r0.putInt(FIELD_PREFERRED_TEXT_ROLE_FLAGS, this.preferredTextRoleFlags);
        r0.putBoolean(FIELD_USE_PREFERRED_TEXT_LANGUAGES_AND_ROLE_FLAGS_FROM_CAPTIONING_MANAGER, this.usePreferredTextLanguagesAndRoleFlagsFromCaptioningManager);
        r0.putInt(FIELD_IGNORED_TEXT_SELECTION_FLAGS, this.ignoredTextSelectionFlags);
        r0.putBoolean(FIELD_SELECT_UNDETERMINED_TEXT_LANGUAGE, this.selectUndeterminedTextLanguage);
        r0.putInt(FIELD_AUDIO_OFFLOAD_MODE_PREFERENCE, this.audioOffloadPreferences.audioOffloadMode);
        r0.putBoolean(FIELD_IS_GAPLESS_SUPPORT_REQUIRED, this.audioOffloadPreferences.isGaplessSupportRequired);
        r0.putBoolean(FIELD_IS_SPEED_CHANGE_SUPPORT_REQUIRED, this.audioOffloadPreferences.isSpeedChangeSupportRequired);
        r0.putBundle(FIELD_AUDIO_OFFLOAD_PREFERENCES, this.audioOffloadPreferences.toBundle());
        r0.putBoolean(FIELD_IS_PREFER_IMAGE_OVER_VIDEO_ENABLED, this.isPrioritizeImageOverVideoEnabled);
        r0.putBoolean(FIELD_FORCE_LOWEST_BITRATE, this.forceLowestBitrate);
        r0.putBoolean(FIELD_FORCE_HIGHEST_SUPPORTED_BITRATE, this.forceHighestSupportedBitrate);
        r0.putParcelableArrayList(FIELD_SELECTION_OVERRIDES, BundleCollectionUtil.toBundleArrayList(this.overrides.values(), new TrackSelectionParameters$$ExternalSyntheticLambda0()));
        r0.putIntArray(FIELD_DISABLED_TRACK_TYPE, Ints.toArray(this.disabledTrackTypes));
        return r0;
    }
}
