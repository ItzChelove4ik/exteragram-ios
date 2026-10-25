.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzafg;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/firebase/encoders/ObjectEncoder;


# static fields
.field static final zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzafg;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzafg;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzafg;-><init>()V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzafg;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzafg;

    const-string v0, "options"

    invoke-static {v0}, Lcom/google/firebase/encoders/FieldDescriptor;->builder(Ljava/lang/String;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-direct {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;-><init>()V

    const/4 v2, 0x1

    .line 2
    invoke-virtual {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpv;

    move-result-object v1

    .line 3
    invoke-virtual {v0, v1}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->withProperty(Ljava/lang/annotation/Annotation;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    .line 4
    invoke-virtual {v0}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->build()Lcom/google/firebase/encoders/FieldDescriptor;

    const-string v0, "sessionId"

    .line 5
    invoke-static {v0}, Lcom/google/firebase/encoders/FieldDescriptor;->builder(Ljava/lang/String;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-direct {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;-><init>()V

    const/4 v2, 0x2

    .line 6
    invoke-virtual {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpv;

    move-result-object v1

    .line 7
    invoke-virtual {v0, v1}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->withProperty(Ljava/lang/annotation/Annotation;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    .line 8
    invoke-virtual {v0}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->build()Lcom/google/firebase/encoders/FieldDescriptor;

    const-string v0, "sessionDurationMs"

    .line 9
    invoke-static {v0}, Lcom/google/firebase/encoders/FieldDescriptor;->builder(Ljava/lang/String;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-direct {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;-><init>()V

    const/4 v2, 0x3

    .line 10
    invoke-virtual {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpv;

    move-result-object v1

    .line 11
    invoke-virtual {v0, v1}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->withProperty(Ljava/lang/annotation/Annotation;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    .line 12
    invoke-virtual {v0}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->build()Lcom/google/firebase/encoders/FieldDescriptor;

    const-string v0, "callerAppId"

    .line 13
    invoke-static {v0}, Lcom/google/firebase/encoders/FieldDescriptor;->builder(Ljava/lang/String;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-direct {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;-><init>()V

    const/4 v2, 0x4

    .line 14
    invoke-virtual {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpv;

    move-result-object v1

    .line 15
    invoke-virtual {v0, v1}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->withProperty(Ljava/lang/annotation/Annotation;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    .line 16
    invoke-virtual {v0}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->build()Lcom/google/firebase/encoders/FieldDescriptor;

    const-string v0, "numRetakes"

    .line 17
    invoke-static {v0}, Lcom/google/firebase/encoders/FieldDescriptor;->builder(Ljava/lang/String;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-direct {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;-><init>()V

    const/4 v2, 0x5

    .line 18
    invoke-virtual {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpv;

    move-result-object v1

    .line 19
    invoke-virtual {v0, v1}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->withProperty(Ljava/lang/annotation/Annotation;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    .line 20
    invoke-virtual {v0}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->build()Lcom/google/firebase/encoders/FieldDescriptor;

    const-string v0, "pages"

    .line 21
    invoke-static {v0}, Lcom/google/firebase/encoders/FieldDescriptor;->builder(Ljava/lang/String;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-direct {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;-><init>()V

    const/4 v2, 0x6

    .line 22
    invoke-virtual {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpv;

    move-result-object v1

    .line 23
    invoke-virtual {v0, v1}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->withProperty(Ljava/lang/annotation/Annotation;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    .line 24
    invoke-virtual {v0}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->build()Lcom/google/firebase/encoders/FieldDescriptor;

    const-string v0, "isSessionCanceled"

    .line 25
    invoke-static {v0}, Lcom/google/firebase/encoders/FieldDescriptor;->builder(Ljava/lang/String;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-direct {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;-><init>()V

    const/4 v2, 0x7

    .line 26
    invoke-virtual {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpv;

    move-result-object v1

    .line 27
    invoke-virtual {v0, v1}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->withProperty(Ljava/lang/annotation/Annotation;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    .line 28
    invoke-virtual {v0}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->build()Lcom/google/firebase/encoders/FieldDescriptor;

    const-string v0, "autoEnhancementDisablementReason"

    .line 29
    invoke-static {v0}, Lcom/google/firebase/encoders/FieldDescriptor;->builder(Ljava/lang/String;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-direct {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;-><init>()V

    const/16 v2, 0x8

    .line 30
    invoke-virtual {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpv;

    move-result-object v1

    .line 31
    invoke-virtual {v0, v1}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->withProperty(Ljava/lang/annotation/Annotation;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    .line 32
    invoke-virtual {v0}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->build()Lcom/google/firebase/encoders/FieldDescriptor;

    const-string v0, "numEnhanceButtonClicks"

    .line 33
    invoke-static {v0}, Lcom/google/firebase/encoders/FieldDescriptor;->builder(Ljava/lang/String;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-direct {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;-><init>()V

    const/16 v2, 0x9

    .line 34
    invoke-virtual {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpv;

    move-result-object v1

    .line 35
    invoke-virtual {v0, v1}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->withProperty(Ljava/lang/annotation/Annotation;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    .line 36
    invoke-virtual {v0}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->build()Lcom/google/firebase/encoders/FieldDescriptor;

    const-string v0, "experienceType"

    .line 37
    invoke-static {v0}, Lcom/google/firebase/encoders/FieldDescriptor;->builder(Ljava/lang/String;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-direct {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;-><init>()V

    const/16 v2, 0xa

    .line 38
    invoke-virtual {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpv;

    move-result-object v1

    .line 39
    invoke-virtual {v0, v1}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->withProperty(Ljava/lang/annotation/Annotation;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    .line 40
    invoke-virtual {v0}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->build()Lcom/google/firebase/encoders/FieldDescriptor;

    const-string v0, "v2sFeatureInfo"

    .line 41
    invoke-static {v0}, Lcom/google/firebase/encoders/FieldDescriptor;->builder(Ljava/lang/String;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-direct {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;-><init>()V

    const/16 v2, 0xb

    .line 42
    invoke-virtual {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpv;

    move-result-object v1

    .line 43
    invoke-virtual {v0, v1}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->withProperty(Ljava/lang/annotation/Annotation;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    .line 44
    invoke-virtual {v0}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->build()Lcom/google/firebase/encoders/FieldDescriptor;

    const-string v0, "hatsSurveyInfo"

    .line 45
    invoke-static {v0}, Lcom/google/firebase/encoders/FieldDescriptor;->builder(Ljava/lang/String;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-direct {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;-><init>()V

    const/16 v2, 0xc

    .line 46
    invoke-virtual {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpv;

    move-result-object v1

    .line 47
    invoke-virtual {v0, v1}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->withProperty(Ljava/lang/annotation/Annotation;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    .line 48
    invoke-virtual {v0}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->build()Lcom/google/firebase/encoders/FieldDescriptor;

    const-string v0, "numPageDeletes"

    .line 49
    invoke-static {v0}, Lcom/google/firebase/encoders/FieldDescriptor;->builder(Ljava/lang/String;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-direct {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;-><init>()V

    const/16 v2, 0xd

    .line 50
    invoke-virtual {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpv;

    move-result-object v1

    .line 51
    invoke-virtual {v0, v1}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->withProperty(Ljava/lang/annotation/Annotation;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    .line 52
    invoke-virtual {v0}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->build()Lcom/google/firebase/encoders/FieldDescriptor;

    const-string v0, "maxPagesCapturedInSingleScan"

    .line 53
    invoke-static {v0}, Lcom/google/firebase/encoders/FieldDescriptor;->builder(Ljava/lang/String;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-direct {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;-><init>()V

    const/16 v2, 0xe

    .line 54
    invoke-virtual {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpv;

    move-result-object v1

    .line 55
    invoke-virtual {v0, v1}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->withProperty(Ljava/lang/annotation/Annotation;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    .line 56
    invoke-virtual {v0}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->build()Lcom/google/firebase/encoders/FieldDescriptor;

    const-string v0, "numDuplicateDocumentsDetected"

    .line 57
    invoke-static {v0}, Lcom/google/firebase/encoders/FieldDescriptor;->builder(Ljava/lang/String;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-direct {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;-><init>()V

    const/16 v2, 0xf

    .line 58
    invoke-virtual {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpv;

    move-result-object v1

    .line 59
    invoke-virtual {v0, v1}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->withProperty(Ljava/lang/annotation/Annotation;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    .line 60
    invoke-virtual {v0}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->build()Lcom/google/firebase/encoders/FieldDescriptor;

    const-string v0, "numManualCapturesTriggeredForDuplicateDocument"

    .line 61
    invoke-static {v0}, Lcom/google/firebase/encoders/FieldDescriptor;->builder(Ljava/lang/String;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-direct {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;-><init>()V

    const/16 v2, 0x10

    .line 62
    invoke-virtual {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpv;

    move-result-object v1

    .line 63
    invoke-virtual {v0, v1}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->withProperty(Ljava/lang/annotation/Annotation;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    .line 64
    invoke-virtual {v0}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->build()Lcom/google/firebase/encoders/FieldDescriptor;

    const-string v0, "hasV2sInitializationFailed"

    .line 65
    invoke-static {v0}, Lcom/google/firebase/encoders/FieldDescriptor;->builder(Ljava/lang/String;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-direct {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;-><init>()V

    const/16 v2, 0x11

    .line 66
    invoke-virtual {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpv;

    move-result-object v1

    .line 67
    invoke-virtual {v0, v1}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->withProperty(Ljava/lang/annotation/Annotation;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    .line 68
    invoke-virtual {v0}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->build()Lcom/google/firebase/encoders/FieldDescriptor;

    const-string v0, "numMoveDocumentIntoViewStatusInstances"

    .line 69
    invoke-static {v0}, Lcom/google/firebase/encoders/FieldDescriptor;->builder(Ljava/lang/String;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-direct {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;-><init>()V

    const/16 v2, 0x12

    .line 70
    invoke-virtual {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpv;

    move-result-object v1

    .line 71
    invoke-virtual {v0, v1}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->withProperty(Ljava/lang/annotation/Annotation;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    .line 72
    invoke-virtual {v0}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->build()Lcom/google/firebase/encoders/FieldDescriptor;

    const-string v0, "numPageReorders"

    .line 73
    invoke-static {v0}, Lcom/google/firebase/encoders/FieldDescriptor;->builder(Ljava/lang/String;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-direct {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;-><init>()V

    const/16 v2, 0x13

    .line 74
    invoke-virtual {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpv;

    move-result-object v1

    .line 75
    invoke-virtual {v0, v1}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->withProperty(Ljava/lang/annotation/Annotation;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    .line 76
    invoke-virtual {v0}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->build()Lcom/google/firebase/encoders/FieldDescriptor;

    const-string v0, "sessionInitializationLatencyMs"

    .line 77
    invoke-static {v0}, Lcom/google/firebase/encoders/FieldDescriptor;->builder(Ljava/lang/String;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-direct {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;-><init>()V

    const/16 v2, 0x14

    .line 78
    invoke-virtual {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpv;

    move-result-object v1

    .line 79
    invoke-virtual {v0, v1}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->withProperty(Ljava/lang/annotation/Annotation;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    .line 80
    invoke-virtual {v0}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->build()Lcom/google/firebase/encoders/FieldDescriptor;

    const-string v0, "aggregatedLatencyMetrics"

    .line 81
    invoke-static {v0}, Lcom/google/firebase/encoders/FieldDescriptor;->builder(Ljava/lang/String;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-direct {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;-><init>()V

    const/16 v2, 0x15

    .line 82
    invoke-virtual {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpv;

    move-result-object v1

    .line 83
    invoke-virtual {v0, v1}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->withProperty(Ljava/lang/annotation/Annotation;)Lcom/google/firebase/encoders/FieldDescriptor$Builder;

    move-result-object v0

    .line 84
    invoke-virtual {v0}, Lcom/google/firebase/encoders/FieldDescriptor$Builder;->build()Lcom/google/firebase/encoders/FieldDescriptor;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final bridge synthetic encode(Ljava/lang/Object;Ljava/lang/Object;)V
    .locals 0

    .line 1
    invoke-static {p1}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    check-cast p2, Lcom/google/firebase/encoders/ObjectEncoderContext;

    const/4 p0, 0x0

    .line 2
    throw p0
.end method
