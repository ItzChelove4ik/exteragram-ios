.class final synthetic Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaui;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zzks;


# instance fields
.field private final synthetic zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;

.field private final synthetic zzb:Ljava/util/concurrent/atomic/AtomicLong;

.field private final synthetic zzc:J

.field private final synthetic zzd:Ljava/lang/Object;


# direct methods
.method public synthetic constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;Ljava/util/concurrent/atomic/AtomicLong;JLjava/lang/Object;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaui;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaui;->zzb:Ljava/util/concurrent/atomic/AtomicLong;

    iput-wide p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaui;->zzc:J

    iput-object p5, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaui;->zzd:Ljava/lang/Object;

    return-void
.end method


# virtual methods
.method public final synthetic zza(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 6

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaui;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaui;->zzb:Ljava/util/concurrent/atomic/AtomicLong;

    iget-wide v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaui;->zzc:J

    iget-object v4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaui;->zzd:Ljava/lang/Object;

    move-object v5, p1

    invoke-virtual/range {v0 .. v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzv(Ljava/util/concurrent/atomic/AtomicLong;JLjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method
