package androidx.media3.exoplayer.source.chunk;

/* loaded from: classes4.dex */
public final class ChunkHolder {
    public Chunk chunk;
    public boolean endOfStream;

    public ChunkHolder() {
    }

    public void clear() {
        this.chunk = null;
        this.endOfStream = false;
    }
}
