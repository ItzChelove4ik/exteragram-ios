package androidx.media3.exoplayer.trackselection;

import android.content.Context;
import android.graphics.Point;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.Spatializer;
import android.media.Spatializer$OnSpatializerStateChangedListener;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.accessibility.CaptioningManager;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.Format;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.TrackSelectionOverride;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.audio.AudioManagerCompat;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.RendererConfiguration;
import androidx.media3.exoplayer.audio.DefaultAudioSink$StreamEventCallbackV29$$ExternalSyntheticLambda0;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.AdaptiveTrackSelection;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.trackselection.MappingTrackSelector;
import com.google.common.base.Predicate;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.Set;
import okhttp3.internal.url._UrlKt;
import okhttp3.internal.ws.WebSocketProtocol;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.TranslateController;

/* loaded from: classes4.dex */
public class DefaultTrackSelector extends MappingTrackSelector implements RendererCapabilities.Listener {
    private static final Ordering<Integer> FORMAT_VALUE_ORDERING = null;
    private AudioAttributes audioAttributes;
    public final Context context;
    private Boolean deviceIsTV;
    private final Object lock;
    private Parameters parameters;
    private Thread playbackThread;
    private SpatializerWrapperV32 spatializer;
    private final ExoTrackSelection.Factory trackSelectionFactory;

    /* renamed from: androidx.media3.exoplayer.trackselection.DefaultTrackSelector$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class AudioTrackInfo extends TrackInfo<AudioTrackInfo> implements Comparable<AudioTrackInfo> {
        private final boolean allowMixedMimeTypes;
        private final int bitrate;
        private final int channelCount;
        private final boolean hasMainOrNoRoleFlag;
        private final boolean isDefaultSelectionFlag;
        private final boolean isObjectBasedAudio;
        private final boolean isWithinConstraints;
        private final boolean isWithinRendererCapabilities;
        private final String language;
        private final int localeLanguageMatchIndex;
        private final int localeLanguageScore;
        private final Parameters parameters;
        private final int preferredLanguageIndex;
        private final int preferredLanguageScore;
        private final int preferredMimeTypeMatchIndex;
        private final int preferredRoleFlagsScore;
        private final int sampleRate;
        private final int selectionEligibility;
        private final boolean usesHardwareAcceleration;
        private final boolean usesPrimaryDecoder;

        public AudioTrackInfo(int r6, TrackGroup r7, int r8, Parameters r9, int r10, boolean r11, Predicate<Format> r12, int r13) {
            super(r6, r7, r8);
            this.parameters = r9;
            if (r9.allowAudioNonSeamlessAdaptiveness == false) goto L5;
            int r62 = 24;
        L6:
            boolean r82 = true;
            if (r9.allowAudioMixedMimeTypeAdaptiveness == true) goto L9;
        L11:
            boolean r72 = false;
        L12:
            this.allowMixedMimeTypes = r72;
            this.language = DefaultTrackSelector.normalizeUndeterminedLanguageToNull(this.format.language);
            this.isWithinRendererCapabilities = RendererCapabilities.isFormatSupported(r10, false);
            int r73 = 0;
        L13:
            int r1 = Integer.MAX_VALUE;
            if (r73 >= r9.preferredAudioLanguages.size()) goto L19;
            int r132 = DefaultTrackSelector.getFormatLanguageScore(this.format, r9.preferredAudioLanguages.get(r73), false);
            if (r132 > 0) goto L20;
            r73 = r73 + 1;
        L20:
            this.preferredLanguageIndex = r73;
            this.preferredLanguageScore = r132;
            this.preferredRoleFlagsScore = DefaultTrackSelector.access$4200(this.format.roleFlags, r9.preferredAudioRoleFlags);
            Format r74 = this.format;
            int r133 = r74.roleFlags;
            if (r133 != 0) goto L23;
        L26:
            boolean r134 = true;
        L27:
            this.hasMainOrNoRoleFlag = r134;
            if ((r74.selectionFlags & 1) == 0) goto L30;
            boolean r135 = true;
        L31:
            this.isDefaultSelectionFlag = r135;
            this.isObjectBasedAudio = DefaultTrackSelector.access$4500(r74);
            Format r75 = this.format;
            int r136 = r75.channelCount;
            this.channelCount = r136;
            this.sampleRate = r75.sampleRate;
            int r2 = r75.bitrate;
            this.bitrate = r2;
            if (r2 != (-1)) goto L34;
        L35:
            if (r136 == (-1)) goto L39;
            if (r136 <= r9.maxAudioChannelCount) goto L39;
        L41:
            boolean r76 = false;
        L42:
            this.isWithinConstraints = r76;
            String[] r77 = Util.getSystemLanguageCodes();
            int r122 = 0;
        L44:
            if (r122 >= r77.length) goto L49;
            int r137 = DefaultTrackSelector.getFormatLanguageScore(this.format, r77[r122], false);
            if (r137 > 0) goto L50;
            r122 = r122 + 1;
        L50:
            this.localeLanguageMatchIndex = r122;
            this.localeLanguageScore = r137;
            int r78 = 0;
        L52:
            if (r78 >= r9.preferredAudioMimeTypes.size()) goto L59;
            String r123 = this.format.sampleMimeType;
            if (r123 == null) goto L58;
            if (r123.equals(r9.preferredAudioMimeTypes.get(r78)) == false) goto L58;
            r1 = r78;
        L58:
            r78 = r78 + 1;
        L59:
            this.preferredMimeTypeMatchIndex = r1;
            if (RendererCapabilities.getDecoderSupport(r10) != 128) goto L62;
            boolean r79 = true;
        L63:
            this.usesPrimaryDecoder = r79;
            if (RendererCapabilities.getHardwareAccelerationSupport(r10) == 64) goto L67;
            r82 = false;
        L67:
            this.usesHardwareAcceleration = r82;
            this.selectionEligibility = evaluateSelectionEligibility(r10, r11, r62);
            return;
        L62:
            r79 = false;
            goto L63
        L49:
            r137 = 0;
            r122 = Integer.MAX_VALUE;
        L39:
            if (r12.apply(r75) == false) goto L41;
            r76 = true;
            goto L42
        L34:
            if (r2 > r9.maxAudioBitrate) goto L41;
        L30:
            r135 = false;
            goto L31
        L23:
            if ((r133 & 1) != 0) goto L26;
            r134 = false;
            goto L27
        L19:
            r132 = 0;
            r73 = Integer.MAX_VALUE;
            goto L20
        L9:
            if ((r13 & r62) == 0) goto L11;
            r72 = true;
            goto L12
        L5:
            r62 = 16;
            goto L6
        }

        public static int compareSelections(List<AudioTrackInfo> r0, List<AudioTrackInfo> r1) {
            return ((AudioTrackInfo) Collections.max(r0)).compareTo2((AudioTrackInfo) Collections.max(r1));
        }

        public static ImmutableList<AudioTrackInfo> createForTrackGroup(int r11, TrackGroup r12, Parameters r13, int[] r14, boolean r15, Predicate<Format> r16, int r17) {
            ImmutableList.Builder r0 = ImmutableList.builder();
            int r5 = 0;
        L4:
            if (r5 >= r12.length) goto L7;
            r0.add(new AudioTrackInfo(r11, r12, r5, r13, r14[r5], r15, r16, r17));
            r5 = r5 + 1;
            goto L4
        L7:
            return r0.build();
        }

        private int evaluateSelectionEligibility(int r5, boolean r6, int r7) {
            if (RendererCapabilities.isFormatSupported(r5, this.parameters.exceedRendererCapabilitiesIfNecessary) == true) goto L6;
            return 0;
        L6:
            if (this.isWithinConstraints == false) goto L8;
        L10:
            Parameters r0 = this.parameters;
            if (r0.audioOffloadPreferences.audioOffloadMode != 2) goto L16;
            if (DefaultTrackSelector.access$4600(r0, r5, this.format) == true) goto L16;
            return 0;
        L16:
            if (RendererCapabilities.isFormatSupported(r5, false) == true) goto L18;
            return 1;
        L18:
            if (this.isWithinConstraints == true) goto L20;
            return 1;
        L20:
            if (this.format.bitrate == (-1)) goto L36;
            Parameters r4 = this.parameters;
            if (r4.forceHighestSupportedBitrate == false) goto L24;
            return 1;
        L24:
            if (r4.forceLowestBitrate == false) goto L26;
            return 1;
        L26:
            if (r4.allowMultipleAdaptiveSelections == true) goto L29;
            if (r6 == false) goto L29;
            return 1;
        L29:
            if (r4.audioOffloadPreferences.audioOffloadMode != 2) goto L31;
            return 1;
        L31:
            if ((r5 & r7) == 0) goto L41;
            return 2;
        L41:
            return 1;
        L36:
            return 1;
        L8:
            if (this.parameters.exceedAudioConstraintsIfNecessary == true) goto L10;
            return 0;
        }

        /* renamed from: compareTo, reason: avoid collision after fix types in other method */
        public int compareTo2(AudioTrackInfo r6) {
            if (this.isWithinConstraints == true) goto L5;
        L7:
            Ordering r0 = DefaultTrackSelector.access$4400().reverse();
        L8:
            ComparisonChain r1 = ComparisonChain.start().compareFalseFirst(this.isWithinRendererCapabilities, r6.isWithinRendererCapabilities).compare(Integer.valueOf(this.preferredLanguageIndex), Integer.valueOf(r6.preferredLanguageIndex), Ordering.natural().reverse()).compare(this.preferredLanguageScore, r6.preferredLanguageScore).compare(this.preferredRoleFlagsScore, r6.preferredRoleFlagsScore).compareFalseFirst(this.isDefaultSelectionFlag, r6.isDefaultSelectionFlag).compareFalseFirst(this.hasMainOrNoRoleFlag, r6.hasMainOrNoRoleFlag).compare(Integer.valueOf(this.localeLanguageMatchIndex), Integer.valueOf(r6.localeLanguageMatchIndex), Ordering.natural().reverse()).compare(this.localeLanguageScore, r6.localeLanguageScore).compareFalseFirst(this.isWithinConstraints, r6.isWithinConstraints).compare(Integer.valueOf(this.preferredMimeTypeMatchIndex), Integer.valueOf(r6.preferredMimeTypeMatchIndex), Ordering.natural().reverse());
            if (this.parameters.forceLowestBitrate == false) goto L11;
            r1 = r1.compare(Integer.valueOf(this.bitrate), Integer.valueOf(r6.bitrate), DefaultTrackSelector.access$4400().reverse());
        L11:
            ComparisonChain r12 = r1.compareFalseFirst(this.usesPrimaryDecoder, r6.usesPrimaryDecoder).compareFalseFirst(this.usesHardwareAcceleration, r6.usesHardwareAcceleration).compareFalseFirst(this.isObjectBasedAudio, r6.isObjectBasedAudio).compare(Integer.valueOf(this.channelCount), Integer.valueOf(r6.channelCount), r0).compare(Integer.valueOf(this.sampleRate), Integer.valueOf(r6.sampleRate), r0);
            if (Objects.equals(this.language, r6.language) == false) goto L15;
            r12 = r12.compare(Integer.valueOf(this.bitrate), Integer.valueOf(r6.bitrate), r0);
        L15:
            return r12.result();
        L5:
            if (this.isWithinRendererCapabilities == false) goto L7;
            r0 = DefaultTrackSelector.access$4400();
            goto L8
        }

