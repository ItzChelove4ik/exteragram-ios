package com.exteragram.messenger.api.network;

import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.Interceptor;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nOkHttpClient.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OkHttpClient.kt\nokhttp3/OkHttpClient$Builder$addInterceptor$2\n+ 2 ApiClient.kt\ncom/exteragram/messenger/api/network/ApiClient\n*L\n1#1,1388:1\n33#2,11:1389\n*E\n"})
/* renamed from: com.exteragram.messenger.api.network.ApiClient$apiService_delegate$lambda$1$$inlined$-addInterceptor$1, reason: invalid class name */
/* loaded from: classes4.dex */
public final class ApiClient$apiService_delegate$lambda$1$$inlined$addInterceptor$1 implements Interceptor {
    public ApiClient$apiService_delegate$lambda$1$$inlined$addInterceptor$1() {
    }

    @Override // okhttp3.Interceptor
    public final Response intercept(Interceptor.Chain r4) {
        if (ApiClient.INSTANCE.getRequestsEnabled$TMessagesProj() == false) goto L7;
        return r4.proceed(r4.request());
    L7:
        return new Response.Builder().request(r4.request()).protocol(Protocol.HTTP_1_1).code(503).message("extera API requests disabled").body(ResponseBody.Companion.create$default(ResponseBody.Companion, new byte[0], null, 1, null)).build();
    }
}
