package io.noties.markwon.html;

import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public interface HtmlTag {

    public interface Block extends HtmlTag {
        List<Block> children();
    }

    Map<String, String> attributes();

    int end();

    boolean isClosed();

    String name();

    int start();
}
