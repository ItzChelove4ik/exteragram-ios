.class final synthetic Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgo;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/util/function/Consumer;


# instance fields
.field private final synthetic zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgp;

.field private final synthetic zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwh;


# direct methods
.method public synthetic constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgp;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwh;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgo;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgp;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgo;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwh;

    return-void
.end method


# virtual methods
.method public final synthetic accept(Ljava/lang/Object;)V
    .locals 1

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgo;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgp;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgo;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwh;

    check-cast p1, Landroid/util/Pair;

    invoke-virtual {v0, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgp;->zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwh;Landroid/util/Pair;)V

    return-void
.end method
