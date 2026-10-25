.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzafk;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/firebase/encoders/ObjectEncoder;


# static fields
.field static final zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzafk;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzafk;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzafk;-><init>()V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzafk;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzafk;

    const-string v0, "initialImageUriCount"

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

    const-string v0, "defaultCaptureMode"

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

    const-string v0, "flashModeChangeAllowed"

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

    const-string v0, "galleryImportAllowed"

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

    const-string v0, "multiPageAllowed"

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

    const-string v0, "filterAllowed"

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

    const-string v0, "targetResolutionWidth"

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

    const-string v0, "targetResolutionHeight"

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

    const-string v0, "resultFormats"

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

    const-string v0, "pageEditListenerSet"

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

    const-string v0, "shadowRemovalAllowed"

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

    const-string v0, "stainRemovalAllowed"

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

    const-string v0, "enableAllNewFeaturesByDefault"

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

    const-string v0, "pageLimitMax"

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

    const-string v0, "enableGalleryImportAutoTransform"

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

    const-string v0, "enableComputeHashForGalleryImage"

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

    const-string v0, "enableAutoEnhancements"

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
