package androidx.media3.exoplayer.metadata;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.BaseRenderer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.extractor.metadata.MetadataDecoder;
import androidx.media3.extractor.metadata.MetadataInputBuffer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class MetadataRenderer extends BaseRenderer implements Handler.Callback {
    private final MetadataInputBuffer buffer;
    private MetadataDecoder decoder;
    private final MetadataDecoderFactory decoderFactory;
    private boolean inputStreamEnded;
    private final MetadataOutput output;
    private final Handler outputHandler;
    private final boolean outputMetadataEarly;
    private boolean outputStreamEnded;
    private long outputStreamOffsetUs;
    private Metadata pendingMetadata;
    private long subsampleOffsetUs;

    public MetadataRenderer(MetadataOutput r2, Looper r3) {
        this(r2, r3, MetadataDecoderFactory.DEFAULT);
    }

    public MetadataRenderer(MetadataOutput r2, Looper r3, MetadataDecoderFactory r4) {
        this(r2, r3, r4, false);
    }

    public MetadataRenderer(MetadataOutput r2, Looper r3, MetadataDecoderFactory r4, boolean r5) {
        super(5);
        this.output = (MetadataOutput) Assertions.checkNotNull(r2);
        if (r3 != null) goto L5;
        Handler r22 = null;
    L6:
        this.outputHandler = r22;
        this.decoderFactory = (MetadataDecoderFactory) Assertions.checkNotNull(r4);
        this.outputMetadataEarly = r5;
        this.buffer = new MetadataInputBuffer();
        this.outputStreamOffsetUs = -9223372036854775807L;
        return;
    L5:
        r22 = Util.createHandler(r3, this);
        goto L6
    }

    private void decodeWrappedMetadata(Metadata r6, List<Metadata.Entry> r7) {
        int r0 = 0;
    L4:
        if (r0 >= r6.length()) goto L14;
        Format r1 = r6.get(r0).getWrappedMetadataFormat();
        if (r1 != null) goto L8;
    L12:
        r7.add(r6.get(r0));
    L13:
        r0 = r0 + 1;
        goto L4
    L8:
        if (this.decoderFactory.supportsFormat(r1) == false) goto L12;
        MetadataDecoder r12 = this.decoderFactory.createDecoder(r1);
        byte[] r2 = (byte[]) Assertions.checkNotNull(r6.get(r0).getWrappedMetadataBytes());
        this.buffer.clear();
        this.buffer.ensureSpaceForWrite(r2.length);
        ((ByteBuffer) Util.castNonNull(this.buffer.data)).put(r2);
        this.buffer.flip();
        Metadata r13 = r12.decode(this.buffer);
        if (r13 == null) goto L13;
        decodeWrappedMetadata(r13, r7);
        goto L13
    }

    @SideEffectFree
    private long getPresentationTimeUs(long r8) {
        boolean r3 = false;
        if (r8 == (-9223372036854775807L)) goto L5;
        boolean r2 = true;
    L6:
        Assertions.checkState(r2);
        if (this.outputStreamOffsetUs == (-9223372036854775807L)) goto L9;
        r3 = true;
    L9:
        Assertions.checkState(r3);
        return r8 - this.outputStreamOffsetUs;
    L5:
        r2 = false;
        goto L6
    }

    private void invokeRenderer(Metadata r2) {
        Handler r0 = this.outputHandler;
        if (r0 == null) goto L6;
        r0.obtainMessage(1, r2).sendToTarget();
        return;
    L6:
        invokeRendererInternal(r2);
    }

    private void invokeRendererInternal(Metadata r1) {
        this.output.onMetadata(r1);
    }

    private boolean outputMetadata(long r5) {
        Metadata r0 = this.pendingMetadata;
        if (r0 != null) goto L5;
    L9:
        boolean r52 = false;
    L11:
        if (this.inputStreamEnded == true) goto L13;
    L15:
        return r52;
    L13:
        if (this.pendingMetadata != null) goto L15;
        this.outputStreamEnded = true;
        goto L15
    L5:
        if (this.outputMetadataEarly == false) goto L7;
    L8:
        invokeRenderer(this.pendingMetadata);
        this.pendingMetadata = null;
        r52 = true;
        goto L11
    L7:
        if (r0.presentationTimeUs > getPresentationTimeUs(r5)) goto L9;
        goto L8
    }

    private void readMetadata() {
        if (this.inputStreamEnded == false) goto L5;
        return;
    L5:
        if (this.pendingMetadata != null) goto L25;
        this.buffer.clear();
        FormatHolder r0 = getFormatHolder();
        int r1 = readSource(r0, this.buffer, 0);
        if (r1 != (-4)) goto L21;
        if (this.buffer.isEndOfStream() == false) goto L13;
        this.inputStreamEnded = true;
        return;
    L13:
        if (this.buffer.timeUs < getLastResetPositionUs()) goto L26;
        MetadataInputBuffer r02 = this.buffer;
        r02.subsampleOffsetUs = this.subsampleOffsetUs;
        r02.flip();
        Metadata r03 = ((MetadataDecoder) Util.castNonNull(this.decoder)).decode(this.buffer);
        if (r03 == null) goto L27;
        ArrayList r12 = new ArrayList(r03.length());
        decodeWrappedMetadata(r03, r12);
        if (r12.isEmpty() == true) goto L28;
        this.pendingMetadata = new Metadata(getPresentationTimeUs(this.buffer.timeUs), r12);
        return;
    L28:
        return;
    L27:
        return;
    L26:
        return;
    L21:
        if (r1 != (-5)) goto L29;
        this.subsampleOffsetUs = ((Format) Assertions.checkNotNull(r0.format)).subsampleOffsetUs;
        return;
    L29:
        return;
    }

    @Override // androidx.media3.exoplayer.Renderer, androidx.media3.exoplayer.RendererCapabilities
    public String getName() {
        return "MetadataRenderer";
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message r3) {
        if (r3.what != 1) goto L6;
        invokeRendererInternal((Metadata) r3.obj);
        return true;
    L6:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return false;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isEnded() {
        return this.outputStreamEnded;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isReady() {
        return true;
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onDisabled() {
        this.pendingMetadata = null;
        this.decoder = null;
        this.outputStreamOffsetUs = -9223372036854775807L;
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onPositionReset(long r1, boolean r3) {
        this.pendingMetadata = null;
        this.inputStreamEnded = false;
        this.outputStreamEnded = false;
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onStreamChanged(Format[] r3, long r4, long r6, MediaSource.MediaPeriodId r8) {
        this.decoder = this.decoderFactory.createDecoder(r3[0]);
        Metadata r32 = this.pendingMetadata;
        if (r32 == null) goto L5;
        this.pendingMetadata = r32.copyWithPresentationTimeUs((r32.presentationTimeUs + this.outputStreamOffsetUs) - r6);
    L5:
        this.outputStreamOffsetUs = r6;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void render(long r1, long r3) {
        boolean r32 = true;
    L3:
        if (r32 == false) goto L5;
        readMetadata();
        r32 = outputMetadata(r1);
        goto L3
    }

    @Override // androidx.media3.exoplayer.RendererCapabilities
    public int supportsFormat(Format r1) {
        if (this.decoderFactory.supportsFormat(r1) == false) goto L11;
        if (r1.cryptoType != 0) goto L7;
        int r0 = 4;
    L9:
        return RendererCapabilities.create(r0);
    L7:
        r0 = 2;
        goto L9
    L11:
        return RendererCapabilities.create(0);
    }
}