        @Override // java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(AudioTrackInfo r1) {
            return compareTo2(r1);
        }

        @Override // androidx.media3.exoplayer.trackselection.DefaultTrackSelector.TrackInfo
        public int getSelectionEligibility() {
            return this.selectionEligibility;
        }

        public boolean isCompatibleForAdaptationWith(AudioTrackInfo r4) {
            if (this.parameters.allowAudioMixedChannelCountAdaptiveness == true) goto L9;
            int r0 = this.format.channelCount;
            if (r0 != (-1)) goto L7;
            return false;
        L7:
            if (r0 == r4.format.channelCount) goto L9;
            return false;
        L9:
            if (this.allowMixedMimeTypes == true) goto L14;
            String r02 = this.format.sampleMimeType;
            if (r02 != null) goto L13;
            return false;
        L13:
            if (TextUtils.equals(r02, r4.format.sampleMimeType) == true) goto L14;
            return false;
        L14:
            Parameters r03 = this.parameters;
            if (r03.allowAudioMixedSampleRateAdaptiveness == true) goto L21;
            int r2 = this.format.sampleRate;
            if (r2 != (-1)) goto L19;
            return false;
        L19:
            if (r2 == r4.format.sampleRate) goto L21;
            return false;
        L21:
            if (r03.allowAudioMixedDecoderSupportAdaptiveness == false) goto L23;
            return true;
        L23:
            if (this.usesPrimaryDecoder == r4.usesPrimaryDecoder) goto L25;
            return false;
        L25:
            if (this.usesHardwareAcceleration == r4.usesHardwareAcceleration) goto L38;
            return false;
        L38:
            return true;
        }

        @Override // androidx.media3.exoplayer.trackselection.DefaultTrackSelector.TrackInfo
        public /* bridge */ /* synthetic */ boolean isCompatibleForAdaptationWith(TrackInfo r1) {
            return isCompatibleForAdaptationWith((AudioTrackInfo) r1);
        }
    }

    public static final class ImageTrackInfo extends TrackInfo<ImageTrackInfo> implements Comparable<ImageTrackInfo> {
        private final int pixelCount;
        private final int selectionEligibility;

        public ImageTrackInfo(int r1, TrackGroup r2, int r3, Parameters r4, int r5) {
            super(r1, r2, r3);
            this.selectionEligibility = RendererCapabilities.isFormatSupported(r5, r4.exceedRendererCapabilitiesIfNecessary) ? 1 : 0;
            this.pixelCount = this.format.getPixelCount();
        }

        public static int compareSelections(List<ImageTrackInfo> r1, List<ImageTrackInfo> r2) {
            return r1.get(0).compareTo2(r2.get(0));
        }

        public static ImmutableList<ImageTrackInfo> createForTrackGroup(int r8, TrackGroup r9, Parameters r10, int[] r11) {
            ImmutableList.Builder r0 = ImmutableList.builder();
            int r5 = 0;
        L4:
            if (r5 >= r9.length) goto L7;
            r0.add(new ImageTrackInfo(r8, r9, r5, r10, r11[r5]));
            r5 = r5 + 1;
            goto L4
        L7:
            return r0.build();
        }

        /* renamed from: compareTo, reason: avoid collision after fix types in other method */
        public int compareTo2(ImageTrackInfo r1) {
            return Integer.compare(this.pixelCount, r1.pixelCount);
        }

        @Override // java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(ImageTrackInfo r1) {
            return compareTo2(r1);
        }

        @Override // androidx.media3.exoplayer.trackselection.DefaultTrackSelector.TrackInfo
        public int getSelectionEligibility() {
            return this.selectionEligibility;
        }

        public boolean isCompatibleForAdaptationWith(ImageTrackInfo r1) {
            return false;
        }

        @Override // androidx.media3.exoplayer.trackselection.DefaultTrackSelector.TrackInfo
        public /* bridge */ /* synthetic */ boolean isCompatibleForAdaptationWith(TrackInfo r1) {
            return isCompatibleForAdaptationWith((ImageTrackInfo) r1);
        }
    }

    public static final class OtherTrackScore implements Comparable<OtherTrackScore> {
        private final boolean isDefault;
        private final boolean isWithinRendererCapabilities;

        public OtherTrackScore(Format r3, int r4) {
            boolean r0 = true;
            if ((r3.selectionFlags & 1) != 0) goto L6;
            r0 = false;
        L6:
            this.isDefault = r0;
            this.isWithinRendererCapabilities = RendererCapabilities.isFormatSupported(r4, false);
        }

        /* renamed from: compareTo, reason: avoid collision after fix types in other method */
        public int compareTo2(OtherTrackScore r4) {
            return ComparisonChain.start().compareFalseFirst(this.isWithinRendererCapabilities, r4.isWithinRendererCapabilities).compareFalseFirst(this.isDefault, r4.isDefault).result();
        }

        @Override // java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(OtherTrackScore r1) {
            return compareTo2(r1);
        }
    }

    public static final class Parameters extends TrackSelectionParameters {
        public static final Parameters DEFAULT = null;

        @Deprecated
        public static final Parameters DEFAULT_WITHOUT_CONTEXT = null;
        private static final String FIELD_ALLOW_AUDIO_MIXED_CHANNEL_COUNT_ADAPTIVENESS = null;
        private static final String FIELD_ALLOW_AUDIO_MIXED_DECODER_SUPPORT_ADAPTIVENESS = null;
        private static final String FIELD_ALLOW_AUDIO_MIXED_MIME_TYPE_ADAPTIVENESS = null;
        private static final String FIELD_ALLOW_AUDIO_MIXED_SAMPLE_RATE_ADAPTIVENESS = null;
        private static final String FIELD_ALLOW_AUDIO_NON_SEAMLESS_ADAPTIVENESS = null;
        private static final String FIELD_ALLOW_INVALIDATE_SELECTIONS_ON_RENDERER_CAPABILITIES_CHANGE = null;
        private static final String FIELD_ALLOW_MULTIPLE_ADAPTIVE_SELECTIONS = null;
        private static final String FIELD_ALLOW_VIDEO_MIXED_DECODER_SUPPORT_ADAPTIVENESS = null;
        private static final String FIELD_ALLOW_VIDEO_MIXED_MIME_TYPE_ADAPTIVENESS = null;
        private static final String FIELD_ALLOW_VIDEO_NON_SEAMLESS_ADAPTIVENESS = null;
        private static final String FIELD_CONSTRAIN_AUDIO_CHANNEL_COUNT_TO_DEVICE_CAPABILITIES = null;
        private static final String FIELD_EXCEED_AUDIO_CONSTRAINTS_IF_NECESSARY = null;
        private static final String FIELD_EXCEED_RENDERER_CAPABILITIES_IF_NECESSARY = null;
        private static final String FIELD_EXCEED_VIDEO_CONSTRAINTS_IF_NECESSARY = null;
        private static final String FIELD_RENDERER_DISABLED_INDICES = null;
        private static final String FIELD_SELECTION_OVERRIDES = null;
        private static final String FIELD_SELECTION_OVERRIDES_RENDERER_INDICES = null;
        private static final String FIELD_SELECTION_OVERRIDES_TRACK_GROUP_ARRAYS = null;
        private static final String FIELD_TUNNELING_ENABLED = null;
        public final boolean allowAudioMixedChannelCountAdaptiveness;
        public final boolean allowAudioMixedDecoderSupportAdaptiveness;
        public final boolean allowAudioMixedMimeTypeAdaptiveness;
        public final boolean allowAudioMixedSampleRateAdaptiveness;
        public final boolean allowAudioNonSeamlessAdaptiveness;
        public final boolean allowInvalidateSelectionsOnRendererCapabilitiesChange;
        public final boolean allowMultipleAdaptiveSelections;
        public final boolean allowVideoMixedDecoderSupportAdaptiveness;
        public final boolean allowVideoMixedMimeTypeAdaptiveness;
        public final boolean allowVideoNonSeamlessAdaptiveness;
        public final boolean constrainAudioChannelCountToDeviceCapabilities;
        public final boolean exceedAudioConstraintsIfNecessary;
        public final boolean exceedRendererCapabilitiesIfNecessary;
        public final boolean exceedVideoConstraintsIfNecessary;
        private final SparseBooleanArray rendererDisabledFlags;
        private final SparseArray<Map<TrackGroupArray, SelectionOverride>> selectionOverrides;
        public final boolean tunnelingEnabled;

        public static final class Builder extends TrackSelectionParameters.Builder {
            private boolean allowAudioMixedChannelCountAdaptiveness;
            private boolean allowAudioMixedDecoderSupportAdaptiveness;
            private boolean allowAudioMixedMimeTypeAdaptiveness;
            private boolean allowAudioMixedSampleRateAdaptiveness;
            private boolean allowAudioNonSeamlessAdaptiveness;
            private boolean allowInvalidateSelectionsOnRendererCapabilitiesChange;
            private boolean allowMultipleAdaptiveSelections;
            private boolean allowVideoMixedDecoderSupportAdaptiveness;
            private boolean allowVideoMixedMimeTypeAdaptiveness;
            private boolean allowVideoNonSeamlessAdaptiveness;
            private boolean constrainAudioChannelCountToDeviceCapabilities;
            private boolean exceedAudioConstraintsIfNecessary;
            private boolean exceedRendererCapabilitiesIfNecessary;
            private boolean exceedVideoConstraintsIfNecessary;
            private final SparseBooleanArray rendererDisabledFlags;
            private final SparseArray<Map<TrackGroupArray, SelectionOverride>> selectionOverrides;
            private boolean tunnelingEnabled;

            public Builder() {
                this.selectionOverrides = new SparseArray();
                this.rendererDisabledFlags = new SparseBooleanArray();
                init();
            }

            private Builder(Parameters r2) {
                super(r2);
                this.exceedVideoConstraintsIfNecessary = r2.exceedVideoConstraintsIfNecessary;
                this.allowVideoMixedMimeTypeAdaptiveness = r2.allowVideoMixedMimeTypeAdaptiveness;
                this.allowVideoNonSeamlessAdaptiveness = r2.allowVideoNonSeamlessAdaptiveness;
                this.allowVideoMixedDecoderSupportAdaptiveness = r2.allowVideoMixedDecoderSupportAdaptiveness;
                this.exceedAudioConstraintsIfNecessary = r2.exceedAudioConstraintsIfNecessary;
                this.allowAudioMixedMimeTypeAdaptiveness = r2.allowAudioMixedMimeTypeAdaptiveness;
                this.allowAudioMixedSampleRateAdaptiveness = r2.allowAudioMixedSampleRateAdaptiveness;
                this.allowAudioMixedChannelCountAdaptiveness = r2.allowAudioMixedChannelCountAdaptiveness;
                this.allowAudioMixedDecoderSupportAdaptiveness = r2.allowAudioMixedDecoderSupportAdaptiveness;
                this.allowAudioNonSeamlessAdaptiveness = r2.allowAudioNonSeamlessAdaptiveness;
                this.constrainAudioChannelCountToDeviceCapabilities = r2.constrainAudioChannelCountToDeviceCapabilities;
                this.exceedRendererCapabilitiesIfNecessary = r2.exceedRendererCapabilitiesIfNecessary;
                this.tunnelingEnabled = r2.tunnelingEnabled;
                this.allowMultipleAdaptiveSelections = r2.allowMultipleAdaptiveSelections;
                this.allowInvalidateSelectionsOnRendererCapabilitiesChange = r2.allowInvalidateSelectionsOnRendererCapabilitiesChange;
                this.selectionOverrides = cloneSelectionOverrides(Parameters.access$000(r2));
                this.rendererDisabledFlags = Parameters.access$100(r2).clone();
            }

            public /* synthetic */ Builder(Parameters r1, AnonymousClass1 r2) {
                this(r1);
            }

            public static /* synthetic */ boolean access$2200(Builder r0) {
                return r0.exceedVideoConstraintsIfNecessary;
            }

            public static /* synthetic */ boolean access$2300(Builder r0) {
                return r0.allowVideoMixedMimeTypeAdaptiveness;
            }

            public static /* synthetic */ boolean access$2400(Builder r0) {
                return r0.allowVideoNonSeamlessAdaptiveness;
            }

            public static /* synthetic */ boolean access$2500(Builder r0) {
                return r0.allowVideoMixedDecoderSupportAdaptiveness;
            }

            public static /* synthetic */ boolean access$2600(Builder r0) {
                return r0.exceedAudioConstraintsIfNecessary;
            }

            public static /* synthetic */ boolean access$2700(Builder r0) {
                return r0.allowAudioMixedMimeTypeAdaptiveness;
            }

            public static /* synthetic */ boolean access$2800(Builder r0) {
                return r0.allowAudioMixedSampleRateAdaptiveness;
            }

            public static /* synthetic */ boolean access$2900(Builder r0) {
                return r0.allowAudioMixedChannelCountAdaptiveness;
            }

            public static /* synthetic */ boolean access$3000(Builder r0) {
                return r0.allowAudioMixedDecoderSupportAdaptiveness;
            }

            public static /* synthetic */ boolean access$3100(Builder r0) {
                return r0.allowAudioNonSeamlessAdaptiveness;
            }

            public static /* synthetic */ boolean access$3200(Builder r0) {
                return r0.constrainAudioChannelCountToDeviceCapabilities;
            }

            public static /* synthetic */ boolean access$3300(Builder r0) {
                return r0.exceedRendererCapabilitiesIfNecessary;
            }

            public static /* synthetic */ boolean access$3400(Builder r0) {
                return r0.tunnelingEnabled;
            }

            public static /* synthetic */ boolean access$3500(Builder r0) {
                return r0.allowMultipleAdaptiveSelections;
            }

            public static /* synthetic */ boolean access$3600(Builder r0) {
                return r0.allowInvalidateSelectionsOnRendererCapabilitiesChange;
            }

            public static /* synthetic */ SparseArray access$3700(Builder r0) {
                return r0.selectionOverrides;
            }

            public static /* synthetic */ SparseBooleanArray access$3800(Builder r0) {
                return r0.rendererDisabledFlags;
            }

            private static SparseArray<Map<TrackGroupArray, SelectionOverride>> cloneSelectionOverrides(SparseArray<Map<TrackGroupArray, SelectionOverride>> r5) {
                SparseArray<Map<TrackGroupArray, SelectionOverride>> r0 = new SparseArray();
                int r1 = 0;
            L4:
                if (r1 >= r5.size()) goto L6;
                r0.put(r5.keyAt(r1), new HashMap(r5.valueAt(r1)));
                r1 = r1 + 1;
                goto L4
            L6:
                return r0;
            }

            private void init() {
                this.exceedVideoConstraintsIfNecessary = true;
                this.allowVideoMixedMimeTypeAdaptiveness = false;
                this.allowVideoNonSeamlessAdaptiveness = true;
                this.allowVideoMixedDecoderSupportAdaptiveness = false;
                this.exceedAudioConstraintsIfNecessary = true;
                this.allowAudioMixedMimeTypeAdaptiveness = false;
                this.allowAudioMixedSampleRateAdaptiveness = false;
                this.allowAudioMixedChannelCountAdaptiveness = false;
                this.allowAudioMixedDecoderSupportAdaptiveness = false;
                this.allowAudioNonSeamlessAdaptiveness = true;
                this.constrainAudioChannelCountToDeviceCapabilities = true;
                this.exceedRendererCapabilitiesIfNecessary = true;
                this.tunnelingEnabled = false;
                this.allowMultipleAdaptiveSelections = true;
                this.allowInvalidateSelectionsOnRendererCapabilitiesChange = false;
            }

            @Override // androidx.media3.common.TrackSelectionParameters.Builder
            public /* bridge */ /* synthetic */ TrackSelectionParameters.Builder addOverride(TrackSelectionOverride r1) {
                return addOverride(r1);
            }

            @Override // androidx.media3.common.TrackSelectionParameters.Builder
            public Builder addOverride(TrackSelectionOverride r1) {
                super.addOverride(r1);
                return this;
            }

            @Override // androidx.media3.common.TrackSelectionParameters.Builder
            public /* bridge */ /* synthetic */ TrackSelectionParameters build() {
                return build();
            }

            @Override // androidx.media3.common.TrackSelectionParameters.Builder
            public Parameters build() {
                return new Parameters(this, null);
            }

            @Override // androidx.media3.common.TrackSelectionParameters.Builder
            public /* bridge */ /* synthetic */ TrackSelectionParameters.Builder clearOverrides() {
                return clearOverrides();
            }

            @Override // androidx.media3.common.TrackSelectionParameters.Builder
            public Builder clearOverrides() {
                super.clearOverrides();
                return this;
            }

            @Override // androidx.media3.common.TrackSelectionParameters.Builder
            public Builder set(TrackSelectionParameters r1) {
                super.set(r1);
                return this;
            }

            @Override // androidx.media3.common.TrackSelectionParameters.Builder
            public /* bridge */ /* synthetic */ TrackSelectionParameters.Builder setDisabledTrackTypes(Set r1) {
                return setDisabledTrackTypes(r1);
            }

            @Override // androidx.media3.common.TrackSelectionParameters.Builder
            public Builder setDisabledTrackTypes(Set<Integer> r1) {
                super.setDisabledTrackTypes(r1);
                return this;
            }

            @Override // androidx.media3.common.TrackSelectionParameters.Builder
            public /* bridge */ /* synthetic */ TrackSelectionParameters.Builder setTrackTypeDisabled(int r1, boolean r2) {
                return setTrackTypeDisabled(r1, r2);
            }

            @Override // androidx.media3.common.TrackSelectionParameters.Builder
            public Builder setTrackTypeDisabled(int r1, boolean r2) {
                super.setTrackTypeDisabled(r1, r2);
                return this;
            }
        }

        static {
            Parameters r0 = new Builder().build();
            DEFAULT = r0;
            DEFAULT_WITHOUT_CONTEXT = r0;
            FIELD_EXCEED_VIDEO_CONSTRAINTS_IF_NECESSARY = Util.intToStringMaxRadix(1000);
            FIELD_ALLOW_VIDEO_MIXED_MIME_TYPE_ADAPTIVENESS = Util.intToStringMaxRadix(WebSocketProtocol.CLOSE_CLIENT_GOING_AWAY);
            FIELD_ALLOW_VIDEO_NON_SEAMLESS_ADAPTIVENESS = Util.intToStringMaxRadix(1002);
            FIELD_EXCEED_AUDIO_CONSTRAINTS_IF_NECESSARY = Util.intToStringMaxRadix(1003);
            FIELD_ALLOW_AUDIO_MIXED_MIME_TYPE_ADAPTIVENESS = Util.intToStringMaxRadix(1004);
            FIELD_ALLOW_AUDIO_MIXED_SAMPLE_RATE_ADAPTIVENESS = Util.intToStringMaxRadix(WebSocketProtocol.CLOSE_NO_STATUS_CODE);
            FIELD_ALLOW_AUDIO_MIXED_CHANNEL_COUNT_ADAPTIVENESS = Util.intToStringMaxRadix(1006);
            FIELD_EXCEED_RENDERER_CAPABILITIES_IF_NECESSARY = Util.intToStringMaxRadix(1007);
            FIELD_TUNNELING_ENABLED = Util.intToStringMaxRadix(1008);
            FIELD_ALLOW_MULTIPLE_ADAPTIVE_SELECTIONS = Util.intToStringMaxRadix(1009);
            FIELD_SELECTION_OVERRIDES_RENDERER_INDICES = Util.intToStringMaxRadix(1010);
            FIELD_SELECTION_OVERRIDES_TRACK_GROUP_ARRAYS = Util.intToStringMaxRadix(1011);
            FIELD_SELECTION_OVERRIDES = Util.intToStringMaxRadix(1012);
            FIELD_RENDERER_DISABLED_INDICES = Util.intToStringMaxRadix(1013);
            FIELD_ALLOW_VIDEO_MIXED_DECODER_SUPPORT_ADAPTIVENESS = Util.intToStringMaxRadix(1014);
            FIELD_ALLOW_AUDIO_MIXED_DECODER_SUPPORT_ADAPTIVENESS = Util.intToStringMaxRadix(1015);
            FIELD_CONSTRAIN_AUDIO_CHANNEL_COUNT_TO_DEVICE_CAPABILITIES = Util.intToStringMaxRadix(1016);
            FIELD_ALLOW_INVALIDATE_SELECTIONS_ON_RENDERER_CAPABILITIES_CHANGE = Util.intToStringMaxRadix(1017);
            FIELD_ALLOW_AUDIO_NON_SEAMLESS_ADAPTIVENESS = Util.intToStringMaxRadix(1018);
        }

        private Parameters(Builder r2) {
            super(r2);
            this.exceedVideoConstraintsIfNecessary = Builder.access$2200(r2);
            this.allowVideoMixedMimeTypeAdaptiveness = Builder.access$2300(r2);
            this.allowVideoNonSeamlessAdaptiveness = Builder.access$2400(r2);
            this.allowVideoMixedDecoderSupportAdaptiveness = Builder.access$2500(r2);
            this.exceedAudioConstraintsIfNecessary = Builder.access$2600(r2);
            this.allowAudioMixedMimeTypeAdaptiveness = Builder.access$2700(r2);
            this.allowAudioMixedSampleRateAdaptiveness = Builder.access$2800(r2);
            this.allowAudioMixedChannelCountAdaptiveness = Builder.access$2900(r2);
            this.allowAudioMixedDecoderSupportAdaptiveness = Builder.access$3000(r2);
            this.allowAudioNonSeamlessAdaptiveness = Builder.access$3100(r2);
            this.constrainAudioChannelCountToDeviceCapabilities = Builder.access$3200(r2);
            this.exceedRendererCapabilitiesIfNecessary = Builder.access$3300(r2);
            this.tunnelingEnabled = Builder.access$3400(r2);
            this.allowMultipleAdaptiveSelections = Builder.access$3500(r2);
            this.allowInvalidateSelectionsOnRendererCapabilitiesChange = Builder.access$3600(r2);
            this.selectionOverrides = Builder.access$3700(r2);
            this.rendererDisabledFlags = Builder.access$3800(r2);
        }

        public /* synthetic */ Parameters(Builder r1, AnonymousClass1 r2) {
            this(r1);
        }

        public static /* synthetic */ SparseArray access$000(Parameters r0) {
            return r0.selectionOverrides;
        }

        public static /* synthetic */ SparseBooleanArray access$100(Parameters r0) {
            return r0.rendererDisabledFlags;
        }

        private static boolean areRendererDisabledFlagsEqual(SparseBooleanArray r4, SparseBooleanArray r5) {
            int r0 = r4.size();
            if (r5.size() == r0) goto L5;
            return false;
        L5:
            int r1 = 0;
        L6:
            if (r1 >= r0) goto L11;
            if (r5.indexOfKey(r4.keyAt(r1)) < 0) goto L9;
            r1 = r1 + 1;
            goto L6
        L9:
            return false;
        L11:
            return true;
        }

        private static boolean areSelectionOverridesEqual(SparseArray<Map<TrackGroupArray, SelectionOverride>> r5, SparseArray<Map<TrackGroupArray, SelectionOverride>> r6) {
            int r0 = r5.size();
            if (r6.size() == r0) goto L5;
            return false;
        L5:
            int r1 = 0;
        L6:
            if (r1 >= r0) goto L14;
            int r3 = r6.indexOfKey(r5.keyAt(r1));
            if (r3 < 0) goto L13;
            if (areSelectionOverridesEqual(r5.valueAt(r1), r6.valueAt(r3)) == false) goto L13;
            r1 = r1 + 1;
        L13:
            return false;
        L14:
            return true;
        }

        private static boolean areSelectionOverridesEqual(Map<TrackGroupArray, SelectionOverride> r4, Map<TrackGroupArray, SelectionOverride> r5) {
            int r0 = r4.size();
            if (r5.size() == r0) goto L5;
            return false;
        L5:
            Iterator<Map.Entry<TrackGroupArray, SelectionOverride>> r42 = r4.entrySet().iterator();
        L7:
            if (r42.hasNext() == false) goto L13;
            Map.Entry<TrackGroupArray, SelectionOverride> r02 = r42.next();
            TrackGroupArray r1 = r02.getKey();
            if (r5.containsKey(r1) == false) goto L12;
            if (Objects.equals(r02.getValue(), r5.get(r1)) == true) goto L7;
        L12:
            return false;
        L13:
            return true;
        }

        private static int[] getKeysFromSparseBooleanArray(SparseBooleanArray r3) {
            int[] r0 = new int[r3.size()];
            int r1 = 0;
        L4:
            if (r1 >= r3.size()) goto L6;
            r0[r1] = r3.keyAt(r1);
            r1 = r1 + 1;
            goto L4
        L6:
            return r0;
        }

        private static void putSelectionOverridesToBundle(Bundle r8, SparseArray<Map<TrackGroupArray, SelectionOverride>> r9) {
            ArrayList r0 = new ArrayList();
            ArrayList r1 = new ArrayList();
            SparseArray r2 = new SparseArray();
            int r3 = 0;
        L4:
            if (r3 >= r9.size()) goto L10;
            int r4 = r9.keyAt(r3);
            Iterator<Map.Entry<TrackGroupArray, SelectionOverride>> r5 = r9.valueAt(r3).entrySet().iterator();
        L7:
            if (r5.hasNext() == false) goto L9;
            Map.Entry<TrackGroupArray, SelectionOverride> r6 = r5.next();
            MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r6.getValue());
            r1.add(r6.getKey());
            r0.add(Integer.valueOf(r4));
            goto L7
        L9:
            r8.putIntArray(FIELD_SELECTION_OVERRIDES_RENDERER_INDICES, Ints.toArray(r0));
            r8.putParcelableArrayList(FIELD_SELECTION_OVERRIDES_TRACK_GROUP_ARRAYS, BundleCollectionUtil.toBundleArrayList(r1, new DefaultTrackSelector$Parameters$$ExternalSyntheticLambda0()));
            r8.putSparseParcelableArray(FIELD_SELECTION_OVERRIDES, BundleCollectionUtil.toBundleSparseArray(r2, new DefaultTrackSelector$Parameters$$ExternalSyntheticLambda1()));
            r3 = r3 + 1;
            goto L4
        }

        @Override // androidx.media3.common.TrackSelectionParameters
        public /* bridge */ /* synthetic */ TrackSelectionParameters.Builder buildUpon() {
            return buildUpon();
        }

        @Override // androidx.media3.common.TrackSelectionParameters
        public Builder buildUpon() {
            return new Builder(this, null);
        }

        @Override // androidx.media3.common.TrackSelectionParameters
        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if (r5 != null) goto L8;
        L47:
            return false;
        L8:
            if (Parameters.class != r5.getClass()) goto L47;
            Parameters r52 = (Parameters) r5;
            if (super.equals(r52) == false) goto L47;
            if (this.exceedVideoConstraintsIfNecessary != r52.exceedVideoConstraintsIfNecessary) goto L47;
            if (this.allowVideoMixedMimeTypeAdaptiveness != r52.allowVideoMixedMimeTypeAdaptiveness) goto L47;
            if (this.allowVideoNonSeamlessAdaptiveness != r52.allowVideoNonSeamlessAdaptiveness) goto L47;
            if (this.allowVideoMixedDecoderSupportAdaptiveness != r52.allowVideoMixedDecoderSupportAdaptiveness) goto L47;
            if (this.exceedAudioConstraintsIfNecessary != r52.exceedAudioConstraintsIfNecessary) goto L47;
            if (this.allowAudioMixedMimeTypeAdaptiveness != r52.allowAudioMixedMimeTypeAdaptiveness) goto L47;
            if (this.allowAudioMixedSampleRateAdaptiveness != r52.allowAudioMixedSampleRateAdaptiveness) goto L47;
            if (this.allowAudioMixedChannelCountAdaptiveness != r52.allowAudioMixedChannelCountAdaptiveness) goto L47;
            if (this.allowAudioMixedDecoderSupportAdaptiveness != r52.allowAudioMixedDecoderSupportAdaptiveness) goto L47;
            if (this.allowAudioNonSeamlessAdaptiveness != r52.allowAudioNonSeamlessAdaptiveness) goto L47;
            if (this.constrainAudioChannelCountToDeviceCapabilities != r52.constrainAudioChannelCountToDeviceCapabilities) goto L47;
            if (this.exceedRendererCapabilitiesIfNecessary != r52.exceedRendererCapabilitiesIfNecessary) goto L47;
            if (this.tunnelingEnabled != r52.tunnelingEnabled) goto L47;
            if (this.allowMultipleAdaptiveSelections != r52.allowMultipleAdaptiveSelections) goto L47;
            if (this.allowInvalidateSelectionsOnRendererCapabilitiesChange != r52.allowInvalidateSelectionsOnRendererCapabilitiesChange) goto L47;
            if (areRendererDisabledFlagsEqual(this.rendererDisabledFlags, r52.rendererDisabledFlags) == false) goto L47;
            if (areSelectionOverridesEqual(this.selectionOverrides, r52.selectionOverrides) == false) goto L47;
            return true;
        }

        public boolean getRendererDisabled(int r1) {
            return this.rendererDisabledFlags.get(r1);
        }

        @Deprecated
        public SelectionOverride getSelectionOverride(int r1, TrackGroupArray r2) {
            Map<TrackGroupArray, SelectionOverride> r0 = this.selectionOverrides.get(r1);
            if (r0 == null) goto L5;
            MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r0.get(r2));
        L5:
            return null;
        }

        @Deprecated
        public boolean hasSelectionOverride(int r1, TrackGroupArray r2) {
            Map<TrackGroupArray, SelectionOverride> r0 = this.selectionOverrides.get(r1);
            if (r0 != null) goto L5;
            return false;
        L5:
            if (r0.containsKey(r2) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // androidx.media3.common.TrackSelectionParameters
        public int hashCode() {
            return ((((((((((((((((((((((((((((((super.hashCode() + 31) * 31) + (this.exceedVideoConstraintsIfNecessary ? 1 : 0)) * 31) + (this.allowVideoMixedMimeTypeAdaptiveness ? 1 : 0)) * 31) + (this.allowVideoNonSeamlessAdaptiveness ? 1 : 0)) * 31) + (this.allowVideoMixedDecoderSupportAdaptiveness ? 1 : 0)) * 31) + (this.exceedAudioConstraintsIfNecessary ? 1 : 0)) * 31) + (this.allowAudioMixedMimeTypeAdaptiveness ? 1 : 0)) * 31) + (this.allowAudioMixedSampleRateAdaptiveness ? 1 : 0)) * 31) + (this.allowAudioMixedChannelCountAdaptiveness ? 1 : 0)) * 31) + (this.allowAudioMixedDecoderSupportAdaptiveness ? 1 : 0)) * 31) + (this.allowAudioNonSeamlessAdaptiveness ? 1 : 0)) * 31) + (this.constrainAudioChannelCountToDeviceCapabilities ? 1 : 0)) * 31) + (this.exceedRendererCapabilitiesIfNecessary ? 1 : 0)) * 31) + (this.tunnelingEnabled ? 1 : 0)) * 31) + (this.allowMultipleAdaptiveSelections ? 1 : 0)) * 31) + (this.allowInvalidateSelectionsOnRendererCapabilitiesChange ? 1 : 0);
        }

        @Override // androidx.media3.common.TrackSelectionParameters
        public Bundle toBundle() {
            Bundle r0 = super.toBundle();
            r0.putBoolean(FIELD_EXCEED_VIDEO_CONSTRAINTS_IF_NECESSARY, this.exceedVideoConstraintsIfNecessary);
            r0.putBoolean(FIELD_ALLOW_VIDEO_MIXED_MIME_TYPE_ADAPTIVENESS, this.allowVideoMixedMimeTypeAdaptiveness);
            r0.putBoolean(FIELD_ALLOW_VIDEO_NON_SEAMLESS_ADAPTIVENESS, this.allowVideoNonSeamlessAdaptiveness);
            r0.putBoolean(FIELD_ALLOW_VIDEO_MIXED_DECODER_SUPPORT_ADAPTIVENESS, this.allowVideoMixedDecoderSupportAdaptiveness);
            r0.putBoolean(FIELD_EXCEED_AUDIO_CONSTRAINTS_IF_NECESSARY, this.exceedAudioConstraintsIfNecessary);
            r0.putBoolean(FIELD_ALLOW_AUDIO_MIXED_MIME_TYPE_ADAPTIVENESS, this.allowAudioMixedMimeTypeAdaptiveness);
            r0.putBoolean(FIELD_ALLOW_AUDIO_MIXED_SAMPLE_RATE_ADAPTIVENESS, this.allowAudioMixedSampleRateAdaptiveness);
            r0.putBoolean(FIELD_ALLOW_AUDIO_MIXED_CHANNEL_COUNT_ADAPTIVENESS, this.allowAudioMixedChannelCountAdaptiveness);
            r0.putBoolean(FIELD_ALLOW_AUDIO_MIXED_DECODER_SUPPORT_ADAPTIVENESS, this.allowAudioMixedDecoderSupportAdaptiveness);
            r0.putBoolean(FIELD_ALLOW_AUDIO_NON_SEAMLESS_ADAPTIVENESS, this.allowAudioNonSeamlessAdaptiveness);
            r0.putBoolean(FIELD_CONSTRAIN_AUDIO_CHANNEL_COUNT_TO_DEVICE_CAPABILITIES, this.constrainAudioChannelCountToDeviceCapabilities);
            r0.putBoolean(FIELD_EXCEED_RENDERER_CAPABILITIES_IF_NECESSARY, this.exceedRendererCapabilitiesIfNecessary);
            r0.putBoolean(FIELD_TUNNELING_ENABLED, this.tunnelingEnabled);
            r0.putBoolean(FIELD_ALLOW_MULTIPLE_ADAPTIVE_SELECTIONS, this.allowMultipleAdaptiveSelections);
            r0.putBoolean(FIELD_ALLOW_INVALIDATE_SELECTIONS_ON_RENDERER_CAPABILITIES_CHANGE, this.allowInvalidateSelectionsOnRendererCapabilitiesChange);
            putSelectionOverridesToBundle(r0, this.selectionOverrides);
            r0.putIntArray(FIELD_RENDERER_DISABLED_INDICES, getKeysFromSparseBooleanArray(this.rendererDisabledFlags));
            return r0;
        }
    }

    public static final class SelectionOverride {
    }

    public static class SpatializerWrapperV32 {
        private final Handler handler;
        private final Spatializer$OnSpatializerStateChangedListener listener;
        private final boolean spatializationSupported;
        private final Spatializer spatializer;

        /* renamed from: androidx.media3.exoplayer.trackselection.DefaultTrackSelector$SpatializerWrapperV32$1, reason: invalid class name */
        public class AnonymousClass1 implements Spatializer$OnSpatializerStateChangedListener {
            final /* synthetic */ SpatializerWrapperV32 this$0;
            final /* synthetic */ DefaultTrackSelector val$defaultTrackSelector;

            public AnonymousClass1(SpatializerWrapperV32 r1, DefaultTrackSelector r2) {
                this.this$0 = r1;
                this.val$defaultTrackSelector = r2;
            }

            public void onSpatializerAvailableChanged(Spatializer r1, boolean r2) {
                DefaultTrackSelector.access$4700(this.val$defaultTrackSelector);
            }

            public void onSpatializerEnabledChanged(Spatializer r1, boolean r2) {
                DefaultTrackSelector.access$4700(this.val$defaultTrackSelector);
            }
        }

        public SpatializerWrapperV32(Context r3, DefaultTrackSelector r4, Boolean r5) {
            if (r3 != null) goto L5;
            AudioManager r32 = null;
        L6:
            boolean r1 = false;
            if (r32 == null) goto L17;
            if (r5 != null) goto L10;
        L12:
            Spatializer r33 = r32.getSpatializer();
            this.spatializer = r33;
            if (r33.getImmersiveAudioLevel() == 0) goto L15;
            r1 = true;
        L15:
            this.spatializationSupported = r1;
            AnonymousClass1 r52 = new AnonymousClass1(this, r4);
            this.listener = r52;
            Handler r42 = new Handler((Looper) Assertions.checkStateNotNull(Looper.myLooper()));
            this.handler = r42;
            Objects.requireNonNull(r42);
            r33.addOnSpatializerStateChangedListener(new DefaultAudioSink$StreamEventCallbackV29$$ExternalSyntheticLambda0(r42), r52);
            return;
        L10:
            if (r5.booleanValue() == false) goto L12;
        L17:
            this.spatializer = null;
            this.spatializationSupported = false;
            this.handler = null;
            this.listener = null;
            return;
        L5:
            r32 = AudioManagerCompat.getAudioManager(r3);
            goto L6
        }

        public boolean canBeSpatialized(AudioAttributes r5, Format r6) {
            if (Objects.equals(r6.sampleMimeType, "audio/eac3-joc") == false) goto L8;
            int r0 = r6.channelCount;
            if (r0 != 16) goto L20;
            r0 = 12;
        L20:
            int r02 = Util.getAudioTrackChannelConfig(r0);
            if (r02 != 0) goto L24;
            return false;
        L24:
            AudioFormat.Builder r03 = new AudioFormat.Builder().setEncoding(2).setChannelMask(r02);
            int r62 = r6.sampleRate;
            if (r62 == (-1)) goto L28;
            r03.setSampleRate(r62);
        L28:
            return DefaultTrackSelector$SpatializerWrapperV32$$ExternalSyntheticApiModelOutline0.m(Assertions.checkNotNull(this.spatializer)).canBeSpatialized(r5.getAudioAttributesV21().audioAttributes, r03.build());
        L8:
            if (Objects.equals(r6.sampleMimeType, "audio/iamf") == false) goto L12;
            r0 = r6.channelCount;
            if (r0 != (-1)) goto L20;
            r0 = 6;
            goto L20
        L12:
            boolean r04 = Objects.equals(r6.sampleMimeType, "audio/ac4");
            int r2 = r6.channelCount;
            if (r04 == true) goto L15;
        L19:
            r0 = r2;
            goto L20
        L15:
            if (r2 != 18) goto L17;
        L18:
            r0 = 24;
            goto L20
        L17:
            if (r2 != 21) goto L19;
            goto L18
        }

        public boolean isAvailable() {
            return DefaultTrackSelector$SpatializerWrapperV32$$ExternalSyntheticApiModelOutline0.m(Assertions.checkNotNull(this.spatializer)).isAvailable();
        }

        public boolean isEnabled() {
            return DefaultTrackSelector$SpatializerWrapperV32$$ExternalSyntheticApiModelOutline0.m(Assertions.checkNotNull(this.spatializer)).isEnabled();
        }

        public boolean isSpatializationSupported() {
            return this.spatializationSupported;
        }

        public void release() {
            Spatializer r0 = this.spatializer;
            if (r0 == null) goto L11;
            Spatializer$OnSpatializerStateChangedListener r1 = this.listener;
            if (r1 != null) goto L7;
            return;
        L7:
            if (this.handler == null) goto L13;
            r0.removeOnSpatializerStateChangedListener(r1);
            this.handler.removeCallbacksAndMessages(null);
            return;
        L13:
            return;
        }
    }

    public static final class TextTrackInfo extends TrackInfo<TextTrackInfo> implements Comparable<TextTrackInfo> {
        private final boolean hasCaptionRoleFlags;
        private final boolean isDefault;
        private final boolean isForced;
        private final boolean isWithinRendererCapabilities;
        private final int preferredLanguageIndex;
        private final int preferredLanguageScore;
        private final int preferredRoleFlagsScore;
        private final int selectedAudioLanguageScore;
        private final int selectionEligibility;

        public TextTrackInfo(int r5, TrackGroup r6, int r7, Parameters r8, int r9, String r10, String r11) {
            super(r5, r6, r7);
            int r52 = 0;
            this.isWithinRendererCapabilities = RendererCapabilities.isFormatSupported(r9, false);
            int r62 = this.format.selectionFlags & (~r8.ignoredTextSelectionFlags);
            if ((r62 & 1) == 0) goto L5;
            boolean r72 = true;
        L6:
            this.isDefault = r72;
            if ((r62 & 2) == 0) goto L9;
            boolean r63 = true;
        L10:
            this.isForced = r63;
            if (r11 == null) goto L14;
            ImmutableList<String> r64 = ImmutableList.of(r11);
        L17:
            int r73 = 0;
        L19:
            if (r73 >= r64.size()) goto L24;
            int r1 = DefaultTrackSelector.getFormatLanguageScore(this.format, r64.get(r73), r8.selectUndeterminedTextLanguage);
            if (r1 > 0) goto L25;
            r73 = r73 + 1;
        L25:
            this.preferredLanguageIndex = r73;
            this.preferredLanguageScore = r1;
            if (r11 == null) goto L28;
            int r74 = 1088;
        L29:
            int r75 = DefaultTrackSelector.access$4200(this.format.roleFlags, r74);
            this.preferredRoleFlagsScore = r75;
            if ((1088 & this.format.roleFlags) == 0) goto L32;
            boolean r65 = true;
        L33:
            this.hasCaptionRoleFlags = r65;
            if (DefaultTrackSelector.normalizeUndeterminedLanguageToNull(r10) != null) goto L36;
            boolean r66 = true;
        L37:
            int r67 = DefaultTrackSelector.getFormatLanguageScore(this.format, r10, r66);
            this.selectedAudioLanguageScore = r67;
            if (r1 <= 0) goto L40;
        L49:
            boolean r68 = true;
        L51:
            if (RendererCapabilities.isFormatSupported(r9, r8.exceedRendererCapabilitiesIfNecessary) == false) goto L54;
            if (r68 == false) goto L54;
            r52 = 1;
        L54:
            this.selectionEligibility = r52;
            return;
        L40:
            if (r8.preferredTextLanguages.isEmpty() == false) goto L43;
            if (r75 > 0) goto L49;
        L43:
            if (this.isDefault == true) goto L49;
            if (this.isForced == false) goto L48;
            if (r67 > 0) goto L49;
        L48:
            r68 = false;
            goto L51
        L36:
            r66 = false;
            goto L37
        L32:
            r65 = false;
            goto L33
        L28:
            r74 = r8.preferredTextRoleFlags;
            goto L29
        L24:
            r73 = Integer.MAX_VALUE;
            r1 = 0;
            goto L25
        L14:
            if (r8.preferredTextLanguages.isEmpty() == false) goto L16;
            r64 = ImmutableList.of(_UrlKt.FRAGMENT_ENCODE_SET);
            goto L17
        L16:
            r64 = r8.preferredTextLanguages;
            goto L17
        L9:
            r63 = false;
            goto L10
        L5:
            r72 = false;
            goto L6
        }

        public static int compareSelections(List<TextTrackInfo> r1, List<TextTrackInfo> r2) {
            return r1.get(0).compareTo2(r2.get(0));
        }

        public static ImmutableList<TextTrackInfo> createForTrackGroup(int r10, TrackGroup r11, Parameters r12, int[] r13, String r14, String r15) {
            ImmutableList.Builder r0 = ImmutableList.builder();
            int r5 = 0;
        L4:
            if (r5 >= r11.length) goto L7;
            r0.add(new TextTrackInfo(r10, r11, r5, r12, r13[r5], r14, r15));
            r5 = r5 + 1;
            goto L4
        L7:
            return r0.build();
        }

        /* renamed from: compareTo, reason: avoid collision after fix types in other method */
        public int compareTo2(TextTrackInfo r5) {
            ComparisonChain r0 = ComparisonChain.start().compareFalseFirst(this.isWithinRendererCapabilities, r5.isWithinRendererCapabilities).compare(Integer.valueOf(this.preferredLanguageIndex), Integer.valueOf(r5.preferredLanguageIndex), Ordering.natural().reverse()).compare(this.preferredLanguageScore, r5.preferredLanguageScore).compare(this.preferredRoleFlagsScore, r5.preferredRoleFlagsScore).compareFalseFirst(this.isDefault, r5.isDefault);
            Boolean r1 = Boolean.valueOf(this.isForced);
            Boolean r2 = Boolean.valueOf(r5.isForced);
            if (this.preferredLanguageScore != 0) goto L5;
            Ordering r3 = Ordering.natural();
        L6:
            ComparisonChain r02 = r0.compare(r1, r2, r3).compare(this.selectedAudioLanguageScore, r5.selectedAudioLanguageScore);
            if (this.preferredRoleFlagsScore != 0) goto L10;
            r02 = r02.compareTrueFirst(this.hasCaptionRoleFlags, r5.hasCaptionRoleFlags);
        L10:
            return r02.result();
        L5:
            r3 = Ordering.natural().reverse();
            goto L6
        }

        @Override // java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(TextTrackInfo r1) {
            return compareTo2(r1);
        }

        @Override // androidx.media3.exoplayer.trackselection.DefaultTrackSelector.TrackInfo
        public int getSelectionEligibility() {
            return this.selectionEligibility;
        }

        public boolean isCompatibleForAdaptationWith(TextTrackInfo r1) {
            return false;
        }

        @Override // androidx.media3.exoplayer.trackselection.DefaultTrackSelector.TrackInfo
        public /* bridge */ /* synthetic */ boolean isCompatibleForAdaptationWith(TrackInfo r1) {
            return isCompatibleForAdaptationWith((TextTrackInfo) r1);
        }
    }

    public static abstract class TrackInfo<T extends TrackInfo<T>> {
        public final Format format;
        public final int rendererIndex;
        public final TrackGroup trackGroup;
        public final int trackIndex;

        public interface Factory<T extends TrackInfo<T>> {
            List<T> create(int r1, TrackGroup r2, int[] r3);
        }

        public TrackInfo(int r1, TrackGroup r2, int r3) {
            this.rendererIndex = r1;
            this.trackGroup = r2;
            this.trackIndex = r3;
            this.format = r2.getFormat(r3);
        }

        public abstract int getSelectionEligibility();

        public abstract boolean isCompatibleForAdaptationWith(T r1);
    }

    public static final class VideoTrackInfo extends TrackInfo<VideoTrackInfo> {
        private final boolean allowMixedMimeTypes;
        private final int bitrate;
        private final int codecPreferenceScore;
        private final boolean hasMainOrNoRoleFlag;
        private final boolean hasReasonableFrameRate;
        private final boolean isWithinMaxConstraints;
        private final boolean isWithinMinConstraints;
        private final boolean isWithinRendererCapabilities;
        private final Parameters parameters;
        private final int pixelCount;
        private final int preferredLanguageIndex;
        private final int preferredLanguageScore;
        private final int preferredMimeTypeMatchIndex;
        private final int preferredRoleFlagsScore;
        private final int selectedAudioLanguageScore;
        private final int selectionEligibility;
        private final boolean usesHardwareAcceleration;
        private final boolean usesPrimaryDecoder;

        /* renamed from: $r8$lambda$G8uVDNHx35S9o7viZ4i-yMcwZzU, reason: not valid java name */
        public static /* synthetic */ int m846$r8$lambda$G8uVDNHx35S9o7viZ4iyMcwZzU(VideoTrackInfo r0, VideoTrackInfo r1) {
            return compareQualityPreferences(r0, r1);
        }

        public static /* synthetic */ int $r8$lambda$mN6XX8i7M4d49ufL_31PyFTAA68(VideoTrackInfo r0, VideoTrackInfo r1) {
            return compareNonQualityPreferences(r0, r1);
        }

        public VideoTrackInfo(int r5, TrackGroup r6, int r7, Parameters r8, int r9, String r10, int r11, boolean r12) {
            super(r5, r6, r7);
            this.parameters = r8;
            if (r8.allowVideoNonSeamlessAdaptiveness == false) goto L5;
            int r52 = 24;
        L6:
            boolean r72 = true;
            if (r8.allowVideoMixedMimeTypeAdaptiveness == true) goto L9;
        L11:
            boolean r62 = false;
        L12:
            this.allowMixedMimeTypes = r62;
            if (r12 == false) goto L31;
            Format r1 = this.format;
            int r2 = r1.width;
            if (r2 != (-1)) goto L17;
        L18:
            int r22 = r1.height;
            if (r22 != (-1)) goto L21;
        L22:
            float r23 = r1.frameRate;
            if (r23 != (-1.0f)) goto L25;
        L26:
            int r13 = r1.bitrate;
            if (r13 != (-1)) goto L29;
        L30:
            boolean r14 = true;
        L32:
            this.isWithinMaxConstraints = r14;
            if (r12 == false) goto L51;
            Format r122 = this.format;
            int r15 = r122.width;
            if (r15 != (-1)) goto L37;
        L38:
            int r16 = r122.height;
            if (r16 != (-1)) goto L41;
        L42:
            float r17 = r122.frameRate;
            if (r17 != (-1.0f)) goto L45;
        L46:
            int r123 = r122.bitrate;
            if (r123 != (-1)) goto L49;
        L50:
            boolean r112 = true;
        L52:
            this.isWithinMinConstraints = r112;
            this.isWithinRendererCapabilities = RendererCapabilities.isFormatSupported(r9, false);
            Format r113 = this.format;
            float r124 = r113.frameRate;
            if (r124 != (-1.0f)) goto L55;
        L57:
            boolean r63 = false;
        L58:
            this.hasReasonableFrameRate = r63;
            this.bitrate = r113.bitrate;
            this.pixelCount = r113.getPixelCount();
            int r64 = 0;
        L59:
            int r125 = Integer.MAX_VALUE;
            if (r64 >= r8.preferredVideoLanguages.size()) goto L65;
            int r114 = DefaultTrackSelector.getFormatLanguageScore(this.format, r8.preferredVideoLanguages.get(r64), false);
            if (r114 > 0) goto L66;
            r64 = r64 + 1;
        L66:
            this.preferredLanguageIndex = r64;
            this.preferredLanguageScore = r114;
            this.preferredRoleFlagsScore = DefaultTrackSelector.access$4200(this.format.roleFlags, r8.preferredVideoRoleFlags);
            int r65 = this.format.roleFlags;
            if (r65 != 0) goto L69;
        L72:
            boolean r66 = true;
        L73:
            this.hasMainOrNoRoleFlag = r66;
            if (DefaultTrackSelector.normalizeUndeterminedLanguageToNull(r10) != null) goto L76;
            boolean r67 = true;
        L77:
            this.selectedAudioLanguageScore = DefaultTrackSelector.getFormatLanguageScore(this.format, r10, r67);
            int r68 = 0;
        L79:
            if (r68 >= r8.preferredVideoMimeTypes.size()) goto L86;
            String r102 = this.format.sampleMimeType;
            if (r102 == null) goto L85;
            if (r102.equals(r8.preferredVideoMimeTypes.get(r68)) == false) goto L85;
            r125 = r68;
        L85:
            r68 = r68 + 1;
        L86:
            this.preferredMimeTypeMatchIndex = r125;
            if (RendererCapabilities.getDecoderSupport(r9) != 128) goto L89;
            boolean r69 = true;
        L90:
            this.usesPrimaryDecoder = r69;
            if (RendererCapabilities.getHardwareAccelerationSupport(r9) == 64) goto L94;
            r72 = false;
        L94:
            this.usesHardwareAcceleration = r72;
            this.codecPreferenceScore = DefaultTrackSelector.access$4300(this.format.sampleMimeType);
            this.selectionEligibility = evaluateSelectionEligibility(r9, r52);
            return;
        L89:
            r69 = false;
            goto L90
        L76:
            r67 = false;
            goto L77
        L69:
            if ((r65 & 1) != 0) goto L72;
            r66 = false;
            goto L73
        L65:
            r64 = Integer.MAX_VALUE;
            r114 = 0;
            goto L66
        L55:
            if (r124 < 10.0f) goto L57;
            r63 = true;
            goto L58
        L49:
            if (r123 < r8.minVideoBitrate) goto L51;
        L45:
            if (r17 < r8.minVideoFrameRate) goto L51;
        L41:
            if (r16 < r8.minVideoHeight) goto L51;
        L37:
            if (r15 >= r8.minVideoWidth) goto L38;
        L51:
            r112 = false;
            goto L52
        L29:
            if (r13 > r8.maxVideoBitrate) goto L31;
        L25:
            if (r23 > r8.maxVideoFrameRate) goto L31;
        L21:
            if (r22 > r8.maxVideoHeight) goto L31;
        L17:
            if (r2 <= r8.maxVideoWidth) goto L18;
        L31:
            r14 = false;
            goto L32
        L9:
            if ((r11 & r52) == 0) goto L11;
            r62 = true;
            goto L12
        L5:
            r52 = 16;
            goto L6
        }

        private static int compareNonQualityPreferences(VideoTrackInfo r4, VideoTrackInfo r5) {
            ComparisonChain r0 = ComparisonChain.start().compareFalseFirst(r4.isWithinRendererCapabilities, r5.isWithinRendererCapabilities).compare(Integer.valueOf(r4.preferredLanguageIndex), Integer.valueOf(r5.preferredLanguageIndex), Ordering.natural().reverse()).compare(r4.preferredLanguageScore, r5.preferredLanguageScore).compare(r4.preferredRoleFlagsScore, r5.preferredRoleFlagsScore).compareFalseFirst(r4.hasMainOrNoRoleFlag, r5.hasMainOrNoRoleFlag).compare(r4.selectedAudioLanguageScore, r5.selectedAudioLanguageScore).compareFalseFirst(r4.hasReasonableFrameRate, r5.hasReasonableFrameRate).compareFalseFirst(r4.isWithinMaxConstraints, r5.isWithinMaxConstraints).compareFalseFirst(r4.isWithinMinConstraints, r5.isWithinMinConstraints).compare(Integer.valueOf(r4.preferredMimeTypeMatchIndex), Integer.valueOf(r5.preferredMimeTypeMatchIndex), Ordering.natural().reverse()).compareFalseFirst(r4.usesPrimaryDecoder, r5.usesPrimaryDecoder).compareFalseFirst(r4.usesHardwareAcceleration, r5.usesHardwareAcceleration);
            if (r4.usesPrimaryDecoder == false) goto L8;
            if (r4.usesHardwareAcceleration == false) goto L8;
            r0 = r0.compare(r4.codecPreferenceScore, r5.codecPreferenceScore);
        L8:
            return r0.result();
        }

        private static int compareQualityPreferences(VideoTrackInfo r5, VideoTrackInfo r6) {
            if (r5.isWithinMaxConstraints == true) goto L5;
        L7:
            Ordering r0 = DefaultTrackSelector.access$4400().reverse();
        L8:
            ComparisonChain r1 = ComparisonChain.start();
            if (r5.parameters.forceLowestBitrate == false) goto L12;
            r1 = r1.compare(Integer.valueOf(r5.bitrate), Integer.valueOf(r6.bitrate), DefaultTrackSelector.access$4400().reverse());
        L12:
            return r1.compare(Integer.valueOf(r5.pixelCount), Integer.valueOf(r6.pixelCount), r0).compare(Integer.valueOf(r5.bitrate), Integer.valueOf(r6.bitrate), r0).result();
        L5:
            if (r5.isWithinRendererCapabilities == false) goto L7;
            r0 = DefaultTrackSelector.access$4400();
            goto L8
        }

        public static int compareSelections(List<VideoTrackInfo> r4, List<VideoTrackInfo> r5) {
            return ComparisonChain.start().compare((VideoTrackInfo) Collections.max(r4, new DefaultTrackSelector$VideoTrackInfo$$ExternalSyntheticLambda0()), (VideoTrackInfo) Collections.max(r5, new DefaultTrackSelector$VideoTrackInfo$$ExternalSyntheticLambda0()), new DefaultTrackSelector$VideoTrackInfo$$ExternalSyntheticLambda0()).compare(r4.size(), r5.size()).compare((VideoTrackInfo) Collections.max(r4, new DefaultTrackSelector$VideoTrackInfo$$ExternalSyntheticLambda1()), (VideoTrackInfo) Collections.max(r5, new DefaultTrackSelector$VideoTrackInfo$$ExternalSyntheticLambda1()), new DefaultTrackSelector$VideoTrackInfo$$ExternalSyntheticLambda1()).result();
        }

        public static ImmutableList<VideoTrackInfo> createForTrackGroup(int r12, TrackGroup r13, Parameters r14, int[] r15, String r16, int r17, Point r18) {
            if (r18 == null) goto L5;
            int r1 = r18.x;
        L6:
            if (r18 == null) goto L8;
            int r0 = r18.y;
        L9:
            int r02 = DefaultTrackSelector.access$4100(r13, r1, r0, r14.viewportOrientationMayChange);
            ImmutableList.Builder r19 = ImmutableList.builder();
            int r6 = 0;
        L11:
            if (r6 >= r13.length) goto L22;
            int r3 = r13.getFormat(r6).getPixelCount();
            if (r02 != Integer.MAX_VALUE) goto L15;
        L19:
            boolean r11 = true;
        L20:
            r19.add(new VideoTrackInfo(r12, r13, r6, r14, r15[r6], r16, r17, r11));
            r6 = r6 + 1;
            goto L11
        L15:
            if (r3 == (-1)) goto L18;
            if (r3 <= r02) goto L19;
        L18:
            r11 = false;
            goto L20
        L22:
            return r19.build();
        L8:
            r0 = r14.viewportHeight;
            goto L9
        L5:
            r1 = r14.viewportWidth;
            goto L6
        }

        private int evaluateSelectionEligibility(int r3, int r4) {
            if ((this.format.roleFlags & 16384) == 0) goto L6;
            return 0;
        L6:
            if (RendererCapabilities.isFormatSupported(r3, this.parameters.exceedRendererCapabilitiesIfNecessary) == true) goto L9;
            return 0;
        L9:
            if (this.isWithinMaxConstraints == true) goto L14;
            if (this.parameters.exceedVideoConstraintsIfNecessary == true) goto L14;
            return 0;
        L14:
            if (RendererCapabilities.isFormatSupported(r3, false) == true) goto L16;
            return 1;
        L16:
            if (this.isWithinMinConstraints == true) goto L18;
            return 1;
        L18:
            if (this.isWithinMaxConstraints == true) goto L20;
            return 1;
        L20:
            if (this.format.bitrate == (-1)) goto L33;
            Parameters r2 = this.parameters;
            if (r2.forceHighestSupportedBitrate == false) goto L24;
            return 1;
        L24:
            if (r2.forceLowestBitrate == false) goto L26;
            return 1;
        L26:
            if ((r3 & r4) == 0) goto L36;
            return 2;
        L36:
            return 1;
        L33:
            return 1;
        }

        @Override // androidx.media3.exoplayer.trackselection.DefaultTrackSelector.TrackInfo
        public int getSelectionEligibility() {
            return this.selectionEligibility;
        }

        @Override // androidx.media3.exoplayer.trackselection.DefaultTrackSelector.TrackInfo
        public /* bridge */ /* synthetic */ boolean isCompatibleForAdaptationWith(TrackInfo r1) {
            return isCompatibleForAdaptationWith((VideoTrackInfo) r1);
        }

        public boolean isCompatibleForAdaptationWith(VideoTrackInfo r3) {
            if (this.allowMixedMimeTypes == true) goto L7;
            if (Objects.equals(this.format.sampleMimeType, r3.format.sampleMimeType) == true) goto L7;
            return false;
        L7:
            if (this.parameters.allowVideoMixedDecoderSupportAdaptiveness == false) goto L9;
            return true;
        L9:
            if (this.usesPrimaryDecoder == r3.usesPrimaryDecoder) goto L11;
            return false;
        L11:
            if (this.usesHardwareAcceleration == r3.usesHardwareAcceleration) goto L19;
            return false;
        L19:
            return true;
        }
    }

    public static /* synthetic */ List $r8$lambda$2T_4iSWD9qbYWNCmj_m_CfgfChw(DefaultTrackSelector r2, Parameters r3, boolean r4, int[] r5, int r6, TrackGroup r7, int[] r8) {
        r2.getClass();
        return AudioTrackInfo.createForTrackGroup(r6, r7, r3, r8, r4, new DefaultTrackSelector$$ExternalSyntheticLambda9(r2, r3), r5[r6]);
    }

    public static /* synthetic */ List $r8$lambda$JU1mh3M1J_D61mV1BweXBVb6UzI(Parameters r0, int r1, TrackGroup r2, int[] r3) {
        return ImageTrackInfo.createForTrackGroup(r1, r2, r0, r3);
    }

    /* renamed from: $r8$lambda$ZyLy63-p4VGAMnoS-urBI1BFReI, reason: not valid java name */
    public static /* synthetic */ boolean m844$r8$lambda$ZyLy63p4VGAMnoSurBI1BFReI(DefaultTrackSelector r0, Parameters r1, Format r2) {
        return r0.isAudioFormatWithinAudioChannelCountConstraints(r2, r1);
    }

    public static /* synthetic */ List $r8$lambda$mEmKQSusMDZEMY2TdLalqESnSC0(Parameters r1, String r2, int[] r3, Point r4, int r5, TrackGroup r6, int[] r7) {
        return VideoTrackInfo.createForTrackGroup(r5, r6, r1, r7, r2, r3[r5], r4);
    }

    /* renamed from: $r8$lambda$wDADG2-vDKhCzf_8d2quwv3GXqM, reason: not valid java name */
    public static /* synthetic */ int m845$r8$lambda$wDADG2vDKhCzf_8d2quwv3GXqM(Integer r2, Integer r3) {
        if (r2.intValue() != (-1)) goto L10;
        if (r3.intValue() != (-1)) goto L8;
        return 0;
    L8:
        return -1;
    L10:
        if (r3.intValue() != (-1)) goto L14;
        return 1;
    L14:
        return r2.intValue() - r3.intValue();
    }

    public static /* synthetic */ List $r8$lambda$xEvqaQbm4_pjUM81V7bbZMVIUic(Parameters r1, String r2, String r3, int r4, TrackGroup r5, int[] r6) {
        return TextTrackInfo.createForTrackGroup(r4, r5, r1, r6, r2, r3);
    }

    static {
        FORMAT_VALUE_ORDERING = Ordering.from(new DefaultTrackSelector$$ExternalSyntheticLambda0());
    }

    public DefaultTrackSelector(Context r2) {
        this(r2, new AdaptiveTrackSelection.Factory());
    }

    public DefaultTrackSelector(Context r1, TrackSelectionParameters r2, ExoTrackSelection.Factory r3) {
        this(r2, r3, r1);
    }

    public DefaultTrackSelector(Context r2, ExoTrackSelection.Factory r3) {
        this(r2, Parameters.DEFAULT, r3);
    }

    private DefaultTrackSelector(TrackSelectionParameters r2, ExoTrackSelection.Factory r3, Context r4) {
        this.lock = new Object();
        if (r4 == null) goto L5;
        Context r0 = r4.getApplicationContext();
    L6:
        this.context = r0;
        this.trackSelectionFactory = r3;
        if ((r2 instanceof Parameters) == false) goto L9;
        this.parameters = (Parameters) r2;
    L10:
        this.audioAttributes = AudioAttributes.DEFAULT;
        if (this.parameters.constrainAudioChannelCountToDeviceCapabilities == false) goto L15;
        if (r4 != null) goto L16;
        Log.w("DefaultTrackSelector", "Audio channel count constraints cannot be applied without reference to Context. Build the track selector instance with one of the non-deprecated constructors that take a Context argument.");
        return;
    L16:
        return;
    L15:
        return;
    L9:
        this.parameters = Parameters.DEFAULT.buildUpon().set(r2).build();
        goto L10
    L5:
        r0 = null;
        goto L6
    }

    public static /* synthetic */ int access$4100(TrackGroup r0, int r1, int r2, boolean r3) {
        return getMaxVideoPixelsToRetainForViewport(r0, r1, r2, r3);
    }

    public static /* synthetic */ int access$4200(int r0, int r1) {
        return getRoleFlagMatchScore(r0, r1);
    }

    public static /* synthetic */ int access$4300(String r0) {
        return getVideoCodecPreferenceScore(r0);
    }

    public static /* synthetic */ Ordering access$4400() {
        return FORMAT_VALUE_ORDERING;
    }

    public static /* synthetic */ boolean access$4500(Format r0) {
        return isObjectBasedAudio(r0);
    }

    public static /* synthetic */ boolean access$4600(Parameters r0, int r1, Format r2) {
        return rendererSupportsOffload(r0, r1, r2);
    }

    public static /* synthetic */ void access$4700(DefaultTrackSelector r0) {
        r0.maybeInvalidateForAudioChannelCountConstraints();
    }

    private static void applyLegacyRendererOverrides(MappingTrackSelector.MappedTrackInfo r4, Parameters r5, ExoTrackSelection.Definition[] r6) {
        int r0 = r4.getRendererCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L9;
        TrackGroupArray r2 = r4.getTrackGroups(r1);
        if (r5.hasSelectionOverride(r1, r2) == false) goto L8;
        r5.getSelectionOverride(r1, r2);
        r6[r1] = null;
    L8:
        r1 = r1 + 1;
        goto L3
    }

    private static void applyTrackSelectionOverrides(MappingTrackSelector.MappedTrackInfo r5, TrackSelectionParameters r6, ExoTrackSelection.Definition[] r7) {
        int r0 = r5.getRendererCount();
        HashMap r1 = new HashMap();
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L5;
        collectTrackSelectionOverrides(r5.getTrackGroups(r3), r6, r1);
        r3 = r3 + 1;
        goto L3
    L5:
        collectTrackSelectionOverrides(r5.getUnmappedTrackGroups(), r6, r1);
    L6:
        if (r2 >= r0) goto L18;
        TrackSelectionOverride r62 = (TrackSelectionOverride) r1.get(Integer.valueOf(r5.getRendererType(r2)));
        if (r62 == null) goto L17;
        if (r62.trackIndices.isEmpty() == false) goto L13;
    L15:
        ExoTrackSelection.Definition r32 = null;
    L16:
        r7[r2] = r32;
        goto L17
    L13:
        if (r5.getTrackGroups(r2).indexOf(r62.mediaTrackGroup) == (-1)) goto L15;
        r32 = new ExoTrackSelection.Definition(r62.mediaTrackGroup, Ints.toArray(r62.trackIndices));
    L17:
        r2 = r2 + 1;
        goto L6
    }

    private static void collectTrackSelectionOverrides(TrackGroupArray r3, TrackSelectionParameters r4, Map<Integer, TrackSelectionOverride> r5) {
        int r0 = 0;
    L4:
        if (r0 >= r3.length) goto L16;
        TrackGroup r1 = r3.get(r0);
        TrackSelectionOverride r12 = r4.overrides.get(r1);
        if (r12 == null) goto L15;
        TrackSelectionOverride r2 = r5.get(Integer.valueOf(r12.getType()));
        if (r2 != null) goto L11;
    L14:
        r5.put(Integer.valueOf(r12.getType()), r12);
        goto L15
    L11:
        if (r2.trackIndices.isEmpty() == false) goto L15;
        if (r12.trackIndices.isEmpty() == false) goto L14;
    L15:
        r0 = r0 + 1;
        goto L4
    }

    public static int getFormatLanguageScore(Format r1, String r2, boolean r3) {
        if (TextUtils.isEmpty(r2) == false) goto L5;
    L8:
        String r22 = normalizeUndeterminedLanguageToNull(r2);
        String r12 = normalizeUndeterminedLanguageToNull(r1.language);
        if (r12 == null) goto L24;
        if (r22 == null) goto L24;
        if (r12.startsWith(r22) == false) goto L15;
        return 3;
    L15:
        if (r22.startsWith(r12) == false) goto L18;
        return 3;
    L18:
        if (Util.splitAtFirst(r12, "-")[0].equals(Util.splitAtFirst(r22, "-")[0]) == false) goto L21;
        return 2;
    L21:
        return 0;
    L24:
        if (r3 == false) goto L28;
        if (r12 != null) goto L28;
        return 1;
    L28:
        return 0;
    L5:
        if (r2.equals(r1.language) == false) goto L8;
        return 4;
    }

    private static int getMaxVideoPixelsToRetainForViewport(TrackGroup r8, int r9, int r10, boolean r11) {
        int r0 = Integer.MAX_VALUE;
        if (r9 == Integer.MAX_VALUE) goto L20;
        if (r10 == Integer.MAX_VALUE) goto L20;
        int r1 = 0;
    L8:
        if (r1 >= r8.length) goto L20;
        Format r2 = r8.getFormat(r1);
        int r3 = r2.width;
        if (r3 <= 0) goto L19;
        int r4 = r2.height;
        if (r4 <= 0) goto L19;
        Point r32 = TrackSelectionUtil.getMaxVideoSizeInViewport(r11, r9, r10, r3, r4);
        int r42 = r2.width;
        int r22 = r2.height;
        int r5 = r42 * r22;
        if (r42 < ((int) (r32.x * 0.98f))) goto L19;
        if (r22 < ((int) (r32.y * 0.98f))) goto L19;
        if (r5 >= r0) goto L19;
        r0 = r5;
    L19:
        r1 = r1 + 1;
    L20:
        return r0;
    }

    private static String getPreferredLanguageFromCaptioningManager(Context r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        CaptioningManager r22 = (CaptioningManager) r2.getSystemService("captioning");
        if (r22 != null) goto L8;
    L15:
        return null;
    L8:
        if (r22.isEnabled() == false) goto L15;
        Locale r23 = r22.getLocale();
        if (r23 != null) goto L14;
        return null;
    L14:
        return Util.getLocaleLanguageTag(r23);
    }

    private static int getRoleFlagMatchScore(int r0, int r1) {
        if (r0 == 0) goto L7;
        if (r0 != r1) goto L7;
        return Integer.MAX_VALUE;
    L7:
        return Integer.bitCount(r0 & r1);
    }

    private static int getVideoCodecPreferenceScore(String r7) {
        if (r7 != null) goto L5;
        return 0;
    L5:
        char r6 = 65535;
        switch(r7.hashCode()) {
            case -1851077871: goto L25;
            case -1662735862: goto L21;
            case -1662541442: goto L17;
            case 1331836730: goto L13;
            case 1599127257: goto L9;
            default: goto L28;
        };
    L28:
        switch(r6) {
            case 0: goto L34;
            case 1: goto L33;
            case 2: goto L32;
            case 3: goto L31;
            case 4: goto L30;
            default: goto L29;
        };
    L29:
        return 0;
    L30:
        return 2;
    L31:
        return 1;
    L32:
        return 3;
    L33:
        return 4;
    L34:
        return 5;
    L9:
        if (r7.equals("video/x-vnd.on2.vp9") == false) goto L28;
        r6 = 4;
        goto L28
    L13:
        if (r7.equals(MediaController.VIDEO_MIME_TYPE) == false) goto L28;
        r6 = 3;
        goto L28
    L17:
        if (r7.equals("video/hevc") == false) goto L28;
        r6 = 2;
        goto L28
    L21:
        if (r7.equals("video/av01") == false) goto L28;
        r6 = 1;
        goto L28
    L25:
        if (r7.equals("video/dolby-vision") == false) goto L28;
        r6 = 0;
        goto L28
    }

    private boolean isAudioFormatWithinAudioChannelCountConstraints(Format r2, Parameters r3) {
        if (r3.constrainAudioChannelCountToDeviceCapabilities == false) goto L35;
        Boolean r32 = this.deviceIsTV;
        if (r32 != null) goto L7;
    L8:
        int r33 = r2.channelCount;
        if (r33 != (-1)) goto L11;
        return true;
    L11:
        if (r33 > 2) goto L13;
        return true;
    L13:
        if (isDolbyAudio(r2) == false) goto L21;
        if (Build.VERSION.SDK_INT < 32) goto L45;
        SpatializerWrapperV32 r34 = this.spatializer;
        if (r34 != null) goto L19;
        return true;
    L19:
        if (r34.isSpatializationSupported() == true) goto L21;
        return true;
    L45:
        return true;
    L21:
        if (Build.VERSION.SDK_INT < 32) goto L33;
        SpatializerWrapperV32 r35 = this.spatializer;
        if (r35 != null) goto L25;
        return false;
    L25:
        if (r35.isSpatializationSupported() == true) goto L27;
        return false;
    L27:
        if (this.spatializer.isAvailable() == true) goto L29;
        return false;
    L29:
        if (this.spatializer.isEnabled() == true) goto L31;
        return false;
    L31:
        if (this.spatializer.canBeSpatialized(this.audioAttributes, r2) == true) goto L48;
        return false;
    L48:
        return true;
    L33:
        return false;
    L7:
        if (r32.booleanValue() == false) goto L8;
        return true;
    L35:
        return true;
    }

    private static boolean isDolbyAudio(Format r4) {
        String r42 = r4.sampleMimeType;
        if (r42 != null) goto L5;
        return false;
    L5:
        r42.getClass();
        char r3 = 65535;
        switch(r42.hashCode()) {
            case -2123537834: goto L21;
            case 187078296: goto L17;
            case 187078297: goto L13;
            case 1504578661: goto L9;
            default: goto L24;
        };
    L24:
        switch(r3) {
            case 0: goto L26;
            case 1: goto L26;
            case 2: goto L26;
            case 3: goto L26;
            default: goto L25;
        };
    L25:
        return false;
    L26:
        return true;
    L9:
        if (r42.equals("audio/eac3") == false) goto L24;
        r3 = 3;
        goto L24
    L13:
        if (r42.equals("audio/ac4") == false) goto L24;
        r3 = 2;
        goto L24
    L17:
        if (r42.equals("audio/ac3") == false) goto L24;
        r3 = 1;
        goto L24
    L21:
        if (r42.equals("audio/eac3-joc") == false) goto L24;
        r3 = 0;
        goto L24
    }

    private static boolean isObjectBasedAudio(Format r4) {
        String r42 = r4.sampleMimeType;
        if (r42 != null) goto L5;
        return false;
    L5:
        r42.getClass();
        char r3 = 65535;
        switch(r42.hashCode()) {
            case -2123537834: goto L17;
            case 187078297: goto L13;
            case 1504698186: goto L9;
            default: goto L20;
        };
    L20:
        switch(r3) {
            case 0: goto L22;
            case 1: goto L22;
            case 2: goto L22;
            default: goto L21;
        };
    L21:
        return false;
    L22:
        return true;
    L9:
        if (r42.equals("audio/iamf") == false) goto L20;
        r3 = 2;
        goto L20
    L13:
        if (r42.equals("audio/ac4") == false) goto L20;
        r3 = 1;
        goto L20
    L17:
        if (r42.equals("audio/eac3-joc") == false) goto L20;
        r3 = 0;
        goto L20
    }

    private static void maybeConfigureRendererForOffload(Parameters r7, MappingTrackSelector.MappedTrackInfo r8, int[][][] r9, RendererConfiguration[] r10, ExoTrackSelection[] r11) {
        int r0 = -1;
        boolean r1 = false;
        int r2 = 0;
        int r3 = 0;
    L4:
        if (r2 >= r8.getRendererCount()) goto L17;
        int r4 = r8.getRendererType(r2);
        ExoTrackSelection r6 = r11[r2];
        if (r4 == 1) goto L9;
        if (r6 == null) goto L9;
        return;
    L9:
        if (r4 != 1) goto L16;
        if (r6 == null) goto L16;
        if (r6.length() != 1) goto L16;
        if (rendererSupportsOffload(r7, r9[r2][r8.getTrackGroups(r2).indexOf(r6.getTrackGroup())][r6.getIndexInTrackGroup(0)], r6.getSelectedFormat()) == false) goto L16;
        r3 = r3 + 1;
        r0 = r2;
    L16:
        r2 = r2 + 1;
        goto L4
    L17:
        if (r3 == 1) goto L19;
        return;
    L19:
        if (r7.audioOffloadPreferences.isGaplessSupportRequired == false) goto L21;
        int r72 = 1;
    L22:
        RendererConfiguration r92 = r10[r0];
        if (r92 != null) goto L25;
    L27:
        r10[r0] = new RendererConfiguration(r72, r1);
        return;
    L25:
        if (r92.tunneling == false) goto L27;
        r1 = true;
        goto L27
    L21:
        r72 = 2;
        goto L22
    }

    private static void maybeConfigureRenderersForTunneling(MappingTrackSelector.MappedTrackInfo r10, int[][][] r11, RendererConfiguration[] r12, ExoTrackSelection[] r13) {
        int r3 = -1;
        int r4 = -1;
        int r2 = 0;
    L4:
        if (r2 >= r10.getRendererCount()) goto L20;
        int r5 = r10.getRendererType(r2);
        ExoTrackSelection r7 = r13[r2];
        if (r5 != 1) goto L8;
    L9:
        if (r7 == null) goto L19;
        if (rendererSupportsTunneling(r11[r2], r10.getTrackGroups(r2), r7) == false) goto L19;
        if (r5 != 1) goto L16;
        if (r4 != (-1)) goto L14;
        r4 = r2;
    L14:
        boolean r102 = false;
    L21:
        if (r4 == (-1)) goto L24;
        if (r3 == (-1)) goto L24;
        boolean r112 = true;
    L26:
        if ((r102 & r112) == false) goto L37;
        RendererConfiguration r103 = new RendererConfiguration(0, true);
        r12[r4] = r103;
        r12[r3] = r103;
        return;
    L37:
        return;
    L24:
        r112 = false;
        goto L26
    L16:
        if (r3 != (-1)) goto L14;
        r3 = r2;
    L19:
        r2 = r2 + 1;
        goto L4
    L8:
        if (r5 != 2) goto L19;
    L20:
        r102 = true;
        goto L21
    }

    private void maybeInvalidateForAudioChannelCountConstraints() {
        Object r0 = this.lock;
        monitor-enter(r0);
    L13:
        th = move-exception;
        throw th;
    L5:
        if (this.parameters.constrainAudioChannelCountToDeviceCapabilities == true) goto L7;
    L15:
        boolean r1 = false;
    L16:
        monitor-exit(r0);     // Catch: Throwable -> L13
        if (r1 == false) goto L24;
        invalidate();
        return;
    L24:
        return;
    L7:
        if (Build.VERSION.SDK_INT < 32) goto L15;
        SpatializerWrapperV32 r12 = this.spatializer;     // Catch: Throwable -> L13
        if (r12 == null) goto L15;
        if (r12.isSpatializationSupported() == false) goto L15;
        r1 = true;
        goto L16
    }

    private void maybeInvalidateForRendererCapabilitiesChange(Renderer r3) {
        Object r0 = this.lock;
        monitor-enter(r0);
        boolean r1 = this.parameters.allowInvalidateSelectionsOnRendererCapabilitiesChange;     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        if (r1 == false) goto L14;
        invalidateForRendererCapabilitiesChange(r3);
        return;
    L14:
        return;
    L9:
        th = move-exception;
        throw th;
    }

    public static String normalizeUndeterminedLanguageToNull(String r1) {
        if (TextUtils.isEmpty(r1) == false) goto L5;
        return null;
    L5:
        if (TextUtils.equals(r1, TranslateController.UNKNOWN_LANGUAGE) == true) goto L10;
        return r1;
    L10:
        return null;
    }

    private static boolean rendererSupportsOffload(Parameters r2, int r3, Format r4) {
        if (RendererCapabilities.getAudioOffloadSupport(r3) != 0) goto L6;
        return false;
    L6:
        if (r2.audioOffloadPreferences.isSpeedChangeSupportRequired == false) goto L11;
        if ((RendererCapabilities.getAudioOffloadSupport(r3) & 2048) != 0) goto L11;
        return false;
    L11:
        if (r2.audioOffloadPreferences.isGaplessSupportRequired == true) goto L13;
    L27:
        return true;
    L13:
        if (r4.encoderDelay == 0) goto L15;
    L18:
        boolean r22 = true;
    L20:
        if ((RendererCapabilities.getAudioOffloadSupport(r3) & 1024) == 0) goto L22;
        boolean r32 = true;
    L23:
        if (r22 == false) goto L27;
        if (r32 == true) goto L27;
        return false;
    L22:
        r32 = false;
        goto L23
    L15:
        if (r4.encoderPadding != 0) goto L18;
        r22 = false;
        goto L20
    }

    private static boolean rendererSupportsTunneling(int[][] r4, TrackGroupArray r5, ExoTrackSelection r6) {
        if (r6 != null) goto L5;
        return false;
    L5:
        int r52 = r5.indexOf(r6.getTrackGroup());
        int r1 = 0;
    L7:
        if (r1 >= r6.length()) goto L12;
        if (RendererCapabilities.getTunnelingSupport(r4[r52][r6.getIndexInTrackGroup(r1)]) != 32) goto L10;
        r1 = r1 + 1;
        goto L7
    L10:
        return false;
    L12:
        return true;
    }

    private <T extends TrackInfo<T>> Pair<ExoTrackSelection.Definition, Integer> selectTracksForType(int r19, MappingTrackSelector.MappedTrackInfo r20, int[][][] r21, TrackInfo.Factory<T> r22, Comparator<List<T>> r23) {
        MappingTrackSelector.MappedTrackInfo r0 = r20;
        ArrayList r1 = new ArrayList();
        int r2 = r0.getRendererCount();
        int r4 = 0;
    L3:
        if (r4 >= r2) goto L35;
        if (r19 != r0.getRendererType(r4)) goto L33;
        TrackGroupArray r5 = r0.getTrackGroups(r4);
        int r7 = 0;
    L8:
        if (r7 >= r5.length) goto L33;
        TrackGroup r8 = r5.get(r7);
        List<T> r9 = r22.create(r4, r8, r21[r4][r7]);
        boolean[] r11 = new boolean[r8.length];
        int r12 = 0;
    L11:
        if (r12 >= r8.length) goto L32;
        T r13 = r9.get(r12);
        int r14 = r13.getSelectionEligibility();
        if (r11[r12] == true) goto L15;
        if (r14 == 0) goto L15;
        if (r14 != 1) goto L20;
        RandomAccess r132 = ImmutableList.of(r13);
    L19:
        int r17 = r2;
        r1.add(r132);
    L31:
        r12 = r12 + 1;
        r2 = r17;
        goto L11
    L20:
        ArrayList r142 = new ArrayList();
        r142.add(r13);
        int r15 = r12 + 1;
    L22:
        if (r15 >= r8.length) goto L29;
        T r3 = r9.get(r15);
        int r172 = r2;
        if (r3.getSelectionEligibility() != 2) goto L28;
        if (r13.isCompatibleForAdaptationWith(r3) == false) goto L28;
        r142.add(r3);
        r11[r15] = true;
    L28:
        r15 = r15 + 1;
        r2 = r172;
        goto L22
    L29:
        r132 = r142;
    L15:
        r17 = r2;
        goto L31
    L32:
        r7 = r7 + 1;
    L33:
        r4 = r4 + 1;
        r0 = r20;
        r2 = r2;
        goto L3
    L35:
        if (r1.isEmpty() == false) goto L38;
        return null;
    L38:
        List r02 = (List) Collections.max(r1, r23);
        int[] r16 = new int[r02.size()];
        int r24 = 0;
    L40:
        if (r24 >= r02.size()) goto L42;
        r16[r24] = ((TrackInfo) r02.get(r24)).trackIndex;
        r24 = r24 + 1;
        goto L40
    L42:
        TrackInfo r03 = (TrackInfo) r02.get(0);
        return Pair.create(new ExoTrackSelection.Definition(r03.trackGroup, r16), Integer.valueOf(r03.rendererIndex));
    }

    private void setParametersInternal(Parameters r3) {
        Assertions.checkNotNull(r3);
        Object r0 = this.lock;
        monitor-enter(r0);
        boolean r1 = this.parameters.equals(r3);     // Catch: Throwable -> L14
        this.parameters = r3;     // Catch: Throwable -> L14
        monitor-exit(r0);     // Catch: Throwable -> L14
        if (r1 == false) goto L8;
        return;
    L8:
        if (r3.constrainAudioChannelCountToDeviceCapabilities == true) goto L10;
    L12:
        invalidate();
        return;
    L10:
        if (this.context != null) goto L12;
        Log.w("DefaultTrackSelector", "Audio channel count constraints cannot be applied without reference to Context. Build the track selector instance with one of the non-deprecated constructors that take a Context argument.");
    L14:
        th = move-exception;
        throw th;
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelector
    public /* bridge */ /* synthetic */ TrackSelectionParameters getParameters() {
        return getParameters();
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelector
    public Parameters getParameters() {
        Object r0 = this.lock;
        monitor-enter(r0);
        Parameters r1 = this.parameters;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelector
    public RendererCapabilities.Listener getRendererCapabilitiesListener() {
        return this;
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelector
    public boolean isSetParametersSupported() {
        return true;
    }

    @Override // androidx.media3.exoplayer.RendererCapabilities.Listener
    public void onRendererCapabilitiesChanged(Renderer r1) {
        maybeInvalidateForRendererCapabilitiesChange(r1);
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelector
    public void release() {
        Object r0 = this.lock;
        monitor-enter(r0);
        Thread r1 = this.playbackThread;     // Catch: Throwable -> L11
        if (r1 != null) goto L7;
    L13:
        monitor-exit(r0);     // Catch: Throwable -> L11
        if (Build.VERSION.SDK_INT < 32) goto L19;
        SpatializerWrapperV32 r02 = this.spatializer;
        if (r02 == null) goto L19;
        r02.release();
        this.spatializer = null;
    L19:
        super.release();
        return;
    L7:
        if (r1 != Thread.currentThread()) goto L9;
        boolean r12 = true;
    L10:
        Assertions.checkState(r12, "DefaultTrackSelector is accessed on the wrong thread.");     // Catch: Throwable -> L11
        goto L13
    L9:
        r12 = false;
    L11:
        th = move-exception;
        throw th;
    }

    public ExoTrackSelection.Definition[] selectAllTracks(MappingTrackSelector.MappedTrackInfo r12, int[][][] r13, int[] r14, Parameters r15) {
        int r0 = r12.getRendererCount();
        ExoTrackSelection.Definition[] r1 = new ExoTrackSelection.Definition[r0];
        Pair<ExoTrackSelection.Definition, Integer> r2 = selectAudioTrack(r12, r13, r14, r15);
        if (r2 == null) goto L5;
        r1[((Integer) r2.second).intValue()] = (ExoTrackSelection.Definition) r2.first;
    L5:
        int r3 = 0;
        Pair<ExoTrackSelection.Definition, Integer> r4 = null;
        if (r2 != null) goto L9;
        String r10 = null;
    L10:
        Pair<ExoTrackSelection.Definition, Integer> r11 = selectVideoTrack(r12, r13, r14, r15, r10);
        String r22 = r10;
        if (r15.isPrioritizeImageOverVideoEnabled == true) goto L13;
        if (r11 == null) goto L13;
    L14:
        if (r4 == null) goto L16;
        r1[((Integer) r4.second).intValue()] = (ExoTrackSelection.Definition) r4.first;
    L18:
        Pair<ExoTrackSelection.Definition, Integer> r112 = selectTextTrack(r12, r13, r15, r22);
        if (r112 == null) goto L21;
        r1[((Integer) r112.second).intValue()] = (ExoTrackSelection.Definition) r112.first;
    L21:
        if (r3 >= r0) goto L32;
        int r113 = r12.getRendererType(r3);
        if (r113 == 2) goto L31;
        if (r113 == 1) goto L31;
        if (r113 == 3) goto L31;
        if (r113 == 4) goto L31;
        r1[r3] = selectOtherTrack(r113, r12.getTrackGroups(r3), r13[r3], r15);
    L31:
        r3 = r3 + 1;
        goto L21
    L32:
        return r1;
    L16:
        if (r11 == null) goto L18;
        r1[((Integer) r11.second).intValue()] = (ExoTrackSelection.Definition) r11.first;
    L13:
        r4 = selectImageTrack(r12, r13, r15);
        goto L14
    L9:
        Object r23 = r2.first;
        r10 = ((ExoTrackSelection.Definition) r23).group.getFormat(((ExoTrackSelection.Definition) r23).tracks[0]).language;
        goto L10
    }

    public Pair<ExoTrackSelection.Definition, Integer> selectAudioTrack(MappingTrackSelector.MappedTrackInfo r8, int[][][] r9, int[] r10, Parameters r11) {
        boolean r0 = false;
        int r1 = 0;
    L4:
        if (r1 >= r8.getRendererCount()) goto L12;
        if (2 != r8.getRendererType(r1)) goto L10;
        if (r8.getTrackGroups(r1).length <= 0) goto L10;
        r0 = true;
    L10:
        r1 = r1 + 1;
    L12:
        return selectTracksForType(1, r8, r9, new DefaultTrackSelector$$ExternalSyntheticLambda5(this, r11, r0, r10), new DefaultTrackSelector$$ExternalSyntheticLambda6());
    }

    public Pair<ExoTrackSelection.Definition, Integer> selectImageTrack(MappingTrackSelector.MappedTrackInfo r7, int[][][] r8, Parameters r9) {
        if (r9.audioOffloadPreferences.audioOffloadMode != 2) goto L7;
        return null;
    L7:
        return selectTracksForType(4, r7, r8, new DefaultTrackSelector$$ExternalSyntheticLambda1(r9), new DefaultTrackSelector$$ExternalSyntheticLambda2());
    }

    public ExoTrackSelection.Definition selectOtherTrack(int r11, TrackGroupArray r12, int[][] r13, Parameters r14) {
        if (r14.audioOffloadPreferences.audioOffloadMode != 2) goto L5;
        return null;
    L5:
        int r112 = 0;
        int r2 = 0;
        TrackGroup r1 = null;
        OtherTrackScore r3 = null;
    L7:
        if (r112 >= r12.length) goto L20;
        TrackGroup r4 = r12.get(r112);
        int[] r5 = r13[r112];
        int r6 = 0;
    L10:
        if (r6 >= r4.length) goto L19;
        if (RendererCapabilities.isFormatSupported(r5[r6], r14.exceedRendererCapabilitiesIfNecessary) == false) goto L18;
        OtherTrackScore r8 = new OtherTrackScore(r4.getFormat(r6), r5[r6]);
        if (r3 != null) goto L16;
    L17:
        r1 = r4;
        r2 = r6;
        r3 = r8;
        goto L18
    L16:
        if (r8.compareTo2(r3) > 0) goto L17;
    L18:
        r6 = r6 + 1;
        goto L10
    L19:
        r112 = r112 + 1;
        goto L7
    L20:
        if (r1 != null) goto L23;
        return null;
    L23:
        return new ExoTrackSelection.Definition(r1, new int[]{r2});
    }

    public Pair<ExoTrackSelection.Definition, Integer> selectTextTrack(MappingTrackSelector.MappedTrackInfo r10, int[][][] r11, Parameters r12, String r13) {
        String r2 = null;
        if (r12.audioOffloadPreferences.audioOffloadMode != 2) goto L6;
        return null;
    L6:
        if (r12.usePreferredTextLanguagesAndRoleFlagsFromCaptioningManager == false) goto L9;
        r2 = getPreferredLanguageFromCaptioningManager(this.context);
    L9:
        return selectTracksForType(3, r10, r11, new DefaultTrackSelector$$ExternalSyntheticLambda7(r12, r13, r2), new DefaultTrackSelector$$ExternalSyntheticLambda8());
    }

    @Override // androidx.media3.exoplayer.trackselection.MappingTrackSelector
    public final Pair<RendererConfiguration[], ExoTrackSelection[]> selectTracks(MappingTrackSelector.MappedTrackInfo r8, int[][][] r9, int[] r10, MediaSource.MediaPeriodId r11, Timeline r12) {
        Object r0 = this.lock;
        monitor-enter(r0);
        this.playbackThread = Thread.currentThread();     // Catch: Throwable -> L49
        Parameters r1 = this.parameters;     // Catch: Throwable -> L49
        monitor-exit(r0);     // Catch: Throwable -> L49
        if (this.deviceIsTV != null) goto L12;
        Context r02 = this.context;
        if (r02 == null) goto L12;
        this.deviceIsTV = Boolean.valueOf(Util.isTv(r02));
    L12:
        if (r1.constrainAudioChannelCountToDeviceCapabilities == true) goto L14;
    L18:
        int r03 = r8.getRendererCount();
        ExoTrackSelection.Definition[] r102 = selectAllTracks(r8, r9, r10, r1);
        applyTrackSelectionOverrides(r8, r1, r102);
        applyLegacyRendererOverrides(r8, r1, r102);
        int r2 = 0;
        int r3 = 0;
    L20:
        if (r3 >= r03) goto L27;
        int r5 = r8.getRendererType(r3);
        if (r1.getRendererDisabled(r3) == false) goto L24;
    L25:
        r102[r3] = null;
    L26:
        r3 = r3 + 1;
        goto L20
    L24:
        if (r1.disabledTrackTypes.contains(Integer.valueOf(r5)) == false) goto L26;
    L27:
        ExoTrackSelection[] r7 = this.trackSelectionFactory.createTrackSelections(r102, getBandwidthMeter(), r11, r12);
        RendererConfiguration[] r103 = new RendererConfiguration[r03];
    L28:
        if (r2 >= r03) goto L42;
        int r112 = r8.getRendererType(r2);
        if (r1.getRendererDisabled(r2) == false) goto L32;
    L39:
        RendererConfiguration r113 = null;
    L40:
        r103[r2] = r113;
        r2 = r2 + 1;
        goto L28
    L32:
        if (r1.disabledTrackTypes.contains(Integer.valueOf(r112)) == true) goto L39;
        if (r8.getRendererType(r2) != (-2)) goto L37;
    L38:
        r113 = RendererConfiguration.DEFAULT;
        goto L40
    L37:
        if (r7[r2] == null) goto L39;
    L42:
        if (r1.tunnelingEnabled == false) goto L45;
        maybeConfigureRenderersForTunneling(r8, r9, r103, r7);
    L45:
        if (r1.audioOffloadPreferences.audioOffloadMode == 0) goto L48;
        maybeConfigureRendererForOffload(r1, r8, r9, r103, r7);
    L48:
        return Pair.create(r103, r7);
    L14:
        if (Build.VERSION.SDK_INT < 32) goto L18;
        if (this.spatializer != null) goto L18;
        this.spatializer = new SpatializerWrapperV32(this.context, this, this.deviceIsTV);
    L49:
        th = move-exception;
        throw th;
    }

    public Pair<ExoTrackSelection.Definition, Integer> selectVideoTrack(MappingTrackSelector.MappedTrackInfo r4, int[][][] r5, int[] r6, Parameters r7, String r8) {
        Point r2 = null;
        if (r7.audioOffloadPreferences.audioOffloadMode != 2) goto L6;
        return null;
    L6:
        if (r7.isViewportSizeLimitedByPhysicalDisplaySize == false) goto L11;
        Context r0 = this.context;
        if (r0 == null) goto L11;
        r2 = Util.getCurrentDisplayModeSize(r0);
    L11:
        return selectTracksForType(2, r4, r5, new DefaultTrackSelector$$ExternalSyntheticLambda3(r7, r8, r6, r2), new DefaultTrackSelector$$ExternalSyntheticLambda4());
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelector
    public void setAudioAttributes(AudioAttributes r2) {
        if (this.audioAttributes.equals(r2) == false) goto L5;
        return;
    L5:
        this.audioAttributes = r2;
        maybeInvalidateForAudioChannelCountConstraints();
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelector
    public void setParameters(TrackSelectionParameters r4) {
        if ((r4 instanceof Parameters) == false) goto L5;
        setParametersInternal((Parameters) r4);
    L5:
        setParametersInternal(new Parameters.Builder(getParameters(), null).set(r4).build());
    }
}
