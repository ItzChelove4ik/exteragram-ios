package com.exteragram.messenger.math;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.IntRange;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0013\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\t\u0018\u00002\u00020\u0001B:\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012!\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\u0002\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0007¢\u0006\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R,\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\u0002\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lcom/exteragram/messenger/math/MathFunction;", _UrlKt.FRAGMENT_ENCODE_SET, "name", _UrlKt.FRAGMENT_ENCODE_SET, "arity", "Lkotlin/ranges/IntRange;", "apply", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ParameterName;", "args", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;Lkotlin/ranges/IntRange;Lkotlin/jvm/functions/Function1;)V", "getName", "()Ljava/lang/String;", "getArity", "()Lkotlin/ranges/IntRange;", "getApply", "()Lkotlin/jvm/functions/Function1;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class MathFunction {
    private final Function1<double[], Double> apply;
    private final IntRange arity;
    private final String name;

    /* JADX WARN: Multi-variable type inference failed */
    public MathFunction(String r1, IntRange r2, Function1<? super double[], Double> r3) {
        this.name = r1;
        this.arity = r2;
        this.apply = r3;
    }

    public final Function1<double[], Double> getApply() {
        return this.apply;
    }

    public final IntRange getArity() {
        return this.arity;
    }

    public final String getName() {
        return this.name;
    }
}
