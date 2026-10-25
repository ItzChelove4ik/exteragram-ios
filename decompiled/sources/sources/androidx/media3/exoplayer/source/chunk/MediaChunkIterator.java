package androidx.media3.exoplayer.source.chunk;

import java.util.NoSuchElementException;

/* loaded from: classes4.dex */
public interface MediaChunkIterator {
    public static final MediaChunkIterator EMPTY = null;

    /* renamed from: androidx.media3.exoplayer.source.chunk.MediaChunkIterator$1, reason: invalid class name */
    public class AnonymousClass1 implements MediaChunkIterator {
        public AnonymousClass1() {
        }

        @Override // androidx.media3.exoplayer.source.chunk.MediaChunkIterator
        public long getChunkEndTimeUs() {
            throw new NoSuchElementException();
        }

        @Override // androidx.media3.exoplayer.source.chunk.MediaChunkIterator
        public long getChunkStartTimeUs() {
            throw new NoSuchElementException();
        }

        @Override // androidx.media3.exoplayer.source.chunk.MediaChunkIterator
        public boolean next() {
            return false;
        }
    }

    static {
        EMPTY = new AnonymousClass1();
    }

    long getChunkEndTimeUs();

    long getChunkStartTimeUs();

    boolean next();
}
