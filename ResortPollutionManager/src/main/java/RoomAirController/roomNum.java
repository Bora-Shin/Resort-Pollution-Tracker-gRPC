// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: RoomAir.proto

package RoomAirController;

/**
 * Protobuf type {@code RoomAirController.roomNum}
 */
public  final class roomNum extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:RoomAirController.roomNum)
    roomNumOrBuilder {
private static final long serialVersionUID = 0L;
  // Use roomNum.newBuilder() to construct.
  private roomNum(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private roomNum() {
    room_ = 0;
    temperature_ = 0;
    aqi_ = 0;
    carbonMonoxide_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private roomNum(
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
          case 16: {

            temperature_ = input.readInt32();
            break;
          }
          case 24: {

            aqi_ = input.readInt32();
            break;
          }
          case 32: {

            carbonMonoxide_ = input.readInt32();
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
    return RoomAirController.RoomAirImpl.internal_static_RoomAirController_roomNum_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return RoomAirController.RoomAirImpl.internal_static_RoomAirController_roomNum_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            RoomAirController.roomNum.class, RoomAirController.roomNum.Builder.class);
  }

  public static final int ROOM_FIELD_NUMBER = 1;
  private int room_;
  /**
   * <code>int32 room = 1;</code>
   */
  public int getRoom() {
    return room_;
  }

  public static final int TEMPERATURE_FIELD_NUMBER = 2;
  private int temperature_;
  /**
   * <code>int32 temperature = 2;</code>
   */
  public int getTemperature() {
    return temperature_;
  }

  public static final int AQI_FIELD_NUMBER = 3;
  private int aqi_;
  /**
   * <code>int32 aqi = 3;</code>
   */
  public int getAqi() {
    return aqi_;
  }

  public static final int CARBONMONOXIDE_FIELD_NUMBER = 4;
  private int carbonMonoxide_;
  /**
   * <code>int32 carbonMonoxide = 4;</code>
   */
  public int getCarbonMonoxide() {
    return carbonMonoxide_;
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
    if (temperature_ != 0) {
      output.writeInt32(2, temperature_);
    }
    if (aqi_ != 0) {
      output.writeInt32(3, aqi_);
    }
    if (carbonMonoxide_ != 0) {
      output.writeInt32(4, carbonMonoxide_);
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
    if (temperature_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, temperature_);
    }
    if (aqi_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, aqi_);
    }
    if (carbonMonoxide_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, carbonMonoxide_);
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
    if (!(obj instanceof RoomAirController.roomNum)) {
      return super.equals(obj);
    }
    RoomAirController.roomNum other = (RoomAirController.roomNum) obj;

    boolean result = true;
    result = result && (getRoom()
        == other.getRoom());
    result = result && (getTemperature()
        == other.getTemperature());
    result = result && (getAqi()
        == other.getAqi());
    result = result && (getCarbonMonoxide()
        == other.getCarbonMonoxide());
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
    hash = (37 * hash) + TEMPERATURE_FIELD_NUMBER;
    hash = (53 * hash) + getTemperature();
    hash = (37 * hash) + AQI_FIELD_NUMBER;
    hash = (53 * hash) + getAqi();
    hash = (37 * hash) + CARBONMONOXIDE_FIELD_NUMBER;
    hash = (53 * hash) + getCarbonMonoxide();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static RoomAirController.roomNum parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static RoomAirController.roomNum parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static RoomAirController.roomNum parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static RoomAirController.roomNum parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static RoomAirController.roomNum parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static RoomAirController.roomNum parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static RoomAirController.roomNum parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static RoomAirController.roomNum parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static RoomAirController.roomNum parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static RoomAirController.roomNum parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static RoomAirController.roomNum parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static RoomAirController.roomNum parseFrom(
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
  public static Builder newBuilder(RoomAirController.roomNum prototype) {
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
   * Protobuf type {@code RoomAirController.roomNum}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:RoomAirController.roomNum)
      RoomAirController.roomNumOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return RoomAirController.RoomAirImpl.internal_static_RoomAirController_roomNum_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return RoomAirController.RoomAirImpl.internal_static_RoomAirController_roomNum_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              RoomAirController.roomNum.class, RoomAirController.roomNum.Builder.class);
    }

    // Construct using RoomAirController.roomNum.newBuilder()
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

      temperature_ = 0;

      aqi_ = 0;

      carbonMonoxide_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return RoomAirController.RoomAirImpl.internal_static_RoomAirController_roomNum_descriptor;
    }

    @java.lang.Override
    public RoomAirController.roomNum getDefaultInstanceForType() {
      return RoomAirController.roomNum.getDefaultInstance();
    }

    @java.lang.Override
    public RoomAirController.roomNum build() {
      RoomAirController.roomNum result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public RoomAirController.roomNum buildPartial() {
      RoomAirController.roomNum result = new RoomAirController.roomNum(this);
      result.room_ = room_;
      result.temperature_ = temperature_;
      result.aqi_ = aqi_;
      result.carbonMonoxide_ = carbonMonoxide_;
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
      if (other instanceof RoomAirController.roomNum) {
        return mergeFrom((RoomAirController.roomNum)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(RoomAirController.roomNum other) {
      if (other == RoomAirController.roomNum.getDefaultInstance()) return this;
      if (other.getRoom() != 0) {
        setRoom(other.getRoom());
      }
      if (other.getTemperature() != 0) {
        setTemperature(other.getTemperature());
      }
      if (other.getAqi() != 0) {
        setAqi(other.getAqi());
      }
      if (other.getCarbonMonoxide() != 0) {
        setCarbonMonoxide(other.getCarbonMonoxide());
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
      RoomAirController.roomNum parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (RoomAirController.roomNum) e.getUnfinishedMessage();
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

    private int temperature_ ;
    /**
     * <code>int32 temperature = 2;</code>
     */
    public int getTemperature() {
      return temperature_;
    }
    /**
     * <code>int32 temperature = 2;</code>
     */
    public Builder setTemperature(int value) {
      
      temperature_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 temperature = 2;</code>
     */
    public Builder clearTemperature() {
      
      temperature_ = 0;
      onChanged();
      return this;
    }

    private int aqi_ ;
    /**
     * <code>int32 aqi = 3;</code>
     */
    public int getAqi() {
      return aqi_;
    }
    /**
     * <code>int32 aqi = 3;</code>
     */
    public Builder setAqi(int value) {
      
      aqi_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 aqi = 3;</code>
     */
    public Builder clearAqi() {
      
      aqi_ = 0;
      onChanged();
      return this;
    }

    private int carbonMonoxide_ ;
    /**
     * <code>int32 carbonMonoxide = 4;</code>
     */
    public int getCarbonMonoxide() {
      return carbonMonoxide_;
    }
    /**
     * <code>int32 carbonMonoxide = 4;</code>
     */
    public Builder setCarbonMonoxide(int value) {
      
      carbonMonoxide_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 carbonMonoxide = 4;</code>
     */
    public Builder clearCarbonMonoxide() {
      
      carbonMonoxide_ = 0;
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


    // @@protoc_insertion_point(builder_scope:RoomAirController.roomNum)
  }

  // @@protoc_insertion_point(class_scope:RoomAirController.roomNum)
  private static final RoomAirController.roomNum DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new RoomAirController.roomNum();
  }

  public static RoomAirController.roomNum getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<roomNum>
      PARSER = new com.google.protobuf.AbstractParser<roomNum>() {
    @java.lang.Override
    public roomNum parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new roomNum(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<roomNum> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<roomNum> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public RoomAirController.roomNum getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

