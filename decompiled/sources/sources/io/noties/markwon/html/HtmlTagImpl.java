package io.noties.markwon.html;

import io.noties.markwon.html.HtmlTag;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
abstract class HtmlTagImpl implements HtmlTag {
    final Map<String, String> attributes;
    int end;
    final String name;
    final int start;

    public static class BlockImpl extends HtmlTagImpl implements HtmlTag.Block {
        List<BlockImpl> children;
        final BlockImpl parent;

        public BlockImpl(String r1, int r2, Map<String, String> r3, BlockImpl r4) {
            super(r1, r2, r3);
            this.parent = r4;
        }

        public static BlockImpl create(String r1, int r2, Map<String, String> r3, BlockImpl r4) {
            return new BlockImpl(r1, r2, r3, r4);
        }

        public static BlockImpl root() {
            return new BlockImpl(_UrlKt.FRAGMENT_ENCODE_SET, 0, Collections.EMPTY_MAP, null);
        }

        @Override // io.noties.markwon.html.HtmlTagImpl, io.noties.markwon.html.HtmlTag
        public Map<String, String> attributes() {
            return this.attributes;
        }

        @Override // io.noties.markwon.html.HtmlTag.Block
        public List<HtmlTag.Block> children() {
            List<BlockImpl> r0 = this.children;
            if (r0 != null) goto L7;
            return Collections.EMPTY_LIST;
        L7:
            return Collections.unmodifiableList(r0);
        }

        public void closeAt(int r2) {
            if (isClosed() == true) goto L10;
            this.end = r2;
            List<BlockImpl> r1 = this.children;
            if (r1 == null) goto L12;
            Iterator<BlockImpl> r12 = r1.iterator();
        L8:
            if (r12.hasNext() == false) goto L13;
            r12.next().closeAt(r2);
            goto L8
        L13:
            return;
        L12:
            return;
        }

        public String toString() {
            StringBuilder r0 = new StringBuilder("BlockImpl{name='");
            r0.append(this.name);
            r0.append("', start=");
            r0.append(this.start);
            r0.append(", end=");
            r0.append(this.end);
            r0.append(", attributes=");
            r0.append(this.attributes);
            r0.append(", parent=");
            BlockImpl r1 = this.parent;
            if (r1 == null) goto L5;
            String r12 = r1.name;
        L6:
            r0.append(r12);
            r0.append(", children=");
            r0.append(this.children);
            r0.append('}');
            return r0.toString();
        L5:
            r12 = null;
            goto L6
        }
    }

    public static class InlineImpl extends HtmlTagImpl implements HtmlTag {
        public InlineImpl(String r1, int r2, Map<String, String> r3) {
            super(r1, r2, r3);
        }

        public void closeAt(int r2) {
            if (isClosed() == true) goto L6;
            this.end = r2;
            return;
        }

        public String toString() {
            return "InlineImpl{name='" + this.name + "', start=" + this.start + ", end=" + this.end + ", attributes=" + this.attributes + '}';
        }
    }

    public HtmlTagImpl(String r2, int r3, Map<String, String> r4) {
        this.end = -1;
        this.name = r2;
        this.start = r3;
        this.attributes = r4;
    }

    @Override // io.noties.markwon.html.HtmlTag
    public Map<String, String> attributes() {
        return this.attributes;
    }

    @Override // io.noties.markwon.html.HtmlTag
    public int end() {
        return this.end;
    }

    @Override // io.noties.markwon.html.HtmlTag
    public boolean isClosed() {
        if (this.end <= (-1)) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isEmpty() {
        if (this.start != this.end) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // io.noties.markwon.html.HtmlTag
    public String name() {
        return this.name;
    }

    @Override // io.noties.markwon.html.HtmlTag
    public int start() {
        return this.start;
    }
}
