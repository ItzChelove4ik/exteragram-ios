.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxk;
.super Lkotlin/coroutines/jvm/internal/ContinuationImpl;
.source "SourceFile"


# instance fields
.field synthetic zza:Ljava/lang/Object;

.field final synthetic zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxo;

.field zzc:I

.field zzd:Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;

.field zze:Ljava/lang/String;

.field zzf:Ljava/lang/String;


# direct methods
.method public constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxo;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxk;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxo;

    invoke-direct {p0, p2}, Lkotlin/coroutines/jvm/internal/ContinuationImpl;-><init>(Lkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 1

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxk;->zza:Ljava/lang/Object;

    iget p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxk;->zzc:I

    const/high16 v0, -0x80000000

    or-int/2addr p1, v0

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxk;->zzc:I

    iget-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxk;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxo;

    const/4 v0, 0x0

    invoke-virtual {p1, v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxo;->create(Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method
