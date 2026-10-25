package androidx.media3.container;

import androidx.media3.common.util.ParsableByteArray;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes3.dex */
public abstract class Mp4Box {
    public final int type;

    /* renamed from: androidx.media3.container.Mp4Box$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class ContainerBox extends Mp4Box {
        public final List<ContainerBox> containerChildren;
        public final long endPosition;
        public final List<LeafBox> leafChildren;

        public ContainerBox(int r2, long r3) {
            super(r2, null);
            this.endPosition = r3;
            this.leafChildren = new ArrayList();
            this.containerChildren = new ArrayList();
        }

        public void add(ContainerBox r1) {
            this.containerChildren.add(r1);
        }

        public void add(LeafBox r1) {
            this.leafChildren.add(r1);
        }

        public ContainerBox getContainerBoxOfType(int r5) {
            int r0 = this.containerChildren.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L8;
            ContainerBox r2 = this.containerChildren.get(r1);
            if (r2.type == r5) goto L6;
            r1 = r1 + 1;
            goto L3
        L6:
            return r2;
        L8:
            return null;
        }

        public LeafBox getLeafBoxOfType(int r5) {
            int r0 = this.leafChildren.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L8;
            LeafBox r2 = this.leafChildren.get(r1);
            if (r2.type == r5) goto L6;
            r1 = r1 + 1;
            goto L3
        L6:
            return r2;
        L8:
            return null;
        }

        @Override // androidx.media3.container.Mp4Box
        public String toString() {
            return Mp4Box.getBoxTypeString(this.type) + " leaves: " + Arrays.toString(this.leafChildren.toArray()) + " containers: " + Arrays.toString(this.containerChildren.toArray());
        }
    }

    public static final class LeafBox extends Mp4Box {
        public final ParsableByteArray data;

        public LeafBox(int r2, ParsableByteArray r3) {
            super(r2, null);
            this.data = r3;
        }
    }

    private Mp4Box(int r1) {
        this.type = r1;
    }

    public /* synthetic */ Mp4Box(int r1, AnonymousClass1 r2) {
        this(r1);
    }

    public static String getBoxTypeString(int r2) {
        return _UrlKt.FRAGMENT_ENCODE_SET + ((char) ((r2 >> 24) & 255)) + ((char) ((r2 >> 16) & 255)) + ((char) ((r2 >> 8) & 255)) + ((char) (r2 & 255));
    }

    public String toString() {
        return getBoxTypeString(this.type);
    }
}
