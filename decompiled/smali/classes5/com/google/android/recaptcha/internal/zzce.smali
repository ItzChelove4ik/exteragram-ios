.class public final Lcom/google/android/recaptcha/internal/zzce;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzcf;


# instance fields
.field private final zza:Lkotlinx/coroutines/CompletableDeferred;


# direct methods
.method public constructor <init>(Lkotlinx/coroutines/CompletableDeferred;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzce;->zza:Lkotlinx/coroutines/CompletableDeferred;

    return-void
.end method


# virtual methods
.method public final zza()Lkotlinx/coroutines/CompletableDeferred;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzce;->zza:Lkotlinx/coroutines/CompletableDeferred;

    return-object p0
.end method
