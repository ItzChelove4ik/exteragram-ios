.class public final Lcom/google/mlkit/genai/prompt/ModelConfig;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/google/mlkit/genai/prompt/ModelConfig$Builder;,
        Lcom/google/mlkit/genai/prompt/ModelConfig$Companion;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0008\n\u0002\u0008\u0007\n\u0002\u0010\u000b\n\u0002\u0008\u0005\u0018\u0000 \u000f2\u00020\u0001:\u0002\u000e\u000fJ\u0014\u0010\n\u001a\u00020\u000b2\u0008\u0010\u000c\u001a\u0004\u0018\u00010\u0001H\u0096\u0082\u0004J\n\u0010\r\u001a\u00020\u0003H\u0096\u0080\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0086\u0004\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0007\u0010\u0008R\u0014\u0010\u0004\u001a\u00020\u0003X\u0086\u0004\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\t\u0010\u0008\u00a8\u0006\u0010"
    }
    d2 = {
        "Lcom/google/mlkit/genai/prompt/ModelConfig;",
        "",
        "releaseStage",
        "",
        "preference",
        "<init>",
        "(II)V",
        "getReleaseStage",
        "()I",
        "getPreference",
        "equals",
        "",
        "other",
        "hashCode",
        "Builder",
        "Companion",
        "java.com.google.android.libraries.mlkit.granules.genai.prompt_mlkit_genai_prompt"
    }
    k = 0x1
    mv = {
        0x2,
        0x3,
        0x0
    }
    xi = 0x30
.end annotation


# static fields
.field public static final Companion:Lcom/google/mlkit/genai/prompt/ModelConfig$Companion;

.field private static final zzc:Lcom/google/mlkit/genai/prompt/ModelConfig;


# instance fields
.field private final zza:I

.field private final zzb:I


# direct methods
.method static constructor <clinit>()V
    .locals 3

    new-instance v0, Lcom/google/mlkit/genai/prompt/ModelConfig$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/google/mlkit/genai/prompt/ModelConfig$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/google/mlkit/genai/prompt/ModelConfig;->Companion:Lcom/google/mlkit/genai/prompt/ModelConfig$Companion;

    new-instance v0, Lcom/google/mlkit/genai/prompt/ModelConfig;

    const/4 v1, 0x0

    const/4 v2, 0x2

    invoke-direct {v0, v1, v2}, Lcom/google/mlkit/genai/prompt/ModelConfig;-><init>(II)V

    sput-object v0, Lcom/google/mlkit/genai/prompt/ModelConfig;->zzc:Lcom/google/mlkit/genai/prompt/ModelConfig;

    return-void
.end method

.method private constructor <init>(II)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput p1, p0, Lcom/google/mlkit/genai/prompt/ModelConfig;->zza:I

    iput p2, p0, Lcom/google/mlkit/genai/prompt/ModelConfig;->zzb:I

    return-void
.end method

.method public synthetic constructor <init>(IILkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lcom/google/mlkit/genai/prompt/ModelConfig;-><init>(II)V

    return-void
.end method

.method public static final builder()Lcom/google/mlkit/genai/prompt/ModelConfig$Builder;
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    sget-object v0, Lcom/google/mlkit/genai/prompt/ModelConfig;->Companion:Lcom/google/mlkit/genai/prompt/ModelConfig$Companion;

    invoke-virtual {v0}, Lcom/google/mlkit/genai/prompt/ModelConfig$Companion;->builder()Lcom/google/mlkit/genai/prompt/ModelConfig$Builder;

    move-result-object v0

    return-object v0
.end method

.method public static final synthetic zza()Lcom/google/mlkit/genai/prompt/ModelConfig;
    .locals 1

    sget-object v0, Lcom/google/mlkit/genai/prompt/ModelConfig;->zzc:Lcom/google/mlkit/genai/prompt/ModelConfig;

    return-object v0
.end method


# virtual methods
.method public equals(Ljava/lang/Object;)Z
    .locals 4

    const/4 v0, 0x1

    if-ne p0, p1, :cond_0

    return v0

    .line 1
    :cond_0
    instance-of v1, p1, Lcom/google/mlkit/genai/prompt/ModelConfig;

    const/4 v2, 0x0

    if-nez v1, :cond_1

    return v2

    :cond_1
    iget v1, p0, Lcom/google/mlkit/genai/prompt/ModelConfig;->zza:I

    check-cast p1, Lcom/google/mlkit/genai/prompt/ModelConfig;

    iget v3, p1, Lcom/google/mlkit/genai/prompt/ModelConfig;->zza:I

    if-eq v1, v3, :cond_2

    return v2

    :cond_2
    iget p0, p0, Lcom/google/mlkit/genai/prompt/ModelConfig;->zzb:I

    .line 2
    iget p1, p1, Lcom/google/mlkit/genai/prompt/ModelConfig;->zzb:I

    if-eq p0, p1, :cond_3

    return v2

    :cond_3
    return v0
.end method

.method public final getPreference()I
    .locals 0

    iget p0, p0, Lcom/google/mlkit/genai/prompt/ModelConfig;->zzb:I

    return p0
.end method

.method public final getReleaseStage()I
    .locals 0

    iget p0, p0, Lcom/google/mlkit/genai/prompt/ModelConfig;->zza:I

    return p0
.end method

.method public hashCode()I
    .locals 1

    iget v0, p0, Lcom/google/mlkit/genai/prompt/ModelConfig;->zza:I

    mul-int/lit8 v0, v0, 0x1f

    iget p0, p0, Lcom/google/mlkit/genai/prompt/ModelConfig;->zzb:I

    add-int/2addr v0, p0

    return v0
.end method
