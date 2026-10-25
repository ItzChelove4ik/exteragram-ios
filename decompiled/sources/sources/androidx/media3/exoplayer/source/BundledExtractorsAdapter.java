package androidx.media3.exoplayer.source;

import android.net.Uri;
import androidx.media3.common.DataReader;
import androidx.media3.common.util.Assertions;
import androidx.media3.extractor.DefaultExtractorInput;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.mp3.Mp3Extractor;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.io.EOFException;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public final class BundledExtractorsAdapter implements ProgressiveMediaExtractor {
    private Extractor extractor;
    private ExtractorInput extractorInput;
    private final ExtractorsFactory extractorsFactory;

    public static /* synthetic */ String $r8$lambda$qBNn1419lJeBXNszSGFAq4fjqyo(Extractor r0) {
        return r0.getUnderlyingImplementation().getClass().getSimpleName();
    }

    public BundledExtractorsAdapter(ExtractorsFactory r1) {
        this.extractorsFactory = r1;
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public void disableSeekingOnMp3Streams() {
        Extractor r1 = this.extractor;
        if (r1 == null) goto L10;
        Extractor r12 = r1.getUnderlyingImplementation();
        if ((r12 instanceof Mp3Extractor) == false) goto L9;
        ((Mp3Extractor) r12).disableSeeking();
        return;
    L9:
        return;
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public long getCurrentInputPosition() {
        ExtractorInput r2 = this.extractorInput;
        if (r2 != null) goto L5;
        return -1;
    L5:
        return r2.getPosition();
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public void init(DataReader r8, Uri r9, Map<String, List<String>> r10, long r11, long r13, ExtractorOutput r15) {
        DefaultExtractorInput r1 = new DefaultExtractorInput(r8, r11, r13);
        this.extractorInput = r1;
        if (this.extractor == null) goto L5;
        return;
    L5:
        Extractor[] r82 = this.extractorsFactory.createExtractors(r9, r10);
        ImmutableList.Builder r102 = ImmutableList.builderWithExpectedSize(r82.length);
        boolean r12 = false;
        if (r82.length != 1) goto L8;
        this.extractor = r82[0];
    L41:
        this.extractor.init(r15);
        return;
    L8:
        int r112 = r82.length;
        int r14 = 0;
    L9:
        if (r14 >= r112) goto L40;
        Extractor r0 = r82[r14];
    L15:
        th = move-exception;
        if (this.extractor == null) goto L29;
    L30:
        r12 = true;
    L31:
        Assertions.checkState(r12);
        r1.resetPeekPosition();
        throw th;
    L29:
        if (r1.getPosition() != r11) goto L31;
    L12:
        if (r0.sniff(r1) == false) goto L17;
        this.extractor = r0;     // Catch: Throwable -> L15 EOFException -> L33
        goto L34
    L14:
        Assertions.checkState(true);
        r1.resetPeekPosition();
        goto L40
    L17:
        r102.addAll(r0.getSniffFailureDetails());     // Catch: Throwable -> L15 EOFException -> L33
        if (this.extractor == null) goto L21;
    L24:
        boolean r02 = true;
    L25:
        Assertions.checkState(r02);
        r1.resetPeekPosition();
        r14 = r14 + 1;
        goto L9
    L21:
        if (r1.getPosition() == r11) goto L24;
    L23:
        r02 = false;
    L34:
        if (this.extractor != null) goto L24;
        if (r1.getPosition() != r11) goto L23;
    L40:
        if (this.extractor != null) goto L41;
        throw new UnrecognizedInputFormatException("None of the available extractors (" + Joiner.on(", ").join(Lists.transform(ImmutableList.copyOf(r82), new BundledExtractorsAdapter$$ExternalSyntheticLambda0())) + ") could read the stream.", (Uri) Assertions.checkNotNull(r9), r102.build());
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public int read(PositionHolder r2) {
        return ((Extractor) Assertions.checkNotNull(this.extractor)).read((ExtractorInput) Assertions.checkNotNull(this.extractorInput), r2);
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public void release() {
        Extractor r0 = this.extractor;
        if (r0 == null) goto L5;
        r0.release();
        this.extractor = null;
    L5:
        this.extractorInput = null;
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public void seek(long r1, long r3) {
        ((Extractor) Assertions.checkNotNull(this.extractor)).seek(r1, r3);
    }
}
