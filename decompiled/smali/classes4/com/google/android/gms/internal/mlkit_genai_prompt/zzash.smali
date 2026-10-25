.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzash;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final zza:Ljava/lang/Integer;

.field private final zzb:Ljava/lang/Integer;


# direct methods
.method public synthetic constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzasg;[B)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzasg;->zzd()Ljava/lang/Integer;

    move-result-object p2

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzash;->zza:Ljava/lang/Integer;

    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzasg;->zze()Ljava/lang/Integer;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzash;->zzb:Ljava/lang/Integer;

    return-void
.end method


# virtual methods
.method public final zza()Ljava/lang/Integer;
    .locals 0
    .annotation build Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpv;
        zza = 0x1
    .end annotation

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzash;->zza:Ljava/lang/Integer;

    return-object p0
.end method

.method public final zzb()Ljava/lang/Integer;
    .locals 0
    .annotation build Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpv;
        zza = 0x2
    .end annotation

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzash;->zzb:Ljava/lang/Integer;

    return-object p0
.end method
