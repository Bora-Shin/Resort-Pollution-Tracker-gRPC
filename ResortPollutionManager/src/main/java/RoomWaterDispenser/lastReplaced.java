// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: RoomWaterDispenser.proto

package RoomWaterDispenser;

/**
 * Protobuf type {@code RoomWaterDispenser.lastReplaced}
 */
public  final class lastReplaced extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:RoomWaterDispenser.lastReplaced)
    lastReplacedOrBuilder {
private static final long serialVersionUID = 0L;
  // Use lastReplaced.newBuilder() to construct.
  private lastReplaced(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private lastReplaced() {
    room_ = 0;
    lastReplacedDate_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private lastReplaced(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {

            room_ = input.readInt32();
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            lastReplacedDate_ = s;
            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return RoomWaterDispenser.RoomWaterDispenserImpl.internal_static_RoomWaterDispenser_lastReplaced_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return RoomWaterDispenser.RoomWaterDispenserImpl.internal_static_RoomWaterDispenser_lastReplaced_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            RoomWaterDispenser.lastReplaced.class, RoomWaterDispenser.lastReplaced.Builder.class);
  }

  public static final int ROOM_FIELD_NUMBER = 1;
  private int room_;
  /**
   * <code>int32 room = 1;</code>
   */
  public int getRoom() {
    return room_;
  }

  public static final int LASTREPLACEDDATE_FIELD_NUMBER = 2;
  private volatile java.lang.Object lastReplacedDate_;
  /**
   * <code>string lastReplacedDate = 2;</code>
   */
  public java.lang.String getLastReplacedDate() {
    java.lang.Object ref = lastReplacedDate_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      lastReplacedDate_ = s;
      return s;
    }
  }
  /**
   * <code>string lastReplacedDate = 2;</code>
   */
  public com.google.protobuf.ByteString
      getLastReplacedDateBytes() {
    java.lang.Object ref = lastReplacedDate_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      lastReplacedDate_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (room_ != 0) {
      output.writeInt32(1, room_);
    }
    if (!getLastReplacedDateBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, lastReplacedDate_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (room_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, room_);
    }
    if (!getLastReplacedDateBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, lastReplacedDate_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof RoomWaterDispenser.lastReplaced)) {
      return super.equals(obj);
    }
    RoomWaterDispenser.lastReplaced other = (RoomWaterDispenser.lastReplaced) obj;

    boolean result = true;
    result = result && (getRoom()
        == other.getRoom());
    result = result && getLastReplacedDate()
        .equals(other.getLastReplacedDate());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ROOM_FIELD_NUMBER;
    hash = (53 * hash) + getRoom();
    hash = (37 * hash) + LASTREPLACEDDATE_FIELD_NUMBER;
    hash = (53 * hash) + getLastReplacedDate().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static RoomWaterDispenser.lastReplaced parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static RoomWaterDispenser.lastReplaced parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static RoomWaterDispenser.lastReplaced parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static RoomWaterDispenser.lastReplaced parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static RoomWaterDispenser.lastReplaced parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static RoomWaterDispenser.lastReplaced parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static RoomWaterDispenser.lastReplaced parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static RoomWaterDispenser.lastReplaced parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static RoomWaterDispenser.lastReplaced parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static RoomWaterDispenser.lastReplaced parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static RoomWaterDispenser.lastReplaced parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static RoomWaterDispenser.lastReplaced parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(RoomWaterDispenser.lastReplaced prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code RoomWaterDispenser.lastReplaced}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:RoomWaterDispenser.lastReplaced)
      RoomWaterDispenser.lastReplacedOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return RoomWaterDispenser.RoomWaterDispenserImpl.internal_static_RoomWaterDispenser_lastReplaced_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return RoomWaterDispenser.RoomWaterDispenserImpl.internal_static_RoomWaterDispenser_lastReplaced_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              RoomWaterDispenser.lastReplaced.class, RoomWaterDispenser.lastReplaced.Builder.class);
    }

    // Construct using RoomWaterDispenser.lastReplaced.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      room_ = 0;

      lastReplacedDate_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return RoomWaterDispenser.RoomWaterDispenserImpl.internal_static_RoomWaterDispenser_lastReplaced_descriptor;
    }

    @java.lang.Override
    public RoomWaterDispenser.lastReplaced getDefaultInstanceForType() {
      return RoomWaterDispenser.lastReplaced.getDefaultInstance();
    }

    @java.lang.Override
    public RoomWaterDispenser.lastReplaced build() {
      RoomWaterDispenser.lastReplaced result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public RoomWaterDispenser.lastReplaced buildPartial() {
      RoomWaterDispenser.lastReplaced result = new RoomWaterDispenser.lastReplaced(this);
      result.room_ = room_;
      result.lastReplacedDate_ = lastReplacedDate_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof RoomWaterDispenser.lastReplaced) {
        return mergeFrom((RoomWaterDispenser.lastReplaced)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(RoomWaterDispenser.lastReplaced other) {
      if (other == RoomWaterDispenser.lastReplaced.getDefaultInstance()) return this;
      if (other.getRoom() != 0) {
        setRoom(other.getRoom());
      }
      if (!other.getLastReplacedDate().isEmpty()) {
        lastReplacedDate_ = other.lastReplacedDate_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      RoomWaterDispenser.lastReplaced parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (RoomWaterDispenser.lastReplaced) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int room_ ;
    /**
     * <code>int32 room = 1;</code>
     */
    public int getRoom() {
      return room_;
    }
    /**
     * <code>int32 room = 1;</code>
     */
    public Builder setRoom(int value) {
      
      room_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 room = 1;</code>
     */
    public Builder clearRoom() {
      
      room_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object lastReplacedDate_ = "";
    /**
     * <code>string lastReplacedDate = 2;</code>
     */
    public java.lang.String getLastReplacedDate() {
      java.lang.Object ref = lastReplacedDate_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        lastReplacedDate_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string lastReplacedDate = 2;</code>
     */
    public com.google.protobuf.ByteString
        getLastReplacedDateBytes() {
      java.lang.Object ref = lastReplacedDate_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        lastReplacedDate_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string lastReplacedDate = 2;</code>
     */
    public Builder setLastReplacedDate(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      lastReplacedDate_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string lastReplacedDate = 2;</code>
     */
    public Builder clearLastReplacedDate() {
      
      lastReplacedDate_ = getDefaultInstance().getLastReplacedDate();
      onChanged();
      return this;
    }
    /**
     * <code>string lastReplacedDate = 2;</code>
     */
    public Builder setLastReplacedDateBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      lastReplacedDate_ = value;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:RoomWaterDispenser.lastReplaced)
  }

  // @@protoc_insertion_point(class_scope:RoomWaterDispenser.lastReplaced)
  private static final RoomWaterDispenser.lastReplaced DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new RoomWaterDispenser.lastReplaced();
  }

  public static RoomWaterDispenser.lastReplaced getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<lastReplaced>
      PARSER = new com.google.protobuf.AbstractParser<lastReplaced>() {
    @java.lang.Override
    public lastReplaced parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new lastReplaced(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<lastReplaced> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<lastReplaced> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public RoomWaterDispenser.lastReplaced getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
