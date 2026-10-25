package androidx.media3.exoplayer.offline;

import android.net.Uri;
import androidx.media3.common.StreamKey;
import androidx.media3.exoplayer.offline.FilterableManifest;
import androidx.media3.exoplayer.upstream.ParsingLoadable;
import java.io.InputStream;
import java.util.List;

/* loaded from: classes4.dex */
public final class FilteringManifestParser<T extends FilterableManifest<T>> implements ParsingLoadable.Parser<T> {
    private final ParsingLoadable.Parser<? extends T> parser;
    private final List<StreamKey> streamKeys;

    public FilteringManifestParser(ParsingLoadable.Parser<? extends T> r1, List<StreamKey> r2) {
        this.parser = r1;
        this.streamKeys = r2;
    }

    @Override // androidx.media3.exoplayer.upstream.ParsingLoadable.Parser
    public T parse(Uri r2, InputStream r3) {
        T r22 = this.parser.parse(r2, r3);
        List<StreamKey> r32 = this.streamKeys;
        if (r32 != null) goto L5;
    L9:
        return r22;
    L5:
        if (r32.isEmpty() == true) goto L9;
        return (T) r22.copy(this.streamKeys);
    }

    @Override // androidx.media3.exoplayer.upstream.ParsingLoadable.Parser
    public /* bridge */ /* synthetic */ Object parse(Uri r1, InputStream r2) {
        return parse(r1, r2);
    }
}
