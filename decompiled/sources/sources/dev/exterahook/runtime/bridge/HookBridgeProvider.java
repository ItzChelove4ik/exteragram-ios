package dev.exterahook.runtime.bridge;

import a.a;
import a.b;
import a.c;
import c.g;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u0000 \u00042\u00020\u0001:\u0001\u0005B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0006"}, d2 = {"Ldev/exterahook/runtime/bridge/HookBridgeProvider;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Companion", "a/b", "exterahook_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public final class HookBridgeProvider {
    public static final b Companion = null;
    private static final Lazy<a> defaultBridge$delegate = null;
    private static final Lazy<a> embeddedBridge$delegate = null;
    private static volatile boolean useEmbeddedByDefault;

    /* renamed from: $r8$lambda$3-pHWcEv6KqoSKe_tjndr2Jl4zk, reason: not valid java name */
    public static a m2010$r8$lambda$3pHWcEv6KqoSKe_tjndr2Jl4zk() {
        b r0 = Companion;
        g r1 = new g("exterahook", false);
        r0.getClass();
        return new c(r1, new b.b(), new b.a(new b.b(), new b.c(r1)));
    }

    /* renamed from: $r8$lambda$iP2bZl690-FcfaDtB5t0ycPww_0, reason: not valid java name */
    public static a m2011$r8$lambda$iP2bZl690FcfaDtB5t0ycPww_0() {
        b r0 = Companion;
        g r1 = new g(null, true);
        r0.getClass();
        return new c(r1, new b.b(), new b.a(new b.b(), new b.c(r1)));
    }

    static {
        Companion = new b();
        defaultBridge$delegate = LazyKt.lazy(new HookBridgeProvider$$ExternalSyntheticLambda0());
        embeddedBridge$delegate = LazyKt.lazy(new HookBridgeProvider$$ExternalSyntheticLambda1());
    }

    private HookBridgeProvider() {
    }

    public static final /* synthetic */ Lazy access$getDefaultBridge$delegate$cp() {
        return defaultBridge$delegate;
    }

    public static final /* synthetic */ Lazy access$getEmbeddedBridge$delegate$cp() {
        return embeddedBridge$delegate;
    }

    public static final /* synthetic */ boolean access$getUseEmbeddedByDefault$cp() {
        return useEmbeddedByDefault;
    }

    public static final /* synthetic */ void access$setUseEmbeddedByDefault$cp(boolean r0) {
        useEmbeddedByDefault = r0;
    }

    @JvmStatic
    public static final a createDefault() {
        Companion.getClass();
        if (access$getUseEmbeddedByDefault$cp() == false) goto L7;
        return (a) access$getEmbeddedBridge$delegate$cp().getValue();
    L7:
        return (a) access$getDefaultBridge$delegate$cp().getValue();
    }

    @JvmStatic
    public static final void initializeDefault() {
        Companion.getClass();
        if (access$getUseEmbeddedByDefault$cp() == false) goto L5;
        a r0 = (a) access$getEmbeddedBridge$delegate$cp().getValue();
    L6:
        ((c) r0).a();
        JniBridgeBindings.initialize0();
        return;
    L5:
        r0 = (a) access$getDefaultBridge$delegate$cp().getValue();
        goto L6
    }

    @JvmStatic
    public static final void useEmbeddedAsDefault() {
        Companion.getClass();
        access$setUseEmbeddedByDefault$cp(true);
    }
}
