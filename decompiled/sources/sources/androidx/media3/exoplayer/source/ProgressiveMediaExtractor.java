package androidx.media3.exoplayer.source;

import android.net.Uri;
import androidx.media3.common.DataReader;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.PositionHolder;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public interface ProgressiveMediaExtractor {

    public interface Factory {
        ProgressiveMediaExtractor createProgressiveMediaExtractor(PlayerId r1);
    }

    void disableSeekingOnMp3Streams();

    long getCurrentInputPosition();

    void init(DataReader r1, Uri r2, Map<String, List<String>> r3, long r4, long r6, ExtractorOutput r8);

    int read(PositionHolder r1);

    void release();

    void seek(long r1, long r3);
}
