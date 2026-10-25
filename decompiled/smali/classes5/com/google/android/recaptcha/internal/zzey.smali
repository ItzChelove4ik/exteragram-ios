.class final Lcom/google/android/recaptcha/internal/zzey;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field final synthetic zza:Lcom/google/android/recaptcha/internal/zzez;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzez;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzey;->zza:Lcom/google/android/recaptcha/internal/zzez;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p2}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 0

    new-instance p1, Lcom/google/android/recaptcha/internal/zzey;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzey;->zza:Lcom/google/android/recaptcha/internal/zzez;

    invoke-direct {p1, p0, p2}, Lcom/google/android/recaptcha/internal/zzey;-><init>(Lcom/google/android/recaptcha/internal/zzez;Lkotlin/coroutines/Continuation;)V

    return-object p1
.end method

.method public final bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzmg;

    check-cast p2, Lkotlin/coroutines/Continuation;

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzey;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzey;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzey;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 2

    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    .line 1
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzey;->zza:Lcom/google/android/recaptcha/internal/zzez;

    sget p1, Landroid/os/Build$VERSION;->SDK_INT:I

    .line 3
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzamb;->zzb()Lcom/google/android/recaptcha/internal/zzaly;

    move-result-object v0

    .line 4
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzama;->zza()Lcom/google/android/recaptcha/internal/zzalz;

    move-result-object v1

    invoke-static {p1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v1, p1}, Lcom/google/android/recaptcha/internal/zzalz;->zzc(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzalz;

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p1

    invoke-static {p1}, Lkotlin/collections/CollectionsKt;->listOf(Ljava/lang/Object;)Ljava/util/List;

    move-result-object p1

    check-cast p1, Ljava/lang/Iterable;

    .line 5
    invoke-virtual {v0, p1}, Lcom/google/android/recaptcha/internal/zzaly;->zzc(Ljava/lang/Iterable;)Lcom/google/android/recaptcha/internal/zzaly;

    .line 6
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p1

    check-cast p1, Lcom/google/android/recaptcha/internal/zzamb;

    .line 2
    invoke-static {p0, p1}, Lcom/google/android/recaptcha/internal/zzew;->zza(Lcom/google/android/recaptcha/internal/zzev;Lcom/google/android/recaptcha/internal/zzamb;)Lcom/google/android/recaptcha/internal/zzex;

    move-result-object p0

    return-object p0
.end method
