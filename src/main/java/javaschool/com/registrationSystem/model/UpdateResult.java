package javaschool.com.registrationSystem.model;

import lombok.Data;

@Data
public class UpdateResult<T, S> {
   private T object;
   private S resultObject;

}
