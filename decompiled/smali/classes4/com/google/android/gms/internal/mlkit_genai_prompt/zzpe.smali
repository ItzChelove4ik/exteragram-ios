.class final synthetic Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpe;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/util/concurrent/Executor;


# instance fields
.field private final synthetic zza:Ljava/util/concurrent/Executor;

.field private final synthetic zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoc;


# direct methods
.method public synthetic constructor <init>(Ljava/util/concurrent/Executor;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoc;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpe;->zza:Ljava/util/concurrent/Executor;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpe;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoc;

    return-void
.end method


# virtual methods
.method public final synthetic execute(Ljava/lang/Runnable;)V
    .locals 1

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpe;->zza:Ljava/util/concurrent/Executor;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpe;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoc;

    invoke-static {v0, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpi;->zzd(Ljava/util/concurrent/Executor;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoc;Ljava/lang/Runnable;)V

    return-void
.end method
