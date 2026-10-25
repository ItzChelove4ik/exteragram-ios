.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxv;
.super Lkotlin/coroutines/jvm/internal/ContinuationImpl;
.source "SourceFile"


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:Ljava/lang/Object;

.field zzc:Ljava/lang/Object;

.field zzd:I

.field synthetic zze:Ljava/lang/Object;

.field final synthetic zzf:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;

.field zzg:I

.field zzh:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;


# direct methods
.method public constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxv;->zzf:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;

    invoke-direct {p0, p2}, Lkotlin/coroutines/jvm/internal/ContinuationImpl;-><init>(Lkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 1

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxv;->zze:Ljava/lang/Object;

    iget p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxv;->zzg:I

    const/high16 v0, -0x80000000

    or-int/2addr p1, v0

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxv;->zzg:I

    iget-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxv;->zzf:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;

    invoke-static {p1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyd;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method
