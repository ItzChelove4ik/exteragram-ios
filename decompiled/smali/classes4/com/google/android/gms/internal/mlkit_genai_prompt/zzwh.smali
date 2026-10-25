.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwh;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field final synthetic zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;

.field final synthetic zzb:Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;


# direct methods
.method public constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwh;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwh;->zzb:Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final zza(Z)V
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwh;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;

    invoke-static {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;->zzh(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;)Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwh;->zzb:Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;

    invoke-virtual {v0, p0}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpk;

    if-eqz p0, :cond_0

    invoke-static {p1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p1

    invoke-virtual {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpk;->zzh(Ljava/lang/Object;)Z

    :cond_0
    return-void
.end method
