package io.noties.markwon;

/* loaded from: classes5.dex */
public interface MarkwonPlugin {

    public interface Registry {
        <P extends MarkwonPlugin> P require(Class<P> r1);
    }
}
