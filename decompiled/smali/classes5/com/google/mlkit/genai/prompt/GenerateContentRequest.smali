.class public final Lcom/google/mlkit/genai/prompt/GenerateContentRequest;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;,
        Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Companion;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\u0008\u0004\n\u0002\u0010\u0008\n\u0002\u0008\n\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0010\u000e\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0010\u000b\n\u0002\u0008\u0007\u0018\u0000 ,2\u00020\u0001:\u0002-,R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\u000c\n\u0004\u0008\u0003\u0010\u0004\u001a\u0004\u0008\u0005\u0010\u0006R\u0017\u0010\u0008\u001a\u00020\u00078\u0006\u00a2\u0006\u000c\n\u0004\u0008\u0008\u0010\t\u001a\u0004\u0008\n\u0010\u000bR\u0017\u0010\u000c\u001a\u00020\u00078\u0006\u00a2\u0006\u000c\n\u0004\u0008\u000c\u0010\t\u001a\u0004\u0008\r\u0010\u000bR\u0017\u0010\u000e\u001a\u00020\u00078\u0006\u00a2\u0006\u000c\n\u0004\u0008\u000e\u0010\t\u001a\u0004\u0008\u000f\u0010\u000bR\u0017\u0010\u0010\u001a\u00020\u00078\u0006\u00a2\u0006\u000c\n\u0004\u0008\u0010\u0010\t\u001a\u0004\u0008\u0011\u0010\u000bR\u001d\u0010\u0014\u001a\u0008\u0012\u0004\u0012\u00020\u00130\u00128\u0006\u00a2\u0006\u000c\n\u0004\u0008\u0014\u0010\u0015\u001a\u0004\u0008\u0016\u0010\u0017R\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00188\u0006\u00a2\u0006\u000c\n\u0004\u0008\u0019\u0010\u001a\u001a\u0004\u0008\u001b\u0010\u001cR\u0019\u0010\u001e\u001a\u0004\u0018\u00010\u001d8\u0006\u00a2\u0006\u000c\n\u0004\u0008\u001e\u0010\u001f\u001a\u0004\u0008 \u0010!R\u0019\u0010#\u001a\u0004\u0018\u00010\"8\u0006\u00a2\u0006\u000c\n\u0004\u0008#\u0010$\u001a\u0004\u0008%\u0010&R\u0017\u0010(\u001a\u00020\'8\u0006\u00a2\u0006\u000c\n\u0004\u0008(\u0010)\u001a\u0004\u0008*\u0010+\u00a8\u0006."
    }
    d2 = {
        "Lcom/google/mlkit/genai/prompt/GenerateContentRequest;",
        "",
        "",
        "temperature",
        "F",
        "getTemperature",
        "()F",
        "",
        "seed",
        "I",
        "getSeed",
        "()I",
        "topK",
        "getTopK",
        "candidateCount",
        "getCandidateCount",
        "maxOutputTokens",
        "getMaxOutputTokens",
        "",
        "Lcom/google/mlkit/genai/prompt/Content;",
        "contents",
        "Ljava/util/List;",
        "getContents",
        "()Ljava/util/List;",
        "Lcom/google/mlkit/genai/prompt/PromptPrefix;",
        "promptPrefix",
        "Lcom/google/mlkit/genai/prompt/PromptPrefix;",
        "getPromptPrefix",
        "()Lcom/google/mlkit/genai/prompt/PromptPrefix;",
        "",
        "cachedContextName",
        "Ljava/lang/String;",
        "getCachedContextName",
        "()Ljava/lang/String;",
        "Lcom/google/mlkit/genai/prompt/SystemInstruction;",
        "systemInstruction",
        "Lcom/google/mlkit/genai/prompt/SystemInstruction;",
        "getSystemInstruction",
        "()Lcom/google/mlkit/genai/prompt/SystemInstruction;",
        "",
        "enableThinking",
        "Z",
        "getEnableThinking",
        "()Z",
        "Companion",
        "Builder",
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
.field public static final Companion:Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Companion;


# instance fields
.field private final zza:F

.field private final zzb:I

.field private final zzc:I

.field private final zzd:I

.field private final zze:I

.field private final zzf:Ljava/util/List;

.field private final zzg:Lcom/google/mlkit/genai/prompt/PromptPrefix;

.field private final zzh:Ljava/lang/String;

.field private final zzi:Lcom/google/mlkit/genai/prompt/SystemInstruction;

.field private final zzj:Z


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->Companion:Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Companion;

    return-void
.end method

.method public synthetic constructor <init>(FIIIILjava/util/List;Lcom/google/mlkit/genai/prompt/PromptPrefix;Ljava/lang/String;Lcom/google/mlkit/genai/prompt/SystemInstruction;ZLkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput p1, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->zza:F

    iput p2, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->zzb:I

    iput p3, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->zzc:I

    iput p4, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->zzd:I

    iput p5, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->zze:I

    iput-object p6, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->zzf:Ljava/util/List;

    iput-object p7, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->zzg:Lcom/google/mlkit/genai/prompt/PromptPrefix;

    iput-object p8, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->zzh:Ljava/lang/String;

    iput-object p9, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->zzi:Lcom/google/mlkit/genai/prompt/SystemInstruction;

    iput-boolean p10, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->zzj:Z

    return-void
.end method


# virtual methods
.method public final getCachedContextName()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->zzh:Ljava/lang/String;

    return-object p0
.end method

.method public final getCandidateCount()I
    .locals 0

    iget p0, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->zzd:I

    return p0
.end method

.method public final getContents()Ljava/util/List;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Lcom/google/mlkit/genai/prompt/Content;",
            ">;"
        }
    .end annotation

    iget-object p0, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->zzf:Ljava/util/List;

    return-object p0
.end method

.method public final getEnableThinking()Z
    .locals 0

    iget-boolean p0, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->zzj:Z

    return p0
.end method

.method public final getMaxOutputTokens()I
    .locals 0

    iget p0, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->zze:I

    return p0
.end method

.method public final getPromptPrefix()Lcom/google/mlkit/genai/prompt/PromptPrefix;
    .locals 0

    iget-object p0, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->zzg:Lcom/google/mlkit/genai/prompt/PromptPrefix;

    return-object p0
.end method

.method public final getSeed()I
    .locals 0

    iget p0, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->zzb:I

    return p0
.end method

.method public final getSystemInstruction()Lcom/google/mlkit/genai/prompt/SystemInstruction;
    .locals 0

    iget-object p0, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->zzi:Lcom/google/mlkit/genai/prompt/SystemInstruction;

    return-object p0
.end method

.method public final getTemperature()F
    .locals 0

    iget p0, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->zza:F

    return p0
.end method

.method public final getTopK()I
    .locals 0

    iget p0, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->zzc:I

    return p0
.end method

.method public final zza(Lcom/google/mlkit/genai/prompt/ImagePart;Lcom/google/mlkit/genai/prompt/TextPart;)Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;
    .locals 0

    .line 1
    new-instance p1, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;

    iget-object p2, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->zzf:Ljava/util/List;

    invoke-direct {p1, p2}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;-><init>(Ljava/util/List;)V

    iget p2, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->zza:F

    .line 2
    invoke-static {p2}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object p2

    invoke-virtual {p1, p2}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->setTemperature(Ljava/lang/Float;)V

    iget p2, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->zzb:I

    .line 3
    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    invoke-virtual {p1, p2}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->setSeed(Ljava/lang/Integer;)V

    iget p2, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->zzc:I

    .line 4
    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    invoke-virtual {p1, p2}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->setTopK(Ljava/lang/Integer;)V

    iget p2, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->zzd:I

    .line 5
    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    invoke-virtual {p1, p2}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->setCandidateCount(Ljava/lang/Integer;)V

    iget p2, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->zze:I

    .line 6
    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    invoke-virtual {p1, p2}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->setMaxOutputTokens(Ljava/lang/Integer;)V

    iget-object p2, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->zzg:Lcom/google/mlkit/genai/prompt/PromptPrefix;

    .line 7
    invoke-virtual {p1, p2}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->setPromptPrefix(Lcom/google/mlkit/genai/prompt/PromptPrefix;)V

    iget-object p2, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->zzh:Ljava/lang/String;

    .line 8
    invoke-virtual {p1, p2}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->setCachedContextName(Ljava/lang/String;)V

    iget-object p2, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->zzi:Lcom/google/mlkit/genai/prompt/SystemInstruction;

    .line 9
    invoke-virtual {p1, p2}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->setSystemInstruction(Lcom/google/mlkit/genai/prompt/SystemInstruction;)V

    iget-boolean p0, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->zzj:Z

    .line 10
    invoke-virtual {p1, p0}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->setEnableThinking(Z)V

    return-object p1
.end method
