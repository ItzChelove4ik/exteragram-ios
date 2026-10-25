package androidx.camera.core.processing;

import androidx.core.util.Consumer;

/* loaded from: classes3.dex */
public class Edge<T> implements Consumer<T> {
    private Consumer<T> mListener;

    public Edge() {
    }

    @Override // androidx.core.util.Consumer
    public void accept(T r1) {
        this.mListener.accept(r1);
    }

    public void setListener(Consumer<T> r1) {
        this.mListener = r1;
    }
}
