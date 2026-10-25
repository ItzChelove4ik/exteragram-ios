.class public abstract Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhs;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static zzi(ILandroid/graphics/Bitmap;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhs;
    .locals 9

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhd;

    const/4 v7, 0x0

    const/4 v8, 0x1

    const/4 v1, 0x0

    const/4 v2, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    move-object v3, p1

    invoke-direct/range {v0 .. v8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhd;-><init>(ILjava/lang/String;Landroid/graphics/Bitmap;Landroid/net/Uri;Landroid/net/Uri;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhq;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhr;I)V

    return-object v0
.end method

.method public static zzj(ILjava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhs;
    .locals 9

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhd;

    const/4 v7, 0x0

    const/4 v8, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    move v1, p0

    move-object v2, p1

    invoke-direct/range {v0 .. v8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhd;-><init>(ILjava/lang/String;Landroid/graphics/Bitmap;Landroid/net/Uri;Landroid/net/Uri;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhq;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhr;I)V

    return-object v0
.end method


# virtual methods
.method public abstract zza()I
.end method

.method public abstract zzb()Ljava/lang/String;
.end method

.method public abstract zzc()Landroid/graphics/Bitmap;
.end method

.method public abstract zzd()Landroid/net/Uri;
.end method

.method public abstract zze()Landroid/net/Uri;
.end method

.method public abstract zzf()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhq;
.end method

.method public abstract zzg()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhr;
.end method

.method public abstract zzh()I
.end method
