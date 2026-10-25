.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;
.super Lkotlin/coroutines/jvm/internal/ContinuationImpl;
.source "SourceFile"


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:Ljava/lang/Object;

.field zzc:Z

.field synthetic zzd:Ljava/lang/Object;

.field final synthetic zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;

.field zzf:I

.field zzg:Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

.field zzh:Ljava/lang/String;

.field zzi:Ljava/lang/String;


# direct methods
.method public constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;

    invoke-direct {p0, p2}, Lkotlin/coroutines/jvm/internal/ContinuationImpl;-><init>(Lkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 6

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzd:Ljava/lang/Object;

    iget p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzf:I

    const/high16 v0, -0x80000000

    or-int/2addr p1, v0

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zzf:I

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzn;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;

    const/4 v3, 0x0

    const/4 v4, 0x0

    const/4 v1, 0x0

    const/4 v2, 0x0

    move-object v5, p0

    invoke-static/range {v0 .. v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;->zzj(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lcom/google/mlkit/genai/common/StreamingCallback;Lkotlin/reflect/KClass;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method
