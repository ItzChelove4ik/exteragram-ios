.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyb;
.super Lkotlin/coroutines/jvm/internal/ContinuationImpl;
.source "SourceFile"


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:Ljava/lang/Object;

.field zzc:Ljava/lang/Object;

.field zzd:Ljava/lang/Object;

.field synthetic zze:Ljava/lang/Object;

.field final synthetic zzf:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;

.field zzg:I


# direct methods
.method public constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyb;->zzf:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;

    invoke-direct {p0, p2}, Lkotlin/coroutines/jvm/internal/ContinuationImpl;-><init>(Lkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 7

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyb;->zze:Ljava/lang/Object;

    iget p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyb;->zzg:I

    const/high16 v0, -0x80000000

    or-int/2addr p1, v0

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyb;->zzg:I

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyb;->zzf:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v1, 0x0

    const/4 v2, 0x0

    const/4 v3, 0x0

    move-object v6, p0

    invoke-static/range {v0 .. v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;->zzh(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaur;Lcom/google/mlkit/genai/common/DownloadCallback;ZLkotlinx/coroutines/CompletableDeferred;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method
