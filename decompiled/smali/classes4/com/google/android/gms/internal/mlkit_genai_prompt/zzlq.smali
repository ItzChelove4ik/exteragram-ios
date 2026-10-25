.class final synthetic Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlq;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/util/function/Function;


# static fields
.field static final synthetic zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlq;


# direct methods
.method public static synthetic constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlq;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlq;-><init>()V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlq;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlq;

    return-void
.end method

.method private synthetic constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final synthetic apply(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 2

    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmr;

    .line 4
    iget p0, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzme;->zzb:I

    if-eqz p0, :cond_1

    .line 1
    iget-object v0, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzme;->zza:[Ljava/lang/Object;

    const/4 v1, 0x1

    if-eq p0, v1, :cond_0

    .line 4
    invoke-static {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzms;->zzk(I[Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzms;

    move-result-object p0

    .line 5
    invoke-virtual {p0}, Ljava/util/AbstractCollection;->size()I

    move-result v0

    iput v0, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzme;->zzb:I

    iput-boolean v1, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzme;->zzc:Z

    return-object p0

    :cond_0
    const/4 p0, 0x0

    .line 2
    aget-object p0, v0, p0

    invoke-static {p0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    new-instance p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznp;

    .line 3
    invoke-direct {p1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznp;-><init>(Ljava/lang/Object;)V

    return-object p1

    .line 1
    :cond_1
    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznm;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zznm;

    return-object p0
.end method
