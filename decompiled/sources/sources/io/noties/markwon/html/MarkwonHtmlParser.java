package io.noties.markwon.html;

import io.noties.markwon.html.HtmlTag;
import java.util.List;

/* loaded from: classes5.dex */
public abstract class MarkwonHtmlParser {

    public interface FlushAction<T> {
        void apply(List<T> r1);
    }

    public MarkwonHtmlParser() {
    }

    public abstract void flushBlockTags(int r1, FlushAction<HtmlTag.Block> r2);

    public abstract void flushInlineTags(int r1, FlushAction<Object> r2);

    public abstract <T extends Appendable & CharSequence> void processFragment(T r1, String r2);
}
