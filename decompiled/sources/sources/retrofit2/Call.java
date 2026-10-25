package retrofit2;

import okhttp3.Request;

/* loaded from: classes3.dex */
public interface Call<T> extends Cloneable {
    void cancel();

    /* renamed from: clone */
    Call<T> mo22453clone();

    void enqueue(Callback<T> r1);

    boolean isCanceled();

    Request request();
}
