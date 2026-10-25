.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaaj;
.super Lkotlin/coroutines/jvm/internal/ContinuationImpl;
.source "SourceFile"


# instance fields
.field synthetic zza:Ljava/lang/Object;

.field final synthetic zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;

.field zzc:I

.field zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;

.field zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;


# direct methods
.method public constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaaj;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;

    invoke-direct {p0, p2}, Lkotlin/coroutines/jvm/internal/ContinuationImpl;-><init>(Lkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 1

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaaj;->zza:Ljava/lang/Object;

    iget p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaaj;->zzc:I

    const/high16 v0, -0x80000000

    or-int/2addr p1, v0

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaaj;->zzc:I

    iget-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaaj;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;

    const/4 v0, 0x0

    invoke-static {p1, v0, v0, v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaal;Lcom/google/mlkit/genai/common/StreamingCallback;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method
