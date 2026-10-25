.class public abstract Lcom/google/android/gms/internal/mlkit_genai_prompt/zzja;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzja;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 2
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzk()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v0

    .line 3
    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zziu;

    if-nez v0, :cond_0

    .line 4
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzk()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v0

    goto :goto_0

    .line 5
    :cond_0
    invoke-static {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzq(Ljava/util/Collection;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v0

    :goto_0
    invoke-direct {v1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zziu;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;)V

    sput-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzja;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzja;

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public abstract zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;
.end method
