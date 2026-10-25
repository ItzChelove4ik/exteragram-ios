.class final synthetic Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfe;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlc;


# static fields
.field static final synthetic zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfe;


# direct methods
.method public static synthetic constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfe;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfe;-><init>()V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfe;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfe;

    return-void
.end method

.method private synthetic constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final synthetic zza()Ljava/lang/Object;
    .locals 2

    sget p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->$r8$clinit:I

    .line 1
    const-string p0, "AICore service disconnected"

    const/4 v0, 0x0

    const/4 v1, 0x6

    invoke-static {v1, p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zzc(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    move-result-object p0

    return-object p0
.end method
