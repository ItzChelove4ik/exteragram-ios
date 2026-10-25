.class public abstract Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhu;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static zzc()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzht;
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhe;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhe;-><init>()V

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzk()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhe;->zza(Ljava/util/List;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzht;

    return-object v0
.end method


# virtual methods
.method public abstract zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;
.end method

.method public abstract zzb()Landroid/os/ParcelFileDescriptor;
.end method
