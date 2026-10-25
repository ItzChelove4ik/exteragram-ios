.class public interface abstract Lcom/google/protobuf/MessageLite;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/protobuf/MessageLiteOrBuilder;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/google/protobuf/MessageLite$Builder;
    }
.end annotation


# virtual methods
.method public abstract getSerializedSize()I
.end method

.method public abstract newBuilderForType()Lcom/google/protobuf/MessageLite$Builder;
.end method

.method public abstract toBuilder()Lcom/google/protobuf/MessageLite$Builder;
.end method

.method public abstract writeTo(Lcom/google/protobuf/CodedOutputStream;)V
.end method
