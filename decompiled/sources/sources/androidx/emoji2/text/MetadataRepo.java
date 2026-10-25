package androidx.emoji2.text;

import android.graphics.Typeface;
import android.util.SparseArray;
import androidx.core.os.TraceCompat;
import androidx.core.util.Preconditions;
import androidx.emoji2.text.flatbuffer.MetadataList;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class MetadataRepo {
    private final char[] mEmojiCharArray;
    private final MetadataList mMetadataList;
    private final Node mRootNode;
    private final Typeface mTypeface;

    public static class Node {
        private final SparseArray<Node> mChildren;
        private TypefaceEmojiRasterizer mData;

        private Node() {
            this(1);
        }

        public Node(int r2) {
            this.mChildren = new SparseArray(r2);
        }

        public Node get(int r1) {
            SparseArray<Node> r0 = this.mChildren;
            if (r0 != null) goto L7;
            return null;
        L7:
            return r0.get(r1);
        }

        public final TypefaceEmojiRasterizer getData() {
            return this.mData;
        }

        public void put(TypefaceEmojiRasterizer r3, int r4, int r5) {
            Node r0 = get(r3.getCodepointAt(r4));
            if (r0 != null) goto L5;
            r0 = new Node();
            this.mChildren.put(r3.getCodepointAt(r4), r0);
        L5:
            if (r5 <= r4) goto L8;
            r0.put(r3, r4 + 1, r5);
            return;
        L8:
            r0.mData = r3;
        }
    }

    private MetadataRepo(Typeface r2, MetadataList r3) {
        this.mTypeface = r2;
        this.mMetadataList = r3;
        this.mRootNode = new Node(1024);
        this.mEmojiCharArray = new char[r3.listLength() * 2];
        constructIndex(r3);
    }

    private void constructIndex(MetadataList r6) {
        int r62 = r6.listLength();
        int r0 = 0;
    L3:
        if (r0 >= r62) goto L5;
        TypefaceEmojiRasterizer r1 = new TypefaceEmojiRasterizer(this, r0);
        Character.toChars(r1.getId(), this.mEmojiCharArray, r0 * 2);
        put(r1);
        r0 = r0 + 1;
        goto L3
    }

    public static MetadataRepo create(Typeface r1, ByteBuffer r2) {
        TraceCompat.beginSection("EmojiCompat.MetadataRepo.create");     // Catch: Throwable -> L5
        MetadataRepo r0 = new MetadataRepo(r1, MetadataListReader.read(r2));     // Catch: Throwable -> L5
        TraceCompat.endSection();
        return r0;
    L5:
        th = move-exception;
        TraceCompat.endSection();
        throw th;
    }

    public char[] getEmojiCharArray() {
        return this.mEmojiCharArray;
    }

    public MetadataList getMetadataList() {
        return this.mMetadataList;
    }

    public int getMetadataVersion() {
        return this.mMetadataList.version();
    }

    public Node getRootNode() {
        return this.mRootNode;
    }

    public Typeface getTypeface() {
        return this.mTypeface;
    }

    public void put(TypefaceEmojiRasterizer r5) {
        Preconditions.checkNotNull(r5, "emoji metadata cannot be null");
        if (r5.getCodepointsLength() <= 0) goto L5;
        boolean r0 = true;
    L6:
        Preconditions.checkArgument(r0, "invalid metadata codepoint length");
        this.mRootNode.put(r5, 0, r5.getCodepointsLength() - 1);
        return;
    L5:
        r0 = false;
        goto L6
    }
}
