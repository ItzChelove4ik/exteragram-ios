package androidx.media3.exoplayer.hls;

import android.net.Uri;
import androidx.media3.common.util.Assertions;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes4.dex */
final class FullSegmentEncryptionKeyCache {
    private final LinkedHashMap<Uri, byte[]> backingMap;

    /* renamed from: androidx.media3.exoplayer.hls.FullSegmentEncryptionKeyCache$1, reason: invalid class name */
    public class AnonymousClass1 extends LinkedHashMap<Uri, byte[]> {
        final /* synthetic */ FullSegmentEncryptionKeyCache this$0;
        final /* synthetic */ int val$maxSize;

        public AnonymousClass1(FullSegmentEncryptionKeyCache r1, int r2, float r3, boolean r4, int r5) {
            this.this$0 = r1;
            this.val$maxSize = r5;
            super(r2, r3, r4);
        }

        @Override // java.util.LinkedHashMap
        public boolean removeEldestEntry(Map.Entry<Uri, byte[]> r1) {
            if (size() <= this.val$maxSize) goto L6;
            return true;
        L6:
            return false;
        }
    }

    public FullSegmentEncryptionKeyCache(int r7) {
        this.backingMap = new AnonymousClass1(this, r7 + 1, 1.0f, false, r7);
    }

    public byte[] get(Uri r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        return this.backingMap.get(r1);
    }

    public byte[] put(Uri r1, byte[] r2) {
        return this.backingMap.put((Uri) Assertions.checkNotNull(r1), (byte[]) Assertions.checkNotNull(r2));
    }

    public byte[] remove(Uri r1) {
        return this.backingMap.remove(Assertions.checkNotNull(r1));
    }
}
