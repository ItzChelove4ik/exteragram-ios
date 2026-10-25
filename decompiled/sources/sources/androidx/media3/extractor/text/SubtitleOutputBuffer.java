package androidx.media3.extractor.text;

import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Assertions;
import androidx.media3.decoder.DecoderOutputBuffer;
import java.util.List;
import kotlin.jvm.internal.LongCompanionObject;

/* loaded from: classes4.dex */
public abstract class SubtitleOutputBuffer extends DecoderOutputBuffer implements Subtitle {
    private long subsampleOffsetUs;
    private Subtitle subtitle;

    public SubtitleOutputBuffer() {
    }

    @Override // androidx.media3.decoder.DecoderOutputBuffer, androidx.media3.decoder.Buffer
    public void clear() {
        super.clear();
        this.subtitle = null;
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public List<Cue> getCues(long r4) {
        return ((Subtitle) Assertions.checkNotNull(this.subtitle)).getCues(r4 - this.subsampleOffsetUs);
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public long getEventTime(int r3) {
        return ((Subtitle) Assertions.checkNotNull(this.subtitle)).getEventTime(r3) + this.subsampleOffsetUs;
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public int getEventTimeCount() {
        return ((Subtitle) Assertions.checkNotNull(this.subtitle)).getEventTimeCount();
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public int getNextEventTimeIndex(long r4) {
        return ((Subtitle) Assertions.checkNotNull(this.subtitle)).getNextEventTimeIndex(r4 - this.subsampleOffsetUs);
    }

    public void setContent(long r3, Subtitle r5, long r6) {
        this.timeUs = r3;
        this.subtitle = r5;
        if (r6 == LongCompanionObject.MAX_VALUE) goto L6;
        r3 = r6;
    L6:
        this.subsampleOffsetUs = r3;
    }
}
