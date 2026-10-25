package androidx.media3.exoplayer.source;

import android.content.Context;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DefaultDataSource;
import androidx.media3.exoplayer.dash.DashMediaSource;
import androidx.media3.exoplayer.drm.DrmSessionManagerProvider;
import androidx.media3.exoplayer.hls.HlsMediaSource;
import androidx.media3.exoplayer.source.ClippingMediaSource;
import androidx.media3.exoplayer.source.ExternallyLoadedMediaSource;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.exoplayer.source.SingleSampleMediaSource;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.extractor.DefaultExtractorsFactory;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.text.DefaultSubtitleParserFactory;
import androidx.media3.extractor.text.SubtitleExtractor;
import androidx.media3.extractor.text.SubtitleParser;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.protobuf.UnsafeUtil$$ExternalSyntheticBUOutline0;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class DefaultMediaSourceFactory implements MediaSourceFactory {
    private DataSource.Factory dataSourceFactory;
    private final DelegateFactoryLoader delegateFactoryLoader;
    private long liveMaxOffsetMs;
    private float liveMaxSpeed;
    private long liveMinOffsetMs;
    private float liveMinSpeed;
    private long liveTargetOffsetMs;
    private LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private boolean parseSubtitlesDuringExtraction;
    private MediaSource.Factory serverSideAdInsertionMediaSourceFactory;
    private SubtitleParser.Factory subtitleParserFactory;

    public static final class DelegateFactoryLoader {
        private int codecsToParseWithinGopSampleDependencies;
        private DataSource.Factory dataSourceFactory;
        private DrmSessionManagerProvider drmSessionManagerProvider;
        private final ExtractorsFactory extractorsFactory;
        private LoadErrorHandlingPolicy loadErrorHandlingPolicy;
        private final Map<Integer, MediaSource.Factory> mediaSourceFactories;
        private final Map<Integer, Supplier<MediaSource.Factory>> mediaSourceFactorySuppliers;
        private boolean parseSubtitlesDuringExtraction;
        private SubtitleParser.Factory subtitleParserFactory;

        public static /* synthetic */ MediaSource.Factory $r8$lambda$7bc9jp54KO0Zw_Jq9skLCivtI8A(Class r0, DataSource.Factory r1) {
            return DefaultMediaSourceFactory.access$100(r0, r1);
        }

        /* renamed from: $r8$lambda$BchZV8gOG-5Ca8rE7fdEfu2ySJk, reason: not valid java name */
        public static /* synthetic */ MediaSource.Factory m835$r8$lambda$BchZV8gOG5Ca8rE7fdEfu2ySJk(Class r0) {
            return DefaultMediaSourceFactory.access$000(r0);
        }

        /* renamed from: $r8$lambda$E_0PBF3HPPlg0UUu-bJ9obL-998, reason: not valid java name */
        public static /* synthetic */ MediaSource.Factory m836$r8$lambda$E_0PBF3HPPlg0UUubJ9obL998(DelegateFactoryLoader r1, DataSource.Factory r2) {
            r1.getClass();
            return new ProgressiveMediaSource.Factory(r2, r1.extractorsFactory);
        }

        public static /* synthetic */ MediaSource.Factory $r8$lambda$Px9spLjsQQZ2vJUlWiFCpndcK3M(Class r0, DataSource.Factory r1) {
            return DefaultMediaSourceFactory.access$100(r0, r1);
        }

        /* renamed from: $r8$lambda$qCo4A_1ilg5VlEVFMv-xZdf6PI0, reason: not valid java name */
        public static /* synthetic */ MediaSource.Factory m837$r8$lambda$qCo4A_1ilg5VlEVFMvxZdf6PI0(Class r0, DataSource.Factory r1) {
            return DefaultMediaSourceFactory.access$100(r0, r1);
        }

        public DelegateFactoryLoader(ExtractorsFactory r1, SubtitleParser.Factory r2) {
            this.extractorsFactory = r1;
            this.subtitleParserFactory = r2;
            this.mediaSourceFactorySuppliers = new HashMap();
            this.mediaSourceFactories = new HashMap();
            this.parseSubtitlesDuringExtraction = true;
        }

        private Supplier<MediaSource.Factory> loadSupplier(int r4) {
            Supplier<MediaSource.Factory> r0 = this.mediaSourceFactorySuppliers.get(Integer.valueOf(r4));
            if (r0 == null) goto L5;
            return r0;
        L5:
            DataSource.Factory r02 = (DataSource.Factory) Assertions.checkNotNull(this.dataSourceFactory);
            if (r4 != 0) goto L8;
            int r2 = DashMediaSource.Factory.$r8$clinit;
            Supplier<MediaSource.Factory> r22 = new DefaultMediaSourceFactory$DelegateFactoryLoader$$ExternalSyntheticLambda0(DashMediaSource.Factory.class.asSubclass(MediaSource.Factory.class), r02);
        L20:
            Supplier<MediaSource.Factory> r1 = r22;
        L23:
            this.mediaSourceFactorySuppliers.put(Integer.valueOf(r4), r1);
            return r1;
        L8:
            if (r4 != 1) goto L10;
            r22 = new DefaultMediaSourceFactory$DelegateFactoryLoader$$ExternalSyntheticLambda1(Class.forName("androidx.media3.exoplayer.smoothstreaming.SsMediaSource$Factory").asSubclass(MediaSource.Factory.class), r02);
            goto L20
        L10:
            if (r4 != 2) goto L12;
            int r23 = HlsMediaSource.Factory.$r8$clinit;
            r22 = new DefaultMediaSourceFactory$DelegateFactoryLoader$$ExternalSyntheticLambda2(HlsMediaSource.Factory.class.asSubclass(MediaSource.Factory.class), r02);
            goto L20
        L12:
            if (r4 != 3) goto L14;
            r1 = new DefaultMediaSourceFactory$DelegateFactoryLoader$$ExternalSyntheticLambda3(Class.forName("androidx.media3.exoplayer.rtsp.RtspMediaSource$Factory").asSubclass(MediaSource.Factory.class));
            goto L23
        L14:
            if (r4 != 4) goto L16;
            r1 = new DefaultMediaSourceFactory$DelegateFactoryLoader$$ExternalSyntheticLambda4(this, r02);
            goto L23
        L16:
            CharCodeKt$$ExternalSyntheticBUOutline0.m("Unrecognized contentType: ", r4);
            return null;
        }

        public MediaSource.Factory getMediaSourceFactory(int r3) {
            MediaSource.Factory r0 = this.mediaSourceFactories.get(Integer.valueOf(r3));
            if (r0 == null) goto L5;
            return r0;
        L5:
            MediaSource.Factory r02 = loadSupplier(r3).get();
            DrmSessionManagerProvider r1 = this.drmSessionManagerProvider;
            if (r1 == null) goto L8;
            r02.setDrmSessionManagerProvider(r1);
        L8:
            LoadErrorHandlingPolicy r12 = this.loadErrorHandlingPolicy;
            if (r12 == null) goto L11;
            r02.setLoadErrorHandlingPolicy(r12);
        L11:
            r02.setSubtitleParserFactory(this.subtitleParserFactory);
            r02.experimentalParseSubtitlesDuringExtraction(this.parseSubtitlesDuringExtraction);
            r02.experimentalSetCodecsToParseWithinGopSampleDependencies(this.codecsToParseWithinGopSampleDependencies);
            this.mediaSourceFactories.put(Integer.valueOf(r3), r02);
            return r02;
        }

        public void setCodecsToParseWithinGopSampleDependencies(int r1) {
            this.codecsToParseWithinGopSampleDependencies = r1;
            this.extractorsFactory.experimentalSetCodecsToParseWithinGopSampleDependencies(r1);
        }

        public void setDataSourceFactory(DataSource.Factory r2) {
            if (r2 == this.dataSourceFactory) goto L6;
            this.dataSourceFactory = r2;
            this.mediaSourceFactorySuppliers.clear();
            this.mediaSourceFactories.clear();
            return;
        }

        public void setDrmSessionManagerProvider(DrmSessionManagerProvider r2) {
            this.drmSessionManagerProvider = r2;
            Iterator<MediaSource.Factory> r1 = this.mediaSourceFactories.values().iterator();
        L4:
            if (r1.hasNext() == false) goto L6;
            r1.next().setDrmSessionManagerProvider(r2);
            goto L4
        }

        public void setJpegExtractorFlags(int r2) {
            ExtractorsFactory r1 = this.extractorsFactory;
            if ((r1 instanceof DefaultExtractorsFactory) == false) goto L6;
            ((DefaultExtractorsFactory) r1).setJpegExtractorFlags(r2);
            return;
        }

        public void setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy r2) {
            this.loadErrorHandlingPolicy = r2;
            Iterator<MediaSource.Factory> r1 = this.mediaSourceFactories.values().iterator();
        L4:
            if (r1.hasNext() == false) goto L6;
            r1.next().setLoadErrorHandlingPolicy(r2);
            goto L4
        }

        public void setParseSubtitlesDuringExtraction(boolean r2) {
            this.parseSubtitlesDuringExtraction = r2;
            this.extractorsFactory.experimentalSetTextTrackTranscodingEnabled(r2);
            Iterator<MediaSource.Factory> r1 = this.mediaSourceFactories.values().iterator();
        L4:
            if (r1.hasNext() == false) goto L6;
            r1.next().experimentalParseSubtitlesDuringExtraction(r2);
            goto L4
        }

        public void setSubtitleParserFactory(SubtitleParser.Factory r2) {
            this.subtitleParserFactory = r2;
            this.extractorsFactory.setSubtitleParserFactory(r2);
            Iterator<MediaSource.Factory> r1 = this.mediaSourceFactories.values().iterator();
        L4:
            if (r1.hasNext() == false) goto L6;
            r1.next().setSubtitleParserFactory(r2);
            goto L4
        }
    }

    public static final class UnknownSubtitlesExtractor implements Extractor {
        private final Format format;

        public UnknownSubtitlesExtractor(Format r1) {
            this.format = r1;
        }

        @Override // androidx.media3.extractor.Extractor
        public void init(ExtractorOutput r5) {
            TrackOutput r0 = r5.track(0, 3);
            r5.seekMap(new SeekMap.Unseekable(-9223372036854775807L));
            r5.endTracks();
            r0.format(this.format.buildUpon().setSampleMimeType("text/x-unknown").setCodecs(this.format.sampleMimeType).build());
        }

        @Override // androidx.media3.extractor.Extractor
        public int read(ExtractorInput r1, PositionHolder r2) {
            if (r1.skip(Integer.MAX_VALUE) != (-1)) goto L5;
            return -1;
        L5:
            return 0;
        }

        @Override // androidx.media3.extractor.Extractor
        public void release() {
        }

        @Override // androidx.media3.extractor.Extractor
        public void seek(long r1, long r3) {
        }

        @Override // androidx.media3.extractor.Extractor
        public boolean sniff(ExtractorInput r1) {
            return true;
        }
    }

    /* renamed from: $r8$lambda$_VZ2pXMbMSL-EQ9WgwhkaEf3oJI, reason: not valid java name */
    public static /* synthetic */ Extractor[] m834$r8$lambda$_VZ2pXMbMSLEQ9WgwhkaEf3oJI(DefaultMediaSourceFactory r1, Format r2) {
        if (r1.subtitleParserFactory.supportsFormat(r2) == false) goto L5;
        Extractor r0 = new SubtitleExtractor(r1.subtitleParserFactory.create(r2), null);
    L7:
        return new Extractor[]{r0};
    L5:
        r0 = new UnknownSubtitlesExtractor(r2);
        goto L7
    }

    public DefaultMediaSourceFactory(Context r2, ExtractorsFactory r3) {
        this(new DefaultDataSource.Factory(r2), r3);
    }

    public DefaultMediaSourceFactory(DataSource.Factory r3, ExtractorsFactory r4) {
        this.dataSourceFactory = r3;
        DefaultSubtitleParserFactory r0 = new DefaultSubtitleParserFactory();
        this.subtitleParserFactory = r0;
        DelegateFactoryLoader r1 = new DelegateFactoryLoader(r4, r0);
        this.delegateFactoryLoader = r1;
        r1.setDataSourceFactory(r3);
        this.liveTargetOffsetMs = -9223372036854775807L;
        this.liveMinOffsetMs = -9223372036854775807L;
        this.liveMaxOffsetMs = -9223372036854775807L;
        this.liveMinSpeed = -3.4028235E38f;
        this.liveMaxSpeed = -3.4028235E38f;
        this.parseSubtitlesDuringExtraction = true;
    }

    public static /* synthetic */ MediaSource.Factory access$000(Class r0) {
        return newInstance(r0);
    }

    public static /* synthetic */ MediaSource.Factory access$100(Class r0, DataSource.Factory r1) {
        return newInstance(r0, r1);
    }

    private static MediaSource maybeClipMediaSource(MediaItem r5, MediaSource r6) {
        MediaItem.ClippingConfiguration r0 = r5.clippingConfiguration;
        if (r0.startPositionUs != 0) goto L10;
        if (r0.endPositionUs != Long.MIN_VALUE) goto L10;
        if (r0.relativeToDefaultPosition == true) goto L10;
        return r6;
    L10:
        return new ClippingMediaSource.Builder(r6).setStartPositionUs(r5.clippingConfiguration.startPositionUs).setEndPositionUs(r5.clippingConfiguration.endPositionUs).setEnableInitialDiscontinuity(!r5.clippingConfiguration.startsAtKeyFrame).setAllowDynamicClippingUpdates(r5.clippingConfiguration.relativeToLiveWindow).setRelativeToDefaultPosition(r5.clippingConfiguration.relativeToDefaultPosition).setAllowUnseekableMedia(r5.clippingConfiguration.allowUnseekableMedia).build();
    }

    private MediaSource maybeWrapWithAdsMediaSource(MediaItem r1, MediaSource r2) {
        Assertions.checkNotNull(r1.localConfiguration);
        if (r1.localConfiguration.adsConfiguration != null) goto L5;
        return r2;
    L5:
        Log.w("DMediaSourceFactory", "Playing media without ads. Configure ad support by calling setAdsLoaderProvider and setAdViewProvider.");
        return r2;
    }

    private static MediaSource.Factory newInstance(Class<? extends MediaSource.Factory> r1) {
        return r1.getConstructor(null).newInstance(null);
    L5:
        e = move-exception;
        UnsafeUtil$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    private static MediaSource.Factory newInstance(Class<? extends MediaSource.Factory> r1, DataSource.Factory r2) {
        return r1.getConstructor(new Class[]{DataSource.Factory.class}).newInstance(new Object[]{r2});
    L4:
        e = move-exception;
        UnsafeUtil$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource.Factory
    public MediaSource createMediaSource(MediaItem r11) {
        Assertions.checkNotNull(r11.localConfiguration);
        String r0 = r11.localConfiguration.uri.getScheme();
        if (r0 != null) goto L5;
    L8:
        boolean r02 = Objects.equals(r11.localConfiguration.mimeType, "application/x-image-uri");
        MediaItem.LocalConfiguration r1 = r11.localConfiguration;
        if (r02 == false) goto L12;
        long r03 = Util.msToUs(r1.imageDurationMs);
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(Assertions.checkNotNull(null));
        return new ExternallyLoadedMediaSource.Factory(r03, null).createMediaSource(r11);
    L12:
        int r04 = Util.inferContentTypeForUriAndMimeType(r1.uri, r1.mimeType);
        if (r11.localConfiguration.imageDurationMs == (-9223372036854775807L)) goto L59;
        this.delegateFactoryLoader.setJpegExtractorFlags(1);
    L59:
        MediaSource.Factory r05 = this.delegateFactoryLoader.getMediaSourceFactory(r04);     // Catch: ClassNotFoundException -> L56
        MediaItem.LiveConfiguration.Builder r12 = r11.liveConfiguration.buildUpon();
        if (r11.liveConfiguration.targetOffsetMs != (-9223372036854775807L)) goto L20;
        r12.setTargetOffsetMs(this.liveTargetOffsetMs);
    L20:
        if (r11.liveConfiguration.minPlaybackSpeed != (-3.4028235E38f)) goto L23;
        r12.setMinPlaybackSpeed(this.liveMinSpeed);
    L23:
        if (r11.liveConfiguration.maxPlaybackSpeed != (-3.4028235E38f)) goto L26;
        r12.setMaxPlaybackSpeed(this.liveMaxSpeed);
    L26:
        if (r11.liveConfiguration.minOffsetMs != (-9223372036854775807L)) goto L29;
        r12.setMinOffsetMs(this.liveMinOffsetMs);
    L29:
        if (r11.liveConfiguration.maxOffsetMs != (-9223372036854775807L)) goto L31;
        r12.setMaxOffsetMs(this.liveMaxOffsetMs);
    L31:
        MediaItem.LiveConfiguration r13 = r12.build();
        if (r13.equals(r11.liveConfiguration) == true) goto L34;
        r11 = r11.buildUpon().setLiveConfiguration(r13).build();
    L34:
        MediaSource r06 = r05.createMediaSource(r11);
        ImmutableList<MediaItem.SubtitleConfiguration> r14 = ((MediaItem.LocalConfiguration) Util.castNonNull(r11.localConfiguration)).subtitleConfigurations;
        if (r14.isEmpty() == true) goto L55;
        MediaSource[] r2 = new MediaSource[r14.size() + 1];
        r2[0] = r06;
        int r07 = 0;
    L38:
        if (r07 >= r14.size()) goto L53;
        if (this.parseSubtitlesDuringExtraction == false) goto L48;
        Format r4 = new Format.Builder().setSampleMimeType(r14.get(r07).mimeType).setLanguage(r14.get(r07).language).setSelectionFlags(r14.get(r07).selectionFlags).setRoleFlags(r14.get(r07).roleFlags).setLabel(r14.get(r07).label).setId(r14.get(r07).id).build();
        ProgressiveMediaSource.Factory r8 = new ProgressiveMediaSource.Factory(this.dataSourceFactory, new DefaultMediaSourceFactory$$ExternalSyntheticLambda0(this, r4));
        if (this.subtitleParserFactory.supportsFormat(r4) == false) goto L44;
        r4 = r4.buildUpon().setSampleMimeType("application/x-media3-cues").setCodecs(r4.sampleMimeType).setCueReplacementBehavior(this.subtitleParserFactory.getCueReplacementBehavior(r4)).build();
    L44:
        ProgressiveMediaSource.Factory r42 = r8.enableLazyLoadingWithSingleTrack(0, r4);
        LoadErrorHandlingPolicy r7 = this.loadErrorHandlingPolicy;
        if (r7 == null) goto L47;
        r42.setLoadErrorHandlingPolicy(r7);
    L47:
        r2[r07 + 1] = r42.createMediaSource(MediaItem.fromUri(r14.get(r07).uri.toString()));
    L52:
        r07 = r07 + 1;
        goto L38
    L48:
        SingleSampleMediaSource.Factory r43 = new SingleSampleMediaSource.Factory(this.dataSourceFactory);
        LoadErrorHandlingPolicy r72 = this.loadErrorHandlingPolicy;
        if (r72 == null) goto L51;
        r43.setLoadErrorHandlingPolicy(r72);
    L51:
        r2[r07 + 1] = r43.createMediaSource(r14.get(r07), -9223372036854775807L);
        goto L52
    L53:
        r06 = new MergingMediaSource(r2);
    L55:
        return maybeWrapWithAdsMediaSource(r11, maybeClipMediaSource(r11, r06));
    L56:
        e = move-exception;
        UnsafeUtil$$ExternalSyntheticBUOutline0.m(e);
        return null;
    L5:
        if (r0.equals("ssai") == false) goto L8;
        return ((MediaSource.Factory) Assertions.checkNotNull(this.serverSideAdInsertionMediaSourceFactory)).createMediaSource(r11);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource.Factory
    @Deprecated
    public DefaultMediaSourceFactory experimentalParseSubtitlesDuringExtraction(boolean r2) {
        this.parseSubtitlesDuringExtraction = r2;
        this.delegateFactoryLoader.setParseSubtitlesDuringExtraction(r2);
        return this;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource.Factory
    @Deprecated
    public /* bridge */ /* synthetic */ MediaSource.Factory experimentalParseSubtitlesDuringExtraction(boolean r1) {
        return experimentalParseSubtitlesDuringExtraction(r1);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource.Factory
    public DefaultMediaSourceFactory experimentalSetCodecsToParseWithinGopSampleDependencies(int r2) {
        this.delegateFactoryLoader.setCodecsToParseWithinGopSampleDependencies(r2);
        return this;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource.Factory
    public /* bridge */ /* synthetic */ MediaSource.Factory experimentalSetCodecsToParseWithinGopSampleDependencies(int r1) {
        return experimentalSetCodecsToParseWithinGopSampleDependencies(r1);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource.Factory
    public DefaultMediaSourceFactory setDrmSessionManagerProvider(DrmSessionManagerProvider r3) {
        this.delegateFactoryLoader.setDrmSessionManagerProvider((DrmSessionManagerProvider) Assertions.checkNotNull(r3, "MediaSource.Factory#setDrmSessionManagerProvider no longer handles null by instantiating a new DefaultDrmSessionManagerProvider. Explicitly construct and pass an instance in order to retain the old behavior."));
        return this;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource.Factory
    public /* bridge */ /* synthetic */ MediaSource.Factory setDrmSessionManagerProvider(DrmSessionManagerProvider r1) {
        return setDrmSessionManagerProvider(r1);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource.Factory
    public DefaultMediaSourceFactory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy r2) {
        this.loadErrorHandlingPolicy = (LoadErrorHandlingPolicy) Assertions.checkNotNull(r2, "MediaSource.Factory#setLoadErrorHandlingPolicy no longer handles null by instantiating a new DefaultLoadErrorHandlingPolicy. Explicitly construct and pass an instance in order to retain the old behavior.");
        this.delegateFactoryLoader.setLoadErrorHandlingPolicy(r2);
        return this;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource.Factory
    public /* bridge */ /* synthetic */ MediaSource.Factory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy r1) {
        return setLoadErrorHandlingPolicy(r1);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource.Factory
    public DefaultMediaSourceFactory setSubtitleParserFactory(SubtitleParser.Factory r2) {
        this.subtitleParserFactory = (SubtitleParser.Factory) Assertions.checkNotNull(r2);
        this.delegateFactoryLoader.setSubtitleParserFactory(r2);
        return this;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource.Factory
    public /* bridge */ /* synthetic */ MediaSource.Factory setSubtitleParserFactory(SubtitleParser.Factory r1) {
        return setSubtitleParserFactory(r1);
    }
}
