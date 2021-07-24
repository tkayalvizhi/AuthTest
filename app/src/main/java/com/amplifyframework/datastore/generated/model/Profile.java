package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Profile type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Profiles", authRules = {
  @AuthRule(allow = AuthStrategy.OWNER, ownerField = "owner", identityClaim = "cognito:username", provider = "userPools", operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
@Index(name = "profileByOwner", fields = {"owner"})
public final class Profile implements Model {
  public static final QueryField ID = field("Profile", "id");
  public static final QueryField EMAIL = field("Profile", "email");
  public static final QueryField OWNER = field("Profile", "owner");
  public static final QueryField FIRST_NAME = field("Profile", "first_name");
  public static final QueryField LAST_NAME = field("Profile", "last_name");
  public static final QueryField ADDRESS = field("Profile", "address");
  public static final QueryField PH_NUMBER = field("Profile", "ph_number");
  public static final QueryField PROFILE_PICTURE = field("Profile", "profile_picture");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String email;
  private final @ModelField(targetType="String", isRequired = true) String owner;
  private final @ModelField(targetType="String") String first_name;
  private final @ModelField(targetType="String") String last_name;
  private final @ModelField(targetType="String") String address;
  private final @ModelField(targetType="String") String ph_number;
  private final @ModelField(targetType="String") String profile_picture;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getEmail() {
      return email;
  }
  
  public String getOwner() {
      return owner;
  }
  
  public String getFirstName() {
      return first_name;
  }
  
  public String getLastName() {
      return last_name;
  }
  
  public String getAddress() {
      return address;
  }
  
  public String getPhNumber() {
      return ph_number;
  }
  
  public String getProfilePicture() {
      return profile_picture;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Profile(String id, String email, String owner, String first_name, String last_name, String address, String ph_number, String profile_picture) {
    this.id = id;
    this.email = email;
    this.owner = owner;
    this.first_name = first_name;
    this.last_name = last_name;
    this.address = address;
    this.ph_number = ph_number;
    this.profile_picture = profile_picture;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Profile profile = (Profile) obj;
      return ObjectsCompat.equals(getId(), profile.getId()) &&
              ObjectsCompat.equals(getEmail(), profile.getEmail()) &&
              ObjectsCompat.equals(getOwner(), profile.getOwner()) &&
              ObjectsCompat.equals(getFirstName(), profile.getFirstName()) &&
              ObjectsCompat.equals(getLastName(), profile.getLastName()) &&
              ObjectsCompat.equals(getAddress(), profile.getAddress()) &&
              ObjectsCompat.equals(getPhNumber(), profile.getPhNumber()) &&
              ObjectsCompat.equals(getProfilePicture(), profile.getProfilePicture()) &&
              ObjectsCompat.equals(getCreatedAt(), profile.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), profile.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getEmail())
      .append(getOwner())
      .append(getFirstName())
      .append(getLastName())
      .append(getAddress())
      .append(getPhNumber())
      .append(getProfilePicture())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Profile {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("email=" + String.valueOf(getEmail()) + ", ")
      .append("owner=" + String.valueOf(getOwner()) + ", ")
      .append("first_name=" + String.valueOf(getFirstName()) + ", ")
      .append("last_name=" + String.valueOf(getLastName()) + ", ")
      .append("address=" + String.valueOf(getAddress()) + ", ")
      .append("ph_number=" + String.valueOf(getPhNumber()) + ", ")
      .append("profile_picture=" + String.valueOf(getProfilePicture()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static EmailStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static Profile justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Profile(
      id,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      email,
      owner,
      first_name,
      last_name,
      address,
      ph_number,
      profile_picture);
  }
  public interface EmailStep {
    OwnerStep email(String email);
  }
  

  public interface OwnerStep {
    BuildStep owner(String owner);
  }
  

  public interface BuildStep {
    Profile build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep firstName(String firstName);
    BuildStep lastName(String lastName);
    BuildStep address(String address);
    BuildStep phNumber(String phNumber);
    BuildStep profilePicture(String profilePicture);
  }
  

  public static class Builder implements EmailStep, OwnerStep, BuildStep {
    private String id;
    private String email;
    private String owner;
    private String first_name;
    private String last_name;
    private String address;
    private String ph_number;
    private String profile_picture;
    @Override
     public Profile build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Profile(
          id,
          email,
          owner,
          first_name,
          last_name,
          address,
          ph_number,
          profile_picture);
    }
    
    @Override
     public OwnerStep email(String email) {
        Objects.requireNonNull(email);
        this.email = email;
        return this;
    }
    
    @Override
     public BuildStep owner(String owner) {
        Objects.requireNonNull(owner);
        this.owner = owner;
        return this;
    }
    
    @Override
     public BuildStep firstName(String firstName) {
        this.first_name = firstName;
        return this;
    }
    
    @Override
     public BuildStep lastName(String lastName) {
        this.last_name = lastName;
        return this;
    }
    
    @Override
     public BuildStep address(String address) {
        this.address = address;
        return this;
    }
    
    @Override
     public BuildStep phNumber(String phNumber) {
        this.ph_number = phNumber;
        return this;
    }
    
    @Override
     public BuildStep profilePicture(String profilePicture) {
        this.profile_picture = profilePicture;
        return this;
    }
    
    /** 
     * WARNING: Do not set ID when creating a new object. Leave this blank and one will be auto generated for you.
     * This should only be set when referring to an already existing object.
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     * @throws IllegalArgumentException Checks that ID is in the proper format
     */
    public BuildStep id(String id) throws IllegalArgumentException {
        this.id = id;
        
        try {
            UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
        } catch (Exception exception) {
          throw new IllegalArgumentException("Model IDs must be unique in the format of UUID.",
                    exception);
        }
        
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String email, String owner, String firstName, String lastName, String address, String phNumber, String profilePicture) {
      super.id(id);
      super.email(email)
        .owner(owner)
        .firstName(firstName)
        .lastName(lastName)
        .address(address)
        .phNumber(phNumber)
        .profilePicture(profilePicture);
    }
    
    @Override
     public CopyOfBuilder email(String email) {
      return (CopyOfBuilder) super.email(email);
    }
    
    @Override
     public CopyOfBuilder owner(String owner) {
      return (CopyOfBuilder) super.owner(owner);
    }
    
    @Override
     public CopyOfBuilder firstName(String firstName) {
      return (CopyOfBuilder) super.firstName(firstName);
    }
    
    @Override
     public CopyOfBuilder lastName(String lastName) {
      return (CopyOfBuilder) super.lastName(lastName);
    }
    
    @Override
     public CopyOfBuilder address(String address) {
      return (CopyOfBuilder) super.address(address);
    }
    
    @Override
     public CopyOfBuilder phNumber(String phNumber) {
      return (CopyOfBuilder) super.phNumber(phNumber);
    }
    
    @Override
     public CopyOfBuilder profilePicture(String profilePicture) {
      return (CopyOfBuilder) super.profilePicture(profilePicture);
    }
  }
  
}
