package androidx.media3.exoplayer.upstream;

import androidx.media3.common.ParserException;
import androidx.media3.datasource.DataSourceException;
import androidx.media3.datasource.HttpDataSource$CleartextNotPermittedException;
import androidx.media3.datasource.HttpDataSource$InvalidResponseCodeException;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.exoplayer.upstream.Loader;
import java.io.FileNotFoundException;
import java.io.IOException;

/* loaded from: classes4.dex */
public class DefaultLoadErrorHandlingPolicy implements LoadErrorHandlingPolicy {
    private final int minimumLoadableRetryCount;

    public DefaultLoadErrorHandlingPolicy() {
        this(-1);
    }

    public DefaultLoadErrorHandlingPolicy(int r1) {
        this.minimumLoadableRetryCount = r1;
    }

    @Override // androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy
    public LoadErrorHandlingPolicy.FallbackSelection getFallbackSelectionFor(LoadErrorHandlingPolicy.FallbackOptions r3, LoadErrorHandlingPolicy.LoadErrorInfo r4) {
        if (isEligibleForFallback(r4.exception) == true) goto L6;
        return null;
    L6:
        if (r3.isFallbackAvailable(1) == false) goto L10;
        return new LoadErrorHandlingPolicy.FallbackSelection(1, 300000);
    L10:
        if (r3.isFallbackAvailable(2) == true) goto L12;
        return null;
    L12:
        return new LoadErrorHandlingPolicy.FallbackSelection(2, 60000);
    }

    @Override // androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy
    public int getMinimumLoadableRetryCount(int r2) {
        int r1 = this.minimumLoadableRetryCount;
        if (r1 == (-1)) goto L5;
        return r1;
    L5:
        if (r2 != 7) goto L8;
        return 6;
    L8:
        return 3;
    }

    @Override // androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy
    public long getRetryDelayMsFor(LoadErrorHandlingPolicy.LoadErrorInfo r2) {
        IOException r1 = r2.exception;
        if ((r1 instanceof ParserException) == false) goto L5;
        return -9223372036854775807L;
    L5:
        if ((r1 instanceof FileNotFoundException) == false) goto L7;
        return -9223372036854775807L;
    L7:
        if ((r1 instanceof HttpDataSource$CleartextNotPermittedException) == false) goto L9;
        return -9223372036854775807L;
    L9:
        if ((r1 instanceof Loader.UnexpectedLoaderException) == false) goto L11;
        return -9223372036854775807L;
    L11:
        if (DataSourceException.isCausedByPositionOutOfRange(r1) == false) goto L14;
        return -9223372036854775807L;
    L14:
        return Math.min((r2.errorCount - 1) * 1000, 5000);
    }

    public boolean isEligibleForFallback(IOException r2) {
        if ((r2 instanceof HttpDataSource$InvalidResponseCodeException) == true) goto L5;
        return false;
    L5:
        int r1 = ((HttpDataSource$InvalidResponseCodeException) r2).responseCode;
        if (r1 != 403) goto L8;
        return true;
    L8:
        if (r1 != 404) goto L10;
        return true;
    L10:
        if (r1 != 410) goto L12;
        return true;
    L12:
        if (r1 != 416) goto L14;
        return true;
    L14:
        if (r1 != 500) goto L16;
        return true;
    L16:
        if (r1 == 503) goto L25;
        return false;
    L25:
        return true;
    }
}
