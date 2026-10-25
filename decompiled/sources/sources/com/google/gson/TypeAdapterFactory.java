package com.google.gson;

import com.google.gson.reflect.TypeToken;

/* loaded from: classes.dex */
public interface TypeAdapterFactory {
    <T> TypeAdapter<T> create(Gson r1, TypeToken<T> r2);
}
