package retrofit2;

/* loaded from: classes3.dex */
public interface Callback<T> {
    void onFailure(Call<T> r1, Throwable r2);

    void onResponse(Call<T> r1, Response<T> r2);
}
