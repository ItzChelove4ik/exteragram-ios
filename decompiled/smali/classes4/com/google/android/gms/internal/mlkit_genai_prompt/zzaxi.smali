.class public abstract Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxi;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final zza:[B


# direct methods
.method static constructor <clinit>()V
    .locals 7

    const/4 v0, 0x0

    .line 1
    new-array v2, v0, [B

    sput-object v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxi;->zza:[B

    invoke-static {v2}, Ljava/nio/ByteBuffer;->wrap([B)Ljava/nio/ByteBuffer;

    .line 2
    sget v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawe;->$r8$clinit:I

    .line 3
    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawd;

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    invoke-direct/range {v1 .. v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawd;-><init>([BIIZ[B)V

    .line 4
    :try_start_0
    invoke-virtual {v1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawc;->zza(I)I
    :try_end_0
    .catch Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxm; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception v0

    .line 5
    invoke-static {v0}, Landroidx/core/app/NavUtils$$ExternalSyntheticBUOutline0;->m(Ljava/lang/Throwable;)V

    return-void
.end method

.method public static zza(Z)I
    .locals 0

    if-eqz p0, :cond_0

    const/16 p0, 0x4cf

    return p0

    :cond_0
    const/16 p0, 0x4d5

    return p0
.end method

.method public static zzb(I[BII)I
    .locals 2

    move v0, p2

    :goto_0
    add-int v1, p2, p3

    if-ge v0, v1, :cond_0

    mul-int/lit8 p0, p0, 0x1f

    .line 1
    aget-byte v1, p1, v0

    add-int/2addr p0, v1

    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_0
    return p0
.end method
