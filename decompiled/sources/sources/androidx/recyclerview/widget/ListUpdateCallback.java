package androidx.recyclerview.widget;

/* loaded from: classes.dex */
public interface ListUpdateCallback {
    void onChanged(int r1, int r2, Object r3);

    void onInserted(int r1, int r2);

    void onMoved(int r1, int r2);

    void onRemoved(int r1, int r2);
}
