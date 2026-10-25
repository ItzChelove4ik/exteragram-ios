.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxu;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxj;


# static fields
.field public static final zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxu;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxu;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxu;-><init>()V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxu;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxu;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final bridge synthetic zza(Lorg/json/JSONObject;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxi;
    .locals 4

    .line 1
    const-string p0, "name"

    .line 2
    invoke-virtual {p1, p0}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    const-string v0, "path"

    .line 3
    invoke-virtual {p1, v0}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    const-string v1, "prompt_prefix"

    .line 4
    invoke-virtual {p1, v1}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    const-string v2, "model_name"

    const-string v3, "unknown"

    .line 5
    invoke-virtual {p1, v2, v3}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    .line 6
    new-instance v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxt;

    .line 7
    invoke-direct {v2, p0, v0, v1, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzxt;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    return-object v2
.end method
