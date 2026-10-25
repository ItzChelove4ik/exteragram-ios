.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzh;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxx;


# static fields
.field private static final zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzh;


# instance fields
.field private zzb:I

.field private zzd:Z


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzh;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzh;-><init>()V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzh;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzh;

    const-class v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzh;

    invoke-static {v1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzN(Ljava/lang/Class;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;)V

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .line 1
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;-><init>()V

    return-void
.end method

.method public static zzc([BLcom/google/android/gms/internal/mlkit_genai_prompt/zzawl;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzh;
    .locals 1

    .line 1
    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzh;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzh;

    invoke-static {v0, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzR(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;[BLcom/google/android/gms/internal/mlkit_genai_prompt/zzawl;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    move-result-object p0

    check-cast p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzh;

    return-object p0
.end method

.method public static zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzh;
    .locals 1

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzh;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzh;

    return-object v0
.end method

.method public static synthetic zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzh;
    .locals 1

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzh;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzh;

    return-object v0
.end method


# virtual methods
.method public final zza()Z
    .locals 0

    iget-boolean p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzh;->zzd:Z

    return p0
.end method

.method public final zzb(ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    add-int/lit8 p1, p1, -0x1

    if-eqz p1, :cond_4

    const/4 p0, 0x2

    if-eq p1, p0, :cond_3

    const/4 p0, 0x3

    if-eq p1, p0, :cond_2

    const/4 p0, 0x4

    const/4 p2, 0x0

    if-eq p1, p0, :cond_1

    const/4 p0, 0x5

    if-ne p1, p0, :cond_0

    .line 1
    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzh;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzh;

    return-object p0

    .line 4
    :cond_0
    throw p2

    .line 2
    :cond_1
    new-instance p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzg;

    .line 3
    invoke-direct {p0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzg;-><init>([B)V

    return-object p0

    :cond_2
    new-instance p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzh;

    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzh;-><init>()V

    return-object p0

    .line 1
    :cond_3
    const-string p0, "zzb"

    const-string p1, "zzd"

    filled-new-array {p0, p1}, [Ljava/lang/Object;

    move-result-object p0

    sget-object p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzh;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzh;

    const-string p2, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u1007\u0000"

    .line 2
    invoke-static {p1, p2, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzO(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxw;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0

    :cond_4
    const/4 p0, 0x1

    .line 1
    invoke-static {p0}, Ljava/lang/Byte;->valueOf(B)Ljava/lang/Byte;

    move-result-object p0

    return-object p0
.end method
