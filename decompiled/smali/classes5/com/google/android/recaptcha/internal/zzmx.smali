.class public interface abstract Lcom/google/android/recaptcha/internal/zzmx;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public static synthetic zzc(Lcom/google/android/recaptcha/internal/zzmx;Ljava/lang/String;[BLjava/util/Map;Lkotlin/coroutines/Continuation;ILjava/lang/Object;)Ljava/lang/Object;
    .locals 0

    .line 1
    invoke-static {}, Lkotlin/collections/MapsKt;->emptyMap()Ljava/util/Map;

    move-result-object p1

    const-string p2, "https://www.gstatic.com/recaptcha/verify_key/orcas/prod/android/verify_key.txt"

    const/4 p3, 0x0

    .line 2
    invoke-interface {p0, p2, p3, p1, p4}, Lcom/google/android/recaptcha/internal/zzmx;->zzb(Ljava/lang/String;[BLjava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method


# virtual methods
.method public abstract zza(Ljava/lang/String;[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;
.end method

.method public abstract zzb(Ljava/lang/String;[BLjava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
.end method
