
package com.vi.migrationtool.schemas.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "value",
    "readOnly"
})
public class MultitenancyEnabled {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("value")
    private Boolean value;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("readOnly")
    private Boolean readOnly;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MultitenancyEnabled() {
    }

    /**
     * 
     * @param readOnly
     * @param value
     */
    public MultitenancyEnabled(Boolean value, Boolean readOnly) {
        super();
        this.value = value;
        this.readOnly = readOnly;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("value")
    public Boolean getValue() {
        return value;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("value")
    public void setValue(Boolean value) {
        this.value = value;
    }

    public MultitenancyEnabled withValue(Boolean value) {
        this.value = value;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("readOnly")
    public Boolean getReadOnly() {
        return readOnly;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("readOnly")
    public void setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
    }

    public MultitenancyEnabled withReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(value).append(readOnly).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MultitenancyEnabled) == false) {
            return false;
        }
        MultitenancyEnabled rhs = ((MultitenancyEnabled) other);
        return new EqualsBuilder().append(value, rhs.value).append(readOnly, rhs.readOnly).isEquals();
    }

}
