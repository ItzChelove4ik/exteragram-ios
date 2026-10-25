package androidx.media3.exoplayer.dash;

import androidx.media3.common.Format;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.dash.PlayerEmsgHandler;
import androidx.media3.exoplayer.dash.manifest.DashManifest;
import androidx.media3.exoplayer.source.chunk.ChunkSource;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.LoaderErrorThrower;
import androidx.media3.extractor.text.SubtitleParser;
import java.util.List;

/* loaded from: classes4.dex */
public interface DashChunkSource extends ChunkSource {

    public interface Factory {
        DashChunkSource createDashChunkSource(LoaderErrorThrower r1, DashManifest r2, BaseUrlExclusionList r3, int r4, int[] r5, ExoTrackSelection r6, int r7, long r8, boolean r10, List<Format> r11, PlayerEmsgHandler.PlayerTrackEmsgHandler r12, TransferListener r13, PlayerId r14, CmcdConfiguration r15);

        Factory experimentalParseSubtitlesDuringExtraction(boolean r1);

        Factory experimentalSetCodecsToParseWithinGopSampleDependencies(int r1);

        Format getOutputTextFormat(Format r1);

        Factory setSubtitleParserFactory(SubtitleParser.Factory r1);
    }

    void updateManifest(DashManifest r1, int r2);

    void updateTrackSelection(ExoTrackSelection r1);
}
