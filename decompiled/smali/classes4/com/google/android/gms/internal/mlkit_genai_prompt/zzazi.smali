.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzazi;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxd;


# static fields
.field static final zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxd;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzazi;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzazi;-><init>()V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzazi;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxd;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final zza(I)Z
    .locals 0

    invoke-static {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzazj;->zzb(I)I

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method
