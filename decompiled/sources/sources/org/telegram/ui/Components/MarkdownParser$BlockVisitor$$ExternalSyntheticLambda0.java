package org.telegram.ui.Components;

import io.noties.markwon.html.MarkwonHtmlParser;
import java.util.List;

/* loaded from: classes7.dex */
public final /* synthetic */ class MarkdownParser$BlockVisitor$$ExternalSyntheticLambda0 implements MarkwonHtmlParser.FlushAction {
    public final /* synthetic */ List f$0;

    public /* synthetic */ MarkdownParser$BlockVisitor$$ExternalSyntheticLambda0(List r1) {
        this.f$0 = r1;
    }

    @Override // io.noties.markwon.html.MarkwonHtmlParser.FlushAction
    public final void apply(List r1) {
        this.f$0.addAll(r1);
    }
}
