package androidx.media3.extractor;

import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public final class ChunkIndexMerger {
    private final Map<Long, ChunkIndex> chunkMap;

    public ChunkIndexMerger() {
        this.chunkMap = new LinkedHashMap();
    }

    public void add(ChunkIndex r6) {
        long[] r0 = r6.timesUs;
        if (r0.length > 0) goto L5;
        return;
    L5:
        if (this.chunkMap.containsKey(Long.valueOf(r0[0])) == true) goto L9;
        this.chunkMap.put(Long.valueOf(r6.timesUs[0]), r6);
        return;
    }

    public ChunkIndex merge() {
        ArrayList r0 = new ArrayList();
        ArrayList r1 = new ArrayList();
        ArrayList r2 = new ArrayList();
        ArrayList r3 = new ArrayList();
        Iterator<ChunkIndex> r6 = this.chunkMap.values().iterator();
    L4:
        if (r6.hasNext() == false) goto L7;
        ChunkIndex r4 = r6.next();
        r0.add(r4.sizes);
        r1.add(r4.offsets);
        r2.add(r4.durationsUs);
        r3.add(r4.timesUs);
        goto L4
    L7:
        return new ChunkIndex(Ints.concat((int[][]) r0.toArray(new int[r0.size()][])), Longs.concat((long[][]) r1.toArray(new long[r1.size()][])), Longs.concat((long[][]) r2.toArray(new long[r2.size()][])), Longs.concat((long[][]) r3.toArray(new long[r3.size()][])));
    }

    public int size() {
        return this.chunkMap.size();
    }
}
