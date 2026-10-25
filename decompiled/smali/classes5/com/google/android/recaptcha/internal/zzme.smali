.class public abstract Lcom/google/android/recaptcha/internal/zzme;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public static final zza(IILkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 0

    .line 1
    new-instance p3, Lcom/google/android/recaptcha/internal/zzmb;

    invoke-static {p1}, Lkotlin/coroutines/jvm/internal/Boxing;->boxInt(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-direct {p3, p0, p2, p1}, Lcom/google/android/recaptcha/internal/zzmb;-><init>(ILkotlin/jvm/functions/Function2;Ljava/lang/Integer;)V

    return-object p3
.end method
