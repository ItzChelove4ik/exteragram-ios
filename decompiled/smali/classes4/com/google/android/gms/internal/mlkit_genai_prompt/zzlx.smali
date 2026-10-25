.class public abstract Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlx;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlx;

.field private static final zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlx;

.field private static final zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlx;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlv;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlv;-><init>()V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlx;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlx;

    .line 2
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlw;

    const/4 v1, -0x1

    invoke-direct {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlw;-><init>(I)V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlx;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlx;

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlw;

    const/4 v1, 0x1

    invoke-direct {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlw;-><init>(I)V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlx;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlx;

    return-void
.end method

.method public synthetic constructor <init>([B)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static zzc()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlx;
    .locals 1

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlx;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlx;

    return-object v0
.end method

.method public static synthetic zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlx;
    .locals 1

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlx;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlx;

    return-object v0
.end method

.method public static synthetic zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlx;
    .locals 1

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlx;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlx;

    return-object v0
.end method

.method public static synthetic zzf()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlx;
    .locals 1

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlx;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlx;

    return-object v0
.end method


# virtual methods
.method public abstract zza(Ljava/lang/Comparable;Ljava/lang/Comparable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlx;
.end method

.method public abstract zzb()I
.end method
