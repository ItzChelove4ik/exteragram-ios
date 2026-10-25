package com.exteragram.messenger.math;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001BJ\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012!\u0010\t\u001a\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R,\u0010\t\u001a\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lcom/exteragram/messenger/math/PrefixOperator;", _UrlKt.FRAGMENT_ENCODE_SET, "symbols", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "precedence", _UrlKt.FRAGMENT_ENCODE_SET, "operation", _UrlKt.FRAGMENT_ENCODE_SET, "apply", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ParameterName;", "name", "value", "<init>", "(Ljava/util/List;IZLkotlin/jvm/functions/Function1;)V", "getSymbols", "()Ljava/util/List;", "getPrecedence", "()I", "getOperation", "()Z", "getApply", "()Lkotlin/jvm/functions/Function1;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class PrefixOperator {
    private final Function1<Double, Double> apply;
    private final boolean operation;
    private final int precedence;
    private final List<String> symbols;

    /* JADX WARN: Multi-variable type inference failed */
    public PrefixOperator(List<String> r1, int r2, boolean r3, Function1<? super Double, Double> r4) {
        this.symbols = r1;
        this.precedence = r2;
        this.operation = r3;
        this.apply = r4;
    }

    public /* synthetic */ PrefixOperator(List r1, int r2, boolean r3, Function1 r4, int r5, DefaultConstructorMarker r6) {
        if ((r5 & 4) == 0) goto L5;
        r3 = false;
    L5:
        this(r1, r2, r3, r4);
    }

    public final Function1<Double, Double> getApply() {
        return this.apply;
    }

    public final boolean getOperation() {
        return this.operation;
    }

    public final int getPrecedence() {
        return this.precedence;
    }

    public final List<String> getSymbols() {
        return this.symbols;
    }
}
