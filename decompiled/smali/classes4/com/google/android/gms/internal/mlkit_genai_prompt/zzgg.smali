.class final synthetic Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgg;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/concurrent/futures/CallbackToFutureAdapter$Resolver;


# instance fields
.field private final synthetic zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;

.field private final synthetic zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdh;

.field private final synthetic zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

.field private final synthetic zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;


# direct methods
.method public synthetic constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdh;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgg;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgg;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdh;

    iput-object p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgg;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

    iput-object p4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgg;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;

    return-void
.end method


# virtual methods
.method public final synthetic attachCompleter(Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)Ljava/lang/Object;
    .locals 3

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgg;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgg;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdh;

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgg;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgg;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;

    invoke-virtual {v0, v1, v2, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzn(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdh;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method
