package com.ecs.latam.commons.schemas;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class TransformationObject extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
    private static final long serialVersionUID = -2205515669281208123L;
    public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"TransformationObject\",\"namespace\":\"com.ecs.latam.commons.schemas\",\"fields\":[{\"name\":\"bankCode\",\"type\":\"string\",\"doc\":\"code of bank\"},{\"name\":\"fileName\",\"type\":\"string\",\"doc\":\"name of file\"},{\"name\":\"process\",\"type\":\"string\",\"doc\":\"name or identifier of process\"}]}");
    public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

    private static SpecificData MODEL$ = new SpecificData();

    private static final BinaryMessageEncoder<TransformationObject> ENCODER =
            new BinaryMessageEncoder<TransformationObject>(MODEL$, SCHEMA$);

    private static final BinaryMessageDecoder<TransformationObject> DECODER =
            new BinaryMessageDecoder<TransformationObject>(MODEL$, SCHEMA$);

    /**
     * Return the BinaryMessageEncoder instance used by this class.
     * @return the message encoder used by this class
     */
    public static BinaryMessageEncoder<TransformationObject> getEncoder() {
        return ENCODER;
    }

    /**
     * Return the BinaryMessageDecoder instance used by this class.
     * @return the message decoder used by this class
     */
    public static BinaryMessageDecoder<TransformationObject> getDecoder() {
        return DECODER;
    }

    /**
     * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
     * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
     * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
     */
    public static BinaryMessageDecoder<TransformationObject> createDecoder(SchemaStore resolver) {
        return new BinaryMessageDecoder<TransformationObject>(MODEL$, SCHEMA$, resolver);
    }

    /**
     * Serializes this TransformationObject to a ByteBuffer.
     * @return a buffer holding the serialized data for this instance
     * @throws java.io.IOException if this instance could not be serialized
     */
    public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
        return ENCODER.encode(this);
    }

    /**
     * Deserializes a TransformationObject from a ByteBuffer.
     * @param b a byte buffer holding serialized data for an instance of this class
     * @return a TransformationObject instance decoded from the given buffer
     * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
     */
    public static TransformationObject fromByteBuffer(
            java.nio.ByteBuffer b) throws java.io.IOException {
        return DECODER.decode(b);
    }

    /** code of bank */
    private java.lang.CharSequence bankCode;
    /** name of file */
    private java.lang.CharSequence fileName;
    /** name or identifier of process */
    private java.lang.CharSequence process;

    /**
     * Default constructor.  Note that this does not initialize fields
     * to their default values from the schema.  If that is desired then
     * one should use <code>newBuilder()</code>.
     */
    public TransformationObject() {}

    /**
     * All-args constructor.
     * @param bankCode code of bank
     * @param fileName name of file
     * @param process name or identifier of process
     */
    public TransformationObject(java.lang.CharSequence bankCode, java.lang.CharSequence fileName, java.lang.CharSequence process) {
        this.bankCode = bankCode;
        this.fileName = fileName;
        this.process = process;
    }

    public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
    public org.apache.avro.Schema getSchema() { return SCHEMA$; }
    // Used by DatumWriter.  Applications should not call.
    public java.lang.Object get(int field$) {
        switch (field$) {
            case 0: return bankCode;
            case 1: return fileName;
            case 2: return process;
            default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
        }
    }

    // Used by DatumReader.  Applications should not call.
    @SuppressWarnings(value="unchecked")
    public void put(int field$, java.lang.Object value$) {
        switch (field$) {
            case 0: bankCode = (java.lang.CharSequence)value$; break;
            case 1: fileName = (java.lang.CharSequence)value$; break;
            case 2: process = (java.lang.CharSequence)value$; break;
            default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
        }
    }

    /**
     * Gets the value of the 'bankCode' field.
     * @return code of bank
     */
    public java.lang.CharSequence getBankCode() {
        return bankCode;
    }


    /**
     * Sets the value of the 'bankCode' field.
     * code of bank
     * @param value the value to set.
     */
    public void setBankCode(java.lang.CharSequence value) {
        this.bankCode = value;
    }

    /**
     * Gets the value of the 'fileName' field.
     * @return name of file
     */
    public java.lang.CharSequence getFileName() {
        return fileName;
    }


    /**
     * Sets the value of the 'fileName' field.
     * name of file
     * @param value the value to set.
     */
    public void setFileName(java.lang.CharSequence value) {
        this.fileName = value;
    }

    /**
     * Gets the value of the 'process' field.
     * @return name or identifier of process
     */
    public java.lang.CharSequence getProcess() {
        return process;
    }


    /**
     * Sets the value of the 'process' field.
     * name or identifier of process
     * @param value the value to set.
     */
    public void setProcess(java.lang.CharSequence value) {
        this.process = value;
    }

    /**
     * Creates a new TransformationObject RecordBuilder.
     * @return A new TransformationObject RecordBuilder
     */
    public static com.ecs.latam.commons.schemas.TransformationObject.Builder newBuilder() {
        return new com.ecs.latam.commons.schemas.TransformationObject.Builder();
    }

    /**
     * Creates a new TransformationObject RecordBuilder by copying an existing Builder.
     * @param other The existing builder to copy.
     * @return A new TransformationObject RecordBuilder
     */
    public static com.ecs.latam.commons.schemas.TransformationObject.Builder newBuilder(com.ecs.latam.commons.schemas.TransformationObject.Builder other) {
        if (other == null) {
            return new com.ecs.latam.commons.schemas.TransformationObject.Builder();
        } else {
            return new com.ecs.latam.commons.schemas.TransformationObject.Builder(other);
        }
    }

    /**
     * Creates a new TransformationObject RecordBuilder by copying an existing TransformationObject instance.
     * @param other The existing instance to copy.
     * @return A new TransformationObject RecordBuilder
     */
    public static com.ecs.latam.commons.schemas.TransformationObject.Builder newBuilder(com.ecs.latam.commons.schemas.TransformationObject other) {
        if (other == null) {
            return new com.ecs.latam.commons.schemas.TransformationObject.Builder();
        } else {
            return new com.ecs.latam.commons.schemas.TransformationObject.Builder(other);
        }
    }

    /**
     * RecordBuilder for TransformationObject instances.
     */
    @org.apache.avro.specific.AvroGenerated
    public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<TransformationObject>
            implements org.apache.avro.data.RecordBuilder<TransformationObject> {

        /** code of bank */
        private java.lang.CharSequence bankCode;
        /** name of file */
        private java.lang.CharSequence fileName;
        /** name or identifier of process */
        private java.lang.CharSequence process;

        /** Creates a new Builder */
        private Builder() {
            super(SCHEMA$);
        }

        /**
         * Creates a Builder by copying an existing Builder.
         * @param other The existing Builder to copy.
         */
        private Builder(com.ecs.latam.commons.schemas.TransformationObject.Builder other) {
            super(other);
            if (isValidValue(fields()[0], other.bankCode)) {
                this.bankCode = data().deepCopy(fields()[0].schema(), other.bankCode);
                fieldSetFlags()[0] = other.fieldSetFlags()[0];
            }
            if (isValidValue(fields()[1], other.fileName)) {
                this.fileName = data().deepCopy(fields()[1].schema(), other.fileName);
                fieldSetFlags()[1] = other.fieldSetFlags()[1];
            }
            if (isValidValue(fields()[2], other.process)) {
                this.process = data().deepCopy(fields()[2].schema(), other.process);
                fieldSetFlags()[2] = other.fieldSetFlags()[2];
            }
        }

        /**
         * Creates a Builder by copying an existing TransformationObject instance
         * @param other The existing instance to copy.
         */
        private Builder(com.ecs.latam.commons.schemas.TransformationObject other) {
            super(SCHEMA$);
            if (isValidValue(fields()[0], other.bankCode)) {
                this.bankCode = data().deepCopy(fields()[0].schema(), other.bankCode);
                fieldSetFlags()[0] = true;
            }
            if (isValidValue(fields()[1], other.fileName)) {
                this.fileName = data().deepCopy(fields()[1].schema(), other.fileName);
                fieldSetFlags()[1] = true;
            }
            if (isValidValue(fields()[2], other.process)) {
                this.process = data().deepCopy(fields()[2].schema(), other.process);
                fieldSetFlags()[2] = true;
            }
        }

        /**
         * Gets the value of the 'bankCode' field.
         * code of bank
         * @return The value.
         */
        public java.lang.CharSequence getBankCode() {
            return bankCode;
        }


        /**
         * Sets the value of the 'bankCode' field.
         * code of bank
         * @param value The value of 'bankCode'.
         * @return This builder.
         */
        public com.ecs.latam.commons.schemas.TransformationObject.Builder setBankCode(java.lang.CharSequence value) {
            validate(fields()[0], value);
            this.bankCode = value;
            fieldSetFlags()[0] = true;
            return this;
        }

        /**
         * Checks whether the 'bankCode' field has been set.
         * code of bank
         * @return True if the 'bankCode' field has been set, false otherwise.
         */
        public boolean hasBankCode() {
            return fieldSetFlags()[0];
        }


        /**
         * Clears the value of the 'bankCode' field.
         * code of bank
         * @return This builder.
         */
        public com.ecs.latam.commons.schemas.TransformationObject.Builder clearBankCode() {
            bankCode = null;
            fieldSetFlags()[0] = false;
            return this;
        }

        /**
         * Gets the value of the 'fileName' field.
         * name of file
         * @return The value.
         */
        public java.lang.CharSequence getFileName() {
            return fileName;
        }


        /**
         * Sets the value of the 'fileName' field.
         * name of file
         * @param value The value of 'fileName'.
         * @return This builder.
         */
        public com.ecs.latam.commons.schemas.TransformationObject.Builder setFileName(java.lang.CharSequence value) {
            validate(fields()[1], value);
            this.fileName = value;
            fieldSetFlags()[1] = true;
            return this;
        }

        /**
         * Checks whether the 'fileName' field has been set.
         * name of file
         * @return True if the 'fileName' field has been set, false otherwise.
         */
        public boolean hasFileName() {
            return fieldSetFlags()[1];
        }


        /**
         * Clears the value of the 'fileName' field.
         * name of file
         * @return This builder.
         */
        public com.ecs.latam.commons.schemas.TransformationObject.Builder clearFileName() {
            fileName = null;
            fieldSetFlags()[1] = false;
            return this;
        }

        /**
         * Gets the value of the 'process' field.
         * name or identifier of process
         * @return The value.
         */
        public java.lang.CharSequence getProcess() {
            return process;
        }


        /**
         * Sets the value of the 'process' field.
         * name or identifier of process
         * @param value The value of 'process'.
         * @return This builder.
         */
        public com.ecs.latam.commons.schemas.TransformationObject.Builder setProcess(java.lang.CharSequence value) {
            validate(fields()[2], value);
            this.process = value;
            fieldSetFlags()[2] = true;
            return this;
        }

        /**
         * Checks whether the 'process' field has been set.
         * name or identifier of process
         * @return True if the 'process' field has been set, false otherwise.
         */
        public boolean hasProcess() {
            return fieldSetFlags()[2];
        }


        /**
         * Clears the value of the 'process' field.
         * name or identifier of process
         * @return This builder.
         */
        public com.ecs.latam.commons.schemas.TransformationObject.Builder clearProcess() {
            process = null;
            fieldSetFlags()[2] = false;
            return this;
        }

        @Override
        @SuppressWarnings("unchecked")
        public TransformationObject build() {
            try {
                TransformationObject record = new TransformationObject();
                record.bankCode = fieldSetFlags()[0] ? this.bankCode : (java.lang.CharSequence) defaultValue(fields()[0]);
                record.fileName = fieldSetFlags()[1] ? this.fileName : (java.lang.CharSequence) defaultValue(fields()[1]);
                record.process = fieldSetFlags()[2] ? this.process : (java.lang.CharSequence) defaultValue(fields()[2]);
                return record;
            } catch (org.apache.avro.AvroMissingFieldException e) {
                throw e;
            } catch (java.lang.Exception e) {
                throw new org.apache.avro.AvroRuntimeException(e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static final org.apache.avro.io.DatumWriter<TransformationObject>
            WRITER$ = (org.apache.avro.io.DatumWriter<TransformationObject>)MODEL$.createDatumWriter(SCHEMA$);

    @Override public void writeExternal(java.io.ObjectOutput out)
            throws java.io.IOException {
        WRITER$.write(this, SpecificData.getEncoder(out));
    }

    @SuppressWarnings("unchecked")
    private static final org.apache.avro.io.DatumReader<TransformationObject>
            READER$ = (org.apache.avro.io.DatumReader<TransformationObject>)MODEL$.createDatumReader(SCHEMA$);

    @Override public void readExternal(java.io.ObjectInput in)
            throws java.io.IOException {
        READER$.read(this, SpecificData.getDecoder(in));
    }

    @Override protected boolean hasCustomCoders() { return true; }

    @Override public void customEncode(org.apache.avro.io.Encoder out)
            throws java.io.IOException
    {
        out.writeString(this.bankCode);

        out.writeString(this.fileName);

        out.writeString(this.process);

    }

    @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
            throws java.io.IOException
    {
        org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
        if (fieldOrder == null) {
            this.bankCode = in.readString(this.bankCode instanceof Utf8 ? (Utf8)this.bankCode : null);

            this.fileName = in.readString(this.fileName instanceof Utf8 ? (Utf8)this.fileName : null);

            this.process = in.readString(this.process instanceof Utf8 ? (Utf8)this.process : null);

        } else {
            for (int i = 0; i < 3; i++) {
                switch (fieldOrder[i].pos()) {
                    case 0:
                        this.bankCode = in.readString(this.bankCode instanceof Utf8 ? (Utf8)this.bankCode : null);
                        break;

                    case 1:
                        this.fileName = in.readString(this.fileName instanceof Utf8 ? (Utf8)this.fileName : null);
                        break;

                    case 2:
                        this.process = in.readString(this.process instanceof Utf8 ? (Utf8)this.process : null);
                        break;

                    default:
                        throw new java.io.IOException("Corrupt ResolvingDecoder.");
                }
            }
        }
    }
}











