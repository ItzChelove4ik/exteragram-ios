.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;
.super Lkotlin/coroutines/jvm/internal/ContinuationImpl;
.source "SourceFile"


# instance fields
.field zza:Ljava/lang/Object;

.field zzb:Ljava/lang/Object;

.field synthetic zzc:Ljava/lang/Object;

.field final synthetic zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;

.field zze:I

.field zzf:Ljava/lang/String;

.field zzg:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;


# direct methods
.method public constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;Lkotlin/coroutines/Continuation;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;

    invoke-direct {p0, p2}, Lkotlin/coroutines/jvm/internal/ContinuationImpl;-><init>(Lkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 1

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;->zzc:Ljava/lang/Object;

    iget p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;->zze:I

    const/high16 v0, -0x80000000

    or-int/2addr p1, v0

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;->zze:I

    iget-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaad;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;

    const/4 v0, 0x0

    invoke-static {p1, v0, v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;->zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaag;Landroid/content/Context;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaab;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method
