.class final synthetic Lcom/google/android/gms/internal/mlkit_genai_prompt/zzie;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/util/function/Function;


# static fields
.field static final synthetic zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzie;


# direct methods
.method public static synthetic constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzie;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzie;-><init>()V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzie;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzie;

    return-void
.end method

.method private synthetic constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final synthetic apply(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 3

    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzy;

    sget p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzig;->$r8$clinit:I

    .line 1
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzy;->zza()I

    move-result p0

    .line 2
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzy;->zzb()I

    move-result v0

    .line 3
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzy;->zzc()Ljava/lang/String;

    move-result-object v1

    .line 4
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzy;->zzd()Ljava/lang/String;

    move-result-object p1

    new-instance v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhc;

    .line 5
    invoke-direct {v2, p0, v0, v1, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhc;-><init>(IILjava/lang/String;Ljava/lang/String;)V

    return-object v2
.end method
