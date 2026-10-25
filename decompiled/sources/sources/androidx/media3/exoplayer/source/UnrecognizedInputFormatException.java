package androidx.media3.exoplayer.source;

import android.net.Uri;
import androidx.media3.common.ParserException;
import androidx.media3.extractor.SniffFailure;
import com.google.common.collect.ImmutableList;
import java.util.List;

/* loaded from: classes4.dex */
public class UnrecognizedInputFormatException extends ParserException {
    public final ImmutableList<SniffFailure> sniffFailures;
    public final Uri uri;

    public UnrecognizedInputFormatException(String r4, Uri r5, List<? extends SniffFailure> r6) {
        super(r4, null, false, 1);
        this.uri = r5;
        this.sniffFailures = ImmutableList.copyOf(r6);
    }
}
