.class final synthetic Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgi;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoj;


# instance fields
.field private final synthetic zza:I


# direct methods
.method public synthetic constructor <init>(II)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgi;->zza:I

    return-void
.end method


# virtual methods
.method public final synthetic zza(Ljava/lang/Object;)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 1

    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzan;

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgi;->zza:I

    const/4 v0, -0x1

    invoke-static {p0, v0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzk(IILcom/google/android/gms/internal/mlkit_genai_prompt/zzan;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method
